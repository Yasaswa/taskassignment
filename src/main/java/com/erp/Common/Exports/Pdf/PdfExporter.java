package com.erp.Common.Exports.Pdf;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

public class PdfExporter {
	private JSONObject list;

	public PdfExporter(JSONObject exportJson) {
		this.list = exportJson;
	}

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);
		cell.setNoWrap(true);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		JSONObject columnNames = (JSONObject) list.get("columns");
		for (int colKeyIndex = 0; colKeyIndex < columnNames.length(); colKeyIndex++) {
			cell.setPhrase(new Phrase(columnNames.getString(Integer.toString(colKeyIndex)), font));
			table.addCell(cell);
		}

	}

	private void writeTableData(PdfPTable table) {
		JSONObject columnNames = (JSONObject) list.get("columns");
		JSONObject columnValues = (JSONObject) list.get("allData");

		for (int jsonAllDataKeys = 0; jsonAllDataKeys < columnValues.length(); jsonAllDataKeys++) {
			JSONObject data = (JSONObject) columnValues.get(Integer.toString(jsonAllDataKeys));
			for (int colKeyIndex = 0; colKeyIndex < columnNames.length(); colKeyIndex++) {
				Object excelDataValue = data.get(columnNames.getString(Integer.toString(colKeyIndex)));
//				if (excelDataValue.equals(null)) {
//					excelDataValue = "";
//				}
				table.addCell(String.valueOf(excelDataValue));
			}

		}

//        for (User user : list) {
//            table.addCell(String.valueOf(user.getId()));
//            table.addCell(user.getEmail());
//            table.addCell(user.getFullName());
//            table.addCell(user.getRoles().toString());
//            table.addCell(String.valueOf(user.isEnabled()));
//        }
	}

	private static float[] calculateColumnWidths(String[] columnContent) {
		float[] columnWidths = new float[columnContent.length];
		int totalLength = 0;

		// Calculate total length of all strings
		for (String content : columnContent) {
			totalLength += content.length();
		}

		// Calculate relative column widths based on string lengths
		for (int i = 0; i < columnContent.length; i++) {
			columnWidths[i] = (float) columnContent[i].length() + 1 / totalLength;
		}

		return columnWidths;
	}

	public void export(HttpServletResponse response) throws IOException {
		Document document = new Document(PageSize.A1);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.darkGray);

		Paragraph p = new Paragraph("List of Users", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		JSONObject columnNames = (JSONObject) list.get("columns");

		PdfPTable table = new PdfPTable(columnNames.length());
		table.setWidthPercentage(200f);

		String[] columnNamesArr = new String[columnNames.length()];
		for (int colKeyIndex = 0; colKeyIndex < columnNames.length(); colKeyIndex++) {
			columnNamesArr[colKeyIndex] = columnNames.getString(Integer.toString(colKeyIndex));
		}

		// Calculate relative column widths based on string lengths
		float[] columnWidths = calculateColumnWidths(columnNamesArr);

		// Set the relative widths of the columns
		table.setWidths(columnWidths);

		writeTableHeader(table);
		writeTableData(table);

		document.add(table);

		document.close();

	}
}
