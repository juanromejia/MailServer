
package mmws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10-b140803.1500
 * Generated source version: 2.2
 * 
 */
@WebService(name = "MMWsrv", targetNamespace = "http://mmws/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface MMWsrv {


    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "addMailbox", targetNamespace = "http://mmws/", className = "mmws.AddMailbox")
    @ResponseWrapper(localName = "addMailboxResponse", targetNamespace = "http://mmws/", className = "mmws.AddMailboxResponse")
    @Action(input = "http://mmws/MMWsrv/addMailboxRequest", output = "http://mmws/MMWsrv/addMailboxResponse")
    public boolean addMailbox(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "delMailbox", targetNamespace = "http://mmws/", className = "mmws.DelMailbox")
    @ResponseWrapper(localName = "delMailboxResponse", targetNamespace = "http://mmws/", className = "mmws.DelMailboxResponse")
    @Action(input = "http://mmws/MMWsrv/delMailboxRequest", output = "http://mmws/MMWsrv/delMailboxResponse")
    public boolean delMailbox(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}
