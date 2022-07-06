import java.math.BigDecimal;
import java.util.Scanner;

public class Aplicacao {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escolha o tipo da cliente: (1) PF - (2) PJ");
        int t = sc.nextInt();
        Cliente cliente;
        if(t == 1){
            System.out.println("Insira o nome do cliente");
            String nome = sc.next();
            System.out.println("Insira o cpf do cliente");
            String cpf = sc.next();
            cliente = new PF(nome, cpf);
        }
        else{
            System.out.println("Insira a razão social do cliente");
            String razaoSocial = sc.next();
            System.out.println("Insira o cnpj do cliente");
            String cnpj = sc.next();
            cliente = new PJ(razaoSocial, cnpj);
        }
        System.out.println("Insira o telefone");
        cliente.setTelefone(sc.next());
        System.out.println("Escolha o tipo da conta: (1) Corrente - (2) Poupança - (3) Investimentos");
        Conta conta = cliente.abrirConta(sc.nextInt());
        System.out.println("Conta criada com sucesso!");
        System.out.println(conta.consultarSaldo());
        conta.depositar(BigDecimal.valueOf(10));
        System.out.println(conta.consultarSaldo());
        conta.sacar(BigDecimal.valueOf(5));
        System.out.println(conta.consultarSaldo());
        System.out.println("Escolha o tipo da cliente: (1) PF - (2) PJ");
        t = sc.nextInt();
        Cliente cliente2;
        if(t == 1){
            System.out.println("Insira o nome do cliente");
            String nome = sc.next();
            System.out.println("Insira o cpf do cliente");
            String cpf = sc.next();
            cliente2 = new PF(nome, cpf);
        }
        else{
            System.out.println("Insira a razão social do cliente");
            String razaoSocial = sc.next();
            System.out.println("Insira o cnpj do cliente");
            String cnpj = sc.next();
            cliente2 = new PJ(razaoSocial, cnpj);
        }
        System.out.println("Insira o telefone");
        cliente2.setTelefone(sc.next());
        System.out.println("Escolha o tipo da conta: (1) Corrente - (2) Poupança - (3) Investimentos");
        Conta conta2 = cliente2.abrirConta(sc.nextInt());
        System.out.println("Conta criada com sucesso!");
        conta2.depositar(BigDecimal.valueOf(1000));
        conta2.transferir(BigDecimal.valueOf(200), conta);
        System.out.println(conta.consultarSaldo());
        System.out.println(conta2.consultarSaldo());
        Conta poupanca = cliente.abrirConta(2);
        System.out.println("Conta poupança criada para " + cliente.getNome());
        poupanca.depositar(BigDecimal.valueOf(100));
        System.out.println(poupanca.consultarSaldo());
        Investimento investimento = (Investimento) cliente2.abrirConta(3);
        investimento.depositar(BigDecimal.valueOf(200));
        investimento.investir();
        System.out.println(investimento.consultarSaldo());
    }
}
