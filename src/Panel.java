import java.awt.*;
import java.util.List;
import javax.swing.*;

public class Panel extends JPanel {

    private JPanel panel;
    private JButton gernerateButton;
    private JButton difficutlyButton;
    private Model model = new Model();

    public Panel() {

        panel = new JPanel();
        panel.setLayout(new FlowLayout());

        gernerateButton = new JButton("Generate");
        difficutlyButton = new JButton("Difficulty");

        gernerateButton.setBounds(144, 650, 150, 50);
        difficutlyButton.setBounds(288, 450, 150, 50);

        panel.add(gernerateButton);
        panel.add(difficutlyButton);

    }

    public JPanel getPanel() {
        return panel;
    }

    public JButton getGenerateButton() {
        return gernerateButton;
    }

    public JButton getDifficutlyButton() {
        return difficutlyButton;
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