package vars;

/**
 * Created by Guilherme on 12/04/2016.
 */
public class Scope {
    protected static int id;
    private String type;
    private double performance;

    public Scope(String type, double performance) {
        this.id = Scope.id++;
        this.type = type;
        this.performance = performance;
    }

    public String getType() {
        return this.type;
    }
}
