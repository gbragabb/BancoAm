package br.com.letscode;

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
}
