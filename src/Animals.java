import java.util.ArrayList;
import java.util.List;

public class Animals {
	protected int animalID;
    protected String name;
	protected String species;
    protected String birthdate;
    protected double adoptionPrice;
    protected int ownerID;
    
    public List<Review> review = new ArrayList<Review>();
    
    public int getReviewSize() {
    	return review.size();
    }
    
    public String getReviewersName(int index) {
    	return review.get(index).getReviewersName();
    }
    
    public int getReviewID(int index) {
    	return review.get(index).getReviewID();
    }
    
    public Animals() {
    }
    
    public Animals(int id) {
        this.animalID = id;
    }
 
    public Animals(int animalID, String name, String species, String birthdate, double adoptionPrice, int ownerID) {
        this.name = name;
        this.species = species;
        this.birthdate = birthdate;
        this.adoptionPrice = adoptionPrice;
        this.ownerID = ownerID;
        this.animalID = animalID;
    }
    
    public Animals(String name, String species, String birthdate, double adoptionPrice, int ownerID) {
    	this.name = name;
        this.species = species;
        this.birthdate = birthdate;
        this.adoptionPrice = adoptionPrice;
        this.ownerID = ownerID;
  }
 
    @Override
	public String toString() {
		return "Animal [=" + animalID + ", name=" + name + ", species=" + species + ", birthdate="
				+ birthdate + ", adoption price=" + adoptionPrice + "]";
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
     
    public int getOwner() {
    	return ownerID;
    }
    
    public void setOwner(int ownerID) {
    	this.ownerID = ownerID; 
    }   
}