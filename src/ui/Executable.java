package ui;

import java.util.Scanner;
import model.Controller;

public class Executable {
    
    private Scanner reader;
    private Controller readXSystem;

    public Executable() {

        reader = new Scanner(System.in);
        readXSystem = new Controller();
    }

    public static void main(String[] args) {

        Executable exe = new Executable();
        exe.menu();
    }

    public void menu() {

        System.out.println("Welcome to ReadX");

        boolean flag = false;

        while (!flag) {
            System.out.println("Menu Principal");
            System.out.println("1) Register user");
            System.out.println("2) Manage bibliographic products");
            System.out.println("3) Purchase book");
            System.out.println("4) Subscribe to magazine");
            System.out.println("5) Cancel subcription");
            System.out.println("6) Access to user librery");
            System.out.println("7) Generate real-time reports");
            System.out.println("0) Exit");
            int option = reader.nextInt();

            switch (option) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    manageBibliographicProducts();
                    break;
                case 3:
                    purchaseBook();
                    break;
                case 4:
                    subscribeToMagazine();
                    break;
                case 5:
                    cancelMagazineSubscription();
                    break;
                case 6:
                    showUserCollection();
                    break;
                case 7:
                    reports();
                    break;
                case 0:
                    flag = true;
                    break;
            }
        }
    }

    public void registerUser() {

        reader.nextLine();

        System.out.println("Enter the name");
        String name = reader.nextLine();

        System.out.println("Enter the identification");
        String id = reader.nextLine();

        int selection = 0;

        do {
            System.out.println("Select the user type: \n1) Regular user \n2) Premium user");
            selection = reader.nextInt();

            if (selection < 1 || selection > 2) {
                System.out.println("Error. Enter a valid number");
            }
        } while(selection < 1 || selection > 2);

        if (readXSystem.registerUser(selection, name, id)) {
            System.out.println("User registered correctly");
        } else {
            System.out.println("Full memory. User was not registered. ");
        }
    }

    public void manageBibliographicProducts(){

        int selection = 0;
        int selection1 = 0;
        String id = "";
        String review = "";
        int selection2 = 0;
        double price = 0.0;
        int selection3 = 0;
        String query = "";
        boolean flag = false;

        do {
            System.out.println("1) Register \n2) Delete \n3) Modify \n4) Go back");
            selection1 = reader.nextInt();

            switch (selection1) {
                case 1:
                    do {
                        System.out.println("Select the product type: \n1) Book \n2) Magazine");
                        selection = reader.nextInt();
            
                        if (selection < 1 || selection > 2) {
                            System.out.println("Error. Enter a valid number");
                        }
                    } while(selection < 1 || selection > 2);
            
                    reader.nextLine();
            
                    do {
                        System.out.println("Enter the identification (3 hex characters: 4AF)");
                        id = reader.nextLine();
            
                        if(id.length() != 3 || !id.matches("[0-9A-Fa-f]{3}")) {
                            System.out.println("Error. A 3 hex identification must be provided");
                        }
                    } while(id.length() != 3 || !id.matches("[0-9A-Fa-f]{3}"));
            
                    System.out.println("Enter the name");
                    String name = reader.nextLine();
            
                    System.out.println("Enter the number of pages");
                    int pages = reader.nextInt();
            
                    reader.nextLine();
            
                    if (selection == 1) {
                        System.out.println("Enter the book review");
                        review = reader.nextLine();
                    }
                    
                    System.out.println("Provide the publication date:");
                    System.out.println("Enter the day:");
                    int day = reader.nextInt();
            
                    System.out.println("Enter the month");
                    int month = reader.nextInt();
            
                    System.out.println("Enter the year");
                    int year = reader.nextInt();
            
                    if (selection == 1) {
                        System.out.println("Select the book genre: \n1) Sci-fi \n2) Fantasy \n3) Historical novel");
                        selection2 = reader.nextInt();
                    }
            
                    if (selection == 2) {
                        System.out.println("Select the magazine category: \n1) Varieties \n2) Design \n3) Scientific");
                        selection2 = reader.nextInt();
                    }
            
                    reader.nextLine();
            
                    System.out.println("Enter the front page URL");
                    String url = reader.nextLine();
            
                    switch (selection) {
                        case 1: 
                            System.out.println("Enter the book sell price (use comma)");
                            break;
                        case 2:
                            System.out.println("Enter the magazine subscription price (use comma)");
                            break;
                    }
            
                    price = reader.nextDouble();
            
                    if (selection == 2) {
                        do{
                            System.out.println("Select the emision periodicity: \n1) Daily \n2) Monthly \n3) Anually");
                            selection3 = reader.nextInt();
            
                            if (selection3 < 1 || selection3 > 2) {
                                System.out.println("Error. Provide a valid number");
                            }
                        } while (selection3 < 1 || selection3 > 2);
                    }
            
                    if (readXSystem.registerBibliographicProduct(selection, id, name, pages, review, day, month, year, selection2, url, price, selection3)) {
                        System.out.println("Bibliographical product registered correctly");
                    } else {
                        System.out.println("Error. Bibliographical product was not registered");
                    }
                    break;
                case 2:
                    query = readXSystem.getProductList();

                    if (query.equals("")) {
                        System.out.println("No registered products");
                    } else {
                        System.out.println("List of bibliographical products:");
                        System.out.println(query);
            
                        System.out.println("Select the position of the product to delete");
                        int position = reader.nextInt();
            
                        if (readXSystem.deleteBibliographicalProduct(position - 1)) {
                            System.out.println("Bibliographical product correctly deleted");
                        } else {
                            System.out.println("Error! Bibliographical product was not deleted");
                        }
                    }   
                    break;
                case 3:
                    query = readXSystem.getProductList();
                    int position = 0;
                    int option = 0;
                    String newValue = "";
            
                    if (query.equals("")) {
                        System.out.println("No registered products");
                    } else {
                        System.out.println("List of bibliographical products: ");
                        System.out.println(query);
            
                        System.out.println("Select the position of the product to modify");
                        position = reader.nextInt();
            
                        switch (readXSystem.getBibliographicProductType(position - 1)) {
                            case 1:
                                do {
                                    System.out.println("Select the attribute to change: \n1) Id (HEX code) \n2) Name \n3) Number of pages \n4) Review \n5) Publication date (dd-mm-yyyy) \n6) Genre (1-3) \n7) Front page URL \n8) Sell price (use dot)");
                                    option = reader.nextInt();
            
                                    if (option < 1 || option > 8) {
                                        System.out.println("Error! Provide a valid number");
                                    }
                                } while (option < 1 || option > 8);
                                break;
                            case 2:
                            do {
                                System.out.println("Select the attribute to change: \n1) Id (HEX code) \n2) Name \n3) Number of pages \n4) Publication date (dd-mm-yyy) \n5) Category (1-3) \n6) Front page URL \n7) Subscription price (use dot) \n8) Periodicity of emitions (1-3)");
                                option = reader.nextInt();
            
                                if (option < 1 || option > 8) {
                                    System.out.println("Error! Provide a valid number");
                                }
                            } while (option < 1 || option > 8);
                            break;
                        }
            
                        reader.nextLine();
            
                        System.out.println("Enter the new value");
                        newValue = reader.nextLine();
            
                        if (readXSystem.modifyBibliographicalProduct(position - 1, option, newValue)) {
                            System.out.println("Bibliographic product correctly modified");
                        } else {
                            System.out.println("Error! Bibliographic product was not modified");
                        }
                    }  
                    break;
                case 4:
                    flag = true;
                    break;
                default:
                    System.out.println("Error! Provide a valid number");
                    break;
            }
        } while (!flag);
    }

    public void purchaseBook() {

        String userQuery = readXSystem.getUserList();
        String bookQuery = readXSystem.getBookList();

        if (userQuery.equals("")) {
            System.out.println("No registered users");
        } else {
            System.out.println("User list: ");
            System.out.println(userQuery);

            System.out.println("Select a user");
            int user = reader.nextInt();

            if (bookQuery.equals("")) {
                System.out.println("No registered products");
            } else {
                System.out.println("Book list: ");
                System.out.println(bookQuery);

                String msg = "";

                do {
                    System.out.println("Select the book you want to purchase");
                    int book = reader.nextInt();

                    System.out.println(readXSystem.purchaseABook(user-1, book-1));
                    msg = readXSystem.purchaseABook(user-1, book-1);
                } while (msg.equals("Select a valid position"));
            }
        }
    }

    public void subscribeToMagazine() {

        String userQuery = readXSystem.getUserList();
        String magazineQuery = readXSystem.getMagazineList();

        if (userQuery.equals("")) {
            System.out.println("No registered users");
        } else {
            System.out.println("User list: ");
            System.out.println(userQuery);

            System.out.println("Select a user");
            int user = reader.nextInt();

            if (magazineQuery.equals("")) {
                System.out.println("No registered products");
            } else {
                System.out.println("Magazine list: ");
                System.out.println(magazineQuery);

                String msg = "";

                do {
                    System.out.println("Select the magazine you want to subscribe to");
                    int book = reader.nextInt();

                    System.out.println(readXSystem.subscribeToMagazine(user-1, book-1));
                    msg = readXSystem.subscribeToMagazine(user-1, book-1);
                } while (msg.equals("Select a valid position"));
            }
        }
    }

    public void showUserCollection() {

        reader.nextLine();
        String userQuery = readXSystem.getUserList();

        if (userQuery.equals("")) {
            System.out.println("No registered users");
        } else {
            System.out.println("User list: ");
            System.out.println(userQuery);

            System.out.println("Select a user");
            int user = reader.nextInt();
            int page = 0;
            boolean flag = false;

            while (!flag) {

                System.out.println(readXSystem.showUserCollection(user-1, page));

                System.out.println("Enter R to start a reading session");
                System.out.println("Enter A to go to the next page");
                System.out.println("Enter S to go to the previous page");
                System.out.println("Enter E to go to leave"); 
                String selection = reader.nextLine();

                if (selection.toUpperCase().equals("R")) {
                    System.out.println("Type the [x,y] coordinate or the corresponding code of the bibliographic product:");
                    System.out.println("Enter x coordinate:");
                    int x = reader.nextInt();
                    System.out.println("Enter y coordinate:");
                    int y = reader.nextInt();

                    boolean flag1 = false;
                    int page1 = 0;

                    while (!flag1) {
                        System.out.println(readXSystem.readingSession(user-1, page, page1, x, y));

                        System.out.println("Enter A to go to the next page");
                        System.out.println("Enter S to go to the previous page");
                        System.out.println("Enter E to go back to library"); 
                        String selection1 = reader.nextLine();

                        if (selection1.toUpperCase().equals("A")) {
                            page1++;
                        } else if (selection1.toUpperCase().equals("S")) {
                            if (page1 == 0) {
                                System.out.println("There is no previous page");
                            } else {
                                page1--;
                            }
                        } else if (selection1.toUpperCase().equals("E")) {
                            flag1 = true;
                        } else {
                            System.out.println("Enter a valid character");
                        }
                    }

                    
                } else if (selection.toUpperCase().equals("A")) {
                    page++;
                } else if (selection.toUpperCase().equals("S")) {
                    if (page == 0) {
                        System.out.println("There is no previous page");
                    } else {
                        page--;
                    }
                } else if (selection.toUpperCase().equals("E")) {
                    flag = true;
                } else {
                    System.out.println("Enter a valid character");
                }
            }
        }
    }

    public void cancelMagazineSubscription() {

        String userQuery = readXSystem.getUserList();
        
        if (userQuery.equals("")) {
            System.out.println("No registered users");
        } else {

            System.out.print(userQuery);
            System.out.println("Select a user");
            int user = reader.nextInt();

            String magazineQuery = readXSystem.getUserMagazineSubscriptionList(user - 1);
            
            if (magazineQuery.equals("")) {
                System.out.println("No active subscriptions");
            } else {

                System.out.print(magazineQuery);
                System.out.println("Select a magazine");
                int magazine = reader.nextInt();

                if (readXSystem.cancelMagazineSubscription(user-1, magazine-1)) {
                    System.out.println("Subscription cancelled");
                } else {
                    System.out.println("Error! Subscription was not cancelled");
                }
            }
        }
    }

    public void reports() {

        System.out.println("System Reports\n");
        System.out.println("Read pages of books " + readXSystem.getBookRedPages());
        System.out.println("Read pages of magazines " + readXSystem.getMagazineRedPages());
        System.out.println("Most read genre: " + readXSystem.getMostReadGenre());
        System.out.println("Most read category " + readXSystem.getMostReadCategory());

    }
}