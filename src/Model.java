import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.*;

public class Model {

    List<ImageIcon> gateIcons;

    public List<ImageIcon> generateRandomSchaltung(int numGates) {
        Random rand = new Random();
        gateIcons = new ArrayList<>();

        for (int i = 0; i < numGates; i++) {
            String[] gateNames = {"AND-GATTER.png", "OR-GATTER.png", "NOT-GATTER.png"};
            String selectedGateName = gateNames[rand.nextInt(gateNames.length)];

            gateIcons.add(new ImageIcon(selectedGateName));

            if (i < numGates - 1) {
                gateIcons.add(new ImageIcon("PFEIL.png"));
            }
        }

        System.out.println(gateIcons);
        return gateIcons;
    }

}
