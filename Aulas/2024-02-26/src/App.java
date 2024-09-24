import java.util.Arrays;

import exemplo1.Cliente;
import exemplo1.LinhaDePedido;
import exemplo1.Pedido;
import exemplo1.Produto;

public class App {
    public static void main(String[] args) throws Exception {
       Produto produto = new Produto();
       produto.setNome("Iphone 14s");
       produto.setPreco(4000d);


       Cliente cliente = new Cliente();
       cliente.setPercentualDesconto(5d);


       LinhaDePedido linhaDePedido= new LinhaDePedido();
       linhaDePedido.setProduto(produto);
       linhaDePedido.setQuantidade(3);

       Pedido pedido = new Pedido();
       pedido.setCliente(cliente);
       pedido.setLinhasDePedido(Arrays.asList(linhaDePedido));

       System.out.println(pedido.calcularPreco());


    }
    

    
}
