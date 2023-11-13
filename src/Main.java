import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Model model = new Model();
        Panel view = new Panel();
        Controller controller = new Controller(model, view);

        JFrame frame = new JFrame("RBN Klausur-Fragen Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setResizable(false);
        frame.add(view.getPanel());

        frame.setVisible(true);

//Majd war hier



    }
}