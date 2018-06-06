import java.awt.*;


import javax.swing.*;
import javax.swing.JPasswordField;


public class AbimottoWahlGUI extends JFrame {
  /**
   * # Relevant
   */

  /**
   * ## Datenbank
   */
  private DatabaseConnector db;

  private JTextField txtDBPasswort = new JTextField();
  private JTextField txtDBName = new JTextField();
  private JTextField txtDBUsername = new JTextField();
  private JTextField txtDBHostname = new JTextField();
  private JTextField txtDBPort = new JTextField();

  /**
   * ## Motto
   */
  private JTextField txtMottoVorschlag = new JTextField();
  private JList<String> mottoListe = new JList<>();

  /**
   * ## Registrierung
   */
  private JTextField txtVorname = new JTextField();
  private JTextField txtNachname = new JTextField();
  private JPasswordField txtPasswort = new JPasswordField();

  /**
   * ## Abstimmung
   */
  private JPasswordField txtPasswortAbstimmung = new JPasswordField();

  /**
   * ## etc
   */

  private JTextArea txtAusgabe = new JTextArea("");

  /**
   * # Buttons
   */
  private JButton btnAbstimmen = new JButton();
  private JButton btnVerbinden = new JButton();
  private JButton btnVerbindungTrennen = new JButton();
  private JButton btnVorname = new JButton();
  private JButton btnRegistrieren = new JButton();
  private JButton btnRanking = new JButton();
  private JButton btnMottoVorschlagen = new JButton();
  private JButton btnZeigeMottos = new JButton();

