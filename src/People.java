

public class People {
   // protected int id;
    protected String username;
    protected String userpassword;
    protected String firstname;
    protected String lastname;
    protected String emailaddress;
 
    public People() {
    }
    
    public People(String username, String userpassword, String firstname, String lastname, String emailaddress) {
      this.username = username;
      this.userpassword = userpassword;
      this.firstname = firstname;
      this.lastname = lastname;
      this.emailaddress = emailaddress;
      
  }
 
//    public People(int id) {
//        this.id = id;
//    }
// 
//    public People(int id, String name, String address, String status) {
//        this(name, address, status);
//        this.id = id;
//    }
//     
//    public People(String name, String address, String status) {
//        this.name = name;
//        this.address = address;
//        this.status = status;
//    }
 
//    public int getId() {
//        return id;
//    }
// 
//    public void setId(int id) {
//        this.id = id;
//    }
 
    public String getUserName() {
        return username;
    }
 
    public void setUserName(String username) {
        this.username = username;
    }
 
    public String getUserPassWord() {
        return userpassword;
    }
 
    public void setUserPassword(String userpassword) {
        this.userpassword = userpassword;
    }
    
    public String getFirstName() {
        return firstname;
    }
 
    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }
    
    public String getLastName() {
        return lastname;
    }
 
    public void setLastName(String lastname) {
        this.lastname = lastname;
    }
    
    public String getEmailAddress() {
        return emailaddress;
    }
 
    public void setEmailAddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }
 
   
}