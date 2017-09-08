package View.Controllers;

import DatabaseService.entities.entities.Record;
import DatabaseService.entities.entities.Shop;
import View.Model.ShopModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * Created by sergey on 02.05.17.
 */
public class ShopController extends Controller {

    ObservableList<ShopModel> shopModels = FXCollections.observableArrayList();

    @FXML
    private TableView<ShopModel> shopModelTableView;

    @FXML
    private TableColumn<ShopModel, Integer> id;

    @FXML
    private TableColumn<ShopModel, String> address;

    @FXML
    private TableColumn<ShopModel, String> phone;

    @FXML
    private void initialize() {
        id.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getId()));
        address.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        phone.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());

        refreshData();
    }

    private void getData() {
        List<Shop> list = service.findAll(Shop.class);

        for (Shop shop : list) {
            ShopModel model = new ShopModel(shop);
            this.shopModels.add(model);
        }
    }

    private void refreshData() {
        this.shopModels.clear();
        getData();
        this.shopModelTableView.setItems(shopModels);
    }

    @FXML
    private void handleDelete() {
        ShopModel selected = shopModelTableView.getSelectionModel().getSelectedItem();
        Integer id = selected.getId();

        Shop shop = (Shop) service.findById(Shop.class, id);

        service.delete(shop);

        refreshData();
    }

    @FXML
    private void handleAdd() {
        try {
            EditShopController controller = showEditDialog(new Shop());

            if(controller.isOk()) {
                service.persist(controller.getShop());
                refreshData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleUpdate() {
        ShopModel shopModel = shopModelTableView.getSelectionModel().getSelectedItem();
        Integer id = shopModel.getId();

        Shop updated = (Shop) service.findById(Shop.class, id);

        try {
            EditShopController controller = showEditDialog(updated);
            if(controller.isOk()) {
                Shop newShop = controller.getShop();
                updated.setTelephone(newShop.getTelephone());
                updated.setAddress(newShop.getAddress());

                service.merge(updated);
                refreshData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private EditShopController showEditDialog(Shop shop) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("editShopDialog.fxml"));
        AnchorPane pane = loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Add/Edit shop");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(pane);
        dialogStage.setScene(scene);

        EditShopController controller = loader.getController();
        controller.setShop(shop);
        controller.setStage(dialogStage);
        dialogStage.showAndWait();

        return controller;
    }
}
