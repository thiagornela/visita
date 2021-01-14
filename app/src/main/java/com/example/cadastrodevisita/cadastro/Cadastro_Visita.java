package com.example.cadastrodevisita.cadastro;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.dao.ColaboradorDAO;
import com.example.cadastrodevisita.dao.ComoNosConheceuDAO;
import com.example.cadastrodevisita.dao.SecretariaDAO;
import com.example.cadastrodevisita.dao.SituacaoDAO;
import com.example.cadastrodevisita.dao.TipoAtendimentoDAO;
import com.example.cadastrodevisita.dao.TurmaDAO;
import com.example.cadastrodevisita.dao.TurnoDAO;
import com.example.cadastrodevisita.dao.UnidadeDAO;
import com.example.cadastrodevisita.dao.VisitaDAO;
import com.example.cadastrodevisita.model.Colaborador;
import com.example.cadastrodevisita.model.ComoNosConheceu;
import com.example.cadastrodevisita.model.Secretaria;
import com.example.cadastrodevisita.model.Situacao;
import com.example.cadastrodevisita.model.TipoAtendimento;
import com.example.cadastrodevisita.model.Turma;
import com.example.cadastrodevisita.model.Turno;
import com.example.cadastrodevisita.model.Unidade;
import com.example.cadastrodevisita.model.Visita;
import com.google.android.material.textfield.TextInputLayout;

import static com.example.cadastrodevisita.ui.activities.Constantes.CHAVE_VISITA;

public class Cadastro_Visita extends AppCompatActivity {

    public static final String TITULO_APPBAR_NOVA_VISITA = "Cadastro de visita";
    private static final String TITULO_APPBAR_EDITA_VISITA = "Edição de visita";

    private ImageView campoImagemFamilia;

    private TextInputLayout campo_nome_crianca;
    private TextInputLayout campo_data_nascimento;

    private TextInputLayout campo_nome_responsavel_1;
    private TextInputLayout campo_cpf_responsavel_1;
    private TextInputLayout campo_telefone_fixo_responsavel_1;
    private TextInputLayout campo_telefone_celular_responsavel_1;
    private TextInputLayout campo_email_responsavel_1;

    private AutoCompleteTextView spinnerUnidade;
    private final UnidadeDAO unidadeDAO = new UnidadeDAO();
    private AutoCompleteTextView spinnerSecretaria;
    private final SecretariaDAO secretariaDAO = new SecretariaDAO();
    private AutoCompleteTextView spinnerTipoAtendimento;
    private final TipoAtendimentoDAO tipoAtendimentoDAO = new TipoAtendimentoDAO();
    private AutoCompleteTextView spinnerColaborador;
    private final ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
    private AutoCompleteTextView spinnerComoNosConheceu;
    private final ComoNosConheceuDAO comoNosConheceuDAO = new ComoNosConheceuDAO();
    private AutoCompleteTextView spinnerSituacao;
    private final SituacaoDAO situacaoDAO = new SituacaoDAO();
    private AutoCompleteTextView spinnerTurma;
    private final TurmaDAO turmaDAO = new TurmaDAO();
    private AutoCompleteTextView spinnerTurno;
    private final TurnoDAO turnoDAO = new TurnoDAO();

    private TextInputLayout campo_data_situacao_limite;

    private final VisitaDAO visitaDAO = new VisitaDAO();
    private Visita visita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_visita);
        inicializacaoDosCampos();
        carregaVisita();
        verificaSituacao();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater()
                .inflate(R.menu.activity_formulario_visita_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_formulario_menu_salvar) {
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void verificaSituacao() {
        spinnerSituacao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String situacao_selecionada = spinnerSituacao.getEditableText().toString();
                if (situacao_selecionada.equals("Aguardando retorno da escola para ")) {
                    campo_data_situacao_limite.setVisibility(view.VISIBLE);
                } else
                    campo_data_situacao_limite.setVisibility(view.GONE);
            }
        });
    }

    private void carregaVisita() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_VISITA)) {
            setTitle(TITULO_APPBAR_EDITA_VISITA);
            visita = (Visita) dados.getSerializableExtra(CHAVE_VISITA);
            preencheCampos();
        } else {
            setTitle(TITULO_APPBAR_NOVA_VISITA);
            visita = new Visita();
        }
    }

    private void preencheCampos() {
        campo_nome_crianca.getEditText().setText(visita.getNome_crianca());
    }

