
package mmws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mmws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AddMailbox_QNAME = new QName("http://mmws/", "addMailbox");
    private final static QName _DelMailbox_QNAME = new QName("http://mmws/", "delMailbox");
    private final static QName _DelMailboxResponse_QNAME = new QName("http://mmws/", "delMailboxResponse");
    private final static QName _AddMailboxResponse_QNAME = new QName("http://mmws/", "addMailboxResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mmws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DelMailbox }
     * 
     */
    public DelMailbox createDelMailbox() {
        return new DelMailbox();
    }

    /**
     * Create an instance of {@link DelMailboxResponse }
     * 
     */
    public DelMailboxResponse createDelMailboxResponse() {
        return new DelMailboxResponse();
    }

    /**
     * Create an instance of {@link AddMailbox }
     * 
     */
    public AddMailbox createAddMailbox() {
        return new AddMailbox();
    }

    /**
     * Create an instance of {@link AddMailboxResponse }
     * 
     */
    public AddMailboxResponse createAddMailboxResponse() {
        return new AddMailboxResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddMailbox }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mmws/", name = "addMailbox")
    public JAXBElement<AddMailbox> createAddMailbox(AddMailbox value) {
        return new JAXBElement<AddMailbox>(_AddMailbox_QNAME, AddMailbox.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DelMailbox }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mmws/", name = "delMailbox")
    public JAXBElement<DelMailbox> createDelMailbox(DelMailbox value) {
        return new JAXBElement<DelMailbox>(_DelMailbox_QNAME, DelMailbox.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DelMailboxResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mmws/", name = "delMailboxResponse")
    public JAXBElement<DelMailboxResponse> createDelMailboxResponse(DelMailboxResponse value) {
        return new JAXBElement<DelMailboxResponse>(_DelMailboxResponse_QNAME, DelMailboxResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddMailboxResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mmws/", name = "addMailboxResponse")
    public JAXBElement<AddMailboxResponse> createAddMailboxResponse(AddMailboxResponse value) {
        return new JAXBElement<AddMailboxResponse>(_AddMailboxResponse_QNAME, AddMailboxResponse.class, null, value);
    }

}
