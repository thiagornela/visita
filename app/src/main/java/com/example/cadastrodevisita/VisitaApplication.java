package com.example.cadastrodevisita;

import android.app.Application;

import com.example.cadastrodevisita.dao.ColaboradorDAO;
import com.example.cadastrodevisita.dao.ComoNosConheceuDAO;
import com.example.cadastrodevisita.dao.SecretariaDAO;
import com.example.cadastrodevisita.dao.TipoAtendimentoDAO;
import com.example.cadastrodevisita.dao.TurmaDAO;
import com.example.cadastrodevisita.dao.TurnoDAO;
import com.example.cadastrodevisita.dao.UnidadeDAO;
import com.example.cadastrodevisita.model.Colaborador;
import com.example.cadastrodevisita.model.ComoNosConheceu;
import com.example.cadastrodevisita.model.Secretaria;
import com.example.cadastrodevisita.model.TipoAtendimento;
import com.example.cadastrodevisita.model.Turma;
import com.example.cadastrodevisita.model.Turno;
import com.example.cadastrodevisita.model.Unidade;

public class VisitaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //criaAlunosDeTeste();
        //criaSituacaoDeTeste();
        criaUnidadeDeTeste();
        criaSecretariaDeTeste();
        criaTipoAtendimentoDeTeste();
        criaColaboradorDeTeste();
        criaComoNosConheceuDeTeste();
        criaTurmaDeTeste();
        criaTurnoDeTeste();

    }

    private void criaTurmaDeTeste() {
        TurmaDAO dao = new TurmaDAO();
        Turma m1 = new Turma("Maternal 1");
        Turma m2 = new Turma("Maternal 2");
        dao.salva(m1);
        dao.salva(m2);
    }

    private void criaTurnoDeTeste() {
        TurnoDAO dao = new TurnoDAO();
        Turno tarde = new Turno("Tarde");
        Turno manha = new Turno("Manhã");
        dao.salva(tarde);
        dao.salva(manha);
    }

    private void criaComoNosConheceuDeTeste() {
        ComoNosConheceuDAO dao = new ComoNosConheceuDAO();
        ComoNosConheceu Facebook = new ComoNosConheceu("Facebook App");
        ComoNosConheceu Instagram = new ComoNosConheceu("Instagram App");
        dao.salva(Facebook);
        dao.salva(Instagram);
    }


    private void criaColaboradorDeTeste() {
        ColaboradorDAO dao = new ColaboradorDAO();
        Colaborador thiago = new Colaborador("Thiago App");
        Colaborador elisana = new Colaborador("Elisana App");
        dao.salva(thiago);
        dao.salva(elisana);
    }

    private void criaTipoAtendimentoDeTeste() {
        TipoAtendimentoDAO dao = new TipoAtendimentoDAO();
        TipoAtendimento telefone = new TipoAtendimento("Telefone App");
        TipoAtendimento presencial = new TipoAtendimento("Presencial App");
        dao.salva(telefone);
        dao.salva(presencial);
    }

    private void criaSecretariaDeTeste() {
        SecretariaDAO dao = new SecretariaDAO();
        Secretaria eliana = new Secretaria("Eliana App");
        Secretaria alessandra = new Secretaria("Alessandra App");
        dao.salva(eliana);
        dao.salva(alessandra);
    }

    private void criaUnidadeDeTeste() {
        UnidadeDAO dao = new UnidadeDAO();
        Unidade buritis = new Unidade("Buritis App");
        Unidade gutierrez = new Unidade("Gutierrez App");
        dao.salva(buritis);
        dao.salva(gutierrez);
    }


//    private void criaAlunosDeTeste() {
//        VisitaDAO dao = new VisitaDAO();
//        dao.salva(new Visita(
//                5,
//                "Thiago",
//                "28/05/1990",
//                "Maternal 1",
//                "Manhã",
//                "Rita",
//                "858031",
//                "333",
//                "999",
//                "rita@gmail.com",
//                "Buritis App",
//                "Eliana App",
//                "Telefone App",
//                "Elisana App",
//                "Facebook App",
//                "Matriculado",
//                data
//                ));
//    }

//    private void criaSituacaoDeTeste() {
//        SituacaoDAO dao = new SituacaoDAO();
//        Situacao matriculado = new Situacao("Matriculado");
//        Situacao aguardando_retorno_da_escola_para = new Situacao("Aguardando retorno da escola para ");
//        dao.salva(matriculado);
//        dao.salva(aguardando_retorno_da_escola_para);
//    }


}
