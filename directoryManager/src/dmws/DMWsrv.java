package dmws;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebParam;

@WebService
public interface DMWsrv {
	
	public boolean login (String usr, String pwd);
	public String lookupRights (String usr);
}
  