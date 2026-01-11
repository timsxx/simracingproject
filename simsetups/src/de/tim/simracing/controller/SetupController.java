package de.tim.simracing.controller;

import de.tim.simracing.models.Setup;
import de.tim.simracing.storage.SetupStorage;
import java.util.ArrayList;

// Controller verwaltet einfach nur die Liste der Setups
public class SetupController {

    // Liste aller Setups im Speicher
    private ArrayList<Setup> setups = new ArrayList<>();

    // Speichern/Laden über diese Klasse
    private SetupStorage storage = new SetupStorage();

    public SetupController() {
        // Beim Start direkt versuchen zu laden
        setups = storage.loadSetups();
    }

    // Neues Setup hinzufügen
    public void addSetup(String car, String track, String aeroBalance,
                         double suspension, double camberFront, double camberBack,
                         double tractionControl, double abs, String notes) {

        // Einfach neues Objekt erstellen und in die Liste packen
        setups.add(new Setup(
                car, track, aeroBalance,
                suspension, camberFront, camberBack,
                tractionControl, abs, notes
        ));
    }

    // Alles speichern
    public void save() {
        storage.saveSetups(setups);
    }

    // Datei neu einlesen 
    public void reloadFromFile() {
        setups = storage.loadSetups();
    }

    // Ganze Liste zurückgeben
    public ArrayList<Setup> getSetups() {
        return setups;
    }

    // Einzelnes Setup holen
    public Setup getSetup(int index) {
        if (index >= 0 && index < setups.size()) {
            return setups.get(index);
        }
        return null; // Studentenlösung: einfach null zurück
    }

    // Setup löschen
    public void deleteSetup(int index) {
        if (index >= 0 && index < setups.size()) {
            setups.remove(index);
            save(); // nach dem Löschen direkt speichern
        }
    }

    // Setup bearbeiten
    public void updateSetup(int index, String car, String track, String aeroBalance,
                            double suspension, double camberFront, double camberBack,
                            double tractionControl, double abs, String notes) {

        if (index >= 0 && index < setups.size()) {

            // Setup holen und Werte überschreiben
            Setup s = setups.get(index);

            s.setCar(car);
            s.setTrack(track);
            s.setAeroBalance(aeroBalance);
            s.setSuspensionStiffness(suspension);
            s.setCamberFront(camberFront);
            s.setCamberBack(camberBack);
            s.setTractionControl(tractionControl);
            s.setAbs(abs);
            s.setNotes(notes);

            save(); 
        }
    

        System.out.println("Fahrwerk: " + suspension);
        System.out.println("Camber vorne: " + camberFront);
        System.out.println("Camber hinten: " + camberBack);
        System.out.println("Traktionskontrolle: " + tractionControl);
        System.out.println("ABS: " + abs);
        System.out.println("Notizen: " + notes);
    }
}