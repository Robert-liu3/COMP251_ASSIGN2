import java.util.*;

public class A2_Q1 {
	public static ArrayList<ArrayList<Integer>> coords = new ArrayList<ArrayList<Integer>>();
	public static int[] game(String[][] board){
		for (int row = 0; row < board[0].length; row++) {
			for (int col = 0; col < board.length; col++) {
				if (board[row][col].equals("o")) {
					ArrayList<Integer> single_coords = new ArrayList<Integer>();
					single_coords.add(row);
					single_coords.add(col);
					coords.add(single_coords);

				}
			}
		}
	}

	public void oBeside(String[][] board, int row, int col) {
		if (board[row][col+1].equals("o")) {
			if (board[row][col+2].equals("o")) {
				board[row][col] = ".";
				board[row][col + 1] = ".";
				board[row][col+2] = "o";
			}
		}
		else if (board[row+1][col].equals("o")) {
			if (board[row+1][col].equals("o")) {
				board[row][col] = ".";
				board[row + 1][col] = ".";
				board[row+2][col] = "o";
			}
		}
		else if (board[row-1][col].equals("o")) {
			if (board[row+1][col].equals("o")) {
				board[row][col] = ".";
				board[row - 1][col] = ".";
			}
		}
		else if (board[row][col-1].equals("o")) {
			board[row][col] = ".";
			board[row][col-1] = ".";
			oBeside(board, row, col-1);
		}
		else board[row][col] = "o";
	}

}
