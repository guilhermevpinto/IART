package algorithms.GA;

import java.util.ArrayList;
import java.util.Collections;

import vars.*;

public class Fitness {
	private static ArrayList<Double> workersFinal; //each position of the array corresponds to the moment that the respective worker will be available
	private static ArrayList<Double> tasksFinal; //each position of the array corresponds to the moment that the respective task will end
	
	/**
	 * @param c Chromosome to be processed
	 * @return Boolean to determine if the representation of the project is viable or not
	 */
	public static boolean validateWorkersAssignment(Chromosome c) {
		int numWorkers = Worker.allWorkers.size();
		int numTasks = Task.allTasks.size();

		for (int i = 0; i < numTasks; i++) {
			boolean assigned = false;
			int scopeId = Task.allTasks.get(i).getScope().getId();
			
			for (int j = 0; j < numWorkers; j++) {
				if(c.getGenes().get(i*numWorkers + j) == 0) continue;
				
				Worker w = Worker.allWorkers.get(j);
				Skill s = w.getSkillFromScopeId(scopeId);
				
				if(s != null) {
					assigned = true;
					break;
				}
			}
			
			if(!assigned)
				return false;
		}

		return true;
	}

	/**
	 * @param c Chromosome to be processed
	 * @return Float with the value of the fitness calculated
	 */
	public static double evaluateFitness(Chromosome c) {
		initializeWorkersStatus();
		initializeTasksStatus();
		
		for (int i = 0; i < Task.allTasks.size(); i++) {
			processTask(c, i);
		}
		
		return Collections.max(tasksFinal);
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

	private static void processTask(Chromosome c, int i) {
		Task t = Task.allTasks.get(i);
		double totalPerformance = 0;
		
		double start = getStartingTime(t);
		
		for (int j = 0; j < Worker.allWorkers.size(); j++) {
			if(c.getGenes().get(i*Worker.allWorkers.size() + j) == 0) continue;
			
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
			if (c.getGenes().get(i*Worker.allWorkers.size() + j) == 1)
				workersFinal.set(j, finalExecutionTime);
		}
	}

	private static double getStartingTime(Task t) {
		double result = 0;
		for (int i = 0; i < t.getPrecedences().size(); i++) {
			if(result < tasksFinal.get(t.getPrecedences().get(i).getId()))
				result = tasksFinal.get(t.getPrecedences().get(i).getId());
		}
		return result;
	}

}
