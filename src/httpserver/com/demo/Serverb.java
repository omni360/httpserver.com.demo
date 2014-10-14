/**
 * 
 */
package httpserver.com.demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javax.xml.crypto.Data;

/**
 * @author omni360
 *
 */
public class Serverb {
	
	private ServerSocket server;
	public static final String CRLF ="\r\n";
	public static final String BLANK = " "; 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Serverb server = new Serverb();

		
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
	/**
	 * 接收客户端
	 */
	private void receive(){
		try {
			Socket client = server.accept();
			StringBuilder sb = new StringBuilder();
			byte[] data = new byte[20480];
			int len = client.getInputStream().read(data);

			String requestInfo = new String(data,0,len).trim();
			System.out.println(requestInfo);
			
			//响应
			StringBuilder responseContext = new StringBuilder();
			responseContext.append("<!doctype html>" +
"<html>" +
	"<head>" +
		"<title>第一个表单</title>"+
		"<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\">"+
	"</head>"+
	"<body>"+
		"<div><p>hello world</p></div>"+
	"</body>"+
"</html>");
			StringBuilder response = new StringBuilder();
			response.append("HTTP/1.1").append(BLANK).append("200").append(BLANK).append("OK").append(CRLF);
			response.append(new Date()).append(CRLF);
			response.append("Content-Length:").append(responseContext.toString().getBytes().length).append(CRLF);
			response.append(CRLF);
			response.append(responseContext);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			bw.write(response.toString());
			bw.flush();
			bw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
