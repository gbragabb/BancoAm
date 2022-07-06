public class PJ extends Cliente{
    public PJ(String razaoSocial, String cnpj){
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
    }
    public String getNome(){
        return razaoSocial;
    }
    private String razaoSocial;
    private String cnpj;

    public String getCadastro() {
        return cnpj;
    }
}
