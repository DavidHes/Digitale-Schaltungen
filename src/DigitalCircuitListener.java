import java.awt.event.*;

public class DigitalCircuitListener implements ActionListener {

    private DigitalCircuitUI view;

    public DigitalCircuitListener(DigitalCircuitUI view) {
        this.view = view;

        view.erstellenButton.addActionListener(this);
        view.exitButton.addActionListener(this);
        view.schwierigkeitsmenu.addActionListener(this);
    }

    /**
     * Diese Methode behandelt Aktionen, die durch Benutzerinteraktionen ausgelöst wurden.
     * Dazu zählt das Klicken von Buttons oder das Auswählen von Optionen im Schwierigkeitsmenü.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.erstellenButton) {
            view.spListe.clear();
            view.epListe.clear();
            view.repaint();

            int anzahlEingaenge = view.schwierigkeit == DigitalCircuitUI.SchwierigkeitsAuswahl.EASY ? 3 : 4;

            if(view.schwierigkeit == DigitalCircuitUI.SchwierigkeitsAuswahl.EASY) {
                view.createTruthTable(anzahlEingaenge);
                view.addRandomGatter();
            }

            if(view.schwierigkeit == DigitalCircuitUI.SchwierigkeitsAuswahl.DIFFICULT) {
                view.createTruthTable(anzahlEingaenge);
                for(int i = 0; i < 2; i++) {
                    view.addRandomGatter();
                    if(i == 1){
                        view.addEndGatter();
                       }
                    }
                }
            view.drawSchaltung();
            }

            if (e.getSource() == view.schwierigkeitsmenu) {
                if(view.schwierigkeitsmenu.getSelectedItem() == "Einfach"){
                    view.schwierigkeit = DigitalCircuitUI.SchwierigkeitsAuswahl.EASY;
                }
                if(view.schwierigkeitsmenu.getSelectedItem() == "Schwierig"){
                    view.schwierigkeit = DigitalCircuitUI.SchwierigkeitsAuswahl.DIFFICULT;
                }
            }
            if (e.getSource() == view.exitButton) {
                System.exit(0);
        }
    }
}

