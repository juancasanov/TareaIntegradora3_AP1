package model;

import java.util.Calendar;

public abstract class BibliographicProduct implements Comparable<BibliographicProduct> {
    
    private String id;
    private String name;
    private int totalPages;
    private Calendar publicationDate;
    private String frontPageUrl;
    private int pagesRed;
    private double price;

    public BibliographicProduct(String id, String name, int totalPages, Calendar publicationDate, String frontPageUrl, double price) {
        this.id = id;
        this.name = name;
        this.totalPages = totalPages;
        this.publicationDate = publicationDate;
        this.frontPageUrl = frontPageUrl;
        this.price = price;
        this.pagesRed = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public Calendar getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Calendar publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getFrontPageUrl() {
        return frontPageUrl;
    }

    public void setFrontPageUrl(String frontPageUrl) {
        this.frontPageUrl = frontPageUrl;
    }

    public int getPagesRed() {
        return pagesRed;
    }

    public void setPagesRed(int pagesRed) {
        this.pagesRed = pagesRed;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int compareTo(BibliographicProduct o) {
        
        return this.publicationDate.getTime().compareTo(o.getPublicationDate().getTime());
    }

    public void addRedPage() {
        this.pagesRed++;
    }
}
