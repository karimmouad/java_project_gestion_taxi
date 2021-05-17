package appTransport;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import net.proteanit.sql.DbUtils;

public class Graphe extends JPanel {

	private static final long serialVersionUID = 1L;

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

	public static Date DATEFROM, DATETO;
	public static int i;
	static Connection cnx = null;
	static PreparedStatement prepared = null;
	static ResultSet resultat = null;
	public static String ville = AdminDASHBOARD.getCity();


	/**
	 * Constructeur
	 * @param titre : le titre du graphique
	 * @param abscisse : le nom de l'axe des abscisses
	 * @param ordonnee : le nom de l'axe des ordonnées
	 * @param valeurs : les valeurs
	 * @param fond : la couleur de fond
	 * @param listeSeries : les séries
	 * @param listeCategory : les catégories
	 * @param legende : vrai si on affiche la légende
	 */
	public Graphe(String titre, String abscisse, String ordonnee, List<Float> valeurs, Color fond, List<String> listeSeries, List<String> listeCategory, boolean legende) {
		super(new GridLayout(1,0));
		this.titre=titre;
		this.ordonnee=ordonnee;
		this.abscisse=abscisse;
		this.valeurs=valeurs;
		this.series=listeSeries;
		this.categories=listeCategory;
		this.legende=legende;
		this.couleurFond=fond;
		initialiser();
	}

	/**
	 * Initialise le graphique
	 */
	private void initialiser(){
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		int k = 0;
		for ( int j=0; j<categories.size(); j++){
			for (int i=0; i<series.size(); i++){
				dataset.addValue(valeurs.get(k), series.get(i), categories.get(j));
				k++;
			}

		}
		JFreeChart chart = ChartFactory.createBarChart(
				titre,   					// chart title
				abscisse,					// domain axis label
				ordonnee,   				// range axis label
				dataset,    				// data
				PlotOrientation.VERTICAL, 	// orientation
				legende,                    // include legend
				true,                     	// tooltips
				false                     	// URL
		);

		// definition de la couleur de fond
		chart.setBackgroundPaint(couleurFond);

		CategoryPlot plot = (CategoryPlot) chart.getPlot();

		//valeur comprise entre 0 et 1 transparence de la zone graphique
		plot.setBackgroundAlpha(0.9f);

		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setDrawBarOutline(false);

		// pour la couleur des barres pour chaque serie

		for (int s=0; s<series.size(); s++){
			GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, couleursBarres[s],
					0.0f, 0.0f, new Color(0, 40, 70));
			renderer.setSeriesPaint(s, gp0);

		}		

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setFillZoomRectangle(true);
		chartPanel.setMouseWheelEnabled(true);
		chartPanel.setPreferredSize(new Dimension(500, 270));

		add(chartPanel);
	}

	/**
	 * Création d'un graphique
	 * @param a
	 */
	public static void main(String[] a){

		/**

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
		*/

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