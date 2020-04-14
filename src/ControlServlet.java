import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.servlet.http.*;  
 
/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @author www.codejava.net
 */
public class ControlServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PeopleDAO peopleDAO;
	private HttpSession session = null;
	private List<Animals> animals = null;
    
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
            case "/newUser":
                showNewForm(request, response);
                break;
            case "/insertUser":
            	insertPeople(request, response);
                break;
            case "/deleteUser":
            	deletePeople(request, response);
                break;
            case "/editUser":
                showEditForm(request, response);
                break;
            case "/updateUser":
            	updatePeople(request, response);
                break;
            case "/listUsers":
            	listPeople(request, response);
            	break;
            case "/initDB":
        		initDB(request, response); 
            	break;
            case "/login":
        		login(request, response); 
            	break;
            case "/welcome":
            	welcomeForm(request, response);
            	break;
            case "/logout":
            	logout(request, response);
            	break;
            case "/insertAnimal":
            	insertAnimal(request, response);
                break;
            case "/deleteAnimal":
            	deleteAnimal(request, response);
                break;
            case "/updateAnimal":
            	updateAnimal(request, response);
                break;
            case "/AnimalList":
            	animalListForm(request, response, animals);
                break;
            case "/AnimalRegistrationForm":
            	animalRegistrationForm(request, response);
                break;
            case "/AnimalListFormDropDown":
            	animalListFormDropDown(request, response);
                break;
            case "/SearchByTrait":
            	searchByTrait(request, response);
                break;
            case "/myAnimals":
            	myAnimals(request,response);
            	break;
            case "/createReview":
            	createReview(request,response);
            	break;
            case "/insertReview":
            	insertReview(request,response);
            	break;
            case "/showReview":
            	showReview(request,response);
            	break;
            case "/mostExpensiveAnimalsForm":
            	mostExpensiveAnimalsForm(request,response);
            	break;
            case "/mostExpensiveAnimals":
            	mostExpensiveAnimals(request,response);
            	break;
            case "/saveAnimal":
            	saveAnimal(request,response);
            	break;
            case "/saveBreeder":
            	saveBreeder(request,response);
            	break;
            case "/topReviewers":
            	topReviewers(request,response);
            	break;
            case "/topAnimals":
            	topAnimals(request,response);
            	break;
            case "/listUserBySpecies":
            	listUserBySpecies(request, response);
            	break;
            case "/usersBySpecies":
            	searchUserBySpecies(request, response);
            	break;
            case "/deleteFavoriteAnimal":
            	deleteFavoriteAnimal(request, response);
            	break;
            case "/deleteFavoriteBreeder":
            	deleteFavoriteBreeder(request, response);
            	break;
            case "/notRidicAdorbs":
            	notRidicAdorbs(request, response);
            	break;
            case "/notCrayCray":
            	notCrayCray(request, response);
            	break;
            case "/negativeReviewers":
            	negativeReviewers(request, response);
            	break;
            case "/listCommonPets":
            	listCommonPets(request, response);
            	break;
            case "/adoptionCrate":
            	adoptionCrate(request, response);
            	break;
            case "/deleteSavedAnimals":
            	deleteSavedAnimals(request, response);
            	break;
