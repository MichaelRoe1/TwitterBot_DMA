import java.util.ArrayList;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class MyBot {
	
	static ArrayList<Status> tweets = new ArrayList<Status>();  

	public static void main(String[] args) throws TwitterException{
		// TODO Auto-generated method stub
		String latestStatus = "testing";
		Twitter twitter = TwitterFactory.getSingleton(); 
//		Status status = twitter.updateStatus(latestStatus); 
		 
		makeSearch("A bird pooped");
	}
	
	public static void makeSearch(String s) throws TwitterException {
		for (int i = 0; i < tweets.size(); i++) {
			tweets.remove(i); 
		}
		Query query = new Query(s); 
		Twitter twitter = TwitterFactory.getSingleton();
		QueryResult result = twitter.search(query); 
		for (Status t : result.getTweets()) {
			tweets.add(t); 
			System.out.print(t.getText());
			System.out.print(t.getInReplyToScreenName());
		}
		
		
	}

}
