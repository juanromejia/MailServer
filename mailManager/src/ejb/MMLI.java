package ejb;

import javax.ejb.Remote;

@Remote public interface MMLI {
    
    public boolean createMailbox(String owner);
    public boolean deleteMailbox(String owner);
}
