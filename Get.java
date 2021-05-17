package appTransport;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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


public class Get {
	Connection cnx = null;
	Statement prepared = null;
	ResultSet resultat = null;

	public static int i = 0;
	
	
	public int Jan() throws Exception {
		try {
			cnx = ConnexionMysql.ConnectDb();
			String deb, fin;
			deb = "2021-01-01";
			fin = "2021-01-31";
			prepared = cnx.createStatement();
			String check = "select sum(Prix) from courtier WHERE Date>='" + deb + "' and Date<='" + fin + "'";
			resultat = prepared.executeQuery(check);
			while (resultat.next()) {
				i = resultat.getInt(1);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return i;
	}
	public int Feb() throws Exception {
		try {
			cnx = ConnexionMysql.ConnectDb();
			String deb, fin;
			deb = "2021-02-01";
			fin = "2021-02-31";
			prepared = cnx.createStatement();
			String check = "select sum(Prix) from courtier WHERE Date>='" + deb + "' and Date<='" + fin + "'";
			resultat = prepared.executeQuery(check);
			while (resultat.next()) {
				i = resultat.getInt(1);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return i;
	}
	public int Mars() throws Exception {
		try {
			cnx = ConnexionMysql.ConnectDb();
			String deb, fin;
			deb = "2021-03-01";
			fin = "2021-03-31";
			prepared = cnx.createStatement();
			String check = "select sum(Prix) from courtier WHERE Date>='" + deb + "' and Date<='" + fin + "'";
			resultat = prepared.executeQuery(check);
			while (resultat.next()) {
				i = resultat.getInt(1);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return i;
	}
	public int avr() throws Exception {
		try {
			cnx = ConnexionMysql.ConnectDb();
			String deb, fin;
			deb = "2021-04-01";
			fin = "2021-04-31";
			prepared = cnx.createStatement();
			String check = "select sum(Prix) from courtier WHERE Date>='" + deb + "' and Date<='" + fin + "'";
			resultat = prepared.executeQuery(check);
			while (resultat.next()) {
				i = resultat.getInt(1);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return i;
	}
	public int mai() throws Exception {
		try {
			
			String deb, fin;
			deb = "2021-05-01";
			fin = "2021-05-31";
			prepared = cnx.createStatement();
			String check = "select sum(Prix) from courtier WHERE Date>='" + deb + "' and Date<='" + fin + "'";
			resultat = prepared.executeQuery(check);
			while (resultat.next()) {
				i = resultat.getInt(1);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return i;
	}
	public int juin() throws Exception {
		try {
			cnx =  ConnexionMysql.ConnectDb();
			String deb, fin;
			deb = "2021-06-01";
			fin = "2021-06-31";
			prepared = cnx.createStatement();
			String check = "select sum(Prix) from courtier WHERE Date>='" + deb + "' and Date<='" + fin + "'";
			resultat = prepared.executeQuery(check);
			while (resultat.next()) {
				i = resultat.getInt(1);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return i;
	}
	public int jul() throws Exception {
		try {
			cnx = ConnexionMysql.ConnectDb();
			String deb, fin;
			deb = "2021-07-01";
			fin = "2021-07-31";
			prepared = cnx.createStatement();
			String check = "select sum(Prix) from courtier WHERE Date>='" + deb + "' and Date<='" + fin + "'";
			resultat = prepared.executeQuery(check);
			while (resultat.next()) {
				i = resultat.getInt(1);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return i;
	}
	public int aug() throws Exception {
		try {
			cnx = ConnexionMysql.ConnectDb();
			String deb, fin;
			deb = "2021-08-01";
			fin = "2021-08-31";
			prepared = cnx.createStatement();
			String check = "select sum(Prix) from courtier WHERE Date>='" + deb + "' and Date<='" + fin + "'";
			resultat = prepared.executeQuery(check);
			while (resultat.next()) {
				i = resultat.getInt(1);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return i;
	}
	public int sep() throws Exception {
		try {
			cnx = ConnexionMysql.ConnectDb();
			String deb, fin;
			deb = "2021-09-01";
			fin = "2021-09-31";
			prepared = cnx.createStatement();
			String check = "select sum(Prix) from courtier WHERE Date>='" + deb + "' and Date<='" + fin + "'";
			resultat = prepared.executeQuery(check);
			while (resultat.next()) {
				i = resultat.getInt(1);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return i;
	}
	public int oct() throws Exception {
		try {
			cnx = ConnexionMysql.ConnectDb();
			String deb, fin;
			deb = "2021-10-01";
			fin = "2021-10-31";
			prepared = cnx.createStatement();
			String check = "select sum(Prix) from courtier WHERE Date>='" + deb + "' and Date<='" + fin + "'";
			resultat = prepared.executeQuery(check);
			while (resultat.next()) {
				i = resultat.getInt(1);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return i;
	}
	public int nov() throws Exception {
		try {
			cnx = ConnexionMysql.ConnectDb();
			String deb, fin;
			deb = "2021-11-01";
			fin = "2021-11-31";
			prepared = cnx.createStatement();
			String check = "select sum(Prix) from courtier WHERE Date>='" + deb + "' and Date<='" + fin + "'";
			resultat = prepared.executeQuery(check);
			while (resultat.next()) {
				i = resultat.getInt(1);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return i;
	}
	public int dec() throws Exception {
		try {
			cnx = ConnexionMysql.ConnectDb();
			String deb, fin;
			deb = "2021-12-01";
			fin = "2021-12-31";
			prepared = cnx.createStatement();
			String check = "select sum(Prix) from courtier WHERE Date>='" + deb + "' and Date<='" + fin + "'";
			resultat = prepared.executeQuery(check);
			while (resultat.next()) {
				i = resultat.getInt(1);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return i;
	}
}