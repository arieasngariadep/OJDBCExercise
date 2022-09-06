package IControllers;

import Models.Country;

import java.util.List;

public interface ICountryController {

    public List<Country> getAll();

    public Country getById(String id);

    public List<Country> search(String key);

    public String insert(String id,String name,String regionId);

    public String update(String id,String name,String regionId);

    public String delete(String id);
}
