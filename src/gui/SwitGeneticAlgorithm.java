package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import algorithms.GA.GeneticAlgorithm;

public class SwitGeneticAlgorithm implements ActionListener{
	private JFrame mainFrame;
	private JButton run;
	private JButton back;
	private JSpinner numChromosomes;
	private JSpinner numGenerations;
	private JSpinner numSelections;
	private JSpinner cutsPerCrossover;
	private JSpinner mutationP;
	private JCheckBox elitist;
	
	public SwitGeneticAlgorithm(JFrame frame) {
		this.mainFrame = frame;
	
		this.setupAlgorithm();
		this.mainFrame.pack();
		this.mainFrame.setLocationRelativeTo(null);
	}

	private void setupAlgorithm() {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("src/res/genetic.jpg"));
		} catch (IOException e) {}
		ImageIcon imageIcon = new ImageIcon(img);
		this.mainFrame.setContentPane(new JLabel(imageIcon));
		
		JLabel label = (JLabel) this.mainFrame.getContentPane();
		
		this.run = new JButton("Run Algorithm");
		this.run.addActionListener(this);
		this.back = new JButton("Back");
		this.back.addActionListener(this);
		this.numChromosomes = new JSpinner(new SpinnerNumberModel(10, 5, Integer.MAX_VALUE, 5));
		this.numGenerations = new JSpinner(new SpinnerNumberModel(100, 10, Integer.MAX_VALUE, 10));
		this.numSelections = new JSpinner(new SpinnerNumberModel(5, 2, Integer.MAX_VALUE, 1));
		this.numSelections.setVisible(false);
		this.cutsPerCrossover = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
		this.mutationP = new JSpinner(new SpinnerNumberModel(0.1, 0, 1, 0.01));
		this.elitist = new JCheckBox();
		this.elitist.addActionListener(this);
		
		
		label.add(this.run);
		label.add(this.back);
		label.add(this.numChromosomes);
		label.add(this.numGenerations);
		label.add(this.mutationP);
		label.add(this.cutsPerCrossover);
		label.add(this.elitist);
		label.add(this.numSelections);
		
		
		JLabel text = new JLabel("<html> <h3>No Chromosomes: &emsp &emsp &emsp &emsp &emsp &emsp &emsp &emsp &emsp No Generations: </h3><br/>"
				+ "<h3>Mutation Probability: &emsp &emsp &emsp &emsp &emsp &emsp &emsp &emsp Cuts Per Crossover: </h3> <br/>"
				+ "<h3> No Selections (Elitist): </h3></html>");
		text.setLayout(null);
		text.setOpaque(true);
		text.setBackground(new Color(255,255,255,200));
		label.add(text);
		
		this.run.setBounds(150, 300, 200, 40);
		this.back.setBounds(450, 300, 200, 40);
		this.numChromosomes.setBounds(250, 112, 80, 20);
		this.numGenerations.setBounds(550, 112, 80, 20);
		this.mutationP.setBounds(250, 168, 80, 20);
		this.cutsPerCrossover.setBounds(550, 168, 80, 20);
		this.elitist.setBounds(260, 220, 20, 20);
		this.numSelections.setBounds(300, 220, 80, 20);
		
		JLabel title = new JLabel("<html> <h1>Genetic Algorithm</h1></html>");
		title.setOpaque(true);
		title.setBackground(new Color(190,235,255,200));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		label.add(title);

		title.setBounds(0,0,300,50);
		text.setBounds(100,100,600,150);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		 if (arg0.getActionCommand().equals("Back")) {
			this.mainFrame.remove(this.mainFrame.getContentPane());
			this.mainFrame.revalidate();
			this.mainFrame.repaint();
			new SwitProject(this.mainFrame);
		 }
		 else if (arg0.getActionCommand().equals("Run Algorithm")) {
			 int numChromosomes = (int) this.numChromosomes.getValue();
			 int numGenerations = (int) this.numGenerations.getValue();
			 int numSelections = -1;
			 if (this.elitist.isSelected())
				 numSelections = (int) this.numSelections.getValue();
			 int cutsPerCrossover = (int) this.cutsPerCrossover.getValue();
			 double mutationP = (double) this.mutationP.getValue();
			 
			 System.out.println(numChromosomes);
			 System.out.println(numGenerations);
			 System.out.println(numSelections);
			 System.out.println(cutsPerCrossover);
			 System.out.println(mutationP);
			 new GeneticAlgorithm(numChromosomes, numGenerations, numSelections, cutsPerCrossover, mutationP);
		 }
		 else if (arg0.getSource() == this.elitist) {
			 if (this.elitist.isSelected())
				 this.numSelections.setVisible(true);
			 else this.numSelections.setVisible(false);
		 }
		
	}

}
