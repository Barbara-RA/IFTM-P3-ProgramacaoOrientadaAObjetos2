import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import domain.PessoaEstrangeira;
import domain.PessoaFisica;
import domain.PessoaJuridica;
import domain.Validavel;


public class App {
    public static void main(String[] args) throws Exception {
        Validavel pessoa2 = new PessoaFisica();
        Validavel pessoa3 = new PessoaJuridica();
        Validavel pessoa4 = new PessoaEstrangeira();

        List<Validavel> pessoas = new ArrayList<>(Arrays.asList(pessoa2,pessoa3,pessoa4));

        for (Validavel pessoa: pessoas) {
            chamaValidacao(pessoa);
        }

    }

    private static void chamaValidacao(Validavel pessoa) {
        pessoa.valida();
    }
}
