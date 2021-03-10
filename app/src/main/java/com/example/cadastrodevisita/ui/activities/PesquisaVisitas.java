package com.example.cadastrodevisita.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.cadastrodevisita.listview.ListaVisitasView;
import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.dao.TurmaDAO;
import com.example.cadastrodevisita.dao.TurnoDAO;
import com.example.cadastrodevisita.dao.UnidadeDAO;
import com.example.cadastrodevisita.model.Turma;
import com.example.cadastrodevisita.model.Turno;
import com.example.cadastrodevisita.model.Unidade;
import com.example.cadastrodevisita.validator.ConfiguraCampos;
import com.example.cadastrodevisita.validator.Validador;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.util.Calendar;

public class PesquisaVisitas extends AppCompatActivity {

    private static final String TITULO_APPBAR = "Pesquisar visitas";
    private final ListaVisitasView listaVisitasView = new ListaVisitasView(this);
    private UnidadeDAO unidadeDAO = new UnidadeDAO();
    private final TurmaDAO turmaDAO = new TurmaDAO();
    private final TurnoDAO turnoDAO = new TurnoDAO();

    private TextInputLayout formulario_Unidade;
    private AutoCompleteTextView spinner_filtro_unidade;
    private TextInputLayout campo_dataInicial;

    private TextInputLayout campo_dataFinal;

    private TextInputLayout formulario_Turma;
    private AutoCompleteTextView spinnerTurma;
    private TextInputLayout formulario_Turno;
    private AutoCompleteTextView spinnerTurno;
    private TextInputLayout nomeCrianca;
    private TextInputLayout nomeResponsavel;
    private Button buttonPesquisar;
    private Button buttonLimpar;
    private RecyclerView listaDeVisitas;

    private ConfiguraCampos configuracampos = new ConfiguraCampos();
    private String unidade;
    private String dataInicial;
    private String dataFinal;
    private String crianca;
    private String responsavel;
    private String turma;
    private String turno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa_visitas);
        setTitle(TITULO_APPBAR);

        inicializaCampos();

        buttonLimpar.setOnClickListener(v -> {
            spinner_filtro_unidade.setText(null);
            spinnerTurma.setText(null);
            spinnerTurno.setText(null);
            campo_dataInicial.getEditText().setText(null);
            campo_dataFinal.getEditText().setText(null);
            nomeCrianca.getEditText().setText(null);
            nomeResponsavel.getEditText().setText(null);
            listaVisitasView.removeTudo();
        });

        buttonPesquisar.setOnClickListener(v -> {
            listaVisitasView.configuraAdapter(listaDeVisitas);
            unidade = spinner_filtro_unidade.getEditableText().toString();
            dataInicial = campo_dataInicial.getEditText().getText().toString();
            dataFinal = campo_dataFinal.getEditText().getText().toString();
            crianca = nomeCrianca.getEditText().getText().toString();
            responsavel = nomeResponsavel.getEditText().getText().toString();
            turma = spinnerTurma.getEditableText().toString();
            turno = spinnerTurno.getEditableText().toString();

            configuraCampoObrigatorio(turma, turno);
            verificaCamposObrigatorios(unidade, dataInicial, dataFinal, crianca, responsavel, turma, turno);
        });

//        listaDeVisitas.setOnItemClickListener((parent, view, position, id) -> {
//            Visita visitaClicada = (Visita) parent.getItemAtPosition(position);
//            vaiParaEditaVisita(visitaClicada);
//        });

    }

//    private void vaiParaEditaVisita(Visita visitaClicada) {
//        Intent intent = new Intent(PesquisaVisitas.this, Cadastro_Visita.class);
//        intent.putExtra(CHAVE_VISITA, visitaClicada);
//        startActivity(intent);
//    }

    private void verificaCamposObrigatorios(String unidade, String dataInicial, String dataFinal, String crianca, String responsavel, String turma, String turno) {
        boolean formularioEstaValido = validaTodosCampos();
        if (formularioEstaValido) {
            try {
                listaVisitasView.buscaVisitas(unidade, dataInicial, dataFinal, crianca, responsavel, turma, turno);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaVisitasView.atualizaActivityPesquisa();
    }

    private void configuraCampoObrigatorio(String turma, String turno) {
        configuracampos.configuraCampoObrigatorio(formulario_Unidade);
        configuracampos.configuraCampoObrigatorio(campo_dataInicial);
        configuracampos.configuraCampoObrigatorio(campo_dataFinal);
        if(!turma.equals("") || !turno.equals("")){
            configuracampos.configuraCampoObrigatorio(formulario_Turma);
            configuracampos.configuraCampoObrigatorio(formulario_Turno);
        }
    }

    private void inicializaCampos() {
        formulario_Unidade = findViewById(R.id.pesquisaVisita_unidade);
        spinner_filtro_unidade = findViewById(R.id.spinner_dialog_unidade);
        ArrayAdapter<Unidade> arrayAdapterUnidade = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, unidadeDAO.todos());
        spinner_filtro_unidade.setAdapter(arrayAdapterUnidade);

        campo_dataInicial = findViewById(R.id.pesquisaVisita_DataInicial);
        pegaData(campo_dataInicial);
        campo_dataFinal = findViewById(R.id.pesquisaVisita_DataFinal);
        pegaData(campo_dataFinal);

        nomeCrianca = findViewById(R.id.pesquisaVisita_nomeCrianca);
        nomeResponsavel = findViewById(R.id.pesquisaVisita_nomeResponsavel);

        formulario_Turma = findViewById(R.id.pesquisaVisita_Turma);
        spinnerTurma = findViewById(R.id.spinner_pesquisaVisita_turma);
        formulario_Turno = findViewById(R.id.pesquisaVisita_Turno);
        spinnerTurno = findViewById(R.id.spinner_pesquisaVisita_turno);

        ArrayAdapter<Turma> arrayAdapterTurma = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, turmaDAO.todos());
        spinnerTurma.setAdapter(arrayAdapterTurma);
        ArrayAdapter<Turno> arrayAdapterTurno = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, turnoDAO.todos());
        spinnerTurno.setAdapter(arrayAdapterTurno);

        buttonPesquisar = findViewById(R.id.pesquisaVisita_button_buscar);
        buttonLimpar = findViewById(R.id.pesquisaVisita_button_limpar);
        listaDeVisitas = findViewById(R.id.pesquisaVisita_lista_visitas_recycleview);
    }

    private void pegaData(TextInputLayout campo_data) {
        EditText campo = (EditText) campo_data.getEditText();
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        campo.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(PesquisaVisitas.this, (view, year1, month1, day1) -> {
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