//            case "/noCrayCrayUsers":
//            	noCrayCrayUsers(request, response);
//            	break;
            default:   	
            	pageNotFound(request,response);
            	break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

	private void pageNotFound(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	String urlInfo = request.getServletPath();
    	request.setAttribute("urlInfo", urlInfo);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("pageNotFound.jsp");
    	dispatcher.forward(request, response);
	}

	private void initDB(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException, ServletException{
    	System.out.println("Initializing Database");
    	peopleDAO.wipe();
    	String username, userpassword, firstname, lastname, emailaddress;
    	People people; 
    	
    	//Create root user Root id = 1
    	username = "root" ;
		userpassword = "pass1234";
		firstname = "root";
		lastname = "user";
		emailaddress = "root@gmail.com";
		
        people = new People(username, userpassword, firstname, lastname, emailaddress);
        peopleDAO.insert(people);

    	//Insert 14 other users. 
    	for(int i =2; i < 15; i++) {
    		username = "user" + i;
    		userpassword = "pass" + i;
    		firstname = "name" + i;
    		lastname = "last" + i;
    		emailaddress = username + "@gmail.com";
    		
	        people = new People(username, userpassword, firstname, lastname, emailaddress);
	        peopleDAO.insert(people);
    	}
    	
    	//Insert animals
    	for(int i = 1; i < 15; i++) {
    		String name = "pet" + i;
    		String species = "species" + i;
    		String birthdate = "birthday" + i;
    		Double adoptionPrice = (double) (i*10);
    		int owner = i;
    		Animals animal = new Animals(name, species, birthdate, adoptionPrice, owner);
    		peopleDAO.insertAnimal(animal);
    	}
    	
    	//Create reviews
     	for(int i = 1; i < 15; i++) {
    		String reviewText = "Review...Text place holder" + i;
    		int reviewScore = ThreadLocalRandom.current().nextInt(1, 4 + 1);
    		int animalID = i;
    		int ownerID = i%14 + 1;
    		Review review= new Review(reviewText, reviewScore, animalID, ownerID);
    		peopleDAO.insertReview(review);
    	}
     	
     	//Insert 2 traits in the trait table per animal
     	//Second trait is a random one between 1-14
     	for (int i = 1; i < 15; i++) {
     		int animalID = i;
    		String traitText1 = "trait" + i;
    		String traitText2 = "trait" + ThreadLocalRandom.current().nextInt(1, 15);
    		Trait trait1= new Trait(animalID, traitText1);
    		Trait trait2= new Trait(animalID, traitText2);
    		peopleDAO.insertTrait(trait1);	
    		peopleDAO.insertTrait(trait2);	
		}
     	
     	if(authenticate(request,response)) {
			response.sendRedirect("welcome.jsp");
		}		
	}
	
	private boolean authenticate(HttpServletRequest request, HttpServletResponse response) 
    		throws IOException, ServletException{
		session = request.getSession();
		if(session != null && session.getAttribute("userID") != null) {
			return true;
		}else
			response.sendRedirect("login.jsp");
			return false;
	}
		
	private void login(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException, ServletException{
		
		 String username = request.getParameter("username");
	     String password = request.getParameter("userpassword");
	     
	     if(peopleDAO.findUser(username, password)) {
	    	 int userID = peopleDAO.getUserId(username, password);
	    	 System.out.println(userID);
	    	 People user = peopleDAO.getUser(userID);
	    	 session = request.getSession();
	    	 session.setAttribute("userName", user.getUserName());
	    	 session.setAttribute("firstName", user.getFirstName());
	    	 session.setAttribute("userID", userID);
	    	    	 
	    	 response.sendRedirect("welcome");
	    	 System.out.println("login successful");
	     }
	     else {
	    	 request.setAttribute("failedLogin", true);
	         RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
	         dispatcher.forward(request, response);
	     }	     
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException, ServletException{
		session = request.getSession();
		if (session != null && session.getAttribute("userID") != null) 
		    session.invalidate();
		response.sendRedirect("login.jsp");
	}
	
	private void welcomeForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		session = request.getSession();
		if(authenticate(request,response)) {
			int userID =  (Integer) (session.getAttribute("userID"));
			 
			List<Animals> savedAnimals = peopleDAO.getSavedAnimal(userID);
			List<People> savedBreeders = peopleDAO.getSavedBreeder(userID);
			
			if(savedAnimals.size() > 0) 
				request.setAttribute("savedAnimals", savedAnimals);
			if(savedBreeders.size() > 0)
				request.setAttribute("savedBreeders", savedBreeders);
			
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
	        dispatcher.forward(request, response);
	        System.out.println("logged in with " + session.getAttribute("userName"));
		}
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
        People existingPeople = peopleDAO.getUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("PeopleForm.jsp");
        request.setAttribute("people", existingPeople);
        dispatcher.forward(request, response);
    }
 
    private void insertPeople(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String username = request.getParameter("username");
        String userpassword = request.getParameter("userpassword");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String emailaddress = request.getParameter("emailaddress");
        
        //See if the username and password and unique 
        try {
            People newPeople = new People(username, userpassword, firstname, lastname, emailaddress);
            peopleDAO.insert(newPeople);
         	if(authenticate(request, response)) {
         		response.sendRedirect("welcome.jsp");
         	}
        } catch (SQLException e) {
        	if(e.getMessage().contains("users.username")) 
        		request.setAttribute("msg", "Username must be unique!");
        	else if(e.getMessage().contains("users.emailaddress")) 
        		request.setAttribute("msg", "Email addresss must be unique!");
        	
   			RequestDispatcher dispatcher = request.getRequestDispatcher("/newUser");
   			dispatcher.forward(request, response);
        }
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
        response.sendRedirect("welcome.jsp");
    }
    
    private void deletePeople(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        //People people = new People(id);
        peopleDAO.delete(id);
        response.sendRedirect("listUsers"); 
    }
 ///////////////////////part 2/////////////////////////
    
    //animalID is animal to be saved.
    //userId is active user
    //Saves breeder to favorites
    private void saveAnimal(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	if(authenticate(request, response)) {
	        int animalID = Integer.parseInt(request.getParameter("animalID"));
	        int userID =  (Integer) (session.getAttribute("userID"));
	        try {
		        peopleDAO.saveAnimal(animalID, userID);
	        } catch (SQLException e) {
	        	if(e.getMessage().contains("Duplicate entry")) 
	        		System.out.println("Animal already added!");	        	
	        }
	        response.sendRedirect("welcome");
	    }
    }
    
    //owner ID is the breeder of the pet
    //userId is active user
    //Saves breeder to favorites
    private void saveBreeder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	if(authenticate(request, response)) {
	        int ownerID = Integer.parseInt(request.getParameter("ownerID"));
	        int userID =  (Integer) (session.getAttribute("userID"));
	        try {
		        peopleDAO.saveBreeder(ownerID, userID);
	        } catch (SQLException e) {
	        	if(e.getMessage().contains("Duplicate entry")) 
	        		System.out.println("Breeder already added!");	        	
	        }
	        response.sendRedirect("welcome");
    	}
    }
    
    private void deleteFavoriteAnimal(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    	if(authenticate(request, response)) {
    		int animalID = Integer.parseInt(request.getParameter("animalID"));
	        int userID =  (Integer) (session.getAttribute("userID"));
	        peopleDAO.deleteFavoriteAnimal(animalID, userID);
    		response.sendRedirect("welcome");
    	}
    }
    
    private void deleteFavoriteBreeder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	if(authenticate(request, response)) {
	        int ownerID = Integer.parseInt(request.getParameter("breederID"));
	        int userID =  (Integer) (session.getAttribute("userID"));
	        peopleDAO.deleteFavoriteBreeder(ownerID, userID);
	        response.sendRedirect("welcome");
    	}
    }
    
    private void deleteAnimal(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	if(authenticate(request, response)) {
	        int animalID = Integer.parseInt(request.getParameter("animalID"));
	        peopleDAO.deleteAnimal(animalID);
    	}
    }
    
	 private void animalRegistrationForm(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException, ServletException {
		 if(authenticate(request, response)) {
			 RequestDispatcher dispatcher = request.getRequestDispatcher("AnimalRegistrationForm.jsp");
			 dispatcher.forward(request, response);
		}
	}

    private void insertAnimal(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	if(authenticate(request, response)) {
	        String name = request.getParameter("name");
	        String species = request.getParameter("species");
	        String birthdate = request.getParameter("birthdate");
	        String traits = request.getParameter("traits");
	        Double adoptionPrice = Double.parseDouble(request.getParameter("adoptionPrice"));
	        int ownerID =  (Integer) (session.getAttribute("userID"));
	        
	        Animals newAnimals = new Animals(name, species, birthdate, adoptionPrice, ownerID);
	        int animalID = peopleDAO.insertAnimal(newAnimals);
	        
	        //Push each trait into the trait table 
	        List<String> traitsList = Arrays.asList(traits.split(","));
	        for (String traitText : traitsList) {
	        	traitText = traitText.replaceAll("\\s+",""); // Remove all whitespace
	        	Trait trait = new Trait(animalID, traitText);
				peopleDAO.insertTrait(trait);
			}
	        response.sendRedirect("AnimalList");
    	}
    }
 
    private void updateAnimal(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	if(authenticate(request, response)) {
	        int animalID = Integer.parseInt(request.getParameter("animalID"));
	        String name = request.getParameter("name");
	        String species = request.getParameter("species");
	        String birthdate = request.getParameter("birthdate");
	        Double adoptionPrice = Double.parseDouble(request.getParameter("adoptionPrice"));
	        int ownerID = Integer.parseInt(request.getParameter("ownerID"));
	        
	        System.out.println(name);
	        
	        Animals animals = new Animals(animalID, name, species, birthdate, adoptionPrice, ownerID);
	        peopleDAO.updateAnimal(animals);
	        response.sendRedirect("myAnimals");
    	}
    }
   
    //Can pass a list of animals (optional)
    private void animalListForm(HttpServletRequest request, HttpServletResponse response, 
    		List<Animals> animalList) throws SQLException, IOException, ServletException {
    	if(authenticate(request, response)) {
    		if(animalList == null)
    			animalList = peopleDAO.listAllAnimals();
	        List<String> ownerFullname = new ArrayList<String>();
	        
	        //Display owner first and last name (fullname)
	        for (Animals animal : animalList) {
	        	int id = animal.getOwner();
				String fullName = peopleDAO.getUserFullName(id);
				ownerFullname.add(fullName);
			}
	        
	        request.setAttribute("animalListForm", animalList);       
	        request.setAttribute("ownerFullName", ownerFullname);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("AnimalList.jsp");       
	        dispatcher.forward(request, response);
    	}
    }
    
    private void animalListFormDropDown(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	if(authenticate(request, response)) {
	        List<String> traitNames = peopleDAO.getTraitNames();
	        java.util.Collections.sort(traitNames);
	        request.setAttribute("traitNames", traitNames);       
	        request.setAttribute("redirectURL", "SearchByTrait");
	        RequestDispatcher dispatcher = request.getRequestDispatcher("AdoptionSearchForm.jsp");       
	        dispatcher.forward(request, response);
    	}
    }
        
    private void searchByTrait(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	if(authenticate(request, response)) {
	        String trait = request.getParameter("trait");
	        List<Animals> searchByTrait = peopleDAO.getAnimalByTrait(trait);
	        animalListForm(request,response,searchByTrait);
    	}
    }
    
    private void myAnimals(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	if(authenticate(request, response)) {
	        int userID =  (Integer) (session.getAttribute("userID"));
	        List<Animals> animals = peopleDAO.searchByOwner(userID);
	        
	        //Get the animals reviews and owner names
	        for (Animals animal : animals) {
	        	System.out.println("animalid = " + animal.getId());
	        	animal.review.addAll(peopleDAO.getReviews(animal.getId()));
	        	System.out.println("reviewSize = " + animal.review.size());
	        }
	        
	        request.setAttribute("animals", animals);     
	        RequestDispatcher dispatcher = request.getRequestDispatcher("myAnimals.jsp");       
	        dispatcher.forward(request, response);
    	}
    }
    
    //Shows the review form
    private void createReview(HttpServletRequest request, HttpServletResponse response) 
        throws SQLException, ServletException, IOException {
    	if(authenticate(request, response)) {
		    int animalID = Integer.parseInt(request.getParameter("animalID"));
		    Animals reviewAnimal = peopleDAO.getAnimal(animalID);
		    request.setAttribute("reviewAnimal", reviewAnimal);
		    request.setAttribute("ownerID", reviewAnimal.getOwner());
		    RequestDispatcher dispatcher = request.getRequestDispatcher("createReview.jsp");
		    dispatcher.forward(request, response);
    	}
  	}
    
    //Shows the review form
    private void showReview(HttpServletRequest request, HttpServletResponse response) 
        throws SQLException, ServletException, IOException {
    	if(authenticate(request, response)) {
		    int reviewID = Integer.parseInt(request.getParameter("reviewID"));
		    Review review = peopleDAO.getReview(reviewID);
		    Animals animal = peopleDAO.getAnimal(review.getAnimalID());
		    request.setAttribute("animalName", animal.getName());
		    request.setAttribute("review", review);
		    RequestDispatcher dispatcher = request.getRequestDispatcher("showReview.jsp");
		    dispatcher.include(request, response);
    	}
  	}
    
    //Insert the review into the database
    private void insertReview(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String review = request.getParameter("review");
        int reviewScore = Integer.parseInt(request.getParameter("reviewScore"));
        int animalID = Integer.parseInt(request.getParameter("animalID"));
        int ownerID = Integer.parseInt(request.getParameter("ownerID"));

        Review newReview = new Review(review, reviewScore, animalID, ownerID);
        
        //See if the user posted more than 5 reviews
        try {
        	peopleDAO.insertReview(newReview);     
        	 response.sendRedirect("AnimalList");
        }catch(SQLException e) {
        	RequestDispatcher dispatcher = request.getRequestDispatcher("AnimalList");
        	request.setAttribute("mt5reviews", true);
 		    dispatcher.forward(request, response);
        }
    }    
    
    private void mostExpensiveAnimalsForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	if(authenticate(request, response)) {
	        List<String> traitNames = peopleDAO.getTraitNames();
	        java.util.Collections.sort(traitNames);
	        request.setAttribute("traitNames", traitNames);
	        request.setAttribute("redirectURL", "mostExpensiveAnimals");
	        RequestDispatcher dispatcher = request.getRequestDispatcher("AdoptionSearchForm.jsp");       
	        dispatcher.forward(request, response);
    	}
    }
    
    private void mostExpensiveAnimals(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	if(authenticate(request, response)) {
	        String trait = request.getParameter("trait");
	        List<Animals> searchByTrait = peopleDAO.getAnimalByTrait(trait);
	        Comparator<Animals> comparator = Comparator.comparing(a -> a.getAdoptionPrice());
	        searchByTrait.sort(comparator.reversed());
	        animalListForm(request,response,searchByTrait);
    	}
    }
    
    private void listCommonPets(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	if(authenticate(request, response)) {
    		int userID =  (Integer) (session.getAttribute("userID"));
			 
			List<Animals> commonPets = peopleDAO.getCommonPets(userID);
			
					
			request.setAttribute("commonPets", commonPets );
			
			
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("listCommonPets.jsp");
	        dispatcher.forward(request, response);
	        System.out.println("logged in with " + session.getAttribute("userName"));
    	}
    }
    
	private void topReviewers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Review> reviewers = peopleDAO.getTopReviewers();
        List<String> ownerFullname = new ArrayList<String>();
        
        //Display owner first and last name (fullname)
        for (Review reviewer : reviewers) {
        	int id = reviewer.getOwnerID();
			String fullName = peopleDAO.getUserFullName(id);
			ownerFullname.add(fullName);
		}
           
        request.setAttribute("reviewers", reviewers);   
        request.setAttribute("ownerFullName", ownerFullname);
        RequestDispatcher dispatcher = request.getRequestDispatcher("topReviewers.jsp");       
        dispatcher.forward(request, response);
    }
	
	//Show top reviewed animals and include their rating
	private void topAnimals(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Review> topAnimalReviews = peopleDAO.getTopAnimals();
        List<Integer> reviewScores = new ArrayList<Integer>();
        List<Animals> topAnimals = new ArrayList<Animals>();
        
        //Get a list of the top animals from the reviews
        for (Review review : topAnimalReviews) {
			topAnimals.add(peopleDAO.getAnimal(review.getAnimalID()));
			reviewScores.add(review.getReviewScore());
		}
        request.setAttribute("reviewScores", reviewScores);   
        animalListForm(request,response, topAnimals);
    }
	
    //Show two traits the user can select from to list species who have posted both species 
    private void listUserBySpecies(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	if(authenticate(request, response)) {
	        List<String> speciesName = peopleDAO.getSpeciesNames();
	        java.util.Collections.sort(speciesName);
	        request.setAttribute("speciesName", speciesName);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("speciesSearchForm.jsp");       
	        dispatcher.forward(request, response);
    	}
    }
    
    private void searchUserBySpecies(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	if(authenticate(request, response)) {
	        String species1 = request.getParameter("species1");
	        String species2 = request.getParameter("species2");
	        List<Integer> searchByTrait = peopleDAO.getUserBySpecies(species1, species2);
	        List<People> users = new ArrayList<People>();
	        for (Integer userID : searchByTrait) {
		        users.add(peopleDAO.getUser(userID));
			}
	        request.setAttribute("users", users);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("usersBySpecies.jsp");       
	        dispatcher.forward(request, response);
    	}
    }
    
	private void notRidicAdorbs(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
		if(authenticate(request, response)) {
	        List<Integer> notRidicAdorbsUsers = peopleDAO.getNotRidicAdorbs();
	        List<People> users = new ArrayList<People>();
	        for (Integer userID : notRidicAdorbsUsers) {
		        users.add(peopleDAO.getUser(userID));
			}
	        request.setAttribute("users", users);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("usersBySpecies.jsp");       
	        dispatcher.forward(request, response);
		}
    }
	
	private void notCrayCray(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
		if(authenticate(request, response)) {
	        List<Integer> notRidicAdorbsUsers = peopleDAO.getNotCrayCray();
	        List<People> users = new ArrayList<People>();
	        for (Integer userID : notRidicAdorbsUsers) {
		        users.add(peopleDAO.getUser(userID));
			}
	        request.setAttribute("users", users);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("usersBySpecies.jsp");       
	        dispatcher.forward(request, response);
		}
    }
	
	private void negativeReviewers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
		if(authenticate(request, response)) {
	        List<Integer> negativeReviewers = peopleDAO.getNegativeReviewers();
	        List<People> users = new ArrayList<People>();
	        for (Integer userID : negativeReviewers) {
		        users.add(peopleDAO.getUser(userID));
			}
	        request.setAttribute("users", users);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("usersBySpecies.jsp");       
	        dispatcher.forward(request, response);
		}
    }
	
	private void adoptionCrate(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
		if(authenticate(request, response)) {
			int userID =  (Integer) (session.getAttribute("userID"));
			List<Animals> savedAnimals = peopleDAO.getSavedAnimal(userID);
			int totalPrice = 0;
			for (Animals animal : savedAnimals) {
				totalPrice += animal.adoptionPrice;
			}
			request.setAttribute("totalPrice", totalPrice);   
		    request.setAttribute("savedAnimals", savedAnimals); 
		    RequestDispatcher dispatcher = request.getRequestDispatcher("adoptionCrate.jsp");       
	        dispatcher.forward(request, response);
		}
    }
	
	private void deleteSavedAnimals(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
		if(authenticate(request, response)) {
			int userID =  (Integer) (session.getAttribute("userID"));
			List<Animals> savedAnimals = peopleDAO.getSavedAnimal(userID);
			for (Animals animal : savedAnimals) {
				peopleDAO.deleteAnimal(animal.getId());
			}
			response.sendRedirect("welcome");
		}
    }
	
//	private void noCrayCrayUsers(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, IOException, ServletException {
//        List<Integer> negativeReviewers = peopleDAO.getNegativeReviewers();
//        List<People> users = new ArrayList<People>();
//        for (Integer userID : negativeReviewers) {
//	        users.add(peopleDAO.getUser(userID));
//		}
//        request.setAttribute("users", users);       
//        RequestDispatcher dispatcher = request.getRequestDispatcher("usersBySpecies.jsp");       
//        dispatcher.forward(request, response);
//    }
}