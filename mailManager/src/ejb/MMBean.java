package ejb;

import dmws.*;
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
public class MMBean implements MMRMI {



    @PersistenceContext(unitName="pu1")
    private EntityManager em;
    private DMWsrv dmwsclient;
    String userSession;
    boolean authenticated,rp,wp;


    //Add a new mail to the database
    public String sendMail(String to, String sub, String b) {
    	if (authenticated) {
            try{
                if(to.equals("newsgroup")){
                    String permissions = dmwsclient.lookupRights(userSession);
                    if(wRight(permissions)){
                        sendNewsgroupMail(sub,b);
                    }else{
                        return "User "+userSession+" doesn't have the rights to send this email";
                    }
                }else{
                    Mailbox mb = findMailbox(to);
                    // Create the mail
                    Mail m = new Mail();
                    java.sql.Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
                    m.setDatetime(ts);
                    m.setSender(userSession);
                    m.setReceiver(to);
                    m.setSubject(sub);
                    m.setBody(b);
                    m.setStatus(false); //not read

                    // Associate the mail with a mailbox. The association 
                    // must be set on both sides of the relationship: on the 
                    // mailbox side for the mails to be persisted when 
                    // transaction commits, and on the mail side because it 
                    // is the owning side.            
                    mb.getMails().add(m);
                    m.setMailbox(mb);
                }

                em.flush();
                return "Mail sent to "+to+" successfully";       
            }catch(Exception e){
                //Mailbox doesnt exists
                return "Error: Destination user ("+to+") doesn't exists";
            }
        }
        return "Not authenticated";
    }

    //This method gets called when someone with write permissions to the newsgroup wants to send an email to the group
    //It sends the email desired to all the users that have read permissions
    public void sendNewsgroupMail(String sub, String b){
        if (authenticated) {
            Query q = em.createQuery("select mb from Mailbox mb");
            List results = q.getResultList();
            java.sql.Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());

            for(int i = 0; i < results.size(); i++){
                Mailbox mb = (Mailbox)results.get(i);
                String mbOwner = mb.getOwner();
                String permissions = dmwsclient.lookupRights(mbOwner);
                if(rRight(permissions)){
                    Mail m = new Mail();
                    m.setDatetime(ts);
                    m.setSender(userSession);
                    m.setReceiver("newsgroup");
                    m.setSubject(sub);
                    m.setBody(b);
                    m.setStatus(false); //not read

                    mb.getMails().add(m);
                    m.setMailbox(mb);
                }
            }
        }
    }

    //Delete a mail
    public String delMail(int id){
        if (authenticated) {
            try{
                Mail m = findMail(id);
                Mailbox mb = m.getMailbox();
                if(userSession.equals(mb.getOwner())){
                    List<Mail> mailsList = mb.getMails();
                    int mailsListSize = mailsList.size();

                    for(int i = 0; i < mailsListSize; i++){
                        if(mailsList.get(i).getId() == id){
                            mailsList.remove(i);
                            mailsListSize--;
                        }
                    }
                    mb.setMails(mailsList);
                }else{
                    return "Mail doesn't exists in your mail's list";
                }
                return "Mail deleted successfully";
            }catch(Exception e){
                return "Mail doesn't exists";
            }
        }
        return "Not authenticated";
    }

    //Read a mail
    public String readMail(int id){
        if (authenticated) {
            try{
                Mail m = findMail(id);
                Mailbox mb = m.getMailbox();
                if(userSession.equals(mb.getOwner())){
                    m.setStatus(true); //mail read
                    return mailToString(m);
                }else{
                    return "Mail doesn't exists in your mail's list";
                }
            }catch(Exception e){
                return "Mail not found";
            }
        }
        return "Not authenticated";
    }

    //Get mails unread
    public String getUnreadMails(){
        if (authenticated) {
            Mailbox mb = findMailbox(userSession);
            List<Mail> results = mb.getMails();
            String s = "";
            for(int i = 0; i < results.size(); i++){
                Mail m = (Mail)results.get(i);
                if(!m.getStatus()){
                    s = s+toString(m);
                }
            }
            if(s.equals("")){
                return "No new mails";
            }
            return s;
        }
        return "Not authenticated";
    }

    //Get all mails
    public String getAllMails(){
        if (authenticated) {
            Mailbox mb = findMailbox(userSession);
            List<Mail> results = mb.getMails();
            String s = "";
            for(int i = 0; i < results.size(); i++){
                Mail m = (Mail)results.get(i);
                s = s+toString(m);
            }
            if(s.equals("")){
                return "No mails";
            }
            return s;
            }
        return "Not authenticated";
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

    public String mailToString(Mail m){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String date = sdf.format(m.getDatetime());
        return "\nMail ID: "+m.getId()+"\nDate: "+date+"\nFrom: "+m.getSender()+"\nTo: "+m.getReceiver()+"\nSubject:\n"+m.getSubject()+"\nBody:\n"+m.getBody();
    }

    public String toString(Mail m){
        return "\nMail ID: "+m.getId()+"\tFrom: "+m.getSender()+"\tSubject: "+m.getSubject();
    }

    //Ensures that someone has initiated session before doing anything
    public boolean logIn(String usr, String pwd){
        authenticated=false;
        try{
            dmwsclient = new DMWSService().getDMWSPort();
            authenticated = dmwsclient.login(usr,pwd);
        }catch(Exception e) {
                return false;
            }
        if (authenticated) {
            userSession=usr;
            String permissions = dmwsclient.lookupRights(usr);
            wp = wRight(permissions);
            rp = rRight(permissions);  
            return true;
        }
        return false;
    }

    //Look for write permissions
    public boolean wRight(String s){
        String rights[] = s.split(" -- ");
        String write[] = rights[0].split(" = ");
        return Boolean.parseBoolean(write[1]);
    }

    //Look for read permissions
    public boolean rRight(String s){
        String rights[] = s.split(" -- ");
        String read[] = rights[1].split(" = ");
        return Boolean.parseBoolean(read[1]);
    }

    public String getSessionInfo(){
        return "User: "+userSession+"\nAuthenticated? "+authenticated+"\nAble to write? "+wp+"\nAble to read? "+rp;
    }
}