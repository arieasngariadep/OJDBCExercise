package Controllers;

import Daos.CountryDAO;
import IControllers.ICountryController;
import IDaos.ICountryDAO;
import Models.Country;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class CountryController implements ICountryController {
    ICountryDAO iCountryDAO;

    public CountryController(Connection connection) {
        this.iCountryDAO = new CountryDAO(connection);
    }

    @Override
    public List<Country> getAll() {
        return iCountryDAO.getAll();
    }

    @Override
    public Country getById(String id) {
        Country c = new Country("0","0",0);
        if(id.isEmpty()){
            System.out.println("Failed get by id : No input country id detected");
        }else if(id.matches("-?[0-9]*")){
            System.out.println("Failed get by id : Number not allowed");
        }else if(id.length()>2){
            System.out.println("Failed get by id : Country id can't be more than 2 characters");
        }else if(id.length()<2){
            System.out.println("Failed get by id : Country id can't be less than 2 characters");
        }else if(!id.toUpperCase().equals(iCountryDAO.getById(id.toUpperCase()).getId())){
            System.out.println("Failed get by id : Country id not found");
        }else{
            c = iCountryDAO.getById(id.toUpperCase());
        }
        return c;
    }

    @Override
    public List<Country> search(String key) {
        List<Country> keySearch = new ArrayList<>();
        if(key.isEmpty()){
            System.out.println("Failed found keywords :  No input keywords detected");
        }else if(key.length() > 100){
            System.out.println("Failed found keywords : Keywords can't more than 100 characters");
        }else if(iCountryDAO.search(key).isEmpty()){
            System.out.println("Failed found keywords : Keywords not found");
        }else{
            keySearch = iCountryDAO.search(key);
        }
        return keySearch;
    }

    @Override
    public String insert(String id, String name, String regionId) {
        String result = "";
        try {
            Country c = new Country();
            c.setId(id.toUpperCase());
            c.setName(name.substring(0,1).toUpperCase() + name.substring(1));
            c.setRegion_id(Integer.parseInt(regionId));
            if(c.getId().isEmpty() || c.getName().isEmpty() || c.getRegion_id() == 0 ){
                result = "Failed insert data : The data you entered is not complete";
            }else if(c.getId().matches("-?[0-9]*") || c.getName().matches("-?[0-9]*")){
                result = "Failed insert data : Number not allowed in country id and name fields";
            }else if(c.getId().length() > 2){
                result = "Failed insert data : Country id can't be more than 2 characters";
            }else if(c.getId().length() < 2){
                result = "Failed insert data : Country id can't be less than 2 characters";
            }else if(c.getName().length() > 40){
                result = "Failed insert data : Country name can't be more than 40 characters";
            }else if(c.getId().equals(iCountryDAO.getById(c.getId()).getId())){
                result = "Failed insert data : Country id already exist";
            }else if(iCountryDAO.hasName(c.getName())){
                result = "Failed insert data : Country name already exist";
            }else if(!iCountryDAO.hasRegionId(c.getRegion_id())){
                result = "Failed insert data : Region id not exist";
            }else {
                iCountryDAO.insert(c);
                result = "Success insert data";
            }
        }catch (NumberFormatException | StringIndexOutOfBoundsException nfe){
            System.out.println("Failed insert data : Please fill in all the fields provided correctly");
        }

        return result;
    }

    @Override
    public String update(String id, String name, String regionId) {
        String  result = "";
        try {
            Country c = new Country();
            c.setId(id.toUpperCase());
            c.setName(name.substring(0,1).toUpperCase() + name.substring(1));
            c.setRegion_id(Integer.parseInt(regionId));
            if(c.getId().isEmpty() || c.getName().isEmpty() || c.getRegion_id() == 0 ){
                result = "Failed update data : The data you entered is not complete";
            }else if(c.getId().matches("-?[0-9]*") || c.getName().matches("-?[0-9]*")){
                result = "Failed update data : Number not allowed in country id and name fields";
            }else if(c.getId().length() > 2){
                result = "Failed update data : Country id can't be more than 2 characters";
            }else if(c.getId().length() < 2){
                result = "Failed update data : Country id can't be less than 2 characters";
            }else if(c.getName().length() > 40){
                result = "Failed update data : Country name can't be more than 40 characters";
            }else if(!c.getId().equals(iCountryDAO.getById(c.getId()).getId())){
                result = "Failed update data : Country id not found";
            }else if(iCountryDAO.hasName(c.getName())){
                result = "Failed update data : Country name already exist";
            }else if(!iCountryDAO.hasRegionId(c.getRegion_id())){
                result = "Failed update data : Region id not exist";
            }else {
                iCountryDAO.update(c);
                result = "Success update data";
            }
        }catch (NumberFormatException | StringIndexOutOfBoundsException nfe){
            System.out.println("Failed update data : Please fill in all the fields provided correctly");
        }
        return result;
    }

    @Override
    public String delete(String id) {
        String result = "";
        if(id.isEmpty()){
            result = "Failed delete data : No input country id detected";
        }else if(id.matches("-?[0-9]*")){
            result = "Failed delete data : Number not allowed";
        }else if(id.length()>2){
            result = "Failed delete data : Country id can't be more than 2 characters";
        }else if(id.length()<2){
            result = "Failed delete data : Country id can't be less than 2 characters";
        }else if(!id.toUpperCase().equals(iCountryDAO.getById(id.toUpperCase()).getId())){
            result = "Failed delete data : Country id not found";
        }else{
            iCountryDAO.delete(id.toUpperCase());
            result = "Success delete data";
        }
        return result;
    }
}
