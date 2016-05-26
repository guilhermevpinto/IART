package vars;

import algorithms.DFS.DFS;

/**
 * Created by Guilherme on 11/04/2016.
 */
public class Project {
    private String description;
    private Task startingTask;
    
    public Project(String description, Task t) {
    	this.description = description;
    	this.startingTask = t;
    }
    
    public Task getStartingTask() {
    	return this.startingTask;
    }
    
    public String getDescription() {
    	return this.description;
    }
    
    public String toString() {
    	String display = this.description + '\n';
    	for(Task t : Task.allTasks)
    		display += t.toString();
    	
    	display += '\n' + "Estimated Conclusion Time: " + DFS.calculateLongestPath(this.startingTask) + " Months.\n";
    	
    	return display;
    }

}
