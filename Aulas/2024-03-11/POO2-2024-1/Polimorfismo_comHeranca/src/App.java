import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import domain.Pessoa;
import domain.PessoaEstrangeira;
import domain.PessoaFisica;
import domain.PessoaJuridica;
import domain.PessoaRegular;

public class App {
    public static void main(String[] args) throws Exception {
        Pessoa pessoa2 = new PessoaFisica();
        Pessoa pessoa3 = new PessoaJuridica();
        Pessoa pessoa4 = new PessoaEstrangeira();


        List<Pessoa> pessoas = new ArrayList<>(Arrays.asList(pessoa2,pessoa3,pessoa4));
        List<PessoaRegular> pessoaRegulares= new ArrayList<>(Arrays.asList((PessoaRegular)pessoa2, (PessoaRegular)pessoa3));

        for (Pessoa pessoa: pessoas) {
            chamaValidacao(pessoa);
        }

        for (PessoaRegular pessoaRegular: pessoaRegulares){
            pessoaRegular.isPessoaRegular();
        }

    }

    private static void chamaValidacao(Pessoa pessoa) {
        pessoa.valida();
    }
}
