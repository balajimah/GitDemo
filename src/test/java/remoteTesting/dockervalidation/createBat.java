package remoteTesting.dockervalidation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.Test;

public class createBat {

	
	public void createBat() throws IOException, InterruptedException {

		boolean flag = false;
		Runtime runtime = Runtime.getRuntime();
		runtime.exec("cmd /c start dockerUp.bat");

		String f = "outputlog.txt";
	

		Calendar cal =Calendar.getInstance(); //currenttime increament to 45 second
		cal.add(Calendar.SECOND, 45);
		long stopnow = cal.getTimeInMillis();
		Thread.sleep(5000);
		
		while(System.currentTimeMillis()<stopnow) {
			
			if(flag) {
				break;
			}
			
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String currentLine = reader.readLine();
		
		while (currentLine != null && !flag) {

			if (currentLine.contains("Node has been added"))

			{
				System.out.println("found my text");
				flag = true;
				break;

			}
			currentLine=reader.readLine();
		}
		reader.close();
		}
		
		Assert.assertTrue(flag);
		runtime.exec("cmd /c start scale.bat");

		Thread.sleep(15000);

	}
}
