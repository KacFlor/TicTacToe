package com.example.kolkokrzyzyk;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.application.Platform;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Game4x4Controller {
    @FXML
    private Label N1;
    @FXML
    private Label N2;
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
    private Button button10;
    @FXML
    private Button button11;
    @FXML
    private Button button12;
    @FXML
    private Button button13;
    @FXML
    private Button button14;
    @FXML
    private Button button15;
    @FXML
    private Button button16;
    private String N1S;
    private String N2S;
    private int C1;
    private int C2;
    private int currentPlayer = 1;
    private int movesCount = 0;
    @FXML
    private Button[] buttons;
    public void setC1(int c1) {
        C1 = c1;
    }
    public void setC2(int c2) {
        C2 = c2;
    }
    public void setN1S(String n1S) {
        N1S = n1S;
    }
    public void setN2S(String n2S) {
        N2S = n2S;
    }
    public void updateLabels() {
        N1.setText(N1S);
        N2.setText(N2S);
        if (C1 == 1) {
            N1.setTextFill(Color.RED);
        }
        if (C1 == 2) {
            N1.setTextFill(Color.BLUE);
        }
        if (C1 == 3) {
            N1.setTextFill(Color.BLACK);
        }
        if (C1 == 4) {
            N1.setTextFill(Color.YELLOW);
        }
        if (C1 == 5) {
            N1.setTextFill(Color.GREEN);
        }
        if (C2 == 1) {
            N2.setTextFill(Color.RED);
        }
        if (C2 == 2) {
            N2.setTextFill(Color.BLUE);
        }
        if (C2 == 3) {
            N2.setTextFill(Color.BLACK);
        }
        if (C2 == 4) {
            N2.setTextFill(Color.YELLOW);
        }
        if (C2 == 5) {
            N2.setTextFill(Color.GREEN);
        }
    }
    @FXML
    public void initialize() {
        updateLabels();
        currentPlayer = 1;
        buttons = new Button[]{button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12, button13, button14, button15, button16};
    }
    @FXML
    public void handleButtonClick(ActionEvent event) {
        movesCount++;
        Button clickedButton = (Button) event.getSource();
        if (currentPlayer == 1) {
            clickedButton.setText("X");
            if (C1 == 1) {
                clickedButton.setTextFill(Color.RED);
            }
            if (C1 == 2) {
                clickedButton.setTextFill(Color.BLUE);
            }
            if (C1 == 3) {
                clickedButton.setTextFill(Color.BLACK);
            }
            if (C1 == 4) {
                clickedButton.setTextFill(Color.YELLOW);
            }
            if (C1 == 5) {
                clickedButton.setTextFill(Color.GREEN);
            }
            clickedButton.setDisable(true);
            currentPlayer = 2;
        } else if (currentPlayer == 2) {
            clickedButton.setText("O");
            if (C2 == 1) {
                clickedButton.setTextFill(Color.RED);
            }
            if (C2 == 2) {
                clickedButton.setTextFill(Color.BLUE);
            }
            if (C2 == 3) {
                clickedButton.setTextFill(Color.BLACK);
            }
            if (C2 == 4) {
                clickedButton.setTextFill(Color.YELLOW);
            }
            if (C2 == 5) {
                clickedButton.setTextFill(Color.GREEN);
            }
            clickedButton.setDisable(true);
            currentPlayer = 1;
        }
        if (checkWin()) {
            showResultDialog("Wygrana!");
            Platform.exit();
        } else if (checkDraw()) {
            showResultDialog("Remis!");
            Platform.exit();
        }
    }
    private boolean checkWin() {
        String[][] marks = new String[4][4];

        marks[0][0] = button1.getText();
        marks[0][1] = button2.getText();
        marks[0][2] = button3.getText();
        marks[0][3] = button4.getText();
        marks[1][0] = button5.getText();
        marks[1][1] = button6.getText();
        marks[1][2] = button7.getText();
        marks[1][3] = button8.getText();
        marks[2][0] = button9.getText();
        marks[2][1] = button10.getText();
        marks[2][2] = button11.getText();
        marks[2][3] = button12.getText();
        marks[3][0] = button13.getText();
        marks[3][1] = button14.getText();
        marks[3][2] = button15.getText();
        marks[3][3] = button16.getText();

        for (int row = 0; row < 4; row++) {
            if (!marks[row][0].isEmpty() && marks[row][0].equals(marks[row][1]) && marks[row][0].equals(marks[row][2]) && marks[row][0].equals(marks[row][3])) {
                return true;
            }
        }

        for (int col = 0; col < 4; col++) {
            if (!marks[0][col].isEmpty() && marks[0][col].equals(marks[1][col]) && marks[0][col].equals(marks[2][col]) && marks[0][col].equals(marks[3][col])) {
                return true;
            }
        }

        if (!marks[0][0].isEmpty() && marks[0][0].equals(marks[1][1]) && marks[0][0].equals(marks[2][2]) && marks[0][0].equals(marks[3][3])) {
            return true;
        }
        if (!marks[0][3].isEmpty() && marks[0][3].equals(marks[1][2]) && marks[0][3].equals(marks[2][1]) && marks[0][3].equals(marks[3][0])) {
            return true;
        }
        return false;
    }
    private boolean checkDraw() {
        for (Button button : buttons) {
            if (button.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }
    private void showResultDialog(String message) {
        String winnerName = "";
        String loserName = "";
        String result = "";
        if (message.equals("Wygrana!")) {
            if (currentPlayer == 1) {
                winnerName = N2S;
                loserName = N1S;
            } else {
                winnerName = N1S;
                loserName = N2S;
            }
            result = "wygrana";
        } else if (message.equals("Remis!")) {
            winnerName = N1S;
            loserName = N2S;
            result = "remis";
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String dateTime = dtf.format(now);

        String line = dateTime +" ("+ winnerName+" vs "+ loserName + "), " + result;
        if (result.equals("wygrana")) {
            line += " " + winnerName + ", " + "ilość ruchów " + movesCount;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Wyniki4x4.txt", true))) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Wynnie");
        alert.setHeaderText(null);
        if(message.equals("Wygrana!")) alert.setContentText(message + " Wygrał: " + winnerName);
        if(message.equals("Remis!")) alert.setContentText(message);
        alert.showAndWait();
    }
}