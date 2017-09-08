package View.Controllers;

import DatabaseService.entities.entities.Employee;
import DatabaseService.entities.entities.Record;
import DatabaseService.entities.entities.Shop;
import View.Model.EmployeeModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * Created by sergey on 03.05.17.
 */
public class EmployeeController extends Controller{

    ObservableList<EmployeeModel> employeeModels = FXCollections.observableArrayList();

    @FXML
    public TableView<EmployeeModel> employeesTable;
    @FXML
    public TableColumn<EmployeeModel, Integer> id;
    @FXML
    public TableColumn<EmployeeModel, String> name;
    @FXML
    public TableColumn<EmployeeModel, String> surname;
    @FXML
    public TableColumn<EmployeeModel, String> birthDate;
    @FXML
    public TableColumn<EmployeeModel, String> shop;
    @FXML
    public TableColumn<EmployeeModel, String> salary;
    @FXML
    public MenuItem update;
    @FXML
    public MenuItem delete;
    @FXML
    public Button button;

    @FXML
    private void initialize() {
        this.employeesTable.setEditable(true);

        id.setCellValueFactory(cellData -> new SimpleObjectProperty<Integer>(cellData.getValue().getId()));
        name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        surname.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
        birthDate.setCellValueFactory(cellData -> cellData.getValue().birthDateProperty());
        shop.setCellValueFactory(cellData -> cellData.getValue().shopAddressProperty());
        salary.setCellValueFactory(cellData -> cellData.getValue().salaryProperty());

        refreshData();
    }

    private void refreshData() {
        this.employeeModels.clear();

        List<Employee> list = service.findAll(Employee.class);
        for(Employee employee : list) {
            EmployeeModel model = new EmployeeModel(employee);
            employeeModels.add(model);
        }
        employeesTable.setItems(employeeModels);
    }

    private EditEmployeeController showEditDialog(Employee employee) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("editEmployeeDialog.fxml"));
        AnchorPane pane = loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Add/Edit record");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(pane);
        dialogStage.setScene(scene);

        EditEmployeeController controller = loader.getController();
        controller.setEmployee(employee);
        controller.setStage(dialogStage);
        dialogStage.showAndWait();

        return controller;
    }

    @FXML
    private void handleAdd() {
        try {
            EditEmployeeController controller = showEditDialog(new Employee());
            if(controller.isOk()){
                service.persist(controller.getEmployee());

                refreshData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDelete() {
        EmployeeModel employeeModel = employeesTable.getSelectionModel().getSelectedItem();
        Integer id = employeeModel.getId();
        Employee deleted = (Employee) service.findById(Employee.class, id);

        service.delete(deleted);

        refreshData();
    }

    public void handleUpdate(ActionEvent actionEvent) {
        EmployeeModel employeeModel = employeesTable.getSelectionModel().getSelectedItem();
        Integer id = employeeModel.getId();
        Employee updated = (Employee) service.findById(Employee.class, id);

        try {
            EditEmployeeController controller = showEditDialog(updated);

            if(controller.isOk()){
                Employee newEmployee = controller.getEmployee();
                updated.setShop(newEmployee.getShop());
                updated.setBirthdate(newEmployee.getBirthdate());
                updated.setSalary(newEmployee.getSalary());
                updated.setName(newEmployee.getName());
                updated.setSurname(newEmployee.getSurname());

                service.merge(updated);

                refreshData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
