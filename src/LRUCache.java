import java.util.Arrays;
import java.util.Scanner;

public class LRUCache {
	//least recently used means so far in terms of time used value will be removed first

	//these 3 vars we need at anytime of runtime , so these should be static 
	static String memory[];
	static Scanner scan = new Scanner(System.in);
	static int size = 0;

	public static void main(String as[]) throws Exception{
		//to deside memory size
		//java array is used as memory
		
		//first will be most recently used
		// MRU --> [ ] [ ] [ ] [ ] [ ] [ ] <-- LRU
		//								last will be most recently used...
		//MRU to LRU sorting order will be followed, timestamp not logged
		
		System.out.println("enter memory size(blocks): ");
		size = scan.nextInt();
		scan.nextLine();
		
		memory = new String[size];

		//infinite loop for cxontinuous execution , exit condition impl.ed
		while (true) {
			LRUCache lru = new LRUCache();
			String in;
			lru.help();
			System.out.println("Enter Command: ");
			//input stream will be stored on string
			//command pettern>> { [command] [data] } // ex: "add abc" , "remove abc" , "exit"

			in = scan.nextLine();
			//split from 0 to 1st space (it must be command token)
			String[] command = in.split(" ");
			
			//check if its not blank (it will never be a space, we have trimmed)
			if (command[0] != null && !command[0].trim().equals("")) {
				
				switch ((String) command[0].trim().toLowerCase()) {
				case "exit": {//exit command
					lru.printMemory();
					scan.close();
					System.exit(0);
				}
				case "add": {//add command, to add single value
					if (in.length() > 4) {//if length is less, syntex error
						lru.add(in.substring(4));
					}
					lru.printMemory();
					continue;
				}
				case "remove": {//to remove entry if its avail
					lru.remove(in, lru);
					lru.printMemory();
					continue;
				}
				case "fatch": {//fatch if avail, and set it as recently used
					lru.fatch(in, lru);
					lru.printMemory();
					continue;
				}
				case "print": {//printys memory
					lru.printMemory();
					continue;
				}
				case "help": {//instruction set
					lru.help();
					continue;
				}
				// command for testing purpose
				case "par": {//push all to right side, blank nodes in array in between, order will remain as it is
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

	
	//instruction set
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
	
	//to add a new entry,
//	it will be at zero position
//	if 0 is not null, then push all right side, and check again if 0 = null 
//	if not means memory is full >> remove LRU(last node=null)
	private void add(String add) {

		pushAllRight();
		if (memory[0] == null) {
			memory[0] = add;
		} else {// all full , remove 1
			oneStepBack();
			memory[0] = add;
		}
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
