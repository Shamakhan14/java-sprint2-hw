import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class YearlyReport {

    private ArrayList<String[]> yearlyReportContent = new ArrayList<>();

    public boolean readYearlyReport(String pathToFolder) {
        String path = pathToFolder + "y.2021.csv";
        String fileContents = readFileContentsOrNull(path);
        String[] lines = fileContents.split(System.lineSeparator());
        if (lines[0].equals(null)) {
            return false;
        } else {
            for (int i = 1; i < lines.length; i++) {
                String[] lineContents = lines[i].split(",");
                yearlyReportContent.add(lineContents);
            }
            System.out.println("Годовой отчет загружен.");
            return true;
        }
    }

    private String readFileContentsOrNull(String path)
    {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }
}
