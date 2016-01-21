package mmws;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebParam;

@WebService
public interface MMWsrv {
	public boolean addMailbox (String owner);
	public boolean delMailbox (String owner);
}
  