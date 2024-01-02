package com.example.kolkokrzyzyk;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.paint.*;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MenuController {

    @FXML
    private Label Name1;
    private String Name1S = "Gracz 1";
    @FXML
    private Label Name2;
    private String Name2S = "Gracz 2";
    public int Color1 = 2;
    public int Color2 = 1;

    public String getName1S() {
        return Name1S;
    }

    public String getName2S() {
        return Name2S;
    }

    public int getColor1() {
        return Color1;
    }

    public int getColor2() {
        return Color2;
    }

    public void nameButton(ActionEvent event, int num) {
        Stage newP = new Stage();
        newP.setResizable(false);
        newP.initModality(Modality.APPLICATION_MODAL);
        newP.initOwner(((Node) event.getSource()).getScene().getWindow());

        VBox vbox = new VBox(10);
        vbox.setStyle("-fx-background-color: #3D4957;");
        vbox.setAlignment(Pos.CENTER);
        Label label = new Label("Podaj nazwę:");
        label.setTextFill(Color.WHITE);
        TextField textField = new TextField();
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[a-zA-Z ]*")) {
                textField.setText(newValue.replaceAll("[^a-zA-Z ]", ""));
            }
        });
        Button okButton = new Button("Zatwierdź");
        okButton.setStyle("-fx-background-color: #eda678;");
        vbox.getChildren().addAll(label, textField, okButton);


        int maxLength = 7;
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > maxLength) {
                textField.setText(oldValue);
            }
        });
        if (num == 1) {
            okButton.setOnAction(e -> {
                Name1.setText(String.valueOf(textField.getText()));
                Name1S = String.valueOf(textField.getText());
                newP.close();
            });
        }
        if (num == 2) {
            okButton.setOnAction(e -> {
                Name2.setText(String.valueOf(textField.getText()));
                Name2S = String.valueOf(textField.getText());
                newP.close();
            });
        }
        Scene scene = new Scene(vbox, 300, 150);
        newP.setScene(scene);
        newP.show();
    }

    public void colorButton(ActionEvent event, int num) {
        Stage newP = new Stage();
        newP.setResizable(false);
        newP.initModality(Modality.APPLICATION_MODAL);
        newP.initOwner(((Node) event.getSource()).getScene().getWindow());

        VBox vbox = new VBox(10);
        vbox.setStyle("-fx-background-color: #3D4957;");
        vbox.setAlignment(Pos.CENTER);
        Label label = new Label("Wybierz kolor:");
        Button redButton = new Button("Czerwony");
        Button blueButton = new Button("Niebieski");
        Button blackButton = new Button("Czarny");
        Button yellowButton = new Button("Żółty");
        Button greenButton = new Button("Zielony");
        redButton.setStyle("-fx-background-color: #eda678;");
        blueButton.setStyle("-fx-background-color: #eda678;");
        blackButton.setStyle("-fx-background-color: #eda678;");
        yellowButton.setStyle("-fx-background-color: #eda678;");
        greenButton.setStyle("-fx-background-color: #eda678;");
        vbox.getChildren().addAll(label, redButton, blueButton, blackButton, yellowButton, greenButton);

        if (num == 1) {
            if (Color1 == 1) {
                redButton.setDisable(true);
            }
            if (Color1 == 2) {
                blueButton.setDisable(true);
            }
            if (Color1 == 3) {
                blackButton.setDisable(true);
            }
            if (Color1 == 4) {
                yellowButton.setDisable(true);
            }
            if (Color1 == 5) {
                greenButton.setDisable(true);
            }

            redButton.setOnAction(e -> {
                Name1.setTextFill(Color.RED);
                newP.close();
                Color1 = 1;
            });

            blueButton.setOnAction(e -> {
                Name1.setTextFill(Color.BLUE);
                newP.close();
                Color1 = 2;
            });

            blackButton.setOnAction(e -> {
                Name1.setTextFill(Color.BLACK);
                newP.close();
                Color1 = 3;
            });

            yellowButton.setOnAction(e -> {
                Name1.setTextFill(Color.YELLOW);
                newP.close();
                Color1 = 4;
            });

            greenButton.setOnAction(e -> {
                Name1.setTextFill(Color.GREEN);
                newP.close();
                Color1 = 5;
            });
        }
        if (num == 2) {
            if (Color2 == 1) {
                redButton.setDisable(true);
            }
            if (Color2 == 2) {
                blueButton.setDisable(true);
            }
            if (Color2 == 3) {
                blackButton.setDisable(true);
            }
            if (Color2 == 4) {
                yellowButton.setDisable(true);
            }
            if (Color2 == 5) {
                greenButton.setDisable(true);
            }

            redButton.setOnAction(e -> {
                Name2.setTextFill(Color.RED);
                newP.close();
                Color2 = 1;
            });

            blueButton.setOnAction(e -> {
                Name2.setTextFill(Color.BLUE);
                newP.close();
                Color2 = 2;
            });

            blackButton.setOnAction(e -> {
                Name2.setTextFill(Color.BLACK);
                newP.close();
                Color2 = 3;
            });

            yellowButton.setOnAction(e -> {
                Name2.setTextFill(Color.YELLOW);
                newP.close();
                Color2 = 4;
            });

            greenButton.setOnAction(e -> {
                Name2.setTextFill(Color.GREEN);
                newP.close();
                Color2 = 5;
            });
        }
        Scene scene = new Scene(vbox, 300, 230);
        newP.setScene(scene);
        newP.show();
    }

    public void x3Button(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Game3x3-view.fxml"));
            Parent root = loader.load();
            Game3x3Controller gameController1 = loader.getController();
            gameController1.setPlayer1S(getName1S());
            gameController1.setPlayer2S(getName2S());
            gameController1.setPlayer1(getColor1());
            gameController1.setPlayer2(getColor2());


            Scene gameScene = new Scene(root);
            Stage gameStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            gameStage.setScene(gameScene);
            gameStage.show();

          //  Stage primaryStage = (Stage) gameStage.getWindow();
            gameStage.setTitle("Gra3x3");
            gameStage.setWidth(850);
            gameStage.setHeight(700);
            gameStage.setResizable(false);
            gameStage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void x4Button(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Game4x4-view.fxml"));
            Parent root = loader.load();
            Game4x4Controller gameController = loader.getController();
            gameController.setN1S(getName1S());
            gameController.setN2S(getName2S());
            gameController.setC1(getColor1());
            gameController.setC2(getColor2());
            gameController.updateLabels();

            Scene currentScene = ((Node) event.getSource()).getScene();
            currentScene.setRoot(root);
            Stage primaryStage = (Stage) currentScene.getWindow();
            primaryStage.setTitle("Gra4x4");
            primaryStage.setWidth(1100);
            primaryStage.setHeight(700);
            primaryStage.setResizable(false);
            primaryStage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void dataButtonx3(ActionEvent event) {
        try (BufferedReader reader = new BufferedReader(new FileReader("wyniki.txt"))) {
            StringBuilder data = new StringBuilder();
            String result;
            while ((result = reader.readLine()) != null) {
                data.append(result).append("\n");
            }

            sendToServer("wyniki.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void sendToServer(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder data = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n");
            }

            try {
                URL url = new URL("http://localhost:1234/");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.getOutputStream().write(data.toString().getBytes());

                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void dataButtonx4(ActionEvent event) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Wyniki4x4.txt"))) {
            StringBuilder data = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n");
            }

            sendDataToServer("wyniki4x4.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendDataToServer(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder data = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n");
            }

            try {
                URL url = new URL("http://localhost:8080/");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.getOutputStream().write(data.toString().getBytes());

                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}