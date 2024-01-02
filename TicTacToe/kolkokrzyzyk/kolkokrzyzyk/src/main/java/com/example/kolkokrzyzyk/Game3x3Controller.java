package com.example.kolkokrzyzyk;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class Game3x3Controller {
    @FXML
    private Label player1Label;
    @FXML
    private Label player2Label;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;
    @FXML
    private Button resetButton;
    private String player1S;
    private String player2S;
    private int player1;
    private int player2;
    private int currentPlayer = 1;
    private int moves = 0;

    ArrayList<Button> buttons;

    public Game3x3Controller() {
    }
    public void setPlayer1(int c1) {
        this.player1 = player1;
    }

    public void setPlayer2(int player2) {
        this.player2 = player2;
    }

    public void setPlayer1S(String player1S) {
        this.player1S = player1S;
    }

    public void setPlayer2S(String Player2S) {
        this.player2S = Player2S;
    }
    public void setPlayer1Label(Label player1Label) {
        this.player1Label = player1Label;
    }
    public void setPlayer2Label(Label player2Label) {
        this.player2Label = player2Label;
    }

    @FXML
    public void initialize() {

        currentPlayer = 1;
        buttons = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8, button9));
        // buttons.forEach(button->{

        //   button.setFocusTraversable(false);
        //});

    }
    @FXML
    public void resetGame(ActionEvent actionEvent) {
        currentPlayer = 1;
        moves = 0;
        buttons.forEach(this::resetButton);
        //      updateLabels();
    }

    private void resetButton(Button button) {
        button.setDisable(false);
        button.setText("");
        button.setStyle("-fx-background-color: #3D4957; -fx-border-color: WHITE;");
    }


    public void onClick(ActionEvent actionEvent) {
        ++moves;

        Button clicked = (Button) actionEvent.getSource();
        if (moves % 2 == 0) {
            this.currentPlayer = 1;
            clicked.setText("X");
          //  clicked.setStyle("-fx-background-color: blue;");

            clicked.setStyle("-fx-text-fill: navy;");
        } else {
            clicked.setText("O");
            this.currentPlayer = 2;
            clicked.setStyle("-fx-text-fill: red;");
           // clicked.setStyle("-fx-background-color: blue;");
        }
        clicked.setDisable(true);



        if (this.checkWin()) {
            this.showAlert("Wygrana!");
            Platform.exit();

        } else if (this.checkDraw()) {
            this.showAlert("Remis!");
            Platform.exit();


        }

    }



    private boolean checkWin() {
        String[][] results = new String[3][3];


        results[0][0] = button1.getText();
        results[0][1] = button2.getText();
        results[0][2] = button3.getText();
        results[1][0] = button4.getText();
        results[1][1] = button5.getText();
        results[1][2] = button6.getText();
        results[2][0] = button7.getText();
        results[2][1] = button8.getText();
        results[2][2] = button9.getText();


        // Sprawdzenie rządów poziomo
        for (int r = 0; r < 3; r++) {
            if (!results[r][0].isEmpty() && results[r][0].equals(results[r][1]) && results[r][0].equals(results[r][2])) {
                return true;
            }
        }
        //sprawdzecie rzedu poziomo
        for (int c = 0; c < 3; c++) {
            if (!results[0][c].isEmpty() && results[0][c].equals(results[1][c]) && results[0][c].equals(results[2][c])) {
                return true;
            }
        }

        // Sprawdzenie przekątnych
        if (!results[0][0].isEmpty() && results[0][0].equals(results[1][1]) && results[0][0].equals(results[2][2])) {
            return true;
        }

        if (!results[0][2].isEmpty() && results[0][2].equals(results[1][1]) && results[0][2].equals(results[2][0])) {
            return true;
        }
        return false;
    }


    private boolean checkDraw() {
        ArrayList<Button> buttonList = this.buttons;

        for (int i = 0; i < buttonList.size(); ++i) {
            Button button = buttonList.get(i);

            if (button.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }
    private void showAlert(String messageAboutWinner) {
        String nameOfWinner = " ";
        String nameOfLoser = " ";
        String score;
        if (messageAboutWinner.equals("Wygrana!")) {
            if (currentPlayer == 1) {
                nameOfWinner = player2S;
                nameOfLoser = player1S;

            } else {
                nameOfWinner = player1S;
                nameOfLoser = player2S;
            }
            score = "wygrana";
            if (messageAboutWinner.equals("Remis!")) {
                score = "remis";
                nameOfLoser = " ";
                nameOfWinner = " ";

            }
        }
        // Zapis do pliku
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("wyniki.txt", true))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String timestamp = LocalDateTime.now().format(formatter);
            String result = String.format("%s - Wygrana: %s, Przegrana: %s, Ruchy: %d%n",
                    timestamp, nameOfWinner, nameOfLoser, moves);
            writer.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Alert alertMessage = new Alert(Alert.AlertType.INFORMATION);
        alertMessage.setTitle("Message");
        alertMessage.setHeaderText(null);
        if (messageAboutWinner.equals("Wygrana!")) {
            alertMessage.setContentText(messageAboutWinner + " Wygrał: " + nameOfWinner);
        } else if (messageAboutWinner.equals("Remis!")) {
            alertMessage.setContentText(messageAboutWinner);
        }

        alertMessage.showAndWait();
    }



}