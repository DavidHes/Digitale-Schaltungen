import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        Model model = new Model();
        Panel view = new Panel();
        Controller controller = new Controller(model, view);

        JFrame frame = new JFrame("RBN Klausur-Fragen Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setResizable(true);
        frame.add(view);
        frame.setVisible(true);

    }
}