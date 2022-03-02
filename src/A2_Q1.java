import java.lang.reflect.Array;
import java.util.*;

public class A2_Q1 {
	public static ArrayList<ArrayList<Integer>> coords = new ArrayList<ArrayList<Integer>>();
	public static String[][] real_board;

	public static int[] game(String[][] board){
		real_board = board;
		oGetter();
		for (ArrayList<Integer> num_list: coords) {
			for (ArrayList<Integer> )
		}
		return null;
	}
	public static void oGetter() {
		for (int row = 0; row < real_board[0].length; row++) {
			for (int col = 0; col < real_board.length; col++) {
				if (real_board[row][col].equals("o")) {
					ArrayList<Integer> single_coords = new ArrayList<Integer>();
					single_coords.add(row);
					single_coords.add(col);
					coords.add(single_coords);
				}
			}
		}
	}

	public static void jump(String direction, int row, int col ) {
		if (real_board[row][col+1].equals("o") && !real_board[row][col+2].equals("o")) {
			real_board[row][col] = ".";
			real_board[row][col + 1] = ".";
			real_board[row][col + 2] = "o";
		}
		if (direction.trim().equals("left")) {
			real_board[row][col] = ".";
			real_board[row][col-1] = ".";
			real_board[row][col-2] = "o";
		}
		if (direction.trim().equals("up")) {
			real_board[row][col] = ".";
			real_board[row+1][col] = ".";
			real_board[row+2][col] = "o";
		}
		if (direction.trim().equals("down")) {
			real_board[row][col] = ".";
			real_board[row-1][col] = ".";
			real_board[row-2][col] = "o";
		}

	}

	public static ArrayList<String> oBeside(int row, int col) {
		ArrayList<String> possibleJumps = new ArrayList<String>();
		if (real_board[row][col+1].equals("o") && !real_board[row][col+2].equals("o")) {
			possibleJumps.add("right");
		}
		else if (real_board[row][col-1].equals("o") && !real_board[row][col-2].equals("o")) {
			possibleJumps.add("left");
		}
		else if (real_board[row+1][col].equals("o") && !real_board[row+2][col].equals("o")) {
			possibleJumps.add("up");
		}
		else if (real_board[row-1][col].equals("o") && !real_board[row-2][col].equals("o")) {
			possibleJumps.add("down");
		}

		return possibleJumps;
	}

}
