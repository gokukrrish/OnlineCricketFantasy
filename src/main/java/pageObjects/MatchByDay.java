package pageObjects;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/*
 * This class is for accessing "Matches By Day" page
 * 
 * @author Saravanan, Arumgam
 */
public class MatchByDay{
	//"driver" is private instance for WebDriver Class
	private WebDriver driver = null;
				
	//"matchDate" is public instance for match date with non-Saturday matches
	@FindBys(@FindBy(how=How.XPATH, using="//div[@class='cb-lv-grn-strip text-bold'][not(contains(text(),'SAT'))]"))
	public List<WebElement> matchDate;
	
	/*
	 * The constructor  "Current & Future Matches" does initialize the webdriver and
	 * action class objects, initialize the PageFactory for this
	 * web page
	 * 
	 * @param driver - Receives webdriver object
	 */
	public MatchByDay(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/*
	 *To get and send details of the "Matches" in each day
	 * 
	 *@param j - Index for WebElement List
	 * 
	 *@return <Any> - Returns value based on value it processed
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <Any> Any toSendDetails(char i,int j) {
		
		switch(i) {
		case 'a':{
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			
			for(;;) {//Infinite loop executes till finding the last'WED, MAR 13 2019' webelement
				
				
				js.executeScript("window.scrollBy(0,500)");
				driver.manage().timeouts().implicitlyWait(750, TimeUnit.MILLISECONDS);
				//Adding all non-Saturday matches to the list
				matchDate.addAll(driver.findElements(By.xpath("//div[@class='cb-lv-grn-strip text-bold']"
						+ "[not(contains(text(),'SAT'))]")));
				try {
					if(driver.findElement(By.xpath("//div[@class='cb-lv-grn-strip text-bold']"
							+ "[text()='WED, MAR 13 2019']")).isEnabled())
						break;//Infinite loop breaks
				}
				catch(NoSuchElementException e) {
					continue;
				}
			}
			return (Any)((Integer)matchDate.size());
			
		}
		case 'b': {
			
				ArrayList aryLst = new ArrayList();
				int a = 2;
				String dateMch = matchDate.get(j).getText();
				aryLst.add(dateMch);//Adds "Date" to the list
				for(;;) {
					try {
							//Adds "Tour Name" to the list
							aryLst.add((driver.findElement(By.xpath("//div[@class='cb-lv-grn-strip text-bold']"
										+ "[text()='"+dateMch+"']/parent::div/child::div["+a+"]/div[1]")).getText()));
							//Adds "Team Name" to the list
							aryLst.add((driver.findElement(By.xpath("//div[@class='cb-lv-grn-strip text-bold']"
									+ "[text()='"+dateMch+"']/parent::div/child::div["+a+"]/div[2]/div/a")).getText()));
							//Adds "Time" to the list
							aryLst.add((driver.findElement(By.xpath("//div[@class='cb-lv-grn-strip text-bold']"
									+ "[text()='"+dateMch+"']/parent::div/child::div["+a+"]/div[2]/div[2]/span")).getText()));
							//Adds "Venue" to the list
							aryLst.add((driver.findElement(By.xpath("//div[@class='cb-lv-grn-strip text-bold']"
									+ "[text()='"+dateMch+"']/parent::div/child::div["+a+"]/div[2]/div/div"
											+ "[@class='cb-font-12 text-gray cb-ovr-flo']")).getText()));
							//Adds "Local Time" to the list
							aryLst.add((driver.findElement(By.xpath("//div[@class='cb-lv-grn-strip text-bold']"
									+ "[text()='"+dateMch+"']/parent::div/child::div["+a+"]/div[2]/div[2]/div")).getText()));	
							a++;
					}
					catch(NoSuchElementException e) {
						break;
					}
				}
				return  (Any)((ArrayList)aryLst);
				
				
			}
		}
		return null;
	}
}
