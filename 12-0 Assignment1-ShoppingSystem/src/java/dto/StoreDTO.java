package dto;

import java.io.Serializable;

/**
 *
 * @author Kris
 */
public class StoreDTO implements Serializable {

    private int storeID;
    private String storeName;
    private String storeAddress;
    private String storeTelephoneNumber;
    private int storeManagerID;

    public StoreDTO(int storeID, String storeName, String storeAddress, String storeTelephoneNumber, int storeManagerID) {
        this.storeID = storeID;
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.storeTelephoneNumber = storeTelephoneNumber;
        this.storeManagerID = storeManagerID;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStoreTelephoneNumber() {
        return storeTelephoneNumber;
    }

    public void setStoreTelephoneNumber(String storeTelephoneNumber) {
        this.storeTelephoneNumber = storeTelephoneNumber;
    }

    public int getStoreManagerID() {
        return storeManagerID;
    }

    public void setStoreManagerID(int storeManagerID) {
        this.storeManagerID = storeManagerID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null
                || !(obj instanceof StoreDTO))
        {
            return false;
        }
        
        if (obj == this)
        {
            return true;
        }
        
        StoreDTO store = (StoreDTO)obj;
        return this.storeID == store.getStoreID() 
                && this.storeName.equals(store.getStoreName());
    }

    @Override
    public int hashCode() {
        return storeName.hashCode() + storeID;
    }

    @Override
    public String toString() {
        return storeName;
    }

}
