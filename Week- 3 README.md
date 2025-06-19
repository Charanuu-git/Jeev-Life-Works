Student Management System
=========================

Overview:
---------
This is a simple console-based Student Management System written in Java. It lets you add, update, remove, search, and display student records. The system uses Object-Oriented Programming principles and Java Collections to manage data efficiently.

It also saves and loads data using file handling, so the student list is saved between sessions.

Key Features:
-------------
- Add a new student (with unique ID)
- Remove a student by ID
- Update student details
- Search for a student by ID
- Display all students sorted by ID
- Save data to a file (students.dat) on exit
- Load data automatically on startup

Technologies Used:
------------------
- Java SE (Standard Edition)
- OOP (Object-Oriented Programming)
- Collections: ArrayList, HashMap, TreeSet
- File I/O with Object Streams
- Basic Exception Handling and Input Validation

File Descriptions:
------------------
1. **Student.java**  
   - Defines the Student class with fields like ID, name, age, grade, and address  
   - Implements Serializable and Comparable

2. **StudentManager.java**  
   - Manages student records using ArrayList, HashMap, and TreeSet  
   - Handles all CRUD operations and file saving/loading

3. **Main.java**  
   - The main program with a menu system  
   - Uses Scanner for input and calls methods from StudentManager

4. **students.dat** (auto-created)  
   - Stores all student records so they're available the next time you run the program

How to Run:
-----------
1. Open a terminal in the project folder
2. Compile the code
3. Run the program in main.java

Notes:
------
- Make sure student IDs are unique
- Age must be a positive number
- Name and address fields should not be empty
- The list of students is always shown in sorted order by ID

Created by:
-----------
Charan Sai. T
Contact: 9392719956
Date: [19th June, 2025]
