📚 Library Management System – Java OOP Project
🎯 Objective
A console-based Java application that applies core OOP principles (abstraction, inheritance, encapsulation, polymorphism), along with exception handling, basic concurrency, and modular design, to simulate a real-world Library Management System.

🧩 Class Overview
Book: Holds details like ID, title, author, genre, issue status, due date, and a reservation queue.

Member (abstract): Base class with shared attributes; extended by:

StudentMember: Max 3 books for 14 days
TeacherMember: Max 5 books for 30 days
GuestMember: Max 1 book for 7 days
Librarian (extends Member): Can add/remove books, register members, view and override book returns.

🔨 Key Features
issueBook, returnBook, reserveBook: Handles borrowing logic, limits, and queueing.
addBook, removeBook: Librarian-only actions with duplication and issue checks.
searchBooks: Find by title, author, or genre.
viewIssuedBooks, viewOverdueBooks: Track borrowed/overdue books.
registerMember: Ensures uniqueness via email/phone.
🧪 Additional Elements
Exception Handling: For duplicates, over-limit issues, invalid operations.
Persistence Simulation: Use of in-memory collections or optional file I/O.
Concurrency: Reservation queue can be made thread-safe using synchronized structures.
