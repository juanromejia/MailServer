
package mmws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10-b140803.1500
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "MMWSService", targetNamespace = "http://mmws/", wsdlLocation = "http://157.159.15.73:9001/MMWsrv?wsdl")
public class MMWSService
    extends Service
{

    private final static URL MMWSSERVICE_WSDL_LOCATION;
    private final static WebServiceException MMWSSERVICE_EXCEPTION;
    private final static QName MMWSSERVICE_QNAME = new QName("http://mmws/", "MMWSService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://157.159.15.73:9001/MMWsrv?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        MMWSSERVICE_WSDL_LOCATION = url;
        MMWSSERVICE_EXCEPTION = e;
    }

    public MMWSService() {
        super(__getWsdlLocation(), MMWSSERVICE_QNAME);
    }

    public MMWSService(WebServiceFeature... features) {
        super(__getWsdlLocation(), MMWSSERVICE_QNAME, features);
    }

    public MMWSService(URL wsdlLocation) {
        super(wsdlLocation, MMWSSERVICE_QNAME);
    }

    public MMWSService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, MMWSSERVICE_QNAME, features);
    }

    public MMWSService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MMWSService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns MMWsrv
     */
    @WebEndpoint(name = "MMWSPort")
    public MMWsrv getMMWSPort() {
        return super.getPort(new QName("http://mmws/", "MMWSPort"), MMWsrv.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns MMWsrv
     */
    @WebEndpoint(name = "MMWSPort")
    public MMWsrv getMMWSPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://mmws/", "MMWSPort"), MMWsrv.class, features);
    }

    private static URL __getWsdlLocation() {
        if (MMWSSERVICE_EXCEPTION!= null) {
            throw MMWSSERVICE_EXCEPTION;
        }
        return MMWSSERVICE_WSDL_LOCATION;
    }

}
