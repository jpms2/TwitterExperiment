package test.twitter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import util.Message;

//Twitter API With Java Example
public class App {
	public static ArrayList<Message> messages = new ArrayList<Message>();
  public static void main(String args[]) {
    while(messages.size() < 1000){
    	ThreadedPost tp = new ThreadedPost();
    	tp.start();
    	try {
			tp.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    List<String> lines = new ArrayList<String>();
    Path file = Paths.get("tweetsIphone.txt");
    for(Message msg: messages){
    	lines.add("user: " + msg.user);
    	if(msg.geolocation != null){
    		System.out.println("ENTREI PORRA");
    		lines.add("location: " + msg.geolocation);
    	}else{
    		lines.add("location: ");
    	}
    	lines.add("text: " + msg.text);
    	lines.add("#####################################################################################");
    }
    try {
		Files.write(file, lines, Charset.forName("UTF-8"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
}