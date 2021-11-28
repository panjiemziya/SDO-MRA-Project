package mziya.panjie;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


public class GUI extends JFrame
  implements ActionListener, FocusListener, ChangeListener
{
  private int width = 1700;
  private int height = 1000;
  private String title = "MRA Project - Taxpayers Management System";
  private CardLayout cLayout = new CardLayout();
  private BorderLayout bLayout = null;
  private JPanel header = new JPanel();
  private JLabel headerLabel = new JLabel(this.title);
  private JPanel content = new JPanel();
  private Color Green = new Color(0, 100, 0);
  private Color lightGreen2 = new Color(50, 205, 50);
  private Color white = new Color(255, 255, 255);
  private Font centuryGothic = new Font("Century Gothic", 1, 33);
  private Font centuryGothicSmall = new Font("Century Gothic", 1, 15);
  private Font centuryGothicMedium = new Font("Century Gothic", 1, 25);

  private JPanel loginPanel = new JPanel();
  private JButton loginbutton = new JButton("Login");
  private JTextField username = new JTextField("Username/Email");
  private JPasswordField password = new JPasswordField("Password");

  private JPanel mainPanel = new JPanel();
  private JTabbedPane mainTab = new JTabbedPane();
  private JPanel add = new JPanel();
  private JPanel view = new JPanel();

  private JTextField tpin = new JTextField("Enter TPIN");
  private JTextField certificateNo = new JTextField("Enter Business Certificate No.");
  private JTextField tradingName = new JTextField("Enter Trading Name");
  private JTextField registrationDate = new JTextField("Enter Business Registration Date");
  private JTextField mobileNo = new JTextField("Enter Mobile Number");
  private JTextField email = new JTextField("Enter Email Address");
  private JTextField location = new JTextField("Enter Physical Location");
  private JButton addTaxpayer = new JButton("Add Taxpayer");
  private JButton editTaxpayer = new JButton("Edit Taxpayer");
  private JButton deleteTaxpayer = new JButton("Delete Taxpayer");
  private JLabel registrationLabel = new JLabel("Taxpayers Registration");
  private JLabel otherOperationsLabel = new JLabel("Other Operations");
  private JLabel loginLabel = new JLabel("Please Login to use the System");
  private JPanel adding = new JPanel();
  private JPanel otherOperations = new JPanel();

  private JPanel updatePanel = new JPanel();
  private JTextField currenttpin = new JTextField("Enter current TPIN");
  private JTextField newcertificateNo = new JTextField("Enter new Business Certificate No.");
  private JTextField newtradingName = new JTextField("Enter new Trading Name");
  private JTextField newregistrationDate = new JTextField("Enter new Business Registration Date");
  private JTextField newmobileNo = new JTextField("Enter new Mobile Number");
  private JTextField newemail = new JTextField("Enter new Email Address");
  private JTextField newlocation = new JTextField("Enter new Physical Location");
  private JButton updateTaxpayer = new JButton("Update Taxpayer");
  private JLabel updateLabel = new JLabel("Enter old details if no update is required in a field");
  private JFrame updateWindow = new JFrame();

  private Webservice webservice = new Webservice();

  private JTable table = new JTable();
  private DefaultTableModel tablemodel = new DefaultTableModel();
  private String[][] details;
  private JScrollPane tableScrollPane = new JScrollPane(this.table);
  private String[] taxpayerDetails = new String[7];
  private JTableHeader threader;
  private Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
  private Matcher matcher;

  public GUI()
  {
    setVisible(true);
    setSize(this.width, this.height);
    setTitle(this.title + " by Panjie Mziya");
    setResizable(false);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(3);
    setLayout(this.bLayout);
    add(this.header);
    add(this.content);
    getContentPane().setBackground(this.Green);
    this.content.setLayout(this.cLayout);
    this.content.setBounds(15, 70, 1665, 885);
    this.content.add("loginpanel", this.loginPanel);
    this.content.add("mainpanel", this.mainPanel);
    this.header.add(this.headerLabel);
    this.headerLabel.setFont(this.centuryGothic);
    this.header.setBounds(15, 10, 1665, 50);
    this.header.setBackground(this.lightGreen2);
    this.content.setBackground(this.white);
    this.loginPanel.setBackground(this.content.getBackground());
    this.mainPanel.setBackground(this.content.getBackground());
    this.loginPanel.setLayout(this.bLayout);
    this.loginPanel.add(this.loginbutton);
    this.loginPanel.add(this.username);
    this.loginPanel.add(this.password);
    this.loginbutton.setFont(this.centuryGothicSmall);
    this.loginPanel.add(this.loginLabel);
    this.loginLabel.setFont(this.centuryGothicMedium);
    this.loginLabel.setBounds(645, 100, 400, 50);
    this.loginLabel.setForeground(this.Green);
    this.username.setFont(this.centuryGothicSmall);
    this.username.setHorizontalAlignment(0);
    this.password.setHorizontalAlignment(0);
    this.username.setForeground(this.Green);
    this.password.setForeground(this.Green);
    this.password.setFont(this.centuryGothicSmall);
    this.username.setBounds(680, 300, 300, 50);
    this.password.setBounds(680, 400, 300, 50);
    this.loginbutton.setBounds(690, 500, 275, 50);
    this.loginbutton.setBackground(this.lightGreen2);
    this.loginbutton.setFocusable(false);
    this.loginbutton.addActionListener(this);
    this.username.addFocusListener(this);
    this.password.addFocusListener(this);
    this.mainTab.add("Add, Edit & Delete Taxpayers", this.add);
    this.mainTab.add("View Taxpayers", this.view);
    this.mainTab.add("Logout", null);
    this.mainPanel.add(this.mainTab);
    this.mainPanel.setLayout(this.cLayout);
    this.mainTab.setFont(this.centuryGothicSmall);
    this.add.setBackground(this.lightGreen2);
    this.view.setBackground(this.mainPanel.getBackground());
    this.mainTab.addChangeListener(this);
    this.mainTab.setBackground(this.white);
    this.mainTab.setForeground(this.Green);

    this.add.setLayout(this.bLayout);
    this.add.add(this.adding);
    this.add.add(this.otherOperations);
    this.adding.setBounds(10, 10, 815, 833);
    this.otherOperations.setBounds(835, 10, 815, 833);
    this.adding.setBackground(this.white);
    this.otherOperations.setBackground(this.white);
    this.otherOperations.setLayout(this.bLayout);
    this.adding.setLayout(this.bLayout);
    this.adding.add(this.tpin);
    this.tpin.setFont(this.centuryGothicSmall);
    this.tpin.setForeground(this.Green);
    this.tpin.setBounds(270, 70, 300, 50);
    this.tpin.setHorizontalAlignment(0);
    this.tpin.addFocusListener(this);

    this.adding.add(this.certificateNo);
    this.certificateNo.setFont(this.centuryGothicSmall);
    this.certificateNo.setForeground(this.Green);
    this.certificateNo.setBounds(270, 170, 300, 50);
    this.certificateNo.setHorizontalAlignment(0);
    this.certificateNo.addFocusListener(this);

    this.adding.add(this.tradingName);
    this.tradingName.setFont(this.centuryGothicSmall);
    this.tradingName.setForeground(this.Green);
    this.tradingName.setBounds(270, 270, 300, 50);
    this.tradingName.setHorizontalAlignment(0);
    this.tradingName.addFocusListener(this);

    this.adding.add(this.registrationDate);
    this.registrationDate.setFont(this.centuryGothicSmall);
    this.registrationDate.setForeground(this.Green);
    this.registrationDate.setBounds(270, 370, 300, 50);
    this.registrationDate.setHorizontalAlignment(0);
    this.registrationDate.addFocusListener(this);

    this.adding.add(this.mobileNo);
    this.mobileNo.setFont(this.centuryGothicSmall);
    this.mobileNo.setForeground(this.Green);
    this.mobileNo.setBounds(270, 470, 300, 50);
    this.mobileNo.setHorizontalAlignment(0);
    this.mobileNo.addFocusListener(this);

    this.adding.add(this.email);
    this.email.setFont(this.centuryGothicSmall);
    this.email.setForeground(this.Green);
    this.email.setBounds(270, 570, 300, 50);
    this.email.setHorizontalAlignment(0);
    this.email.addFocusListener(this);

    this.adding.add(this.location);
    this.location.setFont(this.centuryGothicSmall);
    this.location.setForeground(this.Green);
    this.location.setBounds(270, 670, 300, 50);
    this.location.setHorizontalAlignment(0);
    this.location.addFocusListener(this);

    this.adding.add(this.addTaxpayer);
    this.addTaxpayer.setFont(this.centuryGothicSmall);
    this.addTaxpayer.setForeground(this.Green);
    this.addTaxpayer.setBounds(280, 760, 280, 50);
    this.addTaxpayer.setBackground(this.lightGreen2);
    this.addTaxpayer.setForeground(this.white);
    this.addTaxpayer.addActionListener(this);
    this.addTaxpayer.setFocusable(false);

    this.adding.add(this.registrationLabel);
    this.registrationLabel.setFont(this.centuryGothicMedium);
    this.registrationLabel.setBounds(280, 0, 300, 50);

    this.otherOperations.add(this.otherOperationsLabel);
    this.otherOperationsLabel.setFont(this.centuryGothicMedium);
    this.otherOperationsLabel.setBounds(320, 0, 300, 50);

    this.otherOperations.add(this.editTaxpayer);
    this.editTaxpayer.setFont(this.centuryGothicSmall);
    this.editTaxpayer.setForeground(this.Green);
    this.editTaxpayer.setBounds(270, 270, 300, 50);
    this.editTaxpayer.setBackground(this.lightGreen2);
    this.editTaxpayer.setForeground(this.white);
    this.editTaxpayer.addActionListener(this);
    this.editTaxpayer.setFocusable(false);
    this.otherOperations.add(this.deleteTaxpayer);

    this.deleteTaxpayer.setFont(this.centuryGothicSmall);
    this.deleteTaxpayer.setForeground(this.Green);
    this.deleteTaxpayer.setBounds(270, 470, 300, 50);
    this.deleteTaxpayer.setBackground(this.lightGreen2);
    this.deleteTaxpayer.setForeground(this.white);
    this.deleteTaxpayer.addActionListener(this);
    this.deleteTaxpayer.setFocusable(false);

    this.tablemodel.addColumn("TPIN");
    this.tablemodel.addColumn("Business Certificate Date");
    this.tablemodel.addColumn("Trading Name");
    this.tablemodel.addColumn("Business Registration Date");
    this.tablemodel.addColumn("Mobile Number");
    this.tablemodel.addColumn("Email");
    this.tablemodel.addColumn("Location");
    this.table.setModel(this.tablemodel);
    this.view.add(this.tableScrollPane);
    this.table.setFont(this.centuryGothicSmall);
    this.view.setLayout(this.cLayout);
    this.table.setEnabled(false);
    this.view.setBackground(this.white);

    if (!this.webservice.checkConnection())
      JOptionPane.showMessageDialog(null, "Please make sure you have an internet Connection", "Error", 0);
  }

  public void updateWindow(boolean show, String title)
  {
    this.updateWindow.setTitle(title);
    this.updateWindow.setVisible(show);
    this.updateWindow.setSize(this.width - this.width * 70 / 100, this.height - this.height * 38 / 100);
    this.updateWindow.setLocationRelativeTo(null);
    this.updateWindow.getContentPane().setBackground(this.Green);
    this.updateWindow.setLayout(this.bLayout);
    this.updateWindow.add(this.updatePanel);
    this.updateWindow.setDefaultCloseOperation(2);
    this.updateWindow.setResizable(false);

    this.updatePanel.setBackground(this.white);
    this.updatePanel.setBounds(10, 10, 483, 571);
    this.updatePanel.setLayout(this.bLayout);

    this.updatePanel.add(this.updateLabel);
    this.updateLabel.setFont(this.centuryGothicSmall);
    this.updateLabel.setBounds(3, 0, 470, 35);
    this.updateLabel.setHorizontalAlignment(0);

    this.updatePanel.add(this.currenttpin);
    this.currenttpin.setFont(this.centuryGothicSmall);
    this.currenttpin.setForeground(this.Green);
    this.currenttpin.setBounds(90, 50, 300, 50);
    this.currenttpin.setHorizontalAlignment(0);
    this.currenttpin.addFocusListener(this);

    this.updatePanel.add(this.newcertificateNo);
    this.newcertificateNo.setFont(this.centuryGothicSmall);
    this.newcertificateNo.setForeground(this.Green);
    this.newcertificateNo.setBounds(90, 115, 300, 50);
    this.newcertificateNo.setHorizontalAlignment(0);
    this.newcertificateNo.addFocusListener(this);

    this.updatePanel.add(this.newtradingName);
    this.newtradingName.setFont(this.centuryGothicSmall);
    this.newtradingName.setForeground(this.Green);
    this.newtradingName.setBounds(90, 180, 300, 50);
    this.newtradingName.setHorizontalAlignment(0);
    this.newtradingName.addFocusListener(this);

    this.updatePanel.add(this.newregistrationDate);
    this.newregistrationDate.setFont(this.centuryGothicSmall);
    this.newregistrationDate.setForeground(this.Green);
    this.newregistrationDate.setBounds(90, 245, 300, 50);
    this.newregistrationDate.setHorizontalAlignment(0);
    this.newregistrationDate.addFocusListener(this);

    this.updatePanel.add(this.newmobileNo);
    this.newmobileNo.setFont(this.centuryGothicSmall);
    this.newmobileNo.setForeground(this.Green);
    this.newmobileNo.setBounds(90, 310, 300, 50);
    this.newmobileNo.setHorizontalAlignment(0);
    this.newmobileNo.addFocusListener(this);

    this.updatePanel.add(this.newemail);
    this.newemail.setFont(this.centuryGothicSmall);
    this.newemail.setForeground(this.Green);
    this.newemail.setBounds(90, 375, 300, 50);
    this.newemail.setHorizontalAlignment(0);
    this.newemail.addFocusListener(this);

    this.updatePanel.add(this.newlocation);
    this.newlocation.setFont(this.centuryGothicSmall);
    this.newlocation.setForeground(this.Green);
    this.newlocation.setBounds(90, 440, 300, 50);
    this.newlocation.setHorizontalAlignment(0);
    this.newlocation.addFocusListener(this);

    this.updatePanel.add(this.updateTaxpayer);
    this.updateTaxpayer.setFont(this.centuryGothicSmall);
    this.updateTaxpayer.setBackground(this.lightGreen2);
    this.updateTaxpayer.setForeground(this.white);
    this.updateTaxpayer.setBounds(100, 507, 280, 50);
    this.updateTaxpayer.setHorizontalAlignment(0);
    this.updateTaxpayer.addActionListener(this);
    this.updateTaxpayer.setFocusable(false);
    this.updateWindow.requestFocus();
  }

  public boolean verifyDate(String date) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    sdf.setLenient(false);
    boolean temp;
    try {
      sdf.parse(date);

      temp = true;
    }
    catch (java.text.ParseException e)
    {
	
      temp = false;
    }
    return temp;
  }

  public void stateChanged(ChangeEvent a)
  {
    if (this.mainTab.getSelectedIndex() == 2) {
      if (!this.webservice.checkConnection()) {
        JOptionPane.showMessageDialog(null, "Please make sure you have an internet Connection", "Error", 0);
        this.mainTab.setSelectedIndex(0);
      }
      else
      {
        try {
          this.webservice.logout();
          this.cLayout.show(this.content, "loginpanel");
        }
        catch (org.json.simple.parser.ParseException e) {
          e.printStackTrace();
        }

      }

    }

    if (this.mainTab.getSelectedIndex() == 1)
      if (!this.webservice.checkConnection()) {
        JOptionPane.showMessageDialog(null, "Please make sure you have an internet Connection", "Error", 0);
        this.mainTab.setSelectedIndex(0);
      }
      else
      {
        this.tablemodel.setRowCount(0);
        this.details = new String[10000][7];
        try
        {
          int i = 0;
          this.details = this.webservice.getTaxpayers();
          while (i < this.webservice.rows) {
            this.taxpayerDetails[0] = this.details[i][0].toString();
            this.taxpayerDetails[1] = this.details[i][1].toString();
            this.taxpayerDetails[2] = this.details[i][2].toString();
            this.taxpayerDetails[3] = this.details[i][3].toString();
            this.taxpayerDetails[4] = this.details[i][4].toString();
            this.taxpayerDetails[5] = this.details[i][5].toString();
            this.taxpayerDetails[6] = this.details[i][6].toString();
            this.tablemodel.insertRow(i, this.taxpayerDetails);
            i++;
          }
        }
        catch (org.json.simple.parser.ParseException e)
        {
          e.printStackTrace();
        }
      }
  }

  public void focusGained(FocusEvent a)
  {
    if (a.getSource() == this.username) {
      this.username.setText("");
    }

    if (a.getSource() == this.password) {
      this.password.setText("");
    }

    if (a.getSource() == this.tpin) {
      this.tpin.setText("");
    }
    if (a.getSource() == this.certificateNo) {
      this.certificateNo.setText("");
    }
    if (a.getSource() == this.tradingName) {
      this.tradingName.setText("");
    }
    if (a.getSource() == this.registrationDate) {
      this.registrationDate.setText("");
    }
    if (a.getSource() == this.mobileNo) {
      this.mobileNo.setText("");
    }
    if (a.getSource() == this.email) {
      this.email.setText("");
    }

    if (a.getSource() == this.location) {
      this.location.setText("");
    }

    if (a.getSource() == this.newcertificateNo) {
      this.newcertificateNo.setText("");
    }
    if (a.getSource() == this.newtradingName) {
      this.newtradingName.setText("");
    }
    if (a.getSource() == this.newregistrationDate) {
      this.newregistrationDate.setText("");
    }
    if (a.getSource() == this.newmobileNo) {
      this.newmobileNo.setText("");
    }
    if (a.getSource() == this.newemail) {
      this.newemail.setText("");
    }

    if (a.getSource() == this.newlocation) {
      this.newlocation.setText("");
    }

    if (a.getSource() == this.currenttpin)
      this.currenttpin.setText("");
  }

  public void focusLost(FocusEvent a)
  {
    if ((a.getSource() == this.username) && 
      (this.username.getText().isEmpty())) {
      this.username.setText("Username/Email");
    }

    if ((a.getSource() == this.password) && 
      (this.password.getText().isEmpty())) {
      this.password.setText("Password");
    }

    if ((a.getSource() == this.tpin) && 
      (this.tpin.getText().isEmpty())) {
      this.tpin.setText("Enter TPIN");
    }

    if ((a.getSource() == this.certificateNo) && 
      (this.certificateNo.getText().isEmpty())) {
      this.certificateNo.setText("Enter Business Certificate No.");
    }

    if ((a.getSource() == this.tradingName) && 
      (this.tradingName.getText().isEmpty())) {
      this.tradingName.setText("Enter Trading Name");
    }

    if ((a.getSource() == this.registrationDate) && 
      (this.registrationDate.getText().isEmpty())) {
      this.registrationDate.setText("Enter Business Registration Date");
    }

    if ((a.getSource() == this.mobileNo) && 
      (this.mobileNo.getText().isEmpty())) {
      this.mobileNo.setText("Enter Mobile No.");
    }

    if ((a.getSource() == this.email) && 
      (this.email.getText().isEmpty())) {
      this.email.setText("Enter Email Address");
    }

    if ((a.getSource() == this.location) && 
      (this.location.getText().isEmpty())) {
      this.location.setText("Enter Physical Location");
    }

    if ((a.getSource() == this.newcertificateNo) && 
      (this.newcertificateNo.getText().isEmpty())) {
      this.newcertificateNo.setText("Enter new Business Certificate No.");
    }

    if ((a.getSource() == this.newtradingName) && 
      (this.newtradingName.getText().isEmpty())) {
      this.newtradingName.setText("Enter new Trading Name");
    }

    if ((a.getSource() == this.newregistrationDate) && 
      (this.newregistrationDate.getText().isEmpty())) {
      this.newregistrationDate.setText("Enter new Business Registration Date");
    }

    if ((a.getSource() == this.newmobileNo) && 
      (this.newmobileNo.getText().isEmpty())) {
      this.newmobileNo.setText("Enter new Mobile No.");
    }

    if ((a.getSource() == this.newemail) && 
      (this.newemail.getText().isEmpty())) {
      this.newemail.setText("Enter new Email Address");
    }

    if ((a.getSource() == this.newlocation) && 
      (this.newlocation.getText().isEmpty())) {
      this.newlocation.setText("Enter new Physical Location");
    }

    if ((a.getSource() == this.currenttpin) && 
      (this.currenttpin.getText().isEmpty()))
      this.currenttpin.setText("Enter current TPIN");
  }

  public void actionPerformed(ActionEvent a)
  {
    if (a.getSource() == this.loginbutton) {
      requestFocus(true);

      if (!this.webservice.checkConnection()) {
        JOptionPane.showMessageDialog(null, "Please make sure you have an internet Connection", "Error", 0);
      }
      else
      {
        try
        {
          if (this.webservice.login(this.username.getText(), this.password.getText())) {
            this.cLayout.show(this.content, "mainpanel");
            this.username.setText("Username/Email");
            this.password.setText("Password");
            this.mainTab.setSelectedIndex(0);
            this.webservice.getTaxpayers();
          }
          else
          {
            JOptionPane.showMessageDialog(null, "User with that Username/Email and password does not exist in the system", "Error", 0);
          }
        }
        catch (HeadlessException|org.json.simple.parser.ParseException e) {
          e.printStackTrace();
        }

      }

    }

    if (a.getSource() == this.addTaxpayer) {
      requestFocus(true);
      this.matcher = this.pattern.matcher(this.email.getText());
      if (!this.webservice.checkConnection()) {
        JOptionPane.showMessageDialog(null, "Please make sure you have an internet Connection", "Error", 0);
        this.mainTab.setSelectedIndex(0);
      }
      else if ((!this.tpin.getText().matches("[0-9]*")) || (this.tpin.getText().trim().isEmpty())) {
        JOptionPane.showMessageDialog(null, "Please enter a valid TPIN", "Error", 0);
      }
      else if ((!this.certificateNo.getText().matches("[A-Z0-9]*")) || (this.certificateNo.getText().trim().isEmpty()) || (this.certificateNo.getText().contains("Enter Business Certificate No."))) {
        JOptionPane.showMessageDialog(null, "Please enter a valid Certificate Number", "Error", 0);
      }
      else if ((!this.tradingName.getText().matches("[a-zA-Z0-9 +]*")) || (this.tradingName.getText().trim().isEmpty()) || (this.tradingName.getText().contains("Enter Trading Name"))) {
        JOptionPane.showMessageDialog(null, "Please enter a valid Trading Name", "Error", 0);
      }
      else if ((this.registrationDate.getText().trim().isEmpty()) || (!verifyDate(this.registrationDate.getText()))) {
        JOptionPane.showMessageDialog(null, "Please enter a valid Business Registration Date", "Error", 0);
      }
      else if ((!this.mobileNo.getText().matches("[0-9]*")) || (this.mobileNo.getText().trim().isEmpty())) {
        JOptionPane.showMessageDialog(null, "Please enter a valid Mobile Number", "Error", 0);
      }
      else if ((this.email.getText().trim().isEmpty()) || (this.email.getText().contains("Enter Email Address")) || (!this.matcher.matches())) {
        JOptionPane.showMessageDialog(null, "Please enter a valid Email Address", "Error", 0);
      }
      else if ((!this.location.getText().matches("[a-zA-Z0-9 +]*")) || (this.location.getText().trim().isEmpty()) || (this.location.getText().contains("Enter Physical Location"))) {
        JOptionPane.showMessageDialog(null, "Please enter a valid Physical Location", "Error", 0);
      }
      else
      {
        try {
          if (!this.webservice.addTaxpayer(this.tpin.getText(), this.certificateNo.getText(), this.tradingName.getText(), this.registrationDate.getText(), this.mobileNo.getText(), this.email.getText(), this.location.getText())) {
            JOptionPane.showMessageDialog(null, "A Taxpayer with TPIN \"" + this.tpin.getText() + "\" already exists in the system. Please enter a different TPIN", "Error", 0);

            this.tpin.setText("Enter TPIN");

            this.certificateNo.setText("Enter Business Certificate No.");

            this.tradingName.setText("Enter Trading Name");
            this.registrationDate.setText("Enter Business Registration Date");

            this.mobileNo.setText("Enter Mobile No.");

            this.email.setText("Enter Email Address");

            this.location.setText("Enter Physical Location");

            this.newcertificateNo.setText("Enter new Business Certificate No.");

            this.newtradingName.setText("Enter new Trading Name");

            this.newregistrationDate.setText("Enter new Business Registration Date");

            this.newmobileNo.setText("Enter new Mobile No.");

            this.newemail.setText("Enter new Email Address");

            this.newlocation.setText("Enter new Physical Location");

            this.currenttpin.setText("Enter current TPIN");
          }
          else
          {
            JOptionPane.showMessageDialog(null, "Taxpayer with TPIN \"" + this.tpin.getText() + "\" has been added successfully", "Taxpayer Added", 1);

            this.tpin.setText("Enter TPIN");

            this.certificateNo.setText("Enter Business Certificate No.");

            this.tradingName.setText("Enter Trading Name");
            this.registrationDate.setText("Enter Business Registration Date");

            this.mobileNo.setText("Enter Mobile No.");

            this.email.setText("Enter Email Address");

            this.location.setText("Enter Physical Location");

            this.newcertificateNo.setText("Enter new Business Certificate No.");

            this.newtradingName.setText("Enter new Trading Name");

            this.newregistrationDate.setText("Enter new Business Registration Date");

            this.newmobileNo.setText("Enter new Mobile No.");

            this.newemail.setText("Enter new Email Address");

            this.newlocation.setText("Enter new Physical Location");

            this.currenttpin.setText("Enter current TPIN");
          }

        }
        catch (org.json.simple.parser.ParseException e)
        {
          e.printStackTrace();
        }
      }
    }

    if (a.getSource() == this.deleteTaxpayer) {
      if (!this.webservice.checkConnection()) {
        JOptionPane.showMessageDialog(null, "Please make sure you have an internet Connection", "Error", 0);
        this.mainTab.setSelectedIndex(0);
      }
      else
      {
        requestFocus(true);
        String temp = JOptionPane.showInputDialog("Please enter the TPIN for the Taxpayer you would like to delete");
        if (temp != null) {
          if (!temp.matches("[0-9]*")) {
            JOptionPane.showMessageDialog(null, "Please enter a correct TPIN", "Error", 0);
          }
          else
          {
            try
            {
              if (!this.webservice.checkTpin(temp)) {
                JOptionPane.showMessageDialog(null, "Taxpayer with TPIN \"" + temp + "\" does not exist in the System.", "Error", 0);
              }
              else if (this.webservice.deleteTaxpayer(temp))
              {
                JOptionPane.showMessageDialog(null, "Taxpayer deleted successfully", "Deleted", 1);
              }
            }
            catch (org.json.simple.parser.ParseException e)
            {
              e.printStackTrace();
            }
          }
        }
      }

    }

    if (a.getSource() == this.editTaxpayer) {
      updateWindow(true, "Updating Taxpayers");
      this.updateWindow.requestFocus();

      this.tpin.setText("Enter TPIN");

      this.certificateNo.setText("Enter Business Certificate No.");

      this.tradingName.setText("Enter Trading Name");
      this.registrationDate.setText("Enter Business Registration Date");

      this.mobileNo.setText("Enter Mobile No.");

      this.email.setText("Enter Email Address");

      this.location.setText("Enter Physical Location");

      this.newcertificateNo.setText("Enter new Business Certificate No.");

      this.newtradingName.setText("Enter new Trading Name");

      this.newregistrationDate.setText("Enter new Business Registration Date");

      this.newmobileNo.setText("Enter new Mobile No.");

      this.newemail.setText("Enter new Email Address");

      this.newlocation.setText("Enter new Physical Location");

      this.currenttpin.setText("Enter current TPIN");
    }

    if (a.getSource() == this.updateTaxpayer) {
      this.updateWindow.requestFocus();
      this.matcher = this.pattern.matcher(this.newemail.getText());

      if (!this.webservice.checkConnection()) {
        JOptionPane.showMessageDialog(null, "Please make sure you have an internet Connection", "Error", 0);
        this.mainTab.setSelectedIndex(0);
      }
      else if ((!this.currenttpin.getText().matches("[0-9]*")) || (this.currenttpin.getText().trim().isEmpty())) {
        JOptionPane.showMessageDialog(null, "Please make sure the current TPIN entered is valid", "Error", 0);
      }
      else if ((!this.newcertificateNo.getText().matches("[A-Z0-9]*")) || (this.newcertificateNo.getText().trim().isEmpty()) || (this.newcertificateNo.getText().contains("Enter Business Certificate No."))) {
        JOptionPane.showMessageDialog(null, "Please enter a valid Certificate Number", "Error", 0);
      }
      else if ((!this.newtradingName.getText().matches("[a-zA-Z0-9 +]*")) || (this.newtradingName.getText().trim().isEmpty()) || (this.newtradingName.getText().contains("Enter Trading Name"))) {
        JOptionPane.showMessageDialog(null, "Please enter a valid Trading Name", "Error", 0);
      }
      else if ((this.newregistrationDate.getText().trim().isEmpty()) || (!verifyDate(this.newregistrationDate.getText()))) {
        JOptionPane.showMessageDialog(null, "Please enter a valid Business Registration Date", "Error", 0);
      }
      else if ((!this.newmobileNo.getText().matches("[0-9]*")) || (this.newmobileNo.getText().trim().isEmpty())) {
        JOptionPane.showMessageDialog(null, "Please enter a valid Mobile Number", "Error", 0);
      }
      else if ((this.newemail.getText().trim().isEmpty()) || (this.newemail.getText().contains("Enter Email Address")) || (!this.matcher.matches())) {
        JOptionPane.showMessageDialog(null, "Please enter a valid Email Address", "Error", 0);
      }
      else if ((!this.newlocation.getText().matches("[a-zA-Z0-9 +]*")) || (this.newlocation.getText().trim().isEmpty()) || (this.newlocation.getText().contains("Enter Physical Location"))) {
        JOptionPane.showMessageDialog(null, "Please enter a valid Physical Location", "Error", 0);
      }
      else
      {
        try
        {
          if (!this.webservice.updateTaxpayer(this.currenttpin.getText(), this.newcertificateNo.getText(), this.newtradingName.getText(), this.newregistrationDate.getText(), this.newmobileNo.getText(), this.newemail.getText(), this.newlocation.getText())) {
            JOptionPane.showMessageDialog(null, "A Taxpayer with TPIN \"" + this.tpin.getText() + "\" does not exists in the system. Please try with a different TPIN", "Error", 0);
          }
          else
          {
            JOptionPane.showMessageDialog(null, "Details for Taxpayer with TPIN \"" + this.tpin.getText() + "\" have been successfully updated", "Taxpayer Added", 1);
            if ((a.getSource() == this.password) && 
              (this.password.getText().isEmpty())) {
              this.password.setText("Password");
            }

          }

        }
        catch (HeadlessException|org.json.simple.parser.ParseException e)
        {
          e.printStackTrace();
        }
      }
    }
  }
}

