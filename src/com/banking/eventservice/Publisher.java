package com.banking.eventservice;

import com.banking.events.Event;

public interface Publisher {

	public void publish(Event e);
}
