package ExcelToJsonConverter;

import java.util.List;

interface ExcelToJson {
    List<DataProvider> readTheExcelFile();

    void generateJsonFile(List<DataProvider> studentRecords);
}
