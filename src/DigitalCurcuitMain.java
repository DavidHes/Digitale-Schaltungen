import javax.swing.*;

public class DigitalCurcuitMain {
    public static void main(String[] args) {

        DigitalCurcuitUI view = new DigitalCurcuitUI();
        DigitalCurcuitListener listener = new DigitalCurcuitListener(view);

        JFrame frame = new JFrame("RBN Klausur-Fragen-Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 1200);
        frame.setResizable(true);
        frame.add(view);
        frame.setVisible(true);

    }
}