//    public void tirarFoto(View view) {
//        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//        startActivityForResult(intent, 0);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resulteCode, Intent data) {
//        super.onActivityResult(requestCode, resulteCode, data);
//        if (data != null) {
//            Bundle bundle = data.getExtras();
//            if (bundle != null) {
//                imagemFamilia = (Bitmap) bundle.get("data");
//
//                campoImagemFamilia = (ImageView) findViewById(R.id.item_foto_familia);
//                campoImagemFamilia.setImageBitmap(imagemFamilia);
//            }
//        }
//    }



    private void finalizaFormulario() {
        preencheVisita();
        visitaDAO.salva(visita);
        finish();
    }

    private void preencheVisita() {
        String nome = campo_nome_crianca.getEditText().getText().toString();
        String data_nascimento = campo_data_nascimento.getEditText().getText().toString();
        String turma = spinnerTurma.getEditableText().toString();
        String turno = spinnerTurno.getEditableText().toString();

        String nome_responsavel_1 = campo_nome_responsavel_1.getEditText().getText().toString();
        String email_responsavel_1 = campo_email_responsavel_1.getEditText().getText().toString();
        String telefone_fixo_responsavel_1 = campo_telefone_fixo_responsavel_1.getEditText().getText().toString();
        String telefone_celular_responsavel_1 = campo_telefone_celular_responsavel_1.getEditText().getText().toString();
        String cpf_responsavel_1 = campo_cpf_responsavel_1.getEditText().getText().toString();

        String unidade = spinnerUnidade.getEditableText().toString();
        String secretaria = spinnerSecretaria.getEditableText().toString();
        String tipoAtendimento = spinnerTipoAtendimento.getEditableText().toString();
        String colaborador = spinnerColaborador.getEditableText().toString();
        String comoNosConheceu = spinnerComoNosConheceu.getEditableText().toString();
        String situacao = spinnerSituacao.getEditableText().toString();

        //visita.setFoto_familia(imagemFamilia);
        visita.setNome_crianca(nome);
        visita.setData_nascimento(data_nascimento);
        visita.setTurma(turma);
        visita.setTurno(turno);

        visita.setNome_responsavel_1(nome_responsavel_1);
        visita.setEmail_responsavel_1(email_responsavel_1);
        visita.setTelefone_fixo_responsavel_1(telefone_fixo_responsavel_1);
        visita.setTelefone_celular_responsavel_1(telefone_celular_responsavel_1);
        visita.setCpf_responsavel_1(cpf_responsavel_1);

        visita.setUnidade(unidade);
        visita.setSecrearia(secretaria);
        visita.setTipoAtendimento(tipoAtendimento);
        visita.setColaborador(colaborador);
        visita.setComoNosConheceu(comoNosConheceu);
        visita.setSituacao(situacao);

        //String dataAtual = RetornaDataAtual.dataAtual();
        //visita.setDataDoCadastro(dataAtual);

    }

    private void inicializacaoDosCampos() {
        campo_nome_crianca = findViewById(R.id.nome_crianca);
        campo_data_nascimento = findViewById(R.id.data_nascimento);
        spinnerTurma = findViewById(R.id.spinner_Turma);
        spinnerTurno = findViewById(R.id.spinner_Turno);

        campo_nome_responsavel_1 = findViewById(R.id.nome_responsavel_1);
        campo_email_responsavel_1 = findViewById(R.id.formulario_email_responsavel1);
        campo_telefone_fixo_responsavel_1 = findViewById(R.id.fixo_responsavel1);
        campo_telefone_celular_responsavel_1 = findViewById(R.id.celular_responsavel1);
        campo_cpf_responsavel_1 = findViewById(R.id.cpf_responsavel1);

        spinnerUnidade = findViewById(R.id.spinner_Unidade);
        spinnerSecretaria = findViewById(R.id.spinner_Secretaria);
        spinnerTipoAtendimento = findViewById(R.id.spinner_TipoAtendimento);
        spinnerColaborador = findViewById(R.id.spinner_Colaborador);
        spinnerComoNosConheceu = findViewById(R.id.spinner_ComoNosConheceu);
        spinnerSituacao = findViewById(R.id.spinner_Situacao);

        campo_data_situacao_limite = findViewById(R.id.data_limite_situacao);

        ArrayAdapter<Unidade> arrayAdapterUnidade = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, unidadeDAO.todos());
        spinnerUnidade.setAdapter(arrayAdapterUnidade);
        ArrayAdapter<Secretaria> arrayAdapterSecretaria = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, secretariaDAO.todos());
        spinnerSecretaria.setAdapter(arrayAdapterSecretaria);
        ArrayAdapter<TipoAtendimento> arrayAdapterTipoAtendimento = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, tipoAtendimentoDAO.todos());
        spinnerTipoAtendimento.setAdapter(arrayAdapterTipoAtendimento);
        ArrayAdapter<Colaborador> arrayAdapterColaborador = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, colaboradorDAO.todos());
        spinnerColaborador.setAdapter(arrayAdapterColaborador);
        ArrayAdapter<ComoNosConheceu> arrayAdapterComoNosConheceu = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, comoNosConheceuDAO.todos());
        spinnerComoNosConheceu.setAdapter(arrayAdapterComoNosConheceu);
        ArrayAdapter<Situacao> arrayAdapterSituacao = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, situacaoDAO.todos());
        spinnerSituacao.setAdapter(arrayAdapterSituacao);
        ArrayAdapter<Turma> arrayAdapterTurma = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, turmaDAO.todos());
        spinnerTurma.setAdapter(arrayAdapterTurma);
        ArrayAdapter<Turno> arrayAdapterTurno = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, turnoDAO.todos());
        spinnerTurno.setAdapter(arrayAdapterTurno);

    }
}