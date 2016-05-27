package algorithms.SA;

import java.util.ArrayList;


import algorithms.DFS.DFS;
import utils.Main;
import vars.Task;
import vars.Worker;

public class SimulatedAnnealing {
	private ArrayList<Integer> data;
	private double T;
	private double ratio;
	private double currentState;
	
	public SimulatedAnnealing(double ratio, double T) {
		initializeData();
		this.T = T;
		this.currentState = StateValue.evaluateState(this.data);
		this.ratio = ratio;
		
	}
	
	private void initializeData() {
		this.data = new ArrayList<Integer> ();
		int length = Task.allTasks.size() * Worker.allWorkers.size();
		for (int i = 0; i < length; i++) {
			if(Math.random() < 0.5)
				this.data.add(0);
			else this.data.add(1);
		}
	}
	
	
	public void run() {
		ArrayList<Integer> nextData;
		while (this.T > Double.MIN_NORMAL){			
			nextData = this.copyOf(this.data);
			for (int i = 0; i < nextData.size(); i++) {
				if(Math.random() < 0.5) {
					if(nextData.get(i) == 0)
						nextData.set(i, 1);
					else nextData.set(i, 0);
				}
			}
			
			double nextState = StateValue.evaluateState(nextData);
			double diff = nextState - this.currentState;
			if (diff > 0) {
				this.currentState = nextState;
				this.data = nextData;
			}
			else {
				double v = Math.exp(diff/this.T);
				if (Math.random() < v) {
					this.currentState = nextState;
					this.data = nextData;
				}
			}
			
			System.out.println("Current Data: " + this.data);
			System.out.println("Value: " + (1/Math.sqrt(this.currentState))*DFS.calculateLongestPath(Main.project.getStartingTask()));
			System.out.println();
			
			this.T *= this.ratio;
		}
	}
	
	private ArrayList<Integer> copyOf(ArrayList<Integer> c) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		for(int i = 0; i < c.size(); i++)
			result.add(c.get(i));
		
		return result;
	}
	
	
	public ArrayList<Integer> getFinalData() {
		return this.data;
	}
	
	public Double getFinalValue() {
		return (1/Math.sqrt(this.currentState))*DFS.calculateLongestPath(Main.project.getStartingTask());
	}
}