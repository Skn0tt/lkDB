import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.stream.Stream;


import javax.swing.*;
import javax.swing.JPasswordField;
import javax.swing.event.*;


public class AbimottoWahl extends JFrame {
  // Anfang Attribute
  private JTextField txtVorname = new JTextField();
  private JTextField txtNachname = new JTextField();
  private JButton Abstimmen = new JButton();
  private JButton btnVerbinden = new JButton();
  private JButton btnVerbindungTrennen = new JButton();
  private JButton btnVorname = new JButton();
  private JButton btnRegistrieren = new JButton();
  private JButton btnRanking = new JButton();
  private JButton btnMottoVorschlagen = new JButton();
  private JButton btnZeigeMottos = new JButton();

  //Protokoll
  private DefaultListModel listModel = new DefaultListModel();
  private JTextField txtVornameSuchen = new JTextField();
  private JPasswordField txtPasswort = new JPasswordField();
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private JLabel jLabel3 = new JLabel();
  private JLabel lblMottoVorschlag = new JLabel();
  private JTextField txtMottoVorschlag = new JTextField();
  private JLabel jLabel4 = new JLabel();
  private JLabel jLabel5 = new JLabel();
  private JLabel jLabel6 = new JLabel();
  private JLabel jLabel7 = new JLabel();
  private JTextField txtNachname1 = new JTextField();
  private JTextField txtVorname1 = new JTextField();
  private JLabel jLabel8 = new JLabel();
  private JLabel jLabel9 = new JLabel();
  private JLabel jLabel10 = new JLabel();
  private JLabel jLabel11 = new JLabel();
  private JLabel jLabel12 = new JLabel();
  private JTextField txtBenutzer = new JTextField();
  private JLabel jLabel13 = new JLabel();
  private JLabel jLabel14 = new JLabel();
  private JTextField tf127001 = new JTextField();
  private JLabel jLabel15 = new JLabel();
  private JLabel jLabel16 = new JLabel();
  private JTextField txtPasswortDB1 = new JTextField();
  private JTextField txtDBName = new JTextField();
  private DatabaseConnector db;
  private JButton btnClear = new JButton();
  private JList<String> jListMottos = new JList<>();
  private DefaultListModel jListMottosModel = new DefaultListModel();
  private JScrollPane jListMottosScrollPane = new JScrollPane(jListMottos);
  private JLabel jLabel17 = new JLabel();
  private JPasswordField txtPasswortAbstimmung = new JPasswordField();

