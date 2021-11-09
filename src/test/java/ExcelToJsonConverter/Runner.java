package ExcelToJsonConverter;

public class Runner {
    public static void main(String[] args) {
        ExcelToJson run = new Implementor();
        run.generateJsonFile(run.readTheExcelFile());
    }
}
