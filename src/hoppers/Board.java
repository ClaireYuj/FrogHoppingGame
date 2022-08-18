package hoppers;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;


public class Board extends JFrame implements ActionListener {
	private Container contentPane;
	// use array list to store the characters
	private ArrayList<Frog> frogs;
	private ArrayList<Pad> pads;
	private int xOffset=0;
	private int yOffset=200;
	private boolean designMode;
	private JButton start;
	private End end=new End();
	
	
	// testing codes -- if the ArrayList works successfully
	private void printFrogsInformation(Frog f) {
		System.out.println("This Frog:");
		System.out.println("x:"+f.getXPosition()+" y:"+ f.getYPosition());
		System.out.println("Player: "+f.isPlayer());
			if(findCharacter(f.getXPosition(),f.getYPosition())==f) {
				System.out.println("founded in the ArrayList: frogs");
			}
		System.out.println("Now Frog List Size: "+frogs.size());
		System.out.println("-----Frog Test Finished-----\n");
	}
	private void printPadInformation(Pad p) {
		System.out.println("This Pad:");
		System.out.println("x:"+p.getXPosition()+" y:"+ p.getYPosition());
		if(findCharacter(p.getXPosition(),p.getYPosition())==p) {
			System.out.println("founded in the ArrayList: pads");
		}
		System.out.println("Now Pad List Size: "+pads.size());
		System.out.println("-----Pad Test Finished-----\n");
	}
	
	
	public void setDesignMode(boolean newState) {
		if(newState) {
			this.add(start);
		}
		else {
			this.remove(start);
		}
		designMode=newState;
	}
	public boolean isDesignMode() {
		return designMode;
	}
	// update new character block
		//find a character
	public Square findCharacter(int x, int y) {
		for(Pad p:pads) {
			if(p.getXPosition()==x &&
				p.getYPosition()==y) {
				return p;}}
		for(Frog f:frogs) {
			if(f.getXPosition()==x &&
				f.getYPosition()==y) {
				return f;}}
		return null;
	}
		// remove a character
	private void removeCharacter(int x,int y) {
		if(this.findCharacter(x, y)!=null) {
			this.remove(findCharacter(x,y));
			if(this.findCharacter(x, y) instanceof Frog) {
				frogs.remove(findCharacter(x,y));}
			if(this.findCharacter(x, y) instanceof Pad) {
				pads.remove(findCharacter(x,y));}
		}
	}
		// add a character
	private void addCharacter(Square s) {
		if(s instanceof Square) {
			this.add(s);
			if(s instanceof Frog){
				frogs.add((Frog)s);
				s.addActionListener(this);}
			if(s instanceof Pad) {
				pads.add((Pad)s);
				s.addActionListener(this);}
		}
	}
	public void updateCharacter(Square s){
		if(s !=null) {
		this.removeCharacter(s.getXPosition(),s.getYPosition());
		this.addCharacter(s);
		}
	}
	
	public void clearFrogs() {
		while(frogs.size()!=0) {
			this.updateCharacter(new Pad(frogs.get(0).getXPosition(),frogs.get(0).getYPosition()));
		}
	}
	
	
	
	public int isWin()
	{
                // count the number of red frog (player)
                int numOfRed = 0;
                // count the number of frogs that can not move to other pads
                int unMoveFrog = 0;
                for(Frog f:frogs)
                {
                        // count the number of pads that frogs can arrive
                        int movablePad = 0;
                        for(Pad p:pads){
                                if(f.isMovable(this, f, p)){
                                        movablePad++;
                                } 
                           }
                        if(f.isPlayer()){
                                numOfRed++;
                        }
                        if(movablePad == 0) unMoveFrog++;
                }
                /*
                 * if there exits only one red frog, then the game is win
                 * and the win label will display
                 */
                if(frogs.size()==numOfRed&&frogs.size()>0){
                	
                			end.endWin();
							this.run();
					        System.out.println("You win!");
                        
                         return 1;
                		}
                /*
                 * if all frogs can not move, then the game is fail
                 * and the lost label will display
                 */
                if(frogs.size()==unMoveFrog){
                		
                        System.out.println("Fail!");
                        end.endLost();
                        return 0;
                }
                else{
                System.out.println("ing");
                 return 2;
                		}
    	}
	public void actionPerformed (ActionEvent e) {
		/*
		 * in the play-model,player can control the frog,to jump from one pad to another pad if there a frog between then
		 * if a frog is selected, it will changed its status(only a selected frog can move)
		 * if the player select a pad after a frog be selected, and the selected frog is movable,the frog will jump to that pad
		 */
		if(!designMode) {
		if (e.getSource() instanceof Frog) {
			printFrogsInformation((Frog)e.getSource());
			for (Frog f: frogs) {
    			f.setSelect(false);
			}
			((Frog)e.getSource()).setSelect(true);
		}
		if (e.getSource() instanceof Pad) {
			Pad newPad=null;
			printPadInformation((Pad)e.getSource());
			for (Frog f: frogs) {
				if(f.isMovable(this, f, (Pad)e.getSource())&&f.getSelected())
	        	{
					newPad=new Pad((f.getXPosition()+((Pad)e.getSource()).getXPosition())/2,
							(f.getYPosition()+((Pad)e.getSource()).getYPosition())/2);
					f.moveTo((Pad)e.getSource());
	        	}
	        }
			this.updateCharacter(newPad);
		}
		/*
		 * judge if the player is win 
		 */
		this.isWin();
		}
		/*
		 * in the design-model, the player can place the frogs
		 * the player can click the green frog to update it to red frog
		 * and click the red frog can clear the frog
		 */
		else {
			if (e.getSource() instanceof Frog) {
				Frog f=(Frog)e.getSource();
				if(!f.isPlayer()) {
					this.updateCharacter(new Frog(f.getXPosition(),f.getYPosition(),true));
					}
				else {
					this.updateCharacter(new Pad(f.getXPosition(),f.getYPosition()));
					}
			}
			if (e.getSource() instanceof Pad) {
				Pad p=(Pad)e.getSource();
				this.updateCharacter(new Frog(p.getXPosition(),p.getYPosition(),false));
			}
			if (e.getSource()==start&&this.isWin()!=0) {
				for(Frog f: frogs) {
					if(f.isPlayer()) {
						this.setDesignMode(false);
						this.isWin();
						break;
					}
				}
			}
		}
	
		this.repaint();
	}
	

	public void run()
	{
		try { Thread.sleep(1000); }
		catch (InterruptedException e) {
			  e.printStackTrace();}
		this.notify();
	}

	
	
    public Board(String title) {
    	super(title);

        
        start=new JButton(Icons.START);
        start.setBounds(50, 800, 100, 100);
        start.addActionListener(this);
      
        contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.setPreferredSize(new Dimension(754+xOffset, 754+yOffset));
        this.setContentPane(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
 
        frogs=new ArrayList<Frog>();
        pads=new ArrayList<Pad>();
        for (int i=0;i<5;i++) {
        	for(int j=0;j<5;j++) {
        		if((i+j)%2==0){
        			this.updateCharacter(new Pad(i,j));
        			}
        		else {
        			this.updateCharacter(new Square(i,j));
        			}
        	}
        }
        this.pack();
        
        
      }
    }