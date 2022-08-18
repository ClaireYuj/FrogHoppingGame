package hoppers;

import java.awt.event.*;
import javax.swing.*;


 
public class Home extends JFrame implements ActionListener{
    
    private JLayeredPane pane;  // Layer Panel
    private JLabel label;
    private JPanel panel1;
    private int lvFlag;
    private JButton lv1Button;
    private JButton lv11Button;
    private JButton lv21Button;
    private JButton designButton;
    
    public int getFlag(){
        return lvFlag;
    }
    public void createGame(int a){
        switch(a){
        	case 0:{
            	Board boardUserDesign= new Board("Hoppers!");   
            	boardUserDesign.setDesignMode(true);
            	break;
        	}
            case 1:{
                Board boardLv1 = new Board("Hoppers!");    
                boardLv1.updateCharacter(new Frog(0,0,true));
                boardLv1.updateCharacter(new Frog(1,1,false));
                boardLv1.isWin();
                break;
            }
            case 11:{
                Board boardLv11 = new Board("Hoppers!");    
                boardLv11.updateCharacter(new Frog(0,0,true));
                boardLv11.updateCharacter(new Frog(2,2,false));
                boardLv11.updateCharacter(new Frog(3,3,false));
                break;
            }
             case 21:{
                    Board boardLv11 = new Board("Hoppers!");    
                    boardLv11.updateCharacter(new Frog(0,0,true));
                    boardLv11.updateCharacter(new Frog(2,2,false));
                  

                    break;
            }
            default:break;
            }
        }
    
    public void actionPerformed(ActionEvent aEvent) {

        
        if(aEvent.getSource()==lv1Button){
            lvFlag = 1;
            createGame(lvFlag);
            this.setVisible(false);
        }
        
        if(aEvent.getSource()==lv11Button){
            lvFlag = 11;
            createGame(lvFlag);
            this.setVisible(false);
        }
        
        if(aEvent.getSource()==lv21Button){
            lvFlag = 21;
            createGame(lvFlag);
            this.setVisible(false);
        }
        if(aEvent.getSource()==designButton)
        {
            lvFlag = 0;
            createGame(lvFlag);
            this.setVisible(false);
        }
        
    }
    public Home() {  
        label = new JLabel(Icons.BACKGROUND);    //add background into bgLabel
        
        panel1 = new JPanel();
        panel1.setBounds(0, 0, Icons.BACKGROUND.getIconWidth(), Icons.BACKGROUND.getIconHeight());   //set the lable size the same as bg image
        panel1 = (JPanel)this.getContentPane();     //Set content panel
        panel1.add(label);
        
        lv1Button = new JButton(Icons.LEVEL1);
        lv1Button.setBounds(310,280, 100, 40);
        lv1Button.addActionListener(this);

        lv11Button = new JButton(Icons.LEVEL11);
        lv11Button.setBounds(310,325, 100, 40);
        lv11Button.addActionListener(this);
        
        lv21Button = new JButton(Icons.LEVEL21);
        lv21Button.setBounds(310,370, 100, 40);
        lv21Button.addActionListener(this);

        designButton = new JButton(Icons.DESIGN);
        designButton.setBounds(310,415, 100, 40);
        designButton.addActionListener(this);
        
        pane= new JLayeredPane();
        pane.add(panel1,JLayeredPane.DEFAULT_LAYER);  
        pane.add(lv1Button, JLayeredPane.MODAL_LAYER);
        pane.add(lv11Button, JLayeredPane.MODAL_LAYER);  
        pane.add(lv21Button, JLayeredPane.MODAL_LAYER);
        pane.add(designButton,JLayeredPane.MODAL_LAYER);

        this.setIconImage(((ImageIcon) Icons.BRAND).getImage()); 
        this.setTitle("Hoppes!");
        this.setBounds(0,0,Icons.BACKGROUND.getIconWidth(), Icons.BACKGROUND.getIconHeight());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayeredPane(pane);
        this.setVisible(true);
        this.setResizable(false);
        }
	}
      
