package com.example.cadastrodevisita.cadastro;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import com.example.cadastrodevisita.ListaVisitasView;
import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.adapter.ListaVisitasAdapter;
import com.example.cadastrodevisita.dao.ColaboradorDAO;
import com.example.cadastrodevisita.dao.ComoNosConheceuDAO;
import com.example.cadastrodevisita.dao.SecretariaDAO;
import com.example.cadastrodevisita.dao.TipoAtendimentoDAO;
import com.example.cadastrodevisita.dao.TurmaDAO;
import com.example.cadastrodevisita.dao.TurnoDAO;
import com.example.cadastrodevisita.dao.UnidadeDAO;
import com.example.cadastrodevisita.dao.VisitaDAO;
import com.example.cadastrodevisita.formatter.FormataTelefoneComDdd;
import com.example.cadastrodevisita.model.Colaborador;
import com.example.cadastrodevisita.model.ComoNosConheceu;
import com.example.cadastrodevisita.model.Secretaria;
import com.example.cadastrodevisita.model.TipoAtendimento;
import com.example.cadastrodevisita.model.Turma;
import com.example.cadastrodevisita.model.Turno;
import com.example.cadastrodevisita.model.Unidade;
import com.example.cadastrodevisita.model.Visita;
import com.example.cadastrodevisita.validator.ValidaCampoSituacao;
import com.example.cadastrodevisita.validator.ValidaCpf;
import com.example.cadastrodevisita.validator.ValidaEmail;
import com.example.cadastrodevisita.validator.ValidaTelefoneComDdd;
import com.example.cadastrodevisita.validator.ValidacaCampoObrigatorio;
import com.example.cadastrodevisita.validator.Validador;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.stella.format.CPFFormatter;

import static com.example.cadastrodevisita.ui.activities.Constantes.CHAVE_VISITA;
import static com.example.cadastrodevisita.ui.activities.Constantes.SITUACAO_AMBIENTACAO_PARA;
import static com.example.cadastrodevisita.ui.activities.Constantes.SITUACAO_CONTATO_ESCOLA_PARA;
import static com.example.cadastrodevisita.ui.activities.Constantes.SITUACAO_MATRICULOU_OUTRA_ESCOLA;

public class Cadastro_Visita extends AppCompatActivity {

    public static final String TITULO_APPBAR_NOVA_VISITA = "Cadastro de visita";
    private static final String TITULO_APPBAR_EDITA_VISITA = "Edição de visita";
    private static final String ERRO_FORMATACAO_CPF = "Erro formatação CPF";
    private final List<Validador> validadores = new ArrayList<>();
    private final ListaVisitasView listaDeVisitas = new ListaVisitasView(this);

    private FloatingActionButton camera;
    private FloatingActionButton galeriaDeFotos;
    private ImageView campoFotoFamilia;
    private Bitmap fotoDaFamilia;
    private String imgDecodableString;
    Uri selectedImage;

    private TextInputLayout campo_nome_crianca;
    private TextInputLayout campo_dataNascimento_crianca;
    private AutoCompleteTextView spinnerTurma_crianca;
    private AutoCompleteTextView spinnerTurno_crianca;

    private Switch switchTem_irmao;
    private CardView cardview_irmao;
    private TextInputLayout campo_nome_Irmao;
    private TextInputLayout campo_dataNascimento_Irmao;
    private AutoCompleteTextView spinnerTurma_irmao;
    private TextInputLayout formulario_turma_crianca;
    private AutoCompleteTextView spinnerTurno_irmao;
    private TextInputLayout formulario_turno_crianca;

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
    private TextInputLayout formulario_unidade;
    private final UnidadeDAO unidadeDAO = new UnidadeDAO();
    private AutoCompleteTextView spinnerSecretaria;
    private TextInputLayout formulario_secretaria;
    private final SecretariaDAO secretariaDAO = new SecretariaDAO();
    private AutoCompleteTextView spinnerTipoAtendimento;
    private TextInputLayout formulario_tipoAtendimento;
    private final TipoAtendimentoDAO tipoAtendimentoDAO = new TipoAtendimentoDAO();
    private AutoCompleteTextView spinnerColaborador;
    private TextInputLayout formulario_colaborador;
    private final ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
    private AutoCompleteTextView spinnerComoNosConheceu;
    private TextInputLayout formulario_comoNosConheceu;
    private final ComoNosConheceuDAO comoNosConheceuDAO = new ComoNosConheceuDAO();
    private AutoCompleteTextView spinnerSituacao;
    private TextInputLayout formulario_situacao;
    private TextInputLayout campo_data_situacao_agendada;
    private TextInputLayout campo_motivo_outraEscola;
    private TextInputLayout campo_nome_outraEscola;
    private TextInputLayout campo_observacao;

