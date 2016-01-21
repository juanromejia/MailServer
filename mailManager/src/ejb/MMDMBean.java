package ejb;

import javax.ejb.*;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.naming.InitialContext;

import entity.*;


//@Stateful(mappedName = "MMLI")
@Stateful
public class MMDMBean implements MMLI {



    @PersistenceContext(unitName="pu1")
    private EntityManager em;
    String userSession;
    boolean authenticated,rp,wp;


    //Add a new mailbox to the database
    public boolean createMailbox(String owner) {
        try{
                Mailbox mb = findMailbox(owner);
                //If it already exists... Error!
                String o = mb.getOwner();
                if(o.equals("owner")){
                    return false;
                }
                      
            }catch(Exception e){
                // Create the mailbox for the specified owner
                Mailbox mbox = new Mailbox();
                mbox.setOwner(owner);
                //Persists the mailbox          
                em.persist(mbox);
                    return true; 
            }
        //}
        return false; 
    }

    //Delete a mailbox (when a user is deleted)
    public boolean deleteMailbox(String owner){
            try{
                Mailbox mb = findMailbox(owner);
                Mailbox mb0 = em.merge(mb);
                em.remove(mb0);
                em.flush();
                return true;
            }catch(Exception e){
                return false;
            }
    }

    
    
    //Find a mail in the database
    public Mail findMail(int id) {
        Query q = em.createQuery("select m from Mail m where m.id = :id");  
        q.setParameter("id", id);
        return (Mail)q.getSingleResult();
    }

    //Find a mailbox in the database
    public Mailbox findMailbox(String owner) { 
        Query q = em.createQuery("select mb from Mailbox mb where mb.owner = :owner");  
        q.setParameter("owner", owner);
        return (Mailbox)q.getSingleResult();

    }
}
