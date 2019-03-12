package a.a;
/* Pierre-Paul GIANNETTI
 * Groupe D - MAIN
 * 06/06/2012
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class mEtListener implements ActionListener{
	
	public void actionPerformed(ActionEvent evt) 
    {  if (evt.getSource()instanceof JMenuItem)  
             // gestion des événements liés aux menus 
            { String ChoixOption = evt.getActionCommand(); 
               if (ChoixOption.equals("Sauver")){
            	   
               }
               else if (ChoixOption.equals("Charger")){
            	   
               }
               else if (ChoixOption.equals("Quitter")) 
               {
            	   System.exit(0);
          
               }
               else if (ChoixOption.equals("A propos"))
        	   {
            	  // FenQuitter F1 = new FenQuitter();
                 //  F1.setVisible(true); 
        	   }
            }
     }

}
