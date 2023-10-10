/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Philip
 */
public class Owner {
    private ArrayList<Book> bookList = new ArrayList<>();
    private final String customerfilename="customers.txt";
    private final ArrayList<Customer> customerList = new ArrayList<>();
    private String username;
    private String password;
    private static Owner instance;
    private final String bookfilename = "books.txt";
    private Owner(String username, String pass){
        this.username = username;
        this.password = pass;
        this.inputBooksFromFile();
        this.inputCustomersFromFile();
    }
    public static Owner getInstance(){
        if(instance == null){
            instance = new Owner("admin", "admin");
            return instance;
        }
        return instance;
    }
    
     public void inputCustomersFromFile(){                                                   
        try{
            BufferedReader bReader = new BufferedReader(new FileReader(customerfilename));
            String username;
            String password;
            String p;
            int points;
            
            String str;
            
            while((str=bReader.readLine())!=null){
                int index1 = str.indexOf(':');
                int index2 = str.indexOf(';');
                int length = str.length();
                username = str.substring(0, index1);
                password = str.substring(index1+1, index2);
                p = str.substring(index2+1, length);
                points = Integer.parseInt(p);
                this.customerList.add(new Customer(username, password, points)); 
            }
        }
        catch(IOException e){
            System.out.println("An error occured!");
            e.printStackTrace();
        }
    }
    
    private void inputBooksFromFile(){
        try{
            BufferedReader bReader = new BufferedReader(new FileReader(bookfilename));      
            
            String name;
            double price;
            String str;
            String priceStr;
            
            while((str=bReader.readLine())!=null){
                int index = str.indexOf('$');
                int length = str.length();
                name=str.substring(0, index-1);
                priceStr=str.substring(index+1, length);
                price=Double.parseDouble(priceStr);
                this.bookList.add(new Book(name, price));
            }
            
        }
        catch(Exception e){
            System.out.println("An error occured!");
            e.printStackTrace();
        }
    }
    
    public void addBook(String name, double price){//updates files as soon as the book is added
        try{
            FileWriter fWriter = new FileWriter(bookfilename, true);
            fWriter.write(name+" $"+price+"\n");
            this.bookList.add(new Book(name, price));
            fWriter.close();
        }
        catch(IOException e){
            System.out.println("An error occured!");
            e.printStackTrace();
        }
    }
        public void deleteBook(int index){//updates files as soon as the book is deleted
        try{
            this.removeBook(index);
            new PrintWriter(bookfilename).close();
            
            FileWriter fWriter = new FileWriter(bookfilename, true);
            for(Book b:this.bookList){
                fWriter.write(b.getName()+" $"+b.getPrice()+"\n");
            }
            fWriter.close();
        }
        catch(IOException e){
            System.out.println("An error occured!");
            e.printStackTrace();
        }
    }
    
        
    public void addCustomer(String username, String password, int points){
        try{
            FileWriter fWriter = new FileWriter(customerfilename, true);
            fWriter.write(username+":"+password+";"+points+"\n");
            this.customerList.add(new Customer(username, password, points));
            fWriter.close();
        }
        catch(IOException e){
            System.out.println("An error occured!");
            e.printStackTrace();
        }
    }
    
    public void deleteCustomer(int index){
        try{
            this.removeCustomer(index);
            new PrintWriter(customerfilename).close();
            FileWriter fWriter = new FileWriter(customerfilename, true);
            for(Customer c: this.customerList){
                fWriter.write(c.getUsername()+":"+c.getPassword()+";"+c.getPointsString()+"\n");
            }
            fWriter.close();
        }
        catch(IOException e){
            System.out.println("An error occured!");
            e.printStackTrace();
        }
    } 
    
        public void rewriteCustomer(){
        try{
            new PrintWriter(customerfilename).close();
            FileWriter fWriter = new FileWriter(customerfilename, true);
            for(Customer c: this.customerList){
                fWriter.write(c.getUsername()+":"+c.getPassword()+";"+c.getPointsString()+"\n");
            }
            fWriter.close();
        }
        catch(IOException e){
            System.out.println("An error occured!");
            e.printStackTrace();
        }
    } 
    public String getUserName(){
        return username;
    }
        public String getPassword(){
        return password;
    }   
    public int getBookListSize(){
        return this.bookList.size();
    }
    public int getCustomerListSize(){
        return this.customerList.size();
    }
    public Book getBook(int index){
        return this.bookList.get(index);
    }
    public Customer getCustomer(int index){
        return this.customerList.get(index);
    }
    private void removeBook(int index){
        this.bookList.remove(index);
    }
    private void removeCustomer(int index){
        this.customerList.remove(index); 
    }
}
