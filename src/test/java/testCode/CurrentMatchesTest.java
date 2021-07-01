package testCode;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import pageObjects.CurrentMatches;
import pageObjects.FirstPage;
import sourceCode.MainFile;

public class CurrentMatchesTest extends testBase {
	public void curMatches(String cMatch) throws IOException, InterruptedException {
		FirstPage.toClick('1');
		FirstPage.toClick('2');
		new CurrentMatchesTest().liveUpdates(driver.getCurrentUrl(),cMatch);
	}
	
	/*
	 * This class method writes live score in excel file everytime
	 * with 10 seconds interval
	 * 
	 *  @param curURL - Gets current URL
	 *  @param cMatch - Gets current Match Details
	 */
	@SuppressWarnings("unchecked")
	public static void liveUpdates(String curURL, String cMatch) throws IOException {
		int rwNum = 0;
		driver.get(curURL);
		CurrentMatches CMObj = new CurrentMatches(driver, cMatch);
		ELObj.toCreateSheet("Live_Score");
		ELObj.rowCreation(rwNum++);
		ELObj.cellFormatting("tijga");
		ELObj.cellCreationAndWrite(0, CMObj.toSendDetails('a').toString());
		ELObj.cellFormatting("rs");
		ELObj.mergeCells(rwNum-1, rwNum-1, 0, 10);
		String[] frTitle = {"Series","Venue","Date: "};
		ELObj.rowCreation(rwNum++);
		for(int i =0,k=0;i<2;i++) {
			String data = frTitle[i]+": "+ CMObj.toSendDetails('a', i);
			ELObj.cellFormatting("tiju4");
			ELObj.cellCreationAndWrite( k,data);
			ELObj.cellFormatting("rsx");
			ELObj.toSetLink(CMObj.toSendDetails('b', i));
			if(i==0) {
				ELObj.mergeCells(rwNum-1, rwNum-1, k, 1); k+=2;
			}
			else 
				ELObj.mergeCells(rwNum-1, rwNum-1, k,9);
		}
		for(int i =0;i<3;i++)
			frTitle[2] += CMObj.toSendDetails('c', i);
		ELObj.cellCreationAndWrite(10,frTitle[2]);
		//ELObj.mergeCells(rwNum-1, rwNum-1, 10,11 );
		ELObj.rowCreation(rwNum++);
		ELObj.cellFormatting("t");
		String status = CMObj.toSendDetails('b').toString();
		System.out.println(status);
		ELObj.cellCreationAndWrite(0, status);
		ELObj.cellFormatting("hgoqrs");
		ELObj.rowCreation(rwNum++,'1');
		ELObj.cellFormatting("t");
		for(int j=0;j<7;j++) {
			if(j==0) 
				ELObj.cellCreationAndWrite(j,CMObj.toSendDetails('c').toString());
			else if(j==6)
				ELObj.cellCreationAndWrite(j,CMObj.toSendDetails('d').toString());
			else
				ELObj.cellCreationAndWrite(j,"");
			if(j==0||j==6) {
				ELObj.cellFormatting("vhrgnq");
			}
			ELObj.cellFormatting("kdps");
		}
		List<WebElement> BtmnNme = null;
		try {
			BtmnNme = CMObj.toSendDetails('e');
			BtmnNme.addAll((Collection<? extends WebElement>) CMObj.toSendDetails('f'));
		}
		catch(NoSuchElementException e){}
		char flag1 = 'y';
		char flag3 = 'n';
		String ColNme[] = {"Batsman","","R","B","4s","6s","SR","Extra","Total","Fall of Wickets","Bolwer","O","M","R","W","NB","WD","ECO"};
		String plyrName = null;
		String plyrURL = null;
		ELObj.cellFormatting("t");
		for(int i= 0;i<BtmnNme.size()+1;i++) {
			ELObj.rowCreation(rwNum++);
			char flag2 = 'y';
			if(flag1!='y') {
				try {
					plyrName = CMObj.toSendDetails('a',BtmnNme.get(i-1));
					plyrURL =  CMObj.toSendDetails('b',BtmnNme.get(i-1));
				}
				catch(NoSuchElementException e){	
					plyrName = CMObj.toSendDetails('c',BtmnNme.get(i-1));
					plyrURL = CMObj.toSendDetails('b',BtmnNme.get(i-1));
					   flag3 = 'y';
				}
			}
			for(int j =0; j<7; j++) {
				if(j==0) ELObj.cellFormatting("c");
				else ELObj.cellFormatting("b");
				if(j==2) ELObj.cellFormatting("j");
				else ELObj.cellFormatting("u");
				if(flag1=='y') {
					ELObj.cellFormatting("ig");
					if(j!=1) {
						ELObj.cellFormatting("mqr");
					}
					if(j==0) ELObj.cellFormatting("j");
					ELObj.cellCreationAndWrite(j,ColNme[j]);
					ELObj.cellFormatting("ldps");
				}
				else {
					ELObj.cellFormatting("hf");
					if(flag2=='y') {
						if(j==0) {
							ELObj.cellFormatting("igoqx");
							if(flag3=='y')ELObj.cellFormatting("j");
							ELObj.cellCreationAndWrite(j++,plyrName);
							ELObj.toSetLink(plyrURL);
							ELObj.cellFormatting("rswt");
						}
						if(j==1) {
							ELObj.cellFormatting("hf");
							try {
								ELObj.cellCreationAndWrite(j,CMObj.toSendDetails('a',plyrName));
							}
							catch(NoSuchElementException e){	
								ELObj.cellCreationAndWrite(j,CMObj.toSendDetails('b',plyrName));
							}
						}
							
						flag2 = 'n';
					}
					else {
						try {
							ELObj.cellCreationAndWrite(j,CMObj.toSendDetails('a',plyrName,j));
						}
						catch(NoSuchElementException e){	
							ELObj.cellCreationAndWrite(j,CMObj.toSendDetails('b',plyrName,j));
						}
					}
					ELObj.cellFormatting("rs");
				}
				if(j==6) flag1='n';
				ELObj.cellFormatting("wt");
			}
		}

		int p = 7;
		for(int i =0;i<3;i++) {
			ELObj.rowCreation(rwNum++);
			for(int j=0;j<7;j++) {
				if(j==0&&(i==0||i==1)) {
					ELObj.cellFormatting("hf");
					ELObj.cellCreationAndWrite(j,ColNme[p]);
					ELObj.cellFormatting("rswt");
					if(i==1) flag1 = 'n';
				}
				else if(j==2&&(i==0||i==1)) {
					if(p==7) {
						ELObj.cellFormatting("hfjb");
						ELObj.cellCreationAndWrite(j,CMObj.toSendDetails('g').toString());
						ELObj.cellFormatting("rswt");
					}
					else {
						ELObj.cellFormatting("hfjb");
						ELObj.cellCreationAndWrite(j,CMObj.toSendDetails('h').toString());
						ELObj.cellFormatting("rswt");
					}
				}
				else if(j==3&&(i==0||i==1)) {
					if(p==7) {
						ELObj.mergeCells(rwNum-1, rwNum-1, j,6);
						ELObj.cellFormatting("hfuc");
						ELObj.cellCreationAndWrite(j,CMObj.toSendDetails('i').toString());
						ELObj.cellFormatting("1ryswt");
						p++;
					}
					else {
						ELObj.mergeCells(rwNum-1, rwNum-1, j,6);
						ELObj.cellFormatting("hfc");
						ELObj.cellCreationAndWrite(j,CMObj.toSendDetails('j').toString());
						ELObj.cellFormatting("1ryswt");
					}
				}
				else if((j==0||j==1)&&i==2) {
					try {
						if(j==0) {
							ELObj.cellFormatting("hf");
							try {
								ELObj.cellCreationAndWrite(j,CMObj.toSendDetails('k').toString());
								ELObj.cellFormatting("rswt");
							}
							catch(NoSuchElementException e){
								rwNum--;
							}
							catch(NullPointerException e) {}
						}
						else {
							ELObj.cellFormatting("cfhoq");
							plyrName = "";
							for(int k=1;;k++) {
								try {
								plyrName += CMObj.toSendDetails('d',k);
//								System.out.println(driver.findElement(By.xpath("//div[@id='"+innings+"']/div/div[@class='cb-col cb-col-100 cb-scrd-itms']"
//										+ "/div[@class='cb-col-73 cb-col']/a["+k+"]")).getAttribute("href"));
								}
								catch(NoSuchElementException e){
									break;
								}
							}
							ELObj.cellCreationAndWrite(j,plyrName);
							ELObj.cellFormatting("12ryswt");
						}
					}
					catch(NoSuchElementException e){}
				}
				else {
					ELObj.cellCreationAndWrite(j,"");
					ELObj.cellFormatting("wst");
				}
			}
		}
		try {
			ELObj.mergeCells(rwNum-1, rwNum-1, 1,6);
		}
		catch(IllegalStateException e) {}
		for(int i = 0;i<2;i++) {
			ELObj.rowCreation(rwNum++);
			for(int j = 0;j<7;j++) {
				if(i==1) break;
				if(j==0) {
					ELObj.cellFormatting("hejldpmq");
					ELObj.cellCreationAndWrite(j,ColNme[++p]);
					ELObj.cellFormatting("rswt");
				}
				else {
					ELObj.cellCreationAndWrite(j,"");
					ELObj.cellFormatting("ldpwst");
				}
			}
			if(i==1) {
				ELObj.mergeCells(rwNum-1, rwNum+1, 0,6);
				ELObj.cellCreationAndWrite(0, CMObj.toSendDetails('l').toString());
				ELObj.cellFormatting("yst");
				
			}
		}
		rwNum +=2;
		List<WebElement> BwlrNme =  CMObj.toSendDetails('m');
		for(int i = 0;i<BwlrNme.size()+1;i++) {
			ELObj.rowCreation(rwNum++);
			if(i!=0) {
				plyrName = CMObj.toSendDetails('d',BwlrNme.get(i-1));
				plyrURL = CMObj.toSendDetails('b',BwlrNme.get(i-1));
			}
			for(int j=0;j<8;j++) {
				if(j==0) ELObj.cellFormatting("c");
				if(j==4) ELObj.cellFormatting("j");
				if(i==0) {
					if(j==0||j==4) ELObj.cellFormatting("j");
					else ELObj.cellFormatting("u");
					if(j!=0) ELObj.cellFormatting("b");
					ELObj.cellFormatting("hemqr");
					ELObj.cellCreationAndWrite(j,ColNme[++p]);
					ELObj.cellFormatting("lpdzwst");
				}
				else {
					if(j==0) {
						ELObj.cellFormatting("igoqx");
						ELObj.cellCreationAndWrite(j,plyrName);
						ELObj.toSetLink(plyrURL);
						ELObj.cellFormatting("rswt");
					}
					else {
						ELObj.cellFormatting("bhe");
						ELObj.cellCreationAndWrite(j,CMObj.toSendDetails('c',plyrName,j));
						ELObj.cellFormatting("rswt");
					}
				}
			}
		}
		ELObj.fileCreation();
		
		//Checking Live Match status to exit from from recursive function
		if(status.contains("won")||status.contains("Match Drawn")){
			return;
		}
		else {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			CurrentMatchesTest.liveUpdates(curURL,cMatch);
		}
		
	}
}
