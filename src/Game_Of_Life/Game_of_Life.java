package Game_Of_Life;



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//Worked with Jacky and Selena

public class Game_of_Life {
	
	private static int size;
	static Model model;
	static View view;
	static Controller controller;
	
	public static void main(String[] args) {
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Conway's Game of Life");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		main_frame.setContentPane(top_panel);
		
		JPanel grid = new JPanel();
		grid.setLayout(new GridLayout(2,1));
		JLabel text = new JLabel("Grid Size");
		
		JSlider slider = new JSlider (10,500);
		slider.setPreferredSize(new Dimension(200,20));
		slider.setValue(10);
		size = slider.getValue();
		slider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				if(!slider.getValueIsAdjusting()) {
					grid.add(text);
					top_panel.removeAll();
					
					JSlider value = (JSlider) e.getSource();
					
					size = value.getValue();
					
					view = new View(size);
					model = new Model(size);
					controller = new Controller(view, model);
					
					grid.add(slider);
					top_panel.add(grid,BorderLayout.SOUTH);
					top_panel.add(view,BorderLayout.CENTER);
					
					top_panel.updateUI();
				}
			}
		});

		
		grid.add(text);
		grid.add(slider);
		top_panel.add(grid, BorderLayout.SOUTH);
		model = new Model(size);
		view = new View(size);
		top_panel.add(view, BorderLayout.CENTER);
		controller = new Controller(view, model);
		main_frame.pack();
		main_frame.setVisible(true);
		
		
	}
}