  private JTextArea jtAAusgabe = new JTextArea("");
  private JScrollPane jtAAusgabeScrollPane = new JScrollPane(jtAAusgabe);
  private JLabel lPort = new JLabel();
  private JTextField txtPort = new JTextField();
  // Ende Attribute
  public AbimottoWahl(String title) {
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
    Abstimmen.setBounds(136, 656, 123, 41);
    Abstimmen.setText("Abstimmen");
    Abstimmen.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
    Abstimmen_ActionPerformed(evt);
    }
    });
    Abstimmen.setEnabled(false);
    Abstimmen.setFont(new Font("Dialog", Font.BOLD, 12));
    cp.add(Abstimmen);
    
    btnRanking.setBounds(136, 224, 121, 41);
    btnRanking.setText("Ranking");
    btnRanking.setMargin(new Insets(2, 2, 2, 2));
    btnRanking.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
    btnRanking_ActionPerformed(evt);
    }
    });
    btnRanking.setEnabled(false);
    cp.add(btnRanking);
    txtVornameSuchen.setBounds(80, 744, 161, 24);
    cp.add(txtVornameSuchen);
    btnVorname.setBounds(352, 736, 113, 41);
    btnVorname.setText("Vorname suchen");
    btnVorname.setMargin(new Insets(2, 2, 2, 2));
    btnVorname.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
    btnVorname_ActionPerformed(evt);
    }
    });
    btnVorname.setEnabled(false);
    cp.add(btnVorname);
    btnRegistrieren.setBounds(8, 184, 193, 33);
    btnRegistrieren.setText("Registrieren");
    btnRegistrieren.setMargin(new Insets(2, 2, 2, 2));
    btnRegistrieren.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
    btnRegistrieren_ActionPerformed(evt);
    }
    });
    btnRegistrieren.setEnabled(false);
    cp.add(btnRegistrieren);
    txtPasswort.setBounds(96, 152, 105, 25);
    txtPasswort.setEchoChar('*');
    cp.add(txtPasswort);
    jLabel1.setBounds(16, 104, 78, 20);
    jLabel1.setText("Vorname:");
    cp.add(jLabel1);
    jLabel2.setBounds(16, 128, 75, 25);
    jLabel2.setText("Nachname:");
    cp.add(jLabel2);
    jLabel3.setBounds(16, 152, 78, 28);
    jLabel3.setText("Passwort:");
    cp.add(jLabel3);
    lblMottoVorschlag.setBounds(16, 304, 98, 20);
    lblMottoVorschlag.setText("Mottovorschlag:");
    cp.add(lblMottoVorschlag);
    txtMottoVorschlag.setBounds(8, 328, 249, 25);
    cp.add(txtMottoVorschlag);
    btnMottoVorschlagen.setBounds(8, 360, 153, 41);
    btnMottoVorschlagen.setText("Motto vorschlagen");
    btnMottoVorschlagen.setMargin(new Insets(2, 2, 2, 2));
    btnMottoVorschlagen.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
    btnMottoVorschlagen_ActionPerformed(evt);
    }
    });
    btnMottoVorschlagen.setEnabled(false);
    cp.add(btnMottoVorschlagen);
    jLabel4.setBounds(16, 516, 78, 20);
    jLabel4.setText("Mottos:");
    cp.add(jLabel4);
    btnZeigeMottos.setBounds(8, 224, 121, 41);
    btnZeigeMottos.setText("Mottos anzeigen");
    btnZeigeMottos.setMargin(new Insets(2, 2, 2, 2));
    btnZeigeMottos.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
    btnZeigeMottos_ActionPerformed(evt);
    }
    });
    btnZeigeMottos.setEnabled(false);
    cp.add(btnZeigeMottos);
    jLabel5.setBounds(8, 72, 194, 28);
    jLabel5.setText("Registrierung");
    jLabel5.setFont(new Font("Calibri", Font.BOLD, 20));
    jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(jLabel5);
    jLabel6.setBounds(8, 272, 250, 28);
    jLabel6.setText("Vorschlagen");
    jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(jLabel6);
    jLabel7.setBounds(16, 408, 234, 28);
    jLabel7.setText("Abstimmen");
    jLabel7.setHorizontalTextPosition(SwingConstants.CENTER);
    jLabel7.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(jLabel7);
    txtNachname1.setBounds(96, 468, 161, 24);
    txtNachname1.setToolTipText("Nachname");
    cp.add(txtNachname1);
    txtVorname1.setBounds(96, 444, 161, 24);
    txtVorname1.setToolTipText("Vorname");
    cp.add(txtVorname1);
    jLabel8.setBounds(16, 444, 78, 20);
    jLabel8.setText("Vorname:");
    cp.add(jLabel8);
    jLabel9.setBounds(16, 468, 75, 25);
    jLabel9.setText("Nachname:");
    cp.add(jLabel9);
    jLabel10.setBounds(0, 712, 74, 28);
    jLabel10.setText("Suchen");
    jLabel10.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel10.setFont(new Font("Calibri", Font.BOLD, 20));
    cp.add(jLabel10);
    jLabel11.setBounds(8, 744, 70, 20);
    jLabel11.setText("Vorname:");
    cp.add(jLabel11);
    jLabel12.setBounds(8, 8, 668, 48);
    jLabel12.setText("Abstimmung des Abi-Mottos");
    jLabel12.setFont(new Font("Calibri", Font.BOLD, 36));
    jLabel12.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(jLabel12);
    btnVerbinden.setBounds(224, 56, 153, 41);
    btnVerbinden.setText("Mit DB verbinden");
    btnVerbinden.setMargin(new Insets(2, 2, 2, 2));
    btnVerbinden.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
    btnVerbinden_ActionPerformed(evt);
    }
    });
    cp.add(btnVerbinden);
    btnVerbindungTrennen.setBounds(224, 104, 153, 41);
    btnVerbindungTrennen.setText("Verbindung trennen");
    btnVerbindungTrennen.setMargin(new Insets(2, 2, 2, 2));
    btnVerbindungTrennen.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
    btnVerbindungTrennen_ActionPerformed(evt);
    }
    });
    btnVerbindungTrennen.setEnabled(false);
    cp.add(btnVerbindungTrennen);
    txtBenutzer.setBounds(464, 48, 105, 24);
    txtBenutzer.setFont(new Font("Dialog", Font.PLAIN, 13));
    txtBenutzer.setToolTipText("Benutzer");
    txtBenutzer.setText("root");
    cp.add(txtBenutzer);
    jLabel13.setBounds(384, 52, 78, 20);
    jLabel13.setText("Benutzer:");
    cp.add(jLabel13);
    jLabel14.setBounds(384, 76, 78, 20);
    jLabel14.setText("Passwort:");
    cp.add(jLabel14);
    tf127001.setBounds(464, 96, 209, 24);
    tf127001.setFont(new Font("Dialog", Font.PLAIN, 13));
    tf127001.setToolTipText("URL");
    tf127001.setText("127.0.0.1");
    cp.add(tf127001);
    jLabel15.setBounds(384, 100, 70, 20);
    jLabel15.setText("Url:");
    cp.add(jLabel15);
    jLabel16.setBounds(384, 148, 70, 20);
    jLabel16.setText("DB:");
    cp.add(jLabel16);
    txtPasswortDB1.setBounds(464, 72, 105, 24);
    txtPasswortDB1.setToolTipText("Passwort");
    cp.add(txtPasswortDB1);
    txtDBName.setBounds(464, 144, 105, 24);
    txtDBName.setFont(new Font("Dialog", Font.PLAIN, 13));
    txtDBName.setToolTipText("DB-Name");
    txtDBName.setText("abimotto");
    cp.add(txtDBName);
    btnClear.setBounds(560, 656, 113, 41);
    btnClear.setText("Clear");
    btnClear.setMargin(new Insets(2, 2, 2, 2));
    btnClear.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
    btnClear_ActionPerformed(evt);
    }
    });
    btnClear.setEnabled(true);
    btnClear.setVisible(true);
    cp.add(btnClear);
    jListMottos.setModel(jListMottosModel);
    jListMottosScrollPane.setBounds(8, 536, 254, 116);
    cp.add(jListMottosScrollPane);
    
    jLabel17.setBounds(16, 492, 78, 28);
    jLabel17.setText("Passwort:");
    cp.add(jLabel17);
    txtPasswortAbstimmung.setBounds(96, 492, 161, 25);
    cp.add(txtPasswortAbstimmung);
    jtAAusgabeScrollPane.setBounds(272, 168, 393, 481);
    cp.add(jtAAusgabeScrollPane);
    lPort.setBounds(384, 124, 70, 20);
    lPort.setText("Port:");
    cp.add(lPort);
    txtPort.setBounds(464, 120, 105, 24);
    txtPort.setFont(new Font("Dialog", Font.PLAIN, 13));
    txtPort.setText("3306");
    txtPort.setToolTipText("Port");
    cp.add(txtPort);
    // Ende Komponenten
    setResizable(false);
    setVisible(true);
    
    //Datenbankverbindung
  }

  // Anfang Methoden
  public void btnVerbinden_ActionPerformed(ActionEvent evt) {
    // TODO Aufgabenteil a) hier Quelltext einfügen
    String user = txtBenutzer.getText();
    String password = txtPasswortDB1.getText();
    String url = tf127001.getText();
    String dbName = txtDBName.getText();
    int port = Integer.parseInt(txtPort.getText());
    try {
      DB.setup(url, port, dbName, user, password);
      this.jtAAusgabe.setText("");
      jtAAusgabe.append("Verbindung zur DB hergestellt!");
      Abstimmen.setEnabled(true);
      btnVerbinden.setEnabled(false);
      btnVerbindungTrennen.setEnabled(true);
      btnVorname.setEnabled(true);
      btnRegistrieren.setEnabled(true);
      btnRanking.setEnabled(true);
      btnMottoVorschlagen.setEnabled(true);
      btnZeigeMottos.setEnabled(true);
    } catch (SQLException e) {
      jtAAusgabe.append(e.getMessage());
    }
  } // end of btnVerbinden_ActionPerformed

  public void btnVerbindungTrennen_ActionPerformed(ActionEvent evt) {
    // TODO Aufgabenteil a) hier Quelltext einfügen
    try {
      db.close();
      jtAAusgabe.append("Verbindung zur DB beendet!");
      Abstimmen.setEnabled(false);
      btnVerbinden.setEnabled(true);
      btnVerbindungTrennen.setEnabled(false);
      btnVorname.setEnabled(false);
      btnRegistrieren.setEnabled(false);
      btnRanking.setEnabled(false);
      btnMottoVorschlagen.setEnabled(false);
      btnZeigeMottos.setEnabled(false);
    } catch(Exception e) {
      jtAAusgabe.append("Verbindung zur DB konte nicht beendet werden!");
      System.out.println(e);
    } finally {
      
    } // end of try
    
    
  } // end of btnVerbindungTrennen_ActionPerformed

  public void btnZeigeMottos_ActionPerformed(ActionEvent evt) {
    try {
      String[] mottos = AbimottoDB.mottoList();

      jListMottosModel.clear();
      for (String motto : mottos) {
        jListMottosModel.addElement(motto);
      }
    } catch (SQLException e) {}
  } // end of btnZeigeMottos_ActionPerformed

  public void btnMottoVorschlagen_ActionPerformed(ActionEvent evt) {
    // TODO Aufgabenteil c) hier Quelltext einfügen
    String motto = txtMottoVorschlag.getText();
    
    if (motto.isEmpty()) {
      JOptionPane.showMessageDialog(this, "Bitte gib ein Motto ein!",
      "Fehler", JOptionPane.ERROR_MESSAGE);
    } // end of if
    else {
      //Abfrage, ob das Motto bereits existiert
      try {
        AbimottoDB.addMotto(motto);
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
    } // end of if-else
  } // end of btnMottoVorschlagen_ActionPerformed

  public void btnRanking_ActionPerformed(ActionEvent evt) {
    // TODO Aufgabenteil d) hier Quelltext einfügen
    try {
      String ranking = AbimottoDB.ranking();
      jtAAusgabe.setText(ranking);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  } // end of btnRanking_ActionPerformed

  public void Abstimmen_ActionPerformed(ActionEvent evt) {
    // TODO Aufgabenteil d) hier Quelltext einfügen
    //Motto ausgewählt?
    if (!jListMottos.isSelectionEmpty()) {
      
      char[] passw = txtPasswortAbstimmung.getPassword();
      String passwort = "";
      //Passwort auslesen und zusammensetzen
      for (int i = 0; i < passw.length; i++) {
        passwort += passw[i];
      } // end of for
      
      //UID abfragen und speichern
      String vorname = txtVorname1.getText();
      String nachname = txtNachname1.getText();

      try {
        String UID = AbimottoDB.findUserID(vorname, nachname, passwort);
        //Registrierter Nutzer?
        if (UID != null) {
          String motto = (String) jListMottosModel.get(jListMottos.getSelectedIndex());
          AbimottoDB.voteMotto(UID, motto);
        }
        //Nicht registrierter Nutzer
        else {
          // Throw fatal error, yell at user
        }
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      } catch (AbimottoDB.UserNotFoundException e) {
        System.out.print("User not found.");
      }
    } // end of if-else
  } // end of Abstimmen_ActionPerformed 
        
  public void btnRegistrieren_ActionPerformed(ActionEvent evt) {
    String vorname = txtVorname.getText();
    String nachname = txtNachname.getText();
    char[] passw = txtPasswort.getPassword();
    String passwort = "";
    
    for (int i = 0; i < passw.length; i++) {
      passwort += passw[i];
    } // end of for

    try {
      //Abfrage, ob der Benutzer bereits existiert
      if (AbimottoDB.userAlreadyExists(vorname, nachname)) {
        JOptionPane.showMessageDialog(this, "Der Benutzer existiert bereits!", "Fehler", JOptionPane.ERROR_MESSAGE);
      } else {
        AbimottoDB.createUser(vorname, nachname, passwort);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }

  } // end of btnRegistrieren_ActionPerformed

  public void btnClear_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    jtAAusgabe.setText("");
  } // end of btnClear_ActionPerformed

  public void btnVorname_ActionPerformed(ActionEvent evt) {
    
    
  } // end of btnVorname_ActionPerformed

  // Ende Methoden
  public static void main(String[] args) {
    new AbimottoWahl("Mottoverwaltung");
  }
}
