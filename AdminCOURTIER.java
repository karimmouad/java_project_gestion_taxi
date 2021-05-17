package appTransport;

import java.awt.BorderLayout;
import java.util.Date;

import java.awt.EventQueue;
import java.awt.Image;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.PreparedStatement;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.mysql.jdbc.Statement;


import net.proteanit.sql.DbUtils;

import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class AdminCOURTIER extends AdminLogIn{
    /**
     * 
     */
	
	// DECLARATION DES VARIABLES
	
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    Connection cnx = null;
    ResultSet resultat = null;
    PreparedStatement prepared = null;
    public JFrame frame = new JFrame();
    protected JTable table_1;
    private JTextField SEARCH;
   

    // LOG
    
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
    public AdminCOURTIER() {
      
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1150, 680);
        cnx = ConnexionMysql.ConnectDb();
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        Panel panel = new Panel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        
        //SCROLLPANE && TABLE
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(21, 194, 494, 390);
        panel.add(scrollPane);
        
        table_1 = new JTable();
       
        table_1.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"ID", "ID_Taxi", "Ville_Depart", "Ville_Arrivee", "Prix", "CIN_Courtier", "Date", "Heure"
        	}
        ));
        DefaultTableModel model = new DefaultTableModel();
        table_1.setModel(model);
        scrollPane.setColumnHeaderView(table_1);
        Table();
        scrollPane.setViewportView(table_1);
        scrollPane.setViewportView(table_1);
        
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
        SEARCH.setColumns(10);
        SEARCH.setBounds(73, 99, 278, 22);
        panel.add(SEARCH);
        
        
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

        
        //Image Courtier
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(AdminCOURTIER.class.getResource("/IMG/amdin2COURTIER.png")));
        lblNewLabel.setBounds(0, 0, 1128, 630);
        panel.add(lblNewLabel);
    }
    
    
    // TABLE FONCTION //
	public void Table() {
		String sql="SELECT Nom , Prenom, CIN, Ville , NumTel , email FROM courtier";
		
		try {
				prepared=cnx.prepareStatement(sql);
				resultat=prepared.executeQuery();
			    table_1.setModel(DbUtils.resultSetToTableModel(resultat));
			
			 }catch(SQLException e1)
			    {
				
			    }
		
	}
}