package Game_Of_Life;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SpotBoardIterator implements Iterator<Spot> {

	private JSpotBoard _board;
	int _x;
	int _y;
	
	public SpotBoardIterator(JSpotBoard board) {
		_board = board;
		_x = 0;
		_y = 0;
	}

	@Override
	public boolean hasNext() {
		return (_y < _board.getSpotHeight());
	}

	@Override
	public Spot next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		Spot s = _board.getSpotAt(_x, _y);
		if (_x < _board.getSpotWidth()-1) {
			_x++;
		} else {
			_x = 0;
			_y++;
		}
		return s;
	}

}