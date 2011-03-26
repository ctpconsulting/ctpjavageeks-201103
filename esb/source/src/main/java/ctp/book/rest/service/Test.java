package ctp.book.rest.service;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

public class Test implements Callable {

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		System.out.println("Got message: " + eventContext);
		return eventContext.getMessage().getPayload();
	}

}
