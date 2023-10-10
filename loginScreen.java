package bookstore;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//@author cjplett

public class loginScreen extends Application {
    
    private final Owner own = Owner.getInstance();
   // private final CustomerList cl = CustomerList.getInstance();
    //private static boolean booksNotInputed = true;
    //private static boolean customersNotInputed = true;
    private static int customerIndex;
    
    public int getCustomerIndex(){
        return this.customerIndex;
    }
    
    public boolean checkOwnerID(String username, String password){
        boolean test = false;
        if((password.equals(own.getPassword()))&&(username.equals(own.getUserName())))
            test=true;
        return test;
    }
    
    public boolean checkCustomerID(String username, String password){
        boolean test=false;
        int numCust = own.getCustomerListSize();
        int i;
        for(i=0; i<numCust; ++i){
            Customer c = own.getCustomer(i);
            if((username.equals(c.getUsername()))&&(password.equals(c.getPassword()))){
                test=true;
                break;
            }
        }
        customerIndex=i;
        return test;
    }
    
    @Override
    public void start(Stage primaryStage) {                 //Login screen grid
        primaryStage.setTitle("Bookstore App");
        
        GridPane LogIn = new GridPane();  
        LogIn.setGridLinesVisible(false);
        LogIn.setAlignment(Pos.CENTER);
        LogIn.setHgap(10);
        LogIn.setVgap(10);
        LogIn.setPadding(new Insets(25, 25, 25, 25));
        
        Text scenetitle = new Text("Welcome to the Bookstore App");
        scenetitle.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 15));
        HBox hbSceneTitle = new HBox();
        hbSceneTitle.setPrefHeight(25);
        hbSceneTitle.setAlignment(Pos.TOP_CENTER);
        hbSceneTitle.getChildren().add(scenetitle);
        LogIn.add(hbSceneTitle, 0, 0, 2, 1);
        Label userName = new Label("User Name:");
        LogIn.add(userName, 0, 1);
        TextField userTextField = new TextField();      //Username text field
        LogIn.add(userTextField, 1, 1);
        Label password = new Label("Password:");
        LogIn.add(password, 0, 2);
        PasswordField passwordTextField = new PasswordField();      //Password text field
        LogIn.add(passwordTextField, 1, 2);
        
        Button logInBtn = new Button("Sign In");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(logInBtn);
        LogIn.add(hbBtn, 1, 4);
        
        logInBtn.setOnAction(new EventHandler<ActionEvent>(){                        //LOGIN Button
            @Override
            public void handle(ActionEvent e){
                loginScreen l = new loginScreen();
                final String username = userTextField.getText();
                final String password = passwordTextField.getText();
                
                if(checkOwnerID(username, password)){
                    ownerStartScreen o = new ownerStartScreen();
                    o.start(primaryStage);
                }
                else{
                    if(checkCustomerID(username, password)){
                        customerStartScreen c = new customerStartScreen();
                        c.start(primaryStage);
                    }
                    else{
                        Label wrongInput = new Label("Wrong Credentials!");
                        wrongInput.setAlignment(Pos.BOTTOM_LEFT);
                        LogIn.add(wrongInput, 0, 3);
                    }
                }
            }
        });
        
        Scene sceneLogIn = new Scene(LogIn, 400, 275);
        primaryStage.setScene(sceneLogIn);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    
    
}
