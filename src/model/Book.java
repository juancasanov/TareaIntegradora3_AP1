package model;

import java.util.Calendar;

public class Book extends BibliographicProduct implements Purchasable{
    
    private String review;
    private BookGenre genre;
    private int copiesSold;

    public Book(String id, String name, int totalPages, String review, Calendar publicationDate, BookGenre genre, String frontPageUrl, double price) {
        super(id, name, totalPages, publicationDate, frontPageUrl, price);

        this.copiesSold = 0;
    }

    public String getReview() {
        return review;
    }
    public void setReview(String review) {
        this.review = review;
    }
    public BookGenre getGenre() {
        return genre;
    }
    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }
    public int getCopiesSold() {
        return copiesSold;
    }
    public void setCopiesSold(int copiesSold) {
        this.copiesSold = copiesSold;
    }

    @Override
    public void addCopy() {
        
        setCopiesSold(copiesSold + 1);
    } 
}
