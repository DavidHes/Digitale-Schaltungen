import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class Controller implements ActionListener, MouseListener, KeyListener {

    private Model model;
    private Panel view;

    public Controller(Model model, Panel view) {
        this.model = model;
        this.view = view;

        view.gernerateButton.addActionListener(this);
      //  view.gernerateButton.addMouseListener(this);

        view.difficutlyButton.addActionListener(this);
      //  view.difficutlyButton.addMouseListener(this);

        view.exitButton.addActionListener(this);
       // view.getDifficutlyButton().addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.gernerateButton) {
            System.out.println("Generate");
            List<ImageIcon> gateIcons = model.generateRandomSchaltung(3);
            view.paintSchaltung(gateIcons);

        }
            if (e.getSource() == view.difficutlyButton) {
                System.out.println("Difficulty");
            }
        else {
            if (e.getSource() == view.exitButton) {
                view.exitButton.setBorderPainted(false);
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

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}