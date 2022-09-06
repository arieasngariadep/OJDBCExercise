package Daos;

import IDaos.ICountryDAO;
import Models.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO implements ICountryDAO {
    Connection connection;

    public CountryDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Country> getAll() {
        List<Country> c = new ArrayList<>();
        String query = "select * from countries";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                c.add(new Country(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3)));
            }
        }catch (Exception e){
            e.getStackTrace();
        }
        return c;
    }

    @Override
    public Country getById(String id) {
        Country c = new Country();
        String query = "select * from countries where country_id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                c = new Country(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3));
            }
        }catch (Exception e){
            e.getStackTrace();
        }
        return c;
    }

    @Override
    public List<Country> search(String key) {
        List<Country> c = new ArrayList<>();
        String query = "select * from countries where country_id like ? or country_name like ? or region_id like ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,"%" + key + "%");
            preparedStatement.setString(2,"%" + key + "%");
            preparedStatement.setString(3,"%" + key + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                c.add(new Country(resultSet.getString(1),resultSet.getString(2), resultSet.getInt(3)));
            }
        }catch (Exception e){
            e.getStackTrace();
        }
        return c;
    }

    @Override
    public boolean insert(Country c) {
        boolean result = false;
        String query = "insert into countries(country_id,country_name,region_id) values (?,?,?)";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,c.getId());
            preparedStatement.setString(2,c.getName());
            preparedStatement.setInt(3,c.getRegion_id());
            preparedStatement.executeQuery();
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(Country c) {
        boolean result = false;
        String query = "update countries set country_name = ?, region_id = ? where country_id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,c.getName());
            preparedStatement.setInt(2,c.getRegion_id());
            preparedStatement.setString(3,c.getId());
            preparedStatement.executeQuery();
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        String query = "delete from countries where country_id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            preparedStatement.executeQuery();
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean hasName(String name) {
        boolean result = false;
        String query = "select country_name from countries where country_name = ?";
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
    public boolean hasRegionId(int regId) {
        boolean result = false;
        String query = "select region_id from regions where region_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,regId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                result = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
