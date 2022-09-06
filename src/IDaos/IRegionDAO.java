package IDaos;

import Models.Region;
import java.util.List;

public interface IRegionDAO {

    public List<Region> getAll();

    public Region getById(int id);

    public List<Region> search(String key);

    public boolean insert(Region r) throws Exception;

    public boolean update(Region r);

    public boolean delete(int id);

    public boolean hasName(String name);

    public boolean hasFK(int regFk);

}
