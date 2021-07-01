package pageObjects;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

/*
 * This class is for accessing "Series Archive" page
 * 
 * @author Saravanan, Arumgam
 */
public class SeriesArchive {
	//"driver" is private instance for WebDriver Class
	private WebDriver driver = null;
	
	//"builder" is an instance for Actions Class
	Actions builder = null; 
	
	/*
	 * The constructor  "Series Archive" does initialize the webdriver and
	 * action class objects, initialize the PageFactory for this
	 * web page
	 * 
	 * @param driver - Receives webdriver object
	 */
	public SeriesArchive(WebDriver driver) {
		this.driver = driver;
		builder = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	/*
	 *To get and send details of the "Series Archive"
	 * 
	 *@param season - Index for season
	 * 
	 *@return HashMap- Returns value based on value it processed
	 */
	public HashMap<Integer, String> toSendDetails(String season) {
		HashMap<Integer, String> smap = new HashMap<Integer, String>();
		int key = 0;
		List<WebElement> eachSeason = driver.findElements(By.xpath("//div[@class='cb-font-14 text-gray cb-yr-rnge']"
				+ "[text()='"+season+"']/following-sibling::div[1]/a"));
		String years[] = new String[eachSeason.size()];
		for(int i =0;i<eachSeason.size();i++)
			years[i] =  eachSeason.get(i).getText();
		for(int i =0;i<years.length;i++) {
			builder.moveToElement(driver.findElement(By.xpath("//div[@class='cb-yr-tmline']"
					+ "/a[text()='"+years[i]+"']"))).click().build().perform();
			
			//Adding all "International" matches to the list
		List<WebElement> intrnlDetails = driver.findElements(By.xpath("//h2[@class='cb-col-16 cb-col "
				+ "text-bold cb-srs-cat cb-lv-scr-mtch-hdr']"
					+ "[text()='International']/following-sibling::div[1]/div"));
			for(int j=0;j<intrnlDetails.size()+1;j++) {
				if(j==0) {
					smap.put(key++,years[i]);
					continue;
				}
				if(intrnlDetails.get(j-1).getText().contains("Oman")) {//Skipping "Oman" Matches
					key--;
					continue;
				}
				smap.put(key++,intrnlDetails.get(j-1).getText());
			}
		}
		return smap;
	}
}
