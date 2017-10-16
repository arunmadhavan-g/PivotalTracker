package ford.pivotaltracker.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Writer {

	private WritableWorkbook workBook;
	private static final String[] headers = {"Total", "Accepted", "In-Progress", "UnStarted", "Iceboxed"};
	
	public Writer(String fileLocation) throws FileNotFoundException, IOException {
		workBook =  Workbook.createWorkbook(new FileOutputStream(new File(fileLocation)));
	}

	int row = 2; 
	int sheetNo = 0;
	
	public void writeReport(String projectId, List<Report> reportList) throws RowsExceededException, WriteException{
		row = 2;
		WritableSheet sheet = workBook.createSheet(projectId, sheetNo);
		printHeaders(sheet);
		for(Report report: reportList)
			printReportLine(sheet,report);
		
		sheetNo++;
	}

	public void flush() throws IOException, WriteException {
		workBook.write();
		workBook.close();
	}

	private void printReportLine(WritableSheet sheet, Report report) throws RowsExceededException, WriteException {
		sheet.addCell(new Label( 0, row, report.getEpicName()));
		sheet.addCell(new Number(1, row, report.getTotalPoints()));
		sheet.addCell(new Number(2, row, report.getTotal()));
		sheet.addCell(new Number(3, row, report.getAcceptedPoints()));
		sheet.addCell(new Number(4, row, report.getAccepted()));
		sheet.addCell(new Number(5, row, report.getInProgressPoints()));
		sheet.addCell(new Number(6, row, report.getInProgress()));
		sheet.addCell(new Number(7, row, report.getUnstartedPoints()));
		sheet.addCell(new Number(8, row, report.getUnstarted()));
		sheet.addCell(new Label(9, row, ""));
		sheet.addCell(new Number(10, row, report.getIceboxed()));
		row++;
	}
	

	private void printHeaders(WritableSheet sheet) throws RowsExceededException, WriteException {
		Label epicLabel = new Label(0, 0, "Epics");
		sheet.addCell(epicLabel);
		addToSheetLabels(sheet);
		addToSheetPointsAndStories(sheet);
		
	}

	private void addToSheetPointsAndStories(WritableSheet sheet) throws RowsExceededException, WriteException {
		int col = 1;
		for(int i=0;i<headers.length;i++) {
			Label point = new Label(col++, 1, "Point");
			sheet.addCell(point);
			
			Label stories = new Label(col++, 1, "Stories");
			sheet.addCell(stories);
		}
	}

	private void addToSheetLabels(WritableSheet sheet) throws RowsExceededException, WriteException {
		int col = 1;
		for(String header: headers) {
			Label lbl = new Label(col++, 0, header);
			sheet.addCell(lbl);
			col++;
		}
	}


}
