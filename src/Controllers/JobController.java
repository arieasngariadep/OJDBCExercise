package Controllers;

import Daos.JobDAO;
import IControllers.IJobController;
import IDaos.IJobDAO;
import Models.Job;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class JobController implements IJobController {
    IJobDAO iJobDAO;

    public JobController(Connection connection) {

        this.iJobDAO = new JobDAO(connection);
    }

    @Override
    public List<Job> getAll() {
        return iJobDAO.getAll();
    }

    @Override
    public Job getById(String id) {
        Job j = new Job("0", "0", 0, 0);
            if(id.isEmpty()){
                System.out.println("Failed get by id : No input job id detected");
            }else if(id.matches("-?[0-9]*")){
                System.out.println("Failed get by id : Number not allowed");
            }else if(!id.toUpperCase().equals(iJobDAO.getById(id.toUpperCase()).getId())){
                System.out.println("Failed get by id : Job id not found");
            }else{
                j = iJobDAO.getById(id.toUpperCase());
            }
        return j;
    }

    @Override
    public List<Job> search(String key) {
        List<Job> keySearch = new ArrayList<>();
        if(key.isEmpty()){
            System.out.println("Failed found keywords : No input keywords detected");
        }else if(key.length() > 100){
            System.out.println("Failed found keywords : Keywords can't more than 100 characters");
        }else if(iJobDAO.search(key).isEmpty()){
            System.out.println("Failed found keywords : Keywords not found");
        }else{
            keySearch = iJobDAO.search(key);
        }
        return keySearch;
    }

    @Override
    public String insert(String id, String title, String minSalary, String maxSalary) throws Exception {
        String result = "";
            try{
                Job j = new Job();
                j.setId(id.toUpperCase());
                j.setTitle(title.substring(0,1).toUpperCase() + title.substring(1));
                j.setMinSalary(Integer.parseInt(minSalary));
                j.setMaxSalary(Integer.parseInt(maxSalary));
                if((j.getId().isEmpty()) || (j.getTitle().isEmpty()) || (j.getMinSalary()==0) || (j.getMaxSalary()==0)){
                    result = "Failed insert data : The data you entered is not complete";
                }else if(j.getId().length() > 10){
                    result = "Failed update data : Job id can't more than 10 characters";
                }else if(j.getTitle().length() > 35){
                    result = "Failed update data : Job title can't more than 35 characters";
                }else if(j.getId().matches("-?[0-9]*") || j.getTitle().matches("-?[0-9]*")){
                    result = "Failed insert data : Number not allowed in job id and title fields";
                }else if(j.getId().equals(iJobDAO.getById(j.getId()).getId())){
                    result = "Failed insert data : Job id already exist";
                }else if(iJobDAO.hasTitle(j.getTitle())){
                    result = "Failed insert data : Job title already exist";
                }else if(j.getMinSalary()<0 || j.getMaxSalary()<0){
                    result = "Failed insert data : Can't Input Salary Less Than 0";
                }else if(j.getMinSalary()>j.getMaxSalary()){
                    result = "Failed insert data : Maksimum salary not allowed less than minimum salary";
                }else{
                    iJobDAO.insert(j);
                    result = "Success insert data";
                }
            }catch (NumberFormatException | StringIndexOutOfBoundsException nfe){
                result = "Failed insert data : Please fill in all the fields provided correctly";
            }
        return result;
    }

    @Override
    public String update(String id, String title, String minSalary, String maxSalary) {
        String result = "";
        try{
            Job j = new Job();
            j.setId(id.toUpperCase());
            j.setTitle(title.substring(0,1).toUpperCase() + title.substring(1));
            j.setMinSalary(Integer.parseInt(minSalary));
            j.setMaxSalary(Integer.parseInt(maxSalary));
            if((j.getId().isEmpty()) || (j.getTitle().isEmpty()) || (j.getMinSalary() == 0) || (j.getMaxSalary()==0)){
                result = "Failed update data : The data you entered is not complete";
            }else if(j.getId().length() > 10){
                result = "Failed update data : Job id can't more than 10 characters";
            }else if(j.getTitle().length() > 35){
                result = "Failed update data : Job title can't more than 35 characters";
            }else if(j.getId().matches("-?[0-9]*") || j.getTitle().matches("-?[0-9]*")){
                result = "Failed update data : Number not allowed in job id and title fields";
            }else if(!j.getId().equals(iJobDAO.getById(j.getId()).getId())){
                result = "Failed update data : Job id not found";
            }else if(iJobDAO.hasTitle(j.getTitle())){
                result = "Failed update data : Job title already exist";
            }else if(j.getMinSalary()<0 || j.getMaxSalary()<0){
                result = "Failed update data : Can't Input Salary Less Than 0";
            }else if(j.getMinSalary()>j.getMaxSalary()){
                result = "Failed update data : Maksimum salary not allowed less than minimum salary";
            }else{
                iJobDAO.update(j);
                result = "Success Update Data";
            }
        }catch (NumberFormatException | StringIndexOutOfBoundsException nfe){
            result = "Failed update data : Please fill in all the fields provided correctly";
        }
        return result;
    }

    @Override
    public String delete(String id) {
        String result = "";
        if(id.isEmpty()){
            result = "Failed delete data : Please enter a job id";
        }else if(id.matches("-?[0-9]*")){
            result = "Failed delete data : Number not allowed";
        }else if(id.length()>10){
            result = "Job id can't more than 10 characters";
        }else if(!id.toUpperCase().equals(iJobDAO.getById(id.toUpperCase()).getId())){
            result = "Failed delete data : Job id not found";
        }else if(iJobDAO.hasFK(id.toUpperCase())){
            result = "Failed delete data : Job id used in other table";
        }else{
            iJobDAO.delete(id.toUpperCase());
            result = "Success delete data";
        }
        return result;
    }
}
