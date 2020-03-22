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
	       
    /**
     * @see HttpServlet#HttpServlet()
     */
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
        String sql = "DELETE FROM users WHERE id = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
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
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
    public People getPeople(int id) throws SQLException {
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
            System.out.println(people);
        }
         
        resultSet.close();
        statement.close();
         
        return people;
    }

	public void wipe() throws SQLException{
		connect_func();
		
		String deleteTable = "DROP TABLE IF EXISTS users";
		String createTable = "CREATE TABLE IF NOT EXISTS users " +
			"(id INTEGER not NULL AUTO_INCREMENT, " +
			" username VARCHAR(50) NOT NULL, " + 
			" userpassword VARCHAR(50) NOT NULL, " + 
			" firstname VARCHAR(50) NOT NULL, " + 
			" lastname VARCHAR(50) NOT NULL, " + 
			" emailaddress varchar(50) not NULL," +
			" PRIMARY KEY ( id ))"; 
		String deleteTable2 ="DROP TABLE IF EXISTS animals"; //added for part 2 -ae
		String createTable2 ="CREATE TABLE IF NOT EXISTS animals " +
				"(id INTEGER not NULL AUTO_INCREMENT, " +
				" name VARCHAR(50) NOT NULL, " + 
				" species VARCHAR(50) NOT NULL, " + 
				" birthdate VARCHAR(50) NOT NULL, " + 
				" adoptionPrice VARCHAR(50) NOT NULL, " + 
				" traits VARCHAR(150) not NULL," +
				" PRIMARY KEY ( id ))"; 
	
		statement = connect.createStatement();
	    statement.executeUpdate(deleteTable);
	    statement.executeUpdate(createTable);
	    statement.executeUpdate(deleteTable2); //added for part 2 -ae
	    statement.executeUpdate(createTable2); //added for part 2 -ae
	    
	    statement.close();
	}
	
	public Boolean findUser(String username, String userpassword) throws SQLException{
		
		connect_func();
        String sql = "SELECT * FROM users WHERE username = ? AND userpassword = ?";
         
        
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, userpassword);
         
        ResultSet resultSet = preparedStatement.executeQuery();
        
        if (resultSet.next()) {

              
             return true;
        }
        
        else
        	return false;
    
		
	}
	
	///////////////////////////////// Begin Animal Registration Form //////////////////////////////////// -ae
    public List<Animals> listAllAnimals() throws SQLException {
        List<Animals> listAnimals = new ArrayList<Animals>();        
        String sql = "SELECT * FROM animals";      
        connect_func();      
        statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String species = resultSet.getString("species");
            String birthdate = resultSet.getString("birthdate");
            double adoptionPrice = resultSet.getDouble("adoptionPrice");
            String traits = resultSet.getString("traits");
                         
            Animals animals = new Animals( id, name, species, birthdate, adoptionPrice, traits);

            listAnimals.add(animals);
        }        
        
        resultSet.close();
        statement.close();         
        disconnect();        
        return listAnimals;
    }
    
    public boolean insertAnimal(Animals animals) throws SQLException {
    	connect_func();         
		String sql = "insert into  animals(name, species, birthdate, adoptionPrice, traits) values (?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, animals.name);
		preparedStatement.setString(2, animals.species);
		preparedStatement.setString(3, animals.birthdate);
		preparedStatement.setDouble(4, animals.adoptionPrice);
		preparedStatement.setString(5, animals.traits);
//		preparedStatement.executeUpdate();
		
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//        disconnect();
        return rowInserted;
    }     
     
    public boolean deleteAnimal(int animalId) throws SQLException {
        String sql = "DELETE FROM animals WHERE id = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, animalId);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//        disconnect();
        return rowDeleted;     
    }
     
    public boolean updateAnimal(Animals animals) throws SQLException {
        String sql = "update animals set name=?, species =?, birthdate = ?, adoptionPrice = ?,traits = ? where id = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, animals.name);
        preparedStatement.setString(2, animals.species);
        preparedStatement.setString(3, animals.birthdate);
        preparedStatement.setDouble(4, animals.adoptionPrice);
        preparedStatement.setString(5, animals.traits);
        preparedStatement.setInt(6, animals.id);
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//        disconnect();
        return rowUpdated;     
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
    public Animals getAnimals(int id) throws SQLException {
    	Animals animals = null;
        String sql = "SELECT * FROM animals WHERE id = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, id);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
        	String name = resultSet.getString("name");
            String species = resultSet.getString("species");
            String birthdate = resultSet.getString("birthdate");
            double adoptionPrice = resultSet.getDouble("adoptionPrice");
            String traits = resultSet.getString("traits");
             
            animals = new Animals( id, name, species, birthdate, adoptionPrice, traits);
            System.out.println(animals);
        }
         
        resultSet.close();
        statement.close();
         
        return animals;
    }
}