  private AbimottoWahlGUI(String title) {
    // Frame-Initialisierung
    super(title);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    
    int frameWidth = 696; 
    int frameHeight = 742;
    setSize(frameWidth, frameHeight);
    
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    txtVorname.setBounds(96, 104, 105, 24);
    txtVorname.setText("");
    txtVorname.setToolTipText("Vorname");
    cp.add(txtVorname);
    txtNachname.setBounds(96, 128, 105, 24);
    txtNachname.setText("");
    txtNachname.setToolTipText("Nachname");
    cp.add(txtNachname);
    btnAbstimmen.setBounds(136, 656, 123, 41);
    btnAbstimmen.setText("btnAbstimmen");
    btnAbstimmen.addActionListener(evt -> abstimmen());
    btnAbstimmen.setEnabled(false);
    btnAbstimmen.setFont(new Font("Dialog", Font.BOLD, 12));
    cp.add(btnAbstimmen);
    
    btnRanking.setBounds(136, 224, 121, 41);
    btnRanking.setText("Ranking");
    btnRanking.setMargin(new Insets(2, 2, 2, 2));
    btnRanking.addActionListener(evt -> zeigeRankingAn());
    btnRanking.setEnabled(false);
    cp.add(btnRanking);
    JTextField txtVornameSuchen = new JTextField();
    txtVornameSuchen.setBounds(80, 744, 161, 24);
    cp.add(txtVornameSuchen);
    btnVorname.setBounds(352, 736, 113, 41);
    btnVorname.setText("Vorname suchen");
    btnVorname.setMargin(new Insets(2, 2, 2, 2));
    btnVorname.addActionListener(evt -> vornameSuchen());
    btnVorname.setEnabled(false);
    cp.add(btnVorname);
    btnRegistrieren.setBounds(8, 184, 193, 33);
    btnRegistrieren.setText("Registrieren");
    btnRegistrieren.setMargin(new Insets(2, 2, 2, 2));
    btnRegistrieren.addActionListener(evt -> neuenNutzerRegistrieren());
    btnRegistrieren.setEnabled(false);
    cp.add(btnRegistrieren);
    txtPasswort.setBounds(96, 152, 105, 25);
    txtPasswort.setEchoChar('*');
    cp.add(txtPasswort);
    JLabel jLabel1 = new JLabel();
    jLabel1.setBounds(16, 104, 78, 20);
    jLabel1.setText("Vorname:");
    cp.add(jLabel1);
    JLabel jLabel2 = new JLabel();
    jLabel2.setBounds(16, 128, 75, 25);
    jLabel2.setText("Nachname:");
    cp.add(jLabel2);
    JLabel jLabel3 = new JLabel();
    jLabel3.setBounds(16, 152, 78, 28);
    jLabel3.setText("Passwort:");
    cp.add(jLabel3);
    JLabel lblMottoVorschlag = new JLabel();
    lblMottoVorschlag.setBounds(16, 304, 98, 20);
    lblMottoVorschlag.setText("Mottovorschlag:");
    cp.add(lblMottoVorschlag);
    txtMottoVorschlag.setBounds(8, 328, 249, 25);
    cp.add(txtMottoVorschlag);
    btnMottoVorschlagen.setBounds(8, 360, 153, 41);
    btnMottoVorschlagen.setText("Motto vorschlagen");
    btnMottoVorschlagen.setMargin(new Insets(2, 2, 2, 2));
    btnMottoVorschlagen.addActionListener(evt -> neuesMottoVorschlagen());
    btnMottoVorschlagen.setEnabled(false);
    cp.add(btnMottoVorschlagen);
    JLabel jLabel4 = new JLabel();
    jLabel4.setBounds(16, 516, 78, 20);
    jLabel4.setText("Mottos:");
    cp.add(jLabel4);
    btnZeigeMottos.setBounds(8, 224, 121, 41);
    btnZeigeMottos.setText("Mottos anzeigen");
    btnZeigeMottos.setMargin(new Insets(2, 2, 2, 2));
    btnZeigeMottos.addActionListener(evt -> zeigeMottosAn());
    btnZeigeMottos.setEnabled(false);
    cp.add(btnZeigeMottos);
    JLabel jLabel5 = new JLabel();
    jLabel5.setBounds(8, 72, 194, 28);
    jLabel5.setText("Registrierung");
    jLabel5.setFont(new Font("Calibri", Font.BOLD, 20));
    jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(jLabel5);
    JLabel jLabel6 = new JLabel();
    jLabel6.setBounds(8, 272, 250, 28);
    jLabel6.setText("Vorschlagen");
    jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(jLabel6);
    JLabel jLabel7 = new JLabel();
    jLabel7.setBounds(16, 408, 234, 28);
    jLabel7.setText("btnAbstimmen");
    jLabel7.setHorizontalTextPosition(SwingConstants.CENTER);
    jLabel7.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(jLabel7);
    JTextField txtNachname1 = new JTextField();
    txtNachname1.setBounds(96, 468, 161, 24);
    txtNachname1.setToolTipText("Nachname");
    cp.add(txtNachname1);
    JTextField txtVorname1 = new JTextField();
    txtVorname1.setBounds(96, 444, 161, 24);
    txtVorname1.setToolTipText("Vorname");
    cp.add(txtVorname1);
    JLabel jLabel8 = new JLabel();
    jLabel8.setBounds(16, 444, 78, 20);
    jLabel8.setText("Vorname:");
    cp.add(jLabel8);
    JLabel jLabel9 = new JLabel();
    jLabel9.setBounds(16, 468, 75, 25);
    jLabel9.setText("Nachname:");
    cp.add(jLabel9);
    JLabel jLabel10 = new JLabel();
    jLabel10.setBounds(0, 712, 74, 28);
    jLabel10.setText("Suchen");
    jLabel10.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel10.setFont(new Font("Calibri", Font.BOLD, 20));
    cp.add(jLabel10);
    JLabel jLabel11 = new JLabel();
    jLabel11.setBounds(8, 744, 70, 20);
    jLabel11.setText("Vorname:");
    cp.add(jLabel11);
    JLabel jLabel12 = new JLabel();
    jLabel12.setBounds(8, 8, 668, 48);
    jLabel12.setText("Abstimmung des Abi-Mottos");
    jLabel12.setFont(new Font("Calibri", Font.BOLD, 36));
    jLabel12.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(jLabel12);
    btnVerbinden.setBounds(224, 56, 153, 41);
    btnVerbinden.setText("Mit DB dbVerbinden");
    btnVerbinden.setMargin(new Insets(2, 2, 2, 2));
    btnVerbinden.addActionListener(evt -> dbVerbinden());
    cp.add(btnVerbinden);
    btnVerbindungTrennen.setBounds(224, 104, 153, 41);
    btnVerbindungTrennen.setText("Verbindung trennen");
    btnVerbindungTrennen.setMargin(new Insets(2, 2, 2, 2));
    btnVerbindungTrennen.addActionListener(evt -> dbTrennen());
    btnVerbindungTrennen.setEnabled(false);
    cp.add(btnVerbindungTrennen);
    txtDBUsername.setBounds(464, 48, 105, 24);
    txtDBUsername.setFont(new Font("Dialog", Font.PLAIN, 13));
    txtDBUsername.setToolTipText("Benutzer");
    txtDBUsername.setText("root");
    cp.add(txtDBUsername);
    JLabel jLabel13 = new JLabel();
    jLabel13.setBounds(384, 52, 78, 20);
    jLabel13.setText("Benutzer:");
    cp.add(jLabel13);
    JLabel jLabel14 = new JLabel();
    jLabel14.setBounds(384, 76, 78, 20);
    jLabel14.setText("Passwort:");
    cp.add(jLabel14);
    txtDBHostname.setBounds(464, 96, 209, 24);
    txtDBHostname.setFont(new Font("Dialog", Font.PLAIN, 13));
    txtDBHostname.setToolTipText("URL");
    txtDBHostname.setText("127.0.0.1");
    cp.add(txtDBHostname);
    JLabel jLabel15 = new JLabel();
    jLabel15.setBounds(384, 100, 70, 20);
    jLabel15.setText("Url:");
    cp.add(jLabel15);
    JLabel jLabel16 = new JLabel();
    jLabel16.setBounds(384, 148, 70, 20);
    jLabel16.setText("DB:");
    cp.add(jLabel16);
    txtDBPasswort.setBounds(464, 72, 105, 24);
    txtDBPasswort.setToolTipText("Passwort");
    cp.add(txtDBPasswort);
    txtDBName.setBounds(464, 144, 105, 24);
    txtDBName.setFont(new Font("Dialog", Font.PLAIN, 13));
    txtDBName.setToolTipText("DB-Name");
    txtDBName.setText("abimotto");
    cp.add(txtDBName);
    JButton btnClear = new JButton();
    btnClear.setBounds(560, 656, 113, 41);
    btnClear.setText("Clear");
    btnClear.setMargin(new Insets(2, 2, 2, 2));
    btnClear.addActionListener(evt -> ausgabeFeldLeeren());
    btnClear.setEnabled(true);
    btnClear.setVisible(true);
    cp.add(btnClear);
    DefaultListModel<String> jListMottosModel = new DefaultListModel<>();
    mottoListe.setModel(jListMottosModel);
    JScrollPane jListMottosScrollPane = new JScrollPane(mottoListe);
    jListMottosScrollPane.setBounds(8, 536, 254, 116);
    cp.add(jListMottosScrollPane);

    JLabel jLabel17 = new JLabel();
    jLabel17.setBounds(16, 492, 78, 28);
    jLabel17.setText("Passwort:");
    cp.add(jLabel17);
    txtPasswortAbstimmung.setBounds(96, 492, 161, 25);
    cp.add(txtPasswortAbstimmung);
    JScrollPane jtAAusgabeScrollPane = new JScrollPane(txtAusgabe);
    jtAAusgabeScrollPane.setBounds(272, 168, 393, 481);
    cp.add(jtAAusgabeScrollPane);
    JLabel lPort = new JLabel();
    lPort.setBounds(384, 124, 70, 20);
    lPort.setText("Port:");
    cp.add(lPort);
    txtDBPort.setBounds(464, 120, 105, 24);
    txtDBPort.setFont(new Font("Dialog", Font.PLAIN, 13));
    txtDBPort.setText("3306");
    txtDBPort.setToolTipText("Port");
    cp.add(txtDBPort);
    // Ende Komponenten
    setResizable(false);
    setVisible(true);
    
    // TODO Datenbankverbindung
  }

