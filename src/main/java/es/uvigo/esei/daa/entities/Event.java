package es.uvigo.esei.daa.entities;

import static java.util.Objects.requireNonNull;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * An entity that represents a person.
 * 
 * @author Fernando Rodriguez, Iago Fernandez
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Event{
	public enum Category{
		Musica,
		Deportes,
		Literatura,
		Cine,
		Videojuegos,
		Series,
		Teatro,
		Otro
	}
	
	private int id;
	private String title;
	private String description;
	private Category category;
	private String location;
	private int capacity;
	private int participants;

	private Date creation_date;

	private Date event_date;
	private int duration;
	private String creator;
	
	
	// Constructor needed for the JSON conversion
		Event() {}
	
	/**
	 * Constructs a new instance of {@link Event}.
	 * 
	 * @param id
	 * @param title
	 * @param description
	 * @param category
	 * @param location
	 * @param capacity
	 * @param participants
	 * @param creation_Date
	 * @param event_date
	 * @param duration
	 * @param creator
	 * @throws IllegalArgumentException 
	 */
	public Event(int id, String title, String description, Category category, String location, int capacity,
			int participants, Date creation_date, Date event_date, int duration, String creator) throws IllegalArgumentException {
		
		this.id = requireNonNull(id, "Identifier can't be null");
		
		if(title == null) {
			throw new NullPointerException("Title can't be null");
		}
		else if(title.length() > 20){
			throw new IllegalArgumentException("Title length can't be higher than 20");
		}
		else {
			this.title = title;
		}
		
		if(description.length() > 5000){
			throw new IllegalArgumentException("Description length can't be higher than 5000");
		}
		else {
			this.description = description;
		}
		
		this.category = requireNonNull(category, "Category can't be null");
		
		if(location == null) {
			throw new NullPointerException("Location can't be null");
		}
		else if(location.length() > 30){
			throw new IllegalArgumentException("Location length can't be higher than 30");
		}
		else {
			this.location = location;
		}
		
		if (capacity <= 1) {
			throw new IllegalArgumentException("Capacity has to be higher than 1");
		}
		else {
			this.capacity = capacity;
		}
		
		if(participants < 0) {
			throw new IllegalArgumentException("Number of participants has to be higher than 0");
		}
		else if (participants > capacity) {
			throw new IllegalArgumentException("Number of participants has to be lower than the event capacity");
		}
		else {
			this.participants = participants;
		}
		
		this.creation_date = creation_date;
		
		if(event_date.compareTo(creation_date) <= 0) {
			throw new IllegalArgumentException("Event date has to be later than creation date");
		}else {
			this.event_date = event_date;
		}
		
		if(duration < 0) {
			throw new IllegalArgumentException("The duration has to be positive");
		}
		else if(duration % 15 != 0) {
			throw new IllegalArgumentException("Duration has to be in blocks of 15 minutes");
		}
		else if(duration > 240) {
			throw new IllegalArgumentException("Duration can't exceed 240 minutes");
		}
		else {
			this.duration = duration;
		}
		
		this.creator = requireNonNull(creator, "Creator can't be null");
	}

	/**
	 * Returns the identifier of the event
	 * 
	 * @return the identifier of the event
	 */
	public int getId() {
		return id;
	}

	/**
	 * Returns the title of the event
	 * 
	 * @return the title of the event
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns the description of the event
	 * 
	 * @return the description of the event 
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the category of the event
	 * 
	 * @return the category of the event
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * Returns the location of the event
	 * 
	 * @return the location of the event
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Returns the capacity of the event
	 * 
	 * @return the capacity of the event
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Returns the amount of participants of the event
	 * 
	 * @return the amount of participants of the event
	 */
	public int getParticipants() {
		return participants;
	}

	/**
	 * Returns the date of creation of the event
	 * 
	 * @return the date of creation of the event
	 */
	public Date getCreation_date() {
		return creation_date;
	}

	/**
	 * Returns the date the event takes place
	 * 
	 * @return the date the event takes place
	 */
	public Date getEvent_date() {
		return event_date;
	}

	/**
	 * Returns the duration in minutes of the event
	 * 
	 * @return the duration in minutes of the event
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Returns the creator of the event
	 * 
	 * @return the creator of the event
	 */
	public String getCreator() {
		return creator;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Event))
			return false;
		Event other = (Event) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", title=" + title + ", description=" + description + ", category=" + category
				+ ", location=" + location + ", capacity=" + capacity + ", participants=" + participants
				+ ", creation_date=" + creation_date.getTime() + ", event_date=" + event_date.getTime() + ", duration=" + duration
				+ ", creator=" + creator + "]";
	}
	
	
	
	
}