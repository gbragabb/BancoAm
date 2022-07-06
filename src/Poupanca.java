import java.math.BigDecimal;

public class Poupanca extends Conta implements Rentavel{
    public Poupanca(Cliente titular){
        super(titular);
    }

    public void rende(){
        super.depositar(getSaldo().multiply(BigDecimal.valueOf(0.005)));
    }

    @Override
    public void depositar(BigDecimal valor){
        super.depositar(valor);
        rende();
    }
}
