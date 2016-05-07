package algorithms.GA;

import java.util.ArrayList;


/**
 * Created by Guilherme on 15/04/2016.
 */
public class Chromosome {
	private ArrayList<Integer> genes;
	
    public Chromosome(int chromosomeSize){
    	this.genes = new ArrayList<Integer> (chromosomeSize);
    	this.generateGenes(chromosomeSize);
    }

	private void generateGenes(int chromosomeSize) {
		for(int i = 0; i < chromosomeSize; i++)
			if(Math.random() < 0.5)
				this.genes.add(i, 0);
			else this.genes.add(i, 1);
		
	}

	public float getFitness() {
		float result;
		
		if(!Fitness.checkWorkersAssignment(this))
			return -1;
			
		result = Fitness.checkTasksPerformance(this);
		
		return result;
	}

	public String toString() {
		return this.genes.toString();
	}
}
