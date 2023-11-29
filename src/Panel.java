import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.util.Random;

public class Panel extends JPanel {

    protected JComboBox<String> difficultymenu, questionsmenu;
    protected JButton exitButton, solutionButton, generateButton;
    protected JLabel difficultylabel, questionlabel, question;
    String[] difficulties = {"Easy", "Average", "Herr Schaal"};
    String[] gatterarten = {"AND", "OR", "NOT"};
    String[] questions = {"Wahrheitstabelle", "Digitale Schaltung"};

    public Image background = new ImageIcon("Background.png").getImage();
    Image exitBild = new ImageIcon("ExitBild.png").getImage();
    private List<JLabel> gateLabels = new ArrayList<>();
    JTable table;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;
    Random numbers;
    int min = 0;
    int max = 1;
    int zeilenanzahl;
    int newanzahl;

    boolean difficult = true;


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


    }

    //einfach parameter übergeben, die die anzahl der eingänge vorgibt. Z.b int gatteranzahl = 2, oder int = 3
    //int ausgange
    public void createTruthTable(int eingange, int ausgaenge, int gatterAnzahl) { //nur ein gatter aber dafür alle drei Eingänge drin, einziger unterschied sind, dass es drei kombis sind (not, and, or)
        // Entfernt scrollpane und dazugehörige tabelle, damit es zu beginn keine mehrfachen tabellen gibt
        if (scrollPane != null) {
            this.remove(scrollPane);
        }
        revalidate();
        repaint();

        //Ende der Entfernung

        //Erstellen der NormalTabelle
        tableModel = new DefaultTableModel();

        String[] columnNames = new String[eingange]; //Anzahl der Spalten

        if (eingange > 0) {
            for (int i = 0; i < eingange+gatterAnzahl; i++) {
                if(i < eingange) {
                    char buchstabe = (char) ('A' + i);
                    columnNames[i] = String.valueOf(buchstabe);
                } else {
                    // columnNames[i] = "Platzhalter";
                    //Hier müssen dann die GatterSpaltenNamen erstellt werden bzw. die Namen derer
                }

            }
        }
        Object[][] rowData = new Object[eingange * eingange][eingange + gatterAnzahl]; //Tabellengröße = Zeilenanzahl + Spaltenanzahl
        for (int i = 0; i < eingange * eingange; i++) {
            for (int j = 0; j < eingange; j++) { //Füllen aller Zeilen/Spalten mit Nullen
                rowData[i][j] = 0;
            }
            // Füllen der restlichen Binärziffern
            String binärZahl = Integer.toBinaryString(i); //Umwandlung aller Zahlen von 0-anzahleingange
            System.out.println("DIE ZAHL " + i + " = " + binärZahl);
            for (int j = 0; j < binärZahl.length(); j++) { //binärZahl.length ist wichtig, da die Binärzahlen teilweise nur einstellig nach der umwandlung sind
                if(eingange < 4)
                    rowData[i][j] = Character.getNumericValue(binärZahl.charAt(j)); //auch hier wieder: eingange - binärZahl.length() + j ist notwenig, da eingeange nicht immer auch der binärZahl.länge entsprechen
                else rowData[i][eingange - binärZahl.length() + j] = Character.getNumericValue(binärZahl.charAt(j)); //auch hier wieder: eingange - binärZahl.length() + j ist notwenig, da eingeange nicht immer auch der binärZahl.länge entsprechen

                // TODO: wenn eingänge >= 5 ist, dann das:  rowData[i][eingange - binärZahl.length() + j] = Character.getNumericValue(binärZahl.charAt(j)); //auch hier wieder: eingange - binärZahl.length() + j ist notwenig, da eingeange nicht immer auch der binärZahl.länge entsprechen

            }
            //Hier wird durch alle Zeilen durchgegangen und der gatterinhalt erstellt
            for (int k = 0; k < gatterAnzahl; k++) {
                //rowData[i][eingange+k] = "Platzhalter";

                //eigentlich müsste hier lediglich addRandomGatter aufgerufen werden
            }
        }

        tableModel.setDataVector(rowData, columnNames);
        table = new JTable(tableModel);

        //  table = new JTable(rowData, columnNames);
        table.setVisible(true);
        this.add(table);
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);
        table.setRowHeight(30);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(200, 250, table.getColumnCount() * 90, table.getRowCount() * table.getRowHeight() + 20);


        add(scrollPane);

        table.getTableHeader().setReorderingAllowed(false); // not allow re-ordering of columns
        table.getTableHeader().setResizingAllowed(false);
        table.setEnabled(false);
        table.setBackground(Color.LIGHT_GRAY);

        //Ende
    }

    public void addRandomGatter() {
        //Beginn der GatterErstellung

        Random random = new Random();
        int randomGatter = random.nextInt(4);
        System.out.println("RANDOMZAHL: " + randomGatter);
        int spaltenAnzahl = table.getColumnCount();
        System.out.println("Spaltenanzahl " + spaltenAnzahl);

        if (difficult == true) {
            int randomX = random.nextInt(3);
            int randomY;
            do {
                randomY = random.nextInt(3);
            } while (randomY == randomX);

            String eingang1 = randomX == 0 ? "A" : randomX == 1 ? "B" : "C";
            String eingang2 = randomY == 0 ? "A" : randomY == 1 ? "B" : "C";

            String columnName = randomGatter == 0 ? eingang1 + " AND " + eingang2 : randomGatter == 1 ?
                    eingang1 + " OR " + eingang2 : randomGatter == 2 ? eingang1 + " NAND " + eingang2 : eingang1 + " NOR " + eingang2;
            tableModel.addColumn(columnName); //Setzt den richtigen Spaltennamen, je nach dem welcher Case zufällig generiert wurde

            for (int i = 0; i < table.getRowCount(); i++) { //Durchgang durch alle Zeilen
                Object x = table.getValueAt(i, randomX);
                Object y = table.getValueAt(i, randomY);

                switch(randomGatter) {
                    //AND
                    case 0:
                        System.out.println("CASE 0");
                        //
                        if (x.equals(1) && y.equals(1)) {
                            tableModel.setValueAt("1", i, spaltenAnzahl);
                            break;
                            //direkt hier zur Tabelle hinzufügen
                        } else {
                            tableModel.setValueAt("0", i, spaltenAnzahl);
                            break;
                        }

                        //OR
                    case 1:
                        System.out.println("CASE 1");
                        //   tableModel.addColumn(eingang1 + " OR " + eingang2);
                        if (!(x.equals(0) && y.equals(0))) {
                            tableModel.setValueAt("1", i, spaltenAnzahl);
                            break;
                        } else {
                            tableModel.setValueAt("0", i, spaltenAnzahl);
                            break;
                        }

                        //NAND
                    case 2:
                        System.out.println("CASE 2");
                        //  tableModel.addColumn(eingang1 + " NAND " + eingang2);
                        if (!(x.equals(1) && y.equals(1))) {
                            tableModel.setValueAt("1", i, spaltenAnzahl);
                            break;
                        } else {
                            tableModel.setValueAt("0", i, spaltenAnzahl);
                            break;
                        }

                        //NOR
                    case 3:
                        System.out.println("CASE 3");
                        //    tableModel.addColumn(eingang1 + " OR " + eingang2);
                        if (!(x.equals(1) && y.equals(1))) {
                            tableModel.setValueAt("1", i, spaltenAnzahl);
                            break;
                        } else {
                            tableModel.setValueAt("0", i, spaltenAnzahl);
                            break;
                        }
                    default:
                        System.out.println("DEFAULT");
                }
            }
        } else {
            String columnName = randomGatter == 0 ? "A AND B AND C" : randomGatter == 1 ?
                    "A OR B OR C" : randomGatter == 2 ? "A NAND B NAND C" : "A NOR B NOR C";
            tableModel.addColumn(columnName); //Setzt den richtigen Spaltennamen, je nach dem welcher Case zufällig generiert wurde

            for (int i = 0; i < table.getRowCount(); i++) { //Durchgang durch alle Zeilen
                Object a = table.getValueAt(i, 0);
                Object b = table.getValueAt(i, 1);
                Object c = table.getValueAt(i, 2);

                switch(randomGatter) {
                    //AND
                    case 0:
                        if (a.equals(1) && b.equals(1) && c.equals(1)) {
                            tableModel.setValueAt("1", i, spaltenAnzahl);
                            break;
                        } else {
                            tableModel.setValueAt("0", i, spaltenAnzahl);
                            break;
                        }

                        //OR
                    case 1:
                        if (!(a.equals(0) && b.equals(0) && c.equals(0))) {
                            tableModel.setValueAt("1", i, spaltenAnzahl);
                            break;
                        } else {
                            tableModel.setValueAt("0", i, spaltenAnzahl);
                            break;
                        }

                        //NAND
                    case 2:
                        if (!(a.equals(1) && b.equals(1) && c.equals(1))) {
                            tableModel.setValueAt("1", i, spaltenAnzahl);
                            break;
                        } else {
                            tableModel.setValueAt("0", i, spaltenAnzahl);
                            break;
                        }

                        //NOR
                    case 3:
                        if (!(a.equals(1) && b.equals(1) && c.equals(1))) {
                            tableModel.setValueAt("1", i, spaltenAnzahl);
                            break;
                        } else {
                            tableModel.setValueAt("0", i, spaltenAnzahl);
                            break;
                        }
                }
            }
        }
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

    public void generateTable(String mode){
        if(mode == "Easy"){
            // for(int i = 0; i < 1; i++) {
            int gatter = numbers.nextInt(3);
            //   createRandomGatter(3, 1, 3);
        }  // }

        if(mode == "Herr Schaal"){
            for(int i = 0; i < 3; i++) {
                int gatter = numbers.nextInt(3);
                //    createRandomGatter(3, 1, 3);
            }
        }
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
