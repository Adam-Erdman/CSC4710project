
public class Review {
	protected int reviewID;
    protected String review;
    protected int reviewScore; 
    protected int animalID;
    protected int ownerID;
    protected String reviewersName;
    protected int reviewCount;
	    
    public Review(int ownerID, int reviewCount) {
    	this.ownerID = ownerID;
    	this.reviewCount = reviewCount;
    }
    
	public Review(int reviewID, String review, int reviewScore, int animalID, int ownerID, String reviewersName) {
		this.reviewID = reviewID;
		this.review = review;
		this.reviewScore = reviewScore;
		this.animalID = animalID;
		this.ownerID = ownerID;
		this.reviewersName = reviewersName;
	}
    
	public Review(int reviewID, String review, int reviewScore, int animalID, int ownerID) {
		this.reviewID = reviewID;
		this.review = review;
		this.reviewScore = reviewScore;
		this.animalID = animalID;
		this.ownerID = ownerID;
	}
	
	public Review(String review, int reviewScore, int animalID, int ownerID) {
		this.review = review;
		this.reviewScore = reviewScore;
		this.animalID = animalID;
		this.ownerID = ownerID;
	}
	
	public int getReviewID() {
		return reviewID;
	}
	
	public void setReviewID(int reviewID) {
		this.reviewID = reviewID;
	}
	
	public String getReview() {
		return review;
	}
	
	public void setReview(String review) {
		this.review = review;
	}
	
	public int getReviewScore() {
		return reviewScore;
	}
	
	public void setReviewScore(int reviewScore) {
		this.reviewScore = reviewScore;
	}
	
	public int getAnimalID() {
		return animalID;
	}
	
	public void setAnimalID(int animalID) {
		this.animalID = animalID;
	}
	
	public int getOwnerID() {
		return ownerID;
	}
	
	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}
	
    public String getReviewersName() {
		return reviewersName;
	}

	public void setReviewersName(String reviewersName) {
		this.reviewersName = reviewersName;
	}
	
	public int getReviewCount()	{
		return reviewCount;
	}
	
	@Override
	public String toString() {
		return "Review [reviewID=" + reviewID + ", review=" + review + ", reviewScore=" + reviewScore + ", animalID="
				+ animalID + ", ownerID=" + ownerID + ", reviewersName=" + reviewersName + "]";
	}
}
