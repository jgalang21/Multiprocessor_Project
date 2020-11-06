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
	private static List<Resource> resource;
	
	
	public Schedule(List<Task> tasks, int winSize) {
		this.tasks = tasks;
		this.winSize = winSize;
	}
	
	
	
	public static void execute()  {
		
		boolean completed = false;
		
		
		//i don't know how your resource class you made works, but i initialized them here
		Resource r1 = new Resource("R1", 0);
		Resource r2 = new Resource("R2", 0);
		
		int p1_usage = 0;
		int p2_usage = 1;
	
		int s = 0; //index within the window we're currently on
		
		p1.push(0);
		p2.push(0);
		
		while(completed == false) {
			
			try {
				//the window we're looking at
				// s: "0 1 2" 3 4 
				// Task 1 2 3 selected 
				
				Task t1 = tasks.get(s);
				Task t2 = tasks.get(s+1);
				Task t3 = tasks.get(s+2);
				
				
				int num_task = 3;
				
				//Resource: shared resource can use shared and exclusive resources
				//Exclusive: Can only use exclusive resources	
				int h1 = 0;
				int h2 = 0;
				int h3 = 0;
				

				System.out.println("t1 resource: " + t1.getUsage());
				System.out.println("t2 resource: " + t2.getUsage());
				System.out.println("t3 resource: " + t3.getUsage());

				//Need Flexibility between p1.peek() and p2.peek()
				//calc heuristic = deadline + EST
				if (p1_usage == 0 && p2_usage == 1) { //could replace these statements with the r1 and r2 i created above
					if (t1.getUsage().equals("N")) {
						h1 = t1.calc_heuristic(t1.getReadyTime(), p1.peek(), 0);
						System.out.println("[EST = max(" + t1.getReadyTime() + ", " + p1.peek() + ", " + 0 + ")]");
						}

					
					if (t2.getUsage().equals("S")) {
						h2 = t2.calc_heuristic(t2.getReadyTime(), p1.peek(), 0);
						
						//call the "lock() or unlock()" method here
						System.out.println("[EST = max(" + t2.getReadyTime() + ", " + p1.peek() + ", " + 0 + ")]");
					}
					
					if (t3.getUsage().equals("E")) {
						h3 = t3.calc_heuristic(t3.getReadyTime(), p1.peek(), 0);
						System.out.println("[EST = max(" + t3.getReadyTime() + ", " + p1.peek() + ", " + 0 + ")]");
					}
					
					p1_usage = 1;
				//   p2_usage = 0;
					
				} else if (p1_usage == 1 && p2_usage == 0) {
					h1 = t1.calc_heuristic(t1.getReadyTime(), p2.peek(), 0);
					h2 = t2.calc_heuristic(t2.getReadyTime(), p2.peek(), 0);
					h3 = t3.calc_heuristic(t3.getReadyTime(), p2.peek(), 0);
					
					//Need Flexibility between p1.peek() and p2.peek()
					
					System.out.println("[EST = max(" + t1.getReadyTime() + ", " + p2.peek() + ", " + 0 + ")]");
					System.out.println("[EST = max(" + t2.getReadyTime() + ", " + p2.peek() + ", " + 0 + ")]");
					System.out.println("[EST = max(" + t3.getReadyTime() + ", " + p2.peek() + ", " + 0 + ")]");
					//p1_usage = 0;
					p2_usage = 1;
				}
				else {					
					if(p1.peek() > p2.peek()) { // im still fixing this, i know this is wrong.
						if (t1.getUsage().equals("N") || t2.getUsage().equals("N") || t3.getUsage().equals("N")  ) {
							h1 = t1.calc_heuristic(t1.getReadyTime(), p2.peek(), 0);
							System.out.println("[EST = max(" + t1.getReadyTime() + ", " + p2.peek() + ", " + 0 + ")]");
							}

						
						if (t1 .getUsage().equals("S") || t2.getUsage().equals("S") || t3.getUsage().equals("S")) {
							h2 = t2.calc_heuristic(t2.getReadyTime(), p2.peek(), 0);
							
							//call the "lock() or unlock()" method here
							System.out.println("[EST = max(" + t2.getReadyTime() + ", " + p2.peek() + ", " + 0 + ")]");
						}
						
						if (t1.getUsage().equals("E") || t2.getUsage().equals("E") || t3.getUsage().equals("E")) {
							h3 = t3.calc_heuristic(t3.getReadyTime(), p2.peek(), 0);
							System.out.println("[EST = max(" + t3.getReadyTime() + ", " + p2.peek() + ", " + 0 + ")]");
						}
						
					}
					if(p2.peek() > p1.peek()) {
						if (t2.getUsage().equals("N") ) {
							h2 = t2.calc_heuristic(t2.getReadyTime(), p1.peek(), 0);
							System.out.println("[EST = max(" + t2.getReadyTime() + ", " + p1.peek() + ", " + 0 + ")]");
						
						}
						
						else if (t1.getUsage().equals("N")) {
							h1 = t1.calc_heuristic(t1.getReadyTime(), p1.peek(), 0);
							System.out.println("[EST = max(" + t1.getReadyTime() + ", " + p1.peek() + ", " + 0 + ")]");
						}
						
						else if (t3.getUsage().equals("N")) {
							h3 = t3.calc_heuristic(t3.getReadyTime(), p1.peek(), 0);
							System.out.println("[EST = max(" + t3.getReadyTime() + ", " + p1.peek() + ", " + 0 + ")]");
						}
						
						
						if (t1 .getUsage().equals("S") || t2.getUsage().equals("S") || t3.getUsage().equals("S")) {
							h2 = t2.calc_heuristic(t2.getReadyTime(), p1.peek(), 0);
							
							//call the "lock() or unlock()" method here
							System.out.println("[EST = max(" + t2.getReadyTime() + ", " + p1.peek() + ", " + 0 + ")]");
						}
						
						
						if (t1.getUsage().equals("E") ) {
							h1 = t1.calc_heuristic(t1.getReadyTime(), p1.peek(), p2.peek());
							System.out.println("[EST = max(" + t1.getReadyTime() + ", " + p1.peek() + ", " + p2.peek() + ")]");
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
				if(h == h1) {
					if(p1.peek() == 0) {
						p1.push(t1.getExecTime() + t1.getReadyTime());

					}
					else if(p2.peek() == 0) {
						p2.push(t1.getExecTime() + t1.getReadyTime());
					}
					
					else {
						if(p1.peek() > p2.peek()) {
							p2.push(p2.peek()+ t1.getExecTime() + t1.getReadyTime()); //  ---- note that i added p2.peek() here
						}
						else {
							p1.push(p1.peek() + t1.getExecTime() + t1.getReadyTime());  // same goes for here
						}
					}				}
				
				else if(h == h2) {
					if(p1.peek() == 0) {
						p1.push(t2.getExecTime() + t2.getReadyTime());
					}

					else if(p2.peek() == 0) {
						p2.push(t2.getExecTime() + t2.getReadyTime());
					}
					else {
						if(p1.peek() > p2.peek()) {
							p2.push(p2.peek() + t2.getExecTime());
						}
						else {
							p1.push(p1.peek() + t2.getExecTime());
						}

					}
				}
					
				
				
				
				else if(h == h3) {

					if(p1.peek() == 0) {
						p1.push(t3.getExecTime() + t3.getReadyTime());
					}
					else if(p2.peek() == 0) {
						p2.push(t3.getExecTime() + t3.getReadyTime());

					}
					else {
						if(p1.peek() > p2.peek()) {
							p2.push(p2.peek() + t3.getExecTime() + t3.getReadyTime());
						}
						else {
							p1.push(p1.peek() + t3.getExecTime() + t3.getReadyTime());
						}
						
					}
//					if(p1.isEmpty() || p1.peek() < t3.getExecTime()) {
//						p1.push(t3.getExecTime());
//					}
//					
//					else{
//						p2.push(t3.getExecTime());
//					}

					
				}
				
				if(!p1.isEmpty()) {
					System.out.println("Processor 1: " + p1.peek());
				}
				if(!p2.isEmpty()) {
					System.out.println("Processor 2: " + p2.peek());
					System.out.println("\n");
				}
				
				s++;
			
			}
			catch(Exception e ){
				completed = true;
				
			}
			
			
			
		}
	}
	
	public static int highest_From_Three(int num1, int num2, int num3){
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

	
	
			
		/**

		 * 
		 * I think for the processes we need to figure out what libraries allow for two processors to run,
		 * or we might not need need to worry about it.
		 * 
		 * I did some research and multi-*processing* is apparently going to provide worse performance:
		 * https://stackoverflow.com/questions/8001966/how-to-do-multiprocessing-in-java-and-what-speed-gains-to-expect
		 * 
		 * The link basically says we'd be running two JVMs and that would use up a lot of our CPU...
		 * 
		 * So I think in our case mutli-threading would probably be the way to go? I'm curious to know
		 * if we actually "need" multiprocessing-related libraries or if we can get away with multi-threading.
		 *
		 * Once we figure out the whole "process" agenda, we can essentially store each
		 * task in a queue, and assign it a resource for its execution time.
		 * 
		 * 
		 * 
		 */
		
		
		

	
	
	
}
