import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/PeopleDAO")
public class PeopleDAO extends HttpServlet {     
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public PeopleDAO() {
    }
	       
    protected void connect_func() throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://localhost:3306/project?"
  			          + "user=john&password=pass1234&useSSL=false");
            System.out.println(connect);
        }
    }
        
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
         
    public boolean insert(People people) throws SQLException {
    	connect_func();         
		String sql = "insert into  users(username, userpassword, firstname, lastname, emailaddress) values (?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, people.username);
		preparedStatement.setString(2, people.userpassword);
		preparedStatement.setString(3, people.firstname);
		preparedStatement.setString(4, people.lastname);
		preparedStatement.setString(5, people.emailaddress);
//		preparedStatement.executeUpdate();
		
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//        disconnect();
        return rowInserted;
    }     
     
    public boolean delete(int peopleid) throws SQLException {
        String sql1 = "DELETE FROM animals WHERE ownerID = ?";      
        String sql2 = "DELETE FROM users WHERE id = ?";      
        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql1);
        preparedStatement.setInt(1, peopleid);
        preparedStatement.executeUpdate();
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql2);
        preparedStatement.setInt(1, peopleid);
        
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//        disconnect();
        return rowDeleted;     
    }
     
    public boolean update(People people) throws SQLException {
        String sql = "update users set username=?, userpassword =?, firstname = ?, lastname = ?,emailaddress = ? where id = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, people.username);
        preparedStatement.setString(2, people.userpassword);
        preparedStatement.setString(3, people.firstname);
        preparedStatement.setString(4, people.lastname);
        preparedStatement.setString(5, people.emailaddress);
        preparedStatement.setInt(6, people.id);
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//        disconnect();
        return rowUpdated;     
    }
    
    public void wipe() throws SQLException{
		connect_func();
			
		String deleteUserTable = "DROP TABLE IF EXISTS users";
		String createUserTable = "CREATE TABLE IF NOT EXISTS users " +
				"	(id INTEGER not NULL AUTO_INCREMENT, " +
				"	username VARCHAR(50) NOT NULL UNIQUE, " + 
				" 	userpassword VARCHAR(50) NOT NULL, " + 
				" 	firstname VARCHAR(50) NOT NULL, " + 
				" 	lastname VARCHAR(50) NOT NULL, " + 
				" 	emailaddress varchar(50) not NULL UNIQUE," +
				"	PRIMARY KEY ( id ))"; 
		
		String deleteAnimalTable ="DROP TABLE IF EXISTS animals"; //added for part 2 -ae
		String createAnimalTable ="CREATE TABLE IF NOT EXISTS animals " +
				"	(animalID INTEGER not NULL AUTO_INCREMENT, " +
				" 	name VARCHAR(50) NOT NULL, " + 
				" 	species VARCHAR(50) NOT NULL, " + 
				" 	birthdate VARCHAR(50) NOT NULL, " + 
				" 	adoptionPrice FLOAT(50) NOT NULL, " + 
				" 	ownerID INTEGER not NULL," +
				" 	PRIMARY KEY (animalID), " + 
				" 	FOREIGN KEY (ownerID) REFERENCES users(id)" + 
				" 	ON DELETE CASCADE)";
		
		String deleteReviewTable = "DROP TABLE IF EXISTS reviews";
		String createReviewTable = "CREATE TABLE IF NOT EXISTS reviews" + 
				"    (reviewID int not NULL AUTO_INCREMENT, " + 
				"    review VARCHAR(500) NOT NULL, " + 
				"    reviewScore int not NULL, " + 
				"    animalID int not NULL, " + 
				"    ownerID int not NULL, " + 
				"    PRIMARY KEY (ReviewID), " + 
				"    FOREIGN KEY (animalID) REFERENCES animals(animalID), " + 
				"    FOREIGN KEY (ownerID) REFERENCES animals(ownerID) " + 
				"    ON DELETE CASCADE)";
		
		String deleteTraitTable = "DROP TABLE IF EXISTS traits"; 
		String createTraitTable = "CREATE TABLE IF NOT EXISTS traits" + 
				"    (traitID int not NULL AUTO_INCREMENT," + 
				"    animalID int not NULL," + 
				"    trait VARCHAR(50) NOT NULL," + 
				"    PRIMARY KEY (traitID)," + 
				"    FOREIGN KEY (animalID) REFERENCES animals(animalID)" + 
				"    ON DELETE CASCADE)" ;
		
		String deleteFavAnimal = "DROP TABLE IF EXISTS favAnimal";
		String createFavAnimal = "CREATE TABLE IF NOT EXISTS favAnimal " +
				"	(id INTEGER not NULL AUTO_INCREMENT, " +
				"	username VARCHAR(50) NOT NULL, " + 
				"   animalID int not NULL," + 
				"	PRIMARY KEY ( id )," +
				"   UNIQUE (username, animalID))"; 
		
		String deleteFavBreeder = "DROP TABLE IF EXISTS favBreeder";
		String createFavBreeder = "CREATE TABLE IF NOT EXISTS favBreeder " +
				"	(id INTEGER not NULL AUTO_INCREMENT, " +
				"	username VARCHAR(50) NOT NULL, " + 
				" 	ownerID INTEGER not NULL," +
				"	PRIMARY KEY ( id )," +
				"   UNIQUE (username, ownerID))"; 

		//Makes sure there are less than 5 reviews per user
		String reviewTrigger =
				"CREATE TRIGGER lt5Reviews BEFORE INSERT on reviews" + 
				"	FOR EACH ROW BEGIN" + 
				"    SET @reviewCount = 0;" + 
				"	select count(*) INTO @reviewCount from reviews where ownerID = NEW.ownerID;" + 
				"    IF @reviewCount >= 5 THEN" + 
				"		 SIGNAL SQLSTATE '45000'" + 
				"		   SET MESSAGE_TEXT = 'Cannot insert more than 5 reivews', MYSQL_ERRNO = 1000;" + 
				"	END IF;" + 
				"END";
	
		statement = connect.createStatement();
		statement.executeUpdate(deleteReviewTable);
		statement.executeUpdate(deleteTraitTable);
	    statement.executeUpdate(deleteAnimalTable); //added for part 2 -ae
	    statement.executeUpdate(deleteUserTable);
	    statement.executeUpdate(deleteFavAnimal);
	    statement.executeUpdate(deleteFavBreeder);
	    statement.executeUpdate(createUserTable);
	    statement.executeUpdate(createAnimalTable); //added for part 2 -ae
	    statement.executeUpdate(createTraitTable);
	    statement.executeUpdate(createReviewTable);
	    statement.executeUpdate(createFavAnimal);
	    statement.executeUpdate(createFavBreeder);
	    statement.executeUpdate(reviewTrigger);
	    
	    statement.close();
	}	

    public People getUser(int id) throws SQLException {
    	People people = null;
        String sql = "SELECT * FROM users WHERE id = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, id);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
        	String firstname = resultSet.getString("firstname");
            String lastname = resultSet.getString("lastname");
            String emailaddress = resultSet.getString("emailaddress");
            String userpassword = resultSet.getString("userpassword");
            String username = resultSet.getString("username");
             
            people = new People( id, username, userpassword, firstname, lastname, emailaddress);
        }
        
        resultSet.close();
         
        return people;
    }
    
	
	public int getUserId(String username, String password) throws SQLException{
		connect_func();
        String sql = "SELECT id FROM users WHERE username = ? AND userpassword = ?";
                  
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
         
        ResultSet resultSet = preparedStatement.executeQuery();
        
        if (resultSet.next())  {
        	int id = resultSet.getInt("id");
         	return id;
        }
        
        return -1;
	}
	
	public String getUserFullName(int id) throws SQLException {
		connect_func();
        String userFullName = null;      
        String sql = "SELECT firstname, lastname FROM users WHERE id = ?";      

        preparedStatement =  (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
         
        while (resultSet.next()) {
            String firstname = resultSet.getString("firstname");
            String lastname = resultSet.getString("lastname");
            userFullName = firstname + " " + lastname;
        }        
        
        resultSet.close();
        return userFullName;
    }

    
	public Boolean findUser(String username, String userpassword) throws SQLException{
		
		connect_func();
        String sql = "SELECT * FROM users WHERE username = ? AND userpassword = ?";
                  
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, userpassword);
         
        ResultSet resultSet = preparedStatement.executeQuery();
        
        return resultSet.next();
	}

	 public List<People> listAllPeople() throws SQLException {
	        List<People> listPeople = new ArrayList<People>();        
	        String sql = "SELECT * FROM users";      
	        connect_func();      
	        statement =  (Statement) connect.createStatement();
	        ResultSet resultSet = statement.executeQuery(sql);
	         
	        while (resultSet.next()) {
	            int id = resultSet.getInt("id");
	            String username = resultSet.getString("username");
	            String userpassword = resultSet.getString("userpassword");
	            String firstname = resultSet.getString("firstname");
	            String lastname = resultSet.getString("lastname");
	            String emailaddress = resultSet.getString("emailaddress");
	                         
	            People people = new People( id, username, userpassword, firstname, lastname, emailaddress);

	            listPeople.add(people);
	        }        
	        
	        resultSet.close();      
	        return listPeople;
	    }
		
	///////////////////////////////// Begin Animal Registration Form //////////////////////////////////// -ae
    public List<Animals> listAllAnimals() throws SQLException {
        List<Animals> listAnimals = new ArrayList<Animals>();        
        String sql = "SELECT * FROM animals";      
        connect_func();      
        statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int animalID = resultSet.getInt("animalID");
            String name = resultSet.getString("name");
            String species = resultSet.getString("species");
            String birthdate = resultSet.getString("birthdate");
            double adoptionPrice = resultSet.getDouble("adoptionPrice");
            int ownerID = resultSet.getInt("ownerID");
                         
            Animals animals = new Animals(animalID, name, species, birthdate, adoptionPrice, ownerID);

            listAnimals.add(animals);
        }        
        
        resultSet.close();
        statement.close();         
        disconnect();        
        return listAnimals;
    }
    
    public List<String> getTraitNames() throws SQLException {
        List<String> traitNames= new ArrayList<String>();        
        String sql = "SELECT DISTINCT trait from traits";      
        connect_func();      
        statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
                
        while (resultSet.next()) {
            traitNames.add(resultSet.getString("trait"));
        }        
        
        resultSet.close();
        statement.close();         
        disconnect();        
        return traitNames;
    }
    
    public boolean insertTrait(Trait trait) throws SQLException {
    	connect_func();         
		String sql = "insert into traits(animalID, trait) values (?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, trait.getAnimalID());
		preparedStatement.setString(2, trait.getTrait());
		 
        boolean rowInserted = preparedStatement.executeUpdate() > 0;

        preparedStatement.close();
//        disconnect();
        return rowInserted;
    } 
    
    public List<Animals> getAnimalByTrait(String trait) throws SQLException {
        List<Animals> animals = new ArrayList<Animals>();  
        List<Integer> animalIDs = new ArrayList<Integer>();
        connect_func();  
        String sql = "SELECT animalID FROM traits WHERE trait = ?";       
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, trait);

        ResultSet resultSet = preparedStatement.executeQuery();
         
        while (resultSet.next()) {
        	animalIDs.add(resultSet.getInt("animalID"));
        }        
        
        for (Integer animalID : animalIDs) {
			animals.add(getAnimal(animalID));
		}
        
        resultSet.close();
        return animals;
    }
    
     public int insertAnimal(Animals animals) throws SQLException {
    	connect_func();         
		String sql = "insert into  animals(name, species, birthdate, adoptionPrice, ownerID) values (?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, animals.name);
		preparedStatement.setString(2, animals.species);
		preparedStatement.setString(3, animals.birthdate);
		preparedStatement.setDouble(4, animals.adoptionPrice);
		preparedStatement.setInt(5, animals.ownerID);
//		preparedStatement.executeUpdate();
		
		preparedStatement.execute();
		ResultSet key = preparedStatement.getGeneratedKeys();
		int animalID = 0;
		if (key.next()) {
		    animalID = key.getInt(1);
		}
		
        preparedStatement.close();
//        disconnect();
        return animalID;
    }     
     
    public boolean deleteAnimal(int animalID) throws SQLException {
        String sql = "DELETE FROM animals WHERE animalID = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, animalID);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//        disconnect();
        return rowDeleted;     
    }
    
    public List<Animals> searchByOwner(int ownerID) throws SQLException {
        List<Animals> listAnimals = new ArrayList<Animals>();  
        connect_func();  
        String sql = "SELECT * FROM animals WHERE ownerID = ?";       
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, ownerID);

        ResultSet resultSet = preparedStatement.executeQuery();
         
        while (resultSet.next()) {
        	int animalID = resultSet.getInt("animalID");
            String name = resultSet.getString("name");
            String species = resultSet.getString("species");
            String birthdate = resultSet.getString("birthdate");
            double adoptionPrice = resultSet.getDouble("adoptionPrice");
            Animals animals = new Animals(animalID, name, species, birthdate, adoptionPrice, ownerID);

            listAnimals.add(animals);
        }        
        
        resultSet.close();
        return listAnimals;
    }
    
    public boolean updateAnimal(Animals animals) throws SQLException {
        String sql = "update animals set name=?, species =?, birthdate = ?, adoptionPrice = ?, owner = ?, where id = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, animals.name);
        preparedStatement.setString(2, animals.species);
        preparedStatement.setString(3, animals.birthdate);
        preparedStatement.setDouble(4, animals.adoptionPrice);
        preparedStatement.setInt(5, animals.ownerID);
        preparedStatement.setInt(6, animals.animalID);
   
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//        disconnect();
        return rowUpdated;     
    }
	
    public Animals getAnimal(int animalID) throws SQLException {
    	Animals animals = null;
        String sql = "SELECT * FROM animals WHERE animalID = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, animalID);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
        	String name = resultSet.getString("name");
            String species = resultSet.getString("species");
            String birthdate = resultSet.getString("birthdate");
            double adoptionPrice = resultSet.getDouble("adoptionPrice");
            int ownerID = resultSet.getInt("ownerID");
             
            animals = new Animals(animalID, name, species, birthdate, adoptionPrice, ownerID);
        }
         
        resultSet.close();
        preparedStatement.close();
        return animals;
    }
       
	public boolean insertReview(Review review) throws SQLException {
		connect_func();         
		String sql = "insert into reviews(review, reviewScore, animalID, ownerID) values (?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, review.review);
		preparedStatement.setInt(2, review.reviewScore);
		preparedStatement.setInt(3, review.animalID);
		preparedStatement.setInt(4, review.ownerID);	//	preparedStatement.executeUpdate();
		
	   boolean rowInserted = preparedStatement.executeUpdate() > 0;
	   preparedStatement.close();
	   return rowInserted;
	}     
	
    public List<Review> getReviews(int animalID) throws SQLException {
        List<Review> reviews = new ArrayList<Review>();  

        String sql = "SELECT * FROM reviews WHERE animalID = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, animalID);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
        	int reviewID = resultSet.getInt("reviewID");
            String reviewText = resultSet.getString("review");
            int reviewScore = resultSet.getInt("reviewScore");
            int ownerID = resultSet.getInt("ownerID");
            String fullName = getUserFullName(ownerID);    
            
            Review review = new Review(reviewID,  reviewText,  reviewScore,  animalID,  ownerID, fullName);
            reviews.add(review);
        }        
         
        resultSet.close();
        preparedStatement.close();
        return reviews;
    }
    
    public Review getReview(int reviewID) throws SQLException {
        String sql = "SELECT * FROM reviews WHERE reviewID = ?";
        Review review = null;
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, reviewID);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String reviewText = resultSet.getString("review");
            int reviewScore = resultSet.getInt("reviewScore");
        	int animalID = resultSet.getInt("animalID");
            int ownerID = resultSet.getInt("ownerID");
            String fullName = getUserFullName(ownerID);    
            
            review = new Review(reviewID,  reviewText,  reviewScore,  animalID,  ownerID, fullName);
        }        
         
        resultSet.close();
        preparedStatement.close();
        return review;
    }
    

    public boolean saveAnimal(int animalID, int userID) throws SQLException {
    	connect_func();         
       
    	String sql = "insert into favAnimal(username, animalID) values (?, ?)";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, userID);
		preparedStatement.setInt(2, animalID);
		
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();

        return rowInserted;    	
    }
    
    public boolean saveBreeder(int ownerID, int userID) throws SQLException {
    	connect_func();         
        
    	String sql = "insert into favbreeder(username, ownerID) values (?, ?)";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, userID);
		preparedStatement.setInt(2, ownerID);
		
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();

        return rowInserted; 
    }
    
    public boolean deleteFavoriteAnimal(int animalID, int userID) throws SQLException {
    	connect_func();         
       
    	String sql = "DELETE FROM favanimal WHERE username = ? AND animalID = ?";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, userID);
		preparedStatement.setInt(2, animalID);
		
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();

        return rowInserted;    	
    }
    
    public boolean deleteFavoriteBreeder(int ownerID, int userID) throws SQLException {
    	connect_func();         
        
    	String sql = "DELETE FROM favbreeder WHERE username = ? AND ownerID = ?";

    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, userID);
		preparedStatement.setInt(2, ownerID);
		
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();

        return rowInserted; 
    }
    
    public List<Animals> getSavedAnimal(int userID) throws SQLException {
    	List<Animals> listAnimals = new ArrayList<Animals>();  
        String sql = "SELECT * FROM animals, favanimal " +
        		     "WHERE animals.animalID = favanimal.animalID AND ? = favanimal.username ";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, userID);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        while (resultSet.next()) {
        	int animalID = resultSet.getInt("animalID");
        	String name = resultSet.getString("name");
            String species = resultSet.getString("species");
            String birthdate = resultSet.getString("birthdate");
            double adoptionPrice = resultSet.getDouble("adoptionPrice");
            int ownerID = resultSet.getInt("ownerID");
             
            Animals animals = new Animals(animalID, name, species, birthdate, adoptionPrice, ownerID);
            
            listAnimals.add(animals);
        }

        resultSet.close();
        preparedStatement.close();
        return listAnimals;
    }
        
    public List<People> getSavedBreeder(int userID) throws SQLException {
    	List<People> users = new ArrayList<People>(); 
    	String sql = "SELECT * FROM users, favbreeder " +
    			     " WHERE users.id = favbreeder.ownerID AND ? = favbreeder.username";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, userID);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        while (resultSet.next()) {
        	int id = resultSet.getInt("ownerID");
        	String firstname = resultSet.getString("firstname");
            String lastname = resultSet.getString("lastname");
            String emailaddress = resultSet.getString("emailaddress");
            String userpassword = resultSet.getString("userpassword");
            String username = resultSet.getString("username");
             
            People people = new People(id, username, userpassword, firstname, lastname, emailaddress);
            
            users.add(people);
        }
     
	    resultSet.close();
	    preparedStatement.close();
	    return users;
    }
    public List<Animals> getCommonPets(int userID) throws SQLException {
    	List<Animals> listAnimals = new ArrayList<Animals>();  
        String sql = "Select " + 
        		"  *" + 
        		" From" + 
        		"  animals" + 
        		" where animalID in(" + 
        		"  Select" + 
        		"  animalID" + 
        		" From" + 
        		"  favanimal" + 
        		" WHERE" + 
        		" username <> ? AND" + 
        		"  animalID IN (" + 
        		"    SELECT" + 
        		"      animals.animalID" + 
        		"    FROM" + 
        		"      animals," + 
        		"      favanimal" + 
        		"    WHERE" + 
        		"      animals.animalID = favanimal.animalID" + 
        		"      AND ? = favanimal.username" + 
        		"  ))";

         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, userID);
        preparedStatement.setInt(2, userID);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        while (resultSet.next()) {
        	int animalID = resultSet.getInt("animalID");
        	String name = resultSet.getString("name");
            String species = resultSet.getString("species");
            String birthdate = resultSet.getString("birthdate");
            double adoptionPrice = resultSet.getDouble("adoptionPrice");
            int ownerID = resultSet.getInt("ownerID");
             
            Animals animals = new Animals(animalID, name, species, birthdate, adoptionPrice, ownerID);
            
            listAnimals.add(animals);
        }

        resultSet.close();
        preparedStatement.close();
        return listAnimals;
    }


    //Returns top 5 reviewers based on their count
    public List<Review> getTopReviewers() throws SQLException {
    	List<Review> topReviewers = new ArrayList<Review>();
        String sql = "SELECT ownerID, COUNT(animalID) as count " +
        			 "FROM reviews " + 
	        		 "GROUP BY ownerID " +
	        		 "ORDER BY count DESC " +
	        		 "LIMIT 5";
        
        connect_func();
                 
        statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int ownerID = resultSet.getInt("ownerID");
            int count = resultSet.getInt("count");
            
            Review reviewer = new Review(ownerID, count);
            topReviewers.add(reviewer);
        }        

        resultSet.close();
        statement.close();
        return topReviewers;

    }
    
    //Select ratings 4 and 3
    public List<Review> getTopAnimals() throws SQLException {
    	List<Review> topAnimals = new ArrayList<Review>();
        String sql = "SELECT * FROM reviews " +
        		     "where reviewScore = 4 OR reviewScore = 3";
        
        connect_func();
        statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {
            int reviewID = resultSet.getInt("reviewID");
            String reviewText = resultSet.getString("review");
            int reviewScore = resultSet.getInt("reviewScore");
        	int animalID = resultSet.getInt("animalID");
            int ownerID = resultSet.getInt("ownerID");
            String fullName = getUserFullName(ownerID);    
            
            Review review = new Review(reviewID, reviewText,  reviewScore,  animalID,  ownerID, fullName);
            topAnimals.add(review);
        }        

        resultSet.close();
        statement.close();
        return topAnimals;
    }
    
    
    public List<String> getSpeciesNames() throws SQLException {
        List<String> traitNames= new ArrayList<String>();        
        String sql = "SELECT DISTINCT species FROM animals";      
        connect_func();      
        statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
                
        while (resultSet.next()) {
            traitNames.add(resultSet.getString("species"));
        }        
        
        resultSet.close();
        statement.close();         
        disconnect();        
        return traitNames;
    }
    
    public List<Integer> getUserBySpecies(String species1, String species2) throws SQLException {
        List<Integer> ownerIDs = new ArrayList<Integer>();
        connect_func();  
        String sql = "Select ownerID from animals " + 
        		"where species in (?, ?) " + 
        		"group by ownerID " + 
        		"having count(ownerID) >= 2";       
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, species1);
        preparedStatement.setString(2, species2);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
        	ownerIDs.add(resultSet.getInt("ownerID"));
        }   
        
        resultSet.close();
        return ownerIDs;
    }
    
    public List<Integer> getNotRidicAdorbs() throws SQLException {
    	List<Integer> notRidicAdorbsUsers = new ArrayList<Integer>();
        String sql = "SELECT ownerID FROM Animals " + 
        		"WHERE ownerID NOT IN( " + 
        		"SELECT animalID from reviews " + 
        		"WHERE reviewScore = 4 " + 
        		"GROUP BY animalID " + 
        		"HAVING count(AnimalID) >= 2)";
        
        connect_func();
        statement =  (Statement) connect.createStatement();
        
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int ownerID = resultSet.getInt("ownerID");
            notRidicAdorbsUsers.add(ownerID);
        }        

        resultSet.close();
        statement.close();
        return notRidicAdorbsUsers;
    }
    
    public List<Integer> getNotCrayCray() throws SQLException {
    	List<Integer> notRidicAdorbsUsers = new ArrayList<Integer>();
        String sql = "SELECT ownerID FROM Animals " + 
        		"WHERE ownerID NOT IN(" + 
        		"SELECT animalID FROM reviews " + 
        		"WHERE reviewScore = 1);";
        
        connect_func();
        statement =  (Statement) connect.createStatement();
        
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int ownerID = resultSet.getInt("ownerID");
            notRidicAdorbsUsers.add(ownerID);
        }        

        resultSet.close();
        statement.close();
        return notRidicAdorbsUsers;
    }
}
