
package moneycalculator4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Scanner;

public class MoneyCalculator4 {
public static void main(String[] args) throws Exception{
        MoneyCalculator4 moneyCalculator = new MoneyCalculator4();
        moneyCalculator.execute();        
    }
    
    private Money money;
    private ExchangeRate exchangeRate;
    private Currency currencyTo;
    private final CurrencyList currencyList = new CurrencyList();
    
    public MoneyCalculator4() {
        currencyList.add(new Currency("EUR","Euro","€"));
        currencyList.add(new Currency("USD","Dolar","$"));
        currencyList.add(new Currency("GBP","Libra","£"));
      
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
        Currency currencys = currencyList.get(scanner.next());
        
        money = new Money(amount,currencys);
        
        System.out.println("Introduzca divisa destino");
        currencyTo = currencyList.get(scanner.next());
    }
    
    private void process() throws Exception{
        exchangeRate = getExchangeRate(money.getCurrency(), currencyTo);
    }
    
    private void output(){
        System.out.println(money.getAmount() + " " + money.getCurrency().getSymbol() + " equivalen a " + money.getAmount() * exchangeRate.getRate() + currencyTo.getSymbol());
    }
    
    private static ExchangeRate getExchangeRate(Currency from, Currency to) throws Exception{
        URL url = new URL("http://api.fixer.io/latest?base=" + from.getCode() +"&symbols=" + to.getCode());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        InputStreamReader input = new InputStreamReader(connection.getInputStream());
        try (BufferedReader reader = new BufferedReader(input)){
            String line= reader.readLine();
            line = line.substring(line.indexOf(to.getCode())+5, line.indexOf("}"));
            return new ExchangeRate(from,to,new Date(),Double.parseDouble(line));
        }
    
    }
    
}
