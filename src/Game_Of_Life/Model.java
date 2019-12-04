package Game_Of_Life;

import java.util.Random;

public class Model {

	private final double RANDOMIZATION = 0.55;
	
	private boolean[][] colony;
	private int size;
	
	public Model(int size) {
		this.size = size;
		
		colony = new boolean[size][size];
	}
	
	public boolean[][] getState() {
		return colony;
	}
	
	public void clear() {
		colony = new boolean[size][size];
	}
	
	public void randomize() {
		this.clear();
		
		Random random = new Random();
		for (int i=0; i<size*size*RANDOMIZATION; i++) {
			colony[random.nextInt(size)][random.nextInt(size)] = true;
		}
	}
	
	public void advanceGame() {
		boolean[][] tempCoords = new boolean[size][size];
		
		for (int m=0; m<size; m++) {
			for (int j=0; j<size; j++) {
				int numLiveNeighbors = getLiveNeighbors(m, j);
				
				if (colony[m][j]) {
					if (numLiveNeighbors > 1 && numLiveNeighbors < 4) {
						tempCoords[m][j] = true;
					}
				} else {
					if (numLiveNeighbors == 3) {
						tempCoords[m][j] = true;
					}
				}
			}
		}
		
		colony = tempCoords;
	}
	
	public void togglePoint(int m, int y) {
		if (colony[m][y]) {
			colony[m][y] = false;
		} else {
			colony[m][y] = true;
		}
	}
	
	private int getLiveNeighbors(int m, int y) {
		int numLiveNeighbors = 0;
	
		if (m-1 >= 0) {
			if (y-1 >= 0 && colony[m-1][y-1]) {
				numLiveNeighbors++;
			}
			if (colony[m-1][y]) {
				numLiveNeighbors++;
			} 
			if (y+1 < size && colony[m-1][y+1]) {
				numLiveNeighbors++;
			}
		}
		
		if (m+1 < size) {
			if (y-1 >= 0 && colony[m+1][y-1]) {
				numLiveNeighbors++;
			}
			if (colony[m+1][y]) {
				numLiveNeighbors++;
			}
			if (y+1 < size && colony[m+1][y+1]) {
				numLiveNeighbors++;
			}
		}
		
		if (y+1 < size && colony[m][y+1]) {
			numLiveNeighbors++;
		}
		if (y-1 >= 0 && colony[m][y-1]) {
			numLiveNeighbors++;
		}
		
		
		return numLiveNeighbors;
	}
}