  private void dbVerbinden() {
    // TODO Aufgabenteil a) hier Quelltext einfügen
    String user = txtDBUsername.getText();
    String password = txtDBPasswort.getText();
    String url = txtDBHostname.getText();
    String dbName = txtDBName.getText();
    int port = Integer.parseInt(txtDBPort.getText());

    db = new DatabaseConnector(url, port, dbName, user, password);
    txtAusgabe.setText("");

    String errorMessage = db.getErrorMessage();

    if (errorMessage == null) {
      txtAusgabe.append("Verbindung zur DB hergestellt!");
      btnAbstimmen.setEnabled(true);
      btnVerbinden.setEnabled(false);
      btnVerbindungTrennen.setEnabled(true);
      btnVorname.setEnabled(true);
      btnRegistrieren.setEnabled(true);
      btnRanking.setEnabled(true);
      btnMottoVorschlagen.setEnabled(true);
      btnZeigeMottos.setEnabled(true);
    } else {
      txtAusgabe.append(errorMessage);
    }
  }

  private void dbTrennen() {
    // TODO Aufgabenteil a) hier Quelltext einfügen
    db.close();

    String errorMessage = db.getErrorMessage();
    if (errorMessage == null) {
      txtAusgabe.append("Verbindung zur DB beendet!");
      btnAbstimmen.setEnabled(false);
      btnVerbinden.setEnabled(true);
      btnVerbindungTrennen.setEnabled(false);
      btnVorname.setEnabled(false);
      btnRegistrieren.setEnabled(false);
      btnRanking.setEnabled(false);
      btnMottoVorschlagen.setEnabled(false);
      btnZeigeMottos.setEnabled(false);
    } else {
      txtAusgabe.append("Verbindung zur DB konte nicht beendet werden!");
      System.out.println(errorMessage);
    }
  }

