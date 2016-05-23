package algorithms.GA;

import java.util.ArrayList;


/**
 * Created by Guilherme on 15/04/2016.
 */
public class Chromosome implements Comparable<Chromosome>{
	private ArrayList<Integer> genes;
	private double fitness;
	
    public Chromosome(){
    	this.genes = new ArrayList<Integer> ();
    }

	public void generateGenes(int chromosomeSize) {
		for(int i = 0; i < chromosomeSize; i++)
			if(Math.random() < 0.5)
				this.genes.add(i, 0);
			else this.genes.add(i, 1);
		
		calculateFitness();
	}
	
	public void addGene(int g) {
		this.genes.add(g);
	}
	
	public ArrayList<Integer> getGenes() {
		return this.genes;
	}

	public void calculateFitness() {
		if(!Fitness.validateWorkersAssignment(this))
			this.fitness = -1.0;
		else this.fitness = Fitness.evaluateFitness(this);
	}

	public double getFitness() {
		return this.fitness;
	}
	
	public String toString() {
		return this.genes.toString() + " Fitness " + this.fitness;
	}

	@Override
	public int compareTo(Chromosome arg0) {
		Double f1 = this.getFitness();
		Double f2 = arg0.getFitness();
		if(f1 == -1)
			f1 = Double.MAX_VALUE;
		if(f2 == -1)
			f2 = Double.MAX_VALUE;
		return Double.compare(f1, f2);
	}

}
