package bookstore;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class customerStartScreen extends Application{
    
    private final Owner own = Owner.getInstance();
    private ArrayList<Book> shoppingCart = new ArrayList<>();
    private ArrayList<CheckBox> cb = new ArrayList<>();
    private double costOfBooks;
    private Customer c;
    private int points;
    private String customerName;
    private String status;
    
    public double getCostOfBooks(){
        return this.costOfBooks;
    }
    public int getCurrentPoints(){
        return this.points;
    }
    
    @Override
    public void start(Stage primaryStage){
        
        loginScreen l = new loginScreen();
        int index = l.getCustomerIndex();
        c = own.getCustomer(index);
        customerName = c.getUsername();
        points = c.getPoints();
        status = c.getStatus();
        
        Label heading = new Label("Welcome "+customerName+"! You have "+points+" points. Your status is "+status+".");
        heading.setPrefHeight(50);
        heading.setAlignment(Pos.TOP_CENTER);
        
        Label books = new Label("Books");
        Label price = new Label("Price");
        Label select = new Label("Select");
        books.setPrefWidth(550);
        books.setAlignment(Pos.CENTER_LEFT);
        price.setPrefWidth(125);
        price.setAlignment(Pos.CENTER);
        select.setPrefWidth(50);
        select.setAlignment(Pos.CENTER_LEFT);
        
        HBox listTitles = new HBox(3);
        listTitles.setAlignment(Pos.CENTER);
        listTitles.getChildren().addAll(books, price, select);
        
        ListView list = new ListView();                                             //Book list
        int number = own.getBookListSize();
        System.out.println("BookList size: "+number);
        for(int i=0; i<number; ++i){
            
            Book b = own.getBook(i);
            double bookP=b.getPrice();
            String bookPr=Double.toString(bookP);
            
            Label bookName = new Label(b.getName());
            bookName.setAlignment(Pos.CENTER_LEFT);
            bookName.setPrefWidth(550);
            
            Label bookPrice = new Label(bookPr); 
            bookPrice.setAlignment(Pos.CENTER);
            bookPrice.setPrefWidth(150);
            
            CheckBox c = new CheckBox();
            c.setAlignment(Pos.CENTER_RIGHT);
            c.setPrefWidth(50);
            
            cb.add(c);
            
            HBox book = new HBox(3);
            book.setAlignment(Pos.CENTER);
            book.getChildren().addAll(bookName, bookPrice, c);
            book.setPrefWidth(700);
            list.getItems().add(book);
        }
        list.setMaxSize(750, 300);
        
        VBox bookList = new VBox(2);
        bookList.setAlignment(Pos.CENTER);
        bookList.setPrefWidth(750);
        bookList.getChildren().addAll(listTitles, list);
        
        Button buyBooks = new Button("Buy");
        buyBooks.setAlignment(Pos.CENTER);
        Button redeemBuy = new Button("Redeem Points and Buy");
        redeemBuy.setAlignment(Pos.CENTER);
        Button logout = new Button("Logout"); 
        logout.setAlignment(Pos.CENTER);
        
        buyBooks.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                 costOfBooks=0;
                int numBooks = cb.size();
                for(int i=numBooks-1; i>=0; --i){
                    if(cb.get(i).isSelected()){
                        costOfBooks+=own.getBook(i).getPrice();
                        own.deleteBook(i);
                    }
                }
                points=10*(int)costOfBooks;
                c.addPoints(points);
                own.rewriteCustomer();
                customerCostScreen cCS = new customerCostScreen(c, costOfBooks);
                cCS.start(primaryStage);
           }
        });
        
          redeemBuy.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                costOfBooks=0;
                int numBooks = cb.size();
                for(int i=numBooks-1; i>=0; --i){
                    if(cb.get(i).isSelected()){
                        costOfBooks+=own.getBook(i).getPrice();
                        own.deleteBook(i);
                    }
                }
                
                if(c.getPoints()==0){
                    points=10*(int)costOfBooks;
                    c.addPoints(points);
                    own.rewriteCustomer();
                    customerCostScreen cCS = new customerCostScreen(c, costOfBooks);
                    cCS.start(primaryStage);
                }else if(c.getPoints()>(100*costOfBooks)){
                        points=(100*(int)costOfBooks);
                        c.removePoints(points);
                        own.rewriteCustomer();
                        costOfBooks=0;
                        customerCostScreen cCS = new customerCostScreen(c, costOfBooks);
                        cCS.start(primaryStage);
                } else{
                        costOfBooks=costOfBooks-((double)c.getPoints()/100);
                        c.removePoints(c.getPoints());
                        c.addPoints(10*(int)costOfBooks);
                        own.rewriteCustomer();
                        customerCostScreen cCS = new customerCostScreen(c, costOfBooks);
                        cCS.start(primaryStage);
                    }
                }
        });
        
        logout.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent e){
               loginScreen l = new loginScreen(); 
               l.start(primaryStage);
           }
        });
        
        HBox buttons = new HBox(3);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(buyBooks, redeemBuy, logout);
        
        VBox customerStartScreen = new VBox(3);
        customerStartScreen.setAlignment(Pos.CENTER);
        customerStartScreen.getChildren().addAll(heading, bookList, buttons);
        customerStartScreen.setPadding(new Insets(25, 25, 25, 25));
        
        Scene sceneStartScreen = new Scene(customerStartScreen, 800, 475);
        primaryStage.setScene(sceneStartScreen);
        primaryStage.show();
    }
}
