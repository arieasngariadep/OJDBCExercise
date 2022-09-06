package IDaos;

import Models.Job;

import java.util.List;

public interface IJobDAO {

    public List<Job> getAll();

    public Job getById(String id);

    public List<Job> search(String key);

    public boolean insert(Job j);

    public boolean update(Job j);

    public boolean delete(String id);

    public boolean hasTitle(String title);

    public boolean hasFK(String empId);
}
