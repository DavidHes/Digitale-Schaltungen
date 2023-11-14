import java.awt.*;
import java.util.List;
import javax.swing.*;
import java.awt.Graphics;

public class Panel extends JPanel {

    private JPanel panel;
    protected JButton gernerateButton;
    protected JComboBox difficutlyButton;
    protected JButton exitButton;
    private Model model = new Model();
    String[] difficulties = {"Easy", "Average", "Hard"};
    public Image background = new ImageIcon("Background.png").getImage();
    Image exitBild = new ImageIcon("ExitBild.png").getImage();

    public Panel() {

        Image scaledexitImage = exitBild.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        ImageIcon scaledexitIcon = new ImageIcon(scaledexitImage);
        exitButton = new JButton(scaledexitIcon);

        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false); //Füllt den Buttonhintergrund mit der jeweiligen Backgroundfarbe
        exitButton.setName("ExitBild.png");

        panel = new JPanel();
       // panel.setLayout(new FlowLayout());
        panel.setLayout(null);

        gernerateButton = new JButton("Generate");
        difficutlyButton = new JComboBox(difficulties);


        gernerateButton.setBounds(260, 30, 150, 50);
        difficutlyButton.setBounds(472, 30, 150, 50);
       // exitButton.setBounds(820, 5, 75, 25);
        exitButton.setBounds(820, 5, 50, 50);


        panel.add(gernerateButton);
        panel.add(difficutlyButton);
        panel.add(exitButton);
    }

    public JPanel getPanel() {
        return panel;
    }
/*
    public JButton getGenerateButton() {
        return gernerateButton;
    }
    public JButton getExitButton() {
        return exitButton;
    }

    public JComboBox getDifficutlyButton() {
       return difficutlyButton;
    }

     */
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