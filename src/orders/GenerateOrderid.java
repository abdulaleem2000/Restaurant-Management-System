package orders;

public class GenerateOrderid {
    private int orderId;

    public GenerateOrderid(int lastOrder) {
        this.orderId = lastOrder;
    }

    public int returnNewOrderId() {
        return orderId;
    }
}
