import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

class atminterface {
    Scanner sc = new Scanner(System.in);
    String name;
    String dob;
    String panno;
    String adrs;
    int adno;
    long accno;
    long balance;

    public atminterface(String name, String dob, String panno, String adrs, int adno) {
        this.name = name;
        this.dob = dob;
        this.panno = panno;
        this.adrs = adrs;
        this.adno = adno;
        System.out.println("Account created successfully\n");
        Random random = new Random();
        accno = (long) (1000000000L + random.nextDouble() * 9000000000L);
        System.out.println("Here is your account number: " + accno + "\nYou can access your account using this account number. Thank you!\n");
        System.out.println("Enter initial deposit amount:");
        balance = sc.nextLong();
    }

    void debitamt(long account) {
        if (this.accno == account) {
            System.out.println("Enter amount to debit:");
            int amt = sc.nextInt();
            if (balance > amt) {
                balance -= amt;
                System.out.println("Amount debited successfully\n");
            } else {
                System.out.println("Insufficient bank balance\n");
            }
        } else {
            System.out.println("Incorrect account number\n");
        }
    }

    void creditamt(long account) {
        if (this.accno == account) {
            System.out.println("Enter amount to credit:");
            int amt = sc.nextInt();
            balance += amt;
            System.out.println("Amount credited successfully\n");
        } else {
            System.out.println("Incorrect account number\n");
        }
    }

    void checkbalance(long account) {
        if (this.accno == account) {
            System.out.println("Available balance: " + balance +" \n");
        } else {
            System.out.println("Incorrect account number\n");
        }
    }

    void userdetails() {
        System.out.println("Name: " + name);
        System.out.println("DOB: " + dob);
        System.out.println("Aadhaar no: " + adno);
        System.out.println("PAN card no: " + panno);
        System.out.println("Address: " + adrs +"\n");
    }
}

public class Atm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Home page 'Welcome to ATM interface'");
        ArrayList<atminterface> users = new ArrayList<>();
        int choice;

        do {
            System.out.println("\nEnter 1 to create a bank account");
            System.out.println("Enter 2 for other transactions");
            System.out.println("Enter 3 to exit\n");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter your details:");
                    System.out.print("Name: ");
                    String name = sc.next();
                    System.out.print("DOB (dd-mm-yyyy): ");
                    String dob = sc.next();
                    System.out.print("Aadhaar no: ");
                    int adno = sc.nextInt();
                    System.out.print("PAN card no: ");
                    String panno = sc.next();
                    sc.nextLine(); // Consume newline
                    System.out.print("Address: ");
                    String adrs = sc.nextLine();
                    users.add(new atminterface(name, dob, panno, adrs, adno));
                    break;

                case 2:
                    System.out.println("Enter account number to access your account:");
                    long account = sc.nextLong();
                    atminterface currentUser = null;
                    for (atminterface user : users) {
                        if (user.accno == account) {
                            currentUser = user;
                            break;
                        }
                    }
                    if (currentUser != null) {
                        int transactionChoice;
                        do {
                            System.out.println("welcome "+currentUser.name);
                            System.out.println("Enter 1 for debit");
                            System.out.println("Enter 2 for credit");
                            System.out.println("Enter 3 to check balance");
                            System.out.println("Enter 4 get your details");
                            System.out.println("Enter 5 to exit transactions\n");
                            transactionChoice = sc.nextInt();
                            switch (transactionChoice) {
                                case 1:
                                    currentUser.debitamt(account);
                                    break;
                                case 2:
                                    currentUser.creditamt(account);
                                    break;
                                case 3:
                                    currentUser.checkbalance(account);
                                    break;
                                case 4:
                                    currentUser.userdetails();
                                    break;
                                case 5:
                                    System.out.println("Exiting transactions...");
                                    break;
                                default:
                                    System.out.println("Invalid choice");
                            }
                        } while (transactionChoice != 5);
                    } else {
                        System.out.println("Account not found");
                    }
                    break;

                case 3:
                    System.out.println("Exiting... Thank you for using our ATM interface!\n");
                    break;

                default:
                    System.out.println("Invalid choice, please try again");
            }
        } while (choice != 3);

        sc.close();
    }
}
