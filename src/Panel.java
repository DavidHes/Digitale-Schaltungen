import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.util.Random;

public class Panel extends JPanel {

    protected JButton generateButton;
    protected JComboBox<String> difficultymenu;
    protected JComboBox<String> questionsmenu;
    protected JButton exitButton;
    protected JLabel difficultylabel;
    protected JLabel questionlabel;
    protected JLabel question;
    protected JButton solutionButton;
    private Model model = new Model();
    String[] difficulties = {"Easy", "Average", "Herr Schaal"};
    String[] questions = {"Wahrheitstabelle", "Digitale Schaltung"};
    public Image background = new ImageIcon("Background.png").getImage();
    Image exitBild = new ImageIcon("ExitBild.png").getImage();
    private List<JLabel> gateLabels = new ArrayList<>();
    JTable table;
    Random numbers;
    int min = 0;
    int max = 1;
    int zeilenanzahl;
    int newanzahl;

    public Panel() {

        Image scaledExitImage = exitBild.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledExitIcon = new ImageIcon(scaledExitImage);
        exitButton = new JButton(scaledExitIcon);
        exitButton.setBorderPainted(false);
        exitButton.setFocusPainted(false);
        exitButton.setContentAreaFilled(false);

        setLayout(null);

        questionlabel = new JLabel("Choose your Question");
        difficultylabel = new JLabel("Choose your Difficulty");
        question = new JLabel("Erstelle aus dieser Wahrheitstabelle eine digitale Schaltungen!");

        questionlabel.setFont(new Font("SansSerif", Font.BOLD, 13));
        difficultylabel.setFont(new Font("SansSerif", Font.BOLD, 13));
        question.setFont(new Font("SansSerif", Font.BOLD, 15));
        question.setForeground(Color.BLACK);
        questionlabel.setForeground(Color.BLACK);
        difficultylabel.setForeground(Color.BLACK);

        generateButton = new JButton("Generate");
        solutionButton = new JButton("Solution");
        difficultymenu = new JComboBox<>(difficulties);
        questionsmenu = new JComboBox<>(questions);

        questionlabel.setBounds(110, 13, 150, 30);
        question.setBounds(200, 160, 600, 30);
        difficultylabel.setBounds(360, 13, 150, 30);
        generateButton.setBounds(600, 30, 150, 50);
        difficultymenu.setBounds(360, 32, 150, 50);
        questionsmenu.setBounds(110, 32, 160, 50);
        solutionButton.setBounds(700, 300, 80, 50);
        exitButton.setBounds(820, 5, 50, 50);

        add(generateButton);
        add(difficultymenu);
        add(questionsmenu);
        add(exitButton);
        add(solutionButton);
        add(questionlabel);
        add(question);
        add(difficultylabel);

        questionlabel.setVisible(false);
        questionsmenu.setVisible(false);
        solutionButton.setVisible(false);

        numbers = new Random();

        String[] columnNames = {"A", "B", "C", "M"};

        Object[][] data = {
                {(int)(Math.random() * ((max - min) + 1)), (int)(Math.random() * ((max - min) + 1)),
                        (int)(Math.random() * ((max - min) + 1)), (int)(Math.random() * ((max - min) + 1))},
                {(int)(Math.random() * ((max - min) + 1)), (int)(Math.random() * ((max - min) + 1)),
                        (int)(Math.random() * ((max - min) + 1)), (int)(Math.random() * ((max - min) + 1))},
                {(int)(Math.random() * ((max - min) + 1)), (int)(Math.random() * ((max - min) + 1)),
                        (int)(Math.random() * ((max - min) + 1)), (int)(Math.random() * ((max - min) + 1))},
                {(int)(Math.random() * ((max - min) + 1)), (int)(Math.random() * ((max - min) + 1)),
                        (int)(Math.random() * ((max - min) + 1)), (int)(Math.random() * ((max - min) + 1))},
        } ;

        table = new JTable(data, columnNames);
        table.setBounds(200, 300, 150, 200);
       // table.setVisible(false);
        add(table);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        g.setColor(Color.BLACK);
        g.drawRoundRect(100, 150, 700, 350, 20, 20);
        g.setColor(Color.WHITE);
        g.fillRoundRect(100, 150, 700, 350, 20, 20);

        g.setColor(Color.BLACK);
        g.drawRoundRect(100, 15, 700, 75, 20, 20);
        g.setColor(Color.WHITE);
        g.fillRoundRect(100, 15, 700, 75, 20, 20);

    }

    public void paintSchaltung(List<ImageIcon> gateIcons) {
        // Vor dem Zeichnen der neuen Schaltung entferne die vorherige
        gateLabels.forEach(this::remove);
        gateLabels.clear();

        int x = 130;  //Startposition
        int y = 230;
        for (ImageIcon icon : gateIcons) {
            JLabel label = new JLabel(icon);
            label.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
            gateLabels.add(label);
            add(label);

            x += 100;  //Position horizontal verändert nach jedem durchgang
        }

        revalidate();
        repaint();
    }

}
