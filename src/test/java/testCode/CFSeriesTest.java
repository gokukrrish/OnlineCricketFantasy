package testCode;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import pageObjects.CFSeries;
import pageObjects.FirstPage;
public class CFSeriesTest extends testBase{
	public void currentSeriesDetails() throws IOException {
		 FirstPage.toClick('1');
		 FirstPage.toClick('3');
		 CFSeries CFSObj = new CFSeries(driver);
		 ELObj.toCreateSheet("Current & Future Series");
		 ELObj.rowCreation(0);
		  ELObj.cellFormatting("tj5oq");
		  ELObj.cellCreationAndWrite(0, "Current & Future Series");
		  ELObj.cellFormatting("rs");
		 int total = Integer.parseInt(CFSObj.toSendDetails('g',0).toString());
		 for(int i = 0;i<total ;i++){
			  CFSObj.toSendDetails('b',i);
			  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			  CFSObj.toSendDetails('a',0);
			  if(Integer.valueOf(CFSObj.toSendDetails('h',0).toString())==0)break;
			  if(i==0) ELObj.rowCreation(ELObj.lastRow());
			  else ELObj.rowCreation(ELObj.lastRow()+1);
			  ELObj.cellFormatting("tj");
			  ELObj.cellCreationAndWrite(0, CFSObj.toSendDetails('c',0).toString());
			  ELObj.cellFormatting("rstj");
			  ELObj.cellCreationAndWrite(1, CFSObj.toSendDetails('d',0).toString());
			  ELObj.cellFormatting("rs");
			  for(int j=0, rwNum = ELObj.lastRow()+1;j<Integer.valueOf(CFSObj.toSendDetails('h',0).toString());j++,rwNum++) {
				  ELObj.rowCreation(rwNum);
				  for(int k = 0; k<2 ; k++) {
					  if(k==0) {
						  ELObj.cellFormatting("t3");
						  ELObj.cellCreationAndWrite(k, CFSObj.toSendDetails('e',j).toString());
					  }
					  else {
						  ELObj.cellFormatting("ty");
						  ELObj.cellCreationAndWrite(k, CFSObj.toSendDetails('f',j).toString());
					  }
					  ELObj.cellFormatting("rs");
				  }
			  }
			  driver.navigate().back();
			  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			  driver.navigate().back();
			  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  }
		  ELObj.fileCreation();
	}
}
