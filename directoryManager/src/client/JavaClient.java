package client;

import javax.naming.InitialContext;
import java.util.Scanner;
import javax.ejb.*;
import ejb.DMRMI;


public class JavaClient {
	public static void main(String args[]) {
		DMRMI sb;
		String tmp, option, usr, pwd;
		boolean w, r;		

	    try {
			InitialContext ic = new InitialContext();
			sb = (DMRMI) ic.lookup("ejb.DMRMI");
			Scanner scanner = new Scanner(System.in);
			Runtime.getRuntime().exec("clear");

			System.out.println("\nHit Enter to start test cases...");
			System.in.read();

			//Add 3 new users
			System.out.println("\nInsert users: \nUsername: Laura\nWrite permission:true\nRead permission:true\n\nUsername: Julia\nWrite permission:false\nRead permission:true\n\nUsername: Bryan\nWrite permission:false\nRead permission:false");
			System.out.println("\nServer response... " + sb.addUser("Laura","123",true,true));
			System.out.println("Server response... " + sb.addUser("Julia","123",false,true));
			System.out.println("Server response... " + sb.addUser("Bryan","123",false,false));
			
			System.out.println("\nTIME TO SEND SOME MAILS...");
			System.in.read();

			//Look for rights
    		System.out.println("\nLookup rights for:");
    		System.out.println("Laura... " + sb.lookupRights("Laura"));
    		System.out.println("Julia... " + sb.lookupRights("Julia"));
    		System.out.println("Bryan... " + sb.lookupRights("Bryan"));

			//Update rights
			System.out.println("\nUpdate Julia's write permission to true... ");
			System.out.println("Server response... "+ sb.updateRights("Julia",true,true));

			//Look for rights
    		System.out.println("\nLookup rights for:");
    		System.out.println("Laura... " + sb.lookupRights("Laura"));
    		System.out.println("Julia... " + sb.lookupRights("Julia"));
    		System.out.println("Bryan... " + sb.lookupRights("Bryan"));

    		System.out.println("\nTIME TO SEND MORE MAILS...");
			System.in.read();

			//List users
			System.out.println("\nList all users:");
			System.out.println("Server response... "+sb.listAllUsers());

			System.out.println("\nOK ENOUGH, START THE INTERACTIVE MENU...");
			System.in.read();

			menu: while (true) {
				//Menu
				System.out.println("\n\n======== MENU ========\n\n1.Add User\n2.Delete User\n3.Update Rights\n4.Lookup Rights\n5.List All Users\n6.Exit\n\nSelect an option with the number");
				option = scanner.next();
				
				switch (option) {
					case "1": 
							System.out.println("Enter username:");
							usr = scanner.next();
							System.out.println("Enter password:");
							pwd = scanner.next();
							System.out.println("Enter write permission (write true or false):");
							tmp = scanner.next();
							w = Boolean.valueOf(tmp);
							System.out.println("Enter read permission (write true or false):");
							tmp = scanner.next();
							r = Boolean.valueOf(tmp);
							System.out.println("Inserting User "+usr+"... ");
							System.out.println("Server response: "+ sb.addUser(usr,pwd,w,r));
							break;
					case "2": 
							System.out.println("Enter username to delete:");
							usr = scanner.next();
							System.out.println("Deleting User "+usr+"... ");
							System.out.println("Server response: "+ sb.delUser(usr));
							break;

					case "3": 
							System.out.println("Enter username to update rights:");
							usr = scanner.next();
							System.out.println("Enter write permission (write true or false):");
							tmp = scanner.next();
							w = Boolean.valueOf(tmp);
							System.out.println("Enter read permission (write true or false):");
							tmp = scanner.next();
							r = Boolean.valueOf(tmp);
							System.out.println("Updating User "+usr+"... ");
							System.out.println("Server response: "+ sb.updateRights(usr,w,r));
							break;
					case "4": 
							System.out.println("Enter username to lookup rights:");
							usr = scanner.next();
							System.out.println("Looking up rights for user "+usr+"... ");
							System.out.println("Server response: "+ sb.lookupRights(usr));
							break;
					case "5":
							System.out.println("Listing users in database... "+sb.listAllUsers());
							break;
					case "6":
							break menu; 
				}
				
			}
			//Delete the test case users
				sb.delUser("Laura");
				sb.delUser("Julia");
				sb.delUser("Bryan");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

