package model;

import java.util.ArrayList;
import java.util.Calendar;

public class PremiumUser extends User {

    private ArrayList<BibliographicProduct> library;

    public PremiumUser(String id, String name, Calendar signUp) {
        super(id, name, signUp);

        this.library = new ArrayList<>();
    }

    public ArrayList<BibliographicProduct> getLibrary() {
        return library;
    }

    public void setLibrary(ArrayList<BibliographicProduct> library) {
        this.library = library;
    }

   
}
