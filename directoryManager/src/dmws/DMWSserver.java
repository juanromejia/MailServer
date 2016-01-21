package dmws;
import javax.xml.ws.Endpoint;

public class DMWSserver {
	

    protected DMWSserver(String ip) throws Exception {
        // START SNIPPET: publish
        System.out.println("Starting Server");
        DMWS inst = new DMWS();
        String address = "http://"+ip+":9000/DMWsrv";
        Endpoint.publish(address, inst);
        // END SNIPPET: publish
    }

    public static void main(String args[]) throws Exception {
        new DMWSserver(args[0]);
        System.out.println("Server ready...");
        System.out.println("Press Enter to stop the server. ");
        System.in.read();
        System.out.println("Server stopped.");
        System.out.println("Server exiting");
        System.exit(0);
    }
}
