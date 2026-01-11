package de.tim.simracing.storage;

import de.tim.simracing.models.Setup;
import java.io.*;
import java.util.ArrayList;

public class SetupStorage {

    // Datei, in der alles gespeichert wird
    private String filePath = "setups.txt";

    // Speichert alle Setups in eine einfache Textdatei
    public void saveSetups(ArrayList<Setup> setups) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            // Jede Zeile = ein Setup
            for (Setup s : setups) {
                writer.write(
                        s.getCar() + ";" +
                        s.getTrack() + ";" +
                        s.getAeroBalance() + ";" +
                        s.getSuspensionStiffness() + ";" +
                        s.getCamberFront() + ";" +
                        s.getCamberBack() + ";" +
                        s.getTractionControl() + ";" +
                        s.getAbs() + ";" +
                        s.getNotes()
                );
                writer.newLine();
            }

            System.out.println("Setups gespeichert.");

        } catch (IOException e) {

            System.out.println("Fehler beim Speichern: " + e.getMessage());
        }
    }

    // Lädt alle Setups aus der Datei
    public ArrayList<Setup> loadSetups() {
        ArrayList<Setup> setups = new ArrayList<>();

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Keine Datei gefunden, starte mit leerer Liste.");
            return setups;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                String[] p = line.split(";", -1);

                // Falls die Zeile nicht passt -> überspringen
                if (p.length != 9) {
                    System.out.println("Ungültige Zeile " + lineNumber + ": " + line);
                    continue;
                }

                // Neues Setup aus der Zeile bauen
                setups.add(new Setup(
                        p[0],                  // Auto
                        p[1],                  // Strecke
                        p[2],                  // Aero
                        parseDoubleSafe(p[3]), // Federung
                        parseDoubleSafe(p[4]), // Sturz vorne
                        parseDoubleSafe(p[5]), // Sturz hinten
                        parseDoubleSafe(p[6]), // TC
                        parseDoubleSafe(p[7]), // ABS
                        p[8]                   // Notizen
                ));
            }

            System.out.println("Setups geladen.");

        } catch (IOException e) {
            System.out.println("Fehler beim Laden: " + e.getMessage());
        }

        return setups;
    }

    // Wandelt Strings in Double um, Fehler = 0.0
    private double parseDoubleSafe(String s) {
        try {
            if (s == null || s.trim().isEmpty()) return 0.0;
            return Double.parseDouble(s);
        } catch (Exception e) {
            
            return 0.0;
        }
    }
}
