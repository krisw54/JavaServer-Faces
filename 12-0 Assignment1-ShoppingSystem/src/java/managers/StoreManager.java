package managers;

import dbWrappers.ListStores;
import dto.StoreDTO;
import gateway.StoreGateway;
import javax.inject.Named;
import java.io.Serializable;
import helpers.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Kris
 */
@Named(value = "stores")
@SessionScoped
public class StoreManager implements Serializable {
    
    private StoreDTO selectedStore;
    private String selStore;
    private int storeID;
    private ArrayList<StoreDTO> stores = new ArrayList<>();
    private ListStores listStores = new ListStores();
    private StoreGateway sGateway = new StoreGateway();

    public StoreManager() {
        stores = sGateway.find();
    }

    public StoreManager(String selStore) {
        this.selStore = selStore;
    }
    
    public List<StoreDTO> getStore() {
    //List<SelectItem> retVal = new ArrayList<SelectItem>();
    List<StoreDTO> retVal = new ArrayList<>();
    for (int i = 0; i < stores.size(); i++) {
        //retVal.add(new SelectItem(stores.get(i).getStoreName()));
    }
    retVal = stores;
    return retVal;
}
    
     public String go(){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        sessionMap.put("storeID", selStore);
        System.out.println(storeID + selStore + ":" + selStore );
        return "shop";
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public String getSelStore() {
        return selStore;
    }

    public void setSelStore(String selStore) {
        this.selStore = selStore;
    }
    
    public StoreDTO getSelectedStore()
    {
        return selectedStore;
    }
    
    public StoreDTO[] getStores() {
        return (StoreDTO[])stores.toArray();
    }

    public void setStores(ArrayList<StoreDTO> stores) {
        this.stores = stores;
    }

    public ListStores getListStores() {
        return listStores;
    }

    public void setListStores(ListStores listStores) {
        this.listStores = listStores;
    }
    
    public void setSelectedStore(StoreDTO store)
    {
        selectedStore = store;
    }
    
}
