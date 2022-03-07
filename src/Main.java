import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
//        String[][] board =
//                {{"#","#","#",".","o",".","#","#","#"},
//                {".",".",".",".",".","o",".",".","."},
//                {".",".",".",".",".",".","o",".","."},
//                {".",".",".","o",".","o",".",".","o"},
//                {"#","#","#","o",".","o","#","#","#"}
//        };

        //A2_Q1 q1 = new A2_Q1();
        A2_Q2 q2 = new A2_Q2();
        //A2_Q4 q4 = new A2_Q4();
        //BigInteger seven = new BigInteger("2");

        int weight = q2.weight(new int[]{200});
        //int[] answer = q1.game(board);


        System.out.println(weight);

    }
}
