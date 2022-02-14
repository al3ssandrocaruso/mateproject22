package kapta.utils.bean;

//POJO
public class InfoEvent {

    private String address;
    private double price;
    private int obb;

    public InfoEvent(String address, Double price, int obb){
        setAddress(address);
        setObb(obb);
        setPrice(price);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getObb() {
        return obb;
    }

    public void setObb(int obb) {
        this.obb = obb;
    }
}
