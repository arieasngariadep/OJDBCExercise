package Models;

public class Country {
    private String id;
    private String name;
    private int region_id;

    public Country() {
    }

    public Country(String id, String name, int region_id) {
        this.id = id;
        this.name = name;
        this.region_id = region_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRegion_id() {
        return region_id;
    }

    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }
}
