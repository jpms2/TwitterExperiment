package test.twitter;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import util.*;

public class ThreadedPost extends Thread{
	public void run(){
		   Twitter twitter = new TwitterFactory().getInstance();
		    // Twitter Consumer key & Consumer Secret
		    twitter.setOAuthConsumer("ConsumerKey", "ConsumerSecret");
		    // Twitter Access token & Access token Secret
		    twitter.setOAuthAccessToken(new AccessToken("AccessToken",
		    "AccessTokenSecret"));
		    try {
		    	Query query = new Query("Iphone x");
		    	query.count(100);
		    	query.lang("en");
		        QueryResult result = twitter.search(query);
		        for (Status status : result.getTweets()) {
		        	Message message;
		        	if(status.getGeoLocation() != null){
		        		message = new Message(status.getUser().getScreenName(),status.getText(),status.getGeoLocation().toString());
		        	}else{
		        		message = new Message(status.getUser().getScreenName(),status.getText(),status.getUser().getLocation());
		        	}
		        
		        	App.messages.add(message);
		        }
	        	System.out.println("Finished 100 messages");
		    } catch (Exception e) {
		    }
	}
}
;