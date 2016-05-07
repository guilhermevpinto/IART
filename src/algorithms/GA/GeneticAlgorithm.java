package algorithms.GA;

import utils.Main;
import vars.Project;

/**
 * Created by Guilherme on 15/04/2016.
 */
public class GeneticAlgorithm {
	private Population population;
	private int numGenerations;

    public GeneticAlgorithm(int numChromossomes, int numGenerations) {
    	this.population = new Population(Main.project.getTasks().size(), Main.project.getWorkers().size(), numChromossomes);
    	this.numGenerations = numGenerations;
    	
    	//System.out.println(Main.project);
    	System.out.println(population);
    }
    
    
}
