package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

public class Controller {
    
    private ArrayList<User> users;
    private ArrayList<BibliographicProduct> products;
    private ArrayList<Transaction> bills;

    public Controller() {

        users = new ArrayList<>();
        products = new ArrayList<>();
        bills = new ArrayList<>();
        testCases();
    }

    public void testCases() {

        users.add(new RegularUser("1126178069", "Beatrice", Calendar.getInstance()));
        users.add(new RegularUser("1102021020", "Monica", Calendar.getInstance()));
        users.add(new PremiumUser("1224178035", "Beatriz", Calendar.getInstance()));
        users.add(new PremiumUser("1109920092", "Jorge", Calendar.getInstance()));

        products.add(new Book("4AF", "El Diario de Anna Frank", 320, "Muy bueno", new GregorianCalendar(1996, 9, 24), BookGenre.HISTORICAL_NOVEL, "www.annita.com", 100));
        products.add(new Book("3AF", "Las Aventuras de Tom Sawyer", 425, "Lo mejor", new GregorianCalendar(2004, 11, 4), BookGenre.FANTASY, "www.sawyer.com", 44));
        products.add(new Magazine("5FF", "Casanobba", 22, new GregorianCalendar(20, 5, 23), MagazineCategory.DESIGN, "www.Casanobba.com", 4, MagazineEmissionPeriodicity.MONTHLY));
        products.add(new Magazine("1F4", "El Pepe", 31, new GregorianCalendar(2004, 3, 12), MagazineCategory.SCIENTIFIC, "www.nowoooa.io", 9, MagazineEmissionPeriodicity.DAILY));
    }

    public boolean registerUser(int userType, String name, String id) {

        Calendar signUp = Calendar.getInstance();

        switch (userType) {
            case 1:
                User newRegularUser = new RegularUser(name, id, signUp);
                users.add(newRegularUser);
                return true;
            case 2: 
                User newPremiumUser = new PremiumUser(name, id, signUp);
                users.add(newPremiumUser);
                return true;
        }

        return false;
    }

    public String getUserList() {

        String msg = "";

        for (int i = 0; i < users.size(); i++) {

            msg += "\n" + (i + 1) + ") " + users.get(i).getId() + " - " + users.get(i).getName();
        }

        return msg;
    }

    public boolean registerBibliographicProduct(int productType, String id, String name, int pages, String review, int day, int month, int year, int selection, String url, double price, int periodicity) {

        Calendar newCalendar = new GregorianCalendar(year, month, day);

        switch (productType) {
            case 1: 
                BookGenre genre = BookGenre.DEFAULT;

                switch (selection) {
                    case 1: 
                        genre = BookGenre.SCI_FI;
                        break;
                    case 2:  
                        genre = BookGenre.FANTASY;
                        break;
                    case 3:     
                        genre = BookGenre.HISTORICAL_NOVEL;
                        break;
                }

                BibliographicProduct newBook = new Book(id, name, pages, review, newCalendar, genre, url, price);
                products.add(newBook);
                return true;
            case 2:
                MagazineCategory category = MagazineCategory.DEFAULT;

                switch (selection) {
                    case 1: 
                        category = MagazineCategory.VARIETIES;
                        break;
                    case 2:  
                        category = MagazineCategory.DESIGN;
                        break;
                    case 3:     
                        category = MagazineCategory.SCIENTIFIC;
                        break;
                }

                MagazineEmissionPeriodicity newPeriodicity = MagazineEmissionPeriodicity.DEFAULT;

                switch (periodicity) {
                    case 1: 
                        newPeriodicity = MagazineEmissionPeriodicity.DAILY;
                        break;
                    case 2:  
                        newPeriodicity = MagazineEmissionPeriodicity.MONTHLY;
                        break;
                    case 3:     
                        newPeriodicity = MagazineEmissionPeriodicity.ANUALLY;
                        break;
                }

                BibliographicProduct newMagazine = new Magazine(id, name, pages, newCalendar, category, url, price, newPeriodicity);
                products.add(newMagazine);
                return true;
        }
        return false;
    }

    public String getProductList() {

        String msg = "";
        String type = "";

        for (int i = 0; i < products.size(); i++) {

            if (products.get(i) instanceof Book) {
                type = "Book";
            } else if (products.get(i) instanceof Magazine) {
                type = "Magazine";
            }

            msg += "\n" + (i + 1) + ") " + products.get(i).getId() + " - " + products.get(i).getName() + " (" + type + ")";
        }

        return msg;
    }

    public boolean deleteBibliographicalProduct(int position) {

        products.remove(position);

        return true;
    }

