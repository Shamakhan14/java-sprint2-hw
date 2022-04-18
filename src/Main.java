import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();
        Scanner scanner = new Scanner(System.in);
        String pathToFolder = "c:/Users/shama/dev/java-sprint2-hw/resources/";
        //путь до папки хранения отчетов без имени файла
        //не стал вносить в меню требование указать путь, т.к. будет слишком сложно прописать требование к оформлению
        boolean yearlyReportUploaded; //проверка загрузки годового отчета
        boolean monthlyReportUploaded; //проверка загрузки месячного отчета

        printMenu();
        int userInput = scanner.nextInt();
        while (userInput!=0) {
            switch (userInput) {
                case 1:

                    break;
                case 2:
                    yearlyReportUploaded = yearlyReport.readYearlyReport(pathToFolder);
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                default:
                    System.out.println("Такой команды нет. Повторите попытку");
                    break;
            }
            printMenu();
            userInput = scanner.nextInt();
        }
        System.out.println("Программа завершена");
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1. Считать все месячные отчёты");
        System.out.println("2. Считать годовой отчёт");
        System.out.println("3. Сверить отчёты");
        System.out.println("4. Вывести информацию о всех месячных отчётах");
        System.out.println("5. Вывести информацию о годовом отчёте");
        System.out.println("0. Выйти из приложения");
    }
}

