package View.Controllers;

import DatabaseService.entities.entities.Litem;
import DatabaseService.entities.entities.Pitem;
import DatabaseService.entities.entities.Purchase;
import View.Model.PitemModel;
import View.Model.PurchaseModel;
import com.itextpdf.text.DocumentException;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.PdfHelper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class PurchaseController extends Controller {

    ObservableList<PurchaseModel> purchaseModels = FXCollections.observableArrayList();
    ObservableList<PitemModel> pitemModels = FXCollections.observableArrayList();

    @FXML
    public Button createReportButton;
    @FXML
    private TableView<PitemModel> itemsTableView;
    @FXML
    private TableColumn<PitemModel,Integer> itemId;
    @FXML
    private TableColumn<PitemModel, String> itemArtist;
    @FXML
    private TableColumn<PitemModel,String> itemAlbum;
    @FXML
    private TableColumn<PitemModel, String> itemAmount;
    @FXML
    private MenuItem updatePurchase;
    @FXML
    private MenuItem deletePurchase;
    @FXML
    private Button addItemButton;
    @FXML
    private Button addPurchaseButton;
    @FXML
    private MenuItem updateItem;
    @FXML
    private MenuItem DeleteItem;
    @FXML
    private ContextMenu purchasesMenu;
    @FXML
    private ContextMenu itemsMenu;
    @FXML
    private TableView<PurchaseModel> purchaseModelTableView;

    @FXML
    private TableColumn<PurchaseModel, Integer> id;

    @FXML
    private TableColumn<PurchaseModel, String> customer;

    @FXML
    private TableColumn<PurchaseModel, String> date;

    @FXML
    private TableColumn<PurchaseModel, String> shop;


    private void refreshData() {
        purchaseModels.clear();
        List<Purchase> list = service.findAll(Purchase.class);

        for(Purchase purchase : list) {
            PurchaseModel model = new PurchaseModel(purchase);
            purchaseModels.add(model);
        }
        purchaseModelTableView.setItems(purchaseModels);
    }

    @FXML
    private void initialize() {
        purchaseModelTableView.setEditable(true);

        id.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getId()));
        customer.setCellValueFactory(cellData -> cellData.getValue().customerNameProperty());
        date.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        shop.setCellValueFactory(cellData -> cellData.getValue().shopAddressProperty());

        itemAlbum.setCellValueFactory(cellData -> cellData.getValue().albumProperty());
        itemArtist.setCellValueFactory(cellData -> cellData.getValue().artistProperty());
        itemAmount.setCellValueFactory(cellData -> cellData.getValue().amountProperty());
        itemId.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getId()));

        purchaseModelTableView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) ->
                        setItemsToList(newValue));

        refreshData();
    }

    private void setItemsToList(PurchaseModel purchase) {
        pitemModels.clear();

        if(purchase!=null) {
            Purchase found = (Purchase) service.findById(Purchase.class, purchase.getId());

            List<Pitem> list = service.findPitemsInPurchase(found);

            for (Pitem pitem : list) {
                PitemModel model = new PitemModel(pitem);
                pitemModels.add(model);
                itemsTableView.setItems(pitemModels);
            }
        }
    }

    @FXML
    private void handleAddPurchase() {
        try {
            EditPurchaseController controller = showEditPurchaseDialog(new Purchase());
            if(controller.isOk()){
                service.persist(controller.getPurchase());
                refreshData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDeletePurchase() {
        PurchaseModel model = purchaseModelTableView.getSelectionModel().getSelectedItem();
        Purchase deleted = (Purchase) service.findById(Purchase.class, model.getId());
        service.delete(deleted);
        refreshData();
    }

    @FXML
    private void handleUpdatePurchase() {
        PurchaseModel model = purchaseModelTableView.getSelectionModel().getSelectedItem();
        Purchase updated = (Purchase) service.findById(Purchase.class, model.getId());

        try {
            EditPurchaseController controller = showEditPurchaseDialog(updated);
            if(controller.isOk()) {
                Purchase newPurchase = controller.getPurchase();
                updated.setCustomer(newPurchase.getCustomer());
                updated.setDate(newPurchase.getDate());
                updated.setShop(newPurchase.getShop());
                service.merge(updated);
                refreshData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private EditPurchaseController showEditPurchaseDialog(Purchase purchase) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("editPurchaseDialog.fxml"));
        AnchorPane pane = loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Add/Edit card");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(pane);
        dialogStage.setScene(scene);

        EditPurchaseController controller = loader.getController();
        controller.setPurchase(purchase);
        controller.setStage(dialogStage);
        dialogStage.showAndWait();

        return controller;
    }

    private EditPitemController showEditPitemDialog(Pitem pitem) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("editPitemDialog.fxml"));
        AnchorPane pane = loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Add/Edit purchased items");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(pane);
        dialogStage.setScene(scene);

        EditPitemController controller = loader.getController();
        controller.setPitem(pitem);
        controller.setStage(dialogStage);

        Purchase purchase = (Purchase) service.findById(Purchase.class,
                purchaseModelTableView.getSelectionModel().getSelectedItem().getId());
        controller.setPurchase(purchase);
        dialogStage.showAndWait();

        return controller;
    }

    @FXML
    private void handleAddItem(ActionEvent actionEvent) {
        try {
            EditPitemController controller = showEditPitemDialog(new Pitem());
            if(controller.isOk()){
                service.persist(controller.getPitem());
                setItemsToList(purchaseModelTableView.getSelectionModel().getSelectedItem());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleUpdateItem(ActionEvent actionEvent) {

    }

    @FXML
    private void handleDeleteItem(ActionEvent actionEvent) {
        PitemModel model = itemsTableView.getSelectionModel().getSelectedItem();
        Integer id = model.getLitem().getId();
        Litem litem = model.getLitem();
        litem.setAmount(String.valueOf(Integer.valueOf(litem.getAmount())+Integer.valueOf(model.getAmount())));
        service.merge(litem);
        Pitem deleted = (Pitem) service.findById(Pitem.class, model.getId());
        service.delete(deleted);
        setItemsToList(purchaseModelTableView.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void handleReportCreating(ActionEvent actionEvent) {
        PdfHelper helper = new PdfHelper(service);
        try {
            helper.createPdf();
            Alert createdAlert = new Alert(Alert.AlertType.INFORMATION);
            createdAlert.setHeaderText("Pdf file created");
            createdAlert.showAndWait();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
