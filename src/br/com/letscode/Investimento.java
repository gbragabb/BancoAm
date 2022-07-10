package br.com.letscode;

import java.math.BigDecimal;

public class Investimento extends Conta implements Rentavel{
    private BigDecimal taxa;

    public Investimento(Cliente titular) {
        super(titular);

        if (titular instanceof PJ) {
            this.taxa = BigDecimal.valueOf(0.03);
        } else {
            this.taxa = BigDecimal.valueOf(0.01);
        }
    }

    public BigDecimal getTaxa() {
        return taxa;
    }

    public void setTaxa(BigDecimal taxa) {
        this.taxa = taxa;
    }

    public void rende(){
        super.depositar(super.getSaldo().multiply(this.taxa));
    }

    public void investir(){
        rende();
    }
}
