package Game_Of_Life;

public abstract class Game_Of_LifeViewEvent {
	
	public boolean isClear() {
		return false;
	}
	public boolean isRandom() {
		return false;
	}
	public boolean isAdvance_Game() {
		return false;
	}
	public abstract String getString();
	
}
	class TypeClear extends Game_Of_LifeViewEvent {
		
		public TypeClear() {}
		
		public String getString() {
			return "Clear";
		}
		public boolean getClear() {
			return true;
		}
	}
	class TypeRandom extends Game_Of_LifeViewEvent {
		
		public TypeRandom() {}
		
		public String getString() {
			return "Random";
		}
		public boolean isRandom() {
			return true;
		}	
	}
	class TypeAdvance_Game extends Game_Of_LifeViewEvent {
		
		public TypeAdvance_Game() {}
		
		public String getString() {
			return "Advance_Game";
		}
		public boolean isAdvance_Game() {
			return true;
		}	
	}
