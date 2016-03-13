package Game;

public abstract class Player {
	private BattleField firstBattleField;
	private BattleField secondBattleField;
	abstract public BattleField getFirstBattleField();
	abstract public BattleField getSecondBattleField();
	abstract boolean placeShips(BattleField battleField, Ship ship, Cell startCell);
	abstract boolean attackShips(Player player1, Player player2, Cell cellAttacked);
}
