package Daos;

import IDaos.IRegionDAO;
import Models.Region;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RegionDAO implements IRegionDAO {
    Connection connection;

    public RegionDAO(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<Region> getAll() {
        List<Region> listRegion = new ArrayList<>();
        String query = "select * from regions";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                listRegion.add(new Region(resultSet.getInt(1),resultSet.getString(2)));
            }
        }catch (Exception e){
            e.getStackTrace();
        }
        return listRegion;
    }

    @Override
    public Region getById(int id) {
        Region region = new Region();
        String query = "select * from regions where region_id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                 region = new Region(resultSet.getInt(1),resultSet.getString(2));
            }
        }catch (Exception e){
            e.getStackTrace();
        }
        return region;
    }

    @Override
    public List<Region> search(String key) {
        List<Region> list = new ArrayList<>();
        String query = "select * from regions where region_name like ? or region_id like ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,"%" + key + "%");
            preparedStatement.setString(2,"%" + key + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Region r = new Region(resultSet.getInt(1),resultSet.getString(2));
                list.add(r);
            }
        }catch (Exception e){
            e.getStackTrace();
        }
        return list;
    }

    @Override
    public boolean insert(Region r) {
        boolean result = false;
        String query = "insert into regions(region_id,region_name) values (?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, r.getId());
            preparedStatement.setString(2, r.getName());
            preparedStatement.executeQuery();
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public boolean update(Region r) {
        boolean result = false;
        String query = "update regions set region_name = ? where region_id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,r.getName());
            preparedStatement.setInt(2,r.getId());
            preparedStatement.executeQuery();
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        String query = "delete from regions where region_id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.executeQuery();
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean hasName(String name){
        boolean result = false;
        String query = "select region_name from regions where region_name = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                result = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean hasFK(int regFk){
        boolean result = false;
        String query = "select region_id from countries where region_id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,regFk);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                result = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
