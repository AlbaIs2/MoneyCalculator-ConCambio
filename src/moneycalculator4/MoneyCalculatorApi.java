
package moneycalculator4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MoneyCalculatorApi {
     private static double getExchangeRate(String from, String to) throws Exception{
        URL url = new URL("http://api.fixer.io/latest?base=" + from +"&symbols=" + to);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        InputStreamReader input = new InputStreamReader(connection.getInputStream());
        try (BufferedReader reader = new BufferedReader(input)){
            String line= reader.readLine();
            line = line.substring(line.indexOf(to)+5, line.indexOf("}"));
            return Double.parseDouble(line);
        }
    }

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setVisible(true);
        window.setSize(400,300);
        window.setTitle("Money Exchange Calculator");
        window. setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        window.setContentPane(panel);
        
        JLabel labelAmount = new JLabel("Introduzca cantidad");
        panel.add(labelAmount);
        JTextField textAmount = new JTextField(10);
        panel.add(textAmount);
        
        JLabel labelDivisaO = new JLabel("Introduzca divisa origen");
        panel.add(labelDivisaO);
        JTextField textDivisaO = new JTextField(10);
        panel.add(textDivisaO);
        
        JLabel labelDivisaC = new JLabel("Introduzca divisa cambio");
        panel.add(labelDivisaC);
        JTextField textDivisaC = new JTextField(10);
        panel.add(textDivisaC);
        
        JButton exchangeButton = new JButton("Exchange");
        panel.add(exchangeButton);
        
        JLabel labelBadge = new JLabel("Cambio:");
        panel.add(labelBadge);
        JTextField textBadge = new JTextField(10);
        panel.add(textBadge);
        
        
        
        exchangeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
               
               Double change = 0.0;
                try {
                    change = getExchangeRate(textDivisaO.getText(), textDivisaC.getText());
                } catch (Exception ex) {
                    Logger.getLogger(MoneyCalculatorApi.class.getName()).log(Level.SEVERE, null, ex);
                }
                Double cantidad = change * Double.valueOf(textAmount.getText()).doubleValue();
               textBadge.setText(""+cantidad);
            }
        });
       
    }
    
}
