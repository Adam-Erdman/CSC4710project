

public class Animals {
	protected int id;
    protected String name;
	protected String species;
    protected String birthdate;
    protected double adoptionPrice;
    protected String traits; //how do we want to implement this? Array of strings?? -AE
    protected int owner;
 
    public Animals() {
    	
    }
    
    public Animals(int id) {
        this.id = id;
    }
 
    public Animals(int id, String name, String species, String birthdate, double adoptionPrice, String traits, int owner) {
        this.name = name;
        this.species = species;
        this.birthdate = birthdate;
        this.adoptionPrice = adoptionPrice;
        this.traits = traits;
        this.owner = owner;
        this.id = id;
    }
    
    public Animals(String name, String species, String birthdate, double adoptionPrice, String traits, int owner) {
    	this.name = name;
        this.species = species;
        this.birthdate = birthdate;
        this.adoptionPrice = adoptionPrice;
        this.traits = traits;
        this.owner = owner;
  }
 
    @Override
	public String toString() {
		return "People [id=" + id + ", name=" + name + ", species=" + species + ", birthdate="
				+ birthdate + ", adoption price=" + adoptionPrice + ", traits=" + traits + "]";
	}
    
    public int getId() {
    	return id;
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
    	return owner;
    }
    
    public void setOwner(int owner) {
    	this.owner = owner; 
    }
    
   
}