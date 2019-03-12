package JFreeChart.vue;


import java.util.List;
import java.awt.*;
import java.awt.event.*; 

import javax.swing.*;

  
import org.jfree.chart.plot.*;  
 

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import reporting.modele.*;

public class FenetrePrincipale extends JFrame 
 {
	/**
	 * 
	 */
	private static final long serialVersionUID = -447998472145987202L;
	private ChartPanel  jpnl_Graph;  // un panneau pour dessiner le graphique
	private JPanel jpnl_Buttons; // un panneau pour les boutons graphiques
	private JPanel jpnl_Sql;// un panneau pour la saisie de la requete sql
	private JButton home;
	private JButton home_pie;
	private JButton campus;
	private JButton campus_pie;
	private JButton company;
	private JButton company_pie;

	// table d'association reliant les graphique et les boutons
	List<Classe> ecole;
	

	public FenetrePrincipale(List<Classe> pecole ) {  
		// appel au constructeur de la classe parente JFrame
		super("Outil de reporting");
		setBackground(new Color(0, 102, 0));
		ecole =pecole;
		
		setSize(800, 550);  
	    setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
		setResizable(false);
		
		
		home=new JButton("Histogramme");
		home.setSize(20,50);
		home_pie=new JButton("pieChart");
		home_pie.setSize(20,50);
		
		
		company=new JButton("Histogramme");
		company.setSize(20,50);
		
		
		company_pie=new JButton("pieChart");
		company_pie.setSize(30,60);
		
		campus=new JButton("Histogramme");
		campus.setSize(20,50);
		
		
		
		campus_pie=new JButton("pieChart");
		campus_pie.setSize(20,50);
		
		
		
		
		// gestionnaire de placement de la fenetre principale
		// en GridLayout sur 2 colonnes
		// le 0 en 1er paramÃ¨tre prÃ©cise que le nb de lignes est extensible
		// on espace les composants de 5 en horizontal et 2 en vertical
		getContentPane().setLayout(new BorderLayout());
		
		//gestionnaire des placement des bouton 
		jpnl_Buttons = new JPanel();//new GridLayout(0,1));	
		jpnl_Buttons.setLayout(new GridLayout(10,1));
		jpnl_Buttons.add(home);
		jpnl_Buttons.add(home_pie);
		jpnl_Buttons.add(campus);
		jpnl_Buttons.add(campus_pie);
		jpnl_Buttons.add(company);
		jpnl_Buttons.add(company_pie);
		jpnl_Sql= new JPanel(new BorderLayout());
	
		
		jpnl_Graph = new ChartPanel(null); 
	   
	

	     
	     getContentPane().add(jpnl_Graph, BorderLayout.EAST);
	     getContentPane().add(jpnl_Buttons, BorderLayout.WEST);
	     getContentPane().add(jpnl_Sql, BorderLayout.SOUTH);
	 	
	 	home.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(!(ecole.isEmpty()))
					afficherhome(ecole);
				else
				{
					
				}
					
			}
		});
	 	
	 	home_pie.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(!(ecole.isEmpty()))
					afficherhome_pie(ecole);
				else
				{
					
				}
					
			}
		});
	 	
	 	campus.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(!(ecole.isEmpty()))
					affichercampus(ecole);
				else
				{
					
				}
					
			}
		});
	 	
	 	campus_pie.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(!(ecole.isEmpty()))
					affichercampus_pie(ecole);
				else
				{
					
				}
					
			}
		});
	 	
	 	
           company.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(!(ecole.isEmpty()))
					affichercompany(ecole);
				else
				{
					
				}
					
			}
		});
           
           
           company_pie.addActionListener(new ActionListener(){
   			public void actionPerformed(ActionEvent e){
   				if(!(ecole.isEmpty()))
   					affichercompany_pie(ecole);
   				else
   				{
   					
   				}
   					
   			}
   		});
   	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	     
	 	home.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ecole.add(new Classe("mean", 765,269));
				ecole.add(new Classe("variance", 4926,203));
				ecole.add(new Classe("Standart deviation", 221,174));
			}
		});
	 	
	 	
	 	company.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ecole.add(new Classe("mean", 272,269));
				ecole.add(new Classe("variance", 6844,203));
				ecole.add(new Classe("Standart deviation", 261,174));
			}
		});
	 	
		campus.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ecole.add(new Classe("mean", 374,269));
				ecole.add(new Classe("variance", 100,203));
				ecole.add(new Classe("Standart deviation", 110,174));
			}
		});
	 	
	 	
	 	
	 	
	     	
		addWindowListener(new WindowAdapter() {       
				       public void windowClosing(WindowEvent e) {         
					      dispose();        
					      System.exit(0);     
				      }      
		}
	);
	}		
			
			
			
		
	/****************histogramme****************/
	
	
	public void afficherhome(List<Classe> ecole){
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
		for (Classe c : ecole){
			  dataset.addValue(new Integer(c.getNbGarcons()), c.getNom(),"");    
			}
		    JFreeChart barChart = ChartFactory.createBarChart("home to campus", "",       
				"Unité vendue", dataset, PlotOrientation.VERTICAL, true, true, false);  	
				jpnl_Graph.setChart(null);
				jpnl_Graph.setChart(barChart);
		}
	
	public void afficherhome_pie(List<Classe> ecole){
		DefaultPieDataset pieDataset = new DefaultPieDataset(); 
		
		for (Classe c : ecole){
		  pieDataset.setValue(c.getNom(), new Integer(c.getNbGarcons()));   
		}	   
		JFreeChart pieChart = ChartFactory.createPieChart("home to campus",pieDataset, true, true, false);     
		jpnl_Graph.setChart(null);
		jpnl_Graph.setChart(pieChart);
	}
	
	public void affichercompany(List<Classe> ecole){
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
		for (Classe c : ecole){
			  dataset.addValue(new Integer(c.getNbGarcons()), c.getNom(),"");    
			}
		    JFreeChart barChart = ChartFactory.createBarChart("campus to company", "",       
				"Unité vendue", dataset, PlotOrientation.VERTICAL, true, true, false);  	
				jpnl_Graph.setChart(null);
				jpnl_Graph.setChart(barChart);
		}
	
	
	public void affichercompany_pie(List<Classe> ecole){
		DefaultPieDataset pieDataset = new DefaultPieDataset(); 
		
		for (Classe c : ecole){
		  pieDataset.setValue(c.getNom(), new Integer(c.getNbGarcons()));   
		}	   
		JFreeChart pieChart = ChartFactory.createPieChart("campus to company",pieDataset, true, true, false);     
		jpnl_Graph.setChart(null);
		jpnl_Graph.setChart(pieChart);
	}
	
	public void affichercampus(List<Classe> ecole){
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
		for (Classe c : ecole){
			  dataset.addValue(new Integer(c.getNbGarcons()), c.getNom(),"");    
			}
		    JFreeChart barChart = ChartFactory.createBarChart("home to company", "",       
				"Unité vendue", dataset, PlotOrientation.VERTICAL, true, true, false);  	
				jpnl_Graph.setChart(null);
				jpnl_Graph.setChart(barChart);
		}
	
	public void affichercampus_pie(List<Classe> ecole){
		DefaultPieDataset pieDataset = new DefaultPieDataset(); 
		
		for (Classe c : ecole){
		  pieDataset.setValue(c.getNom(), new Integer(c.getNbGarcons()));   
		}	   
		JFreeChart pieChart = ChartFactory.createPieChart("home to company",pieDataset, true, true, false);     
		jpnl_Graph.setChart(null);
		jpnl_Graph.setChart(pieChart);
	}
	
	/**************************************************************/		
			
	/********** camembert **********/
	
	/*********************************************/
}

