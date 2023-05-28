package model;

import java.util.Calendar;

public class Magazine extends BibliographicProduct implements Subscribable {

    private MagazineCategory category; 
    private int activeSubscriptions;
    private MagazineEmissionPeriodicity periodicity;

    public Magazine(String id, String name, int totalPages, Calendar publicationDate, MagazineCategory category, String frontPageUrl, double price, MagazineEmissionPeriodicity periodicity) {
        super(id, name, totalPages, publicationDate, frontPageUrl, price);

        this.activeSubscriptions = 0;
    }

    public MagazineCategory getCategory() {
        return category;
    }

    public void setCategory(MagazineCategory category) {
        this.category = category;
    }

    public int getActiveSubscriptions() {
        return activeSubscriptions;
    }

    public void setActiveSubscriptions(int activeSubscriptions) {
        this.activeSubscriptions = activeSubscriptions;
    }

    public MagazineEmissionPeriodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(MagazineEmissionPeriodicity periodicity) {
        this.periodicity = periodicity;
    }

    @Override
    public void addSubscription() {
        
        setActiveSubscriptions(activeSubscriptions + 1);
    }
}
