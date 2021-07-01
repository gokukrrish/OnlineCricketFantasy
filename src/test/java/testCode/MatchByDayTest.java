package testCode;

import java.io.IOException;
import java.util.ArrayList;
import pageObjects.FirstPage;
import pageObjects.MatchByDay;
public class MatchByDayTest extends testBase{
	
	@SuppressWarnings("rawtypes")
	public void matchByDatDetails() throws IOException {
		  FirstPage.toClick('1');
		  MatchByDay MBDObj = new MatchByDay(driver);
		  ELObj.toCreateSheet("Matches By Day");
		  int size = Integer.valueOf(MBDObj.toSendDetails('a', 0).toString());
		  char flag = 'n', flag2 = 'y';
		  ELObj.rowCreation(0);
		  ELObj.cellFormatting("tj5oq");
		  ELObj.cellCreationAndWrite(0, "Match By Day");
		  ELObj.cellFormatting("rs");
		  for(int i = 0; i<size; i++) {
			  ArrayList aryLst = new ArrayList();
			  aryLst = MBDObj.toSendDetails('b',i);
			  if(i==0) {
				  ELObj.rowCreation(ELObj.lastRow());
				  ELObj.cellFormatting("tj");
				  ELObj.cellCreationAndWrite(0, aryLst.get(0).toString());
				  ELObj.cellFormatting("rst");
			  }
			  for(int j = 0,rwNum = ELObj.lastRow()+1; j<aryLst.size();j++,rwNum++) {
				  ELObj.rowCreation(rwNum);
				  if(i==0&&flag=='n') {
					  rwNum--;
					  flag = 'y';
					  continue;
				  }
				  if(j==0) {
					  ELObj.cellFormatting("tj");
					  ELObj.cellCreationAndWrite(0, aryLst.get(j).toString());
					  ELObj.cellFormatting("rst");
					  continue;
				  }
				  if(flag2 == 'y') {
					  for(int k =0;k<3;k++) {
						  ELObj.cellFormatting("t3");
						  ELObj.cellCreationAndWrite(k, aryLst.get(j).toString());
						  ELObj.cellFormatting("rs");
						  if(k!=2) j++;
					  }
					  flag2 = 'n';
				  }
				  else {
					  for(int k =1;k<3;k++) {
						  ELObj.cellCreationAndWrite(k, aryLst.get(j).toString());
						  if(k!=2) j++;
					  }
					  flag2 = 'y';
				  }
			  }
		  }
		  ELObj.fileCreation();
	}

}
