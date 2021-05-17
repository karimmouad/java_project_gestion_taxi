package appTransport;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

public class AdminCITY extends AccueilAdmin {

	private JPanel contentPane;
	private JTextField add_city;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminCITY frame = new AdminCITY();
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
	public AdminCITY() {
		
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
		
		// ADD CITY TEXTFIELD
		add_city = new JTextField();
		add_city.setBorder(null);
		add_city.setBounds(81, 503, 271, 29);
		panel.add(add_city);
		add_city.setColumns(10);
		
		// ADD BOUTTON 
		JLabel add = new JLabel("");
		add.setBounds(372, 503, 78, 29);
		panel.add(add);				
		
		// AL HOCEIMA CASE
		JLabel al_hoceima = new JLabel("");
		al_hoceima.setBounds(121, 137, 277, 41);
		panel.add(al_hoceima);
		
		// AJDIR CASE
		JLabel ajdir = new JLabel("");
		ajdir.setBounds(121, 231, 277, 41);
		panel.add(ajdir);
		
		// IMZOUREN CASE
		JLabel imzouren = new JLabel("");
		imzouren.setBounds(121, 321, 277, 36);
		panel.add(imzouren);
		
		// BENI BOUAYACH CASE
		JLabel beni_bouayash = new JLabel("");
		beni_bouayash.setBounds(121, 414, 277, 36);
		panel.add(beni_bouayash);
		
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

		/* 	++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++	*/
		
		
		
		// FONT 
		JLabel Font = new JLabel("");
		Font.setIcon(new ImageIcon(AdminCITY.class.getResource("/IMG/amdin2City.png")));
		Font.setBounds(0, 0, 1128, 630);
		panel.add(Font);
	}
}
