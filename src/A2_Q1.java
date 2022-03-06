import java.lang.reflect.Array;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.*;

public class A2_Q1 {
	public static int balls = 100000;
	public static int moves = 100000;

	final static int HEIGHT = 5;
	final static int WIDTH = 9;

	final static String TEST_FOLDER = "Open_Tests/Q1";
	public static void main(String[] args) {
		int pass = 0;
		int total = 0;
		File f = new File(TEST_FOLDER);
		for (String name : f.list()) {
			if (name.endsWith(".in")) {
				try {
					File in = new File(TEST_FOLDER + "/" + name);
					File out = new File(TEST_FOLDER + "/" + name.substring(0, name.length()-3)+".ans");
					Scanner in_scan = new Scanner(in);
					Scanner out_scan = new Scanner(out);
					System.out.printf("Attempting file %s\n", name);
					int n = in_scan.nextInt();
					in_scan.nextLine();
					for (int cs = 0; cs < n; cs++) {
						String[][] board = new String[HEIGHT][WIDTH];
						for (int i = 0; i < HEIGHT; i++) {
							String line = in_scan.nextLine();
							for (int j = 0; j < WIDTH; j++) {
								board[i][j] = new String(new char[]{line.charAt(j)});
							}
						}
						int[] got = game(board);
						int expected_0 = out_scan.nextInt();
						int expected_1 = out_scan.nextInt();
						if (got[0] != expected_0 || got[1] != expected_1) {
							total++;
							System.out.printf("Expected %d %d but got %d %d\n", expected_0, expected_1, got[0], got[1]);
						} else {
							pass++;
							total++;
							System.out.printf("Passed test %d\n", cs);
						}
						try {
							in_scan.nextLine(); // Skip empty line
						} catch (NoSuchElementException e) {

						}
					}

					in_scan.close();
					out_scan.close();
				} catch (FileNotFoundException e) {
					System.out.println(e);
				}
			}
		}
		System.out.println("pass: "+ pass);
		System.out.println("total: "+ total);
	}

	public static int[] game(String[][] board){
		ArrayList<ArrayList<Integer>> coords = oGetter(board);
		//Board_value board_value = rec(board, coords, 0);
		int[] stats = rec(board, coords, 0);
		balls = 100000;
		moves = 100000;
		return stats;
	}

