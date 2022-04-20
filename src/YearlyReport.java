import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class YearlyReport {

    public ArrayList<String[]> yearlyReportContent; //хранение данных

    public YearlyReport() {
        yearlyReportContent = new ArrayList<>();
    }

    public boolean readYearlyReport(String pathToFolder) //считывает годовой отчет
    {
        //возвращает +/- если удалось/не удало3сь считать
        String path = pathToFolder + "y.2021.csv";
        String fileContents = readFileContentsOrNull(path);
        String[] lines = fileContents.split(System.lineSeparator());
        if (lines[0].equals("null")) { //проверка считывания перед разбивкой на массив строк из одной большой строки
            return false;
        } else {
            for (int i = 1; i < lines.length; i++) {
                String[] lineContents = lines[i].split(",");
                yearlyReportContent.add(lineContents);
            }
            System.out.println("Годовой отчет загружен.");
            /*for (String[] string: yearlyReportContent) {
                for (int i=0; i<3; i++) {
                    System.out.print(string[i] + " | ");
                }
                System.out.println();
            }*/
            return true;
        }
    }

    private String readFileContentsOrNull(String path) //метод из ТЗ для считывания файла
    {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с годовым отчётом. Возможно, файл не находится в нужной директории.");
            return "null";
        }
    }

    public ArrayList<String[]> getDataForComparison() {
        return yearlyReportContent;
    }

    public void statistics() //стптистика по годовому отчету
    {
        int totalInc = 0; //запись общего дохода
        int totalExp = 0; //запись общего расхода
        int[] profit = new int[3]; //массив прибыли, индекс - месяц
        System.out.println("Рассматриваемый год: 2021");
        for (String[] strings: yearlyReportContent) {
            if (strings[2].equals("true")) {
                totalExp += Integer.parseInt(strings[1]);
                profit[Integer.parseInt(strings[0])-1] -= Integer.parseInt(strings[1]);
            } else {
                totalInc += Integer.parseInt(strings[1]);
                profit[Integer.parseInt(strings[0])-1] += Integer.parseInt(strings[1]);
            }
        }
        for (int i=1; i<4; i++) {
            System.out.println("Прибыль за " + i + " месяц: " + profit[i-1]);
        }
        System.out.println("Средний расход за все месяцы в году: " + Math.round(totalExp/3));
        System.out.println("Средний доход за все месяцы в году: " + Math.round(totalInc/3));
    }
}
