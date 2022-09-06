import Views.CountryView;
import Views.JobView;
import Views.RegionView;

import java.util.Scanner;

public class MainCRUD {
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) throws Exception {
        String choice = "";

        while (true){
            showMenu();
            System.out.print("Input Choice Here : ");
            choice = sc.nextLine().toUpperCase();

            switch (choice) {
                case "1" -> regionView();
                case "2" -> jobView();
                case "3" -> countryView();
                case "Q" -> System.exit(0);
                default -> System.out.println("Invalid Input");
            }
        }
    }


    public static void showMenu(){
        System.out.println("=========Welcome to CRUD HR App==========");
        System.out.println("1. Regions");
        System.out.println("2. Jobs");
        System.out.println("3. Countries");
        System.out.println("-----------------------------------------");
        System.out.println("q. Exit");
        System.out.println("=========================================");
    }

    public static void regionView() throws Exception {
        RegionView rv = new RegionView();
        rv.main();
    }

    public static void jobView() throws Exception {
        JobView jv = new JobView();
        jv.main();
    }

    public static void countryView() throws Exception {
        CountryView cv = new CountryView();
        cv.main();
    }
}
