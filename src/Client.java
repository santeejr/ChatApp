import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;



public class Client {
	static final Integer DEFAULT_PORT = 9000;
	private DatagramSocket socket;
	private DatagramPacket outgoing;
	private Integer port;
	private InetAddress address;
		
	public Client() throws UnknownHostException
	{
		this( InetAddress.getLocalHost() , Client.DEFAULT_PORT );
	}
	
	public Client( InetAddress add, Integer remote )
	{
		port 	= remote;
		address = add;
		
		try {
			socket = new DatagramSocket( );
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR BINDING THE SOCKET TO THE PORT. THE PORT MIGHT BI IN USE.\nTry running the application again.");
			System.exit(0);
		}				
	}
		
	// ENCRYPTION AND CHECKSUM METHOD
	private String encrypt(String msg)
	{
		msg = msg.length() + "#" + msg;		// adding checksum
		String newmsg = "";
		
		// encryption
		for (int i = 0; i < msg.length(); i++)
			newmsg += new String( Character.toChars( msg.charAt(i) + 3 ) );
		
		return newmsg;
	}
	
	public void run()
	{
		System.out.println("Client init\nUsing port " + port);
		System.out.println("Using remote address: " + address.toString());
		System.out.println("Type \"QUIT\" to exit the program");
		Scanner inLine = new Scanner( System.in );
		System.out.print("- ");
		String message = inLine.nextLine();
		
		while (message.compareTo("QUIT") != 0)
		{
			message = encrypt( message );
			try {
				byte data[] = message.getBytes();
				
				outgoing = new DatagramPacket(data, data.length, address, port);
				socket.send(outgoing);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				System.out.println("The HOST you're trying to reach is unknown");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("AN ERROR OCURRED TRYING TO SEND THE LAST MESSAGE");
			}
			System.out.print("- ");
			message = inLine.nextLine();
		}
		socket.close();
		System.exit(0);
	}
}
