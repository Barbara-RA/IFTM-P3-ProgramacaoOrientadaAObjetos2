import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class LinhaClienteTeste {
    @Test
    public void testaLinhaCabecalho() {
        String linha = "1600";
        String tipo = linha.substring(0, 1);
        String lote = linha.substring(1, 4);
        Assert.assertEquals("1", tipo);
        Assert.assertEquals("600",lote);
    }

    @Test
    public void testaClienteInclusao() {
        String linha = "2I06045064343Fulano da Silva               Rua XXX 40                    Jardim Rosa                   Uberlandia                    MG19022014181040";
        String inclusaoAlteracao = linha.substring(1,2);
        String cpf = linha.substring(2,13);
        String nome = linha.substring(13,43).trim();
        String endereco = linha.substring(43,73).trim();
        String bairro = linha.substring(73,103).trim();
        String cidade = linha.substring(103,133).trim();
        String estado = linha.substring(133, 135);
        String dataCadastro = linha.substring(135, 143);
        String horaCadastro = linha.substring(143, 149);


        Assert.assertEquals("I", inclusaoAlteracao);
        Assert.assertEquals("06045064343", cpf);
        Assert.assertEquals("Fulano da Silva", nome);
        Assert.assertEquals("Rua XXX 40", endereco);
        Assert.assertEquals("Jardim Rosa", bairro);
        Assert.assertEquals("Uberlandia", cidade);
        Assert.assertEquals("MG", estado);
        Assert.assertEquals("19022014", dataCadastro);
        Assert.assertEquals("181040", horaCadastro);

    }

    @Test
    public void testaConversaoData() throws ParseException{
        String dataCadastro = "19022014";
        String horaCadastro = "181040";
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
        Date dataHoraCadastro = sdf.parse(dataCadastro+horaCadastro);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataHoraCadastro);
        Assert.assertEquals(2014, calendar.get(Calendar.YEAR));
        Assert.assertEquals(Calendar.FEBRUARY, calendar.get(Calendar.MONTH));
        Assert.assertEquals(19, calendar.get(Calendar.DAY_OF_MONTH));
        Assert.assertEquals(18, calendar.get(Calendar.HOUR_OF_DAY));
        Assert.assertEquals(10, calendar.get(Calendar.MINUTE));
        Assert.assertEquals(40, calendar.get(Calendar.SECOND));
    }

}
