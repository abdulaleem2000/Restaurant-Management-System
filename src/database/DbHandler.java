package database;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Controller;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbHandler {


   public static Connection con;

    public static void createConnection() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rmsdb","abdulaleem","1234");
        System.out.println("connection successful!");



    }
    public static boolean addEmployeeToDataBase(String name,String id,String address,String cnic,String job,String shift) throws SQLException {
       // System.out.println(name);
        Statement stmt = con.createStatement();
        String search = "SELECT * FROM employee WHERE idemployee='"+id+"'";
        ResultSet rs = stmt.executeQuery(search);

        if(rs.next())
        {
            return true;
        }
        else
        {
            String emp = "INSERT INTO employee (idemployee,employee_name,employee_shift,employee_cnic,employee_address,employee_job)values('" +id+"','"+ name + "','"+shift+"','"+cnic+"','"+address+"','"+job+"')";
            stmt.execute(emp);
            stmt.close();

        }

        return false;

    }
    public boolean removeFromDataBase(String id) throws SQLException, IOException {
        Statement stmt = con.createStatement();
        String search = "SELECT * FROM employee WHERE idemployee='"+id+"'";


        ResultSet rs = stmt.executeQuery(search);
        //checking if data exit in database or not
        if(rs.next())
        {
            String remEmp = "DELETE FROM employee WHERE idemployee='"+id+"'";
            stmt.execute(remEmp);
            stmt.close();
        }
        else
        {
            return true;
        }

        return false;
    }
    public static void present(String id, String date) throws SQLException{
        Statement stmt = con.createStatement();
        String attendance = "present";

        String att = "INSERT INTO attendance (date,Attendancecol,idemployee)values('" +date+"','"+ attendance + "','"+id+"')";
        System.out.println(att);
        stmt.execute(att);
        stmt.close();

    }
    public static void absent(String id, String date) throws SQLException{
        Statement stmt = con.createStatement();
        String attendance = "absent";

        String att = "INSERT INTO attendance (date,Attendancecol,idemployee)values('" +date+"','"+ attendance + "','"+id+"')";
        System.out.println(att);
        stmt.execute(att);
        stmt.close();

    }

//******************recipe database***********************
    public boolean checkRecId(String id) throws SQLException, IOException {
        Statement stmt = con.createStatement();
        String search = "SELECT * FROM recipe WHERE idrecipe='"+id+"'";


        ResultSet rs = stmt.executeQuery(search);
        //checking if data exit in database or not
        if(rs.next())
        {
            stmt.close();
            return true;
            //to check if data exists in database or not
        }
        else
        {


        }
        stmt.close();
        return false;
    }
    public void insertRecipe(String name,String id,String price) throws SQLException {
        Statement stmt = con.createStatement();
        String insrt = "INSERT INTO recipe (idrecipe,recipename,recipeprice)values('" +id+"','"+ name + "','"+price+"')";
        stmt.execute(insrt);
        stmt.close();
    }

    public void deleteRecipe(String id) throws SQLException {
        Statement stmt = con.createStatement();
        String delete = "DELETE FROM recipe WHERE idrecipe='"+id+"'";
        stmt.execute(delete);
        stmt.close();
    }

    public static void changeRecname(String name,String id) throws SQLException {
        Statement stmt = con.createStatement();
        String update = "Update recipe Set recipename='" +name+ "' WHERE idrecipe='" +id+ "'";
        stmt.execute(update);
        stmt.close();
    }
    public static void changeRecid(String editId,String id) throws SQLException {
        Statement stmt = con.createStatement();
        String update = "Update recipe Set idrecipe='" +editId+ "' WHERE idrecipe='" +id+ "'";
        System.out.println(update);
        stmt.execute(update);
        stmt.close();
    }
    public static void changeRecPrice(String price,String id) throws SQLException {
        Statement stmt = con.createStatement();
        int pr= Integer.parseInt(price);
        String update = "Update recipe Set recipeprice='" +pr+ "' WHERE idrecipe='" +id+ "'";
        System.out.println(update);
        stmt.execute(update);
        stmt.close();
    }
    //**************orders***************************
    public int readOrderId() throws SQLException {
        Statement stmt = con.createStatement();
        String read = "SELECT * FROM order_no";
        ResultSet rs = stmt.executeQuery(read);
        int lastOrder=0;
        while(rs.next())
        {
            lastOrder= Integer.parseInt(rs.getString("idorder_no"));
        }

        stmt.close();
        return lastOrder;
    }
    public void addOrderId(int orderId) throws SQLException {
        Statement stmt = con.createStatement();
        String read = "INSERT INTO order_no (idorder_no)values('" +orderId+"')";
        stmt.execute(read);

        stmt.close();
    }
    public int addMoreItems(int orderId, int quantity, String recipeId) throws SQLException {
        Statement stmt = con.createStatement();
        Statement stmt1 = con.createStatement();
        String query = "SELECT recipeprice FROM recipe WHERE idrecipe='"+recipeId+"'";
        System.out.println(query);
        ResultSet rs = stmt.executeQuery(query);
        String oId = Integer.toString(orderId);
        String quan = Integer.toString(quantity);
        String getPrice="";
        if(rs.next())
        {
            getPrice = rs.getString("recipeprice");
        }
        System.out.println(getPrice);
        int price = Integer.parseInt(getPrice);
        String read = "INSERT INTO rmsdb.order (idorder,idrecipe,quantity,orderprice)values(" +oId+",'"+ recipeId + "',"+quan+","+getPrice+")";
        System.out.println(read);
        stmt1.execute(read);
        stmt.close();
        stmt1.close();
        return  price;
    }


















//*************************edit database*********************
//methods for editng editing employee
    public boolean checkEmpId(String id) throws SQLException, IOException {
        Statement stmt = con.createStatement();
        String search = "SELECT * FROM employee WHERE idemployee='"+id+"'";


        ResultSet rs = stmt.executeQuery(search);
        //checking if data exit in database or not
        if(rs.next())
        {
          //to check if data exists in database or not
        }
        else
        {
            stmt.close();
            return true;
        }
        stmt.close();
        return false;
    }
    public static void changeName(String name,String id) throws SQLException {
        Statement stmt = con.createStatement();
        String update = "Update employee Set employee_name='" +name+ "' WHERE idemployee='" +id+ "'";
        stmt.execute(update);
        stmt.close();
    }
    public static void changeAddress(String address,String id) throws SQLException {
        Statement stmt = con.createStatement();

        String update = "Update employee Set employee_address='" +address+ "' WHERE idemployee='" +id+ "'";
        System.out.println(update);
        stmt.execute(update);
        stmt.close();
    }
    public static void changeCnic(String cnic,String id) throws SQLException {
        Statement stmt = con.createStatement();
        String update = "Update employee Set employee_cnic='" +cnic+ "' WHERE idemployee='" +id+ "'";
        stmt.execute(update);
        stmt.close();
    }
    public static void changeJob(String job,String id) throws SQLException {
        Statement stmt = con.createStatement();

        String update = "Update employee Set employee_job='" +job+ "' WHERE idemployee='" +id+ "'";
        stmt.execute(update);
        stmt.close();
    }
    public static void changeShift(String shift,String id) throws SQLException {
        Statement stmt = con.createStatement();
        String update = "Update employee Set employee_shift='" +shift+ "' WHERE idemployee='" +id+ "'";
        stmt.execute(update);
        stmt.close();
    }


}
