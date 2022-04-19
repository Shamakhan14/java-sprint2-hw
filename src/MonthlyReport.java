import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MonthlyReport {

    private ArrayList<String[]>[] monthlyReportContent; //массив[3] списков массивов строк... фуx

    public MonthlyReport () {
        monthlyReportContent = new ArrayList[3];
    }

    public boolean readMonthlyReport(String pathToFolder)
    {
        for (int j=1; j<4; j++) {
            String path = pathToFolder + "m.20210" + j + ".csv";
            String fileContents = readFileContentsOrNull(path);
            String[] lines = fileContents.split(System.lineSeparator());
            ArrayList<String[]> list = new ArrayList<>(); //промежуточный список
            if (lines[0].equals("null")) {
                return false;
            } else {
                for (int i = 1; i < lines.length; i++) {
                    String[] lineContents = lines[i].split(",");
                    list.add(lineContents);
                }
            }
            monthlyReportContent[j-1] = list;
        }
        System.out.println("Месячный отчет загружен.");
        for (int i=0; i<3; i++) {
            ArrayList<String[]> mas = monthlyReportContent[i];
            for (String[] strings: mas) {
                for (int j=0; j<4; j++) {
                    System.out.print(strings[j] + " | ");
                    System.out.println();
                }
            }
        }
        return true;
    }

    private String readFileContentsOrNull(String path)
    {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return "null";
        }
    }


    public void comparison(ArrayList<String[]> yearlyReportContent)
    { //сравнение отчетов
        System.out.println("Несоответствия в следующих месяцах:");
        int monthExpenses = 0; //расходы за месяц
        int monthIncome = 0; //доходы за месяц
        int yMonthExpenses = 0;
        int yMonthIncome = 0;
        boolean comparison = false; //переменная для сравнения
        for (int i=1; i<4; i++) { //цикл по месяцам
            ArrayList<String[]> month1 = monthlyReportContent[i-1]; //считывание месяца
            for (int j=1; j<month1.size(); j++) { //цикл массиву строк
                String[] month2 = month1.get(j);
                if (month2[1].equals("TRUE")) {
                    monthExpenses += Integer.parseInt(month2[2]) * Integer.parseInt(month2[3]);
                } else {
                    monthIncome += Integer.parseInt(month2[2]) * Integer.parseInt(month2[3]);
                }
            }
            String[] m1 = yearlyReportContent.get(i*2-1);
            if (m1[2].equals("true")) {
                yMonthExpenses = Integer.parseInt(m1[1]);
            } else {
                yMonthIncome = Integer.parseInt(m1[1]);
            }
            String[] m2 = yearlyReportContent.get(i*2);
            if (m2[2].equals("true")) {
                yMonthExpenses = Integer.parseInt(m1[1]);
            } else {
                yMonthIncome = Integer.parseInt(m1[1]);
            }
            if (monthExpenses != yMonthExpenses || monthIncome != yMonthIncome) {
                System.out.println(i);
            }
            monthExpenses = 0;
            monthIncome = 0;
            yMonthExpenses = 0;
            yMonthIncome = 0;
        }
    }
}
