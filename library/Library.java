import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

class Book {
    private String title;
    private String author;
    private int count;

    public Book(String title, String author, int count) {
        this.title = title;
        this.author = author;
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void displayBookDetails() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Available Copies: " + count);
    }
}

class Customer {
    private String name;
    private int adharNo;
    private long customerId;
    private Book borrowedBook;
    int count = 0;

    public Customer(String name, int adharNo) {
        this.name = name;
        this.adharNo = adharNo;
        Random random = new Random();
        this.customerId = 100000 + random.nextInt(900000);
        this.borrowedBook = null;
        System.out.println("Customer created successfully");
        System.out.println("Here is your customer ID: " + customerId);
    }

    public String getName() {
        return name;
    }

    public long getCustomerId() {
        return customerId;
    }

    public Book getBorrowedBook() {
        return borrowedBook;
    }

    public void borrowBook(Book book) {
        if (borrowedBook != null) {
            System.out.println("You have already borrowed a book. Please return it first.");
        } else {
            if (book.getCount() > 0) {
                borrowedBook = book;
                book.setCount(book.getCount() - 1);
                count++;
                System.out.println("Book borrowed successfully");
            } else {
                System.out.println("Book is out of stock");
            }
        }
    }

    public void returnBook() {
        if (borrowedBook != null) {
            borrowedBook.setCount(borrowedBook.getCount() + 1);
            System.out.println("Book returned successfully");
            borrowedBook = null;
        } else {
            System.out.println("You have no borrowed book to return.");
        }
    }

    public void displayCustomerDetails() {
        System.out.println("Name: " + name);
        System.out.println("Aadhar No: " + adharNo);
        System.out.println("Customer ID: " + customerId);
        if (borrowedBook != null) {
            System.out.println("Book taken: " + borrowedBook.getTitle());
        } else {
            System.out.println("No books taken");
        }
    }
}

public class Library {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Book> books = new ArrayList<>();
        int choice;

        do {
            System.out.println("\nLibrary Management System");
            System.out.println("Enter 1 for Book Interface");
            System.out.println("Enter 2 for Customer Interface");
            System.out.println("Enter 3 to Exit");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    bookInterface(sc, books);
                    break;
                case 2:
                    customerInterface(sc, customers, books);
                    break;
                case 3:
                    System.out.println("Exiting... Thank you for using the Library Management System!");
                    break;
                default:
                    System.out.println("Invalid choice, please try again");
            }
        } while (choice != 3);

        sc.close();
    }

    public static void bookInterface(Scanner sc, ArrayList<Book> books) {
        int choice;
        do {
            System.out.println("\nBook Interface");
            System.out.println("Enter 1 to Add a New Book");
            System.out.println("Enter 2 to Display Book Details");
            System.out.println("Enter 3 to Return to Main Menu");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter book details:");
                    System.out.print("Title: ");
                    String title = sc.next();
                    sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();
                    System.out.print("Count: ");
                    int count = sc.nextInt();
                    books.add(new Book(title, author, count));
                    break;

                case 2:
                    System.out.println("Enter book title to display details:");
                    String bookTitle = sc.next();
                    Book currentBook = null;
                    for (Book book : books) {
                        if (book.getTitle().equalsIgnoreCase(bookTitle)) {
                            currentBook = book;
                            break;
                        }
                    }
                    if (currentBook != null) {
                        currentBook.displayBookDetails();
                    } else {
                        System.out.println("Book not found");
                    }
                    break;

                case 3:
                    System.out.println("Returning to Main Menu...");
                    break;

                default:
                    System.out.println("Invalid choice, please try again");
            }
        } while (choice != 3);
    }

    public static void customerInterface(Scanner sc, ArrayList<Customer> customers, ArrayList<Book> books) {
        int choice;
        do {
            System.out.println("\nCustomer Interface");
            System.out.println("Enter 1 to Add a New Customer");
            System.out.println("Enter 2 to Borrow a Book");
            System.out.println("Enter 3 to Return a Book");
            System.out.println("Enter 4 to Display Customer Details");
            System.out.println("Enter 5 to Return to Main Menu");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter customer details:");
                    System.out.print("Name: ");
                    String name = sc.next();
                    System.out.print("Aadhar No: ");
                    int adharNo = sc.nextInt();
                    sc.nextLine();
                    customers.add(new Customer(name, adharNo));
                    break;

                case 2:
                    System.out.println("Enter customer ID to borrow a book:");
                    long customerId = sc.nextLong();
                    Customer currentCustomer = null;
                    for (Customer customer : customers) {
                        if (customer.getCustomerId() == customerId) {
                            currentCustomer = customer;
                            break;
                        }
                    }
                    if (currentCustomer != null) {
                        System.out.println("Enter book title to borrow:");
                        String bookTitle = sc.next();
                        Book currentBook = null;
                        for (Book book : books) {
                            if (book.getTitle().equalsIgnoreCase(bookTitle)) {
                                currentBook = book;
                                break;
                            }
                        }
                        if (currentBook != null) {
                            currentCustomer.borrowBook(currentBook);
                        } else {
                            System.out.println("Book not found");
                        }
                    } else {
                        System.out.println("Customer not found");
                    }
                    break;

                case 3:
                    System.out.println("Enter customer ID to return a book:");
                    customerId = sc.nextLong();
                    currentCustomer = null;
                    for (Customer customer : customers) {
                        if (customer.getCustomerId() == customerId) {
                            currentCustomer = customer;
                            break;
                        }
                    }
                    if (currentCustomer != null) {
                        currentCustomer.returnBook();
                    } else {
                        System.out.println("Customer not found");
                    }
                    break;

                case 4:
                    System.out.println("Enter customer ID to display details:");
                    customerId = sc.nextLong();
                    currentCustomer = null;
                    for (Customer customer : customers) {
                        if (customer.getCustomerId() == customerId) {
                            currentCustomer = customer;
                            break;
                        }
                    }
                    if (currentCustomer != null) {
                        currentCustomer.displayCustomerDetails();
                    } else {
                        System.out.println("Customer not found");
                    }
                    break;

                case 5:
                    System.out.println("Returning to Main Menu...");
                    break;

                default:
                    System.out.println("Invalid choice, please try again");
            }
        } while (choice != 5);
    }
}
