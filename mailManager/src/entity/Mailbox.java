package entity;

import java.io.Serializable;
import javax.persistence.*;
import static javax.persistence.CascadeType.*;

import java.util.List;
import java.util.ArrayList;

//Mailbox object, it will allocate mails of different users. 
//Identifying the group of mails by the owner name
@Entity
public class Mailbox implements Serializable {

    private String owner;
    private List<Mail> mails = new ArrayList<Mail>();

    //Getters and setters (owner as primary key)
    @Id
    public String getOwner() {
        return owner;
    }

    public void setOwner(String o) {
        this.owner = o;
    }

    @OneToMany(cascade=CascadeType.ALL, mappedBy="mailbox", orphanRemoval=true)
    public List<Mail> getMails() {
        return mails;
    }

    public void setMails(List<Mail> newValue) {
        this.mails = newValue;
    }

}
