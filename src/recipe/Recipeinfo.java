package recipe;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Recipeinfo {
    private final SimpleStringProperty colId;
    private final SimpleStringProperty colName;
    private final SimpleStringProperty colPrice;

    public Recipeinfo(String id, String name, String price) {
        this.colId = new SimpleStringProperty(id);
        this.colName = new SimpleStringProperty(name);
        this.colPrice = new SimpleStringProperty(price);
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
    public String getpriceCol(){
        return colPrice.get();
    }
    public void setpriceCol(String price){
        colPrice.set(price);
    }
}
