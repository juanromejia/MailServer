package ejb;

import mmws.*;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.List;

import entity.*;

@Stateless(name="ejb/DMRMI")
public class DMBean implements DMRMI {

    @PersistenceContext(unitName="pu1")
    private EntityManager em;
    
    //Adds a new user (checks if it doesnt already exists)
    public String addUser(String un, String pwd, boolean w, boolean r) {
    	User u = findUser(un);

        //Check if user exists before adding
        if (!checkUserExistence(u)) {
            //Add user mailbox through webservices call
            try{
            MMWsrv mmwsclient = new MMWSService().getMMWSPort();
            mmwsclient.addMailbox(un);
            }catch(Exception e)
            {
                return "Failed to connect to Mailbox Manager";
            }
            //Create the user
        	u = new User();
        	u.setUsername(un);
        	u.setPassword(pwd);
        	u.setWriteRight(w);
        	u.setReadRight(r);
            //Add to database
            em.persist(u);
            return "OK";
         }
        
        //User doesnt exitis
        return "Error: User "+un+" already exists";

    }

    //Delete a given user from the database (admin user can't be deleted)
    public String delUser(String un) {
    	//Find user in the database
    	User u = findUser(un);

        //Check if user exists before deleting
        if (checkUserExistence(u)) {
            //Attempt to delete the mailbox before deleting user
            try{
            MMWsrv mmwsclient = new MMWSService().getMMWSPort();
            mmwsclient.delMailbox(un);
            }catch(Exception e)
            {
                return "Failed to connect to Mailbox Manager";
            }
            //Merge the user to the persistence context
            User u0 = em.merge(u);
            //Delete from database
            em.remove(u0);
            return "OK";                
        }
        
        //User doesnt exitis
        return "Error: User "+un+" doesn't exists";
    }

    //Change the rights of a given user (admin's rights can't be updated)
    public String updateRights(String un, boolean w, boolean r) {
    	//Find user in the database
    	User u = findUser(un);
       
        //Check if user exists before updating rights       
        if (checkUserExistence(u)) {
            //Merge the user to the persistence context
            User u0 = em.merge(u);
            //Update rights
            u0.setWriteRight(w);
        	u0.setReadRight(r);
            //Save changes to database
            em.persist(u0);
            return "OK";
        }

        //User doesnt exitis
        return "Error: User "+un+" doesn't exists";
        
    }

    //Look for the rights of a given user
    public String lookupRights(String un) {
    	//Find user in the database
    	User u = findUser(un);        
        //Check if user exists before lookup rights     
        if (checkUserExistence(u)) {
            //Merge the user to the persistence context
            User u0 = em.merge(u);

            //lookup rights
            boolean w = u0.getWriteRight();
        	boolean r = u0.getReadRight();

            return "Write permission = "+w+" -- Read permission = "+r;
        }

        //User doesnt exitis
        return "Error: User "+un+" doesn't exists";
    }

    //List all users
    public String listAllUsers(){
        Query q = em.createQuery("select u from User u");
        List results = q.getResultList();
        String s = "";
    
        for(int i = 0; i < results.size(); i++){
            User u = (User)results.get(i);
            s = s + "\n" + (i+1) + ". "+ u.getUsername();
        }
        if(s.equals("")){
            s = s + "NO USERS IN DATABASE";
        }
        return s;
    }
    
    //Find a user in the database
    public User findUser(String un) {
        User uf = new User();

        try {        
            Query q = em.createQuery("select u from User u where u.username = :username");  
            q.setParameter("username", un);
            uf = (User)q.getSingleResult();
        } catch (Exception e) {}
        
        return uf;

    }

    public boolean checkUserExistence(User ue) {
        boolean bool = false;
        
        try {
            String uename = ue.getUsername();
            bool = !uename.equals("");
        } catch (Exception e) {}            
        
        return bool;
        
    }

    //Check is an entered user exists and if its password is correct
    public boolean authenticate(String un, String p) {
        boolean bool = false;
        User u = findUser(un);
        if (checkUserExistence(u)) {
            User u0 = em.merge(u);
            String pass = u0.getPassword();
            if (pass.equals(p)) {
                bool = true;
            }
        }
        return bool;

    }


}
