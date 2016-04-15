package algorithms;

/**
 * Created by Guilherme on 15/04/2016.
 */
public class GeneticAlgorithm {

    private double crossoverRate;
    private double mutationRate;

    public GeneticAlgorithm(double crossoverRate, double mutationRate) {
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
    }
}
