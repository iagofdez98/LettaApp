package es.uvigo.esei.daa.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author David Vila, Iago Fernández
 *
 */
public class DateFormat {
	
	private final static SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	
	
	/**
	 * Convierte una fecha formateada a un objeto Date
	 * @param rawDate
	 * El formato de la fecha es: dd/MM/yyyy hh:mm:ss
	 * @return Date
	 */
	public static Date parseDate(String rawDate) {
		try {
			return FORMAT.parse (rawDate);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Date format is incorrect", e);
		}
	}
	
	/**
	 * Convierte un objeto fecha a un string formateado
	 * @param date
	 * El formato de la fecha es: dd/MM/yyyy hh:mm:ss
	 * @return String
	 */
	public static String formatDate(Date date) {
		return FORMAT.format(date);
	}
}