	public static ArrayList<ArrayList<Integer>> num_jump(String[][] board) {

		ArrayList<ArrayList<Integer>> list_jumps = new ArrayList<ArrayList<Integer>>();
		int num_jump = 0;

		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				if (board[row][col].equals("o")) {
					ArrayList<String> isBeside = new ArrayList<String>(oBeside(board, row, col));
					if (!isBeside.isEmpty()) {
						ArrayList<Integer> single_dot = new ArrayList<Integer>();
						single_dot.add(row);
						single_dot.add(col);
						list_jumps.add(single_dot);
					}
				}
			}
		}
		return list_jumps;
	}

	public static ArrayList<ArrayList<Integer>> oGetter(String[][] board) {
		ArrayList<ArrayList<Integer>> coords = new ArrayList<ArrayList<Integer>>();
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				if (board[row][col].equals("o")) {
					ArrayList<Integer> single_coords = new ArrayList<Integer>();
					single_coords.add(row);
					single_coords.add(col);
					coords.add(single_coords);
				}
			}
		}
		return coords;
	}

	public static int oNum(String[][] board) {
		int oNumber = 0;
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				if (board[row][col].equals("o")) {
					oNumber++;
				}
			}
		}
		return oNumber;
	}

	public static int[] rec(String[][] board, ArrayList<ArrayList<Integer>> coords, int move) {
		int[] answer = new int[2];

		// iterating through each coordinate
		for (ArrayList<Integer> num_list: coords) {

			//getting the list of possible jumps per coordinate
			ArrayList<String> besideDots = oBeside(board, num_list.get(0),num_list.get(1));

			if (!besideDots.isEmpty()) {

				// iterating through each possible jump
				for (String dot: besideDots) {
					//copy of board
					String[][] jump_board = new String[board.length][board[0].length];
					copy(board, jump_board);

					jump(jump_board, dot, num_list.get(0), num_list.get(1));
					//stack.put(jump_board, move + 1);

					//get new list of coordinates
					ArrayList<ArrayList<Integer>> remove_coords = new ArrayList<ArrayList<Integer>>(coords);
					remove_coords = remove_coords(dot, remove_coords, num_list);

					//new ball from recursion
					rec(jump_board, remove_coords, move + 1);
				}
			}
			//if num balls is equivalent to previous state numballs
		}
		if (oNum(board)<balls) {
			balls = oNum(board);
			moves = move;
		} else if (oNum(board)==balls && oNum(board) < moves) {
			moves = move;
		}

		//grab the last board, the number of balls and the number of moves
		//compare them to the value passed

		return new int[]{balls, moves};
	}

	public static ArrayList<ArrayList<Integer>> remove_coords(String direction, ArrayList<ArrayList<Integer>> coords, ArrayList<Integer> moved_coord) {

		ArrayList<Integer> coord_one = new ArrayList<Integer>();
		ArrayList<Integer> coord_two = new ArrayList<Integer>();

		if (direction.trim().equals("left")) {
			coord_one.add(moved_coord.get(0));
			coord_one.add(moved_coord.get(1) -1);

			coord_two.add(moved_coord.get(0));
			coord_two.add(moved_coord.get(1)-2);

			coords.remove(moved_coord);
			coords.remove(coord_one);

			coords.add(coord_two);
		}
		else if (direction.trim().equals("right")) {
			coord_one.add(moved_coord.get(0));
			coord_one.add(moved_coord.get(1) +1);

			coord_two.add(moved_coord.get(0));
			coord_two.add(moved_coord.get(1)+2);

			coords.remove(moved_coord);
			coords.remove(coord_one);

			coords.add(coord_two);
		}
		else if (direction.trim().equals("up")) {
			coord_one.add(moved_coord.get(0)-1);
			coord_one.add(moved_coord.get(1));

			coord_two.add(moved_coord.get(0)-2);
			coord_two.add(moved_coord.get(1));

			coords.remove(moved_coord);
			coords.remove(coord_one);

			coords.add(coord_two);
		}
		else if (direction.trim().equals("down")) {
			coord_one.add(moved_coord.get(0)+1);
			coord_one.add(moved_coord.get(1));

			coord_two.add(moved_coord.get(0)+2);
			coord_two.add(moved_coord.get(1));

			coords.remove(moved_coord);
			coords.remove(coord_one);

			coords.add(coord_two);
		}
		return coords;
	}

	public static void jump(String[][] board,String direction, int row, int col ) {
		if (direction.trim().equals("right")) {
			board[row][col] = ".";
			board[row][col + 1] = ".";
			board[row][col + 2] = "o";
		}
		if (direction.trim().equals("left")) {
			board[row][col] = ".";
			board[row][col-1] = ".";
			board[row][col-2] = "o";
		}
		if (direction.trim().equals("up")) {
			board[row][col] = ".";
			board[row-1][col] = ".";
			board[row-2][col] = "o";
		}
		if (direction.trim().equals("down")) {
			board[row][col] = ".";
			board[row+1][col] = ".";
			board[row+2][col] = "o";
		}
	}



	public static ArrayList<String> oBeside(String[][] board, int row, int col) {
		ArrayList<String> possibleJumps = new ArrayList<String>();
		if (col > 1) {
			if (board[row][col - 1].equals("o") && board[row][col - 2].equals(".")) {
				possibleJumps.add("left");
			}
		}
		if (col < 7 ) {
			if (board[row][col + 1].equals("o") && board[row][col + 2].equals(".")) {
				possibleJumps.add("right");
			}
		}
		if (row > 1) {
			if (board[row - 1][col].equals("o") && board[row - 2][col].equals(".")) {
				possibleJumps.add("up");
			}
		}
		if (row < 3) {
			if (board[row + 1][col].equals("o") && board[row + 2][col].equals(".")) {
				possibleJumps.add("down");
			}
		}
		return possibleJumps;
	}

	public static void copy(String[][] board, String[][] empty_board) {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				empty_board[row][col] = board[row][col];
			}
		}
	}

	public static class Board_value implements Comparable<Board_value>{

		private int moves;
		private int balls;

		Board_value(int moves,int balls) {
			this.moves = moves;
			this.balls = balls;
		}

		//getters and setters
		public int get_moves() {
			return this.moves;
		}
		public void set_moves(int moves) {
			this.moves = moves;
		}
		public int get_balls() {
			return this.balls;
		}
		public void set_balls(int balls) {
			this.balls = balls;
		}

		@Override
		public int compareTo(Board_value b) {
			int compare_val = this.balls - ((Board_value)b).get_balls();
			if (compare_val == 0) {
				compare_val = this.moves - ((Board_value)b).get_moves();
			}
			return compare_val;
		}
	}

}
