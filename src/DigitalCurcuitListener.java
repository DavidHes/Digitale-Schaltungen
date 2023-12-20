import java.awt.event.*;

public class DigitalCurcuitListener implements ActionListener {

    private DigitalCurcuitUI view;

    public DigitalCurcuitListener(DigitalCurcuitUI view) {
        this.view = view;

        view.generateButton.addActionListener(this);
        view.exitButton.addActionListener(this);
        view.difficultymenu.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.generateButton) {
            view.spList.clear();
            view.epList.clear();
            view.repaint();

            int anzahlEingaenge = view.schwierigkeit == DigitalCurcuitUI.SchwierigkeitsAuswahl.EASY ? 3 : 4;
//Leider noch nicht ganz erweiterbar.
          //  if(anzahlEingaenge == 3) {
            if(view.schwierigkeit == DigitalCurcuitUI.SchwierigkeitsAuswahl.EASY) {
                view.createTruthTable(anzahlEingaenge, 4);
                view.addRandomGatter();
            }

           // if(anzahlEingaenge == 4) {
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

       // view.paintSchaltung();//Wofür ist der Aufruf?

            if (e.getSource() == view.difficultymenu) {
                if(view.difficultymenu.getSelectedItem() == "Einfach"){
                  //  System.out.println("easy");
                    view.schwierigkeit = DigitalCurcuitUI.SchwierigkeitsAuswahl.EASY;
                }
                if(view.difficultymenu.getSelectedItem() == "Schwierig"){
                  //  System.out.println("average");
                    view.schwierigkeit = DigitalCurcuitUI.SchwierigkeitsAuswahl.DIFFICULT;
                }
             /*   if(view.difficultymenu.getSelectedItem() == "Herr Schaal"){
                    System.out.println("schaal");
                    view.schwierigkeit = Panel.SchwierigkeitsAuswahl.DIFFICULT;
                }*/
            }

  /*      if (e.getSource() == view.questionsmenu) {
            if(view.questionsmenu.getSelectedItem() == "Digitale Schaltung"){
                System.out.println("Digitale Schaltung");
            }
            if(view.questionsmenu.getSelectedItem() == "Wahrheitstabelle"){
                System.out.println("Wahrheitstabelle");
            }
        }
        else {*/
            if (e.getSource() == view.exitButton) {
                System.exit(0);
              //  System.out.println("Exit Funktioniert");

           // }
        }
    }
   /* @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
     //   System.out.println("Mouse Pressed");
       // view.start = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      //  System.out.println("Mouse Released");
     //   view.spList.add(view.start);
      //  view.end = e.getPoint();
     //   view.epList.add(view.end);
      //  view.repaint();
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }

*/
}

