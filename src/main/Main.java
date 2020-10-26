package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import assets.Schedule;
import assets.Task;


public class Main {
	public static void main(String args[]) {
		
		int winSize = 3; //window size
		
		Task x = new Task("T1", 0, 5, 10, "N");
		Task x2 = new Task("T2", 0, 5, 1, "N");
		Task x3 = new Task("T3", 0, 5, 11, "N");
		Task x4 = new Task("T4", 0, 5, 4, "N");
		
		
		List<Task> list = new ArrayList<Task>();
		list.add(x4);
		list.add(x);
		list.add(x3);
		list.add(x2);
		
		printandSortTasks(list);
	
		//put all tasks ins queue
		Queue<Task> q = new LinkedList<Task>();
		for(int i = 0; i < list.size(); i++) {
			q.add(list.get(i));
		}
		
		
		Schedule schedule = new Schedule(q, winSize);
		schedule.execute();
		
		
		
	}
	
	
	public static void printandSortTasks(List<Task> list) {
		System.out.println("BEFORE: (Unsorted)");
		for(int i = 0; i < list.size(); i++) {
			System.out.println("Task Name: " + list.get(i).getName());
			System.out.println("Deadline: " + list.get(i).getDeadline());
			System.out.println();
			
		}
		
		System.out.println("-------------------------");
		//Did some research, Collections.sort runs at n*logn and mergesort has the same time complexity..
		Collections.sort(list, Comparator.comparing(Task::getDeadline));
		
		System.out.println("AFTER: (Sorted)");
		for(int i = 0; i < list.size(); i++) {
			System.out.println("Task Name: " + list.get(i).getName());
			System.out.println("Deadline: " + list.get(i).getDeadline());
			System.out.println();
	
		}
		
	}
	

}





