package vehicles.logic;
public class Warranty {
//Instance Variables declaration
	private String companyName;
	private String companyAddress;
	private String expirationDate;
         //Constructor
	public Warranty(){

	}
         //Constructor
	public Warranty(String companyName, String companyAddress, String expirationDate ) {
		this.companyName = companyName;
		this.companyAddress = companyAddress;
		this.expirationDate = expirationDate;
	}

        //GETTER AND SETTER METHOD OF INSTANCE VARIABLES
        
	public String getCompanyName() {

		return this.companyName;
	}

	public String getCompanyAddress() {

		return this.companyAddress;
	}

	public String getExpirationDate() {
		return this.expirationDate;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

}