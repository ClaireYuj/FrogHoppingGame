package hoppers;


class Frog extends Square{
	// the condition of a frog
	private boolean player;
	private boolean selected;
	// Can it move -- this method is not completed
	public boolean isMovable(Board b,Square otherPad,Pad selectedPad) {
	if(otherPad instanceof Pad) {
		return false;
	}
	if(otherPad instanceof Frog)//only if the object is a frog,it is movable
	{

		/*
		 * make sure the frog is selected
		 * use the position to judge if there is a frog between the current position of frog and destination
		 * only when the difference between the current distance and target is 0,-2or 2,the frog can move
		 */
		if(((b.findCharacter((selectedPad.getXPosition()+otherPad.getXPosition())/2,
				(selectedPad.getYPosition()+otherPad.getYPosition())/2)) instanceof Frog)&&
				(selectedPad.getXPosition()-otherPad.getXPosition())%2==0)
		{
			int dx=Math.abs((selectedPad.getXPosition()-otherPad.getXPosition()));
			int dy=Math.abs((selectedPad.getYPosition()-otherPad.getYPosition()));
			if(dx==4&&dy==4)
			{
				return false;
			}
			//the red-frog cannot be removed
			Frog midfrog=(Frog)(b.findCharacter((selectedPad.getXPosition()+otherPad.getXPosition())/2,
				(selectedPad.getYPosition()+otherPad.getYPosition())/2));
			if(!midfrog.isPlayer())
				return true;
		}
	}
		return false;
	}
	// is it a player?
	public boolean isPlayer() {
		return player;
	}
	public boolean getSelected() {
		return selected;
	}
	// set the select condition of the frog
	public void setSelect(boolean selected) {
		this.selected=selected;
		if(selected) {
			if(player) {
				this.setIcon(Icons.REDFROG2);}
			else{
				this.setIcon(Icons.GREENFROG2);}
			}
		if(!selected) {
			if(player) {
				this.setIcon(Icons.REDFROG);}
			else{
				this.setIcon(Icons.GREENFROG);}
			}	
		}

	//change the position to another pad
	public void moveTo(Pad otherPad) {
			int newX=otherPad.getXPosition();
			int newY=otherPad.getYPosition();
			otherPad.setLocation(this.getXPosition(),this.getYPosition());
			this.setLocation(newX,newY);
			this.setSelect(false);
	}

	// Constructor - location and player or not 
	public Frog(int x, int y, boolean isPlayer) {
		super(x,y);
		this.player=isPlayer;
		this.selected=false;
		if(isPlayer()) {
			setIcon(Icons.REDFROG);}
		else {
			setIcon(Icons.GREENFROG);}
	}
	
}
