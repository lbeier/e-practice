package unitario.controller;

import TestHelpers.BaseTestServer;
import org.junit.Test;
import play.mvc.Result;
import java.util.HashMap;
import static org.fest.assertions.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.withName;
import static play.test.Helpers.*;

public class ApplicationTest extends BaseTestServer {

    @Test
    public void quandoChamaOMetodoIndexRedirecionaParaOutraRota() {
        Result result;
        result = callAction(controllers.routes.ref.Application.index());
        assertThat(status(result)).isEqualTo(SEE_OTHER);
    }

    @Test
    public void quandoChamaOMetodoCriaExerciciosRedirecionaParaOutraRota() {
        Result result;
        result = callAction(controllers.routes.ref.Application.criaExercicios());
        assertThat(status(result)).isEqualTo(SEE_OTHER);
    }

    @Test
    public void rotaSolucoesComMetodoPostDeveRenderizarUmaView() throws Exception {
        final HashMap<String, String> fakeForm = new HashMap<String, String>();
        fakeForm.put("solucaoDoUsuario", "var x: Integer");
        Result result = routeAndCall(fakeRequest(POST, "/solucoes").withFormUrlEncodedBody(fakeForm));
        assertThat(result).isNotNull();
    }

    @Test
    public void quandoAcessarRotaInexistenteOResultDeveSerNulo() throws Exception {
        Result result = routeAndCall(fakeRequest(GET, "/rotaInexistente"));
        assertThat(result).isNull();
    }

    @Test
    public void quandoPostaNovaSolucaoRetornaMensagemDeSucesso() throws Exception {
        testBrowser.goTo(URL_ENVIRONMENT);
        testBrowser.fill("#solucaoDoUsuario").with("var x  = 1");
        testBrowser.$("#botaoDeEnviar").click();
        testBrowser.$("#solucaoDoUsuario").submit();

        assertThat(testBrowser.getBaseUrl() + testBrowser.url()).isEqualTo(URL_ENVIRONMENT + "/solucoes");
        assertThat(testBrowser.$("#status", 0).getText()).isEqualTo("Status: sua solução foi salva com sucesso!");
    }

    @Test
    public void mantemASolucaoNaCaixaDeTextoAposEnviarASolucao() throws Exception {
        testBrowser.goTo(URL_ENVIRONMENT);
        testBrowser.fill("#solucaoDoUsuario").with("var esseTextoSeraMantido : String");
        testBrowser.find("button", withName("valor")).click();

        String textoDaTextArea = testBrowser.$("#solucaoDoUsuario").getText();
        assertThat(textoDaTextArea).contains("var esseTextoSeraMantido : String");
    }

    @Test
    public void quandoPostaSolucaoEmBrancoRetornaMensagemDeErro() throws Exception {
        testBrowser.goTo(URL_ENVIRONMENT);
        testBrowser.fill("#solucaoDoUsuario").with("");
        testBrowser.$("#botaoDeEnviar").click();
        testBrowser.$("#solucaoDoUsuario").submit();
        String label = testBrowser.$("#status").getText();

        assertThat(label).isEqualTo("Status: erro!");
    }
}