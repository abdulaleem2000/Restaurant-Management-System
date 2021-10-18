package sample;

import database.DbHandler;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import orders.Orderinfo;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Showattend {
    @FXML
    private TableView<Attendanceinfo> attView;
    @FXML

    private TableColumn<Attendanceinfo,String> datecolAtt;
    @FXML
    private TableColumn<Attendanceinfo,String> attCol;
    @FXML
    private TableColumn<Attendanceinfo,String> idattCol;

    Attendanceinfo attendanceinfo;

    Statement statement = null;
    //Object which holds the query results
    ResultSet rs = null;

    @FXML public void initialize()
    {
//This is essential if u use JavaFX 8 to prevent blank columns
        attView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }
    public void search(javafx.event.ActionEvent event) {
        final ObservableList<Attendanceinfo> results = FXCollections.observableArrayList();
        //Assign columns to data
        datecolAtt.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getdateCol()));

        attCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getattCol()));

        idattCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getidCol()));

        //Start databse stuff
        String query = "SELECT * FROM rmsdb.attendance";
        try {

            //DbHandler.createConnection();
            statement = DbHandler.con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                //Assign results to the list

                attendanceinfo= new Attendanceinfo(rs.getString("date"), rs.getString("attendancecol"), rs.getString("idemployee"));
                results.add(attendanceinfo);
            }
            attView.setItems(results);
        } catch (SQLException e) {

        } finally {

            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
    public void SwitchToAttendance(ActionEvent event) throws IOException {
        Controller cont = new Controller();
        cont.switchToAttendance(event);
    }
}
