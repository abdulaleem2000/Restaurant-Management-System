package sample;

import javafx.beans.property.SimpleStringProperty;

public class Attendanceinfo {
    private final SimpleStringProperty dateCol;
    private final SimpleStringProperty attendanceCol;
    private final SimpleStringProperty idemployeeCol;
    public Attendanceinfo(String date, String attendance, String idEmployee) {
        this.dateCol = new SimpleStringProperty(date);
        this.attendanceCol = new SimpleStringProperty(attendance);
        this.idemployeeCol = new SimpleStringProperty(idEmployee);

    }
    public String  getdateCol(){
        return dateCol.get();
    }
    public String  getattCol(){
        return attendanceCol.get();
    }
    public String  getidCol(){
        return idemployeeCol.get();
    }
    public void setdateCol(String id){
        dateCol.set(id);
    }
    public void setAttendanceCol(String id){
        attendanceCol.set(id);
    }
    public void idempCol(String id){
        idemployeeCol.set(id);
    }
}
