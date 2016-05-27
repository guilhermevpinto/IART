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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import algorithms.SA.SimulatedAnnealing;

public class SwitSimulatedAnnealing implements ActionListener{
	private JFrame mainFrame;
	private JButton run;
	private JButton back;
	private JSpinner ratio;
	private JSpinner T;
	
	public SwitSimulatedAnnealing(JFrame frame) {
		this.mainFrame = frame;
		
		this.setupAlgorithm();
		this.mainFrame.pack();
		this.mainFrame.setLocationRelativeTo(null);
	}

	private void setupAlgorithm() {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("src/res/annealing.jpg"));
		} catch (IOException e) {}
		ImageIcon imageIcon = new ImageIcon(img);
		this.mainFrame.setContentPane(new JLabel(imageIcon));
		
		JLabel label = (JLabel) this.mainFrame.getContentPane();

		this.run = new JButton("Run Algorithm");
		this.run.addActionListener(this);
		this.back = new JButton("Back");
		this.back.addActionListener(this);
		this.ratio = new JSpinner(new SpinnerNumberModel(0.5, Double.MIN_VALUE, 1, 0.01));
		this.T = new JSpinner(new SpinnerNumberModel(100, 0, Double.MAX_VALUE, 0.1));

		label.add(this.run);
		label.add(this.back);
		label.add(this.ratio);
		label.add(this.T);

		this.ratio.setBounds(360, 150, 100, 30);
		this.T.setBounds(360, 205, 100, 30);
		this.run.setBounds(125, 300, 200, 40);
		this.back.setBounds(425, 300, 200, 40);
		
		JLabel text = new JLabel("<html> <h3>Decreasing Ratio: <br/><br/><br/> Temperature</h3></html>");
		text.setOpaque(true);
		text.setBackground(new Color(255,255,255,200));
		label.add(text);
		
		JLabel title = new JLabel("<html> <h1>Simulated Annealing</h1></html>");
		title.setOpaque(true);
		title.setBackground(new Color(250,220,165,200));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		label.add(title);
		
		text.setBounds(200,140,300,100);
		title.setBounds(0,0,300,50);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("Run Algorithm")) {
			double ratio = (Double) this.ratio.getValue();
			double T = (Double) this.T.getValue();
			
			SimulatedAnnealing sa = new SimulatedAnnealing(ratio, T);
			sa.run();
			
			System.out.println("Simulated Annealing with parameters (ratio,T) = (" + ratio + "," + T + ")"); 
			System.out.println(sa.getFinalData());
			System.out.println(sa.getFinalValue());
			return;
		}
		else if (arg0.getActionCommand().equals("Back")){
			this.mainFrame.remove(this.mainFrame.getContentPane());
			this.mainFrame.revalidate();
			this.mainFrame.repaint();
			new SwitProject(this.mainFrame);
		}
	}
	
}
