import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Random;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DigitalCurcuitUI extends JPanel {

    protected JComboBox<String> schwierigkeitsmenu;
    protected JButton exitButton, erstellenButton;
    protected JLabel schwierigkeitslabel, aufgabenstellung;
    String[] schwierigkeiten = {"Einfach", "Schwierig"};
    String[] questions = {"Wahrheitstabelle", "Digitale Schaltung"};

    Image hintergrundsbild = new ImageIcon("Background.png").getImage();
    Image exitBild = new ImageIcon("ExitBild.png").getImage();
    JTable tabelle;
    DefaultTableModel tabellenModel;
    JScrollPane scrollFeld;
   // Random numbers;
  //  int min = 0;
   // int max = 1;
   // int zeilenanzahl;
    int eingange;
    List<ImageIcon> gatterIcons = new ArrayList<>();
    List<ImageIcon> gatter = new ArrayList<>();
    List<Integer> zwischengatter = new ArrayList<>();

    int endgatter;
    //ArrayLists und Points um die Linien zu speichern.
    protected ArrayList<Point> spListe = new ArrayList<Point>();
    protected ArrayList<Point> epListe = new ArrayList<Point>();
    protected Point startPunkt;
    protected Point endPunkt;
    private JLabel zwischenlabel;
    private JLabel endlabel;

    public enum SchwierigkeitsAuswahl {EASY, DIFFICULT}
    //public enum TypAuswahl {WAHRHEITSTABELLE, SCHALTUNG}

    public SchwierigkeitsAuswahl schwierigkeit = SchwierigkeitsAuswahl.EASY;
 //   public TypAuswahl typ = TypAuswahl.WAHRHEITSTABELLE;

    private List<JLabel> erstelltenLabels = new ArrayList<>(); //dafür da, um alle zwischenlabels und endlabels zu haben um sie auch vor jedem methodenaufruf von paintschaltung auch wirklich löschen zu können

    public DigitalCurcuitUI() {

        for (int i = 0; i < 4; i++) {
            String[] EingangNames = {"Eingang-A.png", "Eingang-B.png", "Eingang-C.png", "Eingang-D.png"};
            String selectedEingangName = EingangNames[i];
           // System.out.println(selectedEingangName);
            gatterIcons.add(new ImageIcon(selectedEingangName));
        }

        for (int i = 0; i < 4; i++) {
            String[] gatternames = {"AND-GATTER.jpg", "OR-GATTER.jpg", "NAND-GATTER.jpg", "NOR-GATTER.jpg"};
            String selectedgatterName = gatternames[i];
            //System.out.println(selectedgatterName);
            gatter.add(new ImageIcon(selectedgatterName));
        }

        Image scaledExitImage = exitBild.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledExitIcon = new ImageIcon(scaledExitImage);
        exitButton = new JButton(scaledExitIcon);
        exitButton.setBorderPainted(false);
        exitButton.setFocusPainted(false);
        exitButton.setContentAreaFilled(false);

        setLayout(null);

        schwierigkeitslabel = new JLabel("Wähle die Schwierigkeit");
        aufgabenstellung = new JLabel("Vervollständige auf Basis der Wahrheitstabelle die digitale Schaltung!");

        JTextArea legende = new JTextArea("LEGENDE\n\n  NAND ⊼\n  AND *\n  OR +\n  NOR ⊽ ");
        legende.setBounds(120, 750, 200, 150);
        legende.setFont(new Font("SansSerif", Font.BOLD, 13));
        legende.setEditable(false);

        schwierigkeitslabel.setFont(new Font("SansSerif", Font.BOLD, 11));
        aufgabenstellung.setFont(new Font("SansSerif", Font.BOLD, 18));
        aufgabenstellung.setForeground(Color.BLACK);
        //questionlabel.setForeground(Color.BLACK);
        schwierigkeitslabel.setForeground(Color.BLACK);

        erstellenButton = new JButton("Erstellen");
      //  solutionButton = new JButton("Solution");
        schwierigkeitsmenu = new JComboBox<>(schwierigkeiten);
       // questionsmenu = new JComboBox<>(questions);

      //  questionlabel.setBounds(110, 13, 150, 30);
        aufgabenstellung.setBounds(480, 140, 600, 30);
        schwierigkeitslabel.setBounds(580, 11, 150, 30);
        erstellenButton.setBounds(780, 33, 150, 50);
        schwierigkeitsmenu.setBounds(580, 33, 150, 50);
        //maplegend.setBounds(120, 675, 200, 50);
      //  questionsmenu.setBounds(110, 32, 160, 50);
      //  solutionButton.setBounds(700, 300, 80, 50);
        exitButton.setBounds(1350, 10, 50, 50);

        add(erstellenButton);
        add(schwierigkeitsmenu);
        add(exitButton);
        add(aufgabenstellung);
        add(schwierigkeitslabel);
        add(legende);

        //questionlabel.setVisible(false);
      //  questionsmenu.setVisible(false);
       // solutionButton.setVisible(false);

        //numbers = new Random();

        //Listner, um zeichen zu können
        this.addMouseMotionListener(new PointListener());
        this.addMouseListener(new PointListener());
    }

    //einfach parameter übergeben, die die anzahl der eingänge vorgibt. Z.b int gatteranzahl = 2, oder int = 3
    //int ausgange
    public void createTruthTable(int eingange, int gatterAnzahl) { //nur ein gatter aber dafür alle drei Eingänge drin, einziger unterschied sind, dass es drei kombis sind (not, and, or)
        // Entfernt scrollpane und dazugehörige tabelle, damit es zu beginn keine mehrfachen tabellen gibt
        this.eingange = eingange;
        if (scrollFeld != null) {
            this.remove(scrollFeld);
        }
        revalidate();
        repaint();

        tabellenModel = new DefaultTableModel();
        String[] columnNames = new String[eingange]; //Anzahl der Spalten

        if (eingange > 0) {
            for (int i = 0; i < eingange + gatterAnzahl; i++) {
                if (i < eingange) {
                    char buchstabe = (char) ('A' + i);
                    columnNames[i] = String.valueOf(buchstabe);
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
          //  System.out.println("DIE ZAHL " + i + " = " + binärZahl);
            for (int j = 0; j < binärZahl.length(); j++) { //binärZahl.length ist wichtig, da die Binärzahlen teilweise nur einstellig nach der umwandlung sind
                rowData[i][eingange - binärZahl.length() + j] = Character.getNumericValue(binärZahl.charAt(j)); //auch hier wieder: eingange - binärZahl.length() + j ist notwenig, da eingeange nicht immer auch der binärZahl.länge entsprechen

            }

        }
        tabellenModel.setDataVector(rowData, columnNames);
        tabelle = new JTable(tabellenModel);

        //  table = new JTable(rowData, columnNames);
        tabelle.setVisible(true);
        this.add(tabelle);
        tabelle.setShowGrid(true);
        tabelle.setGridColor(Color.BLACK);
        tabelle.setRowHeight(22);

        //Position X der Tabelle anhand dem Schiwerigkeitsgrade

        scrollFeld = new JScrollPane(tabelle);
        if (schwierigkeit == SchwierigkeitsAuswahl.DIFFICULT) {
            scrollFeld.setBounds(125, 250, tabelle.getColumnCount() * 150, tabelle.getRowCount() * tabelle.getRowHeight() + 20);
        } else{
            scrollFeld.setBounds(205, 300, tabelle.getColumnCount() * 150, tabelle.getRowCount() * tabelle.getRowHeight() + 20);
        }

        add(scrollFeld);

        tabelle.getTableHeader().setReorderingAllowed(false); // not allow re-ordering of columns
        tabelle.getTableHeader().setResizingAllowed(false);
        tabelle.setEnabled(false);
        tabelle.setBackground(Color.LIGHT_GRAY);
        //Ende
    }

    public void addRandomGatter() {
        Random random = new Random();
        int randomGatter = random.nextInt(4);
        zwischengatter.add(randomGatter);
        int spaltenAnzahl = tabelle.getColumnCount();

        if (schwierigkeit == SchwierigkeitsAuswahl.DIFFICULT) {
            int randomX = random.nextInt(4);
            int randomY;
            do {
                randomY = random.nextInt(4);
            } while (randomY == randomX);

            String eingang1;
            String eingang2;
            if (tabellenModel.getColumnCount() - eingange == 0) { //Wenn Spalte 5 noch nicht existiert werden zwei Eingänge random ausgewählt
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
                } while (tabellenModel.getColumnName(4).contains(eingang1) || tabellenModel.getColumnName(4).contains(eingang2) || eingang1.equals(eingang2));
            }

            String spaltenName = randomGatter == 0 ? eingang1 + " * " + eingang2 : randomGatter == 1 ?
                    eingang1 + " + " + eingang2 : randomGatter == 2 ? eingang1 + " ⊼ " + eingang2 : eingang1 + " ⊽ " + eingang2;

            tabellenModel.addColumn(spaltenName); //Setzt den richtigen Spaltennamen, je nach dem welcher Case zufällig generiert wurde

            int spaltenNummer1 = eingang1.equals("A") ? 0 : eingang1.equals("B") ? 1 : eingang1.equals("C") ? 2 : 3;
            int spaltenNummer2 = eingang2.equals("A") ? 0 : eingang2.equals("B") ? 1 : eingang2.equals("C") ? 2 : 3;

            Object x ; //LÖSCHEN
            Object y ;

            for (int i = 0; i < tabelle.getRowCount(); i++) { //Durchgang durch alle Zeilen
                //Wenn spaltenanzahl - eingänge = 0 ist, dann random. Wenn nicht, dann spalte 5 anschauen und die anderen nehmen

                if (tabellenModel.getColumnCount() - eingange - 1 == 0) {
                    x = tabelle.getValueAt(i, randomX);
                    y = tabelle.getValueAt(i, randomY);
                } else { //Wenn bereits eine Spalte existiert:
                    x = tabelle.getValueAt(i, spaltenNummer1);
                    y = tabelle.getValueAt(i, spaltenNummer2);
                }

                switch (randomGatter) {
                    //AND
                    case 0:
                      //  System.out.println("CASE 0");
                        //
                        if (x.equals(1) && y.equals(1)) {
                            tabellenModel.setValueAt("1", i, spaltenAnzahl);
                            break;
                            //direkt hier zur Tabelle hinzufügen
                        } else {
                            tabellenModel.setValueAt("0", i, spaltenAnzahl);
                            break;
                        }

                        //OR
                    case 1:
                      //  System.out.println("CASE 1");
                        //   tableModel.addColumn(eingang1 + " OR " + eingang2);
                        if (!(x.equals(0) && y.equals(0))) {
                            tabellenModel.setValueAt("1", i, spaltenAnzahl);
                            break;
                        } else {
                            tabellenModel.setValueAt("0", i, spaltenAnzahl);
                            break;
                        }

                        //NAND
                    case 2:
                    //    System.out.println("CASE 2");
                        //  tableModel.addColumn(eingang1 + " NAND " + eingang2);
                        if (!(x.equals(1) && y.equals(1))) {
                            tabellenModel.setValueAt("1", i, spaltenAnzahl);
                            break;
                        } else {
                            tabellenModel.setValueAt("0", i, spaltenAnzahl);
                            break;
                        }

                        //NOR
                    case 3:
                    //    System.out.println("CASE 3");
                        //    tableModel.addColumn(eingang1 + " OR " + eingang2);
                        if (!(x.equals(1) && y.equals(1))) {
                            tabellenModel.setValueAt("1", i, spaltenAnzahl);
                            break;
                        } else {
                            tabellenModel.setValueAt("0", i, spaltenAnzahl);
                            break;
                        }
                    default:
                        System.out.println("Fehler bei der Generierung des Gatters!");
                }
            }
        } else {
            String columnName = randomGatter == 0 ? "A * B * C" : randomGatter == 1 ?
                    "A + B + C" : randomGatter == 2 ? "A ⊼ B ⊼ C" : "A ⊽ B ⊽ C";

            endgatter = randomGatter;
            tabellenModel.addColumn(columnName); //Setzt den richtigen Spaltennamen, je nach dem welcher Case zufällig generiert wurde

            for (int i = 0; i < tabelle.getRowCount(); i++) { //Durchgang durch alle Zeilen
                Object a = tabelle.getValueAt(i, 0);
                Object b = tabelle.getValueAt(i, 1);
                Object c = tabelle.getValueAt(i, 2);

                switch (randomGatter) {
                    //AND
                    case 0:
                        if (a.equals(1) && b.equals(1) && c.equals(1)) {
                            tabellenModel.setValueAt("1", i, spaltenAnzahl);
                            break;
                        } else {
                            tabellenModel.setValueAt("0", i, spaltenAnzahl);
                            break;
                        }

                        //OR
                    case 1:
                        if (!(a.equals(0) && b.equals(0) && c.equals(0))) {
                            tabellenModel.setValueAt("1", i, spaltenAnzahl);
                            break;
                        } else {
                            tabellenModel.setValueAt("0", i, spaltenAnzahl);
                            break;
                        }

                        //NAND
                    case 2:
                        if (!(a.equals(1) && b.equals(1) && c.equals(1))) {
                            tabellenModel.setValueAt("1", i, spaltenAnzahl);
                            break;
                        } else {
                            tabellenModel.setValueAt("0", i, spaltenAnzahl);
                            break;
                        }

                        //NOR
                    case 3:
                        if (!(a.equals(1) && b.equals(1) && c.equals(1))) {
                            tabellenModel.setValueAt("1", i, spaltenAnzahl);
                            break;
                        } else {
                            tabellenModel.setValueAt("0", i, spaltenAnzahl);
                            break;
                        }
                    default:
                        System.out.println("Fehler bei der Generierung des Gatters!");
                }
            }
        }
    }

    //Damit nehmen wir alle Ausgänge alle Gatter und packen sie in eins.
    public void addEndGatter() {

        Random random = new Random();
        int randomGatter = random.nextInt(4);
        endgatter = randomGatter;
        int spaltenAnzahl = tabelle.getColumnCount();

        ArrayList<String> spaltenNamen = new ArrayList<>();

        for (int eingang = 0; eingang < spaltenAnzahl; eingang++) {
            spaltenNamen.add(tabellenModel.getColumnName(eingang));
        }

        String spaltenName = "";

        for (int i = 4; i < spaltenNamen.size(); i++) {
            switch (randomGatter) {
                case 0:
                    spaltenName += spaltenNamen.get(i) + " * ";
                    break;

                case 1:
                    spaltenName += spaltenNamen.get(i) + " + ";
                    break;

//nand
                case 2:
                    // System.out.println("CASE2 automatisiert funktioniert");
                    spaltenName += spaltenNamen.get(i) + " ⊼ ";
                    break;
//nor
                case 3:
                    //  System.out.println("CASE3 automatisiert funktioniert");
                    spaltenName += spaltenNamen.get(i) + " ⊽ ";
                    break;

                default:
                    System.out.println("Fehler bei der Generierung des Gatters!");
            }
        }
        //substring weil sonst sich der gattertyp wieder ansetzen würde
        tabellenModel.addColumn(spaltenName.substring(0, spaltenName.length() - 2)); //Hinter dir for-schleife geschoben, da sonst mit jedem schleifendurchgang neue spalten hinzugefügt werden

        for (int i = 0; i < tabelle.getRowCount(); i++) {
            Object x = tabelle.getValueAt(i, 4);
            Object y = tabelle.getValueAt(i, 5);

            switch (randomGatter) {
                //AND
                case 0:
                    if (x.equals("1") && y.equals("1")) {
                        tabellenModel.setValueAt("1", i, spaltenAnzahl);
                        break;
                    } else {
                        tabellenModel.setValueAt("0", i, spaltenAnzahl);
                        break;
                    }

                    //OR
                case 1:
                    if (!(x.equals("0") && y.equals("0"))) {
                        tabellenModel.setValueAt("1", i, spaltenAnzahl);
                        break;
                    } else {
                        tabellenModel.setValueAt("0", i, spaltenAnzahl);
                        break;
                    }

                    //NAND
                case 2:
                    //  System.out.println("End CASE 2");
                    //  tableModel.addColumn(eingang1 + " NAND " + eingang2);
                    if (!(x.equals("1") && y.equals("1"))) {
                        tabellenModel.setValueAt("1", i, spaltenAnzahl);
                        break;
                    } else {
                        tabellenModel.setValueAt("0", i, spaltenAnzahl);
                        break;
                    }

                    //NOR
                case 3:
                    //    System.out.println("End CASE 3");
                    //    tableModel.addColumn(eingang1 + " OR " + eingang2);
                    if (!(x.equals("1") && y.equals("1"))) {
                        tabellenModel.setValueAt("1", i, spaltenAnzahl);
                        break;
                    } else {
                        tabellenModel.setValueAt("0", i, spaltenAnzahl);
                        break;
                    }
                default:
                    System.out.println("Fehler bei der Generierung des Gatters!");
            }
        }

    }

    //public void paintSchaltung(List<ImageIcon> gateIcons) {
    // Vor dem Zeichnen der neuen Schaltung entferne die vorherige
    //   gateLabels.forEach(this::remove); nicht notwendig
    //   gateLabels.clear();

    public void drawSchaltung() {
        for (JLabel label : erstelltenLabels) {
            remove(label);
        }
        erstelltenLabels.clear();

        int xEingang = 800;  //Startposition Eingänge
        int xGatter = 975;  //Startposition Gatter
        int xEndGatter = 1150;  //Startposition Endgatter
        int y = 300;

        for (int i = 0; i < eingange; i++) {

            ImageIcon icon = gatterIcons.get(i);
            JLabel einganglabel = new JLabel(icon);
            einganglabel.setBounds(xEingang, y, 50, 50);
            add(einganglabel);
            erstelltenLabels.add(einganglabel);

            y += 100;
        }

        if (schwierigkeit == SchwierigkeitsAuswahl.DIFFICULT) {
            int yZG = 300;

            for (int i = 0; i < zwischengatter.size(); i++) {
                switch (zwischengatter.get(i)) {
                    case 0:
                        ImageIcon zwischengattericon = gatter.get(zwischengatter.get(i));
                        zwischenlabel = new JLabel(zwischengattericon);
                        zwischenlabel.setBounds(xGatter, yZG, 100, 100);
                        add(zwischenlabel);
                        erstelltenLabels.add(zwischenlabel);
                        break;

                    case 1:
                        zwischengattericon = gatter.get(zwischengatter.get(i));
                        zwischenlabel = new JLabel(zwischengattericon);
                        zwischenlabel.setBounds(xGatter, yZG, 100, 100);
                        add(zwischenlabel);
                        erstelltenLabels.add(zwischenlabel);
                        break;
//nand
                    case 2:
                        zwischengattericon = gatter.get(zwischengatter.get(i));
                        zwischenlabel = new JLabel(zwischengattericon);
                        zwischenlabel.setBounds(xGatter, yZG, 100, 100);
                        add(zwischenlabel);
                        erstelltenLabels.add(zwischenlabel);
                        break;
//nor
                    case 3:
                        zwischengattericon = gatter.get(zwischengatter.get(i));
                        zwischenlabel = new JLabel(zwischengattericon);
                        zwischenlabel.setBounds(xGatter, yZG, 100, 100);
                        add(zwischenlabel);
                        erstelltenLabels.add(zwischenlabel);
                        break;
                }
                yZG += 100; //neu
            }
        }
        zwischengatter.clear();

        switch (endgatter) {
            //AND
            case 0:
                ImageIcon endgattericon = gatter.get(endgatter);
                endlabel = new JLabel(endgattericon);
                endlabel.setBounds(xEndGatter, 400, 100, 100);
                add(endlabel);
                erstelltenLabels.add(endlabel);
                break;

            //OR
            case 1:
                endgattericon = gatter.get(endgatter);
                endlabel = new JLabel(endgattericon);
                endlabel.setBounds(xEndGatter, 400, 100, 100);
                add(endlabel);
                erstelltenLabels.add(endlabel);
                break;

            //NAND
            case 2:
               // System.out.println("XY Case 2");
                endgattericon = gatter.get(endgatter);
                endlabel = new JLabel(endgattericon);
                endlabel.setBounds(xEndGatter, 400, 100, 100);
                //  gatter.add(label);
              //  System.out.println("Endgatter: " + endgattericon);
                add(endlabel);
                erstelltenLabels.add(endlabel);
                break;

            //NOR
            case 3:
              //  System.out.println("XY Case 3");
                endgattericon = gatter.get(endgatter);
                endlabel = new JLabel(endgattericon);
                endlabel.setBounds(xEndGatter, 400, 100, 100);
                //  gatter.add(label);
              //  System.out.println("Endgatter: " + endgattericon);
                add(endlabel);
                erstelltenLabels.add(endlabel);
                break;

            default:
                System.out.println("Fehler bei der Generierung des Gatters!");
        }

        //}

        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(hintergrundsbild, 0, 0, getWidth(), getHeight(), this);
        g.setColor(Color.BLACK);
        g.drawRoundRect(100, 120, 1200, 750, 20, 20);
        g.setColor(Color.WHITE);
        g.fillRoundRect(100, 120, 1200, 750, 20, 20);

        g.setColor(Color.BLACK);
        g.drawRoundRect(100, 15, 1200, 75, 20, 20);
        g.setColor(Color.WHITE);
        g.fillRoundRect(100, 15, 1200, 75, 20, 20);

        //neu gemacht --> david braucht das noch
        g.setColor(Color.BLACK);
        g.fillRect(750, 220, 5, 435);

        //Umrandung ums Exit-Button --> david braucht das noch
        g.setColor(Color.WHITE);
        g.fillRoundRect(1350, 5, 60, 60, 20, 20);


// Das zeichnet die Linien
        g.setColor(Color.BLACK);
        for (int i = 0; i < spListe.size(); i++) {
            g.drawLine(spListe.get(i).x, spListe.get(i).y, epListe.get(i).x, epListe.get(i).y);
        }

    }

    //ActionListener für das Zeichnen
    private class PointListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
           // System.out.println("Mouse Pressed");
            startPunkt = e.getPoint();
            // spList.add(start); das geht auch hier oben, das macht keinen Unterschied
        }

        public void mouseReleased(MouseEvent e) {
           // System.out.println("Mouse Released");
            spListe.add(startPunkt);
            endPunkt = e.getPoint();
            epListe.add(endPunkt);
            repaint();
        }

        public void mouseDragged(MouseEvent e) {
        }

/*
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

 */
    }
}


