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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import utils.Main;


public class SwitProject implements ActionListener{
	private JFrame mainFrame;
	private JButton sa;
	private JButton ga;
	private JButton project;
	
	public SwitProject(JFrame frame) {
		this.mainFrame = frame;
		
		if(!Main.set)
			new Main();
		
		this.setupProject();
		this.mainFrame.pack();
		this.mainFrame.setLocationRelativeTo(null);
	}
	
	public void setupProject() {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("src/res/algorithms.jpg"));
		} catch (IOException e) {}
		ImageIcon imageIcon = new ImageIcon(img);
		this.mainFrame.setContentPane(new JLabel(imageIcon));
		
		JLabel label = (JLabel) this.mainFrame.getContentPane();
		
		this.sa = new JButton("Simulated Annealing");
		this.sa.addActionListener(this);
		this.ga = new JButton("Genetic Algorithm");
		this.ga.addActionListener(this);
		this.project = new JButton("Project Info");
		this.project.addActionListener(this);

		label.add(this.sa);
		label.add(this.ga);
		label.add(this.project);
		
		this.sa.setBounds(200, 500, 200, 50);
		this.ga.setBounds(500, 500, 200, 50);
		this.project.setBounds(350, 50, 200, 50);
		

		JLabel algorithms = new JLabel("SELECT THE ALGORITHM YOU WISH TO RUN");
		algorithms.setOpaque(true);
		algorithms.setBackground(new Color(255,255,255,100));
		algorithms.setBounds(250, 400, 400, 80);
		algorithms.setHorizontalAlignment(SwingConstants.CENTER);
		label.add(algorithms);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Simulated Annealing")) {
			this.mainFrame.remove(this.mainFrame.getContentPane());
			this.mainFrame.revalidate();
			this.mainFrame.repaint();
			new SwitSimulatedAnnealing(this.mainFrame);
			return;
		}
		else if (arg0.getActionCommand().equals("Genetic Algorithm")) {
			this.mainFrame.remove(this.mainFrame.getContentPane());
			this.mainFrame.revalidate();
			this.mainFrame.repaint();
			new SwitGeneticAlgorithm(this.mainFrame);
			return;
		}
	}
}
