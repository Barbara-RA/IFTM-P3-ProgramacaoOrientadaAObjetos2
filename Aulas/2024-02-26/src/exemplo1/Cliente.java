package exemplo1;

public class Cliente {
    
    // private String nome;

    private Double desconto;

    public Double obterValorComDesconto(Double precoBase) {
        Double percentualConvertido=desconto/100d;
        Double valorDesconto = precoBase * percentualConvertido;
        return precoBase - valorDesconto;
    }

    public void setPercentualDesconto(double d) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPercentualDesconto'");
    }


}
