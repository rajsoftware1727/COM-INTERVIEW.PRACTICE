import java.io.File;

public class File1 {
	
 	
	public static void main(String args[])
	{ 
 	}
	
	static void File2()
	{

		File f=new File("");;

		try
		{
			if(!(f.exists()))
			{
				throw new FileNotFoundException("file not found");
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e.getMessage()+" "+e);
		}
 	}

}
