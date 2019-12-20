package Day06;

import java.util.ArrayList;
import java.util.List;

public class Planet {

    public String name;
    public List<Planet> orbitedBy;

    public Planet(String name) {
        this.name = name;
        this.orbitedBy = new ArrayList<>();
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
