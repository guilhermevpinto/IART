package gui;

import java.awt.Desktop;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainSwit implements ActionListener {
	
	private JFrame mainFrame;
	private JButton newProject;
	private JButton about;
	
	public static void main(String[] args) {
		new MainSwit();
	}
	
	public MainSwit() {
		this.mainFrame = new JFrame("IART - Project Management Optimization");
		this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mainFrame.setResizable(false);
		this.mainFrame.getContentPane().setLayout(null);
		this.mainFrame.setSize(900,600);

		this.setPanelContent();
		this.mainFrame.setLocationRelativeTo(null);
		this.mainFrame.setVisible(true);
		this.mainFrame.pack();
	}
	
	private void setPanelContent() {
		this.newProject = new JButton("Run Project");
		this.about = new JButton("About The Assignement");
		
		this.newProject.addActionListener(this);
		this.about.addActionListener(this);
				
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("src/res/main.jpg"));
		} catch (IOException e) {}
		ImageIcon imageIcon = new ImageIcon(img);
		this.mainFrame.setContentPane(new JLabel(imageIcon));

		JLabel panel = (JLabel) this.mainFrame.getContentPane();
		Insets insets = panel.getInsets();
		
		panel.add(newProject);
		panel.add(about);
		
		this.newProject.setBounds(insets.left + 100, insets.top + 500, 300, 50);
		this.about.setBounds(insets.left + 500, insets.top + 500, 300, 50);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(this.newProject == e.getSource()) {
			this.mainFrame.removeAll();
			new SwitProject(this.mainFrame);
		}
		else if(this.about == e.getSource()){
			try {
				Desktop.getDesktop().browse(new URI("https://web.fe.up.pt/~eol/IA/1516/TRABALHOS/opt_gestaoproj.html"));
			}catch (Exception e1) {}
		}
		
	}
}
