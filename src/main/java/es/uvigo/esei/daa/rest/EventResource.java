package es.uvigo.esei.daa.rest;

import es.uvigo.esei.daa.dao.*;
import es.uvigo.esei.daa.entities.Event;
import es.uvigo.esei.daa.util.DateFormat;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
public class EventResource {

	private final EventDAO dao;

	public EventResource() {
		this(new EventDAO());
	}

	public EventResource(EventDAO dao) {
		this.dao = dao;
	}

	@GET
	@Path("/recent")
	/**
	 * Devuelve una lista de eventos recientes (Maximo 12 eventos)
	 * @return Response
	 */
	public Response listRecent() {
		try {
			return Response.ok(this.dao.getRecent()).build();
		} catch (DAOException e) {
			e.printStackTrace();
			return Response.serverError().entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Path("/{id}")
	/**
	 * Devuelve un Evento
	 * @return Response
	 */
	public Response get(@PathParam("id") int id) {
		try {
			final Event event = this.dao.get(id);
			
			return Response.ok(event).build();
		} catch (IllegalArgumentException iae) {
			return Response.status(Response.Status.BAD_REQUEST)
				.entity(iae.getMessage())
			.build();
		} catch (DAOException e) {
			e.printStackTrace();
			return Response.serverError().entity(e.getMessage()).build();
		}
	}
	
	@POST
	public Response add(
		@FormParam("title") String title,
		@FormParam("description") String description,
		@FormParam("category") String cat,
		@FormParam("location") String location,
		@FormParam("capacity") int capacity,
		@FormParam("participants") int participants,
		@FormParam("event_date") String event_date,
		@FormParam("duration") int duration,
		@FormParam("creator") String creator
		
	) {
		try {
			Calendar creationDate = Calendar.getInstance();
			Event.Category category = Event.Category.valueOf(cat);

			System.out.println("Rest: " + event_date);
			
			final Event newEvent = this.dao.add(title, description, category, location, capacity, participants, creationDate.getTime(), DateFormat.parseDate(event_date), duration, creator);
			
			return Response.ok(newEvent).build();
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST)
				.entity(iae.getMessage())
			.build();
		} catch (DAOException e) {
			e.printStackTrace();
			return Response.serverError()
				.entity(e.getMessage())
			.build();
		}
	}

}
