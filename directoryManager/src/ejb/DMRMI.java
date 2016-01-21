package ejb;

import javax.ejb.Remote;
import entity.User;

@Remote public interface DMRMI {
    //Insert a user
    public String addUser(String un, String pwd, boolean w, boolean r);

    //Delete a user
    public String delUser(String un);

    //Update user rights
    public String updateRights(String un, boolean w, boolean r);

    //Lookup for user rights
    public String lookupRights(String un);

    //List all users in database
    public String listAllUsers();

}