  public void zeigeMottosAn() {
    // TODO Aufgabenteil b) hier Quelltext einfügen
  
    
  }

  public void neuesMottoVorschlagen() {
    // TODO Aufgabenteil c) hier Quelltext einfügen
    String motto = txtMottoVorschlag.getText();

    if (motto.isEmpty()) {
      showError("Bitte gib ein Motto ein!");
      return;
    }

    // Abfrage, ob das Motto bereits existiert

    // Motto Einfügen

  }

  private void showError(String message) {
    JOptionPane.showMessageDialog(this, message,
      "Fehler", JOptionPane.ERROR_MESSAGE);
  }

  public void zeigeRankingAn() {
    // TODO Aufgabenteil d) hier Quelltext einfügen
    
  }

  public void abstimmen() {
    // TODO Aufgabenteil d) hier Quelltext einfügen
    // Motto ausgewählt?
    if (!mottoListe.isSelectionEmpty()) {
      
      char[] passwordChars = txtPasswortAbstimmung.getPassword();
      String passwort = new String(passwordChars);
      
      // UID abfragen und speichern

      // Registrierter Nutzer?

      // Nicht registrierter Nutzer
      
    }
  }

  public void neuenNutzerRegistrieren() {
    String vorname = txtVorname.getText();
    String nachname = txtNachname.getText();
    char[] passwordChars = txtPasswort.getPassword();
    String passwort = new String(passwordChars);
    
    // Abfrage, ob der Benutzer bereits existiert

    // JOptionPane.showMessageDialog(this, "Der Benutzer existiert bereits!", "Fehler", JOptionPane.ERROR_MESSAGE);
    
  }

  public void ausgabeFeldLeeren() {
    txtAusgabe.setText("");
  }

  public void vornameSuchen() {

  }

  public String ergebnisRelationToString(QueryResult pRes) {
    String ausgabe = "";
    // TODO von Aufgabe 2 kopieren
    return ausgabe;
  }

  public static void main(String[] args) {
    new AbimottoWahlGUI("Mottoverwaltung");
  }
}
