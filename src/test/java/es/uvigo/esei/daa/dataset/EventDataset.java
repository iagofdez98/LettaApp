package es.uvigo.esei.daa.dataset;

import java.util.Comparator;
import java.util.Date;

import static java.util.Arrays.*;

import es.uvigo.esei.daa.entities.Event;
import es.uvigo.esei.daa.util.DateFormat;

public final class EventDataset {
	private EventDataset() {}
	
	/**
	 * Array de objetos Event con datos de prueba
	 * @return Event[]
	 */
	public static Event[] events() {
		return new Event[] {
			new Event(1, "Titulo 1", "Hola soy una descripcion", Event.Category.Cine, "Ourense", 12, 1, 
					DateFormat.parseDate("10/01/3020 14:06:45"), DateFormat.parseDate("20/01/3020 15:06:45"), 30, "pepito"),
			new Event(2, "Titulo 2", "Hola soy una descripcion", Event.Category.Deportes, "Ourense", 12, 0, 
					DateFormat.parseDate("04/02/3020 13:26:45"), DateFormat.parseDate("14/02/3020 14:26:45"), 30, "gregorio"),
			new Event(3, "Titulo 3", "Hola soy una descripcion", Event.Category.Musica, "Ourense", 12, 0, 
					DateFormat.parseDate("23/02/3020 11:06:45"), DateFormat.parseDate("02/03/3020 11:06:45"), 30, "manuel"),
			new Event(4, "Titulo 4", "Hola soy una descripcion", Event.Category.Series, "Ourense", 24, 0, 
					DateFormat.parseDate("01/01/3020 15:06:45"), DateFormat.parseDate("04/01/3020 15:06:45"), 30, "paco"),
			new Event(5, "Titulo 5", "Hola soy una descripcion", Event.Category.Otro, "Ourense", 12, 0, 
					DateFormat.parseDate("02/03/3020 15:06:45"), DateFormat.parseDate("14/03/3020 15:06:45"), 30, "pepito"),
			new Event(6, "Titulo 6", "Hola soy una descripcion", Event.Category.Cine, "Ourense", 12, 0, 
					DateFormat.parseDate("12/02/3020 15:06:45"), DateFormat.parseDate("23/02/3020 15:06:45"), 30, "pepito"),
			new Event(7, "Titulo 7", "Hola soy una descripcion", Event.Category.Otro, "Ourense", 6, 0, 
					DateFormat.parseDate("01/01/3020 15:06:45"), DateFormat.parseDate("10/01/3020 15:06:45"), 30, "gregorio"),
			new Event(8, "Titulo 8", "Hola soy una descripcion", Event.Category.Teatro, "Ourense", 12, 0, 
					DateFormat.parseDate("01/04/3020 15:06:45"), DateFormat.parseDate("10/04/3020 15:06:45"), 30, "gregorio"),
			new Event(9, "Titulo 9", "Hola soy una descripcion", Event.Category.Cine, "Ourense", 12, 0, 
					DateFormat.parseDate("10/02/3020 15:06:45"), DateFormat.parseDate("12/02/3020 15:06:45"), 30, "pepito"),
			new Event(10, "Titulo 10", "Hola soy una descripcion", Event.Category.Literatura, "Ourense", 12, 0, 
					DateFormat.parseDate("05/04/3020 15:06:45"), DateFormat.parseDate("15/04/3020 15:06:45"), 30, "paco"),
			new Event(11, "Titulo 11", "Hola soy una descripcion", Event.Category.Deportes, "Ourense", 22, 0, 
					DateFormat.parseDate("10/02/3020 15:06:45"), DateFormat.parseDate("19/02/3020 15:06:45"), 30, "pepito"),
			new Event(12, "Titulo 12", "Hola soy una descripcion", Event.Category.Cine, "Ourense", 10, 0, 
					DateFormat.parseDate("04/01/3020 15:06:45"), DateFormat.parseDate("07/01/3020 15:06:45"), 30, "manuel"),
			new Event(13, "Titulo 13", "Hola soy una descripcion", Event.Category.Deportes, "Ourense", 12, 0, 
					DateFormat.parseDate("02/04/3020 15:06:45"), DateFormat.parseDate("12/04/3020 15:06:45"), 30, "pepito"),
			new Event(14, "Titulo 14", "Hola soy una descripcion", Event.Category.Cine, "Ourense", 12, 0, 
					DateFormat.parseDate("10/01/3020 15:06:45"), DateFormat.parseDate("20/01/3020 15:06:45"), 30, "paco")
		};
	}
	
	/**
	 * Un array de objetos Event ordenador por fecha de creación. Como máximo 12 objetos.
	 * @return Event[]
	 */
	public static Event[] recentsEvents() {
		return stream(events())
			.filter(event -> event.getCapacity() > event.getParticipants())
			.sorted(Comparator.comparing(Event::getCreation_date).reversed())
			.limit(12)
		.toArray(Event[]::new);
	}
	
	public static Event event(int id) {
		return stream(events())
			.filter(event -> event.getId() == id)
			.findAny()
		.orElseThrow(IllegalArgumentException::new);
	}
	
	public static int existentId() {
		return 1;
	}
	
	public static int nonExistentId() {
		return 12354;
	}
	
	public static Event existentEvent() {
		return event(existentId());
	}
	
	public static String newTitle() {
        return "Soy un titulo";
    }

    public static String newDescription() {
        return "Hola soy una descripcion";
    }

    public static Event.Category newCategory() {
        return Event.Category.Deportes;
    }

    public static String newLocation() {
        return "Ourense";
    }

    public static int newCapacity() {
        return 12;
    }
    
    public static int newParticipants(){
    	return 1;
    }

    public static String newCreation_Date() {
        return "10/03/3020 15:06:45";
    }

    public static String newEvent_Date() {
        return "20/04/3020 15:06:45";
    }

    public static int newDuration() {
        return 45;
    }

    public static String newCreator() {
        return "Paco";
    }

    public static Event newEvent() {
        return new Event(events().length + 1, newTitle(), newDescription(), newCategory(), newLocation(), newCapacity(),
        		newParticipants(), DateFormat.parseDate(newCreation_Date()), DateFormat.parseDate(newEvent_Date()), newDuration(), newCreator());
    }
}
