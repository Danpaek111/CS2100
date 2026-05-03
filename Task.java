// Name: Daniel Paek
// Computing ID: jff7dm
// Homework Name: HW 12 - Priority Queue of Tasks
// Resources Used: https://www.cs.usfca.edu/~galles/visualization/Heap.html

public class Task implements Comparable<Task> {
    private int priority;
    private String name;

    public Task(String s) {
        name = s;
        priority = 1;
    }

    public Task(String s, int p) { // equivalent to a node
        priority = p;
        name = s;
    }

    public String getName() {
        return name;
    }

    public void setName(String s) {
        name = s;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int p) {
        priority = p;
    }

    public int compareTo(Task other) {
        return this.priority - other.priority; // higher priority is greater
    }

    public String toString() {
        return "[ " + name + "," + priority + " ]";
    }

    public boolean equals(Object o) {
        if (o instanceof Task) {
            Task t = (Task) o;
            return name.equals(t.getName()) && priority == t.getPriority();
        }
        return false;
    }
}