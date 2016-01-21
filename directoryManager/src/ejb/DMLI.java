package ejb;

import javax.ejb.Remote;
import entity.User;

@Remote public interface DMLI {
    //Lookup for user rights
    public String lookupRights(String un);
    
    //Authenticate user prior to read mail
    public boolean authenticate(String un, String p);

}
