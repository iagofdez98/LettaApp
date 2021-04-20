package es.uvigo.esei.daa.rest;

import static es.uvigo.esei.daa.dataset.EventDataset.recentsEvents;
import static es.uvigo.esei.daa.dataset.UsersDataset.login;
import static es.uvigo.esei.daa.dataset.UsersDataset.userToken;
import static es.uvigo.esei.daa.matchers.HasHttpStatus.hasOkStatus;
import static es.uvigo.esei.daa.matchers.IsEqualToEvent.containsEventsOrderByCreationDate;
import static es.uvigo.esei.daa.dataset.EventDataset.*;
import static es.uvigo.esei.daa.matchers.HasHttpStatus.*;
import static es.uvigo.esei.daa.matchers.IsEqualToEvent.*;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;

import es.uvigo.esei.daa.LettaTestApplication;
import es.uvigo.esei.daa.entities.Event;
import es.uvigo.esei.daa.listeners.ApplicationContextBinding;
import es.uvigo.esei.daa.listeners.ApplicationContextJndiBindingTestExecutionListener;
import es.uvigo.esei.daa.listeners.DbManagement;
import es.uvigo.esei.daa.listeners.DbManagementTestExecutionListener;

import static org.hamcrest.CoreMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:contexts/mem-context.xml")
@TestExecutionListeners({
	DbUnitTestExecutionListener.class,
	DbManagementTestExecutionListener.class,
	ApplicationContextJndiBindingTestExecutionListener.class
})
@ApplicationContextBinding(
	jndiUrl = "java:/comp/env/jdbc/lettadb",
	type = DataSource.class
)
@DbManagement(
	create = "classpath:db/hsqldb.sql",
	drop = "classpath:db/hsqldb-drop.sql"
)
@DatabaseSetup("/datasets/dataset.xml")
@ExpectedDatabase("/datasets/dataset.xml")
public class EventResourceTest extends JerseyTest {
	
	@Override
	protected Application configure() {
		return new LettaTestApplication();
	}

	@Override
	protected void configureClient(ClientConfig config) {
		super.configureClient(config);
		
		// Enables JSON transformation in client
		config.register(JacksonJsonProvider.class);
		config.property("com.sun.jersey.api.json.POJOMappingFeature", Boolean.TRUE);
	}
	
	@Test
	public void testRecentEvents() throws IOException {
		final Response response = target("events/recent").request()
				.header("Authorization", "Basic " + userToken(login()))
		.get();
		
		assertThat(response, hasOkStatus());

		final List<Event> recentEvents = response.readEntity(new GenericType<List<Event>>(){});
		
		assertThat(recentEvents, containsEventsOrderByCreationDate(recentsEvents()));
	}
	@Test
	public void testGet() throws IOException {
		final Response response = target("events/" + existentId()).request()
				.header("Authorization", "Basic " + userToken(login()))
		.get();
		
		assertThat(response, hasOkStatus());
		
		final Event event = response.readEntity(Event.class);
		
		assertThat(event, is(equalsToEvent(existentEvent())));
	}
	
	@Test
	public void testGetInvalidId() throws IOException {
		final Response response = target("events/" + nonExistentId()).request()
				.header("Authorization", "Basic " + userToken(login()))
				.get();
		
		assertThat(response, hasBadRequestStatus());
	}
}
