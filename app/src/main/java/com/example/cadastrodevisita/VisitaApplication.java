package com.example.cadastrodevisita;

import android.app.Application;

import com.example.cadastrodevisita.dao.CategoriaDAO;
import com.example.cadastrodevisita.dao.ColaboradorDAO;
import com.example.cadastrodevisita.dao.ComoNosConheceuDAO;
import com.example.cadastrodevisita.dao.SecretariaDAO;
import com.example.cadastrodevisita.dao.ServidorEmailDAO;
import com.example.cadastrodevisita.dao.TipoAtendimentoDAO;
import com.example.cadastrodevisita.dao.TurmaDAO;
import com.example.cadastrodevisita.dao.TurnoDAO;
import com.example.cadastrodevisita.dao.UnidadeDAO;
import com.example.cadastrodevisita.dao.VisitaDAO;
import com.example.cadastrodevisita.model.Categoria;
import com.example.cadastrodevisita.model.Colaborador;
import com.example.cadastrodevisita.model.ComoNosConheceu;
import com.example.cadastrodevisita.model.Secretaria;
import com.example.cadastrodevisita.model.ServidorEmail;
import com.example.cadastrodevisita.model.TipoAtendimento;
import com.example.cadastrodevisita.model.Turma;
import com.example.cadastrodevisita.model.Turno;
import com.example.cadastrodevisita.model.Unidade;
import com.example.cadastrodevisita.model.Visita;

import static com.example.cadastrodevisita.ui.Constantes.SITUACAO_AMBIENTACAO_PARA;
import static com.example.cadastrodevisita.ui.Constantes.SITUACAO_CONTATO_ESCOLA_PARA;

