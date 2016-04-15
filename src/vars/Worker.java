package vars;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Guilherme on 11/04/2016.
 */
public class Worker {
    protected static int id = 0;
    private String name;
    private HashMap<String,Double> scopes;

    public Worker(String name) {
        this.id = Worker.id++;
        this.name = name;
        this.scopes = new HashMap<String,Double>();
    }
}
