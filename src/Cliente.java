import java.util.Scanner;

public abstract class Cliente {
    private String telefone;

    protected void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public abstract String getCadastro();

    public abstract String getNome();

    public Conta abrirConta(int escolha){
        Conta c;
        switch (escolha){
            case 2:
                c = new Poupanca(this);
                break;
            case 3:
                c = new Investimento(this);
                break;
            default:
                c = new Corrente(this);
                break;
        }
        return c;
    }
}
