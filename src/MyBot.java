import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class MyBot {
	//one Arraylist will store the tweet objects we want to find
	static ArrayList<Status> tweets = new ArrayList<Status>();  
	
	 //one Arraylist will store the search terms we want to use
	static List<String> searches = new ArrayList<String>(); 
	
	//one Arraylist will store the reply tweets as strings
	static List<String> replies = new ArrayList<String>(); 

	public static void main(String[] args) throws TwitterException, InterruptedException{
		// TODO Auto-generated method stub
		String latestStatus = "testing";
		Twitter twitter = TwitterFactory.getSingleton(); 
//		Status status = twitter.updateStatus(latestStatus); 
		 
		searches.add("#prayforlouisiana"); 
		searches.add("#flooding");
		searches.add("Louisiana flood"); 
		searches.add("Louisiana flooding"); 
		
		
		replies.add(" In addition to praying, please consider #donating @ https://www.redcross.org/donate/donation?campname=genericflood&campmedium=aspo"); 
		replies.add(" Please help victims of Louisiana #flooding by donating here: https://www.redcross.org/donate/donation?campname=genericflood&campmedium=aspo"); 
		
		//keep tweeting forever
		while (true) {
			//create a new query
			for (int i=0; i < searches.size(); i++) {
				Query query = new Query(searches.get(i));
				QueryResult result = twitter.search(query); 
				
				//retrieve the tweet from these search results:
				Status tweetResult = result.getTweets().get(0);
				
				//reply to that tweet with one of our replies:
				if (i==0) {
					StatusUpdate statusUpdate = new StatusUpdate("@" + tweetResult.getUser().getScreenName() + replies.get(i));
					statusUpdate.inReplyToStatusId(tweetResult.getId());
					Status status = twitter.updateStatus(statusUpdate); 
				} else {
					StatusUpdate statusUpdate = new StatusUpdate(".@" + tweetResult.getUser().getScreenName() + replies.get(1));
					statusUpdate.inReplyToStatusId(tweetResult.getId());
					Status status = twitter.updateStatus(statusUpdate); 
				}
			}	
			System.out.println("sleeping");
			Thread.sleep(30*60*1000);
		}
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
			
		}
		
		
	}

}
