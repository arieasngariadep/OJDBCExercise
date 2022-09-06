package IDaos;

import Models.Country;

import java.util.List;

public interface ICountryDAO {

    public List<Country> getAll();

    public Country getById(String id);

    public List<Country> search(String key);

    public boolean insert(Country c);

    public boolean update(Country c);

    public boolean delete(String id);

    public boolean hasName(String name);

    public boolean hasRegionId(int regId);



}
