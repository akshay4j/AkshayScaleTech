import java.util.Random;
import java.util.Scanner;

public class Game {
	
	final int array[] = { 1, 0, 2, 0, 3, 0, 4, 0, 5, 0 };

	static Game game;
	Scanner scan;
	
	//game meta data
	int size;
	long scorePoints;// score
	long bets;// number of bets played
	int betPoints;// amout to make a bet
	int mul;

	private Game() {
		//initilizing creds values
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
			case 1: {//to play a game bets
				System.out.println("How many Bets ??");
				long betsToDo = game.scan.nextLong();
				game.makeMultipleBets(betsToDo);
				break;
			}
			case 0: {//exit way
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

	//makes single bet 
	//deduct amount, generates random, multiplies and credits to score 
	private void makeBet() {
		if (game.scorePoints >= game.betPoints) {//enought points to make bet
			game.scorePoints -= game.betPoints;

			int random = game.getRandom();
			long result = game.mul * random;

			game.scorePoints += result;
			game.bets++;
			game.showBetResult(result, random);

		} else {//not enought points , exit the game
			System.out.println("not enought points to make a bet.");
			game.endGame();
		}
	}

	private void showBetResult(long result, int random) {//shows a single bet result, then continue to game
		System.out.println("Random number choosen: " + random);
		System.out.println("Bet Amount: "+betPoints+" || " +"Points Made: "+result +" || "+ "Credit : " + (result - betPoints));
		System.out.println("Total Score Points: " + game.scorePoints+" || " +" Total Bets: "+game.bets);
		System.out.println();
	}

	private void makeMultipleBets(long no) {// to perform multiple bets
		//performs single bet n times
		for (long i =0; i < no; i++) {
			game.makeBet();
		}
	}

	//shows final game meta data and exits runtime
	private void endGame() {
		System.out.println("total bets: " + game.bets);
		System.out.println("Final Score: " + game.scorePoints);
		System.exit(0);
	}

	//picks random entry from array
	private int getRandom() {
		return new Random().nextInt(game.size);
	}
}