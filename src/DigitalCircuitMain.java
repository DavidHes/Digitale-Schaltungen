import javax.swing.*;

public class DigitalCircuitMain {
    public static void main(String[] args) {

        DigitalCircuitUI view = new DigitalCircuitUI();
        DigitalCircuitListener listener = new DigitalCircuitListener(view);

        JFrame frame = new JFrame("RBN Klausur-Fragen-Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 1200);
        frame.setResizable(true);
        frame.add(view);
        frame.setVisible(true);

    }
}