package Model;

import java.util.List;

/**
 *
 * @author jefferson
 */
public class Buy {
   private String id;
   private double totalPrice;
   private String date;
   private boolean authorized;
   private int clientId;
   private String addressId;
   private List<BuyProduct> listBuyProduct;
   private Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }
    
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public List<BuyProduct> getListBuyProduct() {
        return listBuyProduct;
    }

    public void setListBuyProduct(List<BuyProduct> listBuyProduct) {
        this.listBuyProduct = listBuyProduct;
    }
}
