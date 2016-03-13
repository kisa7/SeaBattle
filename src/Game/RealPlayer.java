package Game;

import Game.Cell.CellStatus;
import Game.Ship.ShipRotation;

public class RealPlayer extends Player{
	public RealPlayer(BattleField firstBattleField, BattleField secondBattleField) {
		this.firstBattleField = firstBattleField;
		this.secondBattleField = secondBattleField;
	}
	private BattleField firstBattleField;
	private BattleField secondBattleField;
	

	public BattleField getFirstBattleField() {
		return firstBattleField;
	}


	public BattleField getSecondBattleField() {
		return secondBattleField;
	}
	
	public boolean placeShips(BattleField battleField, Ship ship, Cell startCell) {
		Cell currentCell;
		
		int y = startCell.getCoordinateY();
		int x = startCell.getCoordinateX();
		int shipSize = ship.getType().getSize();
		if(ship.getRotation() == ShipRotation.HORIZONTAL) {
			for(int i = x; i < (x + shipSize); i++) {
				currentCell = battleField.getCell(y, i);
				if(currentCell != null) {
					CellStatus cellStatus = currentCell.getStatus();
					if(cellStatus == CellStatus.BLOCKED || cellStatus == CellStatus.OCCUPIED) {
						return false;
					}
				}
				else
				{
					return false;
				}
				
			}
			for(int i = x; i < (x + shipSize); i++) {
				if(battleField.getCell(y, i) != null) {
					currentCell = battleField.getCell(y, i);
					currentCell.setStatus(CellStatus.OCCUPIED);
					currentCell.ship = ship;
				}
				else {
					return false;
				}
			}
			for(int i = y - 1; i <= y + 1; i++) {
				for(int j = x - 1; j < x + shipSize + 1; j++) {
					if(battleField.getCell(i, j) != null) {
						if(battleField.getCell(i, j).getStatus() != CellStatus.OCCUPIED) {
							battleField.getCell(i, j).setStatus(CellStatus.BLOCKED);
						}
					}
				}
			}
			return true;
		}
		else {
			for(int i = y; i < (y + shipSize); i++) {
				currentCell = battleField.getCell(i, x);
				if(currentCell != null) {
					CellStatus cellStatus = currentCell.getStatus();
					if(cellStatus == CellStatus.BLOCKED || cellStatus == CellStatus.OCCUPIED) {
						return false;
					}
				}
				else
				{
					return false;
				}
			}
			for(int i = y; i < (y + shipSize); i++) {
				if(battleField.getCell(i, x) != null) {
					currentCell = battleField.getCell(i, x);
					currentCell.setStatus(CellStatus.OCCUPIED);
					currentCell.ship = ship;
				}
				else {
					return false;
				}
			}
			for(int i = x - 1; i <= x + 1; i++) {
				for(int j = y - 1; j < y + shipSize + 1; j++) {
					if(battleField.getCell(j, i) != null) {
						if(battleField.getCell(j, i).getStatus() != CellStatus.OCCUPIED) {
							battleField.getCell(j, i).setStatus(CellStatus.BLOCKED);
						}
					}
				}
			}
			return true;
		}
	}

	
	public boolean attackShips(Player player1, Player player2, Cell cellAttacked) {
		if (cellAttacked == null)
		{
			return false;
		}
		int y = cellAttacked.getCoordinateY();
		int x = cellAttacked.getCoordinateX();
		BattleField player2FirstField = player2.getFirstBattleField();
		Cell enemyCell = player2FirstField.getField()[y][x];
		if(!cellAttacked.isVisited()) {
			BattleField player1SecondField = player1.getSecondBattleField();
			CellStatus cellStatus = enemyCell.getStatus();
			if(cellStatus == CellStatus.FREE || cellStatus == CellStatus.BLOCKED) {
				Cell cell = player1SecondField.getField()[y][x];
				cell.setStatus(CellStatus.MISS_ATTACK);
				player2FirstField.getField()[y][x].setStatus(CellStatus.MISS_ATTACK);
				cell.setVisited(true);
				return false;
			}
			else if(enemyCell.getStatus() == CellStatus.OCCUPIED) {
				player1SecondField.getField()[y][x].setStatus(CellStatus.SHIP_ATTACKED);
				player2FirstField.getField()[y][x].setStatus(CellStatus.SHIP_ATTACKED);
				Ship ship = enemyCell.getShip();
				ship.setCellsKilled(ship.getCellsKilled() + 1);
				int shipSize = enemyCell.getShip().getType().getSize();
				if(ship.getCellsKilled() == shipSize) {
					ship.setKilled(true);
					for(int i = x - 1; (i < x + shipSize + 1) && (ship.getRotation() == ShipRotation.HORIZONTAL) ||  (i <= x + 1) && (ship.getRotation() == ShipRotation.VERTICAL); i++) {
						for(int j = y - 1; (j < y + shipSize + 1) && (ship.getRotation() == ShipRotation.VERTICAL) ||  (j <= y + 1) && (ship.getRotation() == ShipRotation.HORIZONTAL); j++) {
							if(player2FirstField.getCell(j, i) != null) {
								if(player2FirstField.getCell(j, i).getStatus() != CellStatus.SHIP_ATTACKED) {
									player1SecondField.getCell(j, i).setStatus(CellStatus.BLOCKED);
								}
							}
						}
					}
				}
				cellAttacked.setVisited(true);
			}
		}
		return true;
	}
}
