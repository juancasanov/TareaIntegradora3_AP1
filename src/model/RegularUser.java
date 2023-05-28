package model;

import java.util.Calendar;

public class RegularUser extends User {

    private BibliographicProduct[] library;

    public RegularUser(String id, String name, Calendar signUp) {
        super(id, name, signUp);

        this.library = new BibliographicProduct[7];
    }

    public BibliographicProduct[] getLibrary() {
        return library;
    }

    public void setLibrary(BibliographicProduct[] library) {
        this.library = library;
    }

    public int countBooks() {

        int books = 0;

        for (int i = 0; i < library.length; i++) {
            if (this.library[i] instanceof Book) {
                books++;
            }
        }

        return books;
    }

    public int countMagazines() {

        int magazines = 0;

        for (int i = 0; i < library.length; i++) {
            if (this.library[i] instanceof Magazine) {
                magazines++;
            }
        }

        return magazines;
    }

}