    public int getBibliographicProductType(int position) {

        int type = 0;

        if (products.get(position) instanceof Book) {
            type = 1;
        } 

        if (products.get(position) instanceof Magazine) {
            type = 2;
        }

        return type;
    }

    public boolean modifyBibliographicalProduct(int productPosition, int valueToChange, String newValue) {

        if (products.get(productPosition) instanceof Book){

            switch (valueToChange) {
                case 1:
                    products.get(productPosition).setId(newValue);
                    return true;
                case 2:
                    products.get(productPosition).setName(newValue);
                    return true;
                case 3:
                    int pages = Integer.parseInt(newValue);

                    products.get(productPosition).setTotalPages(pages);
                    return true;
                case 4:
                    Book product = (Book) products.get(productPosition);
                    product.setReview(newValue);
                    products.set(productPosition, product);
                    return true;
                case 5:
                    String[] newValues = newValue.split("-");
                    int day = Integer.parseInt(newValues[0]);
                    int month = Integer.parseInt(newValues[1]);
                    int year = Integer.parseInt(newValues[2]);
                    Calendar publicationDate = new GregorianCalendar(day, month, year);

                    products.get(productPosition).setPublicationDate(publicationDate);
                    return true;
                case 6:
                    BookGenre newGenre = BookGenre.DEFAULT;
                    int selection = Integer.parseInt(newValue);

                    switch (selection) {
                        case 1:
                            newGenre = BookGenre.SCI_FI;
                            break;
                        case 2:
                            newGenre = BookGenre.FANTASY;
                            break;
                        case 3:
                            newGenre = BookGenre.HISTORICAL_NOVEL;
                            break;
                    }

                    Book product2 = (Book)products.get(productPosition);
                    product2.setGenre(newGenre);
                    products.set(productPosition, product2);
                    return true;
                case 7:
                    products.get(productPosition).setFrontPageUrl(newValue);
                    return true;
                case 8:
                    double price = Double.parseDouble(newValue);

                    products.get(productPosition).setPrice(price);
                    return true;
            }
        }

        if (products.get(productPosition) instanceof Magazine) {

            switch (valueToChange) {
                case 1:
                    products.get(productPosition).setId(newValue);
                    return true;
                case 2:
                    products.get(productPosition).setName(newValue);
                    return true;
                case 3:
                    int pages = Integer.parseInt(newValue);

                    products.get(productPosition).setTotalPages(pages);
                    return true;
                case 4:
                    String[] newValues = newValue.split("-");
                    int day = Integer.parseInt(newValues[0]);
                    int month = Integer.parseInt(newValues[1]);
                    int year = Integer.parseInt(newValues[2]);
                    Calendar publicationDate = new GregorianCalendar(day, month, year);

                    products.get(productPosition).setPublicationDate(publicationDate);
                    return true;
                case 5:
                    MagazineCategory newCategory = MagazineCategory.DEFAULT;
                    int selection = Integer.parseInt(newValue);

                    switch (selection) {
                        case 1:
                            newCategory = MagazineCategory.VARIETIES;
                            break;
                        case 2:
                            newCategory = MagazineCategory.DESIGN;
                            break;
                        case 3:
                            newCategory = MagazineCategory.SCIENTIFIC;
                            break;
                    }

                    Magazine product = (Magazine)products.get(productPosition);

                    product.setCategory(newCategory);
                    products.set(productPosition, product);
                    return true;
                case 6:
                   products.get(productPosition).setFrontPageUrl(newValue);
                    return true;
                case 7:
                    double price = Double.parseDouble(newValue);

                    products.get(productPosition).setPrice(price);
                    return true;
                case 8:
                    MagazineEmissionPeriodicity newPeriodicity = MagazineEmissionPeriodicity.DEFAULT;
                    int selection2 = Integer.parseInt(newValue);

                    switch (selection2) {
                        case 1:
                            newPeriodicity = MagazineEmissionPeriodicity.DAILY;
                            break;
                        case 2:
                            newPeriodicity = MagazineEmissionPeriodicity.MONTHLY;
                            break;
                        case 3:
                            newPeriodicity = MagazineEmissionPeriodicity.ANUALLY;
                            break;
                    }

                    Magazine product2 = (Magazine)products.get(productPosition);

                    product2.setPeriodicity(newPeriodicity);
                    products.set(productPosition, product2);
                    return true;
            }
        }
        
        return false;
    }

    public String getBookList() {

        String msg = "";

        for (int i = 0; i < products.size(); i++) {

            if (products.get(i) instanceof Book) {
                msg += "\n" + (i + 1) + ") " + products.get(i).getId() + " - " + products.get(i).getName();
            }
        }

        return msg;
    }

