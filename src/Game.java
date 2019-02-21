import java.util.Random;
import java.util.Scanner;

public class Game {
	final int array[] = { 1, 0, 2, 0, 3, 0, 4, 0, 5, 0 };
	int size;
	long scorePoints;// score
	long bets;// number of bets played
	static Game game;
	Scanner scan;
	int betPoints;// amout to make a bet
	int mul;

	private Game() {
		this.size = array.length;
		this.scorePoints = 1000;
		this.scan = new Scanner(System.in);
		this.betPoints = 10;
		this.bets = 0;
		System.out.print("Enter Multiplier: ");
		mul = scan.nextInt();
	}

	public static void main(String as[]) {
		game = new Game();
		while (true) {
			System.out.println("<<< Choose Option >>>");
			System.out.println("1 : for Bet || 0 : for Exit");
			int option = game.scan.nextInt();
			switch (option) {
			case 1: {
				System.out.println("How many Bets ??");
				long betsToDo = game.scan.nextLong();
				game.makeMultipleBets(betsToDo);
				break;
			}
			case 0: {
				game.endGame();
				break;
			}
			default: {
				System.out.println("choose valid option !!");
				break;
			}
			}
			
		}
	}

	private void makeBet() {
		if (game.scorePoints >= game.betPoints) {
			game.scorePoints -= game.betPoints;

			int random = game.getRandom();
			long result = game.mul * random;

			game.scorePoints += result;
			game.bets++;
			game.showBetResult(result, random);

		} else {
			System.out.println("not enought points to make a bet.");
			game.endGame();
		}
	}

	private void showBetResult(long result, int random) {
		System.out.println("Random number choosen: " + random);
		System.out.println("Bet Score: " + (result - betPoints) + " || " + "Total Score Points: " + game.scorePoints);
	}

	private void makeMultipleBets(long no) {
		for (long i =0; i < no; i++) {
			game.makeBet();
		}
	}

	private void endGame() {
		System.out.println("total bets: " + game.bets);
		System.out.println("Final Score: " + game.scorePoints);
		System.exit(0);
	}

	private int getRandom() {
		return new Random().nextInt(game.size);
	}
}