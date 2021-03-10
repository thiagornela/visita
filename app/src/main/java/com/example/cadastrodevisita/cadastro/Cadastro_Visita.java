package com.example.cadastrodevisita.cadastro;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.dao.CategoriaDAO;
import com.example.cadastrodevisita.dao.ColaboradorDAO;
import com.example.cadastrodevisita.dao.ComoNosConheceuDAO;
import com.example.cadastrodevisita.dao.SecretariaDAO;
import com.example.cadastrodevisita.dao.TipoAtendimentoDAO;
import com.example.cadastrodevisita.dao.TurmaDAO;
import com.example.cadastrodevisita.dao.TurnoDAO;
import com.example.cadastrodevisita.dao.UnidadeDAO;
import com.example.cadastrodevisita.dao.VisitaDAO;
import com.example.cadastrodevisita.email.Mail;
import com.example.cadastrodevisita.formatter.ImageResizer;
import com.example.cadastrodevisita.model.Categoria;
import com.example.cadastrodevisita.model.Colaborador;
import com.example.cadastrodevisita.model.ComoNosConheceu;
import com.example.cadastrodevisita.model.Secretaria;
import com.example.cadastrodevisita.model.TipoAtendimento;
import com.example.cadastrodevisita.model.Turma;
import com.example.cadastrodevisita.model.Turno;
import com.example.cadastrodevisita.model.Unidade;
import com.example.cadastrodevisita.model.Visita;
import com.example.cadastrodevisita.validator.ConfiguraCampos;
import com.example.cadastrodevisita.validator.Validador;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Objects;

import static com.example.cadastrodevisita.ui.Constantes.ALTERACAO_SUCESSO;
import static com.example.cadastrodevisita.ui.Constantes.CADASTRO_SUCESSO;
import static com.example.cadastrodevisita.ui.Constantes.CHAVE_VISITA;
import static com.example.cadastrodevisita.ui.Constantes.SITUACAO_AMBIENTACAO_PARA;
import static com.example.cadastrodevisita.ui.Constantes.SITUACAO_CONTATO_ESCOLA_PARA;
import static com.example.cadastrodevisita.ui.Constantes.SITUACAO_MATRICULOU_OUTRA_ESCOLA;
import static com.example.cadastrodevisita.ui.Constantes.TITULO_APPBAR_EDITA_VISITA;
import static com.example.cadastrodevisita.ui.Constantes.TITULO_APPBAR_NOVA_VISITA;

public class Cadastro_Visita extends AppCompatActivity {
    private FloatingActionButton camera;
    private FloatingActionButton galeriaDeFotos;
    private ImageView campoFotoFamilia;
    private Bitmap fotoDaFamilia;
    Uri selectedImage;

    private TextInputLayout campo_nome_crianca;
    private TextInputLayout campo_dataNascimento_crianca;
    private AutoCompleteTextView spinnerTurma_crianca;
    private AutoCompleteTextView spinnerTurno_crianca;
    private TextInputLayout formulario_turma_crianca;
    private TextInputLayout formulario_turno_crianca;

    private Switch switchTem_irmao;
    private CardView cardview_irmao;
    private TextInputLayout campo_nome_Irmao;
    private TextInputLayout campo_dataNascimento_Irmao;
    private AutoCompleteTextView spinnerTurma_irmao;
    private AutoCompleteTextView spinnerTurno_irmao;
    private TextInputLayout formulario_turma_irmao;
    private TextInputLayout formulario_turno_irmao;

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
    private TextInputLayout formulario_categoria;
    private AutoCompleteTextView spinnerCategoria;
    private TextInputLayout campo_motivo_outraEscola;
    private TextInputLayout campo_nome_outraEscola;
    private TextInputLayout campo_observacao;

    private final TurmaDAO turmaDAO = new TurmaDAO();
    private final TurnoDAO turnoDAO = new TurnoDAO();
    private final VisitaDAO visitaDAO = new VisitaDAO();
    private final CategoriaDAO categoriaDAO = new CategoriaDAO();
    private Visita visita;
    private int RESULT_TIRAR_FOTO = 0;
    private int RESULT_PEGA_FOTO_GALERIA = 123;

