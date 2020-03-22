import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
 
/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @author www.codejava.net
 */
public class ControlServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PeopleDAO peopleDAO;
 
    public void init() {
        peopleDAO = new PeopleDAO(); 
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println(action);
        try {
            switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
            	insertPeople(request, response);
                break;
            case "/delete":
            	deletePeople(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
            	updatePeople(request, response);
                break;
            case "/initDB":
            	initDBForm(request,response); 
            	break;
            case "/resetDB":
        		initDB(request,response); 
            	break;
            case "/login":
        		login(request,response); 
            	break;
            case "/loginForm":
            	loginForm(request,response); 
            	break;
            case "/welcome":
            	welcomeForm(request, response);
            	break;
            case "/insertAnimal":
            	insertAnimal(request, response);
                break;
            case "/deleteAnimal":
            	deleteAnimal(request, response);
                break;
//            case "/edit":
//                showEditForm(request, response);
//                break;
            case "/updateAnimal":
            	updateAnimal(request, response);
                break;
            case "/AnimalList":
            	animalListForm(request, response);
                break;
            default:          	
            	listPeople(request, response);           	
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    private void initDBForm(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            RequestDispatcher dispatcher = request.getRequestDispatcher("InitDB.jsp");
            dispatcher.forward(request, response);
	}

	private void initDB(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException, ServletException{
    	System.out.println("Initializing Database");
    	peopleDAO.wipe();
    	String username, userpassword, firstname, lastname, emailaddress;
    	People people; 
    	
    	
    	//Create root user
    	username = "root" ;
		userpassword = "pass1234";
		firstname = "root";
		lastname = "user";
		emailaddress = "root@gmail.com";
		
        people = new People(username, userpassword, firstname, lastname, emailaddress);
        peopleDAO.insert(people);

    	//Insert 14 other users
    	for(int i =1; i < 15; i++) {
    		username = "user" +i;
    		userpassword = "password" + i;
    		firstname = "name" + i;
    		lastname = "last" + i;
    		emailaddress = username + "@gmail.com";
    		
	        people = new People(username, userpassword, firstname, lastname, emailaddress);
	        peopleDAO.insert(people);
    	}
        response.sendRedirect("list");		
	}
	
	 private void loginForm(HttpServletRequest request, HttpServletResponse response)
		        throws ServletException, IOException {
		            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		            dispatcher.forward(request, response);
			}
	 
	private void login(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException, ServletException{
		
		 String username = request.getParameter("username");
	     String userpassword = request.getParameter("userpassword");
	     
	     if(peopleDAO.findUser(username, userpassword)) {
	    	 response.sendRedirect("welcome");
	    	 System.out.println("login successful");
	     }
	     else {
	    	 response.sendRedirect("loginForm");
	     }
	     
	     
	}
	
	private void welcomeForm(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	            RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
	            dispatcher.forward(request, response);
		}

	private void listPeople(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<People> listPeople = peopleDAO.listAllPeople();
        request.setAttribute("listPeople", listPeople);       
        RequestDispatcher dispatcher = request.getRequestDispatcher("PeopleList.jsp");       
        dispatcher.forward(request, response);
    }
 
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("PeopleForm.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        People existingPeople = peopleDAO.getPeople(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("PeopleForm.jsp");
        request.setAttribute("people", existingPeople);
        dispatcher.forward(request, response);
 
    }
 
    private void insertPeople(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String username = request.getParameter("username");
        String userpassword = request.getParameter("userpassword");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String emailaddress = request.getParameter("emailaddress");
        
        People newPeople = new People(username, userpassword, firstname, lastname, emailaddress);
        peopleDAO.insert(newPeople);
        response.sendRedirect("list");
    }
 
    private void updatePeople(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);
        
        String username = request.getParameter("username");
        String userpassword = request.getParameter("userpassword");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String emailaddress = request.getParameter("emailaddress");
      
        System.out.println(username);
        
        People people = new People(id, username, userpassword, firstname, lastname, emailaddress);
        peopleDAO.update(people);
        response.sendRedirect("list");
    }
    
    private void deletePeople(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        //People people = new People(id);
        peopleDAO.delete(id);
        response.sendRedirect("list"); 
    }
 ///////////////////////part 2/////////////////////////
    
    private void deleteAnimal(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        //People people = new People(id);
        peopleDAO.deleteAnimal(id);
        response.sendRedirect("list"); 
    }

    private void insertAnimal(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String species = request.getParameter("species");
        String birthdate = request.getParameter("birthdate");
        Double adoptionPrice = Double.parseDouble(request.getParameter("adoptionPrice"));
        String traits = request.getParameter("traits");
        
        Animals newAnimals = new Animals(name, species, birthdate, adoptionPrice, traits);
        peopleDAO.insertAnimal(newAnimals);
        response.sendRedirect("AnimalList");
    }
 
    private void updateAnimal(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);
        
        String name = request.getParameter("name");
        String species = request.getParameter("species");
        String birthdate = request.getParameter("birthdate");
        Double adoptionPrice = Double.parseDouble(request.getParameter("adoptionPrice"));
        String traits = request.getParameter("traits");
      
        System.out.println(name);
        
        Animals animals = new Animals(id, name, species, birthdate, adoptionPrice, traits);
        peopleDAO.updateAnimal(animals);
        response.sendRedirect("list");
    }
   
    private void animalListForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Animals> animalListForm = peopleDAO.listAllAnimals();
        request.setAttribute("animalListForm", animalListForm);       
        RequestDispatcher dispatcher = request.getRequestDispatcher("AnimalList.jsp");       
        dispatcher.forward(request, response);
    }
   
}