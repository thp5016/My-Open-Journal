package Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Rank {
	
	public static double GetRank(int paperID) {
		double upvotes;
		double downvotes;
		double days;
		double weight;
		DBManager manager;
		Date date1;
		Date date2;
		date1 = new Date();
		manager = new DBManager();
		try {
			date2 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(manager.GetPaperDate(paperID));
			upvotes = manager.GetPaperUpvotes(paperID);
			downvotes = manager.GetPaperDownvotes(paperID);
			days = GetNumberOfDays(date1, date2);
			weight = ((upvotes + 1)/(upvotes + downvotes + 2) + 9 / (days + 1)) / 10;
			return weight;

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1.00;
		}		
	}
	
	public static int GetNumberOfDays(Date d1, Date d2){
		int days = (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
		System.out.println("days between : " + days);
		return days;
	}
}
