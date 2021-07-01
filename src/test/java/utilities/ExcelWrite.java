package utilities;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * This Class writes the product specification in excel file
 * 
 * @author Saravanan, Arumugam
 */

public class ExcelWrite {
	
	//"wbk" is an instance for Workbook interface
	XSSFWorkbook wbk =null;
	
	//"sht" is an instance for Sheet interface
	XSSFSheet sht = null;
	
	//"row" is an instance for Row interface
	XSSFRow row = null;
	
	//"cell" is an instance for Cell interface
	XSSFCell cell = null;
	
	//"out" is a instance for FileOutputStream class
	FileOutputStream out = null;
	
	//"style" is instance for XSSFCellStyle class
	XSSFCellStyle style =null;
	 
	//"font" is a instance for XSSFFont class
	XSSFFont font = null;
	 
	XSSFColor clr = null;
	 
	CreationHelper cHplr = null;
	XSSFHyperlink link = null;
	 
	/*
	 * The constructor for creating instance for excel workbook 2007 version
	 */
	public ExcelWrite() {
		wbk = new XSSFWorkbook();
		}

	/*
	 * Creates sheet in workbook with name received as a parameter
	 * 
	 * @param data - Sheet name
	 */
	public void toCreateSheet(String data) {
		sht = wbk.createSheet(data);
	}
	
	/*
	 * Creates the row in the sheet
	 * 
	 * @param i - row index
	 */
	public void rowCreation(int i,char c) {
		row = sht.createRow(i);
		if(c=='1')
			row.setHeight((short)600);
	}
	
	public void rowCreation(int i) {
		row = sht.createRow(i);
	}
	
	/*
	 * Creates cell and writes received data to that cell
	 * 
	 * @param i - cell index
	 * @param data - data to be written
	 */
	public void cellCreationAndWrite(int i, String data) {
		cell = row.createCell(i);
		cell.setCellValue(data);
		sht.autoSizeColumn(cell.getColumnIndex());
	}
	
	/*
	 * Returns last row of excel file
	 */
	public int lastRow() {
		return sht.getLastRowNum();
	}
	
	/*
	 * This method is for formatting the cell
	 * 
	 * @param sW1 - Required format
	 */
	public void cellFormatting(String sW1) {
		char[] sW2 = sW1.toCharArray();
		for(int i=0;i<sW2.length;i++)
			switch(sW2[i]) {
			case 'a': style.setAlignment(HorizontalAlignment.CENTER);; break;
			case 'b': style.setAlignment(HorizontalAlignment.RIGHT); break;
			case 'c': style.setAlignment(HorizontalAlignment.LEFT); break;
			case 'd': style.setFillPattern(FillPatternType.SOLID_FOREGROUND); break;
			case 'e': font.setFontHeightInPoints((short) 9); break;
			case 'f': font.setFontHeightInPoints((short) 10); break;
			case 'g': font.setFontHeightInPoints((short) 11); break;
			case 'h': font.setFontName("Segoe UI"); break;
			case 'i': font.setFontName("Calibri"); break;
			case 'j': font.setBold(true); break;
			case 'k': clr = new XSSFColor(new java.awt.Color(74,74,74)); break;
			case 'l': clr = new XSSFColor(new java.awt.Color(235,235,235)); break;
			case 'm': clr = new XSSFColor(new java.awt.Color(102,102,102)); break;
			case 'n': clr = new XSSFColor(new java.awt.Color(255,255,255)); break;
			case 'o': clr = new XSSFColor(new java.awt.Color(24,102,219)); break;
			case 'p': style.setFillForegroundColor(clr); break;
			case 'q': font.setColor(clr); break;
			case 'r': style.setFont(font); break;
			case 's': cell.setCellStyle(style); break;
			case 't': cell = null; clr = null; style = wbk.createCellStyle(); font = wbk.createFont();break;
			case 'u': font.setBold(false); break;
			case 'v': style.setVerticalAlignment(VerticalAlignment.CENTER); break;
			case 'w': style.setBorderBottom(BorderStyle.THIN); break;
			case 'x': cHplr = null; link = null; cHplr = wbk.getCreationHelper();
					  link = (XSSFHyperlink)cHplr.createHyperlink(HyperlinkType.URL); break;
			case 'y': style.setWrapText(true); break;
			case 'z': style.setBorderTop(BorderStyle.THIN); break;
			case '1': sht.autoSizeColumn(cell.getColumnIndex()); break;
			case '2': row.setHeightInPoints((2 * sht.getDefaultRowHeightInPoints())); break;
			case '3': font.setItalic(true); break;
			case '4': font.setUnderline(XSSFFont.U_SINGLE); break;
			case '5': font.setFontHeightInPoints((short) 25); break;
			}
		}

	/*
	 * To merge the required cells
	 * 
	 * @param rFrm - Starting row number
	 * @param rTo - Ending row number
	 * @param cFrm - Starting column number
	 * @param cTo - Ending column number
	 */
	public void mergeCells(int rFrm, int rTo, int cFrm, int cTo) {
		sht.addMergedRegion(new CellRangeAddress(rFrm,rTo,cFrm,cTo));
	}
	
	/*
	 * To set link to the cell current data
	 * 
	 * @param URL - URL address
	 */
	public void toSetLink(String URL) {
		link.setAddress(URL);
		cell.setHyperlink(link);
	}
	
	/*
	 * To write the excel file
	 */
	public void fileCreation() throws IOException {
		//out = new FileOutputStream(new File("G:/Test_Data/CircBuzz.xlsx")); 
		out = new FileOutputStream(new File("D:/Gmail_Test_Data/CircBuzz.xlsx"));
		wbk.write(out);
		out.close();
	}
	
}
