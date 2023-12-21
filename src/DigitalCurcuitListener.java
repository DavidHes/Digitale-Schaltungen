import java.awt.event.*;

public class DigitalCurcuitListener implements ActionListener {

    private DigitalCurcuitUI view;

    public DigitalCurcuitListener(DigitalCurcuitUI view) {
        this.view = view;

        view.erstellenButton.addActionListener(this);
        view.exitButton.addActionListener(this);
        view.schwierigkeitsmenu.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.erstellenButton) {
            view.spListe.clear();
            view.epListe.clear();
            view.repaint();

            int anzahlEingaenge = view.schwierigkeit == DigitalCurcuitUI.SchwierigkeitsAuswahl.EASY ? 3 : 4;

            if(view.schwierigkeit == DigitalCurcuitUI.SchwierigkeitsAuswahl.EASY) {
                view.createTruthTable(anzahlEingaenge, 4);
                view.addRandomGatter();
            }

            if(view.schwierigkeit == DigitalCurcuitUI.SchwierigkeitsAuswahl.DIFFICULT) {
                view.createTruthTable(anzahlEingaenge, 4);
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
                    view.schwierigkeit = DigitalCurcuitUI.SchwierigkeitsAuswahl.EASY;
                }
                if(view.schwierigkeitsmenu.getSelectedItem() == "Schwierig"){
                    view.schwierigkeit = DigitalCurcuitUI.SchwierigkeitsAuswahl.DIFFICULT;
                }
            }
            if (e.getSource() == view.exitButton) {
                System.exit(0);
        }
    }
}

