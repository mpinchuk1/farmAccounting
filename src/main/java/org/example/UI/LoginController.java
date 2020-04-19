package org.example.UI;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.example.utils.EmUtil;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    private static final String ADMIN = "admin";
    private static final String PASSWORD = "password";

    @FXML
    void initialize() {
        loginButton.setDefaultButton(true);
        loginButton.setOnAction(actionEvent -> {
            String login = loginField.getText().trim();
            String pass = passwordField.getText().trim();

            if (login.equals("") && pass.equals("")) {
                loginButton.getScene().getWindow().hide();

                try {
                    App.setRoot("main");
                    Parent root = App.loadFXML("main");
                    Stage stage = new Stage();
                    stage.setTitle("Farm Accounting");
                    stage.setResizable(false);
                    stage.setScene(new Scene(root));
                    stage.showAndWait();

                    EmUtil util = new EmUtil();
                    stage.setOnCloseRequest(windowEvent -> {
                        util.close();
                        Platform.exit();
                        System.exit(0);
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                if (!login.equals("") && !pass.equals("")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);

                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong login or/and pass!");

                    alert.showAndWait();
                }
            }

        });
    }

}

