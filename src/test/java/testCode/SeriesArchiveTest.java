package testCode;
import java.io.IOException;
import java.util.HashMap;

import pageObjects.FirstPage;
import pageObjects.SeriesArchive;
public class SeriesArchiveTest extends testBase{
	public void seriesArchiveDetails(String season) throws IOException {
		FirstPage.toClick('1');
		FirstPage.toClick('4');
		 char flag = 'n';
		 ELObj.toCreateSheet(season);
		 SeriesArchive SAObj =new SeriesArchive(driver);
		 HashMap<Integer, String> smap = new HashMap<Integer, String>();
		 smap = SAObj.toSendDetails(season);
		 System.out.println("size: "+smap.size());
		 for(int i =0; i<smap.size();i++) {
			 ELObj.rowCreation(i);
			 
			 if(smap.get(i).length()==4){
				ELObj.cellFormatting("tj");
				ELObj.cellCreationAndWrite(0,smap.get(i));
				ELObj.cellFormatting("rs");
				flag = 'y';
			 }
			 else {
				 if(flag=='y') {
					 ELObj.cellFormatting("t3");
					 ELObj.cellCreationAndWrite(0, "International");
					 ELObj.cellFormatting("rs");
					 ELObj.cellCreationAndWrite(1, smap.get(i));
					 flag = 'n';
				 }
				 else
					 ELObj.cellCreationAndWrite(1, smap.get(i));
			 }
		 }
		 ELObj.fileCreation();
	}
}
