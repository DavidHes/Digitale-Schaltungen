import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Panel extends JPanel {

    protected JButton generateButton;
    protected JComboBox<String> difficultyButton;
    protected JButton exitButton;
    private Model model = new Model();
    String[] difficulties = {"Easy", "Average", "Hard"};
    public Image background = new ImageIcon("Background.png").getImage();
    Image exitBild = new ImageIcon("ExitBild.png").getImage();
    private List<JLabel> gateLabels = new ArrayList<>();

    public Panel() {
        Image scaledExitImage = exitBild.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledExitIcon = new ImageIcon(scaledExitImage);
        exitButton = new JButton(scaledExitIcon);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);

        setLayout(null);

        generateButton = new JButton("Generate");
        difficultyButton = new JComboBox<>(difficulties);

        generateButton.setBounds(260, 30, 150, 50);
        difficultyButton.setBounds(472, 30, 150, 50);
        exitButton.setBounds(820, 5, 50, 50);

        add(generateButton);
        add(difficultyButton);
        add(exitButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);

    }

    public void paintSchaltung(List<ImageIcon> gateIcons) {
        // Vor dem Zeichnen der neuen Schaltung entferne die vorherige
        gateLabels.forEach(this::remove);
        gateLabels.clear();

        int x = 50;  //Startposition
        int y = 50;
        for (ImageIcon icon : gateIcons) {
            JLabel label = new JLabel(icon);
            label.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
            gateLabels.add(label);
            add(label);

            x += 150;  //Position horizontal verändert nach jedem durchgang
        }

        revalidate();
        repaint();
    }

}
