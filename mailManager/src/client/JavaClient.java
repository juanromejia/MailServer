package client;

import javax.naming.InitialContext;
import java.util.Scanner;
import javax.ejb.*;
import ejb.MMRMI;

public class JavaClient {
	public static void main(String args[]) {
		MMRMI sb;
		String sub, option, usr, pwd, body;
		int id; 
		boolean w, r;

	    try {
			InitialContext ic = new InitialContext();
			//sb = (MMRMI) ic.lookup("ejb.MMRMI");
			sb = (MMRMI) ic.lookup("ejb.MMRMI");
			Scanner scanner = new Scanner(System.in);
			Runtime.getRuntime().exec("clear");
			System.out.println("\n\n========================================================================================\n========================================================================================");
			boolean logged = false;

			System.out.println("\nPress Enter to start the test cases...");
			System.in.read();

			System.out.println("Login with Laura (She has read and write permissions for the newsgroup)"); //true true
			System.out.println("Server response... "+sb.logIn("Laura","123"));

			System.out.println("\nSend mail to Julia");
			System.out.println("Server response... "+ sb.sendMail("Julia","Meeting reminder","Hello Julia, I'm writting this mail to remember you to come to the reunion next friday in the office 501"));

			System.out.println("\nSend mail to the newsgroup");
			System.out.println("Server response... "+ sb.sendMail("newsgroup","Norway trip","Hello everyone! Next week we are going to Norway, don't forget to buy thermal cloth"));

			System.out.println("\nGet all Laura's mails");
			System.out.println("Server response... "+ sb.getAllMails());

			System.out.println("\nGet unread Laura's mails");
			System.out.println("Server response... "+ sb.getUnreadMails());



			System.out.println("\nLogin with Bryan (He does not have any permissions for the newsgroup)"); //false false
			System.out.println("Server response... "+sb.logIn("Bryan","123"));
			
			System.out.println("\nSend mail to Julia");
			System.out.println("Server response... "+ sb.sendMail("Julia","Informal mail","Hi, just wanna say that we miss you in Fred's party!"));

			System.out.println("\nSend mail to newsgroup");
			System.out.println("Server response... "+ sb.sendMail("newsgroup","Just trying","I know that I'm not able to send this mail"));

			System.out.println("\nGet all Bryan's mails");
			System.out.println("Server response... "+ sb.getAllMails());

			

			System.out.println("\nLogin with Julia (She only has read permission for the newsgroup)"); //false true
			System.out.println("Server response... "+sb.logIn("Julia","123"));
			
			System.out.println("\nGet unread Julia's mails");
			System.out.println("Server response... "+ sb.getUnreadMails());

			System.out.println("\nSend mail to newsgroup");
			System.out.println("Server response... "+ sb.sendMail("newsgroup","Memo","I will be glad if you came on time for the meeting"));

			System.out.println("\nJulia requires to have write permissions for the newsgroup");
			System.out.println("TIME TO CHANGE SOME RIGHTS...");
			System.in.read();

			System.out.println("\nSend mail to newsgroup");
			System.out.println("Server response... "+ sb.sendMail("newsgroup","Memo","I will be glad if you came on time for the meeting"));

			System.out.println("\nGet all Julia's mails");
			System.out.println("Server response... "+ sb.getAllMails());



			System.out.println("\nOK ENOUGH, START THE INTERACTIVE MENU...");
			System.in.read();

			logged = false;

			do {
				login: System.out.println("Login");
				System.out.println("Enter username");
				usr = scanner.next();
				System.out.println("Enter password");
				pwd = scanner.next();
				logged = sb.logIn(usr,pwd);
				if (!logged) {
					System.out.println("Failed authentication, incorrect user or password. (Directoy manager server may be down)\n");
					Thread.sleep(1000);
				}
			} while (!logged);

			menu: while (true) {
				//Menu
				System.out.println("\n\n======== MENU ========\n\n1.Send Mail\n2.Delete Mail\n3.Read Mail\n4.Unread Mails\n5.All mails\n6.Session Info\n7.Exit\n\nSelect an option with the number");
				option = scanner.next();
				
				switch (option) {
					case "1": 
							System.out.println("Enter destination username:");
							usr = scanner.next();
							System.out.println("Enter subject:");
							scanner.nextLine();
							sub = scanner.nextLine();
							System.out.println("Enter message:");
							body = scanner.nextLine();
							System.out.println("Sending mail to "+usr+"... ");
							System.out.println("Server response: "+ sb.sendMail(usr,sub,body));
							break;
					case "2":
							System.out.println("Enter id of mail to delete:");
							usr = scanner.next();
							id = Integer.parseInt(usr);
							System.out.println("Deleting mail with id "+usr+"... ");
							System.out.println("Server response: "+ sb.delMail(id));
							break; 
					case "3":
							System.out.println("Enter id of mail to read:");
							usr = scanner.next();
							id = Integer.parseInt(usr);
							System.out.println("Reading mail with id "+usr+"... ");
							System.out.println("Server response: "+ sb.readMail(id));
							break;

					case "4":
							System.out.println("Getting new mails... ");
							System.out.println("Server response: "+ sb.getUnreadMails());
							break;
					case "5":
							System.out.println("Getting mails with id "+usr+"... ");
							System.out.println("Server response: "+ sb.getAllMails());
							break;
					case "6":
							 System.out.println("Server response: "+ sb.getSessionInfo());
							break;
					case "7":
							break menu;  
				}
				Thread.sleep(2000);
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

