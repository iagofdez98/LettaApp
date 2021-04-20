package es.uvigo.esei.daa.entities;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import es.uvigo.esei.daa.entities.Event.Category;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import java.util.Date;

public class EventUnitTest{
	
	@Test
	public void testEventIntStringStringCategoryStringIntIntDateDateIntString() {
		final int id = 1;
		final String title = "Titulo 1";
		final String description = "El mejor";
		final Category category = Category.Literatura;
		final String location = "Ourense";
		final int capacity = 10;
		final int participants = 5;
		final Date creation_date = new Date(1592604000000L);
		final Date event_date = new Date(1593468000000L);
		final int duration = 90;
		final String creator = "Yo";
		
		final Event event = new Event(id, title, description, category, location, capacity, participants,creation_date,event_date,duration,creator);
		
		assertThat(event.getId(), is(equalTo(id)));
		assertThat(event.getTitle(), is(equalTo(title)));
		assertThat(event.getDescription(), is(equalTo(description)));
		assertThat(event.getCategory(), is(equalTo(category)));
		assertThat(event.getLocation(), is(equalTo(location)));
		assertThat(event.getCapacity(), is(equalTo(capacity)));
		assertThat(event.getParticipants(), is(equalTo(participants)));
		assertThat(event.getCreation_date(), is(equalTo(creation_date)));
		assertThat(event.getEvent_date(), is(equalTo(event_date)));
		assertThat(event.getDuration(), is(equalTo(duration)));
		assertThat(event.getCreator(), is(equalTo(creator)));
	}
	
	@Test(expected = NullPointerException.class)
	public void testEventIntStringStringCategoryStringIntIntDateDateIntStringNullTitle() {
		new Event(1, null, "descripcion", Event.Category.Cine, "Aqui", 10, 5, new Date(1593444000000L), new Date(1593468000000L), 30, "Yo");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEventIntStringStringCategoryStringIntIntDateDateIntStringHigherTitleLength() {
		new Event(1, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "descripcion", Event.Category.Cine, "Aqui", 10, 5, new Date(1593444000000L), new Date(1593468000000L), 30, "Yo");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEventIntStringStringCategoryStringIntIntDateDateIntStringHigherDescriptionLength() {
		new Event(1, "titulo", "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", Event.Category.Cine, "Aqui", 10, 5, new Date(1593444000000L), new Date(1593468000000L), 30, "Yo");
	}
	
	@Test(expected = NullPointerException.class)
	public void testEventIntStringStringCategoryStringIntIntDateDateIntStringNullCategory() {
		new Event(1, "titulo", "description", null, "Aqui", 10, 5, new Date(1593444000000L), new Date(1593468000000L), 30, "Yo");
	}
	
	@Test(expected = NullPointerException.class)
	public void testEventIntStringStringCategoryStringIntIntDateDateIntStringNullLocation() {
		new Event(1, "titulo", "descripcion", Event.Category.Cine, null, 10, 5, new Date(1593444000000L), new Date(1593468000000L), 30, "Yo");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEventIntStringStringCategoryStringIntIntDateDateIntStringHigherLocationLength() {
		new Event(1, "titulo", "descripcion", Event.Category.Cine, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 10, 5, new Date(1593444000000L), new Date(1593468000000L), 30, "Yo");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEventIntStringStringCategoryStringIntIntDateDateIntStringLowerCapacity() {
		new Event(1, "aaaaaa", "descripcion", Event.Category.Cine, "Aqui", 0, 5, new Date(1593444000000L), new Date(1593468000000L), 30, "Yo");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEventIntStringStringCategoryStringIntIntDateDateIntStringLowerParticipants() {
		new Event(1, "aaaaaa", "descripcion", Event.Category.Cine, "Aqui", 10, -2, new Date(1593444000000L), new Date(1593468000000L), 30, "Yo");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEventIntStringStringCategoryStringIntIntDateDateIntStringLowerCapacityParticipants() {
		new Event(1, "aaaaaa", "descripcion", Event.Category.Cine, "Aqui", 10, 25, new Date(1593444000000L), new Date(1593468000000L), 30, "Yo");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEventIntStringStringCategoryStringIntIntDateDateIntStringLowerEvent_date() {
		new Event(1, "aaaaaa", "descripcion", Event.Category.Cine, "Aqui", 10, 0, new Date(2593444000000L), new Date(1593468000000L), 30, "Yo");
	}	
	
	@Test(expected = NullPointerException.class)
	public void testEventIntStringStringCategoryStringIntIntDateDateIntStringNullCreator() {
		new Event(1, "titulo", "descripcion", Event.Category.Cine, "Aqui", 10, 5, new Date(1593444000000L), new Date(1593468000000L), 30, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEventIntStringStringCategoryStringIntIntDateDateIntStringLowerDuration() {
		new Event(1, "titulo", "descripcion", Event.Category.Cine, "Aqui", 10, 5, new Date(1593444000000L), new Date(1593468000000L), -30, "Yo");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEventIntStringStringCategoryStringIntIntDateDateIntString15Duration() {
		new Event(1, "titulo", "descripcion", Event.Category.Cine, "Aqui", 10, 5, new Date(1593444000000L), new Date(1593468000000L), 32, "Yo");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEventIntStringStringCategoryStringIntIntDateDateIntStringHigherDuration() {
		new Event(1, "titulo", "descripcion", Event.Category.Cine, "Aqui", 10, 5, new Date(1593444000000L), new Date(1593468000000L), 2000, "Yo");
	}
	
	@Test
	public void testEqualsObject() {
		final Event eventA = new Event(1, "fsd", "gfsbdjhfnds", Event.Category.Cine, "ourense", 43, 3, new Date(1593444000000L), new Date(1593468000000L),45,"mikelas");
		final Event eventB = new Event(1, "assda", "adsfsdgdf", Event.Category.Musica, "italia", 42, 7,new Date(1593444000000L), new Date(1593468000000L),30,"fer el friki");
		
		assertTrue(eventA.equals(eventB));
	}
	
	@Test
	public void testEqualsHashcode() {
		EqualsVerifier.forClass(Event.class)
			.withIgnoredFields("title", "description", "category", "location", "capacity", "participants","creation_date", "event_date", "duration", "creator")
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.NONFINAL_FIELDS)
		.verify();
	}
}

	
