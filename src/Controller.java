import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class Controller implements ActionListener, MouseListener, KeyListener {

    private Model model;
    private Panel view;

    public Controller(Model model, Panel view) {
        this.model = model;
        this.view = view;

        view.generateButton.addActionListener(this);
        view.exitButton.addActionListener(this);
        view.difficultymenu.addActionListener(this);
        view.solutionButton.addActionListener(this);
        view.questionsmenu.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.generateButton) {
            System.out.println("Generate");
            //to clear the drawings
            view.spList.clear();
            view.epList.clear();
            view.repaint();

          //  List<ImageIcon> gateIcons = model.generateRandomSchaltung(3);
          //  view.paintSchaltung(gateIcons);
            //view.generateTruthTable(3);
          //  view.solutionButton.setVisible(true); --> NOCH NICHT LÖSCHEN

            int anzahlEingaenge = view.schwierigkeit == Panel.SchwierigkeitsAuswahl.EASY ? 3 : 4;
//Leider noch nicht ganz erweiterbar.
            if(anzahlEingaenge == 3) {
                view.createTruthTable(anzahlEingaenge, 2, 4);
                view.addRandomGatter();
            }

            if(anzahlEingaenge == 4) {
                view.createTruthTable(anzahlEingaenge, 2, 4);
                for(int i = 0; i < 2; i++) {
                    view.addRandomGatter();
                    if(i == 1){
                        view.endgatter();
                        }
                    }
                }
            }

        view.paintSchaltung();

            if (e.getSource() == view.difficultymenu) {
                if(view.difficultymenu.getSelectedItem() == "Easy"){
                    System.out.println("easy");
                    view.schwierigkeit = Panel.SchwierigkeitsAuswahl.EASY;
                }
                if(view.difficultymenu.getSelectedItem() == "Average"){
                    System.out.println("average");
                    view.schwierigkeit = Panel.SchwierigkeitsAuswahl.DIFFICULT;
                }
                if(view.difficultymenu.getSelectedItem() == "Herr Schaal"){
                    System.out.println("schaal");
                    view.schwierigkeit = Panel.SchwierigkeitsAuswahl.DIFFICULT;
                }
            }

        if (e.getSource() == view.questionsmenu) {
            if(view.questionsmenu.getSelectedItem() == "Digitale Schaltung"){
                System.out.println("Digitale Schaltung");
            }
            if(view.questionsmenu.getSelectedItem() == "Wahrheitstabelle"){
                System.out.println("Wahrheitstabelle");
            }
        }
        else {
            if (e.getSource() == view.exitButton) {
                System.exit(0);
                System.out.println("Exit Funktioniert");

            }
        }
    }
    @Override
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


}

