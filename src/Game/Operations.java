package Game;

import java.util.Random;
import java.util.Scanner;

import Game.Ship.ShipRotation;

public class Operations {
	
	public static Cell getRandomCell(Cell[][] field) {
		final int FIELD_SIZE = 10;
		Random random = new Random();
		int firstCoordinate = random.nextInt(FIELD_SIZE - 1);
		int secondCoordinate = random.nextInt(FIELD_SIZE - 1);
		return field[firstCoordinate][secondCoordinate];
	}
		
	public static Cell readCoordinatesAndRotation(String outcomeString, Cell[][] field, Ship ship) {
		final int MAX_POSITION = 10;
		int index = 0;
		int firstCoordinate = outcomeString.charAt(index++) - 'A';
		int secondCoordinate = outcomeString.charAt(index++) - '1';
		if(outcomeString.charAt(index) == ' ') {
			index++;
			ship.setRotation(Operations.readRotation(outcomeString, index));
		}
		else {
			secondCoordinate = MAX_POSITION;
		}
		return field[firstCoordinate][secondCoordinate];
	}

	public static Cell readCoordinates(String outcomeString, Cell[][] field) {
		final int MAX_POSITION = 9;
		int index = 0;
		if (outcomeString.length() < 2)
		{
			return null;
		}
		int firstCoordinate = outcomeString.charAt(index++) - 'A';
		int secondCoordinate = outcomeString.charAt(index++) - '1';
		if(outcomeString.charAt(outcomeString.length() - 1) == '0') {
			secondCoordinate = MAX_POSITION;
		}
		return field[firstCoordinate][secondCoordinate];
	}
	
	public static ShipRotation readRotation(String outcomeString, int index) {
		char rotation = outcomeString.charAt(index);
		if(rotation == 'h') {
			return ShipRotation.HORIZONTAL;
		}
		else {
			return ShipRotation.VERTICAL;
		}
	}
	
	public static void placeShipsModeActivated(Player player, Scanner scanner) {
		final int SHIPS_NUMBER = 10;
		int shipNumber = 0;
		int firstFieldLineNumber = 0;
		int secondFieldLineNumber = 0;
		String firstFieldLine;
		while(shipNumber != SHIPS_NUMBER) {
			Cell startCell;
			String inputedString = scanner.nextLine();
			BattleField firstBattleField = player.getFirstBattleField();
			Ship ship = firstBattleField.getShip(shipNumber);
			startCell = Operations.readCoordinatesAndRotation(inputedString, firstBattleField.getField(), ship);
			if(!player.placeShips(firstBattleField, ship, startCell)){
				shipNumber--;;
			}
			firstFieldLineNumber = 0;
			secondFieldLineNumber = 0;
			while((firstFieldLine = firstBattleField.printFieldLine(firstFieldLineNumber++)) != null) {
				BattleField secondBattleField = player.getSecondBattleField();
				System.out.println(firstFieldLine + "      " + secondBattleField.printFieldLine(secondFieldLineNumber++));
			}
			shipNumber++;
		}
	}
	
