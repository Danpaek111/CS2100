// Name: Daniel Paek
// Computing ID: jff7dm
// Homework Name: HW 12 - Priority Queue of Tasks
// Resources Used: https://www.cs.usfca.edu/~galles/visualization/Heap.html
package pq;

import java.util.*;

public class MyPriorityQueue<T extends Comparable<? super T>> implements SimplePQ<T> {
    ArrayList<T> priorityqueue;
    int pointer;

    public MyPriorityQueue() { // use an array
        priorityqueue = new ArrayList<>();
        pointer = 0; // size = 0;
    }

    public void add(T t) {
        pointer = priorityqueue.size();
        if (priorityqueue.size() == 0) { // if nothing's there
            priorityqueue.add(t);
        }
        else { // if something's there
            priorityqueue.add(t);
            if (t.compareTo(priorityqueue.get((pointer - 1) / 2)) > 0) { // if the new one is higher priority than its parent, then swap them
                while ((pointer > 0 && priorityqueue.get(pointer).compareTo(priorityqueue.get((pointer - 1) / 2)) > 0)) {
                    T temp = priorityqueue.get((pointer - 1) / 2);
                    priorityqueue.set((pointer - 1) / 2, priorityqueue.get(pointer));
                    // priorityqueue.add(temp);
                    priorityqueue.set(pointer, temp);
                    pointer = (pointer - 1) / 2;
                }
            }
        }
    }

    public T remove() {
        if (priorityqueue.size() == 0) { // if nothing's there
            return null;
        }
        T temp = priorityqueue.get(0);
        // swap first node with least priority right node
        priorityqueue.set(0, priorityqueue.get(priorityqueue.size() - 1));
        priorityqueue.set(priorityqueue.size() - 1, temp);
        // remove the last node
        priorityqueue.remove(priorityqueue.size() - 1);
        if (priorityqueue.size() == 0) {
            return temp;
        }
        // rebalance the heap
        pointer = 0; // set pointer to new root
        while (pointer <= priorityqueue.size() / 2 && priorityqueue.get(pointer) != null) {
            if (2 * pointer + 1 < priorityqueue.size() && 2 * pointer + 2 < priorityqueue.size()) {
                if (priorityqueue.get(pointer).compareTo(priorityqueue.get(2 * pointer + 1)) > 0 && priorityqueue.get(pointer).compareTo(priorityqueue.get(2 * pointer + 2)) > 0) {
                    break;
                }
                else if (priorityqueue.get(2 * pointer + 1).compareTo(priorityqueue.get(2 * pointer + 2)) > 0) {
                    // Left is smaller but check if swap is needed
                    T temp2 = priorityqueue.get(2 * pointer + 1);
                    priorityqueue.set(2 * pointer + 1, priorityqueue.get(pointer));
                    priorityqueue.set(pointer, temp2);
                    pointer = 2 * pointer + 1;
                }
                else {
                    // Right is smaller or equal but check if swap is needed
                    T temp2 = priorityqueue.get(2 * pointer + 2);
                    priorityqueue.set(2 * pointer + 2, priorityqueue.get(pointer));
                    priorityqueue.set(pointer, temp2);
                    pointer = 2 * pointer + 2;
                }
            }
            else if (2 * pointer + 1 < priorityqueue.size() && 2 * pointer + 2 >= priorityqueue.size()){ // if only one child
                if (priorityqueue.get(pointer).compareTo(priorityqueue.get(2 * pointer + 1)) < 0) { // left child is higher priority
                    if (2 * pointer + 2 >= priorityqueue.size()) { // if right child is null
                        T temp2 = priorityqueue.get(2 * pointer + 1);
                        priorityqueue.set(2 * pointer + 1, priorityqueue.get(pointer));
                        priorityqueue.set(pointer, temp2);
                        pointer = 2 * pointer + 1;
                    }
                } else {
                    break; // if leaf node, break
                }
            } else { // if no children
                break;
            }
        }
        return temp;
    }

    public void clear() {
        priorityqueue.clear();
    }

    public boolean contains(Object o) {
        return priorityqueue.contains(o);
    }

    public T peek() {
        if (priorityqueue.size() == 0)
            return null;
        return priorityqueue.get(0);
    };

    public int size() {
        return priorityqueue.size();
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < priorityqueue.size(); i++) {
            output += priorityqueue.get(i) + " ";
        }
        return output;
    }
}
