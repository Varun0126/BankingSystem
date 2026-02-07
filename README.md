The Simple Banking System is a Java-based application that simulates basic banking operations such as account creation, deposit, withdrawal, and balance inquiry.
The project is implemented using Object-Oriented Programming (OOP) principles and supports both console-based and GUI-based (Swing) interaction.

To maintain data consistency, the system uses file handling to persist account details across sessions.

✨ Features

Create new bank accounts

Deposit money into an account

Withdraw money from an account

Check account balance

Persistent storage using file handling

User-friendly GUI using Java Swing

Input validation and exception handling

🛠️ Technologies Used

Java

Java Swing (GUI)

File Handling (I/O)

Collections Framework (List, HashMap)

Object-Oriented Programming (OOP)

📂 Project Structure
BankingSystem/
 ├── BankAccount.java     // Model class
 ├── BankApp.java         // Console-based application
 ├── BankingGUI.java      // GUI-based application
 └── accounts.txt         // Persistent data storage

⚙️ How It Works

Account details are stored in accounts.txt in the format:

accountNumber,name,balance


On application startup, all existing accounts are loaded from the file.

Any create, deposit, or withdrawal operation automatically updates the file.

Console and GUI applications share the same data file.

▶️ How to Run
1️⃣ Console Application
javac BankingSystem/*.java
java BankingSystem.BankApp

2️⃣ GUI Application
javac BankingSystem/*.java
java BankingSystem.BankingGUI

🧪 Sample Operations

Create Account with unique account number

Deposit a positive amount

Withdraw amount (only if sufficient balance exists)

View real-time balance updates

🔒 Validations Implemented

Prevents duplicate account numbers

Prevents negative deposit or withdrawal

Handles invalid input gracefully

Avoids runtime exceptions (e.g., NullPointerException)

🚀 Future Enhancements

Integrate database using JDBC (MySQL)

Add user authentication (PIN / Login system)

Implement transaction history

Apply MVC architecture

Improve UI design

🎯 Learning Outcomes

Hands-on experience with Java OOP concepts

Practical usage of file handling

Understanding event-driven programming with Swing

Real-world simulation of banking workflows

👨‍💻 Author

Varun Marathe
B.Tech – Computer Engineering
