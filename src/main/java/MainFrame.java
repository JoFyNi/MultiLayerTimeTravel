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
    private JScrollPane spielbrettScrollPane;
    private JButton saveBtn;
    private JPanel mapPane;
    Map<Class<? extends Ressource>, Ressource> ressourcenMap;
    Spieler spieler;
    Status status;
    //
    private boolean activeFight = false;
    FeldTypen[][] felder = GeneriereSpielFeld.generiereSpielbrett();
    GeneriereSpielFeld generiereSpielFeld = new GeneriereSpielFeld(felder);


    public MainFrame () {
        // Erstelle einen Timer mit einer Aktualisierungsrate von 1 Sekunde (1000 Millisekunden)
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hier kannst du den Inhalt der JTextPane aktualisieren
                updateStatusPane();     // Methode aufrufen, um den Inhalt des statusPane zu aktualisieren
                updateLogPane();        // Methode aufrufen, um den Inhalt des logPane zu aktualisieren
                updateRessourcenPane(); // Methode aufrufen, um den Inhalt des ressourcenPane zu aktualisieren

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

    public void setSpielerStatus(Spieler setSpieler, Status setStatus) {
        this.spieler = setSpieler;
        this.status = setStatus;
    }

    public void setActiveFight(boolean fightIsActive) {
        activeFight = fightIsActive;
        logPane.setText("ActiveFight");

    }

    private void createUIComponents() {
        mapPane = new JPanel();

        // Erstelle das Spielbrett Grafisch und gib es im mapPane aus
        FeldTypen[][] felder = GeneriereSpielFeld.generiereSpielbrett();
        GeneriereSpielFeld hexagonGenerator = new GeneriereSpielFeld(felder);

        // Füge das benutzerdefinierte Panel zum mapPane hinzu
        mapPane.removeAll();
        mapPane.add(hexagonGenerator);

        // Aktualisiere das mapPane
        mapPane.revalidate();
        mapPane.repaint();
    }
}
