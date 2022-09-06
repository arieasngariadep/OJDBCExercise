package Controllers;

import Daos.RegionDAO;
import IControllers.IRegionController;
import IDaos.IRegionDAO;
import Models.Region;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class RegionController implements IRegionController {
    IRegionDAO irdao;

    public RegionController(Connection connection) {
        this.irdao = new RegionDAO(connection);
    }

    @Override
    public List<Region> getAll() {
        return irdao.getAll();
    }

    @Override
    public Region getById(String id) {
        Region r = new Region(0, "0");
            try{
                if(id.isEmpty()){
                    System.out.println("Failed get by id : No input region id detected");
                }else if(Integer.parseInt(id)<0){
                    System.out.println("Failed get by id : Negative number not allowed");
                }else if(Integer.parseInt(id) != irdao.getById(Integer.parseInt(id)).getId()){
                    System.out.println("Failed get by id : Region id not found");
                }else{
                    r = irdao.getById(Integer.parseInt(id));
                }
            }catch (NumberFormatException nfe){
                System.out.println("Failed get data by id : Please fill in the fields provided correctly");
            }
        return r;
    }

    @Override
    public List<Region> search(String key) {
        List<Region> keySearch = new ArrayList<>();
        if(key.isEmpty()){
            System.out.println("Failed found keywords : No input keywords detected");
        }else if(key.length() > 100){
            System.out.println("Failed found keywords : Keywords can't more than 100 characters");
        }else if(irdao.search(key).isEmpty()){
            System.out.println("Failed found keywords : Keywords not found");
        }else{
            keySearch = irdao.search(key);
        }
        return keySearch;
    }

    @Override
    public String insert(String id, String name) throws Exception {
        String result = "";
        try{
            Region r = new Region(Integer.parseInt(id), name.substring(0, 1).toUpperCase() + name.substring(1));
            if (Integer.parseInt(id) < 0) {
                result = "Failed insert data : Negative number not allowed";
            }else if (r.getName().matches("-?[0-9]*")) {
                result = "Failed insert data : Number not allowed in region name";
            } else if(name.length() > 25){
                result = "Failed insert data : Region name can't more than 25 characters";
            } else if (irdao.getById(r.getId()).getId() == r.getId()) {
                result = "Failed insert data : Region id already exist";
            } else if (irdao.hasName(r.getName())){
                result = "Failed insert data : Region name already exist";
            }else{
                irdao.insert(r);
                result = "Success insert data";
            }
        }catch (NumberFormatException | StringIndexOutOfBoundsException nfe){
            result = "Failed insert data : Please fill in all the fields provided correctly";
        }
        return result;
    }

    @Override
    public String update(String id, String name) {
        String result = "";
        try {
            Region r = new Region(Integer.parseInt(id),name.substring(0,1).toUpperCase() + name.substring(1));
            if(Integer.parseInt(id) < 0){
                result = "Failed update data : Negative number not allowed";
            }else if (r.getName().matches("-?[0-9]*")) {
                result = "Failed insert data : Number not allowed in region name";
            } else if(name.length() > 25){
                result = "Failed update data : Region name can't more than 25 characters";
            }else if(r.getId() != irdao.getById(r.getId()).getId()){
                result = "Failed update data : Region id not found";
            }else if(irdao.hasName(r.getName())) {
                result = "Failed update data : Region name already exist";
            }else{
                irdao.update(r);
                result = "Success update data";
            }
        }catch (NumberFormatException | StringIndexOutOfBoundsException nfe){
            result = "Failed update data : Please fill in all the fields provided correctly";
        }
        return result;
    }

    @Override
    public String delete(String id) {
        String result = "";
        try{
            if (id.isEmpty()) {
                result = "Failed delete data : Please enter a region id";
            } else if (Integer.parseInt(id) < 0) {
                result = "Failed delete data : Negative number not allowed";
            } else if (Integer.parseInt(id) != irdao.getById(Integer.parseInt(id)).getId()) {
                result = "Failed delete data : Region id not found";
            } else if (irdao.hasFK(Integer.parseInt(id))){
                result = "Failed delete data : Region id used in other table";
            } else {
                irdao.delete(Integer.parseInt(id));
                result = "Success delete data";
            }
        }catch (NumberFormatException nfe){
            result = "Failed delete data : Please fill in the fields provided correctly";
        }

        return result;
    }
}
