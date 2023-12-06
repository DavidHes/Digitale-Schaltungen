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
    protected JLabel difficultylabel, questionlabel, question, maplegend;
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
    int eingange;

    public enum SchwierigkeitsAuswahl {EASY, DIFFICULT}
    public enum TypAuswahl {WAHRHEITSTABELLE, SCHALTUNG}
    public SchwierigkeitsAuswahl schwierigkeit = SchwierigkeitsAuswahl.EASY;
    public TypAuswahl typ = TypAuswahl.WAHRHEITSTABELLE;

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
        maplegend = new JLabel(" NAND ⊼    AND *    OR +    NOR ⊽ ");

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
        question.setBounds(200, 120, 600, 30);
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
        this.eingange = eingange;
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
        // Hier alles erweiterbar --> man kann hier mit --> (int) Math.pow(2, eingange)
        // --> anhand der Anzahl der Eingänge, die Anzahl der Zeilen erstellen. Formel = 2 ^ Eingänge
        Object[][] rowData = new Object[(int) Math.pow(2, eingange)][eingange + gatterAnzahl]; //Tabellengröße = Zeilenanzahl + Spaltenanzahl
        for (int i = 0; i < ((int) Math.pow(2, eingange)); i++) {
            for (int j = 0; j < eingange; j++) { //Füllen aller Zeilen/Spalten mit Nullen
                rowData[i][j] = 0;
            }


            // Füllen der restlichen Binärziffern
            String binärZahl = Integer.toBinaryString(i); //Umwandlung aller Zahlen von 0-anzahleingange
            System.out.println("DIE ZAHL " + i + " = " + binärZahl);
            for (int j = 0; j < binärZahl.length(); j++) { //binärZahl.length ist wichtig, da die Binärzahlen teilweise nur einstellig nach der umwandlung sind
                rowData[i][eingange - binärZahl.length() + j] = Character.getNumericValue(binärZahl.charAt(j)); //auch hier wieder: eingange - binärZahl.length() + j ist notwenig, da eingeange nicht immer auch der binärZahl.länge entsprechen

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
        table.setRowHeight(22);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(120, 170, table.getColumnCount() * 200, table.getRowCount() * table.getRowHeight() + 20);

        add(scrollPane);

        table.getTableHeader().setReorderingAllowed(false); // not allow re-ordering of columns
        table.getTableHeader().setResizingAllowed(false);
        table.setEnabled(false);
        table.setBackground(Color.LIGHT_GRAY);
        //Ende
    }

    //Damit nehmen wir alle Ausgänge alle Gatter und packen sie in eins.
    public void endgatter(){

        Random random = new Random();
        int randomGatter = random.nextInt(4);
        int spaltenAnzahl = table.getColumnCount();
       // String[] spaltenNamen = new String[tableModel.getColumnCount()];

        ArrayList<String> spaltenNamen = new ArrayList<>();

        //FEHLER

        //anhand der eingänge erstellen wir eine liste mit allen namen der spalten. alle die nach den Eingängen kamen
        //damit wir mit diesen Spalten den endgatter erstellen können.
        //for(int eingang = 3; eingang < spaltenAnzahl; eingang++){
        for(int eingang = 0; eingang < spaltenAnzahl; eingang++){
            System.out.println("Ausgabetest1");
            spaltenNamen.add(tableModel.getColumnName(eingang));
            System.out.println(spaltenNamen.get(eingang));
            System.out.println("Ausgabetest2");
        }

        String columnName ="";

        for(int i = 4; i < spaltenNamen.size(); i++) {

//nach und nach alle spaltennamen in die letzte Spalte schreiben.
            switch (randomGatter){
                case 0:
                    System.out.println("CASE0 automatisiert funktioniert");
                    columnName += spaltenNamen.get(i) + " * ";
                        break;

                case 1:
                    System.out.println("CASE1 automatisiert funktioniert");
                    columnName += spaltenNamen.get(i) + " + ";
                    break;

//nand
                case 2:
                    System.out.println("CASE2 automatisiert funktioniert");
                    columnName += spaltenNamen.get(i) + " ⊼ ";
                    break;
//nor
                case 3:
                    System.out.println("CASE3 automatisiert funktioniert");
                    columnName += spaltenNamen.get(i) + " ⊽ ";
                    break;
            }
        }

        tableModel.addColumn(columnName); //Hinter dir for-schleife geschoben, da sonst mit jedem schleifendurchgang neue spalten hinzugefügt werden

        for (int i = 0; i < table.getRowCount(); i++) { //Durchgang durch alle Zeilen
            //4 und 5 müssen auch weg.
             Object x = table.getValueAt(i,4);
             Object y = table.getValueAt(i,5);

        switch (randomGatter) {
            //AND
            case 0:
                System.out.println("End CASE 0");
                //
                if (x.equals("1") && y.equals("1")) {
                    tableModel.setValueAt("1", i, spaltenAnzahl);
                    break;
                    //direkt hier zur Tabelle hinzufügen
                } else {
                    tableModel.setValueAt("0", i, spaltenAnzahl);
                    break;
                }

                //OR
            case 1:
                System.out.println("End CASE 1");
                //   tableModel.addColumn(eingang1 + " OR " + eingang2);
                if (!(x.equals("0") && y.equals("0"))) {
                    tableModel.setValueAt("1", i, spaltenAnzahl);
                    break;
                } else {
                    tableModel.setValueAt("0", i, spaltenAnzahl);
                    break;
                }

                //NAND
            case 2:
                System.out.println("End CASE 2");
                //  tableModel.addColumn(eingang1 + " NAND " + eingang2);
                if (!(x.equals("1") && y.equals("1"))) {
                    tableModel.setValueAt("1", i, spaltenAnzahl);
                    break;
                } else {
                    tableModel.setValueAt("0", i, spaltenAnzahl);
                    break;
                }

                //NOR
            case 3:
                System.out.println("End CASE 3");
                //    tableModel.addColumn(eingang1 + " OR " + eingang2);
                if (!(x.equals("1") && y.equals("1"))) {
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

    }

    public void addRandomGatter() {
        //Beginn der GatterErstellung

        Random random = new Random();
        int randomGatter = random.nextInt(4);
        System.out.println("RANDOMZAHL: " + randomGatter);
        int spaltenAnzahl = table.getColumnCount();
        System.out.println("Spaltenanzahl " + spaltenAnzahl);

        if (schwierigkeit == SchwierigkeitsAuswahl.DIFFICULT) {
            int randomX = random.nextInt(4);
            int randomY;
            do {
                randomY = random.nextInt(4);
            } while (randomY == randomX);

            String eingang1;
            String eingang2;
            if (tableModel.getColumnCount() - eingange == 0) { //Wenn Spalte 5 noch nicht existiert werden zwei Eingänge random ausgewählt
                eingang1 = randomX == 0 ? "A" : randomX == 1 ? "B" : randomX == 2 ? "C" : "D";
                eingang2 = randomY == 0 ? "A" : randomY == 1 ? "B" : randomY == 2 ? "C" : "D";
            } else {
                int randomBuchstabe1;
                int randomBuchstabe2;
                String[] Buchstaben = {"A", "B", "C", "D"};
                do {
                    randomBuchstabe1 = random.nextInt(4);
                    randomBuchstabe2 = random.nextInt(4);
                    eingang1 = Buchstaben[randomBuchstabe1];
                    eingang2 = Buchstaben[randomBuchstabe2];
                } while (tableModel.getColumnName(4).contains(eingang1) || tableModel.getColumnName(4).contains(eingang2) || eingang1.equals(eingang2));
            }

            String columnName = randomGatter == 0 ? eingang1 + " * " + eingang2 : randomGatter == 1 ?
                    eingang1 + " + " + eingang2 : randomGatter == 2 ? eingang1 + " ⊼ " + eingang2 : eingang1 + " ⊽ " + eingang2;

            tableModel.addColumn(columnName); //Setzt den richtigen Spaltennamen, je nach dem welcher Case zufällig generiert wurde

            int spaltenNummer1 = eingang1.equals("A") ? 0 : eingang1.equals("B") ? 1 : eingang1.equals("C") ? 2 : 3;
            int spaltenNummer2 = eingang2.equals("A") ? 0 : eingang2.equals("B") ? 1 : eingang2.equals("C") ? 2 : 3;

            Object x = "??"; //LÖSCHEN
            Object y = "??";

                for (int i = 0; i < table.getRowCount(); i++) { //Durchgang durch alle Zeilen
                    //Wenn spaltenanzahl - eingänge = 0 ist, dann random. Wenn nicht, dann spalte 5 anschauen und die anderen nehmen

                    if (tableModel.getColumnCount() - eingange-1 == 0) {
                        x = table.getValueAt(i, randomX);
                        y = table.getValueAt(i, randomY);
                    } else { //Wenn bereits eine Spalte existiert:
                        x = table.getValueAt(i, spaltenNummer1);
                        y = table.getValueAt(i, spaltenNummer2);
                    }

                    switch (randomGatter) {
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
        }
        else {
            String columnName = randomGatter == 0 ? "A * B * C" : randomGatter == 1 ?
                    "A + B + C" : randomGatter == 2 ? "A ⊼ B ⊼ C" : "A ⊽ B ⊽ C";
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
        g.drawRoundRect(100, 120, 700, 450, 20, 20);
        g.setColor(Color.WHITE);
        g.fillRoundRect(100, 120, 700, 450, 20, 20);

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
        int y = 600;
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