//Спасибо.
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();
        Scanner scanner = new Scanner(System.in);
        boolean yearlyReportUploaded = false; //проверка загрузки годового отчета
        boolean monthlyReportUploaded = false; //проверка загрузки месячного отчета

        printMenu();
        int userInput = scanner.nextInt();
        while (userInput!=0) {
            switch (userInput) {
                case 1:
                    monthlyReportUploaded = monthlyReport.readMonthlyReport();
                    break;
                case 2:
                    yearlyReportUploaded = yearlyReport.readYearlyReport();
                    break;
                case 3:
                    if (monthlyReportUploaded && yearlyReportUploaded) {
                        monthlyReport.comparison(yearlyReport.getDataForComparison());
                    } else{
                        System.out.println("Невозможно выполнить команду. Сначала считайте отчеты.");
                    }
                    break;
                case 4:
                    if (monthlyReportUploaded) {
                        monthlyReport.statistics();
                    } else {
                        System.out.println("Невозможно выполнить команду. Считайте месячные отчеты.");
                    }
                    break;
                case 5:
                    if (yearlyReportUploaded) {
                        yearlyReport.statistics();
                    } else{
                        System.out.println("Невозможно выполнить команду. Считайте годовой отчет.");
                    }
                    break;
                default:
                    System.out.println("Такой команды нет. Повторите попытку.");
                    break;
            }
            printMenu();
            userInput = scanner.nextInt();
        }
        System.out.println("Программа завершена.");
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1. Считать все месячные отчёты.");
        System.out.println("2. Считать годовой отчёт.");
        System.out.println("3. Сверить отчёты.");
        System.out.println("4. Вывести информацию о всех месячных отчётах.");
        System.out.println("5. Вывести информацию о годовом отчёте.");
        System.out.println("0. Выйти из приложения.");
    }
}

