package Test;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.impl.InputElement;




public class AddReview extends GenericForwardComposer {

  @Wire
	Textbox typedReview;

	@Listen("onClick = #submitReview")
	public void submitReview() {
		int id;
		int paperId;
		String user;
		String review;
		DBManager manager = new DBManager();

		user = SessionManager.GetUser();
		id = manager.GetID(user);
		review = manager.GetTypedReview(user);
		manager.InsertReview(paperId, id, review);
	}

	@Listen("onClick = #backToPaper")
	public void goBack() {
		Executions.sendRedirect("index.zul");
	}
}
