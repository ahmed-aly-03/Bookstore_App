package bookstore;

//@author cjplett

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ownerStartScreen extends Application{
    
    private final Owner own = Owner.getInstance();
    //private final CustomerList cl = CustomerList.getInstance();
    
    @Override
    public void start(Stage primaryStage){                //OwnerStartScreen
        GridPane startScreen = new GridPane();      
        startScreen.setAlignment(Pos.CENTER);
        startScreen.setHgap(20);
        startScreen.setVgap(20);
        startScreen.setPadding(new Insets(25, 25, 25, 25));
        
        Text startScreenTitle = new Text("Welcome to the Bookstore");
        
        Button booksBtn = new Button("Books");
        Button customersBtn = new Button("Customers");
        Button logoutBtn = new Button("Logout");
        HBox bBtn = new HBox(10);
        HBox cBtn = new HBox(10);
        HBox lBtn = new HBox(10);
        bBtn.setAlignment(Pos.CENTER);
        cBtn.setAlignment(Pos.CENTER);
        lBtn.setAlignment(Pos.CENTER);
        bBtn.getChildren().add(booksBtn);
        cBtn.getChildren().add(customersBtn);
        lBtn.getChildren().add(logoutBtn);
        startScreen.add(bBtn, 0, 0);
        startScreen.add(cBtn, 0, 1);
        startScreen.add(lBtn, 0, 2);
        
        booksBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                ownerBooksScreen o = new ownerBooksScreen();
                o.start(primaryStage);
            }
        });
        
        customersBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                ownerCustomersScreen o = new ownerCustomersScreen();
                o.start(primaryStage);
            }
        });
        
        logoutBtn.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent e){
               loginScreen l = new loginScreen(); 
               l.start(primaryStage);
           }
        });
        
        Scene sceneStartScreen = new Scene(startScreen, 400, 275);
        primaryStage.setScene(sceneStartScreen);
        primaryStage.show();
    }
    
    public void ownerBooksScreen(Stage primaryStage){

        Label topLeft = new Label("Book Name");
        topLeft.setPrefWidth(400);
        topLeft.setAlignment(Pos.CENTER);
        Label topRight = new Label("Book Price");
        topRight.setPrefWidth(400);
        topRight.setAlignment(Pos.CENTER);
        
        ListView list = new ListView();                                         //Book list
        int number = own.getBookListSize();
        System.out.println("BookList size: "+number);
        for(int i=0; i<number; ++i){
            Book b = own.getBook(i);
            String bookName=b.getName();
            double bookPr=b.getPrice();
            String bookPrice=Double.toString(bookPr);

            Label bookN = new Label(bookName);
            bookN.setAlignment(Pos.CENTER_LEFT);
            bookN.setPrefWidth(345);
            Label bookP = new Label(bookPrice); 
            bookP.setAlignment(Pos.CENTER_RIGHT);
            bookP.setPrefWidth(345);
            HBox book = new HBox(2);
            book.setAlignment(Pos.CENTER);
            book.getChildren().addAll(bookN, bookP);
            book.setPrefWidth(700);
            list.getItems().add(book);
        }
        
        list.setMaxSize(800, 300);
        list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        GridPane header = new GridPane();
        header.setPrefWidth(800);
        header.setGridLinesVisible(false);
        header.add(topLeft, 0, 0);
        header.add(topRight, 1, 0);
        header.setAlignment(Pos.CENTER);
        
        Label nameL = new Label("Name");
        TextField nameT = new TextField();
        Label priceL = new Label("Price");
        TextField priceT = new TextField();
        Button addBtn = new Button("Add");
        
        HBox addBook = new HBox(5);
        addBook.getChildren().addAll(nameL, nameT, priceL, priceT, addBtn);
        addBook.setAlignment(Pos.CENTER);
        
        Button delBtn = new Button("Delete");
        Button backBtn = new Button("Back");
        
        HBox delBook = new HBox(2);
        delBook.setAlignment(Pos.CENTER);
        delBook.getChildren().addAll(delBtn, backBtn);
       
        VBox entireScreen = new VBox(4);
        entireScreen.setPrefSize(800, 475);
        entireScreen.getChildren().addAll(header, list, addBook, delBook);
        entireScreen.setPadding(new Insets(25, 25, 25, 25));
        entireScreen.setAlignment(Pos.CENTER);
        
        addBtn.setOnAction(new EventHandler<ActionEvent>(){                     //Add book button
            @Override
            public void handle(ActionEvent e){
                final String bookName = nameT.getText();
                final String bookPr = priceT.getText();
                final double bookPrice = Double.parseDouble(bookPr);
                Book b = new Book(bookName, bookPrice);
                own.addBook(bookName, bookPrice);
                
                Label bookN = new Label(bookName);
                bookN.setAlignment(Pos.CENTER_LEFT);
                bookN.setPrefWidth(345);
                Label bookP = new Label(bookPr);
                bookP.setAlignment(Pos.CENTER_RIGHT);
                bookP.setPrefWidth(345);
                HBox book = new HBox(2);
                book.setAlignment(Pos.CENTER);
                book.getChildren().addAll(bookN, bookP);
                book.setPrefWidth(700);
                list.getItems().add(book);
            }
        });
        
        delBtn.setOnAction(new EventHandler<ActionEvent>(){                     //Delete book button
            @Override
            public void handle(ActionEvent e){
                final int selBookIndex = list.getSelectionModel().getSelectedIndex();
                list.getItems().remove(selBookIndex);
                own.deleteBook(selBookIndex);
            }  
        });
        
        
        backBtn.setOnAction(new EventHandler<ActionEvent>(){                    
            @Override
            public void handle(ActionEvent e){
                ownerStartScreen o = new ownerStartScreen();
                o.start(primaryStage);
            }
        });
       
        Scene scene = new Scene(entireScreen, 800, 475);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void ownerCustomersScreen(Stage primaryStage){
        
        Label label1 = new Label("Username");
        label1.setPrefWidth(250);
        label1.setAlignment(Pos.CENTER_LEFT);
        Label label2 = new Label("Password");
        label2.setPrefWidth(250);
        label2.setAlignment(Pos.CENTER);
        Label label3 = new Label("Points");
        label3.setPrefWidth(250);
        label3.setAlignment(Pos.CENTER_RIGHT);
        
        HBox heading = new HBox(3);
        heading.setAlignment(Pos.CENTER);
        heading.getChildren().addAll(label1, label2, label3);
        
        ListView list = new ListView();                                          //will need another static Boolean here
        int number = own.getCustomerListSize();
        for(int i=0; i<number; ++i){
            Customer c = own.getCustomer(i);
            
            Label username = new Label(c.getUsername());
            username.setAlignment(Pos.CENTER_LEFT);
            username.setPrefWidth(250);
            Label password = new Label(c.getPassword());
            password.setAlignment(Pos.CENTER);
            password.setPrefWidth(250);
            Label points = new Label(c.getPointsString());
            points.setAlignment(Pos.CENTER_RIGHT);
            points.setPrefWidth(250);
            
            HBox customer = new HBox(3);
            customer.setAlignment(Pos.CENTER_RIGHT);
            customer.getChildren().addAll(username, password, points);
            customer.setPrefWidth(700);
            list.getItems().add(customer);
        }
        list.setMaxSize(750, 300);
        
        VBox topPart = new VBox(2);
        topPart.setAlignment(Pos.CENTER);
        topPart.getChildren().addAll(heading, list);
        
        Label username = new Label("Username");
        username.setAlignment(Pos.CENTER_LEFT);
        TextField name = new TextField();
        name.setAlignment(Pos.CENTER_LEFT);
        Label password = new Label("Password");
        password.setAlignment(Pos.CENTER_LEFT);
        TextField pass = new TextField();
        Button addBtn = new Button("Add");
        
        HBox middlePart = new HBox(5);
        middlePart.setAlignment(Pos.CENTER);
        middlePart.getChildren().addAll(username, name, password, pass, addBtn);
        
        Button delBtn = new Button("Delete");
        delBtn.setAlignment(Pos.CENTER);
        Button backBtn = new Button("Back");
        backBtn.setAlignment(Pos.CENTER);
        
        HBox bottomPart = new HBox(2);
        bottomPart.setAlignment(Pos.CENTER);
        bottomPart.getChildren().addAll(delBtn, backBtn);
        
        VBox entireScreen = new VBox(3);
        entireScreen.getChildren().addAll(topPart, middlePart, bottomPart);
        entireScreen.setAlignment(Pos.CENTER);
        
        addBtn.setOnAction(new EventHandler<ActionEvent>(){                     //Add customer button
            @Override
            public void handle(ActionEvent e){
                final String username = name.getText();
                final String password = pass.getText();
                
                Customer c = new Customer(username, password, 0);
                own.addCustomer(username, password, 0); 
                
                Label userN = new Label(username);
                userN.setAlignment(Pos.CENTER_LEFT);
                userN.setPrefWidth(250);
                Label passW = new Label(password);
                passW.setAlignment(Pos.CENTER);
                passW.setPrefWidth(250);
                Label p = new Label("0");
                p.setAlignment(Pos.CENTER_RIGHT);
                p.setPrefWidth(250);
                
                HBox customer = new HBox(3);
                customer.setAlignment(Pos.CENTER_RIGHT);
                customer.getChildren().addAll(userN, passW, p);
                customer.setPrefWidth(700);
                list.getItems().add(customer);
            }
        });
        
        delBtn.setOnAction(new EventHandler<ActionEvent>(){                     //Delete book button
            @Override
            public void handle(ActionEvent e){
                final int selCustomerIndex = list.getSelectionModel().getSelectedIndex();
                list.getItems().remove(selCustomerIndex);
                own.deleteCustomer(selCustomerIndex);
            }  
        });
        
        backBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                ownerStartScreen o = new ownerStartScreen();
                o.start(primaryStage);
            }
        });
        
        Scene scene = new Scene(entireScreen, 800, 475);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
