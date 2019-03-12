package a.a;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;

import reporting.modele.Classe;
import reporting.vue.FenetrePrincipale;

import java.awt.BorderLayout;
import javax.swing.JCheckBox;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Choice;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class second_page {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					second_page window = new second_page();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
void visi() {
	frame.setVisible(true);
	
}
	/**
	 * Create the application.
	 */
	public second_page() {
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
		panel.setBackground(new Color(51, 102, 51));
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 130, 1057, 2);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(319, 96, 0, 618);
		panel.add(separator_1);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		
		JButton btnNewButton_1 = new JButton("Jfreechart");
	
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Classe> ecole = new ArrayList<Classe>();

				FenetrePrincipale a = new FenetrePrincipale(ecole);
				a.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(8, 321, 113, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("m/v/s campus");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Statistics.campus();
			}
		});
		btnNewButton.setBounds(8, 245, 113, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("m/v/s company");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Statistics.company();
			}
		});
		btnNewButton_2.setBounds(8, 196, 113, 23);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("m/v/s home");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Statistics.home();
			}
		});
		btnNewButton_3.setBounds(8, 143, 113, 23);
		panel.add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 639, 132);
		panel.add(lblNewLabel);
		// Import ImageIcon     
		ImageIcon iconLogo = new ImageIcon("banner.jpg");
		// In init() method write this code
		lblNewLabel.setIcon(iconLogo);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(357, 143, 261, 251);
		panel.add(lblNewLabel_1);
		
		// Import ImageIcon     
		ImageIcon iconLogo1 = new ImageIcon("d.png");
		// In init() method write this code
		lblNewLabel_1.setIcon(iconLogo1);
		
		
		
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
		
		
	}
}
