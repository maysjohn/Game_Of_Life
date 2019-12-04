package Game_Of_Life;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class View extends JPanel implements ActionListener{
	private int size;
	private List<Game_Of_LifeViewListener> listeners;
	private JSpotBoard Game_of_Life;
	private static int Delay;
	private Play_Time Play_Time;
	JButton play;
	
	public View(int size) {
		
		
		this.size = size;
		Game_of_Life = new JSpotBoard(size, size);
		
		setLayout(new BorderLayout());
		add(Game_of_Life, BorderLayout.CENTER);
		
		JButton clear = new JButton("Clear");	
		JButton random = new JButton("Random");
		JButton advance = new JButton("Advance_Game");
		play = new JButton ("Play");
		play.setActionCommand("start");
		
		
		JPanel options = new JPanel();

		options.add(clear);
		options.add(random);
		options.add(advance);
		options.add(play);
		
		add(options, BorderLayout.SOUTH);
		
		JLabel delay_text = new JLabel ("10 miliseconds - 1 seconds");
		JSlider delay_slider = new JSlider(10,1000,10);
		Delay = delay_slider.getValue();
		delay_slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
			if(!delay_slider.getValueIsAdjusting()) {
				Delay = delay_slider.getValue();
			}	
			}
		});
		
		JPanel slider = new JPanel();
		slider.setLayout(new GridLayout(2,4));
		slider.add(delay_text);
		slider.add(delay_slider);
		add(slider, BorderLayout.NORTH);
		
		
		for(Component component: options.getComponents()) {
			JButton button = (JButton) component;
			button.addActionListener(this);
		}
		
		listeners = new ArrayList<Game_Of_LifeViewListener>();
	}

	public JSpotBoard getBoard() {
		return Game_of_Life;
	}
	
	//FireEvent targets method in Event Class
	//Target String and performs task on String
	
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		JButton button = (JButton) e.getSource();
		String text = button.getText();
		if (text.contentEquals("Clear")){
			ViewEvent(new TypeClear());	
		}
		if (text.contentEquals("Random")) {
			ViewEvent(new TypeRandom());
			
		} 
		if (text.contentEquals("Advance_Game")) {
			ViewEvent(new TypeAdvance_Game());
		}
		if (text.contentEquals("Play") || text.contentEquals("Stop") ) {
			if(command.equals("start")) {
				Play_Time = new Play_Time(this, Delay);
				Play_Time.start();
				play.setText("Stop");
				play.setActionCommand("stop");
			}
			else if (command.equals("stop")) {
				Play_Time.halt();
				try {
					Play_Time.join();
				}
				catch (InterruptedException m) {
				}
					play.setText("Play");
					play.setActionCommand("start");
			}
		}
	}
	
	public void addGame_Of_LifeViewListener(Game_Of_LifeViewListener m) {
		listeners.add(m);
	}
	
	public void removeGame_Of_LifeViewListener(Game_Of_LifeViewListener m) {
		listeners.remove(m);
	}
	
	public void ViewEvent(Game_Of_LifeViewEvent e) {
		for (Game_Of_LifeViewListener m : listeners) {
			m.handleGame_Of_LifeViewEvent(e);
		}
	}
	
	public void updateView(boolean[][] points) {
		for (int m=0; m<points.length; m++) {
			for (int s=0; s<points[0].length; s++) {
				if (points[m][s]) {
					Game_of_Life.getSpotAt(m, s).setSpot();
				} else {
					Game_of_Life.getSpotAt(m, s).clearSpot();
				}
			}
		}
		
		repaint();
	}


}