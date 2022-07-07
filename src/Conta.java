import java.math.BigDecimal;

public abstract class Conta {
    protected int codigo;
    protected static int total = 0;
    protected BigDecimal saldo = BigDecimal.ZERO;
    protected Cliente titular;

    public Conta(Cliente c){
        this.titular = c;
        this.codigo = ++ total;
    }

    public BigDecimal getSaldo() {
        return this.saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
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
        if (valor.compareTo(this.saldo) > 0) {
            System.out.println("O valor do saque não pode ser superior ao saldo da conta!");
        } else {
            this.saldo = this.saldo.subtract(valor);
        }
    }

    public void transferir(BigDecimal valor, Conta c){
        if (valor.compareTo(this.saldo) > 0) {
            System.out.println("O valor da transferência não pode ser superior ao saldo da conta!");
        } else {
            c.depositar(valor);
            this.saldo = this.saldo.subtract(valor);
        }
    }
}
