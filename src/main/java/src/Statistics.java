import java.awt.List;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import org.apache.commons.math.stat.descriptive.SummaryStatistics;

import com.google.maps.errors.ApiException;
import com.google.maps.model.TravelMode;

import JFreeChart.vue.FenetrePrincipale;


public class Statistics {

	/**
	 * @param arg0
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws ApiException 
	 */
	public static void main(ActionEvent arg0) throws ApiException, InterruptedException, IOException {
		
		
	}
		/*
		// Distance
		String address1 = "Oslo";
		String address2 = "Marakech";
		TravelMode distanceType = TravelMode.DRIVING;
		int distance = Distance.getDistance(address1, address2, distanceType);
		System.out.println(distance);
		*/
	
	public static void home() {
		   
			
			
		 			try {
		 				
		 				Connection conn = db_cnn.connectionDB();
		 				String sql = "select home_to_campus from distancetable where home_to_campus> 10";
		 				Statement statement;
		 				statement = conn.createStatement();
		 				ResultSet resultSets = statement.executeQuery(sql);
		 				
		 				ResultSetMetaData rsmd =  resultSets.getMetaData();
		 				int numcolumn = rsmd.getColumnCount();
		 				
		 				ArrayList<Double> frameList =  new ArrayList<Double>();
		 				
		 				SummaryStatistics ss = new SummaryStatistics();
		 				
		 				Double[] ds = frameList.toArray(new Double[frameList.size()]);
		 				
		 				
		 				 
		 			while (resultSets.next()) {
		 		
		 				 double home_to_campus = resultSets.getInt("home_to_campus");
		 					frameList.add(home_to_campus);
		 					 ds = frameList.toArray(new Double[frameList.size()]);
		 			}
		 		
		 			int n1 = ds.length;
		 			JOptionPane.showMessageDialog(null, "Variance: " + 
		 	               variance(ds, n1));
		 		JOptionPane.showMessageDialog(null,"Standard Deviation: " + 
		                    standardDeviation(ds, n1)); 
		 		JOptionPane.showMessageDialog(null,"mean :"+ mean(ds));
		  
		  
		  
	
		
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		 			
		 			
	public static void company() {
			
		
		 			try {
		
		 				 Connection conn = db_cnn.connectionDB();
		 				
		 				 String sql = "select home_to_company from distancetable where home_to_company >= 2";
		 				Statement statement;
		 				statement = conn.createStatement();
		 				ResultSet resultSets = statement.executeQuery(sql);
		 				
		 				ResultSetMetaData rsmd =  resultSets.getMetaData();
		 				int numcolumn = rsmd.getColumnCount();
		 				
		 				ArrayList<Double> frameList1 =  new ArrayList<Double>();
		 				
		 				SummaryStatistics ss = new SummaryStatistics();
		 				
		 				Double[] s = frameList1.toArray(new Double[frameList1.size()]);
		 				
		 				
		 				 
		 			while (resultSets.next()) {
		 		
		 				 double home_to_campus = resultSets.getInt("home_to_company");
		 					frameList1.add(home_to_campus);
		 					 s = frameList1.toArray(new Double[frameList1.size()]);
		 			}
		 		
		 			int n1 = s.length;
		 			JOptionPane.showMessageDialog(null, "Variance: " + 
		 	               variance(s, n1));
		 		JOptionPane.showMessageDialog(null,"Standard Deviation: " + 
		                    standardDeviation(s, n1)); 
		 		JOptionPane.showMessageDialog(null,"mean :"+ mean(s));
		               }
		               catch (Exception e) 
		   			{
		               e.printStackTrace();
		           } 
		   			
		                  
	}
	
	public static void campus() {
	
	
	try {
		Connection conn1 = db_cnn.connectionDB();
		String sql = "select campus_to_company from distancetable where campus_to_company >=20";
			Statement statement;
			statement = conn1.createStatement();
			ResultSet resultSets = statement.executeQuery(sql);
			
			ResultSetMetaData rsmd =  resultSets.getMetaData();
			int numcolumn = rsmd.getColumnCount();
			
			ArrayList<Double> frameList1 =  new ArrayList<Double>();
			
			SummaryStatistics ss = new SummaryStatistics();
			
			Double[] s = frameList1.toArray(new Double[frameList1.size()]);
			
			
			 
		while (resultSets.next()) {
	
			 double home_to_campus = resultSets.getInt("campus_to_company");
				frameList1.add(home_to_campus);
				 s = frameList1.toArray(new Double[frameList1.size()]);
		}
	
		int n1 = s.length;
		JOptionPane.showMessageDialog(null, "Variance: " + 
	               variance(s, n1));
		JOptionPane.showMessageDialog(null,"Standard Deviation: " + 
                   standardDeviation(s, n1)); 
		JOptionPane.showMessageDialog(null,"mean :"+ mean(s));
       }
       catch (Exception e) 
		{
       e.printStackTrace();
   } 
	



}
	

	
	public static double mean(Double[] ds) {
	    double sum = 0;
	    for (int i = 0; i < ds.length; i++) {
	        sum += ds[i];
	    }
	
	    return sum / ds.length;
	}
    static double variance(Double[] ds, 
                           int n)
    {
        // Compute mean (average 
        // of elements)
        double sum = 0;
         
        for (int i = 0; i < n; i++)
            sum += ds[i];
        double mean = (double)sum / 
                      (double)n;
     
        // Compute sum squared 
        // differences with mean.
        double sqDiff = 0;
        double result;
        for (int i = 0; i < n; i++) 
            sqDiff += (ds[i] - mean) * 
                      (ds[i] - mean);
        
				
				result=(double)sqDiff / n; 
				double[] info = new double[1]; 
				info[0] = result;
				
				
		        
        return (double)sqDiff / n;
        
    }
     
    static double standardDeviation(Double[] ds, 
                                    int n)
    {
        return Math.sqrt(variance(ds, n));
    }	
	
	

}
