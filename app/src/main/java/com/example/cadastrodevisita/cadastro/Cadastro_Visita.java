package com.example.cadastrodevisita.cadastro;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.dao.ColaboradorDAO;
import com.example.cadastrodevisita.dao.ComoNosConheceuDAO;
import com.example.cadastrodevisita.dao.SecretariaDAO;
import com.example.cadastrodevisita.dao.TipoAtendimentoDAO;
import com.example.cadastrodevisita.dao.TurmaDAO;
import com.example.cadastrodevisita.dao.TurnoDAO;
import com.example.cadastrodevisita.dao.UnidadeDAO;
import com.example.cadastrodevisita.dao.VisitaDAO;
import com.example.cadastrodevisita.model.Colaborador;
import com.example.cadastrodevisita.model.ComoNosConheceu;
import com.example.cadastrodevisita.model.Secretaria;
import com.example.cadastrodevisita.model.TipoAtendimento;
import com.example.cadastrodevisita.model.Turma;
import com.example.cadastrodevisita.model.Turno;
import com.example.cadastrodevisita.model.Unidade;
import com.example.cadastrodevisita.model.Visita;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import static com.example.cadastrodevisita.ui.activities.Constantes.CHAVE_VISITA;

public class Cadastro_Visita extends AppCompatActivity {

    public static final String TITULO_APPBAR_NOVA_VISITA = "Cadastro de visita";
    private static final String TITULO_APPBAR_EDITA_VISITA = "Edição de visita";

    private FloatingActionButton camera;
    private ImageView campoFotoFamilia;
    private Bitmap fotoDaFamilia;

    private TextInputLayout campo_nome_crianca;
    private TextInputLayout campo_dataNascimento_crianca;
    private AutoCompleteTextView spinnerTurma_crianca;
    private AutoCompleteTextView spinnerTurno_crianca;

    private Switch switchTem_irmao;
    private CardView cardview_irmao;
    private TextInputLayout campo_nome_Irmao;
    private TextInputLayout campo_dataNascimento_Irmao;
    private AutoCompleteTextView spinnerTurma_irmao;
    private AutoCompleteTextView spinnerTurno_irmao;

    private TextInputLayout campo_nome_responsavel_1;
    private TextInputLayout campo_cpf_responsavel_1;
    private TextInputLayout campo_telefone_fixo_responsavel_1;
    private TextInputLayout campo_telefone_celular_responsavel_1;
    private TextInputLayout campo_email_responsavel_1;

    private TextInputLayout campo_nome_responsavel_2;
    private TextInputLayout campo_cpf_responsavel_2;
    private TextInputLayout campo_telefone_fixo_responsavel_2;
    private TextInputLayout campo_telefone_celular_responsavel_2;
    private TextInputLayout campo_email_responsavel_2;

    private TextInputLayout campo_data_visita;
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
    private TextInputLayout campo_data_situacao_agendada;
    private TextInputLayout campo_observacao;

