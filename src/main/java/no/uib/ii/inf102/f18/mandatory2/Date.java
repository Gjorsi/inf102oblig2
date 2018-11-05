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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + day;
        result = prime * result + month.hashCode();
        result = prime * result + year;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Date other = (Date) obj;
        if (day != other.day)
            return false;
        if (month != other.month)
            return false;
        if (year != other.year)
            return false;
        return true;
    }
}