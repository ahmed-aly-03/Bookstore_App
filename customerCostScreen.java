package bookstore;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class customerCostScreen extends Application{
    
    private Customer c;
    private double costOfBooks;
    
    public customerCostScreen(Customer c, double costOfBooks){
        this.c=c;
        this.costOfBooks=costOfBooks;
    }
    
    @Override
    public void start(Stage primaryStage){
        
        Text cost = new Text("Total cost is $"+this.costOfBooks);
        cost.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 15));
        HBox heading = new HBox(1);
        heading.getChildren().addAll(cost);
        heading.setAlignment(Pos.CENTER);
        
        HBox top = new HBox(1);
        top.setAlignment(Pos.CENTER);
        top.getChildren().addAll(heading);
        top.setPrefHeight(50);
        
        Text points = new Text("Points: "+c.getPoints());
        points.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 15));
        
        Text status = new Text("Status: "+c.getStatus());
        status.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 15));
        
        HBox pH = new HBox(1);
        pH.setAlignment(Pos.CENTER);
        pH.setPrefWidth(150);
        pH.getChildren().addAll(points);
        
        HBox sH = new HBox(1);
        sH.setAlignment(Pos.CENTER);
        sH.setPrefWidth(150);
        sH.getChildren().addAll(status);
        
        HBox middle = new HBox(2);
        middle.setAlignment(Pos.CENTER);
        middle.getChildren().addAll(pH, sH);
        middle.setPrefHeight(50);
        middle.setPrefWidth(250);
        
        Button logout = new Button("Logout");
        logout.setAlignment(Pos.CENTER);
        
        HBox bottom = new HBox(1);
        bottom.setAlignment(Pos.CENTER);
        bottom.getChildren().addAll(logout);
        bottom.setPrefHeight(50);
        
        logout.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent e){
               loginScreen l = new loginScreen(); 
               l.start(primaryStage);
           }
        });
        
        VBox costScreen = new VBox(3);
        costScreen.setAlignment(Pos.CENTER);
        costScreen.getChildren().addAll(top, middle, bottom);
        
        Scene customerCostScreen = new Scene(costScreen, 400, 275);
        primaryStage.setScene(customerCostScreen);
        primaryStage.show();
    }
}
