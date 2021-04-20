package es.uvigo.esei.daa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;

import es.uvigo.esei.daa.entities.Event;

public class EventDAO extends DAO {

	/**
	 * Convierte el resultado de una consulta a la BD a un objeto Event
	 * @param result
	 * @param numAssistants
	 * @return Event
	 * @throws SQLException
	 */
	private Event rowToEntity(ResultSet result, int numAssistants) throws SQLException{
		return new Event(
			result.getInt("id"), 
			result.getString("title"), 
			result.getString("description"), 
			Event.Category.valueOf(result.getString("category")), 
			result.getString("location"),
			result.getInt("capacity"),
			numAssistants,
			result.getTimestamp("creation_date"),
			result.getTimestamp("event_date"),
			result.getInt("duration"),
			result.getString("creator")
		);
	}
	
	/**
	 * Devuelve el numero de asistentes de un evento
	 * @param id_event
	 * @param connect
	 * @return Integer
	 * @throws DAOException
	 */
	private int getNumAssistants(int id_event, Connection connect) throws DAOException {
		try{
			final String query = "SELECT COUNT(*) as num_assistants FROM assistants WHERE id_event= ?";
			
			try(final PreparedStatement statement = connect.prepareStatement(query)){
				statement.setInt(1, id_event);
				try(final ResultSet result = statement.executeQuery()){
					
					if(result.next()) {
						return result.getInt("num_assistants");
					}else {
						System.out.println("Illegal");
						throw new IllegalArgumentException("Invalid event id");
					}
					
				}
			}
		}catch(Exception exc) {
			exc.printStackTrace();
			throw new DAOException(exc);
		}
		
	}
	
	/**
	 * Obtiene una lista de los 12 eventos mas recientes
	 * @return List<Event>
	 * @throws DAOException
	 */
	public List<Event> getRecent() throws DAOException {
		try (final Connection connect = this.getConnection()) {
			final List<Event> recentEvents = new LinkedList<>();
			connect.setAutoCommit(false);

			final String query = "SELECT e.* FROM events e "
					+ "WHERE e.event_date > now() AND e.capacity > (SELECT count(*) FROM assistants a WHERE e.id = a.id_event) "
					+ "ORDER BY creation_date DESC LIMIT 12";

			try (final PreparedStatement statement = connect.prepareStatement(query)) {
				try (final ResultSet result = statement.executeQuery()) {
					while (result.next()) {
						recentEvents.add(rowToEntity(result, getNumAssistants(result.getInt("id"), connect)));
					}
				}
				connect.commit();

				return recentEvents;
			} catch (SQLException exc) {
				connect.rollback();
				exc.printStackTrace();
				throw new DAOException(exc);
			}
		} catch (SQLException exc) {
			exc.printStackTrace();
			throw new DAOException(exc);
		}
	}
	
	/**
	 * Recupera un evento por su id
	 * @return Event
	 * @throws DAOException
	 */
	public Event get(int id) throws DAOException {
		try(final Connection connect = this.getConnection()){
			
			//TODO: Comprobar que el usuario esta en la bd
			
			final String query = "SELECT * FROM events WHERE id =?";
			
			try(final PreparedStatement statement = connect.prepareStatement(query)){
				statement.setInt(1, id);
				try(final ResultSet result = statement.executeQuery()){
					
					if(result.next()) {
						return rowToEntity(result,getNumAssistants(result.getInt("id"), connect));
					} else {
						throw new IllegalArgumentException("Invalid id");
					}
				} 
			}
			}catch(SQLException ex) {
				throw new DAOException(ex);
				
			}
		}	
	
	/**
	 * Añade un evento a la bd
	 * @param title
	 * @param description
	 * @param category
	 * @param location
	 * @param capacity
	 * @param participants
	 * @param creation_date
	 * @param event_date
	 * @param duration
	 * @param creator
	 * @return Event
	 * @throws DAOException
	 * @throws IllegalArgumentException
	 */
	public Event add(String title, String description, Event.Category category, String location, int capacity, 
			int participants, Date creation_date, Date event_date, int duration, String creator) throws DAOException, IllegalArgumentException{
		if (title == null || description == null || location == null || capacity < 1 || event_date == null || duration % 15 != 0 || creator == null) {
			throw new IllegalArgumentException("Incorrects arguments");
		}
		
		System.out.println("DAO: " + event_date);
		
		try (Connection conn = this.getConnection()) {
			final String query = "INSERT INTO events VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			try (PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, title);
				statement.setString(2, description);
				statement.setString(3, category.toString());
				statement.setString(4, location);
				statement.setInt(5, capacity);
				statement.setDate(6, new java.sql.Date(event_date.getTime()));
				statement.setDate(7, new java.sql.Date(creation_date.getTime()));
				statement.setInt(8, duration);
				statement.setString(9, creator);

				
				
				if (statement.executeUpdate() == 1) {
					try (ResultSet resultKeys = statement.getGeneratedKeys()) {
						if (resultKeys.next()) {
							return new Event(resultKeys.getInt(1), title, description, category, location, capacity, participants, creation_date, event_date, duration, creator);
						} else {
							throw new SQLException("Error retrieving inserted id");
						}
					}
				} else {
					throw new SQLException("Error inserting value");
				}
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		
	}
}
