package assets;

import java.util.LinkedList;
import java.util.Queue;

public class Schedule {
	
	
	private static Queue<Task> tasks;
	private static int winSize;
	
	
	public Schedule(Queue<Task> tasks, int winSize) {
		this.tasks = tasks;
		this.winSize = winSize;
	}
	
	
	
	public static void execute(){
		Queue<Task> window = new LinkedList<Task>();
		for(int i = 0; i < winSize; i++) {
			
			window.add(tasks.remove());
			
		}
		
		int x = 0; //index within the window we're currently on
		while(!tasks.isEmpty()) {
			//the window we're looking at
			Task t1 = window.remove();
			Task t2 = window.remove();
			Task t3 = window.remove();
			
			//compute the heuristic
			int h = Math.min(t1.getTime() + t1.getDeadline(), Math.min(t2.getTime() + t2.getDeadline(),  t3.getTime() + t3.getDeadline()));
			
			
			
			
			
		}
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
