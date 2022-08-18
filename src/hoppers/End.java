package hoppers;

import java.awt.event.*;
import javax.swing.*;

public class End extends JFrame{

    private JLabel labelWin;
    private JLabel labelLost;
    private JPanel endPanel;

    
	public End() {
		labelWin= new JLabel(Icons.YOUWIN);   
		labelLost= new JLabel(Icons.YOULOST); 
		
        endPanel = new JPanel();
        endPanel.setBounds(0, 0, Icons.YOUWIN.getIconWidth(), Icons.YOUWIN.getIconHeight());   //set the lable size the same as bg image
        endPanel= (JPanel)this.getContentPane();     //Set content panel
     
        
        // TODO Auto-generated constructor stub
	}
	
	/*
	 * to display the win icon
	 */
	public void endWin() 
	{
	
		 	this.setIconImage(((ImageIcon) Icons.YOUWIN).getImage()); 
	        this.setTitle("win!");
	        this.setBounds(0,0,Icons.YOUWIN.getIconWidth(), Icons.YOUWIN.getIconHeight());
	        this.add(labelWin);
	        try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
	      	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	        this.setResizable(false);
	        this.setVisible(true);
		
	}

	/*
	 * to display the lost icon
	 */
	public void endLost()
	{
		 
		this.setIconImage(((ImageIcon) Icons.YOULOST).getImage()); 
        this.setTitle("win!");
        this.setBounds(0,0,Icons.YOUWIN.getIconWidth(), Icons.YOUWIN.getIconHeight());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       
        this.setVisible(true);
        this.setResizable(false);
        this.add(labelLost);
       
		this.setVisible(true);
	}
	
	

}
