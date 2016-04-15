package algorithms.utils;

import algorithms.GeneticAlgorithm;

import java.util.ArrayList;

/**
 * Created by Guilherme on 15/04/2016.
 */
public class Chromosome {
    private GeneticAlgorithm algorithm;
    private ArrayList<Integer> genes;
    private int fitness;


    public Chromosome(GeneticAlgorithm algorithm, int numberOfGenes){
        genes = new ArrayList<Integer>();
        this.algorithm = algorithm;
        this.fitness = 0;
        this.generateGenes(numberOfGenes);
    }

    private void generateGenes(int numberOfGenes) {
        for(int i = 0; i < numberOfGenes; i++) {
            if(Math.random() < 0.5)
                this.genes.add(i, 0);
            else this.genes.add(i, 1);
        }
    }

    public ArrayList<Integer> getGenes() {
        return this.genes;
    }
}
