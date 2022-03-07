import java.util.*;

public class HW_Sched {
	ArrayList<Assignment> Assignments = new ArrayList<Assignment>();
	int m;
	int lastDeadline = 0;

	protected HW_Sched(int[] weights, int[] deadlines, int size) {
		for (int i=0; i<size; i++) {
			Assignment homework = new Assignment(i, weights[i], deadlines[i]);
			this.Assignments.add(homework);
			if (homework.deadline > lastDeadline) {
				lastDeadline = homework.deadline;
			}
		}
		m =size;
	}


	/**
	 *
	 * @return Array where output[i] corresponds to the assignment 
	 * that will be done at time i.
	 */
	public int[] SelectAssignments() {
		//TODO Implement this
		Assignment max_deadline = new Assignment();

		//Sort assignments
		//Order will depend on how compare function is implemented
		Collections.sort(Assignments, new Assignment());
		//Collections.reverse(Assignments);
		// If homeworkPlan[i] has a value -1, it indicates that the 
		// i'th timeslot in the homeworkPlan is empty
		//homeworkPlan contains the homework schedule between now and the last deadline
		int[] homeworkPlan = new int[lastDeadline];
		for (int i=0; i < homeworkPlan.length; ++i) {
			homeworkPlan[i] = -1;
		}

		for (int i = 0; i < Assignments.size() ; i ++) {


			int index = Assignments.get(i).deadline-1;
			//if its corresponding slot is empty
			if (homeworkPlan[index] < 0) {
				homeworkPlan[index] = Assignments.get(i).number;
			}
			//if the slot is not empty and its not the first slot either
			else if (homeworkPlan[index] >= 0 && index > 0) {

				while (index > 0) {
					index --;
					if (homeworkPlan[index] < 0) {
						homeworkPlan[index] = Assignments.get(i).number;
						break;
					}
				}
			}
		}
		return homeworkPlan;
	}
}
	



