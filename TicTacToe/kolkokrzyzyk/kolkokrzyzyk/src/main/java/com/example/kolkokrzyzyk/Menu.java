package com.example.kolkokrzyzyk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class Menu extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource("Menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 600);
        MenuController controller = fxmlLoader.getController();
        stage.setResizable(false);
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();

        Button nameButton1 = (Button) scene.lookup("#setName1");
        nameButton1.setOnAction(event -> controller.nameButton(event, 1));

        Button nameButton2 = (Button) scene.lookup("#setName2");
        nameButton2.setOnAction(event -> controller.nameButton(event, 2));

        Button colorButton1 = (Button) scene.lookup("#setColor1");
        colorButton1.setOnAction(event -> controller.colorButton(event, 1));

        Button colorButton2 = (Button) scene.lookup("#setColor2");
        colorButton2.setOnAction(event -> controller.colorButton(event, 2));

        Button x3Button = (Button) scene.lookup("#X3");
        x3Button.setOnAction(controller::x3Button);


       Button datax3Button = (Button) scene.lookup("#dataX3");
       datax3Button.setOnAction(controller::dataButtonx3);


        Button x4Button = (Button) scene.lookup("#X4");
        x4Button.setOnAction(controller::x4Button);

        Button datax4Button = (Button) scene.lookup("#dataX4");
        datax4Button.setOnAction(controller::dataButtonx4);
    }


}