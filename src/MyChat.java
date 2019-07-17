public class MyChat {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		ConnectionInfo info = new ConnectionInfo();
		info.getPorts();
		
		Server server = new Server(info.getLocalPort());		
		Client client = new Client(info.getRemoteAddress(), info.getRemotePort());
		server.start();
		client.run();
	}

}
