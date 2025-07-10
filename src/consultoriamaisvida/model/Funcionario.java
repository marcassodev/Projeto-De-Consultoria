
package consultoriamaisvida.model;

public class Funcionario {
    int id;
    String nome;
    String cpf;
    String areaAtuacao;
    String telefone;
    


    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setAreaAtuacao(String areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
}
