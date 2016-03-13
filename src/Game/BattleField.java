package Game;

import Game.Cell.CellStatus;
import Game.Ship.ShipType;

public class BattleField {
	private final static int FIELD_SIZE = 10;
	private final static int SHIPS_NUMBER= 10;
	private Cell[][] field = new Cell[FIELD_SIZE][FIELD_SIZE];
	private Ship[] ships = new Ship[SHIPS_NUMBER];

	public BattleField() {
		int shipIndex = 0;
		for(int i = 0; i < FIELD_SIZE; i++) {
			for(int j = 0; j < FIELD_SIZE; j++) {
				field[i][j] = new Cell(i, j, CellStatus.FREE);
			}
		}
		ships[shipIndex++] = new Ship(0, ShipType.CARRIER, false);
		for(int i = 0; i < ShipType.BATTLESHIP.getNumber(); i++) {
			ships[shipIndex++] = new Ship(0, ShipType.BATTLESHIP, false);
		}
		for(int i = 0; i < ShipType.CRUISER.getNumber(); i++) {
			ships[shipIndex++] = new Ship(0, ShipType.CRUISER, false);
		}
		for(int i = 0; i < ShipType.DESTROYER.getNumber(); i++) {
			ships[shipIndex++] = new Ship(0, ShipType.DESTROYER, false);
		}
	}
	
	public BattleField(boolean enemy) {
		for(int i = 0; i < FIELD_SIZE; i++) {
			for(int j = 0; j < FIELD_SIZE; j++) {
				field[i][j] = new Cell(i, j, CellStatus.UNKNOWN, false);
			}
		}
	}

	
	public Ship[] getShips() {
		return ships;
	}
	
	public Ship getShip(int index) {
		return ships[index];
	}

	public Cell[][] getField() {
		return field;
	}
	
	public Cell getCell(int coordinateX, int coordinateY) {
		if((coordinateX >= 0 && coordinateX < FIELD_SIZE) && (coordinateY >= 0 && coordinateY < FIELD_SIZE)) {
			return field[coordinateX][coordinateY];
		}
		else {
			return null;
		}
	}

	public String printFieldLine(int lineNumber) {
		String fieldLine = String.format("%2d",  lineNumber);
		if(lineNumber == 0) {
			return "   A B C D E F G H I J";
		}
		else if(lineNumber <= FIELD_SIZE){
			for(int i = 0; i < FIELD_SIZE; i++) {
				fieldLine += " " + field[lineNumber - 1][i].getStatus().picture;
			}
			return fieldLine;
		}
		else {
			return null;
		}
	}
	
	public void log(String message) {
		System.out.println("+------------------------------------------------------------------+");
		System.out.println(String.format("|   %10s  |", message));
		System.out.println("+------------------------------------------------------------------+");
	}
	
	
}
