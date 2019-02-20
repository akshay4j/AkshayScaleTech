import java.util.Arrays;
import java.util.Scanner;

public class LRUCache {

	static String memory[];
	static Scanner scan = new Scanner(System.in);
	static int size = 0;

	public static void main(String as[]) {
		Scanner scan = new Scanner(System.in);

		System.out.println("enter memory size(blocks): ");
		size = scan.nextInt();

		memory = new String[size];

		while (true) {
			LRUCache lru = new LRUCache();

			String in = scan.nextLine();
			String[] command = in.split(" ", 1);

			if (command[0] != null && command[0].trim().equals("")) {
				switch ((String) command[0].trim().toLowerCase()) {
				case "exit": {
					lru.printMemory();
					break;
				}
				case "add": {
					lru.add();
				}
				
				case "remove":{
					
				}

				default: {
					System.out.println("command not found, please try again.");
				}
				}// end of switch
				lru.printMemory();
				break;
			}
		} // eof loop

		// add data remove fatch printMemory()

		// add : empty:add at 0, full:at front by removing one from last , mix{}
		// mix :
		scan.close();

	}///// end of main , eof program

	private void printMemory() {
		System.out.println(Arrays.toString(memory) + "   :  size : " + size);
	}

//			most ->  [ 0 ]  [ 1 ]  [ 2 ]  [ 3 ]  [ 4 ]  <-least

	private void add() {
		System.out.println("enter your data: ");
		String add = scan.nextLine();
		pushAllRight();
		if(memory[0] == null) {
			memory[0] = add;
		}
		else {//all full , remove 1 
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
		for (int i = size - 1; i > 0; i++) {
			memory[i] = memory[i - 1];
		}
	}

}
