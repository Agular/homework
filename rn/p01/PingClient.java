import java.io.*; 
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
 
  
class PingClient { 
    public static void main(String args[]) throws Exception 
    { 
  
      BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); 
      DatagramSocket clientSocket = new DatagramSocket();
      // Set timeout for socket.
      clientSocket.setSoTimeout(1000); 
      // Get command line argument.
      if (args.length != 2) {
         System.out.println("Required arguments: Hostaddress PortNumber");
         return;
      }
      
      InetAddress IPAddress = InetAddress.getByName(args[0]); 
      int port = Integer.parseInt(args[1]);
      byte[] sendData = new byte[1024]; 
      byte[] receiveData = new byte[1024];
      ArrayList<Long> pings = new ArrayList<Long>();
	int seq = 0;
      while(true){
	if(seq==10){
		break;
			} 
      	long startTime = System.currentTimeMillis();
	//PING Sequence number Time stamp CRLF
      	String sentence = "PING "+seq+" "+startTime + "\r\n"; 
      	sendData = sentence.getBytes();
      	DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port); 
      	clientSocket.send(sendPacket);
 	try{
      		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);   
      		clientSocket.receive(receivePacket); 
      		String modifiedSentence = new String(receivePacket.getData());
      		long finishTime = System.currentTimeMillis();
      		long dTime = finishTime - startTime;
		pings.add(dTime);
      		System.out.println("FROM SERVER: " + modifiedSentence);
      		System.out.println("PING: " + dTime +"mms");
		seq++;
	} catch (SocketTimeoutException e) {
                // timeout exception.
                System.out.println("Timeout reached !!!");
                seq++;
		continue;
            }

      }

 
      clientSocket.close(); 
      System.out.println("MAX PING: " + Collections.max(pings));
      System.out.println("MIN PING: " + Collections.min(pings));
      int avgPing = 0;
      for(int i = 0; i< pings.size();i++){
	avgPing+= pings.get(i);
      }
      avgPing = avgPing / pings.size();
      System.out.println("AVG PING: " + avgPing);		
    } 
} 	  