import java.util.Scanner;

public class PJ extends Cliente{
    
    private String razaoSocial;
    private String cnpj;

    public PJ(String nome, String cnpj, String telefone){
        super(telefone);
        this.cnpj = cnpj;
        this.razaoSocial= nome;
    }
    public String getRazaoSocial(){
        return razaoSocial;
    }
    
    public String getCNPJ() {
        return cnpj;
    }

    @Override
    public Conta abrirConta() {       

        Scanner sc = new Scanner(System.in);
        System.out.println("Qual conta deseja criar?");
		
		System.out.println("<C>orrente - <I>nvetimento");
		    char tipo = sc.nextLine().toLowerCase().charAt(0);
		
        sc.close();
        
        if (tipo == 'i')
            return new Investimento(this);
            
        else
            return new Corrente(this);
    }
}
