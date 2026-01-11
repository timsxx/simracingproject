package de.tim.simracing.models;

// Einfache Datenklasse für ein Setup
public class Setup {

    // Attribute 
    private String car;
    private String track;
    private String aeroBalance;
    private double suspensionStiffness;
    private double camberFront;
    private double camberBack;
    private double tractionControl;
    private double abs;
    private String notes;

    // Konstruktor nimmt einfach alle Werte entgegen
    public Setup(String car,
                 String track,
                 String aeroBalance,
                 double suspensionStiffness,
                 double camberFront,
                 double camberBack,
                 double tractionControl,
                 double abs,
                 String notes) {

        // Direktes Zuweisen
        this.car = car;
        this.track = track;
        this.aeroBalance = aeroBalance;
        this.suspensionStiffness = suspensionStiffness;
        this.camberFront = camberFront;
        this.camberBack = camberBack;
        this.tractionControl = tractionControl;
        this.abs = abs;
        this.notes = notes;
    }

    // Getter – einfach gehalten
    public String getCar() { return car; }
    public String getTrack() { return track; }
    public String getAeroBalance() { return aeroBalance; }
    public double getSuspensionStiffness() { return suspensionStiffness; }
    public double getCamberFront() { return camberFront; }
    public double getCamberBack() { return camberBack; }
    public double getTractionControl() { return tractionControl; }
    public double getAbs() { return abs; }
    public String getNotes() { return notes; }

    // Setter – falls man später doch mal was ändern will
    public void setCar(String car) { this.car = car; }
    public void setTrack(String track) { this.track = track; }
    public void setAeroBalance(String aeroBalance) { this.aeroBalance = aeroBalance; }
    public void setSuspensionStiffness(double suspensionStiffness) { this.suspensionStiffness = suspensionStiffness; }
    public void setCamberFront(double camberFront) { this.camberFront = camberFront; }
    public void setCamberBack(double camberBack) { this.camberBack = camberBack; }
    public void setTractionControl(double tractionControl) { this.tractionControl = tractionControl; }
    public void setAbs(double abs) { this.abs = abs; }
    public void setNotes(String notes) { this.notes = notes; }

    // Kleine Debug-Ausgabe, falls man mal schauen will was drinsteht
    public void print() {
        System.out.println("Auto: " + car);
        System.out.println("Strecke: " + track);
        System.out.println("Aero: " + aeroBalance);
        System.out.println("Federung: " + suspensionStiffness);
        System.out.println("Sturz Vorne: " + camberFront);
        System.out.println("Sturz Hinten: " + camberBack);
        System.out.println("Traction Control: " + tractionControl);
        System.out.println("ABS: " + abs);
        System.out.println("Notizen: " + notes);
        System.out.println("---------------------------");
    }
}
        