import domain.Pessoa;
import domain.PessoaEstrangeira;
import domain.PessoaFisica;
import domain.PessoaJuridica;

public class AppSemPolimorfismo {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        Pessoa pessoa1 = new PessoaFisica();
       // Pessoa pessoa2 = new Pessoa();
        Pessoa pessoa3 = new PessoaJuridica();
        Pessoa pessoa4 = new PessoaEstrangeira();

        chamaValidacao(pessoa1);
     //   chamaValidacao(pessoa2);
        chamaValidacao(pessoa3);
        chamaValidacao(pessoa4);
    }

    private static void chamaValidacao(Pessoa pessoa) {
        pessoa.valida();
        if (pessoa instanceof PessoaFisica) {
            //chama o método de validar o cpf
        } else if (pessoa instanceof PessoaJuridica) {
            //chama o método de validar o cnpj
        } else if (pessoa instanceof PessoaEstrangeira) {
            //chama o método de validar o passaporte
        }
    }
}
