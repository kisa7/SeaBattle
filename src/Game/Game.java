package Game;

import java.util.Scanner;

public class Game {
	public static void main(String[] args) {
		boolean isEnemy = true;
		Scanner scanner = new Scanner(System.in);
		BattleField firstPlayerBattleField = new BattleField();
		BattleField secondPlayerBattleField = new BattleField(isEnemy);
		BattleField firstBotBattleField = new BattleField();
		BattleField secondBotBattleField = new BattleField(isEnemy);
		Player player1 = new RealPlayer(firstPlayerBattleField, secondPlayerBattleField);
		Player player2 = new Bot(firstBotBattleField, secondBotBattleField);
		Operations.printAdditionalInterface((RealPlayer)player1);
		Operations.botPlaceShipsMode(player1, scanner);
		Operations.placeShipsModeActivated(player1, firstPlayerBattleField, secondPlayerBattleField, scanner);
		Operations.botPlaceShipsMode(player2, scanner);
		Operations.playerAttackMode((RealPlayer)player1, (Bot)player2, scanner);
		scanner.close();
	}
}
