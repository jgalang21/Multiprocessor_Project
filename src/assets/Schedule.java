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
				
				
				h1 = t1.calc_heuristic(t1.getReadyTime(), p1.peek(), 0);
				h2 = t2.calc_heuristic(t2.getReadyTime(), p1.peek(), 0);
				h3 = t3.calc_heuristic(t3.getReadyTime(), p1.peek(), 0);
				
				System.out.println("h1: " + h1);
				System.out.println("h2: " + h2);
				System.out.println("h3: " + h3);
				
				int h = Math.min(h1, Math.min(h2, h3));
				
				System.out.println("Smallest Heuristic Value:" + h);
				
				if(h == h1) {
					if(p1.peek() == 0) {
						p1.push(t1.getExecTime() + t1.getReadyTime());
					}
					else if(p2.peek() == 0) {
						p2.push(t1.getExecTime() + t1.getReadyTime());
					}
					
					else {
						if(p1.peek() > p2.peek()) {
							p2.push(t1.getExecTime() + t1.getReadyTime());
						}
						else {
							p1.push(t1.getExecTime() + t1.getReadyTime()); 
						}
					}
				}
				
				else if(h == h2) {
					if(p1.peek() == 0) {
						p1.push(t2.getExecTime() + t2.getReadyTime());
					}
					
					else if(p2.peek() == 0) {
						p2.push(t2.getExecTime() + t2.getReadyTime());
					}
					else {
						if(p1.peek() > p2.peek()) {
							p2.push(t2.getExecTime() + t2.getReadyTime());
						}
						else {
							p1.push(t2.getExecTime() + t2.getReadyTime());
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
							p2.push(t3.getExecTime() + t3.getReadyTime());
						}
						else {
							p1.push(t3.getExecTime() + t3.getReadyTime());
						}

					}

					
				}
				
				if(!p1.isEmpty()) {
					System.out.println("Processor 1: " + p1.peek());
				}
				if(!p2.isEmpty()) {
					System.out.println("Processor 2: " + p2.peek());
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
