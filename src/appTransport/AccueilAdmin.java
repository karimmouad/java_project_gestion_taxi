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
import javax.swing.border.EmptyBorder;

public class AccueilAdmin extends AdminLogIn {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
	public AccueilAdmin() {		 
		
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

	/* 	++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++	*/
	
		
		// TAXI CASE
		JLabel taxi = new JLabel("");
		taxi.setBounds(604, 100, 188, 193);
		panel.add(taxi);
		taxi.addMouseListener(new MouseAdapter() {
		@SuppressWarnings("static-access")
		@Override
		public void mouseClicked(MouseEvent e) {
						AdminTAXI a = new AdminTAXI();
						dispose();
						a.setVisible(true);
		}
		});

		
		// CITY CASE
		JLabel city = new JLabel("");
		city.setBounds(871, 103, 187, 188);
		panel.add(city);
		city.addMouseListener(new MouseAdapter() {
		@SuppressWarnings("static-access")
		@Override
		public void mouseClicked(MouseEvent e) {
						AdminCITY a = new AdminCITY();
						dispose();
						a.setVisible(true);
		}
		});

		
		// DASHBOARD CASE
		JLabel dashboard = new JLabel("");
		dashboard.setBounds(604, 346, 188, 193);
		panel.add(dashboard);
		dashboard.addMouseListener(new MouseAdapter() {
		@SuppressWarnings("static-access")
		@Override
		public void mouseClicked(MouseEvent e) {
						AdminDASHBOARD a = new AdminDASHBOARD();
						dispose();
						a.setVisible(true);
		}
		});

		
		// COURTIER CASE
		JLabel courtier = new JLabel("");
		courtier.setBounds(870, 346, 188, 193);
		panel.add(courtier);
		courtier.addMouseListener(new MouseAdapter() {
		@SuppressWarnings("static-access")
		@Override
		public void mouseClicked(MouseEvent e) {
						AdminCOURTIER a = new AdminCOURTIER();
						dispose();
						a.setVisible(true);
		}
		});

	/* 	
		++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++	*/
	
		
		// FONT
		JLabel Font = new JLabel("");
		Font.setIcon(new ImageIcon(AccueilAdmin.class.getResource("/IMG/amdin2.png")));
		Font.setBounds(0, 0, 1128, 630);
		panel.add(Font);
		
	}

}
