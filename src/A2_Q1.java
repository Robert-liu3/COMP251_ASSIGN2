import java.util.*;

public class A2_Q1 {
	public static HashMap<Integer, Integer> allo = new HashMap<Integer, Integer>();
	public static int[] game(String[][] board){
		int[] game = {0,0};
		for (int row = 0; row < board[0].length; row++) {
			for (int col = 0; col < board.length; col++) {
				if (board[row][col].equals("o")) {
					board = oBeside(board, row, col);
					if (board[row][col].equals("o")) {
						game[0] ++;
					} else game[1] ++;
				}
			}
		}
	}
	public static void oGetter(String[][] board, int row, int col) {
		if (board[row][col].equals)
	}

	public static String[][] oBeside(String[][] board, int row, int col) {
		if (board[row][col+1].equals("o")) {
			if (board[row][col+2].equals(".")) {
				board[row][col] = ".";
				board[row][col + 1] = ".";
				board[row][col+2] = "o";
				return board;
			}
		}
		else if (board[row+1][col].equals("o")) {
			if (board[row+1][col].equals(".")) {
				board[row][col] = ".";
				board[row + 1][col] = ".";
				board[row+2][col] = "o";
				return board;
			}
		}
		else if (board[row-1][col].equals("o")) {
			if (board[row+1][col].equals(".")) {
				board[row][col] = ".";
				board[row - 1][col] = ".";
				board[row-2][col] = "o";
				return board;
			}
		}
		else if (board[row][col-1].equals("o")) {
			if (board[row][col-2].equals(".")) {
				board[row][col] = ".";
				board[row][col - 1] = ".";
				board[row][col-2] = "o";
				return board;
			}
		}
		return board;
	}

}
