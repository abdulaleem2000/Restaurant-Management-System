package orders;

import javafx.beans.property.SimpleStringProperty;

public class Orderinfo {
    private final SimpleStringProperty orderidCol;
    private final SimpleStringProperty recipeidCol;
    private final SimpleStringProperty quantityorderCol;
    private final SimpleStringProperty priceorderCol;

    public Orderinfo(String orderId, String recipeId, String quantityOrder, String priceOrder) {
        this.orderidCol = new SimpleStringProperty(orderId);
        this.recipeidCol = new SimpleStringProperty(recipeId);
        this.quantityorderCol = new SimpleStringProperty(quantityOrder);
        this.priceorderCol = new SimpleStringProperty(priceOrder);
    }

    public String  getorderidCol(){
        return orderidCol.get();
    }
    public void setorderidCol(String id){
        orderidCol.set(id);
    }
    public String getrecipeidCol(){
        return recipeidCol.get();
    }
    public void setRecipeidCol(String name){
        recipeidCol.set(name);
    }
    public String getquantityorderCol(){
        return quantityorderCol.get();
    }
    public void setQuantityorderCol(String price){
        quantityorderCol.set(price);
    }
    public String getPriceorderCol(){
        return priceorderCol.get();
    }
    public void setPriceorderCol(String price){
        priceorderCol.set(price);
    }
}
