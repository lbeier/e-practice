package unitario.model;

import models.GerenciadorDeFeedback;
import models.analisadorLexico.QuebradorDeCodigoEmLinhas;
import models.analisadorSemantico.GerenciadorSemantico;
import models.analisadorSintatico.GerenciadorSintatico;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GerenciadorDeFeedbackTest {

    @Mock private GerenciadorSintatico gerenciadorSintatico;
    @Mock private GerenciadorSemantico gerenciadorSemantico;
    @Mock private QuebradorDeCodigoEmLinhas quebradorDeCodigo;
    private GerenciadorDeFeedback gerenciadorDeFeedback;

    @Before
    public void setUp() throws Exception {
        ArrayList<String> linhaUnica = new ArrayList<String>();
        linhaUnica.add("var x : String");

        ArrayList<String> linhaDupla = new ArrayList<String>();
        linhaDupla.add("var x : String");
        linhaDupla.add("x = \"casa\"");

        ArrayList<String> linhaTripla = new ArrayList<String>();
        linhaTripla.add("var x : String");
        linhaTripla.add("x = 1");
        linhaTripla.add("x = x + 1");

        when(quebradorDeCodigo.quebra("var x : String")).thenReturn(linhaUnica);
        when(quebradorDeCodigo.quebra("var x : String\n x = \"casa\"")).thenReturn(linhaDupla);
        when(quebradorDeCodigo.quebra("var x : String \n x = 1 \n x = x + 1")).thenReturn(linhaTripla);
    }

    @Test
    public void dadoQueReceboUmCodigoComTresExpressoesEntaoOMetodoQuebraSeraChamado() throws Exception {
        String codigo = "var x : String \n x = 1 \n x = x + 1";
        gerenciadorDeFeedback = new GerenciadorDeFeedback(codigo, gerenciadorSintatico, gerenciadorSemantico, quebradorDeCodigo);

        verify(quebradorDeCodigo, times(1)).quebra(codigo);
    }

    @Test
    public void dadoQueFoiRecebidoUmCodigoEntaoDeveSerChamadoOMetodoInterpretaDaClasseGerenciadorDeValidacaoSintatico() throws Exception {
        String codigo = "var x : String\n x = \"casa\"";
        String linha1 = "var x : String";
        String linha2 = "x = \"casa\"";
        gerenciadorDeFeedback = new GerenciadorDeFeedback(codigo, gerenciadorSintatico, gerenciadorSemantico, quebradorDeCodigo);

        gerenciadorDeFeedback.pegaFeedback();

        verify(gerenciadorSintatico).interpreta(linha1);
        verify(gerenciadorSintatico).interpreta(linha2);
    }

    @Ignore
    @Test
    public void dadoQueFoiRecebidoUmCodigoSintaticamenteCorretoEntaoDeveSerChamadoOMetodoInterpretaDaClasseGerenciadorDeValidacaoSemantico() throws Exception {
        String codigo = "var x : String\n x = \"casa\"";
        String linha1 = "var x : String";
        String linha2 = "x = \"casa\"";

        gerenciadorDeFeedback = new GerenciadorDeFeedback(codigo, gerenciadorSintatico, gerenciadorSemantico, quebradorDeCodigo);

        gerenciadorDeFeedback.pegaFeedback();

        verify(gerenciadorSintatico).interpreta(linha1);
        verify(gerenciadorSemantico).interpreta(linha1);
        verify(gerenciadorSintatico).interpreta(linha2);
        verify(gerenciadorSemantico).interpreta(linha2);
    }

    @Test
    public void dadoQueFoiRebebidoUmCodigoComTresExpressoesEntaoChamaTresVezesInterpreta() throws Exception {
        String codigo = "var x : String \n x = 1 \n x = x + 1";
        gerenciadorDeFeedback = new GerenciadorDeFeedback(codigo, gerenciadorSintatico, gerenciadorSemantico, quebradorDeCodigo);

        gerenciadorDeFeedback.pegaFeedback();

        verify(gerenciadorSintatico, times(3)).interpreta(anyString());
    }

    @Ignore
    @Test
    public void dadoQueNaoTenhoErroSintaticoEntaoChamoGerenciadorSemantico() throws Exception {
        String codigo = "numero = 2 + 2";
        gerenciadorDeFeedback = new GerenciadorDeFeedback(codigo, gerenciadorSintatico, gerenciadorSemantico, quebradorDeCodigo);
        gerenciadorDeFeedback.pegaFeedback();

        verify(gerenciadorSemantico).mostraMensagensDeErro();
    }
}
