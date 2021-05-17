package appTransport;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AdminLogIn extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField CIN;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogIn frame = new AdminLogIn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminLogIn() {
		
		 // BOUTTON ET LABEL 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1150, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		Panel panel = new Panel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		

		
		
		// COURTIER BOUTTON
		JLabel courtier = new JLabel("");
		courtier.setBounds(209, 518, 114, 36);
		panel.add(courtier);
		
		// SIGN IN BOUTTON
		JLabel Boutton_Sign_In_1 = new JLabel("");
		Boutton_Sign_In_1.setBounds(762, 439, 142, 43);
		panel.add(Boutton_Sign_In_1);
		
		// CIN CASE
		CIN = new JTextField();
		CIN.setToolTipText("");
		CIN.setColumns(10);
		CIN.setBorder(null);
		CIN.setBounds(765, 293, 204, 20);
		panel.add(CIN);
				
		// PASSWORD CASE
		passwordField = new JPasswordField();
		passwordField.setBorder(null);
		passwordField.setBounds(764, 358, 204, 20);
		panel.add(passwordField);
		
		
		// SIGN IN BOUTTON
		//JLabel Boutton_Sign_In_1 = new JLabel("");
		Boutton_Sign_In_1.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void mouseClicked(MouseEvent e) {
				String Cin = CIN.getText().toString();
				@SuppressWarnings("deprecation")
				String password = passwordField.getText().toString();
						
				if(Cin.equals("") || password.equals("")) 
				{
					JOptionPane.showMessageDialog(null , "Veillez remplir les champs vide !!");
				}
				
				else
				{
					if(Cin.equals("admin") && password.equals("123"))
					{
						AccueilAdmin a = new AccueilAdmin();
						dispose();
						a.setVisible(true);
					}
				}
			}
		});
		
		
		// FONT
		JLabel Font = new JLabel("");
		Font.setIcon(new ImageIcon(AdminLogIn.class.getResource("/IMG/admin1.png")));
		Font.setBounds(0, 0, 1128, 630);
		panel.add(Font);
		}
}

