package no.uib.ii.inf102.f18.mandatory2;

/**
 * @author You
 */
public class Event {
    final Date date;
    final String title;
    String description;

    public Event(Date date, String title) {
        this.date = date;
        this.title = title;
    }
    
    public int hashCode() {
        return date.hashCode()*title.hashCode();
    }
    
    public boolean equals(Object other) {
        return this.hashCode() == other.hashCode();
    }
}
