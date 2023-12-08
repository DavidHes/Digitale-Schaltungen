import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        Model model = new Model();
        Panel view = new Panel();
        Controller controller = new Controller(model, view);

        JFrame frame = new JFrame("RBN Klausur-Fragen Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 1200);
        frame.setResizable(true);
        frame.add(view);
        frame.setVisible(true);

        //Testausgabe für überstrich
        String text = "A\u203EB";
        System.out.println(text);
    }
}