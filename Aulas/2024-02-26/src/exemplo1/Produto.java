package exemplo1;

public class Produto {

    private String nome;

    private Double preco;

    public Double obterPreco(Integer quantidade) {
        return preco*quantidade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public void setPreco(Double preco) {
        this.preco = preco;
    }

    
    
}
