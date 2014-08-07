package integracao;

import TestHelpers.BaseFakeApplication;
import models.SolucaoDoExercicio;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class SolucaoDoExercicioTest extends BaseFakeApplication {
    @Ignore
    @Test
    public void quandoChamaOMetodoCreateSalvaUmaSolucaoNoBanco() {
        SolucaoDoExercicio novaSolucao = new SolucaoDoExercicio("var x = 1");

        novaSolucao.save();
        SolucaoDoExercicio novaSolucaoNoBanco = SolucaoDoExercicio.find.ref(novaSolucao.id);

        assertEquals(novaSolucaoNoBanco.solucaoDoUsuario, "var x = 1");
        SolucaoDoExercicio.delete(novaSolucao.id);
    }
}