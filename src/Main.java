import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        View view = new View();
        Controller controller = new Controller(view);

        JFrame frame = new JFrame("RBN Klausur-Fragen-Generator");
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