package de.tim.simracing.view;

import de.tim.simracing.controller.SetupController;
import de.tim.simracing.models.Setup;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SetupForm extends JFrame {

    // Index zum Bearbeiten, -1 = neues Setup
    private int editingIndex = -1;

    private SetupController controller = new SetupController();

    // Eingabefelder
    private JTextField carField = new JTextField(20);
    private JTextField trackField = new JTextField(20);
    private JTextField aeroField = new JTextField(10);
    private JTextField suspensionField = new JTextField(5);
    private JTextField camberfField = new JTextField(5);
    private JTextField camberbField = new JTextField(5);
    private JTextField tractioncontrol = new JTextField(5);
    private JTextField abs = new JTextField(5);
    private JTextArea notesArea = new JTextArea(5, 20);

    // Tabelle
    private DefaultTableModel tableModel = new DefaultTableModel(
            new String[]{"Auto", "Strecke", "Aero", "Federung", "Sturz Vorne", "Sturz Hinten", "TC", "ABS", "Notizen"}, 0
    );

    private JTable table = new JTable(tableModel);

    public SetupForm() {
        setTitle("Simracing Setup Manager");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Setup eingeben", buildInputTab());
        tabs.add("Setup Liste", buildListTab());

        // Beim Wechsel zur Liste neu laden
        tabs.addChangeListener(e -> {
            if (tabs.getSelectedIndex() == 1) {
                loadTable();
            }
        });

        add(tabs);
        setVisible(true);
    }

    private JPanel buildInputTab() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(11, 2));

        // Labels + Felder
        inputPanel.add(new JLabel("Auto:"));
        inputPanel.add(carField);

        inputPanel.add(new JLabel("Strecke:"));
        inputPanel.add(trackField);

        inputPanel.add(new JLabel("Aero-Balance:"));
        inputPanel.add(aeroField);

        inputPanel.add(new JLabel("Federung:"));
        inputPanel.add(suspensionField);

        inputPanel.add(new JLabel("Sturz Vorne:"));
        inputPanel.add(camberfField);

        inputPanel.add(new JLabel("Sturz Hinten:"));
        inputPanel.add(camberbField);

        inputPanel.add(new JLabel("Traction Control:"));
        inputPanel.add(tractioncontrol);

        inputPanel.add(new JLabel("ABS:"));
        inputPanel.add(abs);

        inputPanel.add(new JLabel("Notizen:"));
        inputPanel.add(new JScrollPane(notesArea));

        JButton saveButton = new JButton("Speichern");
        saveButton.addActionListener(e -> saveSetup());

        JPanel bottom = new JPanel();
        bottom.add(saveButton);

        panel.add(inputPanel, BorderLayout.CENTER);
        panel.add(bottom, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel buildListTab() {
        JPanel panel = new JPanel(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton deleteButton = new JButton("Löschen");
        deleteButton.addActionListener(e -> deleteSelected());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Klick auf Zeile = bearbeiten
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                if (row >= 0) {
                    editSelected(row);
                }
            }
        });

        return panel;
    }

    private void editSelected(int row) {
        Setup s = controller.getSetup(row);
        if (s == null) return;

        // Werte in die Felder laden
        carField.setText(s.getCar());
        trackField.setText(s.getTrack());
        aeroField.setText(s.getAeroBalance());
        suspensionField.setText(String.valueOf(s.getSuspensionStiffness()));
        camberfField.setText(String.valueOf(s.getCamberFront()));
        camberbField.setText(String.valueOf(s.getCamberBack()));
        tractioncontrol.setText(String.valueOf(s.getTractionControl()));
        abs.setText(String.valueOf(s.getAbs()));
        notesArea.setText(s.getNotes());

        editingIndex = row;
    }

    private void saveSetup() {
        try {
            // Eingaben holen
            String car = carField.getText();
            String track = trackField.getText();
            String aero = aeroField.getText();

            // Zahlenfelder (einfach gehalten)
            double suspension = parseDoubleOrZero(suspensionField.getText());
            double camberF = parseDoubleOrZero(camberfField.getText());
            double camberB = parseDoubleOrZero(camberbField.getText());
            double tc = parseDoubleOrZero(tractioncontrol.getText());
            double absVal = parseDoubleOrZero(abs.getText());

            String notes = notesArea.getText();

            // Neu oder Update
            if (editingIndex == -1) {
                controller.addSetup(car, track, aero, suspension, camberF, camberB, tc, absVal, notes);
            } else {
                controller.updateSetup(editingIndex, car, track, aero, suspension, camberF, camberB, tc, absVal, notes);
            }

            controller.save();
            loadTable();
            editingIndex = -1;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Fehler: " + ex.getMessage());
        }
    }

    private double parseDoubleOrZero(String txt) {
        if (txt == null || txt.trim().isEmpty()) return 0.0;
        try {
            return Double.parseDouble(txt);
        } catch (Exception e) {
            return 0.0; 
        }
    }

    private void loadTable() {
        tableModel.setRowCount(0);
        controller.reloadFromFile();

        for (Setup s : controller.getSetups()) {
            tableModel.addRow(new Object[]{
                    s.getCar(),
                    s.getTrack(),
                    s.getAeroBalance(),
                    s.getSuspensionStiffness(),
                    s.getCamberFront(),
                    s.getCamberBack(),
                    s.getTractionControl(),
                    s.getAbs(),
                    s.getNotes()
            });
        }
    }

    private void deleteSelected() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bitte ein Setup auswählen.");
            return;
        }
        controller.deleteSetup(row);
        loadTable();
    }
}