    private final TurmaDAO turmaDAO = new TurmaDAO();
    private final TurnoDAO turnoDAO = new TurnoDAO();
    private final VisitaDAO visitaDAO = new VisitaDAO();
    private Visita visita;
    private int RESULT_TIRAR_FOTO = 0;
    private int RESULT_PEGA_FOTO_GALERIA = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_visita);
        inicializacaoDosCampos();
        habilitaCadastroDoIrmao();
        verificaSituacao();
        carregaVisita();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, RESULT_TIRAR_FOTO);
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, RESULT_PEGA_FOTO_GALERIA);
        }

        camera.setOnClickListener(v -> tirarFoto(v));
        galeriaDeFotos.setOnClickListener(v -> pegaFotoGaleria());

    }

    public void tirarFoto(View view) {
        Intent intentTirarFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intentTirarFoto, RESULT_TIRAR_FOTO);
    }

    private void pegaFotoGaleria() {
        Intent intentPegaFoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intentPegaFoto, RESULT_PEGA_FOTO_GALERIA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resulteCode, Intent data) {
        super.onActivityResult(requestCode, resulteCode, data);
        if (requestCode == RESULT_TIRAR_FOTO) {
            if (data != null) {
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    fotoDaFamilia = (Bitmap) bundle.get("data");
                    campoFotoFamilia.setImageBitmap(fotoDaFamilia);
                    this.selectedImage = data.getData();
                }
            }
        } else if (requestCode == RESULT_PEGA_FOTO_GALERIA && resulteCode == RESULT_OK) {
            carregarImagemGaleria(data);
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imgDecodableString = selectedImage.toString(); // o caminho deve estar aqui
            campoFotoFamilia.setImageBitmap(bitmap);

//            String[] filePathColumn = { MediaStore.Images.Media.DATA };
//
//            Cursor cursor = getContentResolver().query(selectedImage,
//                    filePathColumn, null, null, null);
//            cursor.moveToFirst();
//
//            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//            imgDecodableString = cursor.getString(columnIndex);
//            cursor.close(); // aqui voce tem o caminho -> essa string voce precisa armazenar na sua aplicação para que consiga trabalhar com ela sem problemas maiores
//
//            campoFotoFamilia.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));

        }
    }

    public void carregarImagemGaleria(Intent data) {
        InputStream stream = null;
        try {
            if (fotoDaFamilia != null) {
                fotoDaFamilia.recycle();
            }
            stream = getContentResolver().openInputStream(data.getData());
            fotoDaFamilia = BitmapFactory.decodeStream(stream);
            this.selectedImage = data.getData();
            campoFotoFamilia.setImageBitmap(fotoDaFamilia);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void habilitaCadastroDoIrmao() {
        switchTem_irmao.setOnClickListener(v -> {
            if (switchTem_irmao.isChecked())
                cardview_irmao.setVisibility(v.VISIBLE);
            else if (!switchTem_irmao.isChecked())
                cardview_irmao.setVisibility(v.GONE);
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
            boolean formularioEstaValido = validaTodosCampos();
            if (formularioEstaValido) {
                finalizaFormulario();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void verificaSituacao() {
        spinnerSituacao.setOnItemClickListener((parent, view, position, id) -> {
            String situacao_selecionada = spinnerSituacao.getEditableText().toString();

            if (situacao_selecionada.equals(SITUACAO_CONTATO_ESCOLA_PARA) || situacao_selecionada.equals(SITUACAO_AMBIENTACAO_PARA)) {
                campo_data_situacao_agendada.setVisibility(view.VISIBLE);
                campo_nome_outraEscola.setVisibility(View.GONE);
                campo_motivo_outraEscola.setVisibility(View.GONE);
                configuraCampoObrigatorioSituacao(campo_data_situacao_agendada, true);

            }
            else if (situacao_selecionada.equals(SITUACAO_MATRICULOU_OUTRA_ESCOLA)) {
                campo_data_situacao_agendada.setVisibility(View.GONE);
                campo_nome_outraEscola.setVisibility(View.VISIBLE);
                campo_motivo_outraEscola.setVisibility(View.VISIBLE);
                configuraCampoObrigatorioSituacao(campo_data_situacao_agendada, false);
            }
            else {
                campo_data_situacao_agendada.setVisibility(View.GONE);
                campo_nome_outraEscola.setVisibility(View.GONE);
                campo_motivo_outraEscola.setVisibility(View.GONE);
                configuraCampoObrigatorioSituacao(campo_data_situacao_agendada, false);
            }
        });
    }

    private void configuraCampoObrigatorioSituacao(TextInputLayout situacao, boolean temData) {
        EditText campoDataAgendada = situacao.getEditText();
        final ValidaCampoSituacao validador = new ValidaCampoSituacao(situacao, temData);
        validadores.add(validador);
        campoDataAgendada.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validador.estaValido();
                }
            }
        });
    }

    private void carregaVisita() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_VISITA)) {
            setTitle(TITULO_APPBAR_EDITA_VISITA);
            visita = dados.getParcelableExtra(CHAVE_VISITA);
            preencheCamposEdicao();
        } else {
            setTitle(TITULO_APPBAR_NOVA_VISITA);
            visita = new Visita();
        }
    }

    private void preencheCamposEdicao() {

        campoFotoFamilia.setImageBitmap(visita.getFoto_familia());

        //campoFotoFamilia.setImageBitmap(visita.getFoto_familia());

        campo_nome_crianca.getEditText().setText(visita.getNome_crianca());
        campo_dataNascimento_crianca.getEditText().setText(visita.getDataNascimento_crianca());
        spinnerTurma_crianca.setText(visita.getTurma_crianca(), false);
        spinnerTurno_crianca.setText(visita.getTurno_crianca(), false);

        //int posicao = listaDeVisitas.indexOf(visita.getTurma_crianca());
        //final List<Visita> visitasFiltradas = listaDeVisitas.listaDoAdapterFiltrado();

//        final List<Visita> visitasFiltradas = listaDeVisitas.listaDoAdapterFiltrado();
//        final ListaVisitasAdapter listaAdapter = new ListaVisitasAdapter(visitasFiltradas, this);
//
//        int posicao = visitasFiltradas.indexOf(visita.getTurma_crianca());
//
//        spinnerTurma_crianca.setListSelection(posicao);

        if (visita.getTemIrmao()) {
            switchTem_irmao.setChecked(true);
            cardview_irmao.setVisibility(View.VISIBLE);
            campo_nome_Irmao.getEditText().setText(visita.getNome_irmao());
            campo_dataNascimento_Irmao.getEditText().setText(visita.getDataNascimento_irmao());
            spinnerTurma_irmao.setText(visita.getTurma_irmao(), false);                                    //ALTERAR
            spinnerTurno_irmao.setText(visita.getTurno_irmao(), false);                                    //ALTERAR
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

        campo_data_visita.getEditText().setText((CharSequence) visita.getDataCadastro());
        spinnerUnidade.setText(visita.getUnidade(), false);
        spinnerSecretaria.setText(visita.getSecrearia(), false);
        spinnerTipoAtendimento.setText(visita.getTipoAtendimento(), false);
        spinnerColaborador.setText(visita.getColaborador(), false);
        spinnerComoNosConheceu.setText(visita.getComoNosConheceu(), false);

        if (visita.getSituacao().equals(SITUACAO_CONTATO_ESCOLA_PARA) || visita.getSituacao().equals(SITUACAO_AMBIENTACAO_PARA)) {
            spinnerSituacao.setText(visita.getSituacao(), false);
            campo_data_situacao_agendada.setVisibility(View.VISIBLE);
            campo_data_situacao_agendada.getEditText().setText(visita.getDataAgendada());
        }

        campo_observacao.getEditText().setText(visita.getObservacao());
    }

    private void finalizaFormulario() {
        preencheVisitaFinalizada();
        if (visita.temIdValido()) {
            VisitaDAO.edita(visita);
            Toast toast = Toast.makeText(this, "Alteração realizada com sucesso!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 50);
            toast.show();
        } else {
            visitaDAO.salva(visita);
            Toast toast = Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 50);
            toast.show();
        }
        finish();
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

        //Date dataCadastro = (Date) campo_data_visita.getEditText().getText();
        String dataCadastro = campo_data_visita.getEditText().getText().toString();
        String unidade = spinnerUnidade.getEditableText().toString();
        String secretaria = spinnerSecretaria.getEditableText().toString();
        String tipoAtendimento = spinnerTipoAtendimento.getEditableText().toString();
        String colaborador = spinnerColaborador.getEditableText().toString();
        String comoNosConheceu = spinnerComoNosConheceu.getEditableText().toString();
        String situacao = spinnerSituacao.getEditableText().toString();
        String dataAgendada = campo_data_situacao_agendada.getEditText().getText().toString();
        String motivo = campo_motivo_outraEscola.getEditText().getText().toString();
        String nome_outraEscola = campo_nome_outraEscola.getEditText().getText().toString();
        String observacao = campo_observacao.getEditText().getText().toString();

        if (fotoDaFamilia != null)
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
        visita.setMotivo_outraEscola(motivo);
        visita.setNome_outraEscola(nome_outraEscola);
        visita.setObservacao(observacao);

//        Date dataAtual = (Date) Calendar.getInstance().getTime();
//        visita.setD
    }

    private void inicializacaoDosCampos() {
        campoFotoFamilia = findViewById(R.id.formulario_foto_familia);
        camera = findViewById(R.id.formulario_botao_tirar_foto);
        galeriaDeFotos = findViewById(R.id.formulario_botao_galeria);

        campo_nome_crianca = findViewById(R.id.formulario_nome_crianca);
        configuraCampoObrigatorio(campo_nome_crianca);
        campo_dataNascimento_crianca = findViewById(R.id.formulario_data_nascimento_crianca);
        pegaData(campo_dataNascimento_crianca);
        configuraCampoObrigatorio(campo_dataNascimento_crianca);
        spinnerTurma_crianca = findViewById(R.id.spinner_Turma_crianca);
        formulario_turma_crianca = findViewById(R.id.formulario_turma_crianca);
        configuraCampoObrigatorio(formulario_turma_crianca);
        spinnerTurno_crianca = findViewById(R.id.spinner_Turno_crianca);
        formulario_turno_crianca = findViewById(R.id.formulario_turno_crianca);
        configuraCampoObrigatorio(formulario_turno_crianca);

        ArrayAdapter<Turma> arrayAdapterTurma = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, turmaDAO.todos());
        spinnerTurma_crianca.setAdapter(arrayAdapterTurma);
        ArrayAdapter<Turno> arrayAdapterTurno = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, turnoDAO.todos());
        spinnerTurno_crianca.setAdapter(arrayAdapterTurno);

