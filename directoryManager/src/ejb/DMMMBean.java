package ejb;

import mmws.*;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.*;

@Stateless(name="ejb/DMLI")
public class DMMMBean implements DMLI {

    @PersistenceContext(unitName="pu1")
    private EntityManager em;
    
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
