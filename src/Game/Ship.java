package Game;

public class Ship {
	public enum ShipType {
		CARRIER(4, 1), BATTLESHIP(3, 2), CRUISER(2, 3), DESTROYER(1, 4);
		private int size;
		private int number;
		
		private ShipType(int size, int number) {
			this.size = size;
			this.number = number;
		}

		public int getSize() {
			return size;
		}

		public int getNumber() {
			return number;
		}
	}
	
	public enum ShipRotation {
		VERTICAL, HORIZONTAL;
	}
	
	private int cellsKilled;
	private ShipRotation rotation;
	private ShipType type;
	private boolean isKilled;

	public Ship(int cellsKilled, ShipType type, boolean isKilled) {
		super();
		this.cellsKilled = cellsKilled;
		this.type = type;
		this.isKilled = isKilled;
	}

	public boolean isKilled() {
		return isKilled;
	}


	public void setKilled(boolean isKilled) {
		this.isKilled = isKilled;
	}

	public int getCellsKilled() {
		return cellsKilled;
	}


	public void setCellsKilled(int cellsKilled) {
		this.cellsKilled = cellsKilled;
	}

	public void setType(ShipType type) {
		this.type = type;
	}
	
	public ShipRotation getRotation() {
		return rotation;
	}
	
	public ShipType getType() {
		return type;
	}
	
	public void setRotation(ShipRotation rotation) {
		this.rotation = rotation;
	}
	
	
	
}
