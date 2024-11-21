package INTERVIEW_JAVA;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Generic_Excel_Read_And_Write {

	static FileInputStream fi;
	static XSSFWorkbook wb;
	static XSSFSheet sheet;
	static XSSFRow currentRow;
	static XSSFCell currentCell;

	public static int getTotalRowCount(String path,String sheetName) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		sheet=wb.getSheet(sheetName);
		return sheet.getLastRowNum();
	}

	public static int getTotalCellCount(String path,String sheetName,int RowNo) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		sheet=wb.getSheet(sheetName);
		return sheet.getRow(RowNo).getLastCellNum();
	}

	public static Object getCellData(String path,String sheetName,int RowNo,int cellNo) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		sheet=wb.getSheet(sheetName);
		currentRow=sheet.getRow(RowNo);
	    currentCell=currentRow.getCell(cellNo);
		DataFormatter df=new DataFormatter();
		wb.close();
		fi.close();
		return df.formatCellValue(currentCell).trim();
	}

}
