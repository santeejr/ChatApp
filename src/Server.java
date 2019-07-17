import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class Server extends Thread {
	static final Integer DEFAULT_PORT = 9001;
	private DatagramSocket socket;
	private DatagramPacket incoming;
	private Integer port;
	
	public Server()
	{
		this(Server.DEFAULT_PORT);		
	}
	
	public Server( Integer local )
	{
		port  = local;
		try {
			socket = new DatagramSocket(port);		
		} catch (IOException e)
		{
			System.out.println("ERROR BINDING THE SOCKET TO THE PORT. THE PORT MIGHT BI IN USE.\nTry running the application again.");
			System.exit(0);
		}				
	}
	
	private String decrypt(String msg)
	{
		String newmsg = "";
		int len;
		msg = msg.trim();
		
		// decrypting
		for (int i = 0; i < msg.length(); i++)
			newmsg += new String( Character.toChars( msg.charAt(i) - 3 ) );
		len = Integer.parseInt( newmsg.substring(0, newmsg.indexOf("#")) );
		msg = newmsg.substring( newmsg.indexOf("#") + 1 );
		
		// checking checksum
		if ( len == msg.length() )
			return msg;
		
		// if we get to this point the lengths didn't match, we got an error.
		// this is a simple checksum.
		return "AN ERROR OCCURED. THE MESSAGE TRANSMITTED WITH ERRORS.\n";
	}
	
	public void run()
	{
		String msg;
		System.out.println("Server thread init\nUsing local port " + port);
		while (true)
		{
			try {
				byte data[] = new byte[256];
				incoming = new DatagramPacket(data, data.length);
				
				socket.receive(incoming);
				msg = new String( incoming.getData() );
				System.out.println( decrypt( msg ) );
				System.out.print("- ");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("THERE WAS AN ERROR RETRIEVING THE MESSAGE");
			}
			
		}
	}
}
