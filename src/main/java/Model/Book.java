package Model;

public class Book {
    private String Id;
    private String name;
    private String donator;
    private boolean isBorrowed;
    private String Borrower;

    public Book(String id, String name, String donator, boolean isBorrowed, String borrower) {
        Id = id;
        this.name = name;
        this.donator = donator;
        this.isBorrowed = isBorrowed;
        Borrower = borrower;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return Id;
    }

    public String getDonator() {
        return donator;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public String getBorrower() {
        return Borrower;
    }
}
