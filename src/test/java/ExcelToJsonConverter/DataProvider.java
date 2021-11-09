package ExcelToJsonConverter;

class DataProvider {
    private String Name;
    private int Age;
    private int TotalMarks;

    public DataProvider() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public int getTotalMarks() {
        return TotalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        TotalMarks = totalMarks;
    }

    @Override
    public String toString() {
        return "DataProvider{" +
                "Name='" + Name + '\'' +
                ", Age=" + Age +
                ", TotalMarks=" + TotalMarks +
                '}';
    }
}
