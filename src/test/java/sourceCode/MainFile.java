package sourceCode;

import org.testng.annotations.Test;


import testCode.SeriesArchiveTest;
import testCode.CFSeriesTest;
import testCode.MatchByDayTest;
import testCode.CurrentMatchesTest;
import utilities.ExcelWrite;

import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import testCode.testBase;
/*
 * This writes "Current & Future Matches", "Current Matches", "Matches By Day" and 
 * "Series Archive" in excel file
 * 
 * @author Saravanan, Arumugam

 */
public class MainFile {
	//"driver" is an instance for WebDriver Class
	static WebDriver driver = null;
	
	
	//"ELObj" is an instance for ExcelWirte Class
	static ExcelWrite ELObj = null;
	
	/*
	 * Before Test is for set base URL from circBuzz.xml
	 * 
	 * @param bURL - Gets the base URL from @Parameters
	 */
	@BeforeTest
	@Parameters({"baseURL"})
	public void beforeClass(String bURL){
		new testBase().setUP(bURL);;
	}
	
	/*
	 *Test method is for write "Current & Future Series" Details from base URL  
	 */
	@Test(priority = 0)
	public void curfutSeries() throws IOException {
		new CFSeriesTest().currentSeriesDetails();
	}
	
	/*
	 * Test method is for write "Matches By Day" Details from base URL  
	 */
	@Test(priority = 1)
	public void matchByDay() throws IOException, NumberFormatException, InterruptedException {
		new MatchByDayTest().matchByDatDetails();
	}
	
	 /*
	  * The "PageObjects" is a data provider, which provides 
	  * season details to "seriesArchive" test method
	  * 
	  * @return Object[][] - Consists of Page Objects and integers
	  */
	 @DataProvider(name = "allPageObjects")
	 public Object[][] PageObjects() {
		  return new Object[][] 
				 {{"2011-2019"},{"2001-2010"},{"1991-2000"},{"1981-1990"}};
				  //{{"2011-2019"}};
	 }
	 
	/*
	 *Test method is for write "Series Archive" Details from base URL
	 *
	 *@param  season - Receives season details from dataprovider
	 */
	@Test(priority = 2,dataProvider = "allPageObjects")
	public void seriesArchive(String season) throws IOException {
		new SeriesArchiveTest().seriesArchiveDetails(season);
	}
	
	/*
	 * Test method is for write "Live Score" Details from base URL
	 * 
	 * @param cMatch - Gets current match details from circBuzz.xml file
	 */
	@Test(priority = 3)
	@Parameters({"curMatch"})
	public void liveMatch(String cMatch) throws IOException, InterruptedException{
		new CurrentMatchesTest().curMatches(cMatch);
	}
	
  /*
   * The "toCloseBrowser" is a After Class method,
   * used to close browser
   */
  @AfterTest
  public void afterTest() {
	  new testBase().toCloseWD();
  }

}
