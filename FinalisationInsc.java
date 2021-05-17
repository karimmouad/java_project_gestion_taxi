package appTransport;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class FinalisationInsc extends JFrame{
    /**
	 *  DECLARATION
	 */
	private static final long serialVersionUID = 1L;
    JLabel labelPhotoDeProfil;
    String s;
    private JLabel labelRight;
    private JLabel labelleft;
    private JLabel signFinale;
    private JLabel labelCertificat;
    private JLabel labelCinRecto;
    private JLabel labelCinVerso;
	static Connection cnx = null;
	static ResultSet resultat = null;
	static PreparedStatement prepared = null;
	private JLabel Sign_In;
	int cmp = 0;
	private JLabel addCertificat;
	private JLabel addPhotoDeProfile;
	private JLabel addCinRecto;
	private JLabel addCinVerso;
	
	
    public FinalisationInsc(){
    	super("FinalRegistration");
    	cnx = ConnexionMysql.ConnectDb();
    
    	
    														// PHOTO CERTIFICAT
    
    	
    labelCertificat = new JLabel();
    labelCertificat.setBounds(934, 203, 138, 133);
    getContentPane().add(labelCertificat);
      
    // BOUTTON ADD CERTIFICAT IMAGE DATA BASE
    addCertificat = new JLabel("");
    addCertificat.addMouseListener(new MouseAdapter() {   
        public void mouseClicked(MouseEvent e) {
        	
       	 JFileChooser fileChooser = new JFileChooser();
         fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
         FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg","gif","png");
         fileChooser.addChoosableFileFilter(filter);
         int result = fileChooser.showSaveDialog(null);
        
         if(result == JFileChooser.APPROVE_OPTION)
         {
             File selectedFile = fileChooser.getSelectedFile();
             String path = selectedFile.getAbsolutePath();
             labelCertificat.setIcon(ResizeImage(path));
             s = path;
    		 try
    		 {
    			 PreparedStatement ps = cnx.prepareStatement("UPDATE courtier SET CERTIFICAT = ? WHERE ID = '"+getid()+"'");
                 InputStream is = new FileInputStream(new File(s));  
                 ps.setBlob(1,is);
                 ps.executeUpdate();
                 JOptionPane.showMessageDialog(null, "Certificat Inserted");
                 cmp++; // POUR VERIFIER SI CE CHAMP EST VIDE
             }
    		 catch(Exception ex)
    		 {
                 ex.printStackTrace();
             }

             }
         else if(result == JFileChooser.CANCEL_OPTION){
             System.out.println("No Data");
         }
        }
    });
    
    addCertificat.setBounds(940, 341, 127, 13);
    getContentPane().add(addCertificat);
    getContentPane().setLayout(null);
    // FIN CODE DE BOUTTON ADD CERTIFICAT IMAGE DATA BASE
    
							    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
							    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
							    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
							    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
							    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
							    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //        
    
    													// PHOTO DE PROFILE
    
    
    labelPhotoDeProfil = new JLabel();
    labelPhotoDeProfil.setBounds(694,204,138,133);
    getContentPane().add(labelPhotoDeProfil);
    
   
    // BOUTTON ADD PHOTO DE PROFILE IMAGE DATA BASE
    	addPhotoDeProfile = new JLabel("");
    	addPhotoDeProfile.addMouseListener(new MouseAdapter() {   
        
    	public void mouseClicked(MouseEvent e) {
        	
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg","gif","png");
            fileChooser.addChoosableFileFilter(filter);
            int result = fileChooser.showSaveDialog(null);
            
            if(result == JFileChooser.APPROVE_OPTION)
            {
                File selectedFile = fileChooser.getSelectedFile();
                String path = selectedFile.getAbsolutePath();
                labelPhotoDeProfil.setIcon(ResizeImage(path));
                s = path;
            	   try
            	   {   
                      PreparedStatement ps = cnx.prepareStatement("UPDATE courtier SET PDP = ? WHERE ID = '"+getid()+"'");
                      InputStream is = new FileInputStream(new File(s));
                      ps.setBlob(1,is);
                      ps.executeUpdate();
                      JOptionPane.showMessageDialog(null, "Photo de Profil Inserted");
                      cmp++;
                   }
            	   catch(Exception ex)
            	   {
                      ex.printStackTrace();
                   }
            }
            else if(result == JFileChooser.CANCEL_OPTION){
                System.out.println("No Data");
            }
        }
        });
    	addPhotoDeProfile.setBounds(694, 341, 138, 13);
    	getContentPane().add(addPhotoDeProfile);
    // FIN CODE DE BOUTTON ADD PHOTO DE PROFILE IMAGE DATA BASE 
    
							    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
							    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
							    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
							    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
							    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
							    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //        
        
    														// PHOTO CIN RECTO 
        
    	labelCinRecto = new JLabel();
        labelCinRecto.setBounds(696, 388, 138, 133);
        getContentPane().add(labelCinRecto);
    
    
    // BOUTTON ADD CIN RECTO IMAGE DATA BASE
        addCinRecto = new JLabel("");
        addCinRecto.addMouseListener(new MouseAdapter() {   
        
        public void mouseClicked(MouseEvent e) 
        {
        	JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg","gif","png");
            fileChooser.addChoosableFileFilter(filter);
            int result = fileChooser.showSaveDialog(null);
            
            if(result == JFileChooser.APPROVE_OPTION)
            {
                File selectedFile = fileChooser.getSelectedFile();
                String path = selectedFile.getAbsolutePath();
                labelCinRecto.setIcon(ResizeImage(path));
                s = path;
                try
                { 
    			 PreparedStatement ps = cnx.prepareStatement("UPDATE courtier SET CINRECTO = ? WHERE ID = '"+getid()+"'");
                 InputStream is = new FileInputStream(new File(s));
                 ps.setBlob(1,is);
                 ps.executeUpdate();
                 JOptionPane.showMessageDialog(null, "CIN Recto Inserted");
                 cmp++;
                }
                catch(Exception ex)
                {
                	ex.printStackTrace();
                }
            }
            else if(result == JFileChooser.CANCEL_OPTION)
            {
                System.out.println("No Data");
            }
        }
    });
    addCinRecto.setBounds(698, 526, 138, 13);
    getContentPane().add(addCinRecto);
    // FIN CODE DE BOUTTON ADD CIN RECTO IMAGE DATA BASE

    
							    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
							    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
							    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
							    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
							    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
							    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //        
							    
							    
							    						// PHOTO CIN VERSO
							    
    
    labelCinVerso = new JLabel();
    labelCinVerso.setBounds(934, 391, 138, 133);
    getContentPane().add(labelCinVerso);
    
    // BOUTTON ADD CIN VERSO IMAGE DATA BASE

    addCinVerso = new JLabel("");
    addCinVerso.addMouseListener(new MouseAdapter() {   
        public void mouseClicked(MouseEvent e) 
        {
        	JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg","gif","png");
            fileChooser.addChoosableFileFilter(filter);
            int result = fileChooser.showSaveDialog(null);
            
            if(result == JFileChooser.APPROVE_OPTION)
            {
                File selectedFile = fileChooser.getSelectedFile();
                String path = selectedFile.getAbsolutePath();
                labelCinVerso.setIcon(ResizeImage(path));
                s = path;
                try
                {    
       			 	PreparedStatement ps = cnx.prepareStatement("UPDATE courtier SET CINVERSO = ? WHERE ID = '"+getid()+"'");
                    InputStream is = new FileInputStream(new File(s));
                  
                    ps.setBlob(1,is);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "CIN Verso Inserted");
                    cmp++;
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
            else if(result == JFileChooser.CANCEL_OPTION)
            {
                System.out.println("No Data");
            }
        }
    });
    addCinVerso.setBounds(940, 527, 127, 13);
    getContentPane().add(addCinVerso);
    // FIN CODE DE BOUTTON ADD CIN VERSO IMAGE DATA BASE
    
							    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
							    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
							    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
							    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
							    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
							    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //        
							    
							    
    														// SIGN IN BOUTTON
    
    
    
    Sign_In = new JLabel("");
    Sign_In.addMouseListener(new MouseAdapter() {
    	@Override
    	public void mouseClicked(MouseEvent e) 
    	{
			if(cmp < 4) 
			{
				JOptionPane.showMessageDialog(null , "Veillez remplir le champs vides !!");
			}
			else
			{
	    		dispose();
	    		LogCoursier obj = new LogCoursier();
	    		obj.setVisible(true);
			}    	
		}
    });
    
    
    // LABEL 
    
    Sign_In.setIcon(new ImageIcon(FinalisationInsc.class.getResource("/IMG/BouttonSignIn.png")));
    Sign_In.setBounds(825, 595, 144, 50);
    getContentPane().add(Sign_In);
    
    signFinale = new JLabel("");
    signFinale.setIcon(new ImageIcon(FinalisationInsc.class.getResource("/IMG/finalisationPrime.png")));
    signFinale.setBounds(641, 88, 493, 489);
    getContentPane().add(signFinale);
    
    labelleft = new JLabel("");
    labelleft.setIcon(new ImageIcon(FinalisationInsc.class.getResource("/IMG/leftblue.png")));
    labelleft.setBounds(584, 47, 634, 634);
    getContentPane().add(labelleft);
    
    labelRight = new JLabel("New label");
    labelRight.setIcon(new ImageIcon(FinalisationInsc.class.getResource("/IMG/cover.png")));
    labelRight.setBounds(36, 59, 622, 611);
    getContentPane().add(labelRight);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(1200,750);
    setVisible(true);
    }

    
    
    // ID TO INSERT IN TABLE COURTIER
    public static int getid()
    {
    	int id = 0;
 	   String sqlID = "SELECT MAX(ID) as 'id' FROM courtier";
 	   try {
 		   prepared = cnx.prepareStatement(sqlID);
 		   resultat = prepared.executeQuery();
 		   while(resultat.next())
 		   {
 			   id = resultat.getInt("id");
 		   }
 	   }
 	   catch(SQLException e)
 	   {
 		   e.printStackTrace();
 	   }
 	   return id;
    }
    
    //METHODE TO RESIZE IMAGE
    public ImageIcon ResizeImage(String imgPath){
        ImageIcon MyImage = new ImageIcon(imgPath);
        Image img = MyImage.getImage();
        Image newImage = img.getScaledInstance(labelPhotoDeProfil.getWidth(), labelPhotoDeProfil.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;
    }
    
    // // // ///////////////////////////////////////////////////////////////// // // //
    
    public static void main(String[] args){
        new FinalisationInsc();
    }
   }