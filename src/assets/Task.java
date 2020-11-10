package assets;

/**
 * This class represents a task, as an object. It simply contains getter
 * functions to retrieve information about said task.
 * 
 * @author Jeremy + Jinwoo
 *
 */
public class Task implements Comparable<Task> {

	private String name;
	private Integer ready_time;
	private Integer wcet;
	private Integer deadline;
	private String usage;

	// we might need to add another parameter for the laxity heuristic that the
	// professor emailed us about (?)
	public Task(String name, int ready, int exec, int deadline, String usage) {
		this.name = name;
		this.ready_time = ready;
		this.wcet = exec;
		this.deadline = deadline;
		this.usage = usage;

	}

	public String getName() {
		return name;
	}

	public int getReadyTime() {
		return ready_time;
	}

	public int getExecTime() {
		return wcet;
	}

	public int getDeadline() {
		return deadline;
	}

	public String getUsage() {
		return usage;
	}

	public int calc_heuristic(int ready, int process, int resource) {

		return highest_From_Three(ready, process, resource) + deadline;
	}

	/**
	 * This comparator basically just helps sort the task by deadline
	 */
	@Override
	public int compareTo(Task o) {
		if (this.getDeadline() < o.getDeadline()) {
			return o.getDeadline();
		}
		return this.deadline.compareTo(o.deadline);
	}

	public static int highest_From_Three(int num1, int num2, int num3) {
		int result = 0;
		if (num1 >= num2 && num1 >= num3) {
			result = num1;
		} else if (num2 >= num1 && num2 >= num3) {
			result = num2;
		} else {
			result = num3;
		}
		return result;
	}

}
