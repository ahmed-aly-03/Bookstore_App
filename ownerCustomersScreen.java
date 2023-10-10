package bookstore;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ownerCustomersScreen extends Application {
    
    private final Owner own = Owner.getInstance();
    //private final CustomerList cl = CustomerList.getInstance();
    
    @Override
    public void start(Stage primaryStage){
        
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
