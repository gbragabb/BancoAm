import java.math.BigDecimal;

public class Poupanca extends Conta implements Rentavel{
    private BigDecimal taxa = BigDecimal.valueOf(0.005);

    public Poupanca(Cliente titular){
        super(titular);
    }

    public BigDecimal getTaxa() {
        return taxa;
    }

    public void setTaxa(BigDecimal taxa) {
        this.taxa = taxa;
    }

    public void rende(){
        super.depositar(getSaldo().multiply(this.taxa));
    }

    @Override
    public void depositar(BigDecimal valor){
        super.depositar(valor);
        rende();
    }
}
