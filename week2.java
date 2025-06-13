import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

// book class
class Book {
    final UUID bookID;
    final String title;
    final String author;
    final String genre;
    boolean isIssued = false;
    Member issuedTo = null;
    LocalDate dueDate = null;
    Queue<Member> reservationQueue = new LinkedList<>();

    public Book(String title, String author, String genre) {
        this.bookID = UUID.randomUUID();
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s by %s (%s) %s", bookID, title, author, genre, isIssued ? "(Issued)" : "(Available)");
    }
}

// abstract Member class
abstract class Member {
    final UUID memberID;
    final String name;
    final String email;
    final String phone;
    final int maxBooksAllowed;
    final List<Book> currentlyIssuedBooks = new ArrayList<>();

    public Member(String name, String email, String phone, int maxBooksAllowed) {
        this.memberID = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.maxBooksAllowed = maxBooksAllowed;
    }

    abstract int getMaxAllowedDays();
    abstract String getMemberType();

    @Override
    public String toString() {
        return String.format("%s (%s) [%s]", name, getMemberType(), memberID);
    }
}

// studentMember
class StudentMember extends Member {
    public StudentMember(String name, String email, String phone) {
        super(name, email, phone, 3);
    }
    int getMaxAllowedDays() { return 14; }
    String getMemberType() { return "Student"; }
}

// teacherMember
class TeacherMember extends Member {
    public TeacherMember(String name, String email, String phone) {
        super(name, email, phone, 5);
    }
    int getMaxAllowedDays() { return 30; }
    String getMemberType() { return "Teacher"; }
}

// guestMember
class GuestMember extends Member {
    public GuestMember(String name, String email, String phone) {
        super(name, email, phone, 1);
    }
    int getMaxAllowedDays() { return 7; }
    String getMemberType() { return "Guest"; }
}

// librarian
class Librarian extends Member {
    public Librarian(String name, String email, String phone) {
        super(name, email, phone, 100);
    }
    int getMaxAllowedDays() { return 365; }
    String getMemberType() { return "Librarian"; }
}

// library class
class Library {
    private final Map<UUID, Book> books = new HashMap<>();
    private final Map<UUID, Member> members = new HashMap<>();
    private final Set<String> emails = new HashSet<>();
    private final Set<String> phones = new HashSet<>();

    // add book
    public void addBook(Book book) {
        if (books.containsKey(book.bookID)) {
            throw new IllegalArgumentException("Book with this ID already exists.");
        }
        books.put(book.bookID, book);
        System.out.println("Book added: " + book);
    }

    // remove book
    public void removeBook(Book book) {
        if (book.isIssued) {
            System.out.println("Cannot remove: Book is currently issued.");
            return;
        }
        books.remove(book.bookID);
        System.out.println("Book removed: " + book.title);
    }

    // register member
    public void registerMember(Member member) {
        if (emails.contains(member.email) || phones.contains(member.phone)) {
            System.out.println("Member with this email or phone already exists.");
            return;
        }
        members.put(member.memberID, member);
        emails.add(member.email);
        phones.add(member.phone);
        System.out.println("Member registered: " + member);
    }

    // search books
    public List<Book> searchBooks(String keyword) {
        List<Book> result = new ArrayList<>();
        for (Book b : books.values()) {
            if (b.title.toLowerCase().contains(keyword.toLowerCase()) ||
                b.author.toLowerCase().contains(keyword.toLowerCase()) ||
                b.genre.toLowerCase().contains(keyword.toLowerCase())) {
                result.add(b);
            }
        }
        return result;
    }

    // issue book
    public void issueBook(Book book, Member member) {
        if (book.isIssued) {
            System.out.println("Book is already issued.");
            return;
        }
        if (member.currentlyIssuedBooks.size() >= member.maxBooksAllowed) {
            System.out.println("Member has reached the book limit.");
            return;
        }
        book.isIssued = true;
        book.issuedTo = member;
        book.dueDate = LocalDate.now().plusDays(member.getMaxAllowedDays());
        member.currentlyIssuedBooks.add(book);
        System.out.println("Book issued: " + book.title + " to " + member.name + ", due on " + book.dueDate);
    }

