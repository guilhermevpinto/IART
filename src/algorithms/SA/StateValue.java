package algorithms.SA;

import java.util.ArrayList;
import java.util.Collections;

import algorithms.DFS.DFS;
import algorithms.GA.Chromosome;
import utils.Main;
import vars.Skill;
import vars.Task;
import vars.Worker;

public class StateValue {
	private static ArrayList<Double> workersFinal; //each position of the array corresponds to the moment that the respective worker will be available
	private static ArrayList<Double> tasksFinal; //each position of the array corresponds to the moment that the respective task will end
	
	/**
	 * @param c Chromosome to be processed
	 * @return Boolean to determine if the representation of the project is viable or not
	 */
	public static int validateWorkersAssignment(ArrayList<Integer> c) {
		int numWorkers = Worker.allWorkers.size();
		int numTasks = Task.allTasks.size();
		int counter = 0;

		for (int i = 0; i < numTasks; i++) {
			boolean assigned = false;
			int scopeId = Task.allTasks.get(i).getScope().getId();
			
			for (int j = 0; j < numWorkers; j++) {
				if(c.get(i*numWorkers + j) == 0) continue;
				
				Worker w = Worker.allWorkers.get(j);
				Skill s = w.getSkillFromScopeId(scopeId);
				
				if(s != null) {
					assigned = true;
				}
			}
			
			if(!assigned)
				counter++;
		}

		return counter*10;
	}

	public static double evaluateState(ArrayList<Integer> c) {
		double projectDuration = DFS.calculateLongestPath(Main.project.getStartingTask());
		int penalty = StateValue.validateWorkersAssignment(c);
		if(penalty != 0)
			return Math.pow(projectDuration/(projectDuration + penalty),2);
		
		initializeWorkersStatus();
		initializeTasksStatus();
		
		for (int i = 0; i < Task.allTasks.size(); i++) {
			processTask(c, i);
		}
		
		return Math.pow(projectDuration/Collections.max(tasksFinal),2);
	}

	private static void initializeTasksStatus() {
		tasksFinal = new ArrayList<Double> ();
		for (int i = 0; i < Task.allTasks.size(); i++)
			tasksFinal.add(i, 0.0);		
	}

	private static void initializeWorkersStatus() {
		workersFinal = new ArrayList<Double> ();
		for (int i = 0; i < Worker.allWorkers.size(); i++)
			workersFinal.add(i, 0.0);
	}

	private static void processTask(ArrayList<Integer> c, int i) {
		Task t = Task.allTasks.get(i);
		double totalPerformance = 0;
		
		double start = getStartingTime(c, i, t);
		
		for (int j = 0; j < Worker.allWorkers.size(); j++) {
			if(c.get(i*Worker.allWorkers.size() + j) == 0) continue;
			
			Worker w = Worker.allWorkers.get(j);
			Skill s = w.getSkillFromScopeId(t.getScope().getId());
			if(s != null){
				totalPerformance += s.getPerformance();
			}
			if (start < workersFinal.get(w.getId()))
				start = workersFinal.get(w.getId());
		}

		double executionTime = t.getDuration() / totalPerformance;
		double finalExecutionTime = start + executionTime;

		tasksFinal.set(t.getId(), finalExecutionTime);

		for (int j = 0; j < Worker.allWorkers.size(); j++) {
			if (c.get(i*Worker.allWorkers.size() + j) == 1)
				workersFinal.set(j, finalExecutionTime);
		}
	}

	private static double getStartingTime(ArrayList<Integer> c, int id, Task t) {
		double result = 0;
		for (int i = 0; i < t.getPrecedences().size(); i++) {
			if(result < tasksFinal.get(t.getPrecedences().get(i).getId()))
				result = tasksFinal.get(t.getPrecedences().get(i).getId());
		}
		for (int i = 0; i < Worker.allWorkers.size(); i++) {
			if(c.get(id*Worker.allWorkers.size() + i) == 1)
				if (workersFinal.get(i) > result)
					result = workersFinal.get(i);
		}
		return result;
	}
}
