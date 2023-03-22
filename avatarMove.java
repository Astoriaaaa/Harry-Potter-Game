import javax.swing.ImageIcon;

public class avatarMove {
	
	private int xPos, yPos;
	private int panelHeight, panelWidth;
	
	public avatarMove(int width, int height)
	{
		xPos = 350;
		yPos = 650;
				
		panelHeight = height;
		panelWidth = width;
	}
	
	public int getX()
	{
		return xPos;
	}
	
	public int getY()
	{
		return yPos;
	}
	public void setX(int x)
	{
		xPos = x;
	}
	public void setY(int y)
	{
		yPos = y;
	}
	
	public void move(boolean up,boolean left,boolean right,boolean down )
	{
		if (left == true)
		{
			xPos -= 10;
		}
		if (right == true)
		{
			xPos += 10;
		}
		if (up == true)
		{
			yPos -= 10;
		}
		if (down == true)
		{
			yPos += 10;
		}
	}
	


}
