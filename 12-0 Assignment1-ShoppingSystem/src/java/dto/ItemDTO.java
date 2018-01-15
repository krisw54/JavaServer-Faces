package dto;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.UUID;

/**
 *
 * @author Kris
 */
public class ItemDTO implements Serializable {

    private int itemID;
    private String itemName;
    private String itemDesc;
    private String barcodeNo;
    private double price;
    private String image;
    private int stockLevel;
    private String uid;
    private int qtyReq;
    private int storeID;
    

    public ItemDTO() {
    }

    public ItemDTO(String id) {
        this.uid = id;
    }

    public ItemDTO(int itemID, String itemName, double price, int qtyReq)
    {
        this.itemID = itemID;
        this.itemName = itemName;
        this.price = price;
        this.qtyReq = qtyReq;
        this.uid = String.valueOf(UUID.randomUUID());
    }
    

    public ItemDTO(int itemID, String itemName, String itemDesc, String barcodeNo, String image, double price) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        this.barcodeNo = barcodeNo;
        this.image = image;
        this.price = price;
        this.qtyReq = 0;
        this.uid = String.valueOf(UUID.randomUUID());
    }

    public ItemDTO(int itemID, String itemName, String itemDesc, String barcodeNo, String image, double price, int stockLevel) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        this.barcodeNo = barcodeNo;
        this.price = price;
        this.image = image;
        this.stockLevel = stockLevel;
        this.qtyReq = 0;
        this.uid = String.valueOf(UUID.randomUUID());//
    }

    public ItemDTO(int itemID, String itemName, String itemDesc, String barcodeNo, String image, double price, int stockLevel, int storeID)
    {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        this.barcodeNo = barcodeNo;
        this.price = price;
        this.image = image;
        this.stockLevel = stockLevel;
        this.storeID = storeID;
        this.uid = String.valueOf(UUID.randomUUID());
    }
   

    public int getStoreID()
    {
        return storeID;
    }

    public void setStoreID(int storeID)
    {
        this.storeID = storeID;
    }
    
    public int getQtyReq() {
        return qtyReq;
    }

    public void setQtyReq(int qtyReq) {
        this.qtyReq = qtyReq;
    }
    

    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }

    public int getItemID() {
        return itemID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        DecimalFormat df = new DecimalFormat("#.00");
        String theprice = df.format(price);
        return  Double.parseDouble(theprice);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getBarcodeNo() {
        return barcodeNo;
    }

    public void setBarcodeNo(String barcodeNo) {
        this.barcodeNo = barcodeNo;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    
        @Override
    public boolean equals(Object obj) {
        if (obj == null
                || !(obj instanceof ItemDTO))
        {
            return false;
        }
        
        if (obj == this)
        {
            return true;
        }
        
        ItemDTO item = (ItemDTO)obj;
        return this.itemID == item.getItemID()
                && this.itemName.equals(item.getItemName());
    }

    @Override
    public int hashCode() {
        return itemName.hashCode() + itemID;
    }
    
    

}
