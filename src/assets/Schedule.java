package assets;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Schedule {

	private static List<Task> tasks;
	private static List<Task> tasks_table;
	private static int winSize;
	private static Stack<Integer> p1 = new Stack<Integer>();
	private static Stack<Integer> p2 = new Stack<Integer>();
	private static List<Task> executed = new ArrayList<Task>();

	public Schedule(List<Task> tasks, int winSize) {
		this.tasks = tasks;
		this.winSize = winSize;
	}

	public static void execute() {

		boolean completed = false;

		// i don't know how your resource class you made works, but i initialized them
		// here
		Resource r1 = new Resource("R1", 0);
		Resource r2 = new Resource("R2", 0);

		int p1_usage = 0;
		int p2_usage = 1;

		int s = 0; // index within the window we're currently on

		p1.push(0);
		p2.push(0);

		while (completed == false) {

			try {
				// the window we're looking at
				// s: "0 1 2" 3 4
				// Task 1 2 3 selected

				Task t1 = tasks.get(s);
				Task t2 = tasks.get(s + 1);
				Task t3 = tasks.get(s + 2);

				int num_task = 3;

				// Resource: shared resource can use shared and exclusive resources
				// Exclusive: Can only use exclusive resources
				int h1 = 0;
				int h2 = 0;
				int h3 = 0;

				System.out.println("t1 resource: " + t1.getUsage());
				System.out.println("t2 resource: " + t2.getUsage());
				System.out.println("t3 resource: " + t3.getUsage());

				// Need Flexibility between p1.peek() and p2.peek()
				// calc heuristic = deadline + EST
				if (p1_usage == 0 && p2_usage == 1) { // could replace these statements with the r1 and r2 i created
														// above
					if (t1.getUsage().equals("N")) {
						h1 = t1.calc_heuristic(t1.getReadyTime(), p1.peek(), 0);
						System.out.println("[EST = max(" + t1.getReadyTime() + ", " + p1.peek() + ", " + 0 + ")]");
					}

					if (t2.getUsage().equals("S")) {
						h2 = t2.calc_heuristic(t2.getReadyTime(), p1.peek(), 0);

						// call the "lock() or unlock()" method here
						System.out.println("[EST = max(" + t2.getReadyTime() + ", " + p1.peek() + ", " + 0 + ")]");
					}

					if (t3.getUsage().equals("E")) {
						h3 = t3.calc_heuristic(t3.getReadyTime(), p1.peek(), 0);
						System.out.println("[EST = max(" + t3.getReadyTime() + ", " + p1.peek() + ", " + 0 + ")]");
					}

					p1_usage = 1;
					// p2_usage = 0;

				} else if (p1_usage == 1 && p2_usage == 0) {
					h1 = t1.calc_heuristic(t1.getReadyTime(), p2.peek(), 0);
					h2 = t2.calc_heuristic(t2.getReadyTime(), p2.peek(), 0);
					h3 = t3.calc_heuristic(t3.getReadyTime(), p2.peek(), 0);

					// Need Flexibility between p1.peek() and p2.peek()

					System.out.println("[EST = max(" + t1.getReadyTime() + ", " + p2.peek() + ", " + 0 + ")]");
					System.out.println("[EST = max(" + t2.getReadyTime() + ", " + p2.peek() + ", " + 0 + ")]");
					System.out.println("[EST = max(" + t3.getReadyTime() + ", " + p2.peek() + ", " + 0 + ")]");
					// p1_usage = 0;
					p2_usage = 1;

				}

				// Note that here you need to keep that all as if statements, adding "else if"
				// seems to break the code

				else {
					if (p1.peek() > p2.peek()) {
						if (t1.getUsage().equals("N")) {
							h1 = t1.calc_heuristic(t1.getReadyTime(), p2.peek(), 0);
							System.out.println("[EST = max(" + t1.getReadyTime() + ", " + p2.peek() + ", " + 0 + ")]");
						
							
						}

						if (t2.getUsage().equals("N")) {
							h2 = t2.calc_heuristic(t2.getReadyTime(), p2.peek(), 0);
							System.out.println("[EST = max(" + t2.getReadyTime() + ", " + p2.peek() + ", " + 0 + ")]");

						}
						if (t3.getUsage().equals("N")) {
							h3 = t3.calc_heuristic(t3.getReadyTime(), p2.peek(), 0);
							System.out.println("[EST = max(" + t3.getReadyTime() + ", " + p2.peek() + ", " + 0 + ")]");

						}

						if (t1.getUsage().equals("S")) {
							h1 = t1.calc_heuristic(t1.getReadyTime(), p2.peek(), 0);
							System.out.println("[EST = max(" + t1.getReadyTime() + ", " + p2.peek() + ", " + 0 + ")]");
						}

						if (t2.getUsage().equals("S")) {
							h2 = t2.calc_heuristic(t2.getReadyTime(), p2.peek(), 0);
							System.out.println("[EST = max(" + t2.getReadyTime() + ", " + p2.peek() + ", " + 0 + ")]");

						}
						if (t3.getUsage().equals("S")) {
							h3 = t3.calc_heuristic(t3.getReadyTime(), p2.peek(), 0);
							System.out.println("[EST = max(" + t3.getReadyTime() + ", " + p2.peek() + ", " + 0 + ")]");

						}

						if (t1.getUsage().equals("E")) {
							h1 = t1.calc_heuristic(t1.getReadyTime(), p1.peek(), p2.peek());
							System.out.println(
									"[EST = max(" + t1.getReadyTime() + ", " + p1.peek() + ", " + p2.peek() + ")]");
						}

						if (t2.getUsage().equals("E")) {
							h2 = t2.calc_heuristic(t1.getReadyTime(), p1.peek(), p2.peek());
							System.out.println(
									"[EST = max(" + t2.getReadyTime() + ", " + p1.peek() + ", " + p2.peek() + ")]");
						}
						if (t3.getUsage().equals("E")) {
							h3 = t3.calc_heuristic(t3.getReadyTime(), p1.peek(), p2.peek());
							System.out.println(
									"[EST = max(" + t3.getReadyTime() + ", " + p1.peek() + ", " + p2.peek() + ")]");
						}

					}
					if (p2.peek() > p1.peek()) {
						if (t1.getUsage().equals("N")) {
							h1 = t1.calc_heuristic(t1.getReadyTime(), p1.peek(), 0);
							System.out.println("[EST = max(" + t1.getReadyTime() + ", " + p1.peek() + ", " + 0 + ")]");
						}

						if (t2.getUsage().equals("N")) {
							h2 = t2.calc_heuristic(t2.getReadyTime(), p1.peek(), 0);
							System.out.println("[EST = max(" + t2.getReadyTime() + ", " + p1.peek() + ", " + 0 + ")]");

						}
						if (t3.getUsage().equals("N")) {
							h3 = t3.calc_heuristic(t3.getReadyTime(), p1.peek(), 0);
							System.out.println("[EST = max(" + t3.getReadyTime() + ", " + p1.peek() + ", " + 0 + ")]");

						}

						if (t1.getUsage().equals("S")) {
							h1 = t1.calc_heuristic(t1.getReadyTime(), p1.peek(), 0);
							System.out.println("[EST = max(" + t1.getReadyTime() + ", " + p1.peek() + ", " + 0 + ")]");
						}

						if (t2.getUsage().equals("S")) {
							h2 = t2.calc_heuristic(t2.getReadyTime(), p1.peek(), 0);
							System.out.println("[EST = max(" + t2.getReadyTime() + ", " + p1.peek() + ", " + 0 + ")]");

						}
						if (t3.getUsage().equals("S")) {
							h3 = t3.calc_heuristic(t3.getReadyTime(), p1.peek(), 0);
							System.out.println("[EST = max(" + t3.getReadyTime() + ", " + p1.peek() + ", " + 0 + ")]");

						}

						if (t1.getUsage().equals("E")) {
							h1 = t1.calc_heuristic(t1.getReadyTime(), p1.peek(), p2.peek());
							System.out.println(
									"[EST = max(" + t1.getReadyTime() + ", " + p1.peek() + ", " + p2.peek() + ")]");
						}

						if (t2.getUsage().equals("E")) {
							h2 = t2.calc_heuristic(t1.getReadyTime(), p1.peek(), p2.peek());
							System.out.println(
									"[EST = max(" + t2.getReadyTime() + ", " + p1.peek() + ", " + p2.peek() + ")]");
						}
						if (t3.getUsage().equals("E")) {
							h3 = t3.calc_heuristic(t3.getReadyTime(), p1.peek(), p2.peek());
							System.out.println(
									"[EST = max(" + t3.getReadyTime() + ", " + p1.peek() + ", " + p2.peek() + ")]");
						}

					}

				}

				System.out.println("h1: " + h1);
				System.out.println("h2: " + h2);
				System.out.println("h3: " + h3);

				System.out.println("t1 deadline: " + t1.getDeadline());
				System.out.println("t2 deadline: " + t2.getDeadline());
				System.out.println("t3 deadline: " + t3.getDeadline());

				int h = Math.min(h1, Math.min(h2, h3));

				System.out.println("Smallest Heuristic Value:" + h);
				System.out.println("\n");
				if (h == h1) {
					if (p1.peek() == 0) {
						p1.push(t1.getExecTime() + t1.getReadyTime());
						executed.add(t1);

					} else if (p2.peek() == 0) {
						p2.push(t1.getExecTime() + t1.getReadyTime());
						executed.add(t1);
					}

					else {
						if (p1.peek() > p2.peek()) {
							p2.push(p2.peek() + t1.getExecTime()); // no need to add ready time since we already know
																	// p1/p2 isn't empty
							executed.add(t1);
						} else {
							p1.push(p1.peek() + t1.getExecTime());
							executed.add(t1);
						}
					}
				}

				else if (h == h2) {
					if (p1.peek() == 0) {
						p1.push(t2.getExecTime() + t2.getReadyTime());
						executed.add(t2);
					}

					else if (p2.peek() == 0) {
						p2.push(t2.getExecTime() + t2.getReadyTime());
						executed.add(t2);
					} else {
						if (p1.peek() > p2.peek()) {
							p2.push(p2.peek() + t2.getExecTime());
							executed.add(t2);
						} else {
							p1.push(p1.peek() + t2.getExecTime());
							executed.add(t2);
						}

					}
				}

				else if (h == h3) {

					if (p1.peek() == 0) {
						p1.push(t3.getExecTime() + t3.getReadyTime());
						executed.add(t3);
					} else if (p2.peek() == 0) {
						p2.push(t3.getExecTime() + t3.getReadyTime());
						executed.add(t3);

					} else {
						if (p1.peek() > p2.peek()) {
							p2.push(p2.peek() + t3.getExecTime()); // no need to add ready time since we already know
																	// p1/p2 isn't empty
							executed.add(t3);
						} else {
							p1.push(p1.peek() + t3.getExecTime());
							executed.add(t3);
						}

					}

				}

				if (!p1.isEmpty()) {
					System.out.println("Processor 1: " + p1.peek());
				}
				if (!p2.isEmpty()) {
					System.out.println("Processor 2: " + p2.peek());
					System.out.println("\n");
				}
				
				
				//backtracking logic here
				
				if(executed.size() == 3){ //this if statement will 100% probably need to be changed.
					System.out.println("Remaing tasks that haven't been chosen (for backtracking)");
					Stack<Task> n = new Stack<Task>();
					for(Task t : tasks) { //sort through the current task list and see which ones haven't been executed.				
						if(!executed.contains(t)) {
							n.push(t);
							System.out.println(t.getName());
						}
					}
					t1 = n.pop();
					t2 = n.pop();
		
					h1 = t2.calc_heuristic(t2.getReadyTime(), p2.peek(), 0); //t5
					h2 = t1.calc_heuristic(t1.getReadyTime(), t1.getReadyTime(), p2.peek()); //t3
					h = Math.min(h1, h2);
					
					if (h == h1) {
						if (p1.peek() == 0) {
							p1.push(t1.getExecTime() + t1.getReadyTime());
							executed.add(t1);

						} else if (p2.peek() == 0) {
							p2.push(t1.getExecTime() + t1.getReadyTime());
							executed.add(t1);
						}

						else {
							if (p1.peek() > p2.peek()) {
								p2.push(p2.peek() + t1.getExecTime()); // no need to add ready time since we already know
																		// p1/p2 isn't empty
								executed.add(t1);
							} else {
								p1.push(p1.peek() + t1.getExecTime());
								executed.add(t1);
							}
						}
					}

					else if (h == h2) {
						if (p1.peek() == 0) {
							p1.push(t2.getExecTime() + t2.getReadyTime());
							executed.add(t2);
						}

						else if (p2.peek() == 0) {
							p2.push(t2.getExecTime() + t2.getReadyTime());
							executed.add(t2);
						} else {
							if (p1.peek() > p2.peek()) {
								p2.push(p2.peek() + t2.getExecTime());
								executed.add(t2);
							} else {
								p1.push(p1.peek() + t2.getExecTime());
								executed.add(t2);
							}

						}
					}
					
					if (!p1.isEmpty()) {
						System.out.println("Processor 1: " + p1.peek());
					}
					if (!p2.isEmpty()) {
						System.out.println("Processor 2: " + p2.peek());
						System.out.println("\n");
					}
					
					for(Task t : tasks) { 				
						if(!executed.contains(t)) {
							n.push(t);
							System.out.println(t.getName());
						}
					}
					
					if(p1.peek() < p2.peek()) {
						p1.push(p1.peek() + n.pop().getExecTime());
					
					}
					else if(p2.peek() > p1.peek()) {
						p1.push(p1.peek() + n.pop().getExecTime());
					}
					
					System.out.println("Processor 1: " + p1.peek());
					System.out.println("Processor 2: " + p2.peek());
					
					System.out.println("No more tasks remaining. Set is feasible");
					
					
				}
				
				//backtracking logic ends here
				
				s++;
				
				

			} catch (Exception e) {
				completed = true;

			}

		}
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
