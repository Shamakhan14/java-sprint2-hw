import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MonthlyReport {

    private ArrayList<String[]>[] monthlyReportContent; //массив[3] списков массивов строк... фуx

    public MonthlyReport () {
        monthlyReportContent = new ArrayList[3];
    }

    public boolean readMonthlyReport(String pathToFolder) //считывание отчета
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
        /*for (int i=0; i<3; i++) { //проверка загрузки, вывод инфы на экран
            ArrayList<String[]> mas = monthlyReportContent[i];
            for (String[] strings: mas) {
                for (int j=0; j<4; j++) {
                    System.out.print(strings[j] + " | ");
                }
            System.out.println();
            }
        }*/
        return true;
    }

    private String readFileContentsOrNull(String path) //метод для чтения файла из ТЗ
    {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return "null";
        }
    }


    public void comparison(ArrayList<String[]> yearlyReportContent) //сравнение отчетов
    { //сравнение отчетов
        System.out.println("Несоответствия в следующих месяцах:");
        int monthExpenses = 0; //расходы за месяц
        int monthIncome = 0; //доходы за месяц
        int yMonthExpenses = 0; //расходы согласно годовому отчету
        int yMonthIncome = 0; //доходу согласно говодому отчету
        boolean comparison = true; //переменная для сравнения
        for (int i=1; i<4; i++) { //цикл по месяцам
            ArrayList<String[]> month1 = monthlyReportContent[i-1]; //считывание месяца
            for (int j=0; j<month1.size(); j++) { //цикл массиву строк
                String[] month2 = month1.get(j);
                if (month2[1].equals("TRUE")) {
                    monthExpenses += Integer.parseInt(month2[2]) * Integer.parseInt(month2[3]);
                } else {
                    monthIncome += Integer.parseInt(month2[2]) * Integer.parseInt(month2[3]);
                }
            }
            for (String [] strings: yearlyReportContent) {
                if (strings[0].equals("0"+i) && strings[2].equals("true")) {
                    yMonthExpenses = Integer.parseInt(strings[1]);
                }
                if (strings[0].equals("0"+i) && strings[2].equals("false")) {
                    yMonthIncome = Integer.parseInt(strings[1]);
                }
            }
            if (monthExpenses != yMonthExpenses || monthIncome != yMonthIncome) {
                System.out.println(i);
                comparison = false;
            }
            monthExpenses = 0;
            monthIncome = 0;
            yMonthExpenses = 0;
            yMonthIncome = 0;
        }
        if (comparison) {
            System.out.println("Ошибок нет.");
        }
    }

    public void statistics() //статистика по месяцам
    {
        for (int i=0; i<3; i++) {
            System.out.println(getMonthFromNumber(i+1));
            int maxExp = 0;
            String maxExpName = null;
            int maxInc = 0;
            String maxIncName = null;
            for (String[] strings: monthlyReportContent[i]) {
                if (strings[1].equals("TRUE") && Integer.parseInt(strings[2])*Integer.parseInt(strings[3])>maxExp) {
                    maxExp = Integer.parseInt(strings[2])*Integer.parseInt(strings[3]);
                    maxExpName = strings[0];
                }
                if (strings[1].equals("FALSE") && Integer.parseInt(strings[2])*Integer.parseInt(strings[3])>maxInc) {
                    maxInc = Integer.parseInt(strings[2])*Integer.parseInt(strings[3]);
                    maxIncName = strings[0];
                }
            }
            System.out.println("Максимальная трата: " + maxExpName + " " + maxExp);
            System.out.println("Самый прибыльный товар: " + maxIncName + " " + maxInc);
        }
    }

    private String getMonthFromNumber(int number) //возвращает название месяца по номеру
    {
        switch (number) {
            case 1:
                return "Январь";
            case 2:
                return "Февраль";
            case 3:
                return "Март";
            default:
                return null;
        }
    }
}
