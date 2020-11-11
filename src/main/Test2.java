package main;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import assets.Schedule;
import assets.Task;

public class Test2 {

	public static void main(String[] args) {
		
		int winSize = 3; //window size
		
		Task x = new Task("T1", 0, 28, 80, "N");
		Task x2 = new Task("T2", 0, 24, 73, "E");
		Task x3 = new Task("T3", 14, 39, 95, "E");
		Task x4 = new Task("T4", 5, 25, 89, "N");
		Task x5 = new Task("T5", 35, 21, 108, "S");
		
		List<Task> list = new ArrayList<Task>();
		
		list.add(x);
		list.add(x2);
		list.add(x3);
		list.add(x4);
		list.add(x5);

		printandSortTasks(list);
		
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
