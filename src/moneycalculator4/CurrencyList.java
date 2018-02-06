
package moneycalculator4;

import java.util.HashMap;
import java.util.Map;


public class CurrencyList {
    private  Map <String,Currency> currencies = new HashMap<>();
    public void add(Currency currency){
        currencies.put(currency.getName(),currency);
    }
    public Currency get(String code){
        return currencies.get(code);
    
    }
}
