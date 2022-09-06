package IControllers;

import Models.Region;
import java.util.List;

public interface IRegionController {

    public List<Region> getAll();
    public Region getById(String id);
    public List<Region> search(String key);
    public String insert(String id,String name) throws Exception;
    public String update(String id,String name);
    public String delete(String id);
}
