import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;


public class ConnectionInfo {
	private Integer local;
	private Integer remote;
	private InetAddress address;
	
	public ConnectionInfo() {
		local = 0;
		remote = 0;		
	}
	
	private boolean isValidPort( String s )
	{
		s = s.trim();
		for (int i = 0; i < s.length(); i++)
			if (!Character.isDigit(s.charAt(i)))
				return false;
		
		return !s.equals("") ? true : false;
	}
	
	public void getPorts()
	{
		String input; 
		boolean valid;
		
		// LOCAL PORT INPUT
		do {
			input = JOptionPane.showInputDialog("Enter local port");
			if (input == null) System.exit(0);
		} while (!isValidPort( input ));		
		local = Integer.parseInt(input);
		
		// ADDRESS INPUT		
		do {
			valid = true;
			input = JOptionPane.showInputDialog("Enter remote address");
			try {
				address = InetAddress.getByName(input);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				System.out.println("Unknown Host.");
				valid = false;
			}
		} while (!valid);
		
		// REMOTE PORT INPUT
		do {
			input = JOptionPane.showInputDialog("Enter remote port");
			if (input == null) System.exit(0);
		} while (!isValidPort( input ));
		remote   = Integer.parseInt(input);
	}
	
	public InetAddress getRemoteAddress()
	{
		return address;
	}
	
	public Integer getLocalPort()
	{
		return local;
	}
	
	public Integer getRemotePort()
	{
		return remote;
	}
	
}
