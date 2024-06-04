/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts.logic;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Wajid
 */

// class to get parts info 
public class parts {
    
    private final SimpleIntegerProperty ID;
    private final SimpleStringProperty Name;
    private final SimpleStringProperty Desc;
    private final SimpleIntegerProperty Stock;
    private final SimpleDoubleProperty Cost;
    private final SimpleIntegerProperty PartsWithdrawn;
    private final SimpleIntegerProperty PartsAdded;
    
    public parts(int ID, String Name, String Desc, int Stock, double Cost, int PartsWithdrawn, int PartsAdded){
        this.ID = new SimpleIntegerProperty(ID);
        this.Name = new SimpleStringProperty(Name);
        this.Desc = new SimpleStringProperty(Desc);
        this.Stock = new SimpleIntegerProperty(Stock);
        this.Cost = new SimpleDoubleProperty(Cost);
        this.PartsWithdrawn = new SimpleIntegerProperty(PartsWithdrawn);
        this.PartsAdded = new SimpleIntegerProperty(PartsAdded);
    
    }
    
    public int getID() {
        return ID.get();
    }
    
    public void setID(int ID1) {
        ID.set(ID1);
        
    }
    
    public String getName() {
        return Name.get();
    }
    
    public void setName(String Name1) {
        Name.set(Name1);
        
    }
    
        public String getDesc() {
        return Desc.get();
    }
    
    public void setDesc(String Desc1) {
        Desc.set(Desc1);
        
    }
        public int getStock() {
        return Stock.get();
    }
    
    public void setStock(int Stock1) {
        Stock.set(Stock1);
        
    }
        public double getCost() {
        return Cost.get();
    }
    
    public void setCost(int Cost1) {
        Cost.set(Cost1);
        
    }
    
    
    public int getPartsWithdrawn() {
        return PartsWithdrawn.get();
    }
    
    public int getPartsAdded() {
        return PartsAdded.get();
    }

}
    


