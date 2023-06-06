import ressourcen.Ressource;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class MainFrame {
    private JTextPane logPane;
    private JComboBox topComboBox;
    private JPanel screen;
    private JPanel backScreen;
    private JTextPane statusPane;
    private JTextPane ressourcenPane;
    private JTextArea spielbrettArea;
    private JScrollPane spielbrettScrollPane;
    private JButton saveBtn;
    Map<Class<? extends Ressource>, Ressource> ressourcenMap;
    Spieler spieler;
    Status status;
    //
    private boolean activeFight = false;


    public MainFrame () {
        // Erstelle einen Timer mit einer Aktualisierungsrate von 1 Sekunde (1000 Millisekunden)
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hier kannst du den Inhalt der JTextPane aktualisieren
                updateStatusPane();     // Methode aufrufen, um den Inhalt des statusPane zu aktualisieren
                updateLogPane();        // Methode aufrufen, um den Inhalt des logPane zu aktualisieren
                updateRessourcenPane(); // Methode aufrufen, um den Inhalt des ressourcenPane zu aktualisieren
                updateScreenPane(); //

                buttons();
            }
        });
        timer.start();
    }


    private void buttons() {
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Speichern speicher = new Speichern("ressourcen.dat");
                //speicher.speichern(ressourcenMap, statusMessage, ressourcenMessage);    // ressourcenManager.getRessourcenMap()
            }
        });
    }

    public JPanel getRootPanel() {
        return backScreen;
    }

    public void setScreen(String message) {
        spielbrettArea.setText(message);
    }
    public void setLogPane(String message) {
        logPane.setText(message);
    }
    public void setStatusPane(String message) {
        statusPane.setText(message);
    }
    public void setRessourcenPane(String message) {
        ressourcenPane.setText(message);
    }

    // Methode zum Aktualisieren des Inhalts der JTextPane
    public void updateStatusPane() {
        setStatusPane(Game.getStatusMessage());
    }
    public void updateLogPane() {
        setLogPane(Spieler.getLogMessage());
    }
    public void updateRessourcenPane() {
        setRessourcenPane(Game.getRessourceMessage());
    }
    public void updateScreenPane() {
        if (activeFight) {
            setScreen(Spielbrett.getFight());
        } else {
            setScreen(Spielbrett.updateSpielbrettArea());
        }
    }

    public void setSpielerStatus(Spieler setSpieler, Status setStatus) {
        this.spieler = setSpieler;
        this.status = setStatus;
    }

    public void setActiveFight(boolean fightIsActive) {
        activeFight = fightIsActive;
        logPane.setText("ActiveFight");

    }
}
