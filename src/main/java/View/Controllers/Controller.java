package View.Controllers;

import DatabaseService.entities.DatabaseService;
import View.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by sergey on 02.05.17.
 */
public class Controller {



    protected DatabaseService service = new DatabaseService();
    protected Main main;

    @FXML
    private AnchorPane purchaseAnchorPane;

    @FXML
    private AnchorPane storageAnchorPane;

    @FXML
    private AnchorPane recordsAnchorPane;

    @FXML
    private AnchorPane customersAnchorPane;

    @FXML
    private AnchorPane shopsAnchorPane;

    @FXML
    private AnchorPane cardsAnchorPane;

    @FXML
    private AnchorPane cardholdersAnchorPane;

    @FXML
    private AnchorPane employeesAnchorPane;

    @FXML
    private void initialize() {
        try {
            recordsAnchorPane.getChildren().add(FXMLLoader.load(getClass().getClassLoader().getResource("records.fxml")));
            customersAnchorPane.getChildren().add(FXMLLoader.load(getClass().getClassLoader().getResource("customers.fxml")));
            shopsAnchorPane.getChildren().add(FXMLLoader.load(getClass().getClassLoader().getResource("shops.fxml")));
            cardsAnchorPane.getChildren().add(FXMLLoader.load(getClass().getClassLoader().getResource("cards.fxml")));
            employeesAnchorPane.getChildren().add(FXMLLoader.load(getClass().getClassLoader().getResource("Employees.fxml")));
            cardholdersAnchorPane.getChildren().add(FXMLLoader.load(getClass().getClassLoader().getResource("cardholders.fxml")));
            storageAnchorPane.getChildren().add(FXMLLoader.load(getClass().getClassLoader().getResource("Storage.fxml")));
            purchaseAnchorPane.getChildren().add(FXMLLoader.load(getClass().getClassLoader().getResource("purchases.fxml")));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
