//Daniel Paek jff7dm
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
public class LabArrayList {
    public static <T> T largest(ArrayList<T> list, Comparator<T> comp) {
        T best = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (comp.compare(list.get(i), best) > 0) {
            best = list.get(i);
        }
    }
    return best;
    }
    public static void main(String[] args) {
        ArrayList<Integer> ints = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();
        ArrayList<Book> books = new ArrayList<>();

        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            ints.add(r.nextInt(1000)); // 0..999
        }

        strings.add("UVA");
        strings.add("VT");
        strings.add("Rutgers");
        strings.add("Princeton");
        strings.add("Harvard");
        strings.add("NYU");
        strings.add("Cornell");
        strings.add("BU");
        strings.add("GTech");
        strings.add("UConn");

        // 2c) Put at least 5 Books
        books.add(new Book("Dune", "Frank", "Herbert", 412));
        books.add(new Book("The Hobbit", "J.R.R.", "Tolkien", 310));
        books.add(new Book("Pride and Prejudice", "Jane", "Austen", 279));
        books.add(new Book("The Martian", "Andy", "Weir", 369));
        books.add(new Book("1984", "George", "Orwell", 328));

        // 3) Print each ArrayList
        System.out.println("Integers");
        System.out.println(ints);

        System.out.println("Strings");
        System.out.println(strings);

        System.out.println("Books");
        System.out.println(books);

        // 4) Experiment with ArrayList methods

        // 4a) add(value) and add(index, value)
        ints.add(5000);
        ints.add(0, -10);

        // 4b) contains
        System.out.println("ints contains 5000? " + ints.contains(5000));
        System.out.println("strings contains \"UVA\"? " + strings.contains("UVA"));

        // 4c) get / set
        int old0 = ints.get(0);
        ints.set(0, 9999);
        System.out.println("ints get(0) old=" + old0 + ", new=" + ints.get(0));

        // 4d) indexOf
        System.out.println("indexOf \"VT\" in strings: " + strings.indexOf("VT"));

        // 4e) remove(index) and remove(object)
        Integer removedByObject = 5000; // must be Integer object to call remove(Object)
        boolean didRemove = ints.remove(removedByObject);
        System.out.println("Removed 5000 by object? " + didRemove);

        int removedIndexVal = ints.remove(0); // remove by index returns the removed value
        System.out.println("Removed index 0 value: " + removedIndexVal);

        // 5) Generic largest method
        System.out.println("Largest values");
        System.out.println("Largest Integer: " + largest(ints, Comparator.naturalOrder()));
        System.out.println("Largest String: " + largest(strings, Comparator.naturalOrder()));
        Book biggestAuthor = largest(books, new Comparator<Book>() {
            public int compare(Book a, Book b) {
                return a.getAuthorLast().compareToIgnoreCase(b.getAuthorLast());
            }
        });

        // 6) Sort using built-in sort
        Collections.sort(ints);
        Collections.sort(strings);
        Collections.sort(books); // uses Book.compareTo (author last name)

        System.out.println("After Collections.sort()");
        System.out.println("Integers sorted: " + ints);
        System.out.println("Strings sorted: " + strings);
        System.out.println("Books sorted by author last name: " + books);

        // 7) Sort Books by pages using Comparator
        Comparator<Book> byPages = new Comparator<Book>() {
            public int compare(Book a, Book b) {
                return a.getPages() - b.getPages(); // ascending by pages
            }
        };

        Collections.sort(books, byPages);

        System.out.println("Books sorted by pages");
        System.out.println(books);
    }
}