    private ConfiguraCampos configuracampos = new ConfiguraCampos();
    private String email_responsavel_1;
    private String email_responsavel_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_visita);

        inicializacaoDosCampos();
        habilitaCadastroDoIrmao();
        verificaSituacao();
        carregaVisita();
        solicitaPermissao();

        camera.setOnClickListener(this::tirarFoto);
        galeriaDeFotos.setOnClickListener(v -> pegaFotoGaleria());
    }

    private void solicitaPermissao() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, RESULT_TIRAR_FOTO);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, RESULT_PEGA_FOTO_GALERIA);
        }
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
            campoFotoFamilia.setImageBitmap(bitmap);
        }
    }

//    public void carregarImagemGaleria(Intent data) {
//        InputStream stream = null;
//        try {
//            if (fotoDaFamilia != null) {
//                fotoDaFamilia.recycle();
//            }
//            stream = getContentResolver().openInputStream(data.getData());
//            Bitmap fotoDaFamilia1 = BitmapFactory.decodeStream(stream);
//            this.selectedImage = data.getData();
//            fotoDaFamilia = Bitmap.createScaledBitmap(fotoDaFamilia1, 60, 80, false);             //foto menor qualidade
//            campoFotoFamilia.setImageBitmap(fotoDaFamilia);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            if (stream != null) {
//                try {
//                    stream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }


