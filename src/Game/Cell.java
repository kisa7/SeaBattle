package Game;

public class Cell {

	private int coordinateX;
	private int coordinateY;
	private CellStatus status;
	Ship ship;
	private boolean isVisited;


	public enum CellStatus{
		FREE('~'), BLOCKED('-'), OCCUPIED('#'), SHIP_ATTACKED('X'), MISS_ATTACK('o'), UNKNOWN('~');
		char picture;
		
		private CellStatus(char picture) {
			this.picture = picture;
		}
	}

	public Cell(int coordinateX, int coordinateY, CellStatus status) {
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
		this.status = status;
	}

	public Cell(int coordinateX, int coordinateY, CellStatus status, boolean isVisited) {
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
		this.status = status;
		this.isVisited = false;
	}
	


	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}
	
	public CellStatus getStatus() {
		return status;
	}
	public void setShip(Ship ship) {
		this.ship = ship;
	}

	int getCoordinateX() {
		return coordinateX;
	}

	public int getCoordinateY() {
		return coordinateY;
	}

	public void setStatus(CellStatus status) {
		this.status = status;
	}

	public Ship getShip() {
		return ship;
	}
	

}
