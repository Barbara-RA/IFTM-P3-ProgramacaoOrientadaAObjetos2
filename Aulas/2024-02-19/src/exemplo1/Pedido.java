package exemplo1;

import java.util.List;

public class Pedido {
    private Cliente cliente;

    private List<LinhaDePedido> linhasDePedido;

    private Double calcularPrecoBase(Integer quantidade, Double preco){
        return preco*quantidade;
    }

    private Double calcularDesconto(Double precoBase){
        Double percentualDesconto = cliente.obterInformacaoDeDesconto();
        // 5%
        // 5/100
        Double percentualConvertido= percentualDesconto/100d;
        Double valorDesconto=precoBase*percentualConvertido;
        return precoBase-valorDesconto;
    }

    public Double calcularPreco(){
        Double total = 0.0d;

        for(LinhaDePedido linhadePedido:linhasDePedido){
            Integer quantidade = linhadePedido.obterQuantidade();
            Produto produto = linhadePedido.obteProduto();
            Double preco = produto.obterDetaheDoPreco();
            Double precoBase = calcularPrecoBase(quantidade,preco);
            // Double Desconto= preceoBase - cliente.obterInformacaoDeDesconto();
            double precoFinal=calcularDesconto(precoBase);
            total+=precoFinal;
        }
        return total;
    }

    
}
