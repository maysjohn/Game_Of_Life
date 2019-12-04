package Game_Of_Life;


public class Play_Time extends Thread {
	private View view;
	private boolean play;
	private int Delay;
	
	public Play_Time (View view, int Delay) {
		this.view = view;
		this.Delay = Delay;
		play = true;
	}
	
	public void halt() {
		play = false;
	}
	
	public void run() {
		while(play) {
			try {
				view.ViewEvent(new TypeAdvance_Game());
				Thread.sleep(Delay);
				view.updateUI();
		}
			catch (InterruptedException e) {
				
			}
	}

}}
