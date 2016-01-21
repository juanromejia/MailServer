package ejb;

import javax.ejb.Remote;
import java.util.List;

@Remote public interface MMRMI {
    //Displays the session info
    public String getSessionInfo();

    //Send a mail
    public String sendMail(String to, String sub, String b);

    //Delete a mail
    public String delMail(int id);

    //Read a mail
    public String readMail(int id);

    //Get unread mails
    public String getUnreadMails();

    //Get all mails
    public String getAllMails();

    //Authenticate function to set session variables
    public boolean logIn(String usr, String pwd);
    
}
