package orders;

import database.DbHandler;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import recipe.Recipeinfo;
import sample.Controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Showorder {
    @FXML
    private TableView<Orderinfo> orderTableView;
    @FXML

    private TableColumn<Orderinfo,String> orderidCol;
    @FXML
    private TableColumn<Orderinfo,String> recipeidCol;
    @FXML
    private TableColumn<Orderinfo,String> quantityorderCol;
    @FXML
    private TableColumn<Orderinfo,String> priceorderCol;

    Orderinfo orderInfo;

    Statement statement = null;
    //Object which holds the query results
    ResultSet rs = null;

    @FXML public void initialize()
    {
//This is essential if u use JavaFX 8 to prevent blank columns
        orderTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }
    public void search(javafx.event.ActionEvent event) {
        final ObservableList<Orderinfo> results = FXCollections.observableArrayList();
        //Assign columns to data
        orderidCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getorderidCol()));

        recipeidCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getrecipeidCol()));

        priceorderCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPriceorderCol()));
        quantityorderCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getquantityorderCol()));
        //Start databse stuff
        String query = "SELECT * FROM rmsdb.order";
        try {

            //DbHandler.createConnection();
            statement = DbHandler.con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                //Assign results to the list

                orderInfo= new  Orderinfo(rs.getString("idorder"), rs.getString("idrecipe"), rs.getString("quantity"), rs.getString("orderprice"));
                results.add(orderInfo);
            }
            orderTableView.setItems(results);
        } catch (SQLException e) {

        } finally {

            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
    public void SwitchToOrderMenu(ActionEvent event) throws IOException {
        Controller cont = new Controller();
        cont.SwitchToOrders(event);
    }
}
