import javax.swing.*;

public class DigitalCurcuitMain {
    public static void main(String[] args) {

        DigitalCurcuitUI view = new DigitalCurcuitUI();
        DigitalCurcuitListener controller = new DigitalCurcuitListener(view);

        JFrame frame = new JFrame("RBN Klausur-Fragen-Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 1200);
        frame.setResizable(true);
        frame.add(view);
        frame.setVisible(true);

        //Testausgabe für überstrich
        String text = "A\u203E+\u203EB";
        System.out.println(text);
    }
}