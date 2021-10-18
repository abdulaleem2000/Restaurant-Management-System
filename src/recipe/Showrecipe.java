package recipe;

import database.DbHandler;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.Controller;
import sample.EmployeeInfo;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Showrecipe {
    @FXML
    private TableView<Recipeinfo> recipeTable;
    @FXML

    private TableColumn<Recipeinfo,String> nameCol;
    @FXML
    private TableColumn<Recipeinfo,String> idCol;
    @FXML
    private TableColumn<Recipeinfo,String> priceCol;

    Recipeinfo recipeInfo;

    Statement statement = null;
    //Object which holds the query results
    ResultSet rs = null;

    @FXML public void initialize()
    {
//This is essential if u use JavaFX 8 to prevent blank columns
        recipeTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    public void search(javafx.event.ActionEvent event) {
        final ObservableList<Recipeinfo> results = FXCollections.observableArrayList();
        //Assign columns to data
        idCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getidCol()));

        nameCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getnameCol()));

        priceCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getpriceCol()));

        //Start databse stuff
        String query = "SELECT * FROM recipe";
        try {

            //DbHandler.createConnection();
            statement = DbHandler.con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                //Assign results to the list

                recipeInfo = new Recipeinfo(rs.getString("idrecipe"), rs.getString("recipename"), rs.getString("recipeprice"));
                results.add(recipeInfo);
            }
            recipeTable.setItems(results);
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
        cont.switchToRecipeMenu(event);
    }
}
