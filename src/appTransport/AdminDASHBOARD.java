package appTransport;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Panel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class AdminDASHBOARD extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static Connection cnx = null;
	PreparedStatement prepared = null;
	static ResultSet resultat = null;
	public String YEARTO, MONTHTO, YEARFROM, MONTHFROM;
	public static String city;
	public static String dateFrom;
	public static String dateTo;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminDASHBOARD frame = new AdminDASHBOARD();
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
	public AdminDASHBOARD(){
		
		
		// BOUTTON ET LABEL 
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
        
        //  +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
        // +++++++++++++++++++++++++++++++   choix de ville   ++++++++++++++++++++++++++++++++++ //
    	
        // IMZOUREN	
		JLabel cityImzouren = new JLabel("");
		cityImzouren.setBounds(68, 177, 162, 45);
		panel.add(cityImzouren);
		cityImzouren.addMouseListener(new MouseAdapter() { 
		    public void mouseClicked(MouseEvent e) 
		    {
				city = "imzouren";
		    }
		});
		
		// AJDIR
		JLabel cityAjdir = new JLabel("");
		cityAjdir.setBounds(302, 177, 162, 45);
		panel.add(cityAjdir);
		cityAjdir.addMouseListener(new MouseAdapter() { 
		    public void mouseClicked(MouseEvent e) 
		    {
				city = "ajdir";

		    }
		});
		
		// BENI BOUAYACH
		JLabel cityBenibouayach = new JLabel("");
		cityBenibouayach.setBounds(302, 116, 162, 45);
		panel.add(cityBenibouayach);
		cityBenibouayach.addMouseListener(new MouseAdapter() { 
		    public void mouseClicked(MouseEvent e) 
		    {
				city = "benibouayach";

		    }
		});
		
		// AL HOCEIMA 
		JLabel cityAlhoceima = new JLabel("");
		cityAlhoceima.setBounds(68, 117, 162, 45);
		panel.add(cityAlhoceima);
		cityAlhoceima.addMouseListener(new MouseAdapter() { 
		    public void mouseClicked(MouseEvent e) 
		    {
				city = "alhoceima";

		    }
		});
		
		// ++++++++++++++++++++++++++++++++++++++++++++++++ FROM +++++++++++++++++++++++++++++++++++++++++++++
		// month from
		JComboBox<String> MONTHfrom = new JComboBox<String>();
		MONTHfrom.setModel(new DefaultComboBoxModel<String>(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		MONTHfrom.setFont(new Font("Calibri", Font.BOLD, 13));
		MONTHfrom.setBorder(null);
		MONTHfrom.setBackground(Color.WHITE);
		MONTHfrom.setBounds(128, 313, 72, 19);
		panel.add(MONTHfrom);
		
			
		// year from
		JComboBox<String> YEARfrom = new JComboBox<String>();
		YEARfrom.setModel(new DefaultComboBoxModel<String>(new String[] {"2019", "2020", "2021", "2022", "2023", "2024", "2025"}));
		YEARfrom.setFont(new Font("Calibri", Font.BOLD, 13));
		YEARfrom.setBorder(null);
		YEARfrom.setBackground(Color.WHITE);
		YEARfrom.setBounds(126, 390, 72, 19);
		panel.add(YEARfrom);
		
		
		// +++++++++++++++++++++++++++++++++++++++++++ TOOOOO ++++++++++++++++++++++++++++++++++++++++++++++++//
		
		// year to

		JComboBox<String> YEARto = new JComboBox<String>();
		YEARto.setModel(new DefaultComboBoxModel<String>(new String[] {"2019", "2020", "2021", "2022", "2023"}));
		YEARto.setFont(new Font("Calibri", Font.BOLD, 13));
		YEARto.setBorder(null);
		YEARto.setBackground(Color.WHITE);
		YEARto.setBounds(370, 388, 72, 19);
		panel.add(YEARto);

		
		// month to
		
		JComboBox<String> MONTHto = new JComboBox<String>();
		MONTHto.setModel(new DefaultComboBoxModel<String>(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		MONTHto.setFont(new Font("Calibri", Font.BOLD, 13));
		MONTHto.setBorder(null);
		MONTHto.setBackground(Color.WHITE);
		MONTHto.setBounds(371, 313, 72, 19);
		panel.add(MONTHto);
		
		
		// refresh boutton
		
		JLabel refresh = new JLabel("");
		refresh.setBounds(204, 473, 134, 38);
		panel.add(refresh);
		refresh.addMouseListener(new MouseAdapter() { 
	    public void mouseClicked(MouseEvent e) 
	    {	    
			/** FROM STRING TO DATE */

			MONTHFROM = MONTHfrom.getSelectedItem().toString();
			YEARFROM = YEARfrom.getSelectedItem().toString();
			
			YEARTO = YEARto.getSelectedItem().toString();
			MONTHTO = MONTHto.getSelectedItem().toString();

			dateFrom =  YEARFROM + "-" + MONTHFROM + "-" + "01" ;
			dateTo = YEARTO + "-" + MONTHTO + "-" + "01";
			
			AdminDASHBOARDrefresh b = new AdminDASHBOARDrefresh();
			dispose();
			b.setVisible(true);
			
	    }
        });	
				
		// DASHBOARD CODE
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
		JLabel city1 = new JLabel("");
		city1.setBounds(871, 103, 187, 188);
		panel.add(city1);
		city1.addMouseListener(new MouseAdapter() {
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

		
		JLabel Font = new JLabel("");
		Font.setBackground(new Color(240, 240, 240));
		Font.setIcon(new ImageIcon(AdminDASHBOARD.class.getResource("/IMG/amdindasshboard.png")));
		Font.setBounds(0, 0, 1128, 630);
		panel.add(Font);
	}
	public void Table() {

	}
	
	public static String getCity()
	{
		return city;
	}
	
	public static String dateto ()
	{
		return dateTo;  		
	}

	public static String datefrom()
	{
		return dateFrom;  		
	}
}
