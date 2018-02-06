
package moneycalculator4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MoneyCalculator4 {
public static void main(String[] args) throws Exception{
        MoneyCalculator4 moneyCalculator = new MoneyCalculator4();
        moneyCalculator.execute();        
    }
    
    private Money money;
    private Double exchangeRate;
    private Currency currencyFrom;
    private Currency currencyTo;
    Map <String,Currency> currency = new HashMap<>();


    public MoneyCalculator4() {
        currency.put("Euro", new Currency("EUR","euro","€"));
        currency.put("Dolar", new Currency("USD","dolar","$"));
        currency.put("Libra", new Currency("GBP","libra esterlina","£"));
    }
    
    
    
    private  void execute() throws Exception{
        input();
        process();
        output();
    }
    
    private void input(){
        System.out.println("Introduzca cantidad");
        Scanner scanner = new Scanner(System.in);
        Double amount = Double.parseDouble(scanner.next());
        
        System.out.println("Introduzca divisa origen");
        Currency currencys = currency.get(scanner.next());
        
        money = new Money(amount,currencys);
        
        System.out.println("Introduzca divisa destino");
        currencyTo = currency.get(scanner.next());
    }
    
    private void process() throws Exception{
        exchangeRate = getExchangeRate(money.getCurrency().getCode(), currencyTo.getCode());
    }
    
    private void output(){
        System.out.println(money.getAmount() + " " + money.getCurrency().getSymbol() + " equivalen a " + money.getAmount() * exchangeRate + currencyTo.getSymbol());
    }
    
    private static double getExchangeRate(String form, String to) throws Exception{
        URL url = new URL("http://api.fixer.io/latest?base=" + form +"&symbols=" + to);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        InputStreamReader input = new InputStreamReader(connection.getInputStream());
        try (BufferedReader reader = new BufferedReader(input)){
            String line= reader.readLine();
            line = line.substring(line.indexOf(to)+5, line.indexOf("}"));
            return Double.parseDouble(line);
        }
    }
    
}
