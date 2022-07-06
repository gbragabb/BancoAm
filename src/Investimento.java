import java.math.BigDecimal;

public class Investimento extends Conta implements Rentavel{
    public Investimento(Cliente titular){
        super(titular);
    }

    public void rende(){
        if(super.getTitular().getClass() == PF.class)
            super.depositar(super.getSaldo().multiply(BigDecimal.valueOf(0.01)));
        else
            super.depositar(super.getSaldo().multiply(BigDecimal.valueOf(0.03)));
    }

    public void investir(){
        rende();
    }
}
