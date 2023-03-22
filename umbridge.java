
public class umbridge {
	
	// initializing varibales
	int yPos;
	
	// initializing varibles in contructor
	public umbridge()
	{
		yPos = 10;
	}

	// moving umbridge
	public void move(int dir)
	{
		if(dir == 1)
		{
			yPos += 10;
		}
		else if(dir == -1)
		{
			yPos -= 10;
		}
	}
	// getting y poisition of umbridge
	public int getY()
	{
		return yPos;
	}
}