    public String setCalendarFormat() {

        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = calendar.getTime();

        return dateFormat.format(date);
    }

    public String purchaseABook(int userPosition, int productPosition) {

        boolean flag = false;
        
        if (!(products.get(productPosition) instanceof Book)) {
            return "Error! Select a valid position";
        }

        if (users.get(userPosition) instanceof RegularUser) {
            if (((RegularUser)users.get(userPosition)).countBooks() == 8) {
                return "Error! You have reached the limit of purchases";
            }

            for (int i = 0; i < ((RegularUser)users.get(userPosition)).getLibrary().length; i++) {
                if (((RegularUser)users.get(userPosition)).getLibrary()[i] == null && !flag) {
                    ((RegularUser) users.get(userPosition)).getLibrary()[i] = (Book) products.get(productPosition);
                    ((Book) products.get(productPosition)).addCopy();

                    UUID uuid = UUID.randomUUID();
                    long id = Math.abs(uuid.getMostSignificantBits());
                    Transaction newTransaction = new Transaction(String.valueOf(id), Calendar.getInstance(), products.get(productPosition).getPrice());
                    bills.add(newTransaction); 

                    flag = true;

                    return "\nPurchase Sumary \nTransaction code: " + String.valueOf(id) + "\nDate: " + setCalendarFormat() + "\nTotal: $" + products.get(productPosition).getPrice() + "\n";
                }
            }

            return "Error2";
        } else if (users.get(userPosition) instanceof PremiumUser) {
            ((PremiumUser) users.get(userPosition)).getLibrary().add((Book) products.get(productPosition));

            UUID uuid = UUID.randomUUID();
            long id = Math.abs(uuid.getMostSignificantBits());
            Transaction newTransaction = new Transaction(String.valueOf(id), Calendar.getInstance(), products.get(productPosition).getPrice());
            bills.add(newTransaction);            

            return "\nPruchase Sumary \nTransaction code: " + String.valueOf(id) + "\nDate: " + setCalendarFormat() + "\nTotal: $" + products.get(productPosition).getPrice() + "\n";
        }
    
        return "Error! Book was not purchased";
    }

