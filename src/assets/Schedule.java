package assets;

import java.util.ArrayList;

public class Schedule {
	
	
	private ArrayList<Task> tasks = new ArrayList<Task>();
	private int winSize;
	
	
	public Schedule(ArrayList<Task> tasks, int winSize) {
		this.tasks = tasks;
		this.winSize = winSize;
	}

	
	
	public void sort() {
		
		/**
		 * Here I was thinking we could use Mergesort or some sorting algorithm to
		 * sort each task by deadline. (We'll need to sort by the Task's deadline property, i.e. the deadline)
		 * 
		 * 
		 * once that's sorted, we have our "schedule", meaning we know what order every window should be executed
		 * in. 
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
	
	
	
	
}
