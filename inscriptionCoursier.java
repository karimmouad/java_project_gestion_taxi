package appTransport;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Panel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;

public class inscriptionCoursier extends JFrame {
	/**
	 * Variable
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Connection cnx = null;
	ResultSet resultat = null;
	PreparedStatement prepared = null;
	public JFrame frame = new JFrame();
	private JTextField prenom;
	private JTextField Nom;
	private JTextField email;
	private JTextField cin;
	private JPasswordField password;
	private JTextField phone;
	private JLabel Formulaire;

	// RUN	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try 
				{
					LogCoursier frame = new LogCoursier();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	
	// METHODE DE HASHAGE DU PASSWORD
	public static String hashPassword(String password)
	{
		try 
		{
			MessageDigest message = MessageDigest.getInstance("MD5");
			message.update(password.getBytes());
			byte[] resultByteArray = message.digest();
			StringBuilder sb = new StringBuilder();
			for (byte b : resultByteArray)
			{
				sb.append(String.format("%02x", b));
			}
		return sb.toString();
		}
		catch(NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		return "";
	}
	
	
	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public inscriptionCoursier() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 750);
		cnx = ConnexionMysql.ConnectDb();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Panel panel = new Panel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		// PRENOM CASE
		prenom = new JTextField();
		prenom.setBorder(null);
		prenom.setBounds(751, 215, 199, 20);
		panel.add(prenom);
		prenom.setColumns(10);
		
		// NOM CASE
		Nom = new JTextField();
		Nom.setBorder(null);
		Nom.setBounds(751, 163, 196, 20);
		panel.add(Nom);
		Nom.setColumns(10);
		
		// EMAIL CASE
		email = new JTextField();
		email.setBorder(null);
		email.setBounds(751, 266, 199, 20);
		panel.add(email);
		email.setColumns(10);
		
		// CIN CASE
		cin = new JTextField();
		cin.setBorder(null);
		cin.setBounds(751, 318, 199, 20);
		panel.add(cin);
		cin.setColumns(10);
		
		// PASSWORD CASE
		password = new JPasswordField();
		password.setBorder(null);
		password.setBounds(751, 368, 199, 20);
		panel.add(password);
		
		// PHONE CASE
		phone = new JTextField();
		phone.setBorder(null);
		phone.setBounds(751, 472, 202, 20);
		panel.add(phone);
		phone.setColumns(10);
		
		// VILLE CASE
		String[] ville = {"ALHOCEIMA", "AJDIR", "IMZOUREN", "BENIBOUAYACH"};
		JComboBox<?> comboBox = new JComboBox<Object>(ville);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"alhoceima", "ajdir", "imzouren", "benibouayach"}));
		comboBox.setFont(new Font("Calibri", Font.PLAIN, 11));
		comboBox.setBorder(new EmptyBorder(0, 0, 0, 0));
		comboBox.setBounds(751, 418, 199, 22);
		panel.add(comboBox);
		
		// BOUTTON SIGN UP
		JLabel signup = new JLabel("");
		signup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String CIN = cin.getText().toString();
				String NOM = Nom.getText().toString();
				String PRENOM = prenom.getText().toString();
				String EMAIL = email.getText().toString();
				@SuppressWarnings("deprecation")
				String PASSWORD = password.getText().toString();
				String PHONE = phone.getText().toString();
				@SuppressWarnings("unused")
				
				// LES DECLARATION SUIVANTE SONT POUR LA PAGE SUIVANTE // POUR FINALISER L INSCRIPTION 
				String pdp = "null";
				String cinrecto = "null";
				String cinverso = "null";
				String certificat = "null";
				if(CIN.equals("") || NOM.equals("")|| PRENOM.equals("")|| EMAIL.equals("")|| PASSWORD.equals("")|| PHONE.equals("")) 
				{
					JOptionPane.showMessageDialog(null , "Veillez remplir les champs vide !!");
				}

				try {
					String query = "INSERT INTO courtier (Nom, Prenom, CIN, Password, Ville, NumTel, email, PDP, CINRECTO, CINVERSO, CERTIFICAT) VALUES (?,?,?,?,?,?,?,pdp,cinrecto,cinverso,certificat)";
					
					    prepared = cnx.prepareStatement(query);
						prepared.setString(1, Nom.getText());
						prepared.setString(2, prenom.getText());
						prepared.setString(3, cin.getText());
						prepared.setString(4, hashPassword(password.getText()));
						prepared.setString(5, comboBox.getSelectedItem().toString());
						prepared.setString(6, phone.getText());
						prepared.setString(7, email.getText());
							
						prepared.execute();
						JOptionPane.showMessageDialog(null , "New User Add");
						dispose();
						FinalisationInsc obj = new FinalisationInsc();
						obj.setVisible(true);
						} 
						catch (SQLException e1) 
						{
							e1.printStackTrace();
						}
					}
		});
			
		
													// LABEL 
		
		signup.setFont(new Font("Calibri", Font.PLAIN, 11));
		signup.setIcon(new ImageIcon(inscriptionCoursier.class.getResource("/IMG/bouttonSign .png")));
		signup.setBounds(722, 523, 163, 56);
		panel.add(signup);
		
		Formulaire = new JLabel("");
		Formulaire.setIcon(new ImageIcon(inscriptionCoursier.class.getResource("/IMG/formulaire.png")));
		Formulaire.setBounds(618, 83, 344, 429);
		panel.add(Formulaire);
		JLabel labelLeft = new JLabel("");
		labelLeft.setBorder(null);
		labelLeft.setIcon(new ImageIcon(inscriptionCoursier.class.getResource("/IMG/leftblue.png")));
		labelLeft.setBounds(510, 11, 613, 632);
		panel.add(labelLeft);
		
		JLabel labelRight = new JLabel("");
		labelRight.setIcon(new ImageIcon(inscriptionCoursier.class.getResource("/IMG/cover.png")));
		labelRight.setBounds(10, 22, 588, 610);
		panel.add(labelRight);
	}
}