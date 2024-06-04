/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package specialist.logic;


/**
 *
 * @author Yousuf
 */
public class SpcList {
    private int spcId;
    private String  spcName;
    private String spcAddress;
    private String spcPhone;
    private String spcEmail;
    private Integer noOutstandingParts;
    private Integer noOutstandingVehicles;
    
    public SpcList(int SpcId, String SpcName, String SpcAddress, String SpcPhone, String SpcEmail, Integer noOutstandingParts, Integer noOutstandingVehicles){
        this.spcAddress= SpcAddress;
        this.spcId=SpcId;
        this.spcName=SpcName;
        this.spcPhone=SpcPhone;
        this.spcEmail=SpcEmail;
        this.noOutstandingParts=noOutstandingParts;
        this.noOutstandingVehicles=noOutstandingVehicles;
        
    }

    public SpcList(int spcId, String spcName, String spcAddress, String spcPhone, String spcEmail) {
        this.spcId = spcId;
        this.spcName = spcName;
        this.spcAddress = spcAddress;
        this.spcPhone = spcPhone;
        this.spcEmail = spcEmail;
    }
    
    
    
    public int getSpcId() {
        return spcId;
    }

    

    public String getSpcName() {
        return spcName;
    }

    

    public String getSpcAddress() {
        return spcAddress;
    }

    

    public String getSpcPhone() {
        return spcPhone;
    }

    

    public String getSpcEmail() {
        return spcEmail;
    }

    public Integer getNoOutstandingParts()
    {
        return noOutstandingParts;
    }
    
    public Integer getNoOutstandingVehicles()
    {
        return noOutstandingVehicles;
    }
}