    // return book
    public void returnBook(Book book, Member member) {
        if (!book.isIssued || book.issuedTo != member) {
            System.out.println("This member did not issue this book.");
            return;
        }
        book.isIssued = false;
        book.issuedTo = null;
        book.dueDate = null;
        member.currentlyIssuedBooks.remove(book);
        System.out.println("Book returned: " + book.title);

        // assign to next in reservation queue
        if (!book.reservationQueue.isEmpty()) {
            Member next = book.reservationQueue.poll();
            System.out.println("Book reserved for: " + next.name);
            issueBook(book, next);
        }
    }

    // reserve book
    public void reserveBook(Book book, Member member) {
        if (!book.isIssued) {
            System.out.println("Book is available. No need to reserve.");
            return;
        }
        if (book.reservationQueue.contains(member)) {
            System.out.println("Already in reservation queue.");
            return;
        }
        book.reservationQueue.add(member);
        System.out.println("Book reserved: " + book.title + " for " + member.name);
    }

    // view issued books for a member
    public void viewIssuedBooks(Member member) {
        if (member.currentlyIssuedBooks.isEmpty()) {
            System.out.println(member.name + " has no issued books.");
            return;
        }
        System.out.println("Books issued to " + member.name + ":");
        for (Book b : member.currentlyIssuedBooks) {
            long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), b.dueDate);
            System.out.println("  " + b.title + " (Due: " + b.dueDate + ", Days left: " + daysLeft + ")");
        }
    }

    // view overdue books
    public void viewOverdueBooks() {
        boolean found = false;
        for (Book b : books.values()) {
            if (b.isIssued && b.dueDate != null && b.dueDate.isBefore(LocalDate.now())) {
                if (!found) {
                    System.out.println("Overdue books:");
                    found = true;
                }
                System.out.println(b.title + " issued to " + b.issuedTo.name + " (Due: " + b.dueDate + ")");
            }
        }
        if (!found) {
            System.out.println("No overdue books.");
        }
    }

    // get book by title
    public Book getBookByTitle(String title) {
        for (Book b : books.values()) {
            if (b.title.equalsIgnoreCase(title)) return b;
        }
        return null;
    }

    // get member by name
    public Member getMemberByName(String name) {
        for (Member m : members.values()) {
            if (m.name.equalsIgnoreCase(name)) return m;
        }
        return null;
    }
}

public class week2 {
    public static void main(String[] args) {
        Library lib = new Library();

        // register librarian and members
        Librarian librarian = new Librarian("Libby", "libby@library.com", "111-222-3333");
        lib.registerMember(librarian);

        StudentMember student = new StudentMember("Alice", "alice@student.com", "123-456-7890");
        TeacherMember teacher = new TeacherMember("Bob", "bob@teacher.com", "222-333-4444");
        GuestMember guest = new GuestMember("Charlie", "charlie@guest.com", "333-444-5555");
        lib.registerMember(student);
        lib.registerMember(teacher);
        lib.registerMember(guest);

        // add books
        Book javaBook = new Book("Surrounded by Idiots", "Thomas Erikson", "Personal Development");
        Book pythonBook = new Book("Blink: The Power of Thinking Without Thinking", "Malcolm Gladwell", "Self Help");
        Book historyBook = new Book("The Lucifer Effect: Understanding How Good People Turn Evil", "Philip G. Zimbardo", "psychology");
        lib.addBook(javaBook);
        lib.addBook(pythonBook);
        lib.addBook(historyBook);

        // search books
        List<Book> searchResults = lib.searchBooks("Self Help");
        if (!searchResults.isEmpty()) {
            System.out.println("Search results for 'Self Help':");
            for (Book b : searchResults) {
                System.out.println(b);
            }
        } else {
            System.out.println("No books found for 'Self Help'.");
        }

        // issue book
        lib.issueBook(javaBook, student);
        lib.issueBook(pythonBook, teacher);

        // reserve book
        lib.reserveBook(javaBook, teacher);
        lib.reserveBook(javaBook, guest);

        // view issued books
        lib.viewIssuedBooks(student);
        lib.viewIssuedBooks(teacher);

        // return book (should assign to next in queue)
        lib.returnBook(javaBook, student);

        // view issued books after return
        lib.viewIssuedBooks(teacher);

        // view overdue books (simulate overdue)
        javaBook.dueDate = LocalDate.now().minusDays(1); // force overdue for demo
        lib.viewOverdueBooks();
    }
}