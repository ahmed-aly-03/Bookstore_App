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
import javafx.stage.Stage;

public class ownerBooksScreen extends Application {
    
    private final Owner own = Owner.getInstance();
    
    @Override
    public void start(Stage primaryStage){
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
}
