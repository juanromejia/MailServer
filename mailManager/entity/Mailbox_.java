package entity;

import entity.Mail;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.0.v20150330-rNA", date="2015-11-16T14:04:40")
@StaticMetamodel(Mailbox.class)
public class Mailbox_ { 

    public static volatile SingularAttribute<Mailbox, String> owner;
    public static volatile ListAttribute<Mailbox, Mail> mails;

}