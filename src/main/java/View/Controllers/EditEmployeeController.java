package View.Controllers;

import DatabaseService.entities.entities.Employee;
import DatabaseService.entities.entities.Shop;
import View.Model.ShopModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Created by sergey on 03.05.17.
 */
public class EditEmployeeController extends Controller{


    @FXML
    public TextField name;
    @FXML
    public TextField surname;
    @FXML
    public TextField birthDate;
    @FXML
    public TextField salary;
    @FXML
    public ChoiceBox<String> shop;
    @FXML
    public Button okButton;

    private Employee employee = new Employee();
    private boolean isOk = false;
    private Stage stage;

    public EditEmployeeController() {
    }

    @FXML
    private void initialize() {

        ObservableList<String> observavble = FXCollections.observableArrayList();
        List<Shop> shops = service.findAll(Shop.class);
        for(Shop shop : shops) {
            String model = shop.getId().toString()+" "+shop.getAddress();
            observavble.add(model);
        }
        this.shop.setItems(observavble);
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;

        this.birthDate.setText(employee.getBirthdate());
        this.name.setText(employee.getName());
        this.salary.setText(employee.getSalary());
        this.surname.setText(employee.getSurname());
        if(employee.getShop() == null){

        }
        else
            this.shop.setValue(employee.getId()+" "+employee.getShop().getAddress());

    }

    public boolean isOk() {
        return isOk;
    }

    public EditEmployeeController setOk(boolean ok) {
        isOk = ok;
        return this;
    }

    public Stage getStage() {
        return stage;
    }

    public EditEmployeeController setStage(Stage stage) {
        this.stage = stage;
        return this;
    }

    @FXML
    private void handleOk() {
        employee.setName(name.getText());
        employee.setSurname(surname.getText());
        employee.setBirthdate(birthDate.getText());
        employee.setSalary(salary.getText());

        Shop found = (Shop) service.findById(Shop.class, (int)(shop.getValue().charAt(0))-48);
        employee.setShop(found);

        this.stage.close();
        isOk = true;
    }

}
