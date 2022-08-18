package hoppers;

import java.net.URL;
import javax.swing.*;

class Icons{
	public static final Icon WATER = makeIcon("/images/Water.png");
	public static final Icon GREENFROG=makeIcon("/images/GreenFrog.png");
	public static final Icon GREENFROG2=makeIcon("/images/GreenFrog2.png");
	public static final Icon REDFROG=makeIcon("/images/RedFrog.png");
	public static final Icon REDFROG2=makeIcon("/images/RedFrog2.png");
	public static final Icon PAD=makeIcon("/images/LilyPad.png");
	public static final Icon LEVEL1=makeIcon("/images/lv1.png");
	public static final Icon LEVEL11=makeIcon("/images/lv11.png");
	public static final Icon LEVEL21=makeIcon("/images/lv21.png");
	public static final Icon BRAND=makeIcon("/images/wi.png");
	public static final Icon BACKGROUND=makeIcon("/images/Background.png");
	public static final Icon RESTART=makeIcon("/images/Restart.jpg");
	public static final Icon DESIGN=makeIcon("/images/Design.jpg");
	public static final Icon START=makeIcon("/images/Start.png");
	public static final Icon YOUWIN=makeIcon("/images/win.png");
	public static final Icon YOULOST=makeIcon("/images/lost.png");
	
    private static Icon makeIcon(String path) {
    	URL url = Icons.class.getResource(path);
    	Icon icon = new ImageIcon(url);
    	return icon;
    }
}

class Square extends JButton {
	private int xPosition;
	private int yPosition;

    // get position
	public int getXPosition() {
		return xPosition;
	}
	public int getYPosition() {
		return yPosition;
	}
	// set position (Override from JButton)
	public void setLocation(int x,int y) {
		this.xPosition=x;
		this.yPosition=y;
		super.setLocation(x*151,y*151);
	}

	// constructor - set location, default with Icon WATER
	public Square (int x,int y) {
		xPosition=x;
		yPosition=y;
		this.setIcon(Icons.WATER);
		this.setBorder(null);
		this.setSize(150, 150);
		this.setLocation(x, y );
	}
}
	// pad class for creating a pad
class Pad extends Square{
	public Pad(int x, int y) {
		super(x,y);
		this.setIcon(Icons.PAD);
	}
}
