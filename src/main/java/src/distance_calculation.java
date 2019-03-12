package src;



import com.google.maps.model.TravelMode;

import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class distance_calculation {
	String url = "jdbc:oracle:thin:@localhost:1521:hr";
	String username = "ei";
	String password = "root123";

	Map<String, Integer> distanceMap = new HashMap<String, Integer>();

	// Create Tables
/*	public void createTable() {
		
		try {
			
			Connection conn = connection.connectionDB();
			String sql = "CREATE TABLE DistanceTable (ID_ANO INTEGER not NULL, company_id INTEGER, annee_scolaire varchar(255), home_to_company INTEGER, campus varchar(20), home_to_campus INTEGER, campus_to_company INTEGER);";

			Statement statement = conn.createStatement();
			statement.executeUpdate(sql);
			System.out.println("Create Table DistanceTable Successful");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Connection conn = connection.connectionDB();
			// close the connection
			if (conn != null) 
			{
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
*/
	public void cal_home_to_company() {
        //get
    	Connection conn = db_cnn.connectionDB();
        try {
        	String query = "Select * from CAMPUS where SITE='Cergy'";
			Statement statement;
			try {
				statement = conn.createStatement();
			ResultSet resultSet;
				resultSet = statement.executeQuery(query);
			
				ResultSetMetaData rsmd =  resultSet.getMetaData();
				int numcolumn = rsmd.getColumnCount();
			while (resultSet.next()) {
				for (int i = 1; i <=numcolumn; i++)
			       {	
			    	   String columnValue = resultSet.getString(i);
			    	   System.out.println( columnValue );
			         					       
			       }
			}

            String sql = "SELECT s.ID_ANO, s.ADR_CP, c.CODE_POSTAL,c.COMPANYID, i.annee_scolaire FROM hr.internship  i INNER JOIN hr.student  s ON s.ID_ANO = i.ID_ANO INNER JOIN hr.company c ON i.COMPANY_ID = c.COMPANYID";
         
			statement = conn.createStatement();
		
            ResultSet resultSets = statement.executeQuery(sql);

            String INSERT_SQL = "INSERT INTO DistanceTable(ID_ANO,company_id,annee_scolaire,home_to_company) VALUES(?,?,?,?)";
            
            PreparedStatement update_statement = conn.prepareStatement(INSERT_SQL);

            while (resultSets.next()) {
                try {
                    int ID_ANO = resultSets.getInt("ID_ANO");
                    int company_id = resultSets.getInt("COMPANYID");
                    String annee_scolaire =  resultSets.getString("annee_scolaire");

                    String home_adr_cp = resultSets.getString("ADR_CP");
                    String company_adr_cp = resultSets.getString("CODE_POSTAL");
                    Integer distance = getDistance(home_adr_cp+",france", company_adr_cp+",france");

                    update_statement.setInt(1, ID_ANO);
                    update_statement.setInt(2, company_id);
                    update_statement.setString(3,annee_scolaire);

                    if (distance != null) {
                        update_statement.setInt(4,distance);
                    }else{
                        update_statement.setInt(4,-1);
                    }
                    update_statement.executeUpdate();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) 
			{
            e.printStackTrace();
        } 
			finally {
            //close the connection
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
			}}
			catch (Exception e) {
				e.printStackTrace();
			}
	}

	public void insertCampus() {
		Connection connection = null;
		try {
			String sql ="SELECT ID_ANO FROM DISTANCETABLE";
			Statement statement = connection.createStatement();
			ResultSet resultSets = statement.executeQuery(sql);
			
			Set<Integer> id_set = new HashSet<Integer>();
			while (resultSets.next()) {
				try {
					int ID_ANO = resultSets.getInt("ID_ANO");
					id_set.add(ID_ANO);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			sql = "SELECT ID_ANO,SITE FROM FISCALYEAR group by ID_ANO,SITE";
			resultSets = statement.executeQuery(sql);

			String INSERT_SQL = "INSERT INTO DISTANCETABLE(ID_ANO,campus) VALUES(?,?)";
			PreparedStatement insert_statement = connection.prepareStatement(INSERT_SQL);

			String UPDATE_SQL = "UPDATE DISTANCETABLE SET CAMPUS=? WHERE ID_ANO=?";
			PreparedStatement update_statement = connection.prepareStatement(UPDATE_SQL);

			while (resultSets.next()) {
				try {
					String campus = resultSets.getString("SITE");
					int ID_ANO = resultSets.getInt("ID_ANO");
					if (id_set.contains(ID_ANO))
					{
						update_statement.setString(1, campus);
						update_statement.setInt(2, ID_ANO);
						update_statement.executeUpdate();
					}
					else
					{	
						insert_statement.setInt(1, ID_ANO);
						insert_statement.setString(2, campus);
						insert_statement.executeUpdate();
					}
				} 
				catch (Exception e)
				{
					System.out.println(e);
				}
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			// close the connection
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void cal_home_to_campus() {
		// get
		Connection connection = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(url, username, password);
			connection.setAutoCommit(true);

			Statement statement = connection.createStatement();
			ResultSet resultSets = statement.executeQuery(
					"select s.ADR_CP,s.ID_ANO,d.campus from student  s inner join distancetable  d on s.ID_ANO=d.ID_ANO");

			String UPDATE_SQL = "UPDATE distancetable SET home_to_campus = ? WHERE ID_ANO=?";
			PreparedStatement update_statement = connection.prepareStatement(UPDATE_SQL);

			while (resultSets.next()) {
				try {
					String adr_cp = resultSets.getString("ADR_CP");
					int student_id = resultSets.getInt("ID_ANO");
					String campus = resultSets.getString("ADR_CP");
					String address1 = adr_cp + "," + "france";

					String address2;
					if ("Cergy".equals(campus)) {
						address2 = "95000,france";
					} else {
						address2 = "64000,france";
					}
					Integer distance1 = getDistance(address1, address2);
					if (distance1 != null) {
						update_statement.setInt(1, distance1);
						update_statement.setInt(2, student_id);
						update_statement.executeUpdate();
					} else {
						update_statement.setInt(1, -1);
						update_statement.setInt(2, student_id);
						update_statement.executeUpdate();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close the connection
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void cal_campus_to_company() {
		// get
		Connection connection = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(url, username, password);
			connection.setAutoCommit(true);

			Statement statement = connection.createStatement();
			ResultSet resultSets = statement.executeQuery(
					"select d.ID_ANO,d.campus,c.CODE_POSTAL from company as c inner join distancetable as d on c.companyid=d.company_id");

			String UPDATE_SQL = "UPDATE distancetable SET campus_to_company = ? WHERE ID_ANO=?";
			PreparedStatement update_statement = connection.prepareStatement(UPDATE_SQL);

			while (resultSets.next()) {
				try {
					String adr_cp = resultSets.getString("CODE_POSTAL");
					int student_id = resultSets.getInt("ID_ANO");
					String campus = resultSets.getString("CAMPUS");
					String address1 = adr_cp + "," + "france";

					String address2;
					if ("Cergy".equals(campus)) {
						address2 = "95000,france";
					} else {
						address2 = "64000,france";
					}
					Integer distance1 = getDistance(address1, address2);
					if (distance1 != null) {
						update_statement.setInt(1, distance1);
						update_statement.setInt(2, student_id);
						update_statement.executeUpdate();
					} else {
						update_statement.setInt(1, -1);
						update_statement.setInt(2, student_id);
						update_statement.executeUpdate();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close the connection
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public Integer getDistance(String address1, String address2) {
		String key = address1 + "," + address2;
		if (distanceMap.containsKey(key)) {
			return distanceMap.get(key);
		} else {
			try {
				if ("NA".equals(address1) || "NA".equals(address2)) {
					System.out.println("11111");
					distanceMap.put(key, null);
					return null;
				}
				TravelMode distanceType = TravelMode.DRIVING;
				// int distance=2000;
				int distance = Distance.getDistance(address1, address2, distanceType);
				distanceMap.put(key, distance);
				return distance;
			} catch (Exception e) {
				e.printStackTrace();
				distanceMap.put(key, -1);
				return -1;
			}
		}
	}

	public static void main(String[] args) {
		distance_table main = new distance_table();
		
		main.cal_home_to_company();
		main.insertCampus();
		main.cal_home_to_campus();
		main.cal_campus_to_company();
	}
}