package vars;

import java.util.ArrayList;

/**
 * Created by Guilherme on 11/04/2016.
 */
public class Task {
	public static int idRef = 0;
	public static ArrayList<Task> allTasks = new ArrayList<Task>();
	
	private int id;
	private String description;
    private ArrayList<Task> precedences; //tasks to be concluded before this.Task starts
    private ArrayList<Task> successors;
    private Scope scope;
    private float duration; // COST : Workers per Month

    public Task(String description, float duration, Scope scope){
    	this.description = description;
        this.successors = new ArrayList<Task>();
        this.duration = duration;
        this.scope = scope;
        this.id = Task.idRef++;
        Task.allTasks.add(this);
    }

    public int getId() {
    	return this.id;
    }
    
    public void addPrecedence(Task t) {
    	t.addSuccessor(this);
    }
    
    private void addSuccessor(Task t) {
    	if(!t.isSuccessor(t))
    		this.successors.add(t);
    }
    	
    public ArrayList<Task> getSuccessors() {
    	return this.successors;
    }
    
    public ArrayList<Task> getPrecedences() {
        return this.precedences;
    }

    public boolean isSuccessor(Task task) {
       for(Task t : this.successors)
    	   if(t.getId() == task.getId())
    		   return true;
       
       return false;
    }

    public Scope getScope() {
        return this.scope;
    }

    
    public float getEstimatedConclusionTime() {
    	float result = this.duration;
    	
    	for (int i = 0; i < this.precedences.size(); i++) {
    		float newEstimatedTime = this.precedences.get(i).getEstimatedConclusionTime() + this.duration;
    		if(result < newEstimatedTime)
    			result = newEstimatedTime;
    	}
    	
    	return result;
    }
    
    public String toString() {
    	return "Task #" + this.id + " | Description: " + this.description + "\n";
    }

	public float getDuration() {
		return this.duration;
	}
}
