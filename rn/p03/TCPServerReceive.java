import java.io.*;
import java.net.*;

class TCPServerReceive {
	public static void main(String argv[]) throws Exception {
		ServerSocket welcomeSocket = new ServerSocket(6789);

		while (true) {
   			// Wait for connection from client.
			Socket connectionSocket = welcomeSocket.accept();
			InputStream inFromClient = connectionSocket.getInputStream();
			FileOutputStream fileOut = new FileOutputStream ("out.txt");
			byte[] buffer = new byte[1024];

			while(connectionSocket.isConnected()){
				int bytesRead = inFromClient.read(buffer);
				if(bytesRead == -1){
					break;
				}
				fileOut.write(buffer, 0, bytesRead);
			}
			fileOut.close();
			connectionSocket.close();
		}
	}
}
