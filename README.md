#Real-Time Ticketing System - CLI

Introduction
The Real-Time Ticketing System is an application that simulates the interactions between vendors and customers in a ticket pool. The system includes both a Command-Line Interface (CLI) and a Graphical User Interface (GUI) for configuration and management. This CLI system allows simulated vendors and customers to add or purchase tickets in a thread-safe environment with synchronization.

Setup Instructions

Prerequisites
Java Development Kit (JDK): Version 17 or higher.
Intellij: To manage and build the Java project.
An IDE or Editor: Such as IntelliJ IDEA, Eclipse, or VS Code (optional).

Usage Instructions

Configuring the System
When you run the application, it will ask if you’d like to load an existing configuration file where the previous configuration you entered were saved. Type yes or no:

Yes: It will load the saved configuration values.
No: You’ll be prompted to manually enter:
    Total Tickets
    Ticket Release Rate
    Customer Retrieval Rate
    Maximum Ticket Capacity
    The configuration will be saved to a JSON file for future use.

Starting the Simulation
Enter start to begin the simulation. You'll be prompted to specify:
  Number of Vendors
  Number of Customers
Stopping the Simulation
Enter stop to terminate the simulation gracefully.


Troubleshooting
The CLI doesn’t run?

Ensure you’ve compiled the code correctly with the correct Java version and have Java installed.
Verify that the compiled .class files are in the out folder.

Invalid Input Issues

Ensure all inputs are numbers and meet the validation criteria shown during input prompts.
Logs

Check System.log and ticketpool.log for error details or system activity.

