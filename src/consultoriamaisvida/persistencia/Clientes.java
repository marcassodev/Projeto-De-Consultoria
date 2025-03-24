package consultoriamaisvida.persistencia;


public class Clientes {

    String nome;
    int id;
    String data;
    String rg;
    String cpf;
    String telefone;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }
    
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
