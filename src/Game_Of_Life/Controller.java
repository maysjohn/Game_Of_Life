

package Game_Of_Life;

public class Controller implements SpotListener, Game_Of_LifeViewListener {
	
	private View view;
	private Model model;
	
	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;

		view.addGame_Of_LifeViewListener(this);
		view.getBoard().addSpotListener(this);
	}

	@Override
	public void handleGame_Of_LifeViewEvent(Game_Of_LifeViewEvent e) {
		switch (e.getString()) {
		
		case "Clear": 
			model.clear();
			break;
		
		case "Random":
			model.randomize();
			break;
			
		case "Advance_Game": 
			model.advanceGame();
			break;
		}
		//Updated the View
		view.updateView(model.getState()); 
	}
	public void spotEntered(Spot spot) { }
	
	public void spotExited(Spot spot) { }
	
	public void spotClicked(Spot spot) {
		spot.toggleSpot();
		model.togglePoint(spot.getSpotX(), spot.getSpotY());
	}
}
