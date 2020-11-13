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
	public static boolean feasible;
	public static Task t1;
	public static Task t2;
	public static Task t3;
	public static boolean Locked;
	public static int Locked_count;
	
	public Schedule(List<Task> tasks, int winSize) {
		this.tasks = tasks;
		this.winSize = winSize;
	}

	public static void execute() {

		boolean completed = false;

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
				//Task t1 = new Task("F", 0, 0, 0, "F");
				//Task t2 = new Task("F", 0, 0, 0, "F");
				//Task t3 = new Task("F", 0, 0, 0, "F");
				System.out.println("s value: " + s);
				System.out.println("Task size: "+ tasks.size());
				System.out.println(tasks.get(3).getName());
				System.out.println(tasks.get(4).getName());
		//		if (s < tasks.size()-2) { //s = 3 5-2 =3
					t1 = tasks.get(s);
					t2 = tasks.get(s + 1);
					t3 = tasks.get(s + 2);
//				} else if (tasks.size() - s == 2) {
//					t1 = tasks.get(s);
//					t2 = tasks.get(s + 1);
//				} else { //the issue is here, i think
//					t1 = tasks.get(s);
//					
//				}
				
				//System.out.println(t1.getName());
				
				int num_task = 3;

				// Resource: shared resource can use shared and exclusive resources
				// Exclusive: Can only use exclusive resources
				int h1 = 0;
				int h2 = 0;
				int h3 = 0;
				
				List<Task> store = new ArrayList<Task>(3);
				List<Integer> EST_store = new ArrayList<Integer>(3);
				
				System.out.println("t1 name: " + t1.getName() + "t1 resource: " + t1.getUsage());
				System.out.println("t2 name: " + t1.getName() + "t2 resource: " + t2.getUsage());
				System.out.println("t3 name: " + t1.getName() + "t3 resource: " + t3.getUsage());

				// Need Flexibility between p1.peek() and p2.peek()
				// calc heuristic = deadline + EST
				if (p1_usage == 0 && p2_usage == 1) { 
					if (t1.getUsage().equals("N")) {
						h1 = t1.calc_heuristic(t1.getReadyTime(), p1.peek(), 0);
						store.add(t1);
						EST_store.add(highest_From_Three(t1.getReadyTime(), p1.peek(), 0));
						System.out.println("[t1 EST = max(" + t1.getReadyTime() + ", " + p1.peek() + ", " + 0 + ")]");
						
					}

					if (t2.getUsage().equals("N")) {
						h2 = t2.calc_heuristic(t2.getReadyTime(), p1.peek(), 0);
						store.add(t2);
						EST_store.add(highest_From_Three(t2.getReadyTime(), p1.peek(), 0));
						System.out.println("[t2 EST = max(" + t2.getReadyTime() + ", " + p1.peek() + ", " + 0 + ")]");

					}
					if (t3.getUsage().equals("N")) {
						h3 = t3.calc_heuristic(t3.getReadyTime(), p1.peek(), 0);
						store.add(t3);
						EST_store.add(highest_From_Three(t3.getReadyTime(), p1.peek(), 0));
						System.out.println("[t3 EST = max(" + t3.getReadyTime() + ", " + p1.peek() + ", " + 0 + ")]");

					}

					if (t1.getUsage().equals("S")) {
						h1 = t1.calc_heuristic(t1.getReadyTime(), p1.peek(), 0);
						store.add(t1);
						EST_store.add(highest_From_Three(t1.getReadyTime(), p1.peek(), 0));
						Locked = true;
						Locked_count++;
						System.out.println("p1 has been locked.");
						System.out.println("[t1 EST = max(" + t1.getReadyTime() + ", " + p1.peek() + ", " + 0 + ")]");
					}

					if (t2.getUsage().equals("S")) {
						h2 = t2.calc_heuristic(t2.getReadyTime(), p1.peek(), 0);
						store.add(t2);
						EST_store.add(highest_From_Three(t2.getReadyTime(), p1.peek(), 0));
						Locked = true;
						Locked_count++;
						System.out.println("p1 has been locked.");
						System.out.println("[t2 EST = max(" + t2.getReadyTime() + ", " + p1.peek() + ", " + 0 + ")]");

					}
					if (t3.getUsage().equals("S")) {
						h3 = t3.calc_heuristic(t3.getReadyTime(), p1.peek(), 0);
						store.add(t3);
						EST_store.add(highest_From_Three(t3.getReadyTime(), p1.peek(), 0));
						Locked = true;
						Locked_count++;
						System.out.println("p1 has been locked.");
						System.out.println("[t3 EST = max(" + t3.getReadyTime() + ", " + p1.peek() + ", " + 0 + ")]");

					}

					if (t1.getUsage().equals("E")) 
					{
						h1 = t1.calc_heuristic(t1.getReadyTime(), p1.peek(), p2.peek());
						store.add(t1);
						
						if (Locked_count == 1 && Locked == true) 
						{
							EST_store.add(highest_From_Three(t1.getReadyTime(), p1.peek(), 0));
							System.out.println(
									"[t1 EST = max(" + t1.getReadyTime() + ", " + p1.peek() + ", " + 0 + ")]");
						}
						else if (Locked_count == 0 && Locked == true) {
							if (p1.peek() > p2.peek()) 
							{
								EST_store.add(highest_From_Three(t1.getReadyTime(), p1.peek(), p1.peek()));
								System.out.println(
										"[t1 EST = max(" + t1.getReadyTime() + ", " + p1.peek() + ", " + p1.peek() + ")]");
							} 
							
							else 
							{
								EST_store.add(highest_From_Three(t1.getReadyTime(), p1.peek(), p2.peek()));
								System.out.println(
										"[t1 EST = max(" + t1.getReadyTime() + ", " + p1.peek() + ", " + p2.peek() + ")]");
							}
						}
						
						else if (Locked == false && Locked_count == 0) 
						{
							EST_store.add(highest_From_Three(t1.getReadyTime(), p1.peek(), 0));
							System.out.println(
									"[t1 EST = max(" + t1.getReadyTime() + ", " + p1.peek() + ", " + 0 + ")]");
						}
						
						else if (Locked == true && Locked_count > 1)
						{
							if (p1.peek() > p2.peek()) 
							{
								EST_store.add(highest_From_Three(t1.getReadyTime(), p1.peek(), p1.peek()));
								System.out.println(
										"[t1 EST = max(" + t1.getReadyTime() + ", " + p1.peek() + ", " + p1.peek() + ")]");
							} 
							
							else 
							{
								EST_store.add(highest_From_Three(t1.getReadyTime(), p1.peek(), p2.peek()));
								System.out.println(
										"[t1 EST = max(" + t1.getReadyTime() + ", " + p1.peek() + ", " + p2.peek() + ")]");
							}
						}
						Locked_count = 0;
						
					}

					if (t2.getUsage().equals("E")) {
						h2 = t2.calc_heuristic(t2.getReadyTime(), p1.peek(), p2.peek());
						store.add(t2);
						
						if (Locked_count == 1 && Locked == true) 
						{
							EST_store.add(highest_From_Three(t2.getReadyTime(), p1.peek(), 0));
							System.out.println(
									"[t2 EST = max(" + t2.getReadyTime() + ", " + p1.peek() + ", " + 0 + ")]");
						}
						else if (Locked_count == 0 && Locked == true) {
							if (p1.peek() > p2.peek()) 
							{
								EST_store.add(highest_From_Three(t1.getReadyTime(), p1.peek(), p1.peek()));
								System.out.println(
										"[t1 EST = max(" + t1.getReadyTime() + ", " + p1.peek() + ", " + p1.peek() + ")]");
							} 
							
							else 
							{
								EST_store.add(highest_From_Three(t1.getReadyTime(), p1.peek(), p2.peek()));
								System.out.println(
										"[t1 EST = max(" + t1.getReadyTime() + ", " + p1.peek() + ", " + p2.peek() + ")]");
							}
						}
						else if (Locked == false && Locked_count == 0) 
						{
							EST_store.add(highest_From_Three(t2.getReadyTime(), p1.peek(), 0));
							System.out.println(
									"[t2 EST = max(" + t2.getReadyTime() + ", " + p1.peek() + ", " + 0 + ")]");
						}
						else if (Locked == true && Locked_count > 1)
						{
							if (p1.peek() > p2.peek()) 
							{
								EST_store.add(highest_From_Three(t2.getReadyTime(), p1.peek(), p1.peek()));
								System.out.println(
										"[t2 EST = max(" + t2.getReadyTime() + ", " + p1.peek() + ", " + p1.peek() + ")]");
							} 
							
							else 
							{
								EST_store.add(highest_From_Three(t2.getReadyTime(), p1.peek(), p2.peek()));
								System.out.println(
										"[t2 EST = max(" + t2.getReadyTime() + ", " + p1.peek() + ", " + p2.peek() + ")]");
							}
							
						}
						Locked_count = 0;
						
					}
					if (t3.getUsage().equals("E")) {
						h3 = t3.calc_heuristic(t3.getReadyTime(), p1.peek(), p2.peek());
						store.add(t3);
						
						if (Locked_count == 1 && Locked == true) 
						{
							EST_store.add(highest_From_Three(t3.getReadyTime(), p1.peek(), 0));
							System.out.println(
									"[t3 EST = max(" + t3.getReadyTime() + ", " + p1.peek() + ", " + 0 + ")]");
						}
						else if (Locked_count == 0 && Locked == true) {
							
						}
						else if (Locked == false && Locked_count == 0) 
						{
							EST_store.add(highest_From_Three(t3.getReadyTime(), p1.peek(), 0));
							System.out.println(
									"[t3 EST = max(" + t3.getReadyTime() + ", " + p1.peek() + ", " + 0 + ")]");
						}
						else if (Locked == true && Locked_count > 1)
						{
							if (p1.peek() > p2.peek()) 
							{
								EST_store.add(highest_From_Three(t3.getReadyTime(), p1.peek(), p1.peek()));
								System.out.println(
										"[t3 EST = max(" + t3.getReadyTime() + ", " + p1.peek() + ", " + p1.peek() + ")]");
							} 
							
							else 
							{
								EST_store.add(highest_From_Three(t3.getReadyTime(), p1.peek(), p2.peek()));
								System.out.println(
										"[t3 EST = max(" + t3.getReadyTime() + ", " + p1.peek() + ", " + p2.peek() + ")]");
							}
							
						}
						Locked_count = 0;
						
					}


				} else if (p1_usage == 1 && p2_usage == 0) {
					if (t1.getUsage().equals("N")) {
						h1 = t1.calc_heuristic(t1.getReadyTime(), p2.peek(), 0);
						store.add(t1);
						EST_store.add(highest_From_Three(t1.getReadyTime(), p2.peek(), 0));
						System.out.println("[t1 EST = max(" + t1.getReadyTime() + ", " + p2.peek() + ", " + 0 + ")]");
					}

					if (t2.getUsage().equals("N")) {
						h2 = t2.calc_heuristic(t2.getReadyTime(), p2.peek(), 0);
						store.add(t2);
						EST_store.add(highest_From_Three(t2.getReadyTime(), p2.peek(), 0));
						System.out.println("[t2 EST = max(" + t2.getReadyTime() + ", " + p2.peek() + ", " + 0 + ")]");

					}
					if (t3.getUsage().equals("N")) {
						h3 = t3.calc_heuristic(t3.getReadyTime(), p2.peek(), 0);
						store.add(t3);
						EST_store.add(highest_From_Three(t3.getReadyTime(), p2.peek(), 0));
						System.out.println("[t3 EST = max(" + t3.getReadyTime() + ", " + p2.peek() + ", " + 0 + ")]");

					}

					if (t1.getUsage().equals("S")) {
						h1 = t1.calc_heuristic(t1.getReadyTime(), p2.peek(), 0);
						store.add(t1);
						EST_store.add(highest_From_Three(t1.getReadyTime(), p2.peek(), 0));
						Locked = true;
						Locked_count++;
						System.out.println("p2 has been locked.");
						System.out.println("[t1 EST = max(" + t1.getReadyTime() + ", " + p2.peek() + ", " + 0 + ")]");
					}

					if (t2.getUsage().equals("S")) {
						System.out.println("p2.peek in the last: " + p2.peek());
						h2 = t2.calc_heuristic(t2.getReadyTime(), p2.peek(), 0);
						store.add(t2);
						EST_store.add(highest_From_Three(t2.getReadyTime(), p2.peek(), 0));
						Locked = true;
						Locked_count++;
						System.out.println("p2 has been locked.");
						System.out.println("[t2 EST = max(" + t2.getReadyTime() + ", " + p2.peek() + ", " + 0 + ")]");

					}
					if (t3.getUsage().equals("S")) {
						h3 = t3.calc_heuristic(t3.getReadyTime(), p2.peek(), 0);
						store.add(t3);
						EST_store.add(highest_From_Three(t3.getReadyTime(), p2.peek(), 0));
						Locked = true;
						Locked_count++;
						System.out.println("p2 has been locked.");
						System.out.println("[t3 EST = max(" + t3.getReadyTime() + ", " + p2.peek() + ", " + 0 + ")]");

					}

					if (t1.getUsage().equals("E")) {
						h1 = t1.calc_heuristic(t1.getReadyTime(), p2.peek(), p1.peek());
						store.add(t1);
						
						if (Locked_count == 1 && Locked == true) 
						{
							EST_store.add(highest_From_Three(t1.getReadyTime(), p2.peek(), 0));
							System.out.println(
									"[t1 EST = max(" + t1.getReadyTime() + ", " + p2.peek() + ", " + 0 + ")]");
						}
						else if (Locked_count == 0 && Locked == true) {
							if (p1.peek() > p2.peek()) 
							{
								EST_store.add(highest_From_Three(t1.getReadyTime(), p2.peek(), p1.peek()));
								System.out.println(
										"[t1 EST = max(" + t1.getReadyTime() + ", " + p2.peek() + ", " + p1.peek() + ")]");
							} 
							
							else 
							{
								EST_store.add(highest_From_Three(t1.getReadyTime(), p2.peek(), p2.peek()));
								System.out.println(
										"[t1 EST = max(" + t1.getReadyTime() + ", " + p2.peek() + ", " + p2.peek() + ")]");
							}
						}
						else if (Locked == false && Locked_count == 0) 
						{
							EST_store.add(highest_From_Three(t1.getReadyTime(), p2.peek(), 0));
							System.out.println(
									"[t1 EST = max(" + t1.getReadyTime() + ", " + p2.peek() + ", " + 0 + ")]");
						}
						else if (Locked == true && Locked_count > 1)
						{
							if (p1.peek() > p2.peek()) 
							{
								EST_store.add(highest_From_Three(t1.getReadyTime(), p2.peek(), p1.peek()));
								System.out.println(
										"[t1 EST = max(" + t1.getReadyTime() + ", " + p2.peek() + ", " + p1.peek() + ")]");
							} 
							
							else 
							{
								EST_store.add(highest_From_Three(t1.getReadyTime(), p2.peek(), p2.peek()));
								System.out.println(
										"[t1 EST = max(" + t1.getReadyTime() + ", " + p2.peek() + ", " + p2.peek() + ")]");
							}
							
						}
						Locked_count = 0;
					}

					if (t2.getUsage().equals("E")) {
						h2 = t2.calc_heuristic(t2.getReadyTime(), p2.peek(), p1.peek());
						store.add(t2);
						
						if (Locked_count == 1 && Locked == true) 
						{
							EST_store.add(highest_From_Three(t2.getReadyTime(), p2.peek(), 0));
							System.out.println(
									"[t2 EST = max(" + t2.getReadyTime() + ", " + p2.peek() + ", " + 0 + ")]");
						}
						else if (Locked_count == 0 && Locked == true) {
							if (p1.peek() > p2.peek()) 
							{
								EST_store.add(highest_From_Three(t2.getReadyTime(), p2.peek(), p1.peek()));
								System.out.println(
										"[t2 EST = max(" + t2.getReadyTime() + ", " + p2.peek() + ", " + p1.peek() + ")]");
							} 
							
							else 
							{
								EST_store.add(highest_From_Three(t2.getReadyTime(), p2.peek(), p2.peek()));
								System.out.println(
										"[t2 EST = max(" + t2.getReadyTime() + ", " + p2.peek() + ", " + p2.peek() + ")]");
							}
						}
						else if (Locked == false && Locked_count == 0) 
						{
							EST_store.add(highest_From_Three(t2.getReadyTime(), p2.peek(), 0));
							System.out.println(
									"[t2 EST = max(" + t2.getReadyTime() + ", " + p2.peek() + ", " + 0 + ")]");
						}
						else if (Locked == true && Locked_count > 1)
						{
							if (p1.peek() > p2.peek()) 
							{
								EST_store.add(highest_From_Three(t2.getReadyTime(), p2.peek(), p1.peek()));
								System.out.println(
										"[t2 EST = max(" + t2.getReadyTime() + ", " + p2.peek() + ", " + p1.peek() + ")]");
							} 
							
							else 
							{
								EST_store.add(highest_From_Three(t2.getReadyTime(), p2.peek(), p2.peek()));
								System.out.println(
										"[t2 EST = max(" + t2.getReadyTime() + ", " + p2.peek() + ", " + p2.peek() + ")]");
							}
							
						}
						Locked_count = 0;
						
					}
					if (t3.getUsage().equals("E")) {
						h3 = t3.calc_heuristic(t3.getReadyTime(), p2.peek(), p1.peek());
						store.add(t3);
						if (Locked_count == 1 && Locked == true) 
						{
							EST_store.add(highest_From_Three(t3.getReadyTime(), p2.peek(), 0));
							System.out.println(
									"[t3 EST = max(" + t3.getReadyTime() + ", " + p2.peek() + ", " + 0 + ")]");
						}
						else if (Locked_count == 0 && Locked == true) {
							if (p1.peek() > p2.peek()) 
							{
								EST_store.add(highest_From_Three(t3.getReadyTime(), p2.peek(), p1.peek()));
								System.out.println(
										"[t3 EST = max(" + t3.getReadyTime() + ", " + p2.peek() + ", " + p1.peek() + ")]");
							} 
							
							else 
							{
								EST_store.add(highest_From_Three(t3.getReadyTime(), p2.peek(), p2.peek()));
								System.out.println(
										"[t3 EST = max(" + t3.getReadyTime() + ", " + p2.peek() + ", " + p2.peek() + ")]");
							}
						}
						else if (Locked == false && Locked_count == 0) 
						{
							EST_store.add(highest_From_Three(t3.getReadyTime(), p2.peek(), 0));
							System.out.println(
									"[t3 EST = max(" + t3.getReadyTime() + ", " + p2.peek() + ", " + 0 + ")]");
						}
						else if (Locked == true && Locked_count > 1)
						{
							if (p1.peek() > p2.peek()) 
							{
								EST_store.add(highest_From_Three(t3.getReadyTime(), p2.peek(), p1.peek()));
								System.out.println(
										"[t3 EST = max(" + t3.getReadyTime() + ", " + p2.peek() + ", " + p1.peek() + ")]");
							} 
							
							else 
							{
								EST_store.add(highest_From_Three(t3.getReadyTime(), p2.peek(), p2.peek()));
								System.out.println(
										"[t3 EST = max(" + t3.getReadyTime() + ", " + p2.peek() + ", " + p2.peek() + ")]");
							}
							
						}
						Locked_count = 0;
					}
					

				

				// Note that here you need to keep that all as if statements, adding "else if"
				// seems to break the code


				}
				
					
				
				System.out.println("h1: " + h1);
				System.out.println("h2: " + h2);
				System.out.println("h3: " + h3);

				System.out.println("t1 deadline: " + t1.getDeadline());
				System.out.println("t2 deadline: " + t2.getDeadline());
				System.out.println("t3 deadline: " + t3.getDeadline());

				int h = Math.min(h1, Math.min(h2, h3));
				
				Boolean[] feasible_arr = new Boolean[store.size()];
				
				for (int i = 0; i < store.size(); i++) {
					System.out.println(store.get(i).getDeadline() + " Feasibility: " + store.get(i).getExecTime() +
							" +" + " EST: " + EST_store.get(i) + " = " + (store.get(i).getExecTime()+EST_store.get(i)));
					System.out.println("Feasible?: " + feasibility_check(EST_store.get(i), store.get(i).getExecTime(), 
							store.get(i).getDeadline()));
					feasible_arr[i] = feasibility_check(EST_store.get(i), store.get(i).getExecTime(), 
							store.get(i).getDeadline());
				}
				
				boolean keep_going = true;
				boolean backtrack = false;
				for (int i = 0; i < feasible_arr.length; i++) {
					System.out.println(feasible_arr[i]);
					if (feasible_arr[i] == false) {
						keep_going = false;
						backtrack = true;
						if (p1_usage == 1 && p2_usage == 0) {
							p1_usage = 0;
							p2_usage = 1;
						} else {
							p1_usage = 1;
							p2_usage = 0;
						}
							
					} else {
						keep_going = true;
						backtrack = false;
						if (p1_usage == 1 && p2_usage == 0) {
							p1_usage = 0;
							p2_usage = 1;
						} else {
							p1_usage = 1;
							p2_usage = 0;
						}
					}
				}
				
				if (keep_going == false) {
					
					completed = false;
					
					
				} else if (keep_going == true) {
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
					System.out.println("Executed size: " + executed.size());
					System.out.println("tasks size: " + tasks.size());
					

					while (backtrack == true) {					

					
						if(executed.size() == tasks.size()-2){ //this if statement will 100% probably need to be changed.
							System.out.println("Remaing tasks that haven't been chosen (for backtracking)");
							Stack<Task> n = new Stack<Task>();
							for(Task t : tasks) { //sort through the current task list and see which ones haven't been executed.				
								if(!executed.contains(t)) {
									n.push(t);
									
								}
							}
							t1 = n.pop();
							t2 = n.pop();
							
							System.out.println("p1 peek before backtrack: " + p1.peek());
							System.out.println("p2 peek before backtrack: " + p2.peek());
							
							h1 = t2.calc_heuristic(t2.getReadyTime(), p2.peek(), 0); //t5
							h2 = t1.calc_heuristic(t1.getReadyTime(), t1.getReadyTime(), p2.peek()); //t3
							h = Math.min(h1, h2);
							
							System.out.println("t5 EST in back: " + h1 );
							System.out.println("t3 EST in back: " + h2 );
							
							System.out.println("t5 Comp: " + t1.getExecTime());
							System.out.println("t3 Comp: " + t2.getExecTime());
							
							if (h == h1) {
								if (p1.peek() == 0) {
									System.out.println("p1.peek == 0");
									p1.push(t1.getExecTime() + t1.getReadyTime());
									executed.add(t1);
		
								} else if (p2.peek() == 0) {
									System.out.println("p2.peek == 0");
									p2.push(t1.getExecTime() + t1.getReadyTime());
									executed.add(t1);
								}
		
								else {
									if (p1_usage == 1 && p2_usage == 0) {
										System.out.println("p1.peek > p2.peek this?");
										p2.push(p2.peek() + t2.getExecTime()); // no need to add ready time since we already know
										// p1/p2 isn't empty
										int new_EST = Math.max(t2.getReadyTime(), Math.max(p2.peek(), 0));
										EST_store.add(new_EST);
										feasible = feasibility_check(EST_store.get(EST_store.size()-1),t2.getExecTime(),t2.getDeadline());
										
										if (feasible == false) {
											System.out.println("This is not feasible");
											System.out.println("--------Backtrack Starts--------");
											backtrack = true;
										} else {
											System.out.println("This is feasible");
										}
										
										executed.add(t2);
										p1_usage = 0;
										p2_usage = 1;
										
									} else {
										System.out.println("The task on P2");
										System.out.println("this last?");
										p1.push(p1.peek() + t2.getExecTime());
										executed.add(t1);
										
										p1_usage = 1;
										p2_usage = 0;
									}
								}
							}
		
							else if (h == h2) {
								if (p1.peek() == 0) {
									System.out.println("p1.peek == 0");
									p1.push(t2.getExecTime() + t2.getReadyTime());
									executed.add(t2);
								}
		
								else if (p2.peek() == 0) {
									System.out.println("p2.peek == 0");
									p2.push(t2.getExecTime() + t2.getReadyTime());
									executed.add(t2);
								} else {
									if (p1_usage == 1 && p2_usage == 0) {
										System.out.println("The task on P2");
										System.out.println("this last?");
										p2.push(p2.peek() + t2.getExecTime());
										executed.add(t2);
										p1_usage = 0;
										p2_usage = 1;
									} else {
										System.out.println("The task on P1");
										System.out.println("this 2 last?");
										p1.push(p1.peek() + t2.getExecTime());
										executed.add(t2);
										p1_usage = 1;
										p2_usage = 0;
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
							System.out.println("EST store: " + EST_store.get(0) + " store: " + store.get(0).getExecTime());
							System.out.println("EST store: " + EST_store.get(1) + " store: " + store.get(1).getExecTime());
							System.out.println("EST store: " + EST_store.get(2) + " store: " + store.get(2).getExecTime());
							for(Task t : tasks) { 				
								if(!executed.contains(t)) {
									n.push(t);
									System.out.println(t.getName());
								}
							}
							
							
						if (backtrack == false) {
							if(p1_usage == 0 && p2_usage == 1) {
								System.out.println("p1 peek: " + p1.peek()); //49
								System.out.println("p2 peek: " + p2.peek()); //67
								EST_store.add(highest_From_Three(t2.getReadyTime(),p2.peek(),0));
								System.out.println(EST_store.get(4));
								System.out.println(t2.getExecTime());
								System.out.println(t2.getDeadline());
								feasible = feasibility_check(EST_store.get(EST_store.size()-1),t1.getExecTime(),t1.getDeadline());
								
								if (feasible == false) {
									System.out.println("This is not feasible");
									System.out.println("--------Backtrack Starts--------");
									backtrack = true;
									p2.pop();
									
								} else {
									System.out.println("This is feasible");
									p1.push(p1.peek() + n.pop().getExecTime());
									System.out.println("T2 ready: " + t2.getReadyTime());
									System.out.println("p1 peek: " + p1.peek()); //49
									System.out.println("p2 peek: " + p2.peek()); //67
									System.out.println("This is last? 3 : " + t2.getName());
									System.out.println(EST_store.get(EST_store.size()-1));
									backtrack = false;
								}
								

								
								
								p1_usage = 1;
								p2_usage = 0;
							}
							else if(p1_usage == 1 && p2_usage == 0) {
								System.out.println("For last task");
								EST_store.add(highest_From_Three(t2.getReadyTime(),p1.peek(),p2.peek()));
								feasible = feasibility_check(EST_store.get(EST_store.size()-1),t2.getExecTime(),t2.getDeadline());
								
								if (feasible == false) {
									System.out.println("This is not feasible");
									System.out.println("--------Backtrack Starts--------");
									backtrack = true;
								} else {
									System.out.println("This is feasible");
									backtrack = false;
								}
								p1.push(p1.peek() + n.pop().getExecTime());
								System.out.println("This is last? 5 : " + t2.getName());
								p1_usage = 0;
								p2_usage = 1;
							}
							
							System.out.println("Processor 1: " + p1.peek());
							System.out.println("Processor 2: " + p2.peek());
							

							System.out.println("No more tasks remaining. Set is feasible");
						}
							
								completed=true;
							
							
						} else {
							keep_going = false;
						}
					}
					//backtracking logic ends here
					
					
				}
				s++; //this might be in the wrong place, so i put it here.
				
				

			} catch (Exception e) {
				System.out.println("Task schedule not feasible");
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

	public static boolean feasibility_check(int EST, int comp, int dead) {
		boolean result = false;
		
		if (EST + comp > dead) {
			result = false;
		} else {
			result = true;
		}
		
		return result;
	}

}
