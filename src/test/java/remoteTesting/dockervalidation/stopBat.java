package remoteTesting.dockervalidation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.Test;

public class stopBat {

	
	public void stopBatfile() throws IOException, InterruptedException {

		boolean flag = false;
		Runtime runtime = Runtime.getRuntime();
		runtime.exec("cmd /c start dockerDown.bat");

		String f = "outputlog.txt";
	
		//Comment
		Calendar cal =Calendar.getInstance(); //currenttime increament to 45 second
		cal.add(Calendar.SECOND, 45);
		long stopnow = cal.getTimeInMillis();
		
		while(System.currentTimeMillis()<stopnow) {
			
			if(flag) {
				break;
			}
			
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String currentLine = reader.readLine();
		
		while (currentLine != null && !flag) {

			if (currentLine.contains("Shutdown complete"))

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
		

	}
}
