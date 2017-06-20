
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.zip.CRC32;

public class Client {

	public static void main(String[] args) {
		/*
		 * Read input. Exit if input is not inserted correctly.
		 */
		if (args.length != 3) {
			System.out.println("Input: IP Port file_name.ext Exiting...");
			System.exit(-1);
		}
		/*
		 * Create instances of need arguments.
		 */
		String ipArg = args[0];
		int port = Integer.parseInt(args[1]);
		String fileName = args[2];
		Random rand = new Random();
		short sessionNr = (short) rand.nextInt(Short.MAX_VALUE + 1);
		DatagramSocket socket = null;
		InetAddress IP = null;
		InputStream inFileStream = null;
		File inFile;
		byte[] buf;
		int buffersize = 1021;
		int packetNr = 0;
		/*
		 * Open a socket. Exit if failed.
		 */
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			System.out.println("SOCKET CREATION FAILED! Exiting...");
			e.printStackTrace();
			System.exit(-1);
		}

		/*
		 * Get target for socket. Exit if failed.
		 */
		try {
			IP = InetAddress.getByName(ipArg);
		} catch (UnknownHostException e) {
			System.out.println("Server could not be determined! Exiting...");
			e.printStackTrace();
			System.exit(-1);
		}
		/*
		 * Open the file to be sent. Exit if failed.
		 */
		inFile = new File(fileName);
		try {
			inFileStream = new FileInputStream(inFile);
		} catch (FileNotFoundException e1) {
			System.out.println("File not found! Exiting...");
			e1.printStackTrace();
			System.exit(-1);
		}
		/*
		 * Create startPacket and send it.
		 */
		StartPacket startPacket = new StartPacket(sessionNr, packetNr, fileName, inFile.length());
		byte[] data = startPacket.returnData();
		DatagramPacket sendPacket = new DatagramPacket(data, data.length, IP, port);
		try {
			System.out.println("CLIENT: SESSIONNR: "+ sessionNr);
			System.out.println(
					"Sending file " + fileName + " to " + IP.getHostName() + ":" + port + " SessionID: " + sessionNr);
			System.out.println("Size: " + inFile.length() + " bytes. CRC32 checksum " + startPacket.getCrc()+"\n");
			socket.send(sendPacket);

		} catch (IOException e) {
			System.out.println("Error while sending a packet");
			e.printStackTrace();
		}
		DatagramPacket receiveAck = new DatagramPacket(new byte[3],3);
		try {
			socket.receive(receiveAck);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		Ack ack = new Ack(receiveAck.getData());
		System.out.println("CLIENT: ACK FROM SERVER: SESSIONNR: "+ ack.getSessionNr() + " PACKETNR: " + ack.getPacketNr());;
		/*
		 * Send file data.
		 */
		int eof;
		buf = new byte[buffersize];
		try {
			while ((eof = inFileStream.read(buf, 0, buffersize)) != -1) {
				packetNr+=1;
				DataPacket dataPacket = new DataPacket(sessionNr, packetNr, buf);
				data = dataPacket.returnData();
				sendPacket = new DatagramPacket(data,data.length, IP, port);
				socket.send(sendPacket);
			}
		} catch (IOException e1) {
			System.out.println("Error reading from file stream.");
			e1.printStackTrace();
		}

		// CLEANUP
		try {
			inFileStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		socket.close();
	}
}
