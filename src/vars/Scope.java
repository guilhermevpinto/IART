package vars;

/**
 * Created by Guilherme on 12/04/2016.
 */
public class Scope {
    protected static int idRef = 0;
    private int id;
    private String description;

    public Scope(String description) {
        this.id = Scope.idRef++;
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
    
    public int getId() {
    	return this.id;
    }
}
