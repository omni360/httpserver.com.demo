/**
 * 
 */
package httpserver.com.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author omni360
 *
 */
public class Servera {
	
	private ServerSocket server;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Servera server = new Servera();

		
		server.start();
		}
	
	private void start(){
		try {
			server = new ServerSocket(8888);
			this.receive();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	private void stop(){
		
	}
	private void receive(){
		try {
			Socket client = server.accept();
			StringBuilder sb = new StringBuilder();
			byte[] data = new byte[20480];
			int len = client.getInputStream().read(data);

			String requestInfo = new String(data,0,len).trim();
			System.out.println(requestInfo);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
