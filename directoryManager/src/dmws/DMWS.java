package dmws;
import java.net.*;
import java.io.*;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;
import javax.naming.InitialContext;
import java.util.List;
import javax.naming.*;
import ejb.DMLI;


@WebService(endpointInterface="dmws.DMWsrv")
public class DMWS {
	DMLI dml;

	public DMWS() {}

	public boolean login (String usr, String pwd) {
			try {
			InitialContext ic = new InitialContext();
            dml = (DMLI) ic.lookup("ejb.DMLI");            
			return dml.authenticate(usr,pwd);
		 	}catch(Exception e){
		 		System.out.println(e.getMessage());
		 		return false;
		 	}
			
	}
	public String lookupRights (String usr) {
			try {
			InitialContext ic = new InitialContext();
            dml = (DMLI) ic.lookup("ejb.DMLI");            
			return dml.lookupRights(usr);
		 	}catch(Exception e){
		 		System.out.println(e.getMessage());
		 		return "Remote connection succesful, cant connect locally to DM Service";
		 	}
			
	}
	
} 