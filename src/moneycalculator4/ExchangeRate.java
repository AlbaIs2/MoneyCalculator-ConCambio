
package moneycalculator4;

import java.util.Date;


public class ExchangeRate {
     private final Currency from;
    private final Currency to;
    private final Date date;
    private final Double rate; 

    public ExchangeRate(Currency from, Currency to, Date date, Double rate) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.rate = rate;
    }

    public Currency getFrom() {
        return from;
    }

    public Currency getTo() {
        return to;
    }

    public Date getDate() {
        return date;
    }

    public Double getRate() {
        return rate;
    }
    
}
