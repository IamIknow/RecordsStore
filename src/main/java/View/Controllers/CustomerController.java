package View.Controllers;

import DatabaseService.entities.entities.Customer;
import DatabaseService.entities.entities.Record;
import View.Model.CustomerModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * Created by sergey on 02.05.17.
 */
public class CustomerController extends Controller{

    private ObservableList<CustomerModel> customerModels = FXCollections.observableArrayList();

    @FXML
    private TableView<CustomerModel> customerModelTableView;

    @FXML
    private TableColumn<CustomerModel, Integer> id;

    @FXML
    private TableColumn<CustomerModel, String> name;

    @FXML
    private TableColumn<CustomerModel, String> email;

    @FXML
    private Button addButton;

    @FXML
    private ContextMenu menu;

    @FXML
    private MenuItem updateItem;

    @FXML
    private MenuItem deleteItem;

    @FXML
    private void initialize() {

        id.setCellValueFactory(cellData-> new SimpleObjectProperty<Integer>(cellData.getValue().getId()));
        name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        email.setCellValueFactory(cellData -> cellData.getValue().emailProperty());

        refreshData();
    }


    private void refreshData() {
        this.customerModels.clear();
        getData();
        customerModelTableView.setItems(customerModels);
    }


    private void getData() {
        List<Customer> list = service.findAll(Customer.class);

        for(Customer customer : list) {
            CustomerModel model = new CustomerModel(customer);
            customerModels.add(model);
        }
    }

    private EditCustomerController showEditDialog(Customer customer) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("editCustomerDialog.fxml"));
        AnchorPane pane = loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Add/Edit Customer");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(pane);
        dialogStage.setScene(scene);

        EditCustomerController controller = loader.getController();
        controller.setCustomer(customer);
        controller.setStage(dialogStage);
        dialogStage.showAndWait();

        return controller;
    }

    @FXML
    private void handleDelete() {
        CustomerModel selected = this.customerModelTableView.getSelectionModel().getSelectedItem();
        Integer id = selected.getId();

        Customer customer = (Customer) service.findById(Customer.class, id);

        service.delete(customer);
        refreshData();
    }

    @FXML
    private void handleAdd() {
        try {
            EditCustomerController controller = showEditDialog(new Customer());
            if(controller.isOk()){
                service.persist(controller.getCustomer());
                refreshData();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleUpdate() {
        CustomerModel selected = this.customerModelTableView.getSelectionModel().getSelectedItem();
        Integer id = selected.getId();

        Customer customer = (Customer) service.findById(Customer.class, id);

        try {
            EditCustomerController controller = showEditDialog(customer);
            if(controller.isOk()) {
                Customer newCustomer = controller.getCustomer();
                customer.setEmail(newCustomer.getEmail())
                        .setName(newCustomer.getName());
                service.merge(customer);
                refreshData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
