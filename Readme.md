# Simracing Setup Manager

Java-Projekt für das Modul "Programmieren mit Java" an der PFH Private Hochschule.

## Beschreibung

Dieses Projekt ermöglicht das Erfassen, Anzeigen, Bearbeiten und Löschen von Simracing-Setups.  
Die Daten werden in einer Textdatei gespeichert und beim Programmstart wieder geladen.

Die Anwendung wurde mit Java Swing umgesetzt und erfüllt die grundlegenden Anforderungen des Moduls.

## Funktionen

- Eingabemaske für ein Setup
- Liste aller gespeicherten Setups
- Bearbeiten durch Klick auf eine Tabellenzeile
- Löschen eines ausgewählten Setups
- Speicherung in einer Textdatei (setups.txt)
- Laden der Daten beim Programmstart
- Event Handling über Buttons und MouseListener
- Objektorientierter Aufbau (Model, View, Controller, Storage)

## Projektstruktur

simsetups/
├─ src/de/tim/simracing/
│   ├─ controller/
│   │   └─ SetupController.java
│   ├─ models/
│   │   └─ Setup.java
│   ├─ storage/
│   │   └─ SetupStorage.java
│   └─ view/
│       └─ SetupForm.java
├─ setups.txt
├─ icon.png
└─ .gitignore

## User Stories

1. Als Nutzer möchte ich ein neues Setup eingeben, um meine Einstellungen speichern zu können.
2. Als Nutzer möchte ich eine Liste aller Setups sehen, um einen Überblick zu haben.
3. Als Nutzer möchte ich ein Setup bearbeiten können, um Änderungen vorzunehmen.
4. Als Nutzer möchte ich ein Setup löschen können, um veraltete Einträge zu entfernen.
5. Als Nutzer möchte ich, dass meine Daten gespeichert bleiben, um sie beim nächsten Start wieder nutzen zu können.
6. Als Nutzer möchte ich zwischen Eingabe und Liste wechseln können, um die Anwendung einfach bedienen zu können.

## Wireframes (ASCII)

### Setup eingeben

+------------------------------------------------------+
|                 Setup eingeben                       |
+------------------------------------------------------+
| Auto:              [________________________]        |
| Strecke:           [________________________]        |
| Aero-Balance:      [________]                        |
| Federung:          [________]                        |
| Sturz Vorne:       [________]                        |
| Sturz Hinten:      [________]                        |
| Traction Control:  [________]                        |
| ABS:               [________]                        |
| Notizen:                                           |
|  --------------------------------------------------  |
| |                                                  | |
| |                                                  | |
| |                                                  | |
|  --------------------------------------------------  |
|                                                      |
|                     [ Speichern ]                    |
+------------------------------------------------------+

### Setup Liste

+------------------------------------------------------+
|                    Setup Liste                       |
+------------------------------------------------------+
|  Auto     | Strecke     | Aero | Fed. | Sturz | ...  |
|------------------------------------------------------|
| Porsche   | Spa         | 3.5  | 7.0  | -3.4  | ...  |
| BMW M4    | Nürburgring | 2.8  | 6.0  | -3.0  | ...  |
| Audi R8   | RBR         | 4.0  | 8.0  | -3.8  | ...  |
| ...                                                ...|
+------------------------------------------------------+
|                 [ Löschen ]                          |
+------------------------------------------------------+

### Setup bearbeiten

+------------------------------------------------------+
|              Setup bearbeiten (aus Liste)            |
+------------------------------------------------------+
| Auto:              [Porsche 911 GT3 R__________]     |
| Strecke:           [Spa-Francorchamps__________]     |
| Aero-Balance:      [3.5____]                         |
| Federung:          [7.0____]                         |
| Sturz Vorne:       [-3.4___]                         |
| Sturz Hinten:      [-2.2___]                         |
| Traction Control:  [4______]                         |
| ABS:               [3______]                         |
| Notizen:                                           |
|  --------------------------------------------------  |
| | Regen-Setup, stabil in Eau Rouge                 | |
| |                                                  | |
|  --------------------------------------------------  |
|                                                      |
|                     [ Speichern ]                    |
+------------------------------------------------------+

## Anforderungen erfüllt

- Eingabemaske ✔
- Listenansicht ✔
- Bearbeiten ✔
- Löschen ✔
- Dateispeicherung ✔
- Event Handling ✔
- Objektorientierter Aufbau ✔
- User Stories ✔
- Wireframes ✔

## Autor

Tim Süßemilch  
PFH Private Hochschule  
1. Semester – Programmieren mit Java