package algorithms.GA;

import java.util.ArrayList;


/**
 * Created by Guilherme on 15/04/2016.
 */
public class Chromosome {
	private ArrayList<Integer> genes;
	private float fitness;
	
    public Chromosome(int chromosomeSize){
    	this.genes = new ArrayList<Integer> (chromosomeSize);
    	this.generateGenes(chromosomeSize);
    	this.fitness = 0;
    }

	private void generateGenes(int chromosomeSize) {
		for(int i = 0; i < chromosomeSize; i++)
			if(Math.random() < 0.5)
				this.genes.add(i, 0);
			else this.genes.add(i, 1);
		
	}
	
	public ArrayList<Integer> getGenes() {
		return this.genes;
	}

	public void calculateFitness() {
		if(Fitness.validateWorkersAssignment(this))
			this.fitness = -1;
		else this.fitness = Fitness.evaluateFitness(this);
	}

	public String toString() {
		calculateFitness();
		return this.genes.toString() + " Fitness " + this.fitness;
	}
}
