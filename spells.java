import javax.swing.ImageIcon;

public class spells
{

	private ImageIcon img1, img2, img3, img4, img5;
	
	public spells()
	{
		img1 = new ImageIcon("src/spell1.png");
		img2 = new ImageIcon("src/spell2.png");
		img3 = new ImageIcon("src/Spell 3.png");
		img4 = new ImageIcon("src/spell4.png");
		img5 = new ImageIcon("src/spell4.png");
		
	}
	
	public ImageIcon getimg1()
	{
		return img1;
	}

	public ImageIcon getimg2()
	{
		return img2;
	}
	
	public ImageIcon getimg3()
	{
		return img3;
	}
	
	public ImageIcon getimg4()
	{
		return img4;
	}
	
	public ImageIcon getimg5()
	{
		return img5;
	}
	
}
