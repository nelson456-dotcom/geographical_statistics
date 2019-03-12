package src;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.MenuEvent;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class mainpage {

	private JFrame frame;
	private JTextField username;
	private JPasswordField password;

	/**
	 * author nizar driouich.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainpage window = new mainpage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainpage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.BLUE);
		frame.setBounds(200, 200, 5, 300);
		frame.setSize(655, 482);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setResizable(true);
		Image im1 = null;
		
		frame.setIconImage(im1); 

		JPanel panel = new JPanel();
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		panel.setBackground(new Color(0, 102, 0));
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
	JMenuItem exit = new JMenuItem("exit");
		mnFile.add(exit);
		exit.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				
			}
			
			
			
		});
		

		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection conn = db_cnn.connectionDB();
				
					
				String user = username.getText();
				String pass = password.getText();
				int length = pass.length();
				if (length > 4) {

					JOptionPane.showMessageDialog(null, "password must only contain 4 characters ");

				}

				String info[] = new String[1]; 
			
			

				
					String query = "Select username,password from USERS WHERE USERNAME ='"+user+"' and PASSWORD='"+pass+"'";
					Statement statement;
					try {
						statement = conn.createStatement();
					    ResultSet resultSet;
						resultSet = statement.executeQuery(query);
					    int count = 0;

					while (resultSet.next()) {

						count = count + 1;
					}
					if (count == 1) {
						second_page d = new second_page();
						d.visi();
						frame.setVisible(false);

					}

					if (count > 1) {

						JOptionPane.showMessageDialog(null, "duplicate ");

					}

					else if ((count == 0) && (count != 1)) {
						
						JOptionPane.showMessageDialog(null, "user does not exist or incorrect password");

					}
					}catch(SQLException e) {
						e.printStackTrace();
					}
				}

				
				
					}
			
				
		);
		btnNewButton.setBounds(540, 358, 89, 40);
		panel.add(btnNewButton);
		
		username = new JTextField();
		username.setBounds(395, 130, 135, 40);
		panel.add(username);
		username.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(395, 258, 135, 40);
		panel.add(password);
		
		JLabel lblNewLabel = new JLabel("Username :");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		lblNewLabel.setBounds(276, 135, 135, 23);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password :");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		lblNewLabel_1.setBounds(276, 263, 135, 23);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(37, 99, 195, 266);
		panel.add(lblNewLabel_2);
		
		
		
		
		
		// Import ImageIcon     
		ImageIcon iconLogo = new ImageIcon("a.jpg");
		// In init() method write this code
		lblNewLabel_2.setIcon(iconLogo);
		
		
	}
}
