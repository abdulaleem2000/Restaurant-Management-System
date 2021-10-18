package sample;
import database.DbHandler;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Showemployee {
    @FXML
    private TableView<EmployeeInfo> employeeTable;
    @FXML

    private TableColumn<EmployeeInfo,String> nameCol;
    @FXML
    private TableColumn<EmployeeInfo,String> jobCol;
    @FXML
    private TableColumn<EmployeeInfo,String> idCol;
    @FXML
    private TableColumn<EmployeeInfo,String> cnicCol;
    @FXML
    private TableColumn<EmployeeInfo,String> addressCol;
    @FXML
    private TableColumn<EmployeeInfo,String> shiftCol;

    EmployeeInfo employeeInfo;


    Statement statement = null;
    //Object which holds the query results
    ResultSet rs = null;
    @FXML public void initialize()
    {
//This is essential if u use JavaFX 8 to prevent blank columns
        employeeTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    //ObservableList to automatically update the


    public void search(javafx.event.ActionEvent event) {
        final ObservableList<EmployeeInfo> results = FXCollections.observableArrayList();
        //Assign columns to data
        idCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getidCol()));
        shiftCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getShiftCol()));
        nameCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getnameCol()));
        jobCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getjobCol()));
        cnicCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getCnicCol()));
        addressCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getaddressCol()));
        //Start databse stuff
        String query = "SELECT * FROM employee";
        try {

            //DbHandler.createConnection();
            statement = DbHandler.con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                //Assign results to the list

                employeeInfo = new EmployeeInfo(rs.getString("idemployee"), rs.getString("employee_name"), rs.getString("employee_address"), rs.getString("employee_shift"), rs.getString("employee_job"), rs.getString("employee_cnic"));
                results.add(employeeInfo);
            }
            employeeTable.setItems(results);
        } catch (SQLException e) {

        } finally {

            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    public void switchToEmployeeMenu(ActionEvent event) throws IOException {
        Controller cont = new Controller();
        cont.switchToEmployeeMenu(event);
    }
}
