package bookstore;

public class Book {
    
    private String name;
    private double price;
    
    public Book(){}
    public Book(String title, Double price){
        this.name = title;
        this.price = price;
    }
    
    public String getName(){
        return this.name;
    }
    
    public double getPrice(){
        
        return this.price;
    }
    public void setName(String title){
        name = title;
    }
    public void setPrice(double price){
        this.price = price;
    }
}
