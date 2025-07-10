package consultoriamaisvida.model;

public class Consultoria {
    int id;
    public String data;
    public String numero;
    public int idCliente;

    public void setData(String data) {
        this.data = data;
    }
    

    public String getData() {
        return data;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
