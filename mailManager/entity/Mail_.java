package entity;

import entity.Mailbox;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.0.v20150330-rNA", date="2015-11-16T14:04:39")
@StaticMetamodel(Mail.class)
public class Mail_ { 

    public static volatile SingularAttribute<Mail, Timestamp> datetime;
    public static volatile SingularAttribute<Mail, Mailbox> mailbox;
    public static volatile SingularAttribute<Mail, String> receiver;
    public static volatile SingularAttribute<Mail, String> sender;
    public static volatile SingularAttribute<Mail, String> subject;
    public static volatile SingularAttribute<Mail, Integer> id;
    public static volatile SingularAttribute<Mail, String> body;
    public static volatile SingularAttribute<Mail, Boolean> status;

}