
public abstract class Cliente {

    private String telefone;
    
    public Cliente(String telefone){

        this.telefone = telefone;
    }
    public String getTelefone() {

        return this.telefone;
    }

    public abstract Conta abrirConta();
}
