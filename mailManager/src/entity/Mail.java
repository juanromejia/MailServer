package entity;

import javax.persistence.*;

//Main mail class where we define the structure of the table used in the mailbox manager
@Entity
@Table(name="MAIL_TABLE")
public class Mail{

	private int id;
	private java.sql.Timestamp datetime;
    private String sender, receiver, subject, body;
    private boolean status;
    private Mailbox mailbox;

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="MAIL_ID")
    public int getId() {
        //Id is the primary key for the user table
        return id;
    }

    public void setId(int id) {
        //This functions sets a unique ID to a mail
        this.id = id;
    }

    @Column(name="MAIL_DATETIME")
    public java.sql.Timestamp getDatetime() {
    	//This functions returns the date of the object referred
        return datetime;
    }

    public void setDatetime(java.sql.Timestamp ts) {
    	//This functions sets the DATE and TIME to a mail
        this.datetime = ts;
    }

    @Column(name="MAIL_SENDER")
    public String getSender() {
    	//This functions returns the sender of the object referred
        return sender;
    }

    public void setSender(String s) {
    	//This functions sets the sender of an specific mail
        this.sender = s;
    }

    @Column(name="MAIL_OWNER")
    public String getReceiver() {
    	//This functions returns the receiver of the object referred
        return receiver;
    }

    public void setReceiver(String r) {
    	//This functions sets the receiver of an specific mail
        this.receiver = r;
    }
   	
   	@Column(name="MAIL_SUBJECT")
   	public String getSubject() {
    	//This functions returns the subject of the object referred
        return subject;
    }

    public void setSubject(String sub) {
    	//This functions sets the subject of an specific mail
        this.subject = sub;
    }

    @Column(name="MAIL_BODY")
    public String getBody() {
    	//This functions returns the body of the object referred
        return body;
    }

    public void setBody(String b) {
    	//This functions sets the body of an specific mail
        this.body = b;
    }

    @Column(name="MAIL_STATUS")
    public boolean getStatus() {
    	//This functions returns the status of a mail
        return status;
    }

    public void setStatus(boolean s) {
    	//This functions sets if a mail is read (true) or unread (false)
        this.status = s;
    }

    @ManyToOne()
    @JoinColumn(name="MAILBOX_ID")
    public Mailbox getMailbox() {
    	//This functions returns the receiver of the object referred
        return mailbox;
    }

    public void setMailbox(Mailbox mb) {
    	//This functions sets the receiver of an specific mail
        this.mailbox = mb;
    }
}