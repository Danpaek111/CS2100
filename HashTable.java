// Name: Daniel Paek
// Computing ID: jff7dm@virginia.edu
// Homework Name: HW 11 - Hash Tables
// Resources used: None
package hash;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Hash Table implementation.
 *
 * @param <K>
 * @param <V>
 */
public class HashTable<K,V> implements SimpleMap<K,V>{

    private static final int INITIAL_CAP = 5;  // a default initial capacity (set low for initial debugging)
    private int currentCapacity = INITIAL_CAP;

    /*
     * Here are some hints about how to declare your hash table.
     * If you're using an ArrayList, it might look like this:
     * 		private ArrayList<HashNode<K, V>> table;
     * Note that you cannot declare an array of generics (i.e., an array of HashNodes) like this:
     *          private LinkedList<HashNode<K,V>>[] table;
     * but see here https://programming.guide/java/generic-array-creation.html for workarounds.
     */

    /* YOU WILL LIKELY WANT MORE PRIVATE VARIABLES HERE */
    // one linked list per bucket (chain)
    private ArrayList<LinkedList<HashNode<K,V>>> table;
    //number of key/value pairs stored
    private int size;
    //load factor
    private static final double MAX_LOAD_FACTOR = 0.5;

    public HashTable() {  // default constructor sets capacity to default value
        this(INITIAL_CAP);
    }

    public HashTable(int capacity) {  // constructor sets capacity to given value
        /* TODO: IMPLEMENT THIS METHOD */
        if(capacity <= 0){
            capacity = INITIAL_CAP;
        }
        this.currentCapacity = capacity;
        this.size = 0;
        table =  new ArrayList<LinkedList<HashNode<K,V>>>(capacity);
        for (int i = 0; i < capacity; i++){
            table.add(new LinkedList<HashNode<K,V>>());
        }
        /*
         * Here are some hints about how to allocate memory for your hash table.
         * If you're using an array, it might look like this:
         * 		this.table = (HashNode<K,V>[]) new HashNode<?,?>[initialCapacity];
         * If you're using an ArrayList, it might look like this:
         * 		this.table = new ArrayList<>(capacity); // sets list's initial capacity
         */
    }

    public int getSize() { return size; }

    // insert() adds a new key/value pair if the key is not found, otherwise it replaces
    //    the existing key's value
    @Override
    public void insert(K key, V value) {
        /* TODO: IMPLEMENT THIS METHOD */
        if (key == null){
            return;
        }
        double lf = (double)(size + 1) / (double)currentCapacity;
        if (lf > MAX_LOAD_FACTOR){
            resize(nextPrime(currentCapacity * 2));
        }
        int idx = indexFor(key);
        LinkedList<HashNode<K,V>> bucket = table.get(idx);
        //Replace if key exists with enhanced loop
        for (HashNode<K,V> node : bucket){
            if(node.getKey().equals(key)){
                node.setValue(value);
                return;
            }
        }
        bucket.addFirst(new HashNode<K,V>(key, value));
        size++;
    }
    @Override
    public V retrieve(K key) {
        /* TODO: IMPLEMENT THIS METHOD */
        if (key == null){
            return null;
        }
        int idx = indexFor(key);
        LinkedList<HashNode<K,V>> bucket = table.get(idx);
        for (HashNode<K,V> node : bucket){
            if(node.getKey().equals(key)){
                return node.getValue();
            }
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        /* TODO: IMPLEMENT THIS METHOD */
        if (key == null){
            return false;
        }
        int idx = indexFor(key);
        LinkedList<HashNode<K,V>> bucket = table.get(idx);
        for (HashNode<K,V> node : bucket){
            if (node.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void remove(K key) {
        /* TODO: IMPLEMENT THIS METHOD */
        if(key == null){
            return;
        }
        int idx = indexFor(key);
        LinkedList<HashNode<K,V>> bucket = table.get(idx);
        Iterator<HashNode<K,V>> it = bucket.iterator();
        while(it.hasNext()){
            HashNode<K,V> node = it.next();
            if(node.getKey().equals(key)){
                it.remove();
                size--;
                return;
            }
        }
    }


    /*
     * OPTIONAL HELPER METHODS: The next two methods will let you print out your
     * entire hash table, or let you make sure all keys that hash to a single
     * bucket's index get stored as they should in your table. You'll need to
     * implement the second method; it depends on how you store entries and
     * handle collisions. This is NOT required, but you may find it helpful when
     * debugging and testing your code.
     */
    //other helpers
    private int indexFor(K key){
        int h = key.hashCode();
        if(h < 0){
            h = -h;
        }
        return h % currentCapacity;
    }
    private void resize(int newCapacity){
        ArrayList<LinkedList<HashNode<K,V>>> old = table;
        currentCapacity = newCapacity;
        table = new ArrayList<LinkedList<HashNode<K,V>>>(newCapacity);
        for (int i = 0; i < newCapacity; i++){
            table.add(new LinkedList<HashNode<K,V>>());
        }
        //rehash all entries
        for (LinkedList<HashNode<K,V>> bucket : old){
            for (HashNode<K,V> node : bucket){
                int idx = indexFor(node.getKey());
                table.get(idx).addFirst(node);
            }
        }
    }
    //to rehash into a prime table size to reduce clustering when using mod
    private int nextPrime(int n) {
        if (n <= 2) return 2;
        if (n % 2 == 0) n++;

        while (!isPrime(n)) {
            n += 2;
        }
        return n;
    }

    private boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public void printHashTable() {
        for (int idx=0; idx < this.currentCapacity; ++idx) {
            System.out.print(idx + ": ");
            printEntriesByIndex(idx);
        }
    }

    private void printEntriesByIndex(int idx) {
        /*
         * To implement this method to help print out one bucket of your hash table, you need to determine:
         * a) If there are no key/value pairs in the bucket idx, print "no entries"
         * b) If there are key/value pairs at that bucket, use a loop to print each one.
         *    Best to use System.out.print() and not println() so they're all on one line.
         * c) At the end of that loop, do System.out.println() to print a new line.
         */
        LinkedList<HashNode<K,V>> bucket = table.get(idx);
        if(bucket.size() == 0){
            System.out.println("no entries");
            return;
        }
        for(HashNode<K,V> node : bucket){
            System.out.print(node.toString() + " ");
        }
        System.out.println();
    }

}

