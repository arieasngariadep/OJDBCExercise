package Daos;

import IDaos.IJobDAO;
import Models.Job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JobDAO implements IJobDAO {
    Connection connection;

    public JobDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Job> getAll() {
        List<Job> listJob = new ArrayList<>();
        String query = "select * from jobs";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Job job = new Job(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4) );
                listJob.add(job);
            }
        }catch (Exception e){
                e.getStackTrace();
        }
        return listJob;
    }

    @Override
    public Job getById(String id) {
        Job j = new Job();
        String query = "select * from jobs where job_id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                j = new Job(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4));
            }
        }catch (Exception e){
            e.getStackTrace();
        }
        return j;
    }

    @Override
    public List<Job> search(String key) {
        List<Job> listJob = new ArrayList<>();
        String query = "select * from jobs where job_title like ? or job_id like ? or min_salary like ? or max_salary like ?";
        try{
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,"%" + key + "%");
        preparedStatement.setString(2,"%" + key + "%");
        preparedStatement.setString(3,"%" + key + "%");
        preparedStatement.setString(4,"%" + key + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Job j = new Job(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4));
            listJob.add(j);
        }
        }catch (Exception e){
            e.getStackTrace();
        }
        return listJob;
    }

    @Override
    public boolean insert(Job j) {
        boolean result = false;
        String query = "insert into jobs(job_id,job_title,min_salary,max_salary) values(?,?,?,?) ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,j.getId().toUpperCase());
            preparedStatement.setString(2,j.getTitle());
            preparedStatement.setInt(3,j.getMinSalary());
            preparedStatement.setInt(4,j.getMaxSalary());
            preparedStatement.executeQuery();
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(Job j) {
        boolean result = false;
        String query = "update jobs set job_title = ?, min_salary = ?, max_salary = ? where job_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, j.getTitle());
            preparedStatement.setInt(2,j.getMinSalary());
            preparedStatement.setInt(3,j.getMaxSalary());
            preparedStatement.setString(4,j.getId().toUpperCase());
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
        String query = "delete from jobs where job_id = ?";
        try {
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
    public boolean hasTitle(String title) {
        boolean result = false;
        String query = "select job_title from jobs where job_title = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,title);
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
    public boolean hasFK(String empId) {
        boolean result = false;
        String query = "select job_id from employees where job_id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,empId);
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
