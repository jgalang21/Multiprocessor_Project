# Multiprocessor_Project
Multiprocessor Project using the Myopic Scheduling algorithm in Java.

This project was created in my Real-Time Operating Systems course. This project was intended to simulate the Myopic Scheduling Algorithm, which helps priotizes tasks based on
given heuristics. 

# A brief introduction to the algorithm

The Myopic Scheduling algorithm is a scheduling algorithm specifically geared towards multiprocessor systems. We had a high level understanding of how to decide the order
of the tasks, but implementing it in code was a new challenge. The success of this algorithm depended on the heuristics we chose and other variables such how many tasks
we're comparing and the number of backtracks performed. One example of a multiprocessor system this could be useful for is an automotive vehicle. There are many deadlines
that need to be met and could have severe consequences if missed. The algorithm is given a set of tasks with a ready time, execution time, deadline and resource usage. 
Resource usage simply means either a task can occur concurrently with another or it needs to run alone. 

# The status of the project

We were able to get small tasks sets working with our algorithm, but given the time constraints we were not able to get this project to work with larger task sets. However, 
learning more about complex algorithms in real time systems was very beneficial for our learning.
