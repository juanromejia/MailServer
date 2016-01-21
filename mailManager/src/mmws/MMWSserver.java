package mmws;
import javax.xml.ws.Endpoint;

public class MMWSserver {
	

    protected MMWSserver(String ip) throws Exception {
        // START SNIPPET: publish
        System.out.println("Starting Server");
        MMWS inst = new MMWS();
        String address = "http://"+ip+":9001/MMWsrv";
        Endpoint.publish(address, inst);
        // END SNIPPET: publish
    }

    public static void main(String args[]) throws Exception {
        new MMWSserver(args[0]);
        System.out.println("Server ready...");
        System.out.println("Press Enter to stop the server. ");
        System.in.read();
        System.out.println("Server stopped.");
        System.out.println("Server exiting");
        System.exit(0);
    }
}
