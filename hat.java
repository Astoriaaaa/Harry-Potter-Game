import javax.swing.ImageIcon;

public class hat {
	
	private ImageIcon hat1, hat2, hat3;
	private int xPos1, yPos1, xPos2, yPos2, xPos3, yPos3;
	private int dir1, dir2, dir3;
	
	public hat()
	{
		hat1 = new ImageIcon("src/sortingHat.png");
		hat2 = new ImageIcon("src/sortingHat.png");
		hat3 = new ImageIcon("src/sortingHat.png");
		
		xPos1 = 525;
		yPos1 = 560;
		
		xPos2 = 150;
		yPos2 = 480;
		
		xPos3 = 525;
		yPos3 = 380;
		
		dir1 = 1;
		dir2 = 1;
		dir3 = 1;
	}

	public ImageIcon getHat1()
	{
		return hat1;
	}
	public ImageIcon getHat2()
	{
		return hat2;
	}
	public ImageIcon getHat3()
	{
		return hat3;
	}
	public int getX1()
	{
		return xPos1;
	}
	public int getY1()
	{
		return yPos1;
	}
	public int getX2()
	{
		return xPos2;
	}
	public int getY2()
	{
		return yPos2;
	}
	public int getX3()
	{
		return xPos3;
	}
	public int getY3()
	{
		return yPos3;
	}
	public void move(int dir1, int dir2, int dir3)
	{
		if (dir1 == 1)
		{
			xPos1 -= 40;
		}
		else if (dir1 == -1)
		{
			xPos1 += 40;
		}
		if (dir2 == -1)
		{
			xPos2 -= 10;
		}
		else if (dir2 == 1)
		{
			xPos2 += 10;
		}
		if (dir3 == 1)
		{
			xPos3 -= 20;
		}
		else if (dir3 == -1)
		{
			xPos3 += 20;
		}

	}
	public void setDir(int direction)
	{
		dir1 = direction;
	}
	public void setX1(int x1)
	{
		xPos1 = x1;
	}
	public void setY1(int y1)
	{
		yPos1 = y1;
	}
	public void setX2(int x2)
	{
		xPos2 = x2;
	}
	public void setY2(int y2)
	{
		yPos2 = y2;
	}
	public void setX3(int x3)
	{
		xPos3 = x3;
	}
	public void setY3(int y3)
	{
		yPos3 = y3;
	}

}
