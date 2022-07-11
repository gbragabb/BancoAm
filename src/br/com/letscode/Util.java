package br.com.letscode;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.math.BigDecimal;
import java.util.Scanner;

public class Util {
    
    public static String formataValores(BigDecimal valor){

        Locale locale = new Locale("pt","br");
        NumberFormat format = DecimalFormat.getCurrencyInstance(locale);
        
        return format.format(valor.doubleValue());
    }

    public final static void limpaConsole(Scanner sc){  

        System.out.print("\nDigite ENTER para continuar...");
        sc.nextLine();
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
}
