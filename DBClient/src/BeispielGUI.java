import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 28.05.2018
  * @author Benjamin Reichelt
  */
import javax.swing.*;
import javax.swing.event.*;


public class BeispielGUI extends JFrame {
  // Anfang Attribute
  private JLabel jLabel12 = new JLabel();
  private JButton btnSenden = new JButton();
  private JTextField txtSQLBefehl = new JTextField();
  private JLabel jLabel1 = new JLabel();
  private JButton btnTest = new JButton();
  private JTextArea jtAAusgabe = new JTextArea("");
  private JScrollPane jtAAusgabeScrollPane = new JScrollPane(jtAAusgabe);

  // Ende Attribute
  public BeispielGUI(String title) {
    // Frame-Initialisierung
    super(title);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    int frameWidth = 517;
    int frameHeight = 566;
    setSize(frameWidth, frameHeight);

    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setResizable(false);

    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    jLabel12.setBounds(0, 8, 380, 40);
    jLabel12.setText("SQL-GUI");
    jLabel12.setFont(new Font("Calibri", Font.BOLD, 36));
    jLabel12.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(jLabel12);
    btnSenden.setBounds(384, 80, 113, 33);
    btnSenden.setText("Senden");
    btnSenden.setMargin(new Insets(2, 2, 2, 2));
    btnSenden.addActionListener(this::btnSenden_ActionPerformed);
    btnSenden.setEnabled(true);
    cp.add(btnSenden);
    txtSQLBefehl.setBounds(8, 80, 369, 33);
    txtSQLBefehl.setText("SELECT * FROM user");
    cp.add(txtSQLBefehl);
    jLabel1.setBounds(8, 48, 107, 25);
    jLabel1.setText("SQL-Befehl:");
    cp.add(jLabel1);
    btnTest.setBounds(384, 40, 113, 33);
    btnTest.setText("Test");
    btnTest.setMargin(new Insets(2, 2, 2, 2));
    btnTest.addActionListener(this::btnTest_ActionPerformed);
    cp.add(btnTest);
    jtAAusgabeScrollPane.setBounds(8, 120, 481, 401);
    cp.add(jtAAusgabeScrollPane);
    // Ende Komponenten
    setVisible(true);

    // DB Setup
    try {
      DB.setup("127.0.0.1", 3306, "abimotto",
        "root", "");
      jtAAusgabe.setText("Erfolgreich verbunden!\n\n");
    } catch (SQLException e) {
      jtAAusgabe.setText("Couldn't connect.");
    }
  } // end of public BeispielGUI

  // Anfang Methoden
  public static void main(String[] args) {
    new BeispielGUI("BeispielGUI");
  } // end of main

  public void btnSenden_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einf�gen
    String query = txtSQLBefehl.getText();
    query(query);
  } // end of btnSenden_ActionPerformed

  public void btnTest_ActionPerformed(ActionEvent evt) {
    jtAAusgabe.setText("");
    query("SELECT * FROM user");
  } // end of btnTest_ActionPerformed

  private void query(String statement) {
    try {
      String result = DB.queryAndFormat(statement);
      jtAAusgabe.setText(result);
    } catch (SQLException e) {
      jtAAusgabe.setText(e.getMessage());
    }
  }
  // Ende Methoden
} // end of class BeispielGUI
