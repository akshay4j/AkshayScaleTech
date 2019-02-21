import java.util.Arrays;
import java.util.Scanner;

public class LRUCache {

	static String memory[];
	static Scanner scan = new Scanner(System.in);
	static int size = 0;

	public static void main(String as[]) throws Exception{
		Scanner scan = new Scanner(System.in);

		System.out.println("enter memory size(blocks): ");
		size = scan.nextInt();
		scan.nextLine();

		memory = new String[size];

		while (true) {
			LRUCache lru = new LRUCache();
			String in;
			lru.help();
			System.out.println("Enter Command: ");

			in = scan.nextLine();
			String[] command = in.split(" ");

			if (command[0] != null && !command[0].trim().equals("")) {

				switch ((String) command[0].trim().toLowerCase()) {
				case "exit": {
					lru.printMemory();
					scan.close();
					System.exit(0);
				}
				case "add": {
					if (in.length() > 4) {
						lru.add(in.substring(4));
					}
					lru.printMemory();
					continue;
				}
				case "remove": {
					lru.remove(in, lru);
					lru.printMemory();
					continue;
				}
				case "fatch": {
					lru.fatch(in, lru);
					lru.printMemory();
					continue;
				}
				case "print": {
					lru.printMemory();
					continue;
				}
				case "help": {
					lru.help();
					continue;
				}
				// command for testing purpose
				case "par": {
					lru.pushAllRight();
					lru.printMemory();
					continue;
				}

				default: {
					System.out.println("command not found, please try again.");
				}
				}// end of switch
			} else {
				System.out.println("no command!!");
			}
		} // eof loop
	}///// end of main , eof program

	private void help() {
		System.out.println();
		System.out.println("here are the following commands to make operations:");
		System.out.println("1) to add data : \"add yourDataHere\" ");
		System.out.println("2) to remove data : \"remove yourDataHere\" ");
		System.out.println("3) to print memory : \"print\" ");
		System.out.println("4) to fatch data : \"fatch yourDataHere\" ");
		System.out.println("5) to exit program : \"exit\" ");
		System.out.println("6) to get command instructions : \"help\" ");
		System.out.println();
	}

	private void fatch(String in, LRUCache lru) {
		if (in.length() > 6) {
			int index = lru.getIndexbyValue(in.substring(6), lru);
			String fatched = memory[index];
			if (index != 0) {
				memory[index] = null;
				lru.pushAllRight();
				memory[0] = fatched;
			}
			System.out.println("index: " + index);
		}
	}

	private void remove(String in, LRUCache lru) {
		if (in.length() > 7) {
			int index = lru.getIndexbyValue(in.substring(7), lru);
			if (index != -1) {
				memory[index] = null;
			} else {
				System.out.println("entered value is not available.");
			}
		}
	}

	private int getIndexbyValue(String value, LRUCache lru) {
		return Arrays.asList(memory).indexOf(value);
	}

	private void printMemory() {
		System.out.println("Memory: " + Arrays.toString(memory) + "   :  size : " + size);
	}

//			most ->  [ 0 ]  [ 1 ]  [ 2 ]  [ 3 ]  [ 4 ]  <-least

	private void add(String add) {

		pushAllRight();
		if (memory[0] == null) {
			memory[0] = add;
		} else {// all full , remove 1
			oneStepBack();
			memory[0] = add;
		}
	}

	private void pushAllRight() {

		int empty = size - 1;

		for (int i = size - 1; i >= 0; i--) {
			int br = 0;
			if (memory[i] == null) {
				empty = i;

				for (int j = empty - 1; j >= 0; j--) {
					if (memory[j] != null) {
						memory[empty] = memory[j];
						memory[j] = null;
					}
					if (j == 0 && memory[j] == null) {
						br = 1;
						break;
					}
				} // eof j
			}
			if (br == 1) {
				break;
			}
		} // eof i
	}

	private void oneStepBack() {
		// remove last and
		for (int i = size - 1; i > 0; i--) {
			memory[i] = memory[i - 1];
		}
	}
}
