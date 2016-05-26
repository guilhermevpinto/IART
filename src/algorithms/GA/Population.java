package algorithms.GA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Population {
	
	private ArrayList<Chromosome> chromosomes;

	public Population(int numTasks, int numWorkers, int numChromosomes) {
		this.chromosomes = new ArrayList<Chromosome>(numChromosomes);
		generateChromosomes(numChromosomes, numTasks*numWorkers);
		
	}

	private void generateChromosomes(int numChromosomes, int chromosomeSize) {
		for(int i = 0; i < numChromosomes; i++){
			Chromosome c = new Chromosome();
			c.generateGenes(chromosomeSize);
			this.chromosomes.add(i, c);
		}
		this.sortChromosomes();
	}
	
	private void sortChromosomes() {
			Collections.sort(this.chromosomes);
	}
	
	public String toString() {
		String msg = "";
		for (int i = 0; i < this.chromosomes.size(); i++){
			msg += "Chromosome #" + (i+1) + ": " + this.chromosomes.get(i).toString() + "\n";
		}
		
		return msg;
	}

	public void evolve(int numChromosomes, int numSelections, int cutsPerCrossover, double mutationP) {
		this.selection(numSelections);
		
		while(this.chromosomes.size() < numChromosomes) {
			this.crossoverWithMutation(numChromosomes, cutsPerCrossover, mutationP, numSelections);
		}
		
		this.sortChromosomes();
	}
	
	private void selection(int numSelections) {
		//the array of chromosomes is sorted by fitness. the last ones are the ones to be deleted
		//removes all chromosomes with fitness -1.
		//only select a maximum of 'numSelections' fittest chromosomes.
		for (int i = 0; i < this.chromosomes.size(); i++) {
			if (numSelections != -1)
			{
				if (i >= numSelections || this.chromosomes.get(i).getFitness() == -1){
					this.chromosomes.remove(i);
					i--;
				}
			}
			else if (this.chromosomes.get(i).getFitness() == -1){
				this.chromosomes.remove(i);
				i--;
			}
			
		}
	}
	
	private void crossoverWithMutation(int numChromosomes, int cutsPerCrossover, double mutationP, int numSelections) {
		ArrayList<Integer> cuts = generateCuts(cutsPerCrossover);
		
		Chromosome c = new Chromosome();
		Chromosome c1 = null;
		Chromosome c2 = null;
		
		if(numSelections != -1) {
			c1 = this.chromosomes.get(0);
			c2 = this.chromosomes.get(1);
		}
		else {
			c1 = getChromosomeForCrossover();
			c2 = getChromosomeForCrossover();
		}
		
		int chromosomeSize = c1.getGenes().size();
		int current = 1;
		for (int i = 0; i < chromosomeSize; i++) {
			if (cuts.contains(i)) {
				if (current == 1)
					current = 2;
				else current = 1;
			}
			
			int g;
			
			if(current == 1)
				g = c1.getGenes().get(i);
			else g = c2.getGenes().get(i);
			
			if(Math.random() < mutationP) {
				if(g == 0)
					g =1;
				else g = 0;
			}
			
			c.addGene(g);
		}
		
		c.calculateFitness();
		this.chromosomes.add(c);
	}

	private ArrayList<Integer> generateCuts(int cutsPerCrossover) {
		int minIndexCut = 1;
		int maxIndexCut = chromosomes.get(0).getGenes().size() - 1;
		ArrayList<Integer> cuts = new ArrayList<Integer>();
		Random r = new Random();
		
		//Generation of the cuts indexes to cross the fittest chromosomes of the population
		int i = 0;
		while(i < cutsPerCrossover) {
			int cutIndex = r.nextInt((maxIndexCut - minIndexCut) + 1) + minIndexCut;
			if(!cuts.contains(cutIndex)){
				i++;
				cuts.add(cutIndex);
			}
		}
		
		return cuts;
	}
	
	private Chromosome getChromosomeForCrossover() {
		ArrayList<Double> fitnesses = new ArrayList<Double>();
		
		double total = 0;
		for(int i = 0; i < this.chromosomes.size(); i++) {
			total += 1/this.chromosomes.get(i).getFitness();
			fitnesses.add(total);
		}

		double r = Math.random()*total;
		for(int i = 0; i < this.chromosomes.size(); i++) {
			if(r < fitnesses.get(i)){
				return this.chromosomes.get(i);
			}
		}
		return null;
	}
	
}