//        ArrayAdapter<Turma> arrayAdapterTurmaIrmao = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, turmaDAO.todos());
//        spinnerTurma_irmao.setAdapter(arrayAdapterTurmaIrmao);
//        ArrayAdapter<Turno> arrayAdapterTurnoIrmao = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, turnoDAO.todos());
//        spinnerTurno_irmao.setAdapter(arrayAdapterTurnoIrmao);

        switchTem_irmao = findViewById(R.id.switch_tem_irmao);
        cardview_irmao = findViewById(R.id.cardview_irmao);
        campo_nome_Irmao = findViewById(R.id.formulario_nome_irmao);
        campo_dataNascimento_Irmao = findViewById(R.id.formulario_data_nascimento_irmao);
        pegaData(campo_dataNascimento_Irmao);
        //spinnerTurma_irmao.setAdapter(arrayAdapterTurma);
        //spinnerTurno_irmao.setAdapter(arrayAdapterTurno);

        campo_nome_responsavel_1 = findViewById(R.id.formulario_nome_responsavel_1);
        configuraCampoObrigatorio(campo_nome_responsavel_1);
        campo_email_responsavel_1 = findViewById(R.id.formulario_email_responsavel1);
        configuraCampoEmail(campo_email_responsavel_1);
        campo_telefone_fixo_responsavel_1 = findViewById(R.id.formulario_fixo_responsavel1);
        configuraCampoTelefone(campo_telefone_fixo_responsavel_1);
        campo_telefone_celular_responsavel_1 = findViewById(R.id.formulario_celular_responsavel1);
        configuraCampoTelefone(campo_telefone_celular_responsavel_1);
        campo_cpf_responsavel_1 = findViewById(R.id.formulario_cpf_responsavel1);
        configuraCampoCPF(campo_cpf_responsavel_1);


        campo_nome_responsavel_2 = findViewById(R.id.formulario_nome_responsavel_2);
        campo_email_responsavel_2 = findViewById(R.id.formulario_email_responsavel_2);
        configuraCampoEmail(campo_email_responsavel_2);
        campo_telefone_fixo_responsavel_2 = findViewById(R.id.formulario_fixo_responsavel_2);
        configuraCampoTelefone(campo_telefone_fixo_responsavel_2);
        campo_telefone_celular_responsavel_2 = findViewById(R.id.formulario_celular_responsavel_2);
        configuraCampoTelefone(campo_telefone_celular_responsavel_2);
        campo_cpf_responsavel_2 = findViewById(R.id.formulario_cpf_responsavel_2);
        configuraCampoCPF(campo_cpf_responsavel_2);

        campo_data_visita = findViewById(R.id.formulario_data_visita);
        configuraCampoObrigatorio(campo_data_visita);
        pegaData(campo_data_visita);
        spinnerUnidade = findViewById(R.id.spinner_Unidade);
        formulario_unidade = findViewById(R.id.formulario_unidade);
        configuraCampoObrigatorio(formulario_unidade);
        spinnerSecretaria = findViewById(R.id.spinner_Secretaria);
        formulario_secretaria = findViewById(R.id.formulario_secretaria);
        configuraCampoObrigatorio(formulario_secretaria);
        spinnerTipoAtendimento = findViewById(R.id.spinner_TipoAtendimento);
        formulario_tipoAtendimento = findViewById(R.id.formulario_tipoAtendimento);
        configuraCampoObrigatorio(formulario_tipoAtendimento);
        spinnerColaborador = findViewById(R.id.spinner_Colaborador);
        formulario_colaborador = findViewById(R.id.formulario_colaborador);
        configuraCampoObrigatorio(formulario_colaborador);
        spinnerComoNosConheceu = findViewById(R.id.spinner_ComoNosConheceu);
        formulario_comoNosConheceu = findViewById(R.id.formulario_comoNosConheceu);
        configuraCampoObrigatorio(formulario_comoNosConheceu);
        spinnerSituacao = findViewById(R.id.spinner_Situacao);
        formulario_situacao = findViewById(R.id.formulario_situacao);
        configuraCampoObrigatorio(formulario_situacao);
        campo_data_situacao_agendada = findViewById(R.id.formulario_data_agendada_situacao);
        pegaData(campo_data_situacao_agendada);
        campo_motivo_outraEscola = findViewById(R.id.formulario_Motivo_Matriculou_OutraEscola);
        campo_nome_outraEscola = findViewById(R.id.formulario_Matriculou_Nome_OutraEscola);
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

    private void pegaData(TextInputLayout campo_data) {
        EditText campo = (EditText) campo_data.getEditText();
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        campo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Cadastro_Visita.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String data = day + "/" + month + "/" + year;
                        campo_data.getEditText().setText(data);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    private void configuraCampoObrigatorio(TextInputLayout campo) {
        EditText campoNome = campo.getEditText();
        final ValidacaCampoObrigatorio validador = new ValidacaCampoObrigatorio(campo);
        validadores.add(validador);
        campoNome.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validador.estaValido();
                }
            }
        });
    }

    private void configuraCampoCPF(TextInputLayout cpf) {
        final EditText campoCPF = cpf.getEditText();
        final CPFFormatter formatador = new CPFFormatter();
        final ValidaCpf validador = new ValidaCpf(cpf);
        validadores.add(validador);
        campoCPF.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    removeFormatacao(formatador, campoCPF);
                } else {
                    validador.estaValido();
                }
            }
        });
    }

    private void configuraCampoEmail(TextInputLayout email) {
        EditText campoEmail = email.getEditText();
        final ValidaEmail validador = new ValidaEmail(email);
        validadores.add(validador);
        campoEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validador.estaValido();
                }
            }
        });
    }

    private void configuraCampoTelefone(TextInputLayout telefone) {
        final EditText campoTelefoneComDdd = telefone.getEditText();
        final ValidaTelefoneComDdd validador = new ValidaTelefoneComDdd(telefone);
        final FormataTelefoneComDdd formatador = new FormataTelefoneComDdd();
        validadores.add(validador);
        campoTelefoneComDdd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String telefoneComDdd = campoTelefoneComDdd.getText().toString();
                if (hasFocus) {
                    String telefoneComDddSemFormatacao = formatador.removeFormatacao(telefoneComDdd);
                    campoTelefoneComDdd.setText(telefoneComDddSemFormatacao);
                } else
                    validador.estaValido();
            }
        });
    }

    private void removeFormatacao(CPFFormatter formatador, EditText campoCpf) {
        String cpf = campoCpf.getText().toString();
        try {
            String cpfSemFormato = formatador.unformat(cpf);
            campoCpf.setText(cpfSemFormato);
        } catch (IllegalArgumentException e) {
            Log.e(ERRO_FORMATACAO_CPF, e.getMessage());
        }
    }

    private boolean validaTodosCampos() {
        boolean formularioEstaValido = true;
        for (Validador validador :
                validadores) {
            if (!validador.estaValido())
                formularioEstaValido = false;
        }
        return formularioEstaValido;
    }

}