package remoteTesting.dockervalidation;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class chromeTest1 {
	
	@BeforeTest
	public void startDocker_scale() throws IOException, InterruptedException {
		
		File fi = new File("outputlog.txt");
		if(fi.delete()) {
			System.out.println("file deleted successfully");
		}
		
		createBat cb =new createBat();
		cb.createBat();
	}
	
	@AfterTest
	public void stopBat() throws IOException, InterruptedException {
		
		stopBat sb = new stopBat();
		sb.stopBatfile();
	}

@Test
public void cTest1() throws MalformedURLException {
	
		// TODO Auto-generated method stub

		DesiredCapabilities cap = DesiredCapabilities.chrome();
		URL u = new URL("http://localhost:4444/wd/hub");
		RemoteWebDriver driver = new RemoteWebDriver(u,cap);
		driver.get("https://google.com");
		System.out.println(driver.getTitle());
	}

}