	public static void botPlaceShipsMode(Player player, Scanner scanner) {
		final int SHIPS_NUMBER = 10;
		int shipNumber = 0;
		int firstFieldLineNumber = 0;
		int secondFieldLineNumber = 0;
		String firstFieldLine;
		while(shipNumber != SHIPS_NUMBER) {
			Cell startCell;
			BattleField secondBattleField = player.getSecondBattleField();
			startCell = Operations.getRandomCell(secondBattleField.getField());
			BattleField firstBattleField = player.getFirstBattleField();
			Ship ship = firstBattleField.getShip(shipNumber);
			if ((new Random()).nextInt(2) == 1)
			{
				ship.setRotation(ShipRotation.HORIZONTAL);
			}
			
			if(!player.placeShips(firstBattleField, ship, startCell)) {
				shipNumber--;
			}
			firstFieldLineNumber = 0;
			secondFieldLineNumber = 0;
			while((firstFieldLine = firstBattleField.printFieldLine(firstFieldLineNumber++)) != null) {
				System.out.println(firstFieldLine + "      " + secondBattleField.printFieldLine(secondFieldLineNumber++));
			}
			shipNumber++;
		}
	}
	public static void printAdditionalInterface(Player player) {
		String firstFieldLine;
		int firstFieldLineNumber = 0;
		int secondFieldLineNumber = 0;
		while((firstFieldLine = player.getFirstBattleField().printFieldLine(firstFieldLineNumber++)) != null) {
		System.out.println(firstFieldLine + "      " + player.getSecondBattleField().printFieldLine(secondFieldLineNumber++));
		}
		player.getFirstBattleField().log("Input coordinates(A - J) / (1 - 10) and ship rotation(v / h):");
	}
	
	public static void playerAttackMode(Player player1, Player player2, Scanner scanner) {
		final int SHIPS_NUMBER = 10;
		boolean allShipsAreKilled = false;
		int firstFieldLineNumber = 0;
		int secondFieldLineNumber = 0;
		int killedShipsPlayer1 = 0;
		int killedShipsPlayer2 = 0;
		String firstFieldLine;
		BattleField player1FirstBattleField = player1.getFirstBattleField();
		BattleField player1SecondBattleField = player1.getSecondBattleField();
		BattleField player2SecondBattleField = player2.getSecondBattleField();
		BattleField player2FirstBattleField = player2.getFirstBattleField();
		while(!allShipsAreKilled) {
			Cell cellAttacked;
			String inputedString = scanner.nextLine();
			cellAttacked = Operations.readCoordinates(inputedString, player1SecondBattleField.getField());
			while(player1.attackShips(player1, player2, cellAttacked)) {
				while((firstFieldLine = player1FirstBattleField.printFieldLine(firstFieldLineNumber++)) != null) {
					System.out.println(firstFieldLine + "      " + player1SecondBattleField.printFieldLine(secondFieldLineNumber++));
				}
				inputedString = scanner.nextLine();
				cellAttacked = Operations.readCoordinates(inputedString, player1FirstBattleField.getField());
				firstFieldLineNumber = 0;
				secondFieldLineNumber = 0;
			}
			cellAttacked = Operations.getRandomCell(player2SecondBattleField.getField());
			while(player2.attackShips(player2, player1, cellAttacked)) {
				while((firstFieldLine = player1FirstBattleField.printFieldLine(firstFieldLineNumber++)) != null) {
					System.out.println(firstFieldLine + "      " + player1SecondBattleField.printFieldLine(secondFieldLineNumber++));
				}
				cellAttacked = Operations.getRandomCell(player2FirstBattleField.getField());
				firstFieldLineNumber = 0;
				secondFieldLineNumber = 0;
			}
			while((firstFieldLine = player1FirstBattleField.printFieldLine(firstFieldLineNumber++)) != null) {
				System.out.println(firstFieldLine + "      " + player1SecondBattleField.printFieldLine(secondFieldLineNumber++));
			}
			for(int i = 0; i < player1FirstBattleField.getShips().length; i++) {
				if(player1FirstBattleField.getShips()[i].isKilled()) {
					killedShipsPlayer1++;
				}
			}
			for(int i = 0; i < player2FirstBattleField.getShips().length; i++) {
				if(player2FirstBattleField.getShips()[i].isKilled()) {
					killedShipsPlayer2++;
				}
			}
			if((killedShipsPlayer1 == SHIPS_NUMBER) || (killedShipsPlayer2 == SHIPS_NUMBER)) {
				allShipsAreKilled = true;
			}
			killedShipsPlayer1 = 0;
			killedShipsPlayer2 = 0;
			firstFieldLineNumber = 0;
			secondFieldLineNumber = 0;
		}
	}
}
