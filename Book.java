//Daniel Paek jff7dm
public class Book implements Comparable<Book> {
    private String title;
    private String authorFirst;
    private String authorLast;
    private int pages;
    public Book(String title, String authorFirst, String authorLast, int pages) {
        this.title = title;
        this.authorFirst = authorFirst;
        this.authorLast = authorLast;
        this.pages = pages;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthorFirst() {
        return authorFirst;
    }
    public String getAuthorLast() {
        return authorLast;
    }
    public int getPages() {
        return pages;
    }
    public int compareTo(Book other) {
        int c = this.authorLast.compareToIgnoreCase(other.authorLast);
        if (c != 0) return c;

        c = this.authorFirst.compareToIgnoreCase(other.authorFirst);
        if (c != 0) return c;

        return this.title.compareToIgnoreCase(other.title);
    }
    public String toString() {
        return "\"" + title + "\" by " + authorFirst + " " + authorLast + " (" + pages + " pages)";
    }
}


