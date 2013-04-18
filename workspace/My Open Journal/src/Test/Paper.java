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

public class Paper extends GenericForwardComposer {

@Listen("onClick = #downloadLink")
public void viewPaper()

@Listen("onClick = #upVotes")
public void upVote()

@Listen("onClick = #downVotes")
public void downVote()

@Listen("onClick = #reviewLink")
public void viewReview()

@Listen("onClick = #addReviewLink")
public void addReview()

}
