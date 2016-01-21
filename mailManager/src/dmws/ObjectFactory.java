
package dmws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the dmws package. 
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

    private final static QName _Login_QNAME = new QName("http://dmws/", "login");
    private final static QName _LookupRights_QNAME = new QName("http://dmws/", "lookupRights");
    private final static QName _LoginResponse_QNAME = new QName("http://dmws/", "loginResponse");
    private final static QName _LookupRightsResponse_QNAME = new QName("http://dmws/", "lookupRightsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: dmws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link LookupRightsResponse }
     * 
     */
    public LookupRightsResponse createLookupRightsResponse() {
        return new LookupRightsResponse();
    }

    /**
     * Create an instance of {@link LookupRights }
     * 
     */
    public LookupRights createLookupRights() {
        return new LookupRights();
    }

    /**
     * Create an instance of {@link Login }
     * 
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Login }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dmws/", name = "login")
    public JAXBElement<Login> createLogin(Login value) {
        return new JAXBElement<Login>(_Login_QNAME, Login.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LookupRights }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dmws/", name = "lookupRights")
    public JAXBElement<LookupRights> createLookupRights(LookupRights value) {
        return new JAXBElement<LookupRights>(_LookupRights_QNAME, LookupRights.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dmws/", name = "loginResponse")
    public JAXBElement<LoginResponse> createLoginResponse(LoginResponse value) {
        return new JAXBElement<LoginResponse>(_LoginResponse_QNAME, LoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LookupRightsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dmws/", name = "lookupRightsResponse")
    public JAXBElement<LookupRightsResponse> createLookupRightsResponse(LookupRightsResponse value) {
        return new JAXBElement<LookupRightsResponse>(_LookupRightsResponse_QNAME, LookupRightsResponse.class, null, value);
    }

}
