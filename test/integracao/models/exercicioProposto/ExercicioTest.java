package integracao.models.exercicioProposto;

import TestHelpers.BaseFakeApplication;
import com.avaje.ebean.Ebean;
import models.SolucaoDoExercicio;
import models.exercicioProposto.Exercicio;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ExercicioTest extends BaseFakeApplication {

    @Before
    public void setUp() throws Exception {
        List<Exercicio> todosExercicios = Ebean.find(Exercicio.class).findList();
        Ebean.delete(todosExercicios);

        Exercicio exercicio1 = new Exercicio();
        exercicio1.enunciado = "1) - Exercicio 1";
        exercicio1.possivelSolucao = new SolucaoDoExercicio("Está é a possivel Solução");
        exercicio1.resolvido = false;
        exercicio1.save();

        Exercicio exercicio2 = new Exercicio();
        exercicio2.enunciado = "2) - Exercicio 2";
        exercicio2.possivelSolucao = new SolucaoDoExercicio("Está é a possivel Solução");
        exercicio2.resolvido = true;
        exercicio2.save();

        Exercicio exercicio3 = new Exercicio();
        exercicio3.enunciado = "3) - Exercicio 3";
        exercicio3.possivelSolucao = new SolucaoDoExercicio("Está é a possivel Solução");
        exercicio3.resolvido = false;
        exercicio3.save();
    }

    @Test
    public void pegaExerciciosNaoResolvidosDoBanco() throws Exception {
        Exercicio exercicioDoBanco = new Exercicio();

        List<Exercicio> listaDeExercicios = exercicioDoBanco.todosNaoResolvidos();

        assertThat(2, is(listaDeExercicios.size()));
    }
}
