package es.uvigo.esei.daa.util;

import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateTimeAdapter extends XmlAdapter<String, Date>{

    @Override
    public Date unmarshal(String xml) throws Exception {
        return DateFormat.parseDate(xml);
    }

    @Override
    public String marshal(Date object) throws Exception {
        return DateFormat.formatDate(object);
    }

}
