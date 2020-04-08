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
        statement.close();         
        disconnect();        
        return listPeople;
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

	public void wipe() throws SQLException{
		connect_func();
		
		String deleteUserTable = "DROP TABLE IF EXISTS users";
		String createUserTable = "CREATE TABLE IF NOT EXISTS users " +
			"(id INTEGER not NULL AUTO_INCREMENT, " +
			" username VARCHAR(50) NOT NULL, " + 
			" userpassword VARCHAR(50) NOT NULL, " + 
			" firstname VARCHAR(50) NOT NULL, " + 
			" lastname VARCHAR(50) NOT NULL, " + 
			" emailaddress varchar(50) not NULL," +
			" PRIMARY KEY ( id ))"; 
		String deleteAnimalTable ="DROP TABLE IF EXISTS animals"; //added for part 2 -ae
		String createAnimalTable ="CREATE TABLE IF NOT EXISTS animals " +
				"(animalID INTEGER not NULL AUTO_INCREMENT, " +
				" name VARCHAR(50) NOT NULL, " + 
				" species VARCHAR(50) NOT NULL, " + 
				" birthdate VARCHAR(50) NOT NULL, " + 
				" adoptionPrice FLOAT(50) NOT NULL, " + 
				" traits VARCHAR(150) not NULL," +
				" ownerID INTEGER not NULL," +
				" FOREIGN KEY (ownerID) REFERENCES users(id),\r\n" + 
				" PRIMARY KEY ( animalID ))"; 
	
		statement = connect.createStatement();
	    statement.executeUpdate(deleteAnimalTable); //added for part 2 -ae
	    statement.executeUpdate(deleteUserTable);
	    statement.executeUpdate(createUserTable);
	    statement.executeUpdate(createAnimalTable); //added for part 2 -ae
	    
	    statement.close();
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
            String traits = resultSet.getString("traits");
            int ownerID = resultSet.getInt("ownerID");
                         
            Animals animals = new Animals(animalID, name, species, birthdate, adoptionPrice, traits, ownerID);

            listAnimals.add(animals);
        }        
        
        resultSet.close();
        statement.close();         
        disconnect();        
        return listAnimals;
    }
    
    public List<Animals> listByTrait() throws SQLException {
        List<Animals> listAnimals = new ArrayList<Animals>();        
        String sql = "SELECT * FROM animals WHERE traits = ${param.animals}";      
        connect_func();      
        statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int animalID = resultSet.getInt("animalID");
            String name = resultSet.getString("name");
            String species = resultSet.getString("species");
            String birthdate = resultSet.getString("birthdate");
            double adoptionPrice = resultSet.getDouble("adoptionPrice");
            String traits = resultSet.getString("traits");
            int ownerID = resultSet.getInt("ownerID");
                         
            Animals animals = new Animals( animalID, name, species, birthdate, adoptionPrice, traits, ownerID);

            listAnimals.add(animals);
        }        
        
        resultSet.close();
        statement.close();         
        disconnect();        
        return listAnimals;
    }
    
    public List<Animals> searchByOwner(int ownerID) throws SQLException {
        List<Animals> listAnimals = new ArrayList<Animals>();  
        connect_func();  
        String sql = "SELECT * FROM animals WHERE ownerID = ?";       
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, ownerID);

        ResultSet resultSet = preparedStatement.executeQuery();
         
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String species = resultSet.getString("species");
            String birthdate = resultSet.getString("birthdate");
            double adoptionPrice = resultSet.getDouble("adoptionPrice");
            String traits = resultSet.getString("traits");
                         
            Animals animals = new Animals(name, species, birthdate, adoptionPrice, traits, ownerID);

            listAnimals.add(animals);
        }        
        
        resultSet.close();
        return listAnimals;
    }

    public boolean insertAnimal(Animals animals) throws SQLException {
    	connect_func();         
		String sql = "insert into  animals(name, species, birthdate, adoptionPrice, traits, ownerID) values (?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, animals.name);
		preparedStatement.setString(2, animals.species);
		preparedStatement.setString(3, animals.birthdate);
		preparedStatement.setDouble(4, animals.adoptionPrice);
		preparedStatement.setString(5, animals.traits);
		preparedStatement.setInt(6, animals.ownerID);
//		preparedStatement.executeUpdate();
		
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//        disconnect();
        return rowInserted;
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
     
    public boolean updateAnimal(Animals animals) throws SQLException {
        String sql = "update animals set name=?, species =?, birthdate = ?, adoptionPrice = ?,traits = ?, owner = ?, where id = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, animals.name);
        preparedStatement.setString(2, animals.species);
        preparedStatement.setString(3, animals.birthdate);
        preparedStatement.setDouble(4, animals.adoptionPrice);
        preparedStatement.setString(5, animals.traits);
        preparedStatement.setInt(6, animals.ownerID);
        preparedStatement.setInt(7, animals.animalID);
   
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//        disconnect();
        return rowUpdated;     
    }
	
    public Animals getAnimals(int animalID) throws SQLException {
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
            String traits = resultSet.getString("traits");
            int ownerID = resultSet.getInt("owner");
             
            animals = new Animals( animalID, name, species, birthdate, adoptionPrice, traits, ownerID);
            System.out.println(animals);
        }
         
        resultSet.close();
        statement.close();
         
        return animals;
    }
}
