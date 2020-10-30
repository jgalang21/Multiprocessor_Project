package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import assets.Schedule;
import assets.Task;


public class Main { //check
	public static void main(String args[]) {
		
		int winSize = 3; //window size
		
		
		Task x = new Task("T1", 0, 6, 10, "N");
		Task x2 = new Task("T2", 2, 7, 13, "S");
		Task x3 = new Task("T3", 1, 3, 16, "E");
		Task x4 = new Task("T4", 2, 7, 17, "N");
		Task x5 = new Task("T5", 3, 9, 19, "N");
		
		
		List<Task> list = new ArrayList<Task>();
		list.add(x);
		list.add(x2);
		list.add(x3);
		list.add(x4);
		list.add(x5);
		
		printandSortTasks(list);
	
		//put all tasks ins queue
	
		
		Schedule schedule = new Schedule(list, winSize);
		schedule.execute();
		
		
		
		
	}
	
	
	public static void printandSortTasks(List<Task> list) {
		System.out.println("BEFORE: (Unsorted)");
		for(int i = 0; i < list.size(); i++) {
			System.out.println("Task Name: " + list.get(i).getName());
			System.out.println("Ready Time: " + list.get(i).getReadyTime());
			System.out.println("Exec Time: " + list.get(i).getExecTime());
			System.out.println("Deadline: " + list.get(i).getDeadline());
			System.out.println();
			
		}
		
		System.out.println("--------------------------------");
		//Did some research, Collections.sort runs at n*logn and mergesort has the same time complexity..
		Collections.sort(list, Comparator.comparing(Task::getDeadline));
		
		System.out.println("AFTER: (Sorted)");
		for(int i = 0; i < list.size(); i++) {
			System.out.println("Task Name: " + list.get(i).getName());
			System.out.println("Ready Time: " + list.get(i).getReadyTime());
			System.out.println("Exec Time: " + list.get(i).getExecTime());
			System.out.println("Deadline: " + list.get(i).getDeadline());
			System.out.println();
	
		}


		System.out.println("--------------------------------");
		System.out.println("Begin Heuristic calculations:");
		System.out.println("--------------------------------");
		
		
	}
	

}





