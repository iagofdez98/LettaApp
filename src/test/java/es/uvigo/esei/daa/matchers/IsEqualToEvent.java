package es.uvigo.esei.daa.matchers;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;

import es.uvigo.esei.daa.entities.Event;

public class IsEqualToEvent extends IsEqualToEntity<Event> {
	public IsEqualToEvent(Event entity) {
		super(entity);
	}

	@Override
	protected boolean matchesSafely(Event item) {
		this.clearDescribeTo();
		
		if (item == null) {
			this.addTemplatedDescription("actual", expected.toString());
			return false;
		} else {
			return checkAttribute("id", Event::getId, item)
				&& checkAttribute("title", Event::getTitle, item)
				&& checkAttribute("description", Event::getDescription, item)
				&& checkAttribute("category", Event::getCategory, item)
				&& checkAttribute("location", Event::getLocation, item)
				&& checkAttribute("capacity", Event::getCapacity, item)
				&& checkAttribute("participants", Event::getParticipants, item)
				&& checkAttribute("creation_date", Event::getCreation_date, item)
				&& checkAttribute("event_date", Event::getEvent_date, item)
				&& checkAttribute("duration", Event::getDuration, item)
				&& checkAttribute("creator", Event::getCreator, item);
		}
		
	}
	
	@Factory
	public static IsEqualToEvent equalsToEvent(Event event) {
		return new IsEqualToEvent(event);
	}
	
	@Factory
	public static Matcher<Iterable<? extends Event>> containsEventsOrderByCreationDate(Event ... events) {
		return containsEntityInOrder(IsEqualToEvent::equalsToEvent, events);
	}
	
	@Factory
	public static Matcher<Iterable<? extends Event>> containsEventsInAnyOrder(Event ... events) {
		return containsEntityInAnyOrder(IsEqualToEvent::equalsToEvent, events);
	}
	
}