//funciona normal
//    public void carregarImagemGaleria(Intent data) {
//        InputStream stream = null;
//        try {
//            if (fotoDaFamilia != null) {
//                fotoDaFamilia.recycle();
//            }
//            stream = getContentResolver().openInputStream(data.getData());
//            fotoDaFamilia = BitmapFactory.decodeStream(stream);
//            campoFotoFamilia.setImageBitmap(fotoDaFamilia);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            if (stream != null) {
//                try {
//                    stream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

    public void carregarImagemGaleria(Intent data) {
        InputStream stream = null;
        try {
            if (fotoDaFamilia != null) {
                fotoDaFamilia.recycle();
            }
            stream = getContentResolver().openInputStream(data.getData());
            Bitmap fotoDaFamilia1 = BitmapFactory.decodeStream(stream);

            fotoDaFamilia = ImageResizer.reduceBitmapSize(fotoDaFamilia1, 110592);

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
            if (switchTem_irmao.isChecked()) {
                cardview_irmao.setVisibility(View.VISIBLE);
                configuracampos.configuraCampoObrigatorioIrmao(campo_nome_Irmao, campo_dataNascimento_Irmao, formulario_turma_irmao, formulario_turno_irmao);
            } else if (!switchTem_irmao.isChecked()) {
                cardview_irmao.setVisibility(View.GONE);
                configuracampos.removeValidadorNomeIrmao();
                configuracampos.removeValidadorDtNascIrmao();
                configuracampos.removeValidadorTurmaIrmao();
                configuracampos.removeValidadorTurnoIrmao();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_visita_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_formulario_menu_salvar) {
            boolean formularioEstaValido = validaTodosCampos();
            if (formularioEstaValido) {
                try {
                    finalizaFormulario();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void verificaSituacao() {
        spinnerSituacao.setOnItemClickListener((parent, view, position, id) -> {
            String situacao_selecionada = spinnerSituacao.getEditableText().toString();

            if (situacao_selecionada.equals(SITUACAO_CONTATO_ESCOLA_PARA) || situacao_selecionada.equals(SITUACAO_AMBIENTACAO_PARA)) {
                campo_data_situacao_agendada.setVisibility(View.VISIBLE);
                campo_nome_outraEscola.setVisibility(View.GONE);
                formulario_categoria.setVisibility(View.GONE);
                campo_motivo_outraEscola.setVisibility(View.GONE);
                configuracampos.configuraCampoObrigatorioSituacao(campo_data_situacao_agendada);
                configuracampos.removeValidadorCategoria();
                configuracampos.removeValidadorMotivo();

            } else if (situacao_selecionada.equals(SITUACAO_MATRICULOU_OUTRA_ESCOLA)) {
                campo_data_situacao_agendada.setVisibility(View.GONE);
                formulario_categoria.setVisibility(View.VISIBLE);
                campo_nome_outraEscola.setVisibility(View.VISIBLE);
                campo_motivo_outraEscola.setVisibility(View.VISIBLE);
                configuracampos.removeValidadorSituacao();
                configuracampos.configuraCampoObrigatorioCategoria(formulario_categoria);
                configuracampos.configuraCampoObrigatorioMotivo(campo_motivo_outraEscola);
            } else {
                campo_data_situacao_agendada.setVisibility(View.GONE);
                formulario_categoria.setVisibility(View.GONE);
                campo_nome_outraEscola.setVisibility(View.GONE);
                campo_motivo_outraEscola.setVisibility(View.GONE);
                configuracampos.removeValidadorSituacao();
                configuracampos.removeValidadorCategoria();
                configuracampos.removeValidadorMotivo();
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

        campo_nome_crianca.getEditText().setText(visita.getNome_crianca());
        campo_dataNascimento_crianca.getEditText().setText(visita.getDataNascimento_crianca());
        spinnerTurma_crianca.setText(visita.getTurma_crianca(), false);
        spinnerTurno_crianca.setText(visita.getTurno_crianca(), false);
        preencheCadastroIrmaoEdicao();

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
        spinnerSecretaria.setText(visita.getSecretaria(), false);
        spinnerTipoAtendimento.setText(visita.getTipoAtendimento(), false);
        spinnerColaborador.setText(visita.getColaborador(), false);
        spinnerComoNosConheceu.setText(visita.getComoNosConheceu(), false);
        habilitaSituacao();
        campo_observacao.getEditText().setText(visita.getObservacao());
    }

    private void preencheCadastroIrmaoEdicao() {
        if (visita.getTemIrmao()) {
            switchTem_irmao.setChecked(true);
            cardview_irmao.setVisibility(View.VISIBLE);
            campo_nome_Irmao.getEditText().setText(visita.getNome_irmao());
            campo_dataNascimento_Irmao.getEditText().setText(visita.getDataNascimento_irmao());
            spinnerTurma_irmao.setText(visita.getTurma_irmao(), false);
            spinnerTurno_irmao.setText(visita.getTurno_irmao(), false);
        }
    }

    private void habilitaSituacao() {
        if (visita.getSituacao().equals(SITUACAO_CONTATO_ESCOLA_PARA) || visita.getSituacao().equals(SITUACAO_AMBIENTACAO_PARA)) {
            spinnerSituacao.setText(visita.getSituacao(), false);
            campo_data_situacao_agendada.setVisibility(View.VISIBLE);
            campo_data_situacao_agendada.getEditText().setText(visita.getDataAgendada());
        } else if (visita.getSituacao().equals(SITUACAO_MATRICULOU_OUTRA_ESCOLA)) {
            spinnerSituacao.setText(visita.getSituacao(), false);
            formulario_categoria.setVisibility(View.VISIBLE);
            spinnerCategoria.setText(visita.getCategoria(), false);
            campo_motivo_outraEscola.setVisibility(View.VISIBLE);
            campo_motivo_outraEscola.getEditText().setText(visita.getMotivo_outraEscola());
            campo_nome_outraEscola.setVisibility(View.VISIBLE);
            campo_nome_outraEscola.getEditText().setText(visita.getNome_outraEscola());
        } else {
            spinnerSituacao.setText(visita.getSituacao(), false);
        }
    }

    private void finalizaFormulario() throws IOException {
        preencheVisitaFinalizada();
        if (visita.temIdValido()) {
            VisitaDAO.edita(visita);
            Toast toast = Toast.makeText(this, ALTERACAO_SUCESSO, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 50);
            toast.show();
        } else {
            visitaDAO.salva(visita);
            if (!email_responsavel_1.equals("")) {
                enviarEmail();
            }
            Toast toast = Toast.makeText(this, CADASTRO_SUCESSO, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 50);
            toast.show();
        }
        finish();
    }

    private void enviarEmail() {
        final String nome = "Thiago Nome teste";
        final String email = email_responsavel_1;
        //final String email = email_responsavel_1 + ";" + email_responsavel_2;
        final String subject = "assunto do email";
        final String body = "corpo do email do " + nome;

        if (!isOnline()) {
            //Toast.makeText(getApplicationContext(), "Não estava online para enviar e-mail!", Toast.LENGTH_SHORT).show();
            //System.exit(0);
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                Mail m = new Mail();

                String[] toArr = {email};
                m.setTo(toArr);

                //m.setFrom("seunome@seuemail.com.br"); //caso queira enviar em nome de outro
                m.setSubject(subject);
                m.setBody(body);

                try {
                    //m.addAttachment("pathDoAnexo");//anexo opcional
                    m.send();
                } catch (RuntimeException rex) {
                }//erro ignorado
                catch (Exception e) {
                    e.printStackTrace();
                    System.exit(0);
                }
                //Toast.makeText(getApplicationContext(), "Email enviado!", Toast.LENGTH_SHORT).show();
            }
        }).start();
    }

    private boolean isOnline() {
        try {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return netInfo != null && netInfo.isConnectedOrConnecting();
        } catch (Exception ex) {
            //Toast.makeText(getApplicationContext(), "Erro ao verificar se estava online! (" + ex.getMessage() + ")", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void preencheVisitaFinalizada() {
        String nome = campo_nome_crianca.getEditText().getText().toString();
        String data_nascimento = campo_dataNascimento_crianca.getEditText().getText().toString();
        String turma = spinnerTurma_crianca.getEditableText().toString();
        String turno = spinnerTurno_crianca.getEditableText().toString();
        preencheIrmao();

        String nome_responsavel_1 = campo_nome_responsavel_1.getEditText().getText().toString();
        email_responsavel_1 = campo_email_responsavel_1.getEditText().getText().toString();
        String telefone_fixo_responsavel_1 = campo_telefone_fixo_responsavel_1.getEditText().getText().toString();
        String telefone_celular_responsavel_1 = campo_telefone_celular_responsavel_1.getEditText().getText().toString();
        String cpf_responsavel_1 = campo_cpf_responsavel_1.getEditText().getText().toString();

        String nome_responsavel_2 = campo_nome_responsavel_2.getEditText().getText().toString();
        email_responsavel_2 = campo_email_responsavel_2.getEditText().getText().toString();
        String telefone_fixo_responsavel_2 = campo_telefone_fixo_responsavel_2.getEditText().getText().toString();
        String telefone_celular_responsavel_2 = campo_telefone_celular_responsavel_2.getEditText().getText().toString();
        String cpf_responsavel_2 = campo_cpf_responsavel_2.getEditText().getText().toString();

        //Date dataCadastro = (Date) campo_data_visita.getEditText().getText();Date dataAtual = (Date) Calendar.getInstance().getTime();
        String dataCadastro = campo_data_visita.getEditText().getText().toString();
        String unidade = spinnerUnidade.getEditableText().toString();
        String secretaria = spinnerSecretaria.getEditableText().toString();
        String tipoAtendimento = spinnerTipoAtendimento.getEditableText().toString();
        String colaborador = spinnerColaborador.getEditableText().toString();
        String comoNosConheceu = spinnerComoNosConheceu.getEditableText().toString();
        String situacao = spinnerSituacao.getEditableText().toString();
        String dataAgendada = campo_data_situacao_agendada.getEditText().getText().toString();
        String categoria = spinnerCategoria.getEditableText().toString();
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
        visita.setSecretaria(secretaria);
        visita.setTipoAtendimento(tipoAtendimento);
        visita.setColaborador(colaborador);
        visita.setComoNosConheceu(comoNosConheceu);
        visita.setSituacao(situacao);
        visita.setDataAgendada(dataAgendada);
        visita.setCategoria(categoria);
        visita.setMotivo_outraEscola(motivo);
        visita.setNome_outraEscola(nome_outraEscola);
        visita.setObservacao(observacao);
    }

    private void preencheIrmao() {
        if (switchTem_irmao.isChecked()) {
            visita.setTemIrmao(true);
            String nome_irmao = campo_nome_Irmao.getEditText().getText().toString();
            String data_nascimento_irmao = campo_dataNascimento_Irmao.getEditText().getText().toString();
            String turma_irmao = spinnerTurma_irmao.getEditableText().toString();
            String turno_irmao = spinnerTurno_irmao.getEditableText().toString();

            visita.setNome_irmao(nome_irmao);
            visita.setDataNascimento_irmao(data_nascimento_irmao);
            visita.setTurma_irmao(turma_irmao);
            visita.setTurno_irmao(turno_irmao);
        } else
            visita.setTemIrmao(false);
    }

    private void inicializacaoDosCampos() {
        campoFotoFamilia = findViewById(R.id.formulario_foto_familia);
        camera = findViewById(R.id.formulario_botao_tirar_foto);
        galeriaDeFotos = findViewById(R.id.formulario_botao_galeria);

        campo_nome_crianca = findViewById(R.id.formulario_nome_crianca);
        configuracampos.configuraCampoObrigatorio(campo_nome_crianca);
        campo_dataNascimento_crianca = findViewById(R.id.formulario_data_nascimento_crianca);
        pegaData(campo_dataNascimento_crianca);
        configuracampos.configuraCampoObrigatorio(campo_dataNascimento_crianca);
        spinnerTurma_crianca = findViewById(R.id.spinner_dialog_unidade);
        spinnerTurno_crianca = findViewById(R.id.spinner_Turno_crianca);
        formulario_turma_crianca = findViewById(R.id.formulario_turma_crianca);
        configuracampos.configuraCampoObrigatorio(formulario_turma_crianca);
        formulario_turno_crianca = findViewById(R.id.formulario_turno_crianca);
        configuracampos.configuraCampoObrigatorio(formulario_turno_crianca);

        ArrayAdapter<Turma> arrayAdapterTurma = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, turmaDAO.todos());
        spinnerTurma_crianca.setAdapter(arrayAdapterTurma);
        ArrayAdapter<Turno> arrayAdapterTurno = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, turnoDAO.todos());
        spinnerTurno_crianca.setAdapter(arrayAdapterTurno);

        switchTem_irmao = findViewById(R.id.switch_tem_irmao);
        cardview_irmao = findViewById(R.id.cardview_irmao);
        campo_nome_Irmao = findViewById(R.id.formulario_nome_irmao);
        campo_dataNascimento_Irmao = findViewById(R.id.formulario_data_nascimento_irmao);
        pegaData(campo_dataNascimento_Irmao);
        spinnerTurma_irmao = findViewById(R.id.spinner_Turma_irmao);
        spinnerTurno_irmao = findViewById(R.id.spinner_Turno_irmao);
        spinnerTurma_irmao.setAdapter(arrayAdapterTurma);
        spinnerTurno_irmao.setAdapter(arrayAdapterTurno);
        formulario_turma_irmao = findViewById(R.id.formulario_turma_irmao);
        formulario_turno_irmao = findViewById(R.id.formulario_turno_irmao);

        campo_nome_responsavel_1 = findViewById(R.id.formulario_nome_responsavel_1);
        configuracampos.configuraCampoObrigatorio(campo_nome_responsavel_1);
        campo_email_responsavel_1 = findViewById(R.id.formulario_email_responsavel1);
        configuracampos.configuraCampoEmail(campo_email_responsavel_1);
        campo_telefone_fixo_responsavel_1 = findViewById(R.id.formulario_fixo_responsavel1);
        configuracampos.configuraCampoTelefone(campo_telefone_fixo_responsavel_1);
        campo_telefone_celular_responsavel_1 = findViewById(R.id.formulario_celular_responsavel1);
        configuracampos.configuraCampoTelefone(campo_telefone_celular_responsavel_1);
        campo_cpf_responsavel_1 = findViewById(R.id.formulario_cpf_responsavel1);
        configuracampos.configuraCampoCPF(campo_cpf_responsavel_1);

        campo_nome_responsavel_2 = findViewById(R.id.formulario_nome_responsavel_2);
        campo_email_responsavel_2 = findViewById(R.id.formulario_email_responsavel_2);
        configuracampos.configuraCampoEmail(campo_email_responsavel_2);
        campo_telefone_fixo_responsavel_2 = findViewById(R.id.formulario_fixo_responsavel_2);
        configuracampos.configuraCampoTelefone(campo_telefone_fixo_responsavel_2);
        campo_telefone_celular_responsavel_2 = findViewById(R.id.formulario_celular_responsavel_2);
        configuracampos.configuraCampoTelefone(campo_telefone_celular_responsavel_2);
        campo_cpf_responsavel_2 = findViewById(R.id.formulario_cpf_responsavel_2);
        configuracampos.configuraCampoCPF(campo_cpf_responsavel_2);

        campo_data_visita = findViewById(R.id.formulario_data_visita);
        configuracampos.configuraCampoObrigatorio(campo_data_visita);
        pegaData(campo_data_visita);
        spinnerUnidade = findViewById(R.id.spinner_Unidade);
        formulario_unidade = findViewById(R.id.formulario_unidade);
        configuracampos.configuraCampoObrigatorio(formulario_unidade);
        spinnerSecretaria = findViewById(R.id.spinner_Secretaria);
        formulario_secretaria = findViewById(R.id.formulario_secretaria);
        configuracampos.configuraCampoObrigatorio(formulario_secretaria);
        spinnerTipoAtendimento = findViewById(R.id.spinner_TipoAtendimento);
        formulario_tipoAtendimento = findViewById(R.id.formulario_tipoAtendimento);
        configuracampos.configuraCampoObrigatorio(formulario_tipoAtendimento);
        spinnerColaborador = findViewById(R.id.spinner_Colaborador);
        formulario_colaborador = findViewById(R.id.formulario_colaborador);
        configuracampos.configuraCampoObrigatorio(formulario_colaborador);
        spinnerComoNosConheceu = findViewById(R.id.spinner_ComoNosConheceu);
        formulario_comoNosConheceu = findViewById(R.id.formulario_comoNosConheceu);
        configuracampos.configuraCampoObrigatorio(formulario_comoNosConheceu);
        spinnerSituacao = findViewById(R.id.spinner_Situacao);
        formulario_situacao = findViewById(R.id.formulario_situacao);
        configuracampos.configuraCampoObrigatorio(formulario_situacao);
        campo_data_situacao_agendada = findViewById(R.id.formulario_data_agendada_situacao);
        pegaData(campo_data_situacao_agendada);

        spinnerCategoria = findViewById(R.id.spinner_categoria);
        formulario_categoria = findViewById(R.id.formulario_categoria);
        campo_motivo_outraEscola = findViewById(R.id.formulario_Motivo_Matriculou_OutraEscola);
        campo_nome_outraEscola = findViewById(R.id.formulario_Matriculou_Nome_OutraEscola);
        campo_observacao = findViewById(R.id.formulario_observacao);

        ArrayAdapter<Categoria> arrayAdapterCategoria = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, categoriaDAO.todos());
        spinnerCategoria.setAdapter(arrayAdapterCategoria);
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
        EditText campo = campo_data.getEditText();
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        campo.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(Cadastro_Visita.this, (view, year1, month1, day1) -> {
                month1 = month1 + 1;
                String data = day1 + "/" + month1 + "/" + year1;
                campo_data.getEditText().setText(data);
            }, year, month, day);
            datePickerDialog.show();
        });
    }

    private boolean validaTodosCampos() {
        boolean formularioEstaValido = true;
        for (Validador validador : configuracampos.validadores) {
            if (!validador.estaValido()) formularioEstaValido = false;
        }
        return formularioEstaValido;
    }
}