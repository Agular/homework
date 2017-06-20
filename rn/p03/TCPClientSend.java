import java.io.*; 
import java.net.*; 
class TCPClientSend { 

	public static void main(String args[]) throws Exception {
	
		Socket clientSocket = new Socket("localhost", 6789);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		File file = new File("alice.txt");
		FileInputStream fileInput = new FileInputStream(file);
		System.out.println("Total file size to read in bytes: " + fileInput.available());
		
		byte[] buffer = new byte[1024];
		while(fileInput.available() > 0){
			outToServer.write(buffer, 0, fileInput.read(buffer));
		}
		fileInput.close();
		clientSocket.close();
	}
} 
