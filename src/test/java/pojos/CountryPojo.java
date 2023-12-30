package pojos;

import java.util.List;

public class CountryPojo {

      //     "id": null,
      //     "name": "Banana Republic",
      //     "states":

    private Integer id;
    private String name;
    private List<StatePojo> states;

    public CountryPojo() {
    }

    public CountryPojo(Integer id, String name, List<StatePojo> states) {
        this.id = id;
        this.name = name;
        this.states = states;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StatePojo> getStates() {
        return states;
    }

    public void setStates(List<StatePojo> states) {
        this.states = states;
    }


}
