import java.awt.*;
import java.util.List;
import javax.swing.*;
import java.awt.Graphics;


public class Panel extends JPanel {

    private JPanel panel;
    private JButton gernerateButton;
    private JComboBox difficutlyButton;
    private JButton exitButton;
    private Model model = new Model();
    String[] difficulties = {"easy", "Mid", "Hard"};
    public Image background = new ImageIcon("Background.png").getImage();

    public Panel() {

        panel = new JPanel();
       // panel.setLayout(new FlowLayout());
        panel.setLayout(null);

        gernerateButton = new JButton("Generate");
        difficutlyButton = new JComboBox(difficulties);
        exitButton = new JButton("Exit");

        gernerateButton.setBounds(260, 30, 150, 50);
        difficutlyButton.setBounds(472, 30, 150, 50);
        exitButton.setBounds(820, 5, 75, 25);

        panel.add(gernerateButton);
        panel.add(difficutlyButton);
        panel.add(exitButton);
    }
    public JPanel getPanel() {
        return panel;
    }

    public JButton getGenerateButton() {
        return gernerateButton;
    }

    public JComboBox getDifficutlyButton() {
       return difficutlyButton;
    }
    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
        g.drawImage(background, 0, 0, 900, 600, this);
        repaint();
    }

    public void paintSchaltung(List<ImageIcon> gateIcons) {
        for (ImageIcon icon : gateIcons) {
            JLabel label = new JLabel(icon);
            panel.add(label);
        }

        panel.revalidate();
        panel.repaint();
    }


}