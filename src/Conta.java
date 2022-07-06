import java.math.BigDecimal;

public class Conta {
    private int codigo;
    private static int total = 1;
    private BigDecimal saldo = BigDecimal.ZERO;
    private Cliente titular;
    public Conta(){}
    public Conta(Cliente c){
        this.titular = c;
        this.codigo = total++;
    }

    protected BigDecimal getSaldo() {
        return this.saldo;
    }

    protected Cliente getTitular(){
        return this.titular;
    }

    public String consultarSaldo(){
        return "O saldo da conta " + codigo + " é " + this.saldo;
    }

    public void depositar(BigDecimal valor){
        this.saldo = this.saldo.add(valor);
    }
    public void sacar(BigDecimal valor){
        this.saldo = this.saldo.subtract(valor);
    }

    public void transferir(BigDecimal valor, Conta c){
        c.depositar(valor);
        this.saldo = this.saldo.subtract(valor);
    }
}
