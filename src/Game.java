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
		System.out.println("in constructor");
		this.size = array.length;
		this.scorePoints = 1000;
		this.scan = new Scanner(System.in);
		this.betPoints = 10;
		this.bets = 0;
		System.out.println("Enter Multiplier: ");
		mul = scan.nextInt();
	}

	public static void main(String as[]) {
		game = new Game();
		game.makeBet();
	}

	private void makeBet() {
		if (game.scorePoints >= game.betPoints) {
			int random = game.getRandom();
			long result = game.mul * random;
			
		}
		else {
			System.out.println("not enought points to make a bet.");
			game.endGame();
		}
	}

	private void makeMultipleBets(long no) {
		for (long i = no; i > 0; i++) {
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