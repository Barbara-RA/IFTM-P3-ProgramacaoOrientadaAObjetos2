package exemplo1;

import java.util.List;

public class Pedido {
    private Cliente cliente;

    private List<LinhaDePedido> linhasDePedido;

    public Double calcularPreco(){
        Double total=0.0d;

        for(LinhaDePedido linhaDePedido: linhasDePedido){
            total+=linhaDePedido.calcularPreco();
        }
        return cliente.obterValorComDesconto(total);
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setLinhasDePedido(List<LinhaDePedido> linhasDePedido) {
        this.linhasDePedido = linhasDePedido;
    }

    
}
