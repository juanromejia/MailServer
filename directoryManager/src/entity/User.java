package entity;

import java.io.Serializable;
import javax.persistence.*;
import static javax.persistence.CascadeType.*;

//Main user class where we define the structure of the table used in the directory manager
@Entity
public class User implements Serializable {

    private String username, passwd;
    private boolean write, read;

    @Id
    public String getUsername() {
        //Username is the primary key for the user table
        return username;
    }

    public void setUsername(String un) {
    	//This functions sets the username of an specific user in the table
        this.username = un;
    }

    public String getPassword() {
    	//This functions returns the password of the object referred
        return passwd;
    }

    public void setPassword(String newPass) {
    	//This functions sets a new password of an specific user in the table
        this.passwd = newPass;
    }

    public boolean getWriteRight() {
        //This functions returns the right of the user to write to the newsletter group
        return write;
    }

    public boolean getReadRight() {
    	//This functions returns the right of the user to read the newsletter group
        return read;
    }

    public void setWriteRight(boolean newWrite) {
        //This functions sets the user right to write to the newsletter group
        this.write = newWrite;
    }

    public void setReadRight(boolean newRead) {
        //This functions sets the user right to read the newsletter group
        this.read = newRead;
    }
   
}
