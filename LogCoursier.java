package appTransport;

import java.awt.BorderLayout;


import java.sql.PreparedStatement;
import java.awt.EventQueue;
import java.awt.Image;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LogCoursier extends JFrame {
	/**
	 *  DECLARATION DES VARIABLES
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Connection cnx = null;
	ResultSet resultat = null;
	PreparedStatement prepared = null;
	public JFrame frame = new JFrame();
	protected JTextField cin;
	private JPasswordField Password;
	public String b,c;
	public String d;
	public static String para;

	
	
	
	// HASHAGE DE PASSWORD //
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
	

	// RUN
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogCoursier frame = new LogCoursier();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	public LogCoursier() {
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
		
		// CIN CASE
		cin = new JTextField();
		cin.setToolTipText("");
		cin.setBorder(null);
		cin.setColumns(10);
		cin.setBounds(726, 294, 204, 20);
		panel.add(cin);
		
		// PASSWORD CASE
		Password = new JPasswordField();
		Password.setBorder(null);
		Password.setBounds(726, 353, 204, 20);
		panel.add(Password);
		
		// FORGET PASSWORD CASE
		JLabel forget_password = new JLabel("");
		forget_password.setIcon(new ImageIcon(LogCoursier.class.getResource("/IMG/forget_pass.png")));
		forget_password.setBounds(801, 384, 129, 14);
		panel.add(forget_password);
		
		// SIGN IN BOUTTON
		JLabel Boutton_Sign_In = new JLabel("");
		Boutton_Sign_In.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void mouseClicked(MouseEvent e) {
				String Cin = cin.getText().toString();
				@SuppressWarnings("deprecation")
				String password = Password.getText().toString();
						
				if(Cin.equals("") || password.equals("")) 
				{
					JOptionPane.showMessageDialog(null , "Veillez remplir les champs vide !!");
				}

				String sql = "SELECT * FROM courtier WHERE CIN=? AND Password=?";
				try {
					
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1,Cin);
					prepared.setString(2,hashPassword(password));
					resultat = prepared.executeQuery();
					//UTILISATION DE CIN 
					para = Cin;	
				     if(resultat.next()) 
				     {
				    	 	b = resultat.getString("Ville");
							c = resultat.getString("Nom");		
				    	 	accueil a = new accueil();
							dispose();
							a.setVisible(true);
							// ENVOYER LES DONNEES A L ACCUEIL
							a.textField.setText(c);
							a.textField_1.setText(b);
							a.ville_Depart.setText(b);
				     }
				     else 
				     {
							JOptionPane.showMessageDialog(null , "connexion echouee");
				     }
				     }catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		
		
	   // BOUTTON ET LABEL 
		Boutton_Sign_In.setIcon(new ImageIcon(LogCoursier.class.getResource("/IMG/BouttonSignIn.png")));
		Boutton_Sign_In.setBounds(736, 462, 142, 50);
		panel.add(Boutton_Sign_In);
		
		JLabel password_log_in = new JLabel("");
		password_log_in.setIcon(new ImageIcon(LogCoursier.class.getResource("/IMG/password.png")));
		password_log_in.setBounds(648, 338, 303, 50);
		panel.add(password_log_in);
		
		JLabel cin_log_in = new JLabel("");
		cin_log_in.setIcon(new ImageIcon(LogCoursier.class.getResource("/IMG/cin.png")));
		cin_log_in.setBounds(648, 280, 303, 47);
		panel.add(cin_log_in);
		
		JLabel sign_in = new JLabel("");
		sign_in.setIcon(new ImageIcon(LogCoursier.class.getResource("/IMG/sign in.png")));
		sign_in.setBounds(965, 35, 129, 30);
		panel.add(sign_in);
		
		JLabel sign_up = new JLabel("");
		sign_up.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				inscriptionCoursier obj = new inscriptionCoursier();
				obj.setVisible(true);
			}
		});
		sign_up.setIcon(new ImageIcon(LogCoursier.class.getResource("/IMG/Sign up.png")));
		sign_up.setBounds(868, 35, 123, 30);
		panel.add(sign_up);
		
		JLabel left = new JLabel("");
		left.setBorder(null);
		left.setIcon(new ImageIcon(LogCoursier.class.getResource("/IMG/leftblue.png")));
		left.setBounds(510, 11, 613, 632);
		panel.add(left);
		
		JLabel right = new JLabel("");
		right.setIcon(new ImageIcon(LogCoursier.class.getResource("/IMG/cover.png")));
		right.setBounds(10, 22, 588, 610);
		panel.add(right);
	}
	
// ON AURA BESOIN DE CETTE FONCTION	
public String getCIN()
{
	return para;
}
}
