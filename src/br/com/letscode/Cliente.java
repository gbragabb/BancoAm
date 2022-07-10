package br.com.letscode;

public abstract class Cliente {

    private String telefone;
    
    public Cliente(String telefone){

        this.telefone = telefone;
    }
    public String getTelefone() {

        return this.telefone;
    }
}
