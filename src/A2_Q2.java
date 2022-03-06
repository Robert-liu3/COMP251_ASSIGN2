import java.util.*;

public class A2_Q2 {

	public static int current_num = 0;
	public static HashMap<Integer,ArrayList<Integer>> stack = new HashMap<>();

	public static int weight(int[] plates) {
		ArrayList<Integer> list = new ArrayList<>();
		convert(plates, list);
		int final_num = rec(0,list);
		current_num = 0;
		return final_num;
	}
	public static int rec( int current_weight, ArrayList<Integer> list) {
		int next_weight = 0;
		int return_weight = 0;

		if (current_weight >= 1000) {
			return current_weight;
		}

		ArrayList<Integer> removed_list = new ArrayList<>(list);
		removed_list.remove(0);

		next_weight = rec(current_weight +  list.get(0), removed_list);
		return_weight = find_num(new int[]{next_weight,current_weight});

		//return_weight = find_num2(next_weight, current_weight);
		current_num = find_num2(current_num,return_weight);
		return current_num;
	}

	public static int find_num(int[] num1) {
		int thousand = 1000;
		int num2 = 0;
		for (int i = 0; i < num1.length; i ++) {
			if (i == 0) {
				num2 = num1[i];
			}
			else {
				if (Math.abs(num1[i] - 1000) < Math.abs(num2 - 1000)) {
					num2 = num1[i];
				}
			}
		}
		return num2;
	} //returns the proper thousand

	public static int find_num2(int num1, int num2) {
		int thousand = 1000;
		int return_num = 0;
		if (Math.abs(num1-1000) < Math.abs(num2 - 1000)) {
			return_num = num1;
		} else if (Math.abs(num2-1000) < Math.abs(num1 - 1000)) {
			return_num = num2;
		} else return_num = num2;
		return return_num;
	} //returns the proper thousand

	public static void convert(int[] int_list, ArrayList<Integer> arrayList) {
		for (int i = 0; i < int_list.length; i++) {
			arrayList.add(int_list[i]);
		}
	}

}