    private final TurmaDAO turmaDAO = new TurmaDAO();
    private final TurnoDAO turnoDAO = new TurnoDAO();
    private final VisitaDAO visitaDAO = new VisitaDAO();
    private Visita visita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_visita);
        inicializacaoDosCampos();
        habilitaCadastroDoIrmao();
        verificaSituacao();
        carregaVisita();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0);
        }

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tirarFoto(v);
            }
        });

        //ARRUMAR A ROTAÇÃO DESSA MERDA

    }

    private void habilitaCadastroDoIrmao() {
        switchTem_irmao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchTem_irmao.isChecked())
                    cardview_irmao.setVisibility(v.VISIBLE);
                else if (!switchTem_irmao.isChecked())
                    cardview_irmao.setVisibility(v.GONE);
            }
        });
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
                if (situacao_selecionada.equals("Contato da escola para ") || situacao_selecionada.equals("Ambientação para ")) {
                    campo_data_situacao_agendada.setVisibility(view.VISIBLE);
                } else
                    campo_data_situacao_agendada.setVisibility(view.GONE);
            }
        });
    }

    private void carregaVisita() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_VISITA)) {
            setTitle(TITULO_APPBAR_EDITA_VISITA);
            visita = (Visita) dados.getSerializableExtra(CHAVE_VISITA);
            preencheCamposEdicao();
        } else {
            setTitle(TITULO_APPBAR_NOVA_VISITA);
            visita = new Visita();
        }
    }

    private void preencheCamposEdicao() {

        campoFotoFamilia.setImageBitmap(visita.getFoto_familia());

        campo_nome_crianca.getEditText().setText(visita.getNome_crianca());
        campo_dataNascimento_crianca.getEditText().setText(visita.getDataNascimento_crianca());
        spinnerTurma_crianca.setText(visita.getTurma_crianca());                                    //ALTERAR
        spinnerTurno_crianca.setText(visita.getTurno_crianca());                                    //ALTERAR

        if(visita.getTemIrmao()) {
            switchTem_irmao.setChecked(true);
            cardview_irmao.setVisibility(View.VISIBLE);
            campo_nome_Irmao.getEditText().setText(visita.getNome_irmao());
            campo_dataNascimento_Irmao.getEditText().setText(visita.getDataNascimento_irmao());
            //spinnerTurma_irmao.setText(visita.getTurma_irmao());                                    //ALTERAR
            //spinnerTurno_irmao.setText(visita.getTurno_irmao());                                    //ALTERAR
        }

        campo_nome_responsavel_1.getEditText().setText(visita.getNome_responsavel_1());
        campo_email_responsavel_1.getEditText().setText(visita.getEmail_responsavel_1());
        campo_telefone_fixo_responsavel_1.getEditText().setText(visita.getTelefone_fixo_responsavel_1());
        campo_telefone_celular_responsavel_1.getEditText().setText(visita.getTelefone_celular_responsavel_1());
        campo_cpf_responsavel_1.getEditText().setText(visita.getCpf_responsavel_1());

        campo_nome_responsavel_2.getEditText().setText(visita.getNome_responsavel_2());
        campo_email_responsavel_2.getEditText().setText(visita.getEmail_responsavel_2());
        campo_telefone_fixo_responsavel_2.getEditText().setText(visita.getTelefone_fixo_responsavel_2());
        campo_telefone_celular_responsavel_2.getEditText().setText(visita.getTelefone_celular_responsavel_2());
        campo_cpf_responsavel_2.getEditText().setText(visita.getCpf_responsavel_2());

        campo_data_visita.getEditText().setText(visita.getDataCadastro());
        spinnerUnidade.setText(visita.getUnidade());                                                //ALTERAR
        spinnerSecretaria.setText(visita.getSecrearia());                                           //ALTERAR
        spinnerTipoAtendimento.setText(visita.getTipoAtendimento());                                //ALTERAR
        spinnerColaborador.setText(visita.getColaborador());                                        //ALTERAR
        spinnerComoNosConheceu.setText(visita.getComoNosConheceu());                                //ALTERAR

        if (visita.getSituacao().equals("Contato da escola para ") || visita.getSituacao().equals("Ambientação para ")) {
            spinnerSituacao.setText(visita.getSituacao());                                          //ALTERAR
            campo_data_situacao_agendada.setVisibility(View.VISIBLE);
            campo_data_situacao_agendada.getEditText().setText(visita.getDataAgendada());
        }

        campo_observacao.getEditText().setText(visita.getObservacao());
    }

    public void tirarFoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resulteCode, Intent data) {
        super.onActivityResult(requestCode, resulteCode, data);
        if (data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                fotoDaFamilia = (Bitmap) bundle.get("data");
                campoFotoFamilia.setImageBitmap(fotoDaFamilia);
            }
        }
    }


    private void finalizaFormulario() {
        preencheVisitaFinalizada();
        if(visita.temIdValido()){
            VisitaDAO.edita(visita);
            mensagemSucesso();
        }else {
            visitaDAO.salva(visita);
            mensagemSucesso();
        }
        finish();
    }

    private void mensagemSucesso() {
        if(visita.temIdValido()) {
            Toast toast = Toast.makeText(this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 50);
            toast.show();
        } else {
            Toast toast = Toast.makeText(this, "Alteração realizada com sucesso", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 50);
            toast.show();
        }
    }

    private void preencheVisitaFinalizada() {

        String nome = campo_nome_crianca.getEditText().getText().toString();
        String data_nascimento = campo_dataNascimento_crianca.getEditText().getText().toString();
        String turma = spinnerTurma_crianca.getEditableText().toString();
        String turno = spinnerTurno_crianca.getEditableText().toString();

        if (switchTem_irmao.isChecked()) {
            visita.setTemIrmao(true);
            String nome_irmao = campo_nome_Irmao.getEditText().getText().toString();
            String data_nascimento_irmao = campo_dataNascimento_Irmao.getEditText().getText().toString();
            //String turma_irmao = spinnerTurma_irmao.getEditableText().toString();
            //String turno_irmao = spinnerTurno_irmao.getEditableText().toString();

            visita.setNome_irmao(nome_irmao);
            visita.setDataNascimento_irmao(data_nascimento_irmao);
            //visita.setTurma_crianca(turma_irmao);
            //visita.setTurno_crianca(turno_irmao);
        }

        String nome_responsavel_1 = campo_nome_responsavel_1.getEditText().getText().toString();
        String email_responsavel_1 = campo_email_responsavel_1.getEditText().getText().toString();
        String telefone_fixo_responsavel_1 = campo_telefone_fixo_responsavel_1.getEditText().getText().toString();
        String telefone_celular_responsavel_1 = campo_telefone_celular_responsavel_1.getEditText().getText().toString();
        String cpf_responsavel_1 = campo_cpf_responsavel_1.getEditText().getText().toString();

        String nome_responsavel_2 = campo_nome_responsavel_2.getEditText().getText().toString();
        String email_responsavel_2 = campo_email_responsavel_2.getEditText().getText().toString();
        String telefone_fixo_responsavel_2 = campo_telefone_fixo_responsavel_2.getEditText().getText().toString();
        String telefone_celular_responsavel_2 = campo_telefone_celular_responsavel_2.getEditText().getText().toString();
        String cpf_responsavel_2 = campo_cpf_responsavel_2.getEditText().getText().toString();

        String dataCadastro = campo_data_visita.getEditText().toString();
        String unidade = spinnerUnidade.getEditableText().toString();
        String secretaria = spinnerSecretaria.getEditableText().toString();
        String tipoAtendimento = spinnerTipoAtendimento.getEditableText().toString();
        String colaborador = spinnerColaborador.getEditableText().toString();
        String comoNosConheceu = spinnerComoNosConheceu.getEditableText().toString();
        String situacao = spinnerSituacao.getEditableText().toString();
        String dataAgendada = campo_data_situacao_agendada.getEditText().getText().toString();
        String observacao = campo_observacao.getEditText().toString();

        visita.setFoto_familia(fotoDaFamilia);

        visita.setNome_crianca(nome);
        visita.setDataNascimento_crianca(data_nascimento);
        visita.setTurma_crianca(turma);
        visita.setTurno_crianca(turno);

        visita.setNome_responsavel_1(nome_responsavel_1);
        visita.setEmail_responsavel_1(email_responsavel_1);
        visita.setTelefone_fixo_responsavel_1(telefone_fixo_responsavel_1);
        visita.setTelefone_celular_responsavel_1(telefone_celular_responsavel_1);
        visita.setCpf_responsavel_1(cpf_responsavel_1);

        visita.setNome_responsavel_2(nome_responsavel_2);
        visita.setEmail_responsavel_2(email_responsavel_2);
        visita.setTelefone_fixo_responsavel_2(telefone_fixo_responsavel_2);
        visita.setTelefone_celular_responsavel_2(telefone_celular_responsavel_2);
        visita.setCpf_responsavel_2(cpf_responsavel_2);

        visita.setDataCadastro(dataCadastro);
        visita.setUnidade(unidade);
        visita.setSecrearia(secretaria);
        visita.setTipoAtendimento(tipoAtendimento);
        visita.setColaborador(colaborador);
        visita.setComoNosConheceu(comoNosConheceu);
        visita.setSituacao(situacao);
        visita.setDataAgendada(dataAgendada);
        visita.setObservacao(observacao);

        //String dataAtual = RetornaDataAtual.dataAtual();
        //visita.setDataDoCadastro(dataAtual);

    }

    private void inicializacaoDosCampos() {
        campoFotoFamilia = findViewById(R.id.formulario_foto_familia);
        camera = findViewById(R.id.formulario_botao_tirar_foto);

        campo_nome_crianca = findViewById(R.id.formulario_nome_crianca);
        campo_dataNascimento_crianca = findViewById(R.id.formulario_data_nascimento_crianca);
        spinnerTurma_crianca = findViewById(R.id.spinner_Turma_crianca);
        spinnerTurno_crianca = findViewById(R.id.spinner_Turno_crianca);

        ArrayAdapter<Turma> arrayAdapterTurma = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, turmaDAO.todos());
        spinnerTurma_crianca.setAdapter(arrayAdapterTurma);
        ArrayAdapter<Turno> arrayAdapterTurno = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, turnoDAO.todos());
        spinnerTurno_crianca.setAdapter(arrayAdapterTurno);

        switchTem_irmao = findViewById(R.id.switch_tem_irmao);
        cardview_irmao = findViewById(R.id.cardview_irmao);
        campo_nome_Irmao = findViewById(R.id.formulario_nome_irmao);
        campo_dataNascimento_Irmao = findViewById(R.id.formulario_data_nascimento_irmao);
        spinnerTurma_irmao.setAdapter(arrayAdapterTurma);
        spinnerTurno_irmao.setAdapter(arrayAdapterTurno);

        campo_nome_responsavel_1 = findViewById(R.id.formulario_nome_responsavel_1);
        campo_email_responsavel_1 = findViewById(R.id.formulario_email_responsavel1);
        campo_telefone_fixo_responsavel_1 = findViewById(R.id.formulario_fixo_responsavel1);
        campo_telefone_celular_responsavel_1 = findViewById(R.id.formulario_celular_responsavel1);
        campo_cpf_responsavel_1 = findViewById(R.id.formulario_cpf_responsavel1);

        campo_nome_responsavel_2 = findViewById(R.id.formulario_nome_responsavel_2);
        campo_email_responsavel_2 = findViewById(R.id.formulario_email_responsavel_2);
        campo_telefone_fixo_responsavel_2 = findViewById(R.id.formulario_fixo_responsavel_2);
        campo_telefone_celular_responsavel_2 = findViewById(R.id.formulario_celular_responsavel_2);
        campo_cpf_responsavel_2 = findViewById(R.id.formulario_cpf_responsavel_2);

        campo_data_visita = findViewById(R.id.formulario_data_visita);
        spinnerUnidade = findViewById(R.id.spinner_Unidade);
        spinnerSecretaria = findViewById(R.id.spinner_Secretaria);
        spinnerTipoAtendimento = findViewById(R.id.spinner_TipoAtendimento);
        spinnerColaborador = findViewById(R.id.spinner_Colaborador);
        spinnerComoNosConheceu = findViewById(R.id.spinner_ComoNosConheceu);
        spinnerSituacao = findViewById(R.id.spinner_Situacao);
        campo_data_situacao_agendada = findViewById(R.id.formulario_data_agendada_situacao);
        campo_observacao = (TextInputLayout) findViewById(R.id.formulario_observacao);

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
        ArrayAdapter<String> arrayAdapterSituacao = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item,
                getResources().getStringArray(R.array.array_tipos_situacao));
        spinnerSituacao.setAdapter(arrayAdapterSituacao);


    }
}