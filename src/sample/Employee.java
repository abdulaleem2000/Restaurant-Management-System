package sample;

import database.DbHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class Employee {


    private String name,address,cnic,shift,job,id;
    public boolean addEmployee(TextField eName, TextField eAddress, TextField eCnic, TextField eShift, TextField eJob, TextField eId ) throws RuntimeException
    {
        try {
            name = eName.getText();
            address = eAddress.getText();
            cnic = eCnic.getText();
            shift = eShift.getText();
            job = eJob.getText();
            id = eId.getText();


            if(DbHandler.addEmployeeToDataBase(name,id,address,cnic,job,shift))
            {
                return true;
            }
            //System.out.println(cnic);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return false;
    }
    public boolean removeEmployee(TextField eid)
    {
        try {
            id = eid.getText();
            DbHandler db = new DbHandler();
            if(db.removeFromDataBase(id))
            {
                return true;
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return false;
    }

    public boolean editEmployee(TextField eid) throws IOException, SQLException {

            id=eid.getText();
            DbHandler db = new DbHandler();
            if(db.checkEmpId(id))
            {
                return true;
            }


        return false;
    }


}
