package no.uib.ii.inf102.f18.mandatory2;

/**
 * @author You
 */
public final class Date {
    final Month month;
    final int year;
    final int day;

    public Date(Month month, int year, int day) {
        this.month = month;
        this.year = year;
        this.day = day;
    }
    
    public int hashCode() {
        return year*day*month.toString().hashCode();
    }
    
    public boolean equals(Object other) {
        return this.hashCode() == other.hashCode();
    }
}
