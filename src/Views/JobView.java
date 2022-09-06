package Views;

import Controllers.JobController;
import IControllers.IJobController;
import Models.Job;
import Tools.DBConnection;

import java.util.List;
import java.util.Scanner;

public class JobView {
    DBConnection connection = new DBConnection();
    IJobController ijc = new JobController(connection.getConnection());
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
                case "5" ->  update();
                case "6" ->  delete();
                case "Q" -> System.exit(0);
                case "B" -> kondisi = false;
                default -> System.out.println("Invalid Input");
            }
        }
    }

    public void menu(){
        System.out.println("=========Jobs=========");
        System.out.println("1. Get All Data Table");
        System.out.println("2. Get Data By Id");
        System.out.println("3. Search By Keywords");
        System.out.println("4. Insert Data");
        System.out.println("5. Update Data");
        System.out.println("6. Delete Data");
        System.out.println("----------------------");
        System.out.println("Q. Exit");
        System.out.println("B. Back to Main ");
        System.out.println("======================");
    }

    public void getAll(){
        ijc.getAll().forEach(j -> {
            System.out.println("Id             : " + j.getId());
            System.out.println("Title          : " + j.getTitle());
            System.out.println("Minimum Salary : " + j.getMinSalary());
            System.out.println("Maximum Salary : " + j.getMaxSalary());
        });
    }

    public void getById(){
        System.out.print("Enter Id : ");
        Job j = ijc.getById(sc.nextLine());
            System.out.println("Id             : " + j.getId());
            System.out.println("Name           : " + j.getTitle());
            System.out.println("Minimum Salary : " + j.getMinSalary());
            System.out.println("Maximum Salary : " + j.getMaxSalary());

    }

    public void search(){
        System.out.print("Enter Keywords : ");
        List<Job> keyWords = ijc.search(sc.nextLine());
        keyWords.forEach(j -> {
            System.out.println("Id             : " + j.getId());
            System.out.println("Name           : " + j.getTitle());
            System.out.println("Minimum Salary : " + j.getMinSalary());
            System.out.println("Maximum Salary : " + j.getMaxSalary());
        });
    }

    public void insert() throws Exception {
        System.out.print("Enter Id : ");
        String id = sc.nextLine();

        System.out.print("Enter Job Title : ");
        String title = sc.nextLine();

        System.out.print("Enter Minimum Salary : ");
        String minSal = sc.nextLine();

        System.out.print("Enter Maximum Salary : ");
        String maxSal = sc.nextLine();

        System.out.println(ijc.insert(id,title,minSal,maxSal));
    }

    public void update(){
        System.out.print("Enter Id : ");
        String id = sc.nextLine();

        System.out.print("Enter Job Title : ");
        String title = sc.nextLine();

        System.out.print("Enter Minimum Salary : ");
        String minSal = sc.nextLine();

        System.out.print("Enter Maximum Salary : ");
        String maxSal = sc.nextLine();

        System.out.println(ijc.update(id,title,minSal,maxSal));
    }

    public void delete(){
        System.out.print("Enter Id : ");
        System.out.println(ijc.delete(sc.nextLine()));
    }
}
