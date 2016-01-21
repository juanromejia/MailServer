package mmws;
import java.net.*;
import java.io.*;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;
import javax.naming.InitialContext;
import java.util.List;
import javax.naming.*;
import ejb.MMLI;


@WebService(endpointInterface="mmws.MMWsrv")
public class MMWS {
	MMLI mml;

	public MMWS() {}

	public boolean addMailbox (String owner) {
			try {
			/*InitialContext ctx = new InitialContext();
			NamingEnumeration<NameClassPair> list = ctx.list("");
			while (list.hasMore()) {
  			System.out.println(list.next().getName());
			}	
			*/
			InitialContext ic = new InitialContext();
            mml = (MMLI) ic.lookup("ejb.MMLI");  
			return mml.createMailbox(owner);
		 	}catch(Exception e){
		 		System.out.println(e.getMessage());
		 		return false;
		 	}
			
	}
	public boolean delMailbox (String owner) {
			try {
			System.out.println("Starting deleting procedure for "+owner+" mailbox");
			InitialContext ic = new InitialContext();
            mml = (MMLI) ic.lookup("ejb.MMLI"); 
		 	System.out.println("Deleting "+owner+" mailbox");
			return mml.deleteMailbox(owner);
		 	}catch(Exception e){
		 		System.out.println(e.getMessage());
		 		return false;
		 	}
			
	}
	
} 