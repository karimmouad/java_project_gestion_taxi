package appTransport;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class AdminDASHBOARDrefresh extends AdminDASHBOARD {

	/**
	 * 
	 */
	String a1;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	AdminDASHBOARD a = new AdminDASHBOARD();
	public static Date DATEFROM, DATETO;
	protected JTextField CITY2;
	public static String ville, datefrom, dateto;
	protected JTable table_1 = new JTable();
	/** titre : Le titre du graphique affiché en haut */
	public String titre;
	/** ordonnee : le nom de l'axe des ordonnées */
	public String ordonnee;
	/** abscisses : le nom de l'axe des abscisses */
	public String abscisse;
	/** valeurs : les valeurs à afficher, elles sont triées par séries et par catégories*/
	public List<Float> valeurs;
	/** series : la liste des séries */
	public List<String> series;
	/** categories : la liste des categories */
	public List<String> categories;
	/** legende : booleen vrai si on affiche la légende */
	public boolean legende;
	/** couleurFond : la couleur du fond */
	public Color couleurFond;
	/** couleurBarres : les couleurs appliquées aux barres */
	public Color[] couleursBarres = {Color.cyan.darker(), 
			Color.red, Color.green, Color.cyan, Color.magenta, 
			Color.yellow, Color.pink, Color.darkGray, Color.orange};
	public List<Float> donnees = new ArrayList<Float>();
	public List<String> l1 = new ArrayList<String>();
	public List<String> l2 = new ArrayList<String>();
	public static int i;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminDASHBOARDrefresh frame = new AdminDASHBOARDrefresh();
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
	public AdminDASHBOARDrefresh() {
		
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
		
        // BOUTON DASHBOARD
		JLabel dashboard = new JLabel("");
		dashboard.setIcon(new ImageIcon(AdminDASHBOARDrefresh.class.getResource("/IMG/dashboard.png")));
		dashboard.setBounds(725, 534, 205, 41);
		panel.add(dashboard);
		dashboard.addMouseListener(new MouseAdapter() { 
		    public void mouseClicked(MouseEvent e) 
		    {
		    	
		    	List<Float> donnees = new ArrayList<Float>();
				List<String> l1 = new ArrayList<String>();
				List<String> l2 = new ArrayList<String>();
				l2.add("0");
				l1.add("1");
				l1.add("2");
				l1.add("3");
				l1.add("4");
				l1.add("5");
				l1.add("6");
				l1.add("7");
				l1.add("8");
				l1.add("9");
				l1.add("10");
				l1.add("11");
				l1.add("12");
			
				
				donnees.add((float)getSomme(ville,"2021-01-01","2021-02-01"));
				donnees.add((float)getSomme(ville,"2021-02-01","2021-03-01"));
				donnees.add((float)getSomme(ville,"2021-03-01","2021-04-01"));
				donnees.add((float)getSomme(ville,"2021-04-01","2021-05-01"));
				donnees.add((float)getSomme(ville,"2021-05-01","2021-06-01"));
				donnees.add((float)getSomme(ville,"2021-06-01","2021-07-01"));
				donnees.add((float)getSomme(ville,"2021-07-01","2021-08-01"));
				donnees.add((float)getSomme(ville,"2021-08-01","2021-09-01"));
				donnees.add((float)getSomme(ville,"2021-09-01","2021-10-01"));
				donnees.add((float)getSomme(ville,"2021-10-01","2021-11-01"));
				donnees.add((float)getSomme(ville,"2021-11-01","2021-12-01"));
				donnees.add((float)getSomme(ville,"2021-12-01","2021-12-31"));
				
				donnees.add(200f);
		    	// "dashboard", "mois", "argent", donnees, Color.white, l2, l1, true
				JFrame f = new JFrame();
				f.setBounds(10,10,500,500);
				Graphe g = new Graphe("dashboard", "mois", "argent", donnees, Color.white, l2, l1, true);
				f.add(g);
				f.setVisible(true);
		    }
		});
		
		JLabel back = new JLabel("");
		back.setBounds(1026, 23, 67, 27);
		panel.add(back);
		back.addMouseListener(new MouseAdapter() { 
		    public void mouseClicked(MouseEvent e) 
		    {
				AdminDASHBOARD a = new AdminDASHBOARD();
				dispose();
				a.setVisible(true);
		    }
		});
		
		// STOCKAGE DES VARIABLES NECESSAIRE
		
		ville = AdminDASHBOARD.getCity();
		datefrom = AdminDASHBOARD.datefrom();
		dateto = AdminDASHBOARD.dateto();
		

		try 
		{
			DATEFROM = new SimpleDateFormat("yyyy-MM-dd").parse(datefrom);
			System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(DATEFROM));
			
			DATETO = new SimpleDateFormat("yyyy-MM-dd").parse(dateto);
			System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(DATETO));
		}
		catch (ParseException e1) 
		{
			e1.printStackTrace();
		}
		
		
		
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

			dateFrom = YEARFROM + "-" + MONTHFROM + "-" + "01";
			dateTo = YEARTO + "-" + MONTHTO + "-" + "01";
			
			AdminDASHBOARDrefresh b = new AdminDASHBOARDrefresh();
			dispose();
			b.setVisible(true);
			
	    }
        });	
		

		// TABLEAU
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(569, 78, 512, 427);
		panel.add(scrollPane);
        
		 table_1.setModel(new DefaultTableModel(
		        	new Object[][] {
		        	},
		        	new String[] {
		        		 "ID_TAXI", "Ville_Depart", "Ville_Arrivee", "Prix","Date"
		        	}
		        ));
		        DefaultTableModel model = new DefaultTableModel();
		        table_1.setModel(model);
		        scrollPane.setColumnHeaderView(table_1);
		        Table();
		        scrollPane.setViewportView(table_1);

        
        // FONT
		JLabel Font = new JLabel("");
		Font.setBackground(new Color(240, 240, 240));
		Font.setIcon(new ImageIcon(AdminDASHBOARDrefresh.class.getResource("/IMG/amdindasshboardrefresh.png")));
		Font.setBounds(0, 0, 1128, 630);
		panel.add(Font);
	}
	
    // TABLE FONCTION //
	public void Table() {
		if (ville == "alhoceima")
			{
				String sql = "SELECT ID_TAXI,Ville_Depart,Ville_Arrivee,Prix,Date FROM alhoceima WHERE Date BETWEEN '"
						+ new SimpleDateFormat("yyyy-MM-dd").format(DATEFROM) + "' AND '" + new SimpleDateFormat("yyyy-MM-dd").format(DATETO) + "'";				
				try {
						prepared=cnx.prepareStatement(sql);
						resultat=prepared.executeQuery();
					    table_1.setModel(DbUtils.resultSetToTableModel(resultat));	
					 }
				catch(SQLException e1)
					 {}
			}
		
		else if (ville == "imzouren")
			{
				String sql = "SELECT ID_TAXI,Ville_Depart,Ville_Arrivee,Prix,Date FROM imzouren WHERE Date BETWEEN '"
						+ new SimpleDateFormat("yyyy-MM-dd").format(DATEFROM) + "' AND '" + new SimpleDateFormat("yyyy-MM-dd").format(DATETO) + "'";		
			try {
					prepared=cnx.prepareStatement(sql);
					resultat=prepared.executeQuery();
				    table_1.setModel(DbUtils.resultSetToTableModel(resultat));
				 }
			catch(SQLException e1)
				    {}
			}

		else if (ville == "benibouayach")
			{
				String sql = "SELECT ID_TAXI,Ville_Depart,Ville_Arrivee,Prix,Date FROM benibouayach WHERE Date BETWEEN '"
						+ new SimpleDateFormat("yyyy-MM-dd").format(DATEFROM) + "' AND '" + new SimpleDateFormat("yyyy-MM-dd").format(DATETO) + "'";		
			try {
					prepared=cnx.prepareStatement(sql);
					resultat=prepared.executeQuery();
				    table_1.setModel(DbUtils.resultSetToTableModel(resultat));
				
				}
			catch(SQLException e1)
				    {}
			}
		else if (ville == "ajdir")
			{
				String sql = "SELECT ID_TAXI,Ville_Depart,Ville_Arrivee,Prix,Date FROM ajdir WHERE Date BETWEEN '"
						+ new SimpleDateFormat("yyyy-MM-dd").format(DATEFROM) + "' AND '" + new SimpleDateFormat("yyyy-MM-dd").format(DATETO) + "'";		
			try {
				prepared=cnx.prepareStatement(sql);
				resultat=prepared.executeQuery();
			    table_1.setModel(DbUtils.resultSetToTableModel(resultat));
			 	}
			catch(SQLException e1)
			    {}
			}
	}
	
	public static int getSomme(String ville, String DateDepart, String DateArrivee)
	{

			
		if (ville == "alhoceima")
		{
			try {
		        cnx = ConnexionMysql.ConnectDb();
		        PreparedStatement prepared;
			
				String check = "select sum(Prix) from alhoceima WHERE Date between'" + DateDepart + "' and  '" + DateArrivee + "'";
				prepared = cnx.prepareStatement(check);
				resultat = prepared.executeQuery(check);
				while (resultat.next()) 
				{
					i = resultat.getInt(1);
				}
				
				} 
				catch (SQLException ex) 
				{
				ex.printStackTrace();
				}
			return i;
		}
		
		else if (ville == "imzouren")
		{
			try {
		        cnx = ConnexionMysql.ConnectDb();
		        PreparedStatement prepared;
			
				String check = "select sum(Prix) from imzouren WHERE Date between'" + DateDepart + "' and  '" + DateArrivee + "'";
				prepared = cnx.prepareStatement(check);
				resultat = prepared.executeQuery(check);
				while (resultat.next()) {
					i = resultat.getInt(1);
				}
				
				} 
				catch (SQLException ex) 
				{
				ex.printStackTrace();
				}
			return i;		
		}

		else if (ville == "benibouayach")
		{
			try {
		        cnx = ConnexionMysql.ConnectDb();
		        PreparedStatement prepared;
			
				String check = "select sum(Prix) from benibouayach WHERE Date between'" + DateDepart + "' and  '" + DateArrivee + "'";
				prepared = cnx.prepareStatement(check);
				resultat = prepared.executeQuery(check);
				while (resultat.next()) {
					i = resultat.getInt(1);
				}
				
				} 
				catch (SQLException ex) 
				{
				ex.printStackTrace();
				}
			return i;
		}
		
		else if (ville == "ajdir")
		{
			try {
		        cnx = ConnexionMysql.ConnectDb();
		        PreparedStatement prepared;
			
				String check = "select sum(Prix) from ajdir WHERE Date between'" + DateDepart + "' and  '" + DateArrivee + "'";
				prepared = cnx.prepareStatement(check);
				resultat = prepared.executeQuery(check);
				while (resultat.next()) {
					i = resultat.getInt(1);
				}
				
				} 
				catch (SQLException ex) 
				{
				ex.printStackTrace();
				}
			return i;
		}
		return i;
		
	}
}