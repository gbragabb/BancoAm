import java.util.Scanner;

public class PF extends Cliente{
    private String nome;
    private String cpf;

    public PF(String nome, String cpf, String telefone){
        super(telefone);
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCadastro() {
        return cpf;
    }

    @Override
    public Conta abrirConta() {       

        Scanner sc = new Scanner(System.in);
        System.out.println("Qual conta deseja criar?");
		
		System.out.println("<C>orrente - <P>oupanca - <I>nvetimento");
		    char tipo = sc.nextLine().toLowerCase().charAt(0);
		
        sc.close();
        
        if (tipo == 'p')
            return new Poupanca(this);

        else if (tipo == 'i')
            return new Investimento(this);
            
        else
            return new Corrente(this);
    }
}
