

public class Animals {
	protected int animalID;
    protected String name;
	protected String species;
    protected String birthdate;
    protected double adoptionPrice;
    protected String traits; //how do we want to implement this? Array of strings?? -AE
    protected int ownerID;
 
    public Animals() {
    	
    }
    
    public Animals(int id) {
        this.animalID = id;
    }
 
    public Animals(int animalID, String name, String species, String birthdate, double adoptionPrice, String traits, int ownerID) {
        this.name = name;
        this.species = species;
        this.birthdate = birthdate;
        this.adoptionPrice = adoptionPrice;
        this.traits = traits;
        this.ownerID = ownerID;
        this.animalID = animalID;
    }
    
    public Animals(String name, String species, String birthdate, double adoptionPrice, String traits, int ownerID) {
    	this.name = name;
        this.species = species;
        this.birthdate = birthdate;
        this.adoptionPrice = adoptionPrice;
        this.traits = traits;
        this.ownerID = ownerID;
  }
 
    @Override
	public String toString() {
		return "People [=" + animalID + ", name=" + name + ", species=" + species + ", birthdate="
				+ birthdate + ", adoption price=" + adoptionPrice + ", traits=" + traits + "]";
	}
    
    public int getId() {
    	return animalID;
    }

	public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getSpecies() {
        return species;
    }
 
    public void setSpecies(String species) {
        this.species = species;
    }
    
    public String getBirthdate() {
        return birthdate;
    }
 
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
    
    public double getAdoptionPrice() {
        return adoptionPrice;
    }
 
    public void setAdoptionPrice(double adoptionPrice) {
        this.adoptionPrice = adoptionPrice;
    }
    
    public String getTraits() {
        return traits;
    }
 
    public void setTraits(String traits) {
        this.traits = traits;
    }
 
    public int getOwner() {
    	return ownerID;
    }
    
    public void setOwner(int ownerID) {
    	this.ownerID = ownerID; 
    }
    
   
}