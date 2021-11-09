package ExcelToJsonConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Implementor implements ExcelToJson {
    String userDirectory = System.getProperty("user.dir");
    String pathSeparator = System.getProperty("file.separator");
    String filePath = userDirectory + pathSeparator + "src" + pathSeparator + "files" + pathSeparator + "StudentRecords.";
    String excelFilePath = filePath + "xlsx";
    String jsonFilePath = filePath + "json";

    public List<DataProvider> readTheExcelFile() {
        List<DataProvider> studentRecords = null;
        boolean columnCountCheck = false;
        try {
            File fileObject = new File(excelFilePath);
            FileInputStream fileInputStreamObject = new FileInputStream(fileObject);
            XSSFWorkbook workBook = new XSSFWorkbook(fileInputStreamObject);
            XSSFSheet sheet = workBook.getSheetAt(0);

            int numberOfRows = sheet.getPhysicalNumberOfRows();
            Row firstRow = sheet.getRow(0);
            int numberOfColumns = firstRow.getPhysicalNumberOfCells();

            for (Row individualRow : sheet) {
                int columnCount = individualRow.getPhysicalNumberOfCells();
                if (columnCount == numberOfColumns) {
                    columnCountCheck = true;
                }
            }
            if (columnCountCheck) {
                studentRecords = new ArrayList<>();
                for (int rowIndex = 1; rowIndex < numberOfRows; rowIndex++) {
                    DataProvider data = new DataProvider();
                    Row rowData = sheet.getRow(rowIndex);
                    for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++) {
                        if (columnIndex == 0) {
                            data.setName(rowData.getCell(columnIndex).toString());
                        } else if (columnIndex == 1) {
                            data.setAge(Integer.parseInt(rowData.getCell(columnIndex).toString().replace(".0", "")));
                        } else if (columnIndex == 2) {
                            data.setTotalMarks(Integer.parseInt(rowData.getCell(columnIndex).toString().replace(".0", "")));
                        }
                    }
                    studentRecords.add(data);
                }
            } else {
                System.out.println("We can't generate the file, because of the column count mismatch");
            }

        } catch (IOException exception) {
            System.out.println("Check the file in the specified path.");
        }
        return studentRecords;
    }

    public void generateJsonFile(List<DataProvider> studentRecords) {
        if (studentRecords != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                File file = new File(jsonFilePath);
                mapper.writerWithDefaultPrettyPrinter().writeValue(file, studentRecords);
            } catch (IOException exception) {
                System.out.println("Check the file in the specified path.");
            }
        }
    }
}
