package Views;

import Controllers.RegionController;
import IControllers.IRegionController;
import Models.Region;
import Tools.DBConnection;
import java.util.List;
import java.util.Scanner;

public class RegionView {
    DBConnection connection = new DBConnection();
    IRegionController irc = new RegionController(connection.getConnection());
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
        System.out.println("=========Regions=========");
        System.out.println("1. Get All Data Table");
        System.out.println("2. Get Data By Id");
        System.out.println("3. Search By Keywords");
        System.out.println("4. Insert Data");
        System.out.println("5. Update Data");
        System.out.println("6. Delete Data");
        System.out.println("-------------------------");
        System.out.println("Q. Exit");
        System.out.println("B. Back to Main");
        System.out.println("=========================");

    }

    public void getAll(){
        irc.getAll().forEach(r -> {
            System.out.println("Id   : " + r.getId());
            System.out.println("Name : " + r.getName());
        });
    }

    public void getById(){
        System.out.print("Enter Id Number : ");
        Region region = irc.getById(sc.nextLine());
            System.out.println("Id   : " + region.getId());
            System.out.println("Name : " + region.getName());
    }

    public void search(){
        System.out.print("Input Keywords : ");
        List<Region> search = irc.search(sc.nextLine());
        search.forEach(s -> {
            System.out.println("Id   : " + s.getId());
            System.out.println("Name : " + s.getName());
        });
    }

    public void insert() throws Exception {
        System.out.print("Enter Id Number : ");
        String id = sc.nextLine();

        System.out.print("Enter Region Name : ");
        String name = sc.nextLine();

        System.out.println(irc.insert(id,name));
    }

    public void update(){
        System.out.print("Enter Id Number : ");
        String id = sc.nextLine();

        System.out.print("Enter Region Name : ");
        String name = sc.nextLine();

        System.out.println(irc.update(id,name));
    }

    public void delete(){
        System.out.print("Enter Id Number : ");
        System.out.println(irc.delete(sc.nextLine()));
    }


}
