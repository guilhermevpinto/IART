package algorithms.GA;

import vars.Task;
import vars.Worker;

/**
 * Created by Guilherme on 15/04/2016.
 */
public class GeneticAlgorithm {
	private Population population;
	private int numChromosomes;
	private int numGenerations;
	private int numSelections;
	private int cutsPerCrossover;
	private double mutationP;

    public GeneticAlgorithm(int numChromossomes, int numGenerations, int numSelections, int cutsPerCrossover, double mutationP) {
    	this.population = new Population(Task.allTasks.size(), Worker.allWorkers.size(), numChromossomes);
    	this.numChromosomes = numChromossomes;
    	this.numGenerations = numGenerations;
    	this.numSelections = numSelections;
    	this.cutsPerCrossover = cutsPerCrossover;
    	this.mutationP = mutationP;    	
    	this.run();
    }

	private void run() {
		int i = 0;
		while(i++ < this.numGenerations) {
			this.population.evolve(this.numChromosomes, this.numSelections, this.cutsPerCrossover, this.mutationP);
		}
		
		System.out.println("\n\nFinal Generation Number");
    	System.out.println(population);
	}
    
    
}
