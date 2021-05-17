package appTransport;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AdminTAXI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField id_taxi;
	private JTextField permis;
	private JTextField cin;
	private JTextField matricule;
	Connection cnx = null;
	ResultSet resultat = null;
	PreparedStatement prepared = null;
	protected JTable table_1 = new JTable();
	private JTextField SEARCH;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminTAXI frame = new AdminTAXI();
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
	public AdminTAXI() {
		
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
		
		// ++++++++++++++++++  TEXT FIELD  ++++++++++++++++++++ //
		
		// ID TAXI CASE
		id_taxi = new JTextField();
        id_taxi.setBackground(new Color(90, 191, 173));
		id_taxi.setBorder(null);
		id_taxi.setBounds(167, 471, 188, 19);
		panel.add(id_taxi);
		id_taxi.setColumns(10);
		
		// MATRICULE CASE
		matricule = new JTextField();
		matricule.setBackground(new Color(90, 191, 173));
		matricule.setBorder(null);
		matricule.setColumns(10);
		matricule.setBounds(167, 503, 188, 19);
		panel.add(matricule);
		
		// CIN CASE
		cin = new JTextField();
		cin.setBackground(new Color(90, 191, 173));
		cin.setBorder(null);
		cin.setColumns(10);
		cin.setBounds(167, 533, 188, 19);
		panel.add(cin);
		
		// PERMIS CASE
		permis = new JTextField();
		permis.setBackground(new Color(90, 191, 173));
		permis.setBorder(null);
		permis.setColumns(10);
		permis.setBounds(167, 565, 188, 19);
		panel.add(permis);
		
		
		// connection data base
		cnx = ConnexionMysql.ConnectDb();
		
		// BOUTON ADD DATA BASE
		JLabel addd = new JLabel("");
		addd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String IDTAXI = id_taxi.getText().toString();
				String CIN = cin.getText().toString();
				String PERMIS = permis.getText().toString();
				String MATRICULE = matricule.getText().toString();
				
				if(CIN.equals("") || IDTAXI.equals("")|| PERMIS.equals("")|| MATRICULE.equals("")) 
				{
					JOptionPane.showMessageDialog(null , "Veillez remplir les champs vide !!");
				}

				try {
					String query = "INSERT INTO taxi (ID_TAXI, CIN, MATRICULE, PERMIS) VALUES (?,?,?,?)";
					
					    prepared = cnx.prepareStatement(query);
						prepared.setString(1, IDTAXI);
						prepared.setString(2, CIN);
						prepared.setString(3, MATRICULE);
						prepared.setString(4, PERMIS);
						
						prepared.execute();
						JOptionPane.showMessageDialog(null , "New TAXI Add");
						dispose();
						AdminTAXI obj = new AdminTAXI();
						obj.setVisible(true);
						} 
						catch (SQLException e1) 
						{
							e1.printStackTrace();
						}
					}
		});
		addd.setBounds(398, 471, 65, 29);
		panel.add(addd);

		
		

		// BOUTON DELETE DATA BASE
		JLabel delete = new JLabel("");
		delete.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) 
            {
            	int i =table_1.getSelectedRow();
		  		if(i>=0) 
		  		{
		  			String idTAXI=table_1.getModel().getValueAt(i,0).toString();
		  			String sq="DELETE FROM taxi where ID_TAXI="+idTAXI;
		  			try 
		  			{
		  				prepared=cnx.prepareStatement(sq);
		  				prepared.execute();
		  				JOptionPane.showMessageDialog(null,"ligne supprimée");
		  				Table();
		  			}
		  			catch(Exception e1) 
		  			{
		  				JOptionPane.showConfirmDialog(null, e1);
		  			}
        		}
		  			else 
		  			{
		  				JOptionPane.showConfirmDialog(null, "selectionner une ligne");
		  			}
			}
		});
		delete.setBounds(401, 552, 65, 29);
		panel.add(delete);
		
		// BOUTON MODIFY DATA BASE
		JLabel modify = new JLabel("");
		modify.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) 
            {
				int i= table_1.getSelectedRow();
        		String s=" UPDATE taxi SET ID_Taxi = ?, CIN = ?, MATRICULE = ?, PERMIS = ? WHERE ID_TAXI = ? AND CIN = ?";	
	     		try 
		  		{
	     			
		  			prepared=cnx.prepareStatement(s);	
		  			prepared.setString(1,id_taxi.getText().toString());
		  			prepared.setString(2,cin.getText().toString());
		  			prepared.setString(3,matricule.getText().toString());
		  			prepared.setString(4,permis.getText().toString());
		  			prepared.setString(5,table_1.getModel().getValueAt(i,0).toString());
		  			prepared.setString(6,table_1.getModel().getValueAt(i,1).toString());
		  			prepared.executeUpdate();
		  			Table();		  			
		  		}
	     		catch(SQLException e1)
		  		{
		  			e1.printStackTrace();
		  		}
			}
		});
		modify.setBounds(402, 513, 65, 29);
		panel.add(modify);
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
	
	// search textfield key releasd permis
	SEARCH = new JTextField();
	SEARCH.addKeyListener(new KeyAdapter() {
		@Override
		public void keyReleased(KeyEvent e) {
			DefaultTableModel table= (DefaultTableModel)table_1.getModel();
			String search =SEARCH.getText();
			TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
			table_1.setRowSorter(tr);
			tr.setRowFilter(RowFilter.regexFilter(search));	
		}
	});
	SEARCH.setBorder(null);
	SEARCH.setBounds(74, 96, 281, 29);
	panel.add(SEARCH);
	SEARCH.setColumns(10);

	
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

	
		// TABLE CODE MOUSE LISTENER 
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(63, 143, 400, 299);
		panel.add(scrollPane);
		

        table_1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) 
        	{
        		int i =table_1.getSelectedRow();
		  		try 
		  		{		  		
		  			id_taxi.setText(table_1.getModel().getValueAt(i,0).toString());
		  			cin.setText(table_1.getModel().getValueAt(i,1).toString());
		  			matricule.setText(table_1.getModel().getValueAt(i,2).toString());
		  			permis.setText(table_1.getModel().getValueAt(i,3).toString());
		  		}
		  		catch(Exception e1) 
		  		{
		  			JOptionPane.showConfirmDialog(null, e1);
		  		}
        	}
        });
        table_1.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		 "ID_Taxi", "CIN", "MATRICULE", "PERMIS"
        	}
        ));
        DefaultTableModel model = new DefaultTableModel();
        table_1.setModel(model);
        scrollPane.setColumnHeaderView(table_1);
        Table();
        scrollPane.setViewportView(table_1);
        
		// ++++++++++++++++++++++ FONT ++++++++++++++++++++++++++ //
		JLabel Font = new JLabel("");
		Font.setIcon(new ImageIcon(AdminTAXI.class.getResource("/IMG/amdin2Taxi.png")));
		Font.setBounds(0, 0, 1128, 630);
		panel.add(Font);		
	}
	
	// TABLE FONCTION
    // TABLE FONCTION //
	public void Table() {
		String sql="SELECT ID_TAXI, CIN, MATRICULE, PERMIS FROM taxi";
		
		try {
				prepared=cnx.prepareStatement(sql);
				resultat=prepared.executeQuery();
			    table_1.setModel(DbUtils.resultSetToTableModel(resultat));
			
			 }catch(SQLException e1)
			    {
				
			    }
		
	}
}
