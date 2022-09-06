package IControllers;

import Models.Job;

import java.util.List;

public interface IJobController {

    public List<Job> getAll();

    public Job getById(String id);

    public List<Job> search(String key);

    public String insert(String id,String title,String minSalary,String maxSalary) throws Exception;

    public String update(String id,String title,String minSalary,String maxSalary);

    public String delete(String id);

}
