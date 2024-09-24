package exemplo1;

public class LinhaDePedido {

    private Produto produto;
    private Integer quantidade;


    public Double calcularPreco() {
        return produto.obterPreco(quantidade);
    }


    public void setProduto(Produto produto2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setProduto'");
    }


    public void setQuantidade(int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setQuantidade'");
    }
    
}
