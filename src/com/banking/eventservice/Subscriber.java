package com.banking.eventservice;

import com.banking.events.Event;

public interface Subscriber {

	public void inform(Event e);
}