    public String getMagazineList() {

        String msg = "";

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i) instanceof Magazine) {
                msg += "\n" + (i + 1) + ") " + products.get(i).getId() + " - " + products.get(i).getName();
            }
        }

        return msg;
    }

    public String subscribeToMagazine(int userPosition, int productPosition) {

        boolean flag = false;
        
        if (!(products.get(productPosition) instanceof Magazine)) {
            return "Error! Select a valid position";
        }

        if (users.get(userPosition) instanceof RegularUser) {
            if (((RegularUser)users.get(userPosition)).countMagazines() == 8) {
                return "Error! You have reached the limit of subscriptions";
            }

            for (int i = 0; i < ((RegularUser)users.get(userPosition)).getLibrary().length; i++) {
                if (((RegularUser)users.get(userPosition)).getLibrary()[i] == null && !flag) {
                    ((RegularUser) users.get(userPosition)).getLibrary()[i] = (Magazine) products.get(productPosition);
                    ((Magazine) products.get(productPosition)).addSubscription();

                    UUID uuid = UUID.randomUUID();
                    long id = Math.abs(uuid.getMostSignificantBits());
                    Transaction newTransaction = new Transaction(String.valueOf(id), Calendar.getInstance(), products.get(productPosition).getPrice());
                    bills.add(newTransaction); 

                    flag = true;

                    return "\nSubscription Plan \nTransaction code: " + String.valueOf(id) + "\nDate: " + setCalendarFormat() + "\nTotal: $" + products.get(productPosition).getPrice() + "\n";
                }
            }

            return "Error2";
        } else if (users.get(userPosition) instanceof PremiumUser) {
            ((PremiumUser) users.get(userPosition)).getLibrary().add((Magazine) products.get(productPosition));

            UUID uuid = UUID.randomUUID();
            long id = Math.abs(uuid.getMostSignificantBits());
            Transaction newTransaction = new Transaction(String.valueOf(id), Calendar.getInstance(), products.get(productPosition).getPrice());
            bills.add(newTransaction);            

            return "\nsusbcription plan \nTransaction code: " + String.valueOf(id) + "\nDate: " + setCalendarFormat() + "\nTotal: $" + products.get(productPosition).getPrice() + "\n";
        }
    
        return "Error! Subscription failed";
    }

    public ArrayList<BibliographicProduct> sortListDescending(int userPosition) {

        ArrayList<BibliographicProduct> librarySorted = new ArrayList<>();

        if (users.get(userPosition) instanceof RegularUser) {
            
            for (int i = 0; i < ((RegularUser)users.get(userPosition)).getLibrary().length; i++) {
                librarySorted.add(((RegularUser)users.get(userPosition)).getLibrary()[i]);
            }

            for (int i = 0; i < ((RegularUser)users.get(userPosition)).getLibrary().length; i++) {
                for (int c = 0; c < ((RegularUser)users.get(userPosition)).getLibrary().length; c++) {
                    if (((RegularUser)users.get(userPosition)).getLibrary()[i] != null) {
                        if (((RegularUser)users.get(userPosition)).getLibrary()[i].compareTo(((RegularUser)users.get(userPosition)).getLibrary()[i]) > 0) {
                            BibliographicProduct temp = ((RegularUser)users.get(userPosition)).getLibrary()[i];
                            BibliographicProduct temp2 = ((RegularUser)users.get(userPosition)).getLibrary()[i];
                            librarySorted.set(i, temp2);
                            librarySorted.set(c, temp);
                        }
                    }
                }
            }
        } else if (users.get(userPosition) instanceof PremiumUser) {
            for (int i = 0; i < ((PremiumUser)users.get(userPosition)).getLibrary().size(); i++) {
                for (int c = 0; c < ((PremiumUser)users.get(userPosition)).getLibrary().size(); c++) {
                    if (((PremiumUser)users.get(userPosition)).getLibrary().get(i) != null) {
                        if (((PremiumUser)users.get(userPosition)).getLibrary().get(i).compareTo(((PremiumUser)users.get(userPosition)).getLibrary().get(c)) > 0) {
                            BibliographicProduct temp = ((PremiumUser)users.get(userPosition)).getLibrary().get(i);
                            BibliographicProduct temp2 = ((PremiumUser)users.get(userPosition)).getLibrary().get(c);
                            librarySorted.set(i, temp2);
                            librarySorted.set(c, temp);
                        }
                    }
                }
            }
        }

        return librarySorted;
    }

    public String[][][] fillMatrix(int user) {

        ArrayList<BibliographicProduct> library = sortListDescending(user);
        int n = library.size();

        int numberOfMatrix = (int) Math.ceil((double) n/25);
        String[][][] collection = new String[numberOfMatrix][5][5];
        int index = 0;

        for (int i = 0; i < numberOfMatrix; i++) {
            for (int r = 0; r < 5; r++) {
                for (int c = 0; c < 5; c++) {
                    if (index < n) {
                        BibliographicProduct product = library.get(index);
                        if (product != null) {
                            collection[i][r][c] = product.getId();
                        } else {
                            collection[i][r][c] = "---"; // Valor por defecto para elementos nulos
                        }
                        index++;
                    } else {
                        collection[i][r][c] = "---"; // Valor por defecto para espacios vacíos
                    }
                }
            }
        }

        return collection;
    }

    public String showUserCollection(int userPosition, int page) {

		String print = "";

        if (fillMatrix(userPosition).length == page) {
            print = "There is no next page";
        } else {
            print = users.get(userPosition).getName() + "'s Library\n";

            for (int r = 0; r < 5; r++) {
                for (int c = 0; c < 5; c++) {
                    if (fillMatrix(userPosition)[page][r][c] != null) {
                        print += fillMatrix(userPosition)[page][r][c];
                    } else {
                        print += "---";
                    }
                }
                print += "\n";
            } 
        }
		
		return print;
    }

    public String readingSession(int user, int page, int currentPage, int x, int y) {

        String id = fillMatrix(user)[page][x][y];
        String session = "The matrix in the typed position is empty";

        if (users.get(user) instanceof RegularUser) {
            for (int i = 0; i < ((RegularUser) users.get(user)).getLibrary().length; i++) {
                if (((RegularUser)users.get(user)).getLibrary()[i] == null) {

                } else if (id.equals(((RegularUser)users.get(user)).getLibrary()[i].getId())) {
                    session = "reading session in progress:\n \nReading: " + ((RegularUser)users.get(user)).getLibrary()[i].getName() + "\n\nReading page " + currentPage +  " of " + ((RegularUser)users.get(user)).getLibrary()[i].getTotalPages() + "\n\n";
                    for (int c = 0; c < products.size(); c++) {
                        if (products.get(i) == ((RegularUser)users.get(user)).getLibrary()[i]) {
                            products.get(i).addRedPage();
                        }
                    }
                }
            }
        } else if (users.get(user) instanceof PremiumUser) {
            for (int i = 0; i < ((PremiumUser)users.get(user)).getLibrary().size(); i++) {
                if (((PremiumUser)users.get(user)).getLibrary().get(i) == null) {

                } else if (id.equals(((PremiumUser)users.get(user)).getLibrary().get(i).getId())) {
                    session = "reading session in progress:\n Reading: " + ((PremiumUser)users.get(user)).getLibrary().get(i).getName() + "\nReading page " + currentPage + " of " + ((PremiumUser)users.get(user)).getLibrary().get(i).getTotalPages() + "\n";
                    if (products.get(i) == ((PremiumUser)users.get(user)).getLibrary().get(i)) {
                        products.get(i).addRedPage();
                    }
                }                
            }
        }

        return session;
    }

    public String getUserMagazineSubscriptionList(int user) {
        
        String msg = "";

        if (users.get(user) instanceof PremiumUser) {
            for (int i = 0; i < ((PremiumUser)users.get(user)).getLibrary().size(); i++) {
                if (((PremiumUser)users.get(user)).getLibrary().get(i) instanceof Magazine) {
                    msg += "\n" + (i + 1) + ") " + ((PremiumUser)users.get(user)).getLibrary().get(i).getId() + " - " + ((PremiumUser)users.get(user)).getLibrary().get(i).getId();
                }
            }
        } else if (users.get(user) instanceof RegularUser) {
            for (int i = 0; i < ((RegularUser)users.get(user)).getLibrary().length; i++) {
                if (((RegularUser)users.get(user)).getLibrary()[i] instanceof Magazine) {
                    msg += "\n" + (i + 1) + ") " + ((RegularUser)users.get(user)).getLibrary()[i].getId() + " - " + ((RegularUser)users.get(user)).getLibrary()[i].getName();
                }
            }
        }

        return msg;
    }

    public boolean cancelMagazineSubscription(int user, int magazine) {

        if (users.get(user) instanceof PremiumUser) {
            ((PremiumUser)users.get(user)).getLibrary().remove(magazine);
        } else if (users.get(user) instanceof RegularUser){
            ((RegularUser)users.get(user)).getLibrary()[magazine] = null;
        }

        return true;
    }

    public int getBookRedPages() {

        int redPages = 0;

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i) instanceof Book) {
                redPages += products.get(i).getPagesRed();
            }
        }

        return redPages;
    }

    public int getMagazineRedPages() {

        int redPages = 0;

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i) instanceof Magazine) {
                redPages += products.get(i).getPagesRed();
            }
        }

        return redPages;
    }

    public String getMostReadGenre() {

        String genre = "";
        int a = 0;
        int b = 0;
        int c = 0;

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i) instanceof Book) {
                if (((Book)products.get(i)).getGenre() == BookGenre.FANTASY) {
                    a += products.get(i).getPagesRed();
                } else if (((Book)products.get(i)).getGenre() == BookGenre.HISTORICAL_NOVEL) {
                    b += products.get(i).getPagesRed();
                } else if (((Book)products.get(i)).getGenre() == BookGenre.SCI_FI) {
                    c += products.get(i).getPagesRed();
                } 
            }
        }

        if (a >= b && a >= c) {
            genre = BookGenre.FANTASY + " (" + a + " pages read)";
        } else if (b >= a && b >= c) {
            genre = BookGenre.HISTORICAL_NOVEL + " (" + b + " pages read)";
        } else if (c >= a && c >= b) {
            genre = BookGenre.SCI_FI + " (" + c + " pages read)";
        }
        return genre;
    }

    public String getMostReadCategory() {

        String category = "";
        int a = 0;
        int b = 0;
        int c = 0;

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i) instanceof Magazine) {
                if (((Magazine)products.get(i)).getCategory() == MagazineCategory.DESIGN) {
                    a += products.get(i).getPagesRed();
                } else if (((Magazine)products.get(i)).getCategory() == MagazineCategory.SCIENTIFIC) {
                    b += products.get(i).getPagesRed();
                } else if (((Magazine)products.get(i)).getCategory() == MagazineCategory.VARIETIES) {
                    c += products.get(i).getPagesRed();
                } 
            }
        }

        if (a >= b && a >= c) {
            category = MagazineCategory.DESIGN + " (" + a + " pages read)";
        } else if (b >= a && b >= c) {
            category = MagazineCategory.SCIENTIFIC + " (" + b + " pages read)";
        } else if (c >= a && c >= b) {
            category = MagazineCategory.VARIETIES + " (" + c + " pages read)";
        }
        return category;
    }
}
