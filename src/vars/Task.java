package vars;

import java.util.ArrayList;

/**
 * Created by Guilherme on 11/04/2016.
 */
public class Task {
	protected static int idRef = 0;
	private String description;
    private ArrayList<Worker> workers; //workers involved in this task
    private ArrayList<Task> precedences; //tasks to be concluded before this.Task starts
    private Scope scope;
    private double duration; // COST : Workers per Month
    private int id;

    public Task(String description, double duration, Scope scope){
    	this.description = description;
        this.workers = new ArrayList<Worker>();
        this.precedences = new ArrayList<Task>();
        this.duration = duration;
        this.scope = scope;
        this.id = Task.idRef++;
    }

    public ArrayList<Worker> getWorkers() {
        return this.workers;
    }

    public Worker getWorker(int i) {
        try {
            Worker w = this.workers.get(i);
            return w;
        } catch(IndexOutOfBoundsException e) {
            return null;
        }
    }

    public boolean addWorker(Worker w) {
        if(this.workers.contains(w)) {
            return false;
        }
        else this.workers.add(w);
        return true;
    }

    public void addPrecedence(Task t) {
    	if(!t.isPrecedence(this))
    		this.precedences.add(t);
    }
    
    public ArrayList<Task> getPrecedences() {
        return this.precedences;
    }

    public boolean isPrecedence(Task task) {
       for(Task t : this.precedences)
    	   if(t.getId() == task.getId())
    		   return true;
       
       return false;
    }

    public Scope getScope() {
        return this.scope;
    }

    public int getId() {
    	return this.id;
    }
    
    
    public String toString() {
    	return "Task #" + this.id + " | Description: " + this.description + "\n";
    }
}
