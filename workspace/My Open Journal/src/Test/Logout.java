package Test;

import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;

public class Logout implements Initiator {

	@Override
	public void doInit(Page page, Map<String, Object> args) throws Exception {
		SessionManager.Logout();
		Executions.sendRedirect("index.zul");
	}

}
