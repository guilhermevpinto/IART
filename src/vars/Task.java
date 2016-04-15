package vars;

import java.util.ArrayList;

/**
 * Created by Guilherme on 11/04/2016.
 */
public class Task {
    private ArrayList<Worker> workers; //workers involved in this task
    private ArrayList<Task> precedences; //tasks to be concluded before this.Task starts
    private Scope scope;
    private double duration; // COST : Workers per Month


    public Task(ArrayList<Task> tasks, double duration, Scope scope){
        this.workers = new ArrayList<Worker>();
        this.precedences = tasks;
        this.duration = duration;
        this.scope = scope;
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

    public ArrayList<Task> getPrecedences() {
        return this.precedences;
    }

    public Task getPrecedence(int i) {
        try {
            Task t = this.precedences.get(i);
            return t;
        } catch(IndexOutOfBoundsException e) {
            return null;
        }
    }

    public Scope getScope() {
        return this.scope;
    }


}
