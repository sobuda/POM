package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	private static final String TEST_DATA_XLS_PATH = "./src/test/resources/TestData/OpenCartTestData.xlsx";
	private static Workbook book;
	private static Sheet sheet;
	
	public static Object[][] getTestData(String sheetName) {
		Object data[][] = null;
		try {
			FileInputStream finp = new FileInputStream(TEST_DATA_XLS_PATH);
			book = WorkbookFactory.create(finp);
			sheet = book.getSheet(sheetName);
			int row = sheet.getLastRowNum();
			int col = sheet.getRow(0).getLastCellNum();
//			System.out.println("row= "+row+"      Col ="+col);
			data = new Object[row][col];
		
			for(int i=0;i<row;i++) {
				for(int j = 0;j<col;j++) {
					data[i][j] = sheet.getRow(i+1).getCell(j).toString();				}
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	
	
}
