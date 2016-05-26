package gui;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.Main;

public class SwitProject {
	private JFrame mainFrame;
	
	public SwitProject(JFrame frame) {
		this.mainFrame = frame;
		this.mainFrame.setContentPane(new JPanel());
		this.mainFrame.getContentPane().setLayout(null);
		
		new Main();
		
		this.setupProject();
	}
	
	public void setupProject() {
		JPanel panel = (JPanel) this.mainFrame.getContentPane();
		Insets insets = panel.getInsets();
		
		JLabel label = new JLabel("Ola, entao? tudo bem amigo?");
		Dimension dim = label.getPreferredSize();
		panel.add(label);
		label.setBounds(insets.left, insets.top, dim.width, dim.height);
	}
}
