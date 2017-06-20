import java.io.*; 
import java.net.*; 
class TCPClient { 

    public static void main(String args[]) throws Exception 
    { 
        String sentence; 
        String modifiedSentence; 
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        // Get command line argument.
	if (args.length != 2) {
         System.out.println("Required arguments: Hostaddress PortNumber");
         return;
        }
	String ip = args[0];
	int port = Integer.parseInt(args[1]);
        Socket clientSocket = new Socket(ip, port); 


	
        	DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream()); 
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 
        	sentence = "GET / HTTP/1.1\r\n" + "Host:\r\n" + "\r\n"; 
        	outToServer.writeBytes(sentence);
		String sAnswer;
		while((sAnswer = inFromServer.readLine()) != null){  
        		System.out.println(inFromServer.readLine());
		}
        clientSocket.close(); 
                   
    } 
} 
