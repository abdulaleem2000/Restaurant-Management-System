package sample;



import database.DbHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperReport;
import orders.GenerateOrderid;
import orders.Showorder;
import recipe.Recipe;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller {

    private Stage appStage;
    private Scene appScene;
    private Parent appRoot;

    @FXML
    private TextField empName;
    @FXML
    private TextField empJob;
    @FXML
    private TextField empAddress;
    @FXML
    private TextField empCnic;
    @FXML
    private TextField empShift;
    @FXML
    private TextField empId;
    @FXML
    private TextField remEmpId;
    @FXML
    private TextField editEmpText;

    @FXML
    private RadioButton rName, rCnic, rAddress, rShift, rJob;
    @FXML
    private TextField chngNameText, chngShiftText, chngJobText, chngCnicText, chngAddressText;
    private static String idEdit;
    @FXML
    private TextField attendanceId;
    @FXML
    private DatePicker attDate;

    //recipe
    @FXML
    private TextField idRecipe, nameRecipe, priceRecipeText;
    @FXML
    private TextField removeRecipe;

    @FXML
    private TextField editRecipe;

    @FXML
    private RadioButton chRecName;
    @FXML
    private RadioButton chgRecId, chRecPrice;
    @FXML
    private static String recEditId;
    @FXML
    private TextField chngRecNameText, chngRecIdText, chngRecPriceText;
    static int newOrderId;
    @FXML
    TextField recIdOrder, quantityOrder;
    private static int totalPrice;
    @FXML
    private TextField totalPriceOrder, cash, returnCash;
    private static int intTotal;
    @FXML
    private TextField or1, or2, or3, or4, p1, p2, p3, p4, q1, q2, q3, q4, orderidText, totalOrder;

    public void editEmployee(ActionEvent event) throws IOException, SQLException {

        Employee emp = new Employee();
        idEdit = editEmpText.getText();

        if (emp.editEmployee(editEmpText)) {
            switchToNotFound(event);
        }
        switchToRadioEmployee(event);
    }

    public void addEmployee(ActionEvent event) throws IOException {

        Employee emp = new Employee();

        if (emp.addEmployee(empName, empAddress, empCnic, empShift, empJob, empId)) {
            switchToDataExist(event);
        }
        switchToEmployeeMenu(event);
    }

    public void removeEmployee(ActionEvent event) throws IOException {
        Employee emp = new Employee();
        if (emp.removeEmployee(remEmpId)) {
            switchToNotFound(event);
        }

        switchToRemoveEmployee(event);
    }

    public void changeEmployee(ActionEvent event) throws IOException, SQLException {

        if (rAddress.isSelected()) {
            switchToChangeAddress(event);

        } else if (rName.isSelected()) {
            switchToChangeName(event);

        } else if (rCnic.isSelected()) {
            switchToChangeCnic(event);

        } else if (rJob.isSelected()) {
            switchToChangeJob(event);

        } else if (rShift.isSelected()) {
            switchToChangeShift(event);

        }
    }

    public void editAddress(ActionEvent event) throws SQLException, IOException {
        String address;

        address = chngAddressText.getText();
        DbHandler.changeAddress(address, idEdit);
        switchToRadioEmployee(event);

    }

    public void editCnic(ActionEvent event) throws SQLException, IOException {
        String cnic;
        cnic = chngCnicText.getText();
        DbHandler.changeCnic(cnic, idEdit);
        switchToRadioEmployee(event);
    }

    public void editName(ActionEvent event) throws SQLException, IOException {
        String name;
        name = chngNameText.getText();
        DbHandler.changeName(name, idEdit);
        switchToRadioEmployee(event);
    }

    public void editJob(ActionEvent event) throws SQLException, IOException {
        String job;
        job = chngJobText.getText();
        DbHandler.changeJob(job, idEdit);
        switchToRadioEmployee(event);
    }

    public void editShift(ActionEvent event) throws SQLException, IOException {
        String shift;
        shift = chngShiftText.getText();
        DbHandler.changeShift(shift, idEdit);
        switchToRadioEmployee(event);
    }

    public void switchToAttendance(ActionEvent event) throws IOException {
        appRoot = FXMLLoader.load(getClass().getResource("attendance.fxml"));
        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appScene = new Scene(appRoot);
        appStage.setScene(appScene);
        appStage.show();
    }

    public void switchToMarkAttendance(ActionEvent event) throws IOException {
        appRoot = FXMLLoader.load(getClass().getResource("markattendance.fxml"));
        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appScene = new Scene(appRoot);
        appStage.setScene(appScene);
        appStage.show();
    }

    public void switchToMainMenu(ActionEvent event) throws IOException {
        appRoot = FXMLLoader.load(getClass().getResource("sample.fxml"));
        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appScene = new Scene(appRoot);
        appStage.setScene(appScene);
        appStage.show();
    }

    public void switchToEmployeeMenu(ActionEvent event) throws IOException {
        appRoot = FXMLLoader.load(getClass().getResource("employee.fxml"));
        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appScene = new Scene(appRoot);
        appStage.setScene(appScene);
        appStage.show();
    }

    public void switchToAddEmployee(ActionEvent event) throws IOException {
        appRoot = FXMLLoader.load(getClass().getResource("addemployee.fxml"));
        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appScene = new Scene(appRoot);
        appStage.setScene(appScene);
        appStage.show();
    }

    public void switchToRadioEmployee(ActionEvent event) throws IOException {
        appRoot = FXMLLoader.load(getClass().getResource("radioemployee.fxml"));
        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appScene = new Scene(appRoot);
        appStage.setScene(appScene);
        appStage.show();
    }

    public void switchToDataExist(ActionEvent event) throws IOException {
        appRoot = FXMLLoader.load(getClass().getResource("data_exist.fxml"));
        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appScene = new Scene(appRoot);
        appStage.setScene(appScene);
        appStage.show();
    }

    public void switchToRemoveEmployee(ActionEvent event) throws IOException {
        appRoot = FXMLLoader.load(getClass().getResource("removeemployee.fxml"));
        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appScene = new Scene(appRoot);
        appStage.setScene(appScene);
        appStage.show();
    }

    public void switchToNotFound(ActionEvent event) throws IOException {
        appRoot = FXMLLoader.load(getClass().getResource("not_found.fxml"));
        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appScene = new Scene(appRoot);
        appStage.setScene(appScene);
        appStage.show();
    }

    public void switchToEditEmployee(ActionEvent event) throws IOException {
        appRoot = FXMLLoader.load(getClass().getResource("editemployee.fxml"));
        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appScene = new Scene(appRoot);
        appStage.setScene(appScene);
        appStage.show();
    }

    public void switchToRecipeMenu(ActionEvent event) throws IOException {
        appRoot = FXMLLoader.load(getClass().getResource("recipe.fxml"));
        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appScene = new Scene(appRoot);
        appStage.setScene(appScene);
        appStage.show();
    }

    public void switchToAddRecipe(ActionEvent event) throws IOException {
        appRoot = FXMLLoader.load(getClass().getResource("addrecipe.fxml"));
        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appScene = new Scene(appRoot);
        appStage.setScene(appScene);
        appStage.show();
    }

    public void switchToRemoveRecipe(ActionEvent event) throws IOException {
        appRoot = FXMLLoader.load(getClass().getResource("removerecipe.fxml"));
        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appScene = new Scene(appRoot);
        appStage.setScene(appScene);
        appStage.show();
    }

    public void switchToEditRecipe(ActionEvent event) throws IOException {
        appRoot = FXMLLoader.load(getClass().getResource("editrecipe.fxml"));
        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appScene = new Scene(appRoot);
        appStage.setScene(appScene);
        appStage.show();
    }

    public void switchToShowEmployee(ActionEvent event) throws IOException {
        appRoot = FXMLLoader.load(getClass().getResource("showemployee.fxml"));
        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appScene = new Scene(appRoot);
        appStage.setScene(appScene);
        appStage.show();
    }

    //************************* employee edit portion   *******************************
//functions to switch to choice box options scenes
    public void switchToChangeName(ActionEvent event) throws IOException {
        appRoot = FXMLLoader.load(getClass().getResource("changename.fxml"));
        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appScene = new Scene(appRoot);
        appStage.setScene(appScene);
        appStage.show();
    }

    public void switchToChangeCnic(ActionEvent event) throws IOException {
        appRoot = FXMLLoader.load(getClass().getResource("changecnic.fxml"));
        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appScene = new Scene(appRoot);
        appStage.setScene(appScene);
        appStage.show();
    }

    public void switchToChangeAddress(ActionEvent event) throws IOException {
        appRoot = FXMLLoader.load(getClass().getResource("changeaddress.fxml"));
        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appScene = new Scene(appRoot);
        appStage.setScene(appScene);
        appStage.show();
    }

    public void switchToChangeJob(ActionEvent event) throws IOException {
        appRoot = FXMLLoader.load(getClass().getResource("changejob.fxml"));
        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appScene = new Scene(appRoot);
        appStage.setScene(appScene);
        appStage.show();
    }

    public void switchToChangeShift(ActionEvent event) throws IOException {
        appRoot = FXMLLoader.load(getClass().getResource("changeshift.fxml"));
        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appScene = new Scene(appRoot);
        appStage.setScene(appScene);
        appStage.show();
    }

    //*****************attendance****************************
    public void markPresent(ActionEvent event) throws IOException, SQLException {
        String attId = attendanceId.getText();
        String date = ((TextField) attDate.getEditor()).getText();
        DbHandler db = new DbHandler();
        if (db.checkEmpId(attId)) {
            switchToNotFound(event);
        } else {
            try {
                DbHandler.present(attId, date);
                switchToMarkAttendance(event);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void markAbsent(ActionEvent event) throws IOException, SQLException {
        String attId = attendanceId.getText();
        String date = ((TextField) attDate.getEditor()).getText();
        DbHandler db = new DbHandler();
        if (db.checkEmpId(attId)) {
            switchToNotFound(event);
        } else {
            try {
                DbHandler.absent(attId, date);
                switchToMarkAttendance(event);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }


    //**************recipe methods************************
    public void addRecipe(ActionEvent event) throws IOException, SQLException {
        Recipe add = new Recipe();
        if (add.chkRecipe(idRecipe)) {
            switchToDataExist(event);
        } else {
            add.addRecp(nameRecipe, idRecipe, priceRecipeText);
            switchToAddRecipe(event);
        }

    }

    public void removeRecipe(ActionEvent event) throws IOException, SQLException {
        Recipe rem = new Recipe();
        if (rem.chkRecipe(removeRecipe)) {
            rem.removeRecp(removeRecipe);
            switchToRemoveRecipe(event);

        } else {
            switchToNotFound(event);
        }

    }

    public void editRecipe(ActionEvent event) throws IOException, SQLException {
        Recipe edit = new Recipe();
        recEditId = editRecipe.getText();
        if (edit.chkRecipe(editRecipe)) {
            switchToRadioRecipe(event);
        } else {
            switchToNotFound(event);


        }
    }

    public void changeRecipe(ActionEvent event) throws IOException {
        if (chgRecId.isSelected()) {
            switchToChangeRecId(event);
        }
        if (chRecName.isSelected()) {
            switchToChangeRecName(event);
        }
        if (chRecPrice.isSelected()) {
            switchToChangeRecPrice(event);
        }
    }

    public void changeRecName(ActionEvent event) throws IOException, SQLException {
        String name = chngRecNameText.getText();
        DbHandler.changeRecname(name, recEditId);
        switchToRadioRecipe(event);
    }

    public void changeRecId(ActionEvent event) throws IOException, SQLException {
        String id = chngRecIdText.getText();
        DbHandler.changeRecid(id, recEditId);
        switchToRadioRecipe(event);
    }

    public void changeRecPrice(ActionEvent event) throws IOException, SQLException {
        String price = chngRecPriceText.getText();
        DbHandler.changeRecPrice(price, recEditId);
        switchToRadioRecipe(event);
    }

    public void switchToRadioRecipe(ActionEvent event) throws IOException {
        appRoot = FXMLLoader.load(getClass().getResource("radiorecipe.fxml"));
        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appScene = new Scene(appRoot);
        appStage.setScene(appScene);
        appStage.show();
    }

    public void switchToChangeRecName(ActionEvent event) throws IOException {
        appRoot = FXMLLoader.load(getClass().getResource("changerecname.fxml"));
        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appScene = new Scene(appRoot);
        appStage.setScene(appScene);
        appStage.show();
    }

    public void switchToChangeRecId(ActionEvent event) throws IOException {
        appRoot = FXMLLoader.load(getClass().getResource("changerecid.fxml"));
        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appScene = new Scene(appRoot);
        appStage.setScene(appScene);
        appStage.show();
    }

    public void switchToChangeRecPrice(ActionEvent event) throws IOException {
        appRoot = FXMLLoader.load(getClass().getResource("changerecprice.fxml"));
        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appScene = new Scene(appRoot);
        appStage.setScene(appScene);
        appStage.show();
    }

    public void switchToShowRecipe(ActionEvent event) throws IOException {
        appRoot = FXMLLoader.load(getClass().getResource("showrecipe.fxml"));
        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appScene = new Scene(appRoot);
        appStage.setScene(appScene);
        appStage.show();
    }


    //************************** order methods ****************************************
    public void SwitchToOrders(ActionEvent event) throws IOException {
        appRoot = FXMLLoader.load(getClass().getResource("orders.fxml"));
        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appScene = new Scene(appRoot);
        appStage.setScene(appScene);
        appStage.show();
    }

    public void SwitchToTakeOrders(ActionEvent event) throws IOException {
        appRoot = FXMLLoader.load(getClass().getResource("takeorders.fxml"));
        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appScene = new Scene(appRoot);
        appStage.setScene(appScene);
        appStage.show();
    }

    public void SwitchToShowOrders(ActionEvent event) throws IOException {
        appRoot = FXMLLoader.load(getClass().getResource("showorder.fxml"));
        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appScene = new Scene(appRoot);
        appStage.setScene(appScene);
        appStage.show();
    }

    public void orderId(ActionEvent event) throws IOException, SQLException {
        DbHandler db = new DbHandler();
        int lastOrder = db.readOrderId();
        lastOrder++;
        GenerateOrderid gnt = new GenerateOrderid(lastOrder);

        newOrderId = gnt.returnNewOrderId();
        db.addOrderId(newOrderId);
        System.out.println(newOrderId);
        SwitchToTakeOrders(event);
    }

    public void moreItems(ActionEvent event) throws IOException, SQLException {
        String recId = recIdOrder.getText();

        int quantityRec = Integer.parseInt(quantityOrder.getText());
        DbHandler db = new DbHandler();
        totalPrice += db.addMoreItems(newOrderId, quantityRec, recId);
        System.out.println(totalPrice);
        totalPrice *= quantityRec;

        String p = Integer.toString(totalPrice);

        totalPriceOrder.setText(p + "");
        recIdOrder.setText("");
        quantityOrder.setText("");
    }


    public void placeOrder(ActionEvent event) throws IOException, SQLException {


        String csh = cash.getText();
        int intCash = Integer.parseInt(csh);
        String total = totalPriceOrder.getText();
        intTotal = Integer.parseInt(total);
        int returnC = intCash - intTotal;
        String totalSt = Integer.toString(returnC);
        returnCash.setText(totalSt + "");


    }

    public void switchToReceipt(ActionEvent event) throws IOException, SQLException {
        appRoot = FXMLLoader.load(getClass().getResource("reciept.fxml"));
        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appScene = new Scene(appRoot);
        appStage.setScene(appScene);
        appStage.show();
        generateReceipt(intTotal);
    }

    public void generateReceipt(int total) throws SQLException, IOException {

        String sTotal = Integer.toString(total);
        Statement stmt = DbHandler.con.createStatement();
        String read = "Select * FROM rmsdb.order_no";

        ResultSet rs = stmt.executeQuery(read);
        String id = "";

        while (rs.next()) {
            id = rs.getString("idorder_no");
        }
        String query = "SELECT * FROM rmsdb.order WHERE idorder=" + id;
        System.out.println(query);
        rs = stmt.executeQuery(query);
        while (rs.next()) {

           // id1 = rs.getString("idrecipe");
            //quantity1 = rs.getString("quantity");
            //price1 = rs.getString("orderprice");
            if(or1.getText().isEmpty())
            {
                or1.setText(rs.getString("idrecipe")+"");
             }
            else if(or2.getText().isEmpty())
            {
                or2.setText(rs.getString("idrecipe")+"");
            }
            else if(or3.getText().isEmpty())
            {
                or3.setText(rs.getString("idrecipe")+"");
            }
            else if(or4.getText().isEmpty())
            {
                or4.setText(rs.getString("idrecipe")+"");
            }



            if(p1.getText().isEmpty())
            {
                p1.setText(rs.getString("orderprice")+"");
            }
            else if(p2.getText().isEmpty())
            {
                p2.setText(rs.getString("orderprice")+"");
            }
            else if(p3.getText().isEmpty())
            {
                p3.setText(rs.getString("orderprice")+"");
            }
            else if(p4.getText().isEmpty())
            {
                p4.setText(rs.getString("orderprice")+"");
            }




            if(q1.getText().isEmpty())
            {
                q1.setText(rs.getString("quantity")+"");
            }
            else if(q2.getText().isEmpty())
            {
                q2.setText(rs.getString("quantity")+"");
            }
            else if(q3.getText().isEmpty())
            {
                q3.setText(rs.getString("quantity")+"");
            }
            else if(q4.getText().isEmpty())
            {
                q4.setText(rs.getString("quantity")+"");
            }


        }
            totalOrder.setText(sTotal+"");
            stmt.close();
        }
    public void switchToShowAtt(ActionEvent event) throws IOException, SQLException {
        appRoot = FXMLLoader.load(getClass().getResource("showattend.fxml"));
        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appScene = new Scene(appRoot);
        appStage.setScene(appScene);
        appStage.show();
        generateReceipt(intTotal);
    }

    }


