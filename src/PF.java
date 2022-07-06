public class PF extends Cliente{
    private String nome;
    private String cpf;

    public PF(String nome, String cpf){
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCadastro() {
        return cpf;
    }
}
