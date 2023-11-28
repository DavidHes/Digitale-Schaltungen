import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        TruthTableGenerator truthTableGenerator = new TruthTableGenerator(3, 1);



        Model model = new Model();
        Panel view = new Panel();
        Controller controller = new Controller(model, view);

        JFrame frame = new JFrame("RBN Klausur-Fragen Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setResizable(false);
        frame.add(view);


      //  frame.add(tabelle.table);

        frame.setVisible(true);

//Majd war hier



    }
}