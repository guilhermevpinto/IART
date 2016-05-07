package vars;

import java.util.ArrayList;

/**
 * Created by Guilherme on 11/04/2016.
 */
public class Project {
    private String description;
    private ArrayList<Task> tasks;
    private ArrayList<Worker> workers;
    
    public Project(String description) {
    	this.description = description;
    	this.tasks = new ArrayList<Task>();
    	this.workers = new ArrayList<Worker>();
    }
    
    public void addTask(Task t) {
    	this.tasks.add(t);
    }
    
    public void addWorker(Worker w) {
    	this.workers.add(w);
    }
    
    public ArrayList<Task> getTasks() {
    	return this.tasks;
    }
    
    public String getDescription() {
    	return this.description;
    }
    
    public String toString() {
    	String display = this.description + '\n';
    	for(Task t : this.tasks)
    		display += t.toString();
    	
    	return display;
    }

    public ArrayList<Worker> getWorkers() {
    	return this.workers;
    }
}
