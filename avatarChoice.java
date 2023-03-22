import javax.swing.ImageIcon;

public class avatarChoice {
	
	private int choice, realchoice;
	private ImageIcon griffGirl, griffGuy, slythGirl, slythGuy, huffGirl, huffGuy, ravenGirl, ravenGuy, plainGuy, plainGirl;

	public avatarChoice() {
		
		choice = 0;
		realchoice = 0;
		plainGuy = new ImageIcon ("src/plainGuy.png");
		plainGirl = new ImageIcon("src/plainGirl.png");
		griffGirl = new ImageIcon("src/griffGirl2.png");
		griffGuy = new ImageIcon("src/griffGuy2.png");
		slythGirl = new ImageIcon("src/slythGirl2.png");
		slythGuy = new ImageIcon("src/slythGuy2.png");
		ravenGirl = new ImageIcon("src/slythGirl2.png");
		ravenGuy = new ImageIcon("src/ravenGuy2.png");
		huffGuy = new ImageIcon("src/huffGuy2.png");
		huffGirl = new ImageIcon("src/huffGuy2.png");
	}
	
	public ImageIcon getImage(int chocho) 
	{
		choice = chocho;
		
		if (chocho == 0)
		{
			return plainGuy;
		}
		else
		{
			return plainGirl;
		}
	}
	
	public ImageIcon getRealImage(int recho)
	{
		realchoice = recho;
		
		if (recho == 0 && choice == 0)
		{
			return griffGuy;
		}
		else if (recho == 0 && choice > 0)
		{
			return griffGirl;
		}
		else if (recho == 1 && choice == 0)
		{
			return slythGuy;
		}
		else if (recho == 1 && choice > 0)
		{
			return slythGirl;
		}
		else if (recho == 2 && choice == 0)
		{
			return ravenGuy;
		}
		else if (recho == 2 && choice > 0)
		{
			return ravenGirl;
		}
		else if (recho == 3 && choice == 0)
		{
			return huffGuy;
		}
		else
		{
			return huffGirl;
		}
	}

}
