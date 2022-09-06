package Views;

import Controllers.CountryController;
import IControllers.ICountryController;
import Models.Country;
import Tools.DBConnection;

import java.util.Scanner;

public class CountryView {
    DBConnection connection = new DBConnection();
    ICountryController icc = new CountryController(connection.getConnection());
    Scanner sc = new Scanner(System.in);

    public void main() throws Exception {
        String choice = "";
        boolean kondisi = true;
        while (kondisi){
            menu();
            System.out.print("Input Choice Here : ");
            choice = sc.nextLine().toUpperCase();
            switch (choice) {
                case "1" -> getAll();
                case "2" -> getById();
                case "3" -> search();
                case "4" -> insert();
                case "5" -> update();
                case "6" -> delete();
                case "Q" -> System.exit(0);
                case "B" -> kondisi = false;
                default -> System.out.println("Invalid Input");
            }
        }
    }

    public void menu(){
        System.out.println("=========Countries=========");
        System.out.println("1. Get All Data Table");
        System.out.println("2. Get Data By Id");
        System.out.println("3. Search By Keywords");
        System.out.println("4. Insert Data");
        System.out.println("5. Update Data");
        System.out.println("6. Delete Data");
        System.out.println("---------------------------");
        System.out.println("Q. Exit");
        System.out.println("B. Back to Main ");
        System.out.println("===========================");
    }

    public void getAll(){
        icc.getAll().forEach(c -> {
            System.out.println("Id             : " + c.getId());
            System.out.println("Name           : " + c.getName());
            System.out.println("Region Id      : " + c.getRegion_id());
        });
    }

    public void getById(){
        System.out.print("Enter Id : ");
        Country c = icc.getById(sc.nextLine());
        System.out.println("Id             : " + c.getId());
        System.out.println("Name           : " + c.getName());
        System.out.println("Region Id      : " + c.getRegion_id());
    }

    public void search(){
        System.out.print("Enter Keywords : ");
        icc.search(sc.nextLine()).forEach(c -> {
            System.out.println("Id             : " + c.getId());
            System.out.println("Name           : " + c.getName());
            System.out.println("Region Id      : " + c.getRegion_id());
        });
    }

    public void insert(){
        System.out.print("Enter Id : ");
        String id = sc.nextLine();

        System.out.print("Enter Name : ");
        String name = sc.nextLine();

        System.out.print("Enter Region Id : ");
        String regionId = sc.nextLine();

        System.out.println(icc.insert(id,name,regionId));
    }

    public void update(){
        System.out.print("Enter Id : ");
        String id = sc.nextLine();

        System.out.print("Enter Name : ");
        String name = sc.nextLine();

        System.out.print("Enter Region Id : ");
        String regionId = sc.nextLine();

        System.out.println(icc.update(id,name,regionId));
    }

    public void delete(){
        System.out.print("Enter id : ");
        System.out.println(icc.delete(sc.nextLine()));
    }
}
