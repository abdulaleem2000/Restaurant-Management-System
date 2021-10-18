package sample;

import javafx.beans.property.SimpleStringProperty;

public class EmployeeInfo {
    private final SimpleStringProperty colId;
    private final SimpleStringProperty colName;
    private final SimpleStringProperty colAddress;
    private final SimpleStringProperty colCnic;
    private final SimpleStringProperty colShift;
    private final SimpleStringProperty colJob;

    public EmployeeInfo(String id, String name, String address, String shift, String job,String cnic){
        this.colId = new SimpleStringProperty(id);
        this.colName = new SimpleStringProperty(name);
        this.colAddress = new SimpleStringProperty(address);
        this.colShift = new SimpleStringProperty(shift);
        this.colJob = new SimpleStringProperty(job);
        this.colCnic = new SimpleStringProperty(cnic);
    }



    public String  getidCol(){
        return colId.get();
    }
    public void setidCol(String id){
        colId.set(id);
    }
    public String getnameCol(){
        return colName.get();
    }
    public void setnameCol(String name){
        colName.set(name);
    }
    public String getaddressCol(){
        return colAddress.get();
    }
    public void setaddressCol(String address){
        colAddress.set(address);
    }
    public String getjobCol(){
        return colJob.get();
    }
    public void setjobCol(String job){
        colJob.set(job);
    }
    public String getShiftCol(){
        return colShift.get();
    }
    public void setShiftCol(String shift){
        colShift.set(shift);
    }
    public String getCnicCol(){
        return colCnic.get();
    }
    public void setColCnic(String cnic){
        colCnic.set(cnic);
    }
}
