
public class Trait {
	protected int traitID;
	protected int animalID;
	protected String trait;
	
	public Trait(int traitID, int animalID, String trait) {
		super();
		this.traitID = traitID;
		this.animalID = animalID;
		this.trait = trait;
	}
	
	public Trait(int animalID, String trait) {
		this.animalID = animalID;
		this.trait = trait;
	}

	@Override
	public String toString() {
		return "Trait [traitID=" + traitID + ", animalID=" + animalID + ", trait=" + trait + "]";
	}

	public int getTraitID() {
		return traitID;
	}
	
	public void setTraitID(int traitID) {
		this.traitID = traitID;
	}
	
	public int getAnimalID() {
		return animalID;
	}
	
	public void setAnimalID(int animalID) {
		this.animalID = animalID;
	}
	
	public String getTrait() {
		return trait;
	}
	
	public void setTrait(String trait) {
		this.trait = trait;
	}
}
