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
	
	public SimulatedAnnealing(double ratio) {
		initializeData();
		this.T = Math.pow(DFS.calculateLongestPath(Main.project.getStartingTask()),2);
		this.currentState = StateValue.evaluateState(this.data);
		this.ratio = ratio;
		run();
		
		System.out.println((1/Math.sqrt(this.currentState))*DFS.calculateLongestPath(Main.project.getStartingTask()));
		System.out.println(this.data);
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
	
	
	private void run() {
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
			System.out.println("Diferenca: " + diff);
			if (diff > 0) {
				this.currentState = nextState;
				this.data = nextData;
				System.out.println(this.currentState);
			}
			else {
				double v = Math.exp(diff/this.T);
				System.out.println("o valor do double v : " + v);
				if (Math.random() < v) {
					this.currentState = nextState;
					this.data = nextData;
					System.out.println("regrediu: " + this.currentState);
				}
			}
			
			this.T *= this.ratio;
		}
	}
	
	private ArrayList<Integer> copyOf(ArrayList<Integer> c) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		for(int i = 0; i < c.size(); i++)
			result.add(c.get(i));
		
		return result;
	}
}