public class VisitaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        criaUnidadeDeTeste();
        criaSecretariaDeTeste();
        criaTipoAtendimentoDeTeste();
        criaColaboradorDeTeste();
        criaComoNosConheceuDeTeste();
        criaTurmaDeTeste();
        criaTurnoDeTeste();
        criaVisita();
        criaServidorEmailDeTeste();
        criaCategoriaTeste();
    }

    private void criaVisita() {
        VisitaDAO visitaDAO = new VisitaDAO();
        Visita visita = new Visita();
        visita.setNome_crianca("Thiago Ornelas");
        visita.setDataNascimento_crianca("28/5/1990");
        visita.setTurma_crianca("Maternal 1");
        visita.setTurno_crianca("Manhã");
        visita.setNome_responsavel_1("Rita Ornelas");
        visita.setTelefone_fixo_responsavel_1("(31)3388-8131");
        visita.setTelefone_celular_responsavel_1("(31)98676-4416");
        visita.setEmail_responsavel_1("t@t.com.br");
        visita.setDataCadastro("25/1/2021");
        visita.setUnidade("Gutierrez");
        visita.setSecretaria("Alessandra");
        visita.setTipoAtendimento("Telefone");
        visita.setComoNosConheceu("Facebook");
        visita.setColaborador("Elisana");
        visita.setSituacao(SITUACAO_CONTATO_ESCOLA_PARA);
        visita.setDataAgendada("25/1/2021");
        visitaDAO.salva((visita));

        Visita visita2 = new Visita();
        visita2.setNome_crianca("Criança Teste 2");
        visita2.setDataNascimento_crianca("28/5/1990");
        visita2.setTurma_crianca("Maternal 1");
        visita2.setTurno_crianca("Manhã");
        visita2.setNome_responsavel_1("Teste Responsável");
        visita2.setTelefone_fixo_responsavel_1("(31)3388-8131");
        visita2.setTelefone_celular_responsavel_1("(31)98676-4416");
        visita2.setEmail_responsavel_1("t@t.com.br");
        visita2.setDataCadastro("25/1/2021");
        visita2.setUnidade("Buritis");
        visita2.setSecretaria("Eliana");
        visita2.setTipoAtendimento("Telefone");
        visita2.setComoNosConheceu("Facebook");
        visita2.setColaborador("Elisana");
        visita2.setSituacao(SITUACAO_AMBIENTACAO_PARA);
        visita2.setDataAgendada("25/5/2021");
        visitaDAO.salva((visita2));

        Visita visita3 = new Visita();
        visita3.setNome_crianca("Criança Teste 3");
        visita3.setDataNascimento_crianca("28/5/1990");
        visita3.setTurma_crianca("Maternal 1");
        visita3.setTurno_crianca("Manhã");
        visita3.setNome_responsavel_1("Teste Responsável");
        visita3.setTelefone_fixo_responsavel_1("(31)3388-8131");
        visita3.setTelefone_celular_responsavel_1("(31)98676-4416");
        visita3.setEmail_responsavel_1("t@t.com.br");
        visita3.setDataCadastro("25/1/2021");
        visita3.setUnidade("Buritis");
        visita3.setSecretaria("Eliana");
        visita3.setTipoAtendimento("Telefone");
        visita3.setComoNosConheceu("Facebook");
        visita3.setColaborador("Elisana");
        visita3.setSituacao(SITUACAO_AMBIENTACAO_PARA);
        visita3.setDataAgendada("25/5/2021");
        visitaDAO.salva((visita3));

        Visita visita4 = new Visita();
        visita4.setNome_crianca("Thiago Ornelas");
        visita4.setDataNascimento_crianca("28/5/1990");
        visita4.setTurma_crianca("Maternal 1");
        visita4.setTurno_crianca("Manhã");
        visita4.setNome_responsavel_1("Rita Ornelas");
        visita4.setTelefone_fixo_responsavel_1("(31)3388-8131");
        visita4.setTelefone_celular_responsavel_1("(31)98676-4416");
        visita4.setEmail_responsavel_1("t@t.com.br");
        visita4.setDataCadastro("25/1/2021");
        visita4.setUnidade("Gutierrez");
        visita4.setSecretaria("Alessandra");
        visita4.setTipoAtendimento("Telefone");
        visita4.setComoNosConheceu("Facebook");
        visita4.setColaborador("Elisana");
        visita4.setSituacao(SITUACAO_CONTATO_ESCOLA_PARA);
        visita4.setDataAgendada("25/1/2021");
        visitaDAO.salva((visita4));

    }

    private void criaTurmaDeTeste() {
        TurmaDAO dao = new TurmaDAO();
        Turma m1 = new Turma(1000, "Maternal 1", "Buritis");
        Turma m2 = new Turma(2000, "Maternal 2", "Gutierrez");
        dao.salva(m1);
        dao.salva(m2);
    }

    private void criaCategoriaTeste() {
        CategoriaDAO dao = new CategoriaDAO();
        Categoria m1 = new Categoria("Distância");
        Categoria m2 = new Categoria("Estrutura");
        Categoria m3 = new Categoria("Pedagógico");
        Categoria m4 = new Categoria("Financeiro");
        Categoria m5 = new Categoria("Outros");
        dao.salva(m1);
        dao.salva(m2);
        dao.salva(m3);
        dao.salva(m4);
        dao.salva(m5);
    }

    private void criaServidorEmailDeTeste() {
        ServidorEmailDAO dao = new ServidorEmailDAO();
        ServidorEmail s1 = new ServidorEmail("secretaria@bilboque.com.br", "527", "555", "12345");
        dao.salva(s1);
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
        ComoNosConheceu Facebook = new ComoNosConheceu("Facebook");
        ComoNosConheceu Instagram = new ComoNosConheceu("Instagram");
        dao.salva(Facebook);
        dao.salva(Instagram);
    }


    private void criaColaboradorDeTeste() {
        ColaboradorDAO dao = new ColaboradorDAO();
        Colaborador thiago = new Colaborador("Imaculada");
        Colaborador elisana = new Colaborador("Elisana");
        dao.salva(thiago);
        dao.salva(elisana);
    }

    private void criaTipoAtendimentoDeTeste() {
        TipoAtendimentoDAO dao = new TipoAtendimentoDAO();
        TipoAtendimento telefone = new TipoAtendimento("Telefone");
        TipoAtendimento presencial = new TipoAtendimento("Presencial");
        dao.salva(telefone);
        dao.salva(presencial);
    }

    private void criaSecretariaDeTeste() {
        SecretariaDAO dao = new SecretariaDAO();
        Secretaria eliana = new Secretaria("Eliana");
        Secretaria alessandra = new Secretaria("Alessandra");
        dao.salva(eliana);
        dao.salva(alessandra);
    }

    private void criaUnidadeDeTeste() {
        UnidadeDAO dao = new UnidadeDAO();
        Unidade buritis = new Unidade();
        buritis.setUnidade("Buritis");
        Unidade gutierrez = new Unidade();
        gutierrez.setUnidade("Gutierrez");
        dao.salva(buritis);
        dao.salva(gutierrez);
    }
}
