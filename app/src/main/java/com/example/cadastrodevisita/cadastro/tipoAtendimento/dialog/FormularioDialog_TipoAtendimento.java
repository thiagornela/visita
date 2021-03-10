package com.example.cadastrodevisita.cadastro.tipoAtendimento.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.dao.UnidadeDAO;
import com.example.cadastrodevisita.model.TipoAtendimento;
import com.example.cadastrodevisita.model.Unidade;
import com.example.cadastrodevisita.validator.ConfiguraCampos;
import com.example.cadastrodevisita.validator.Validador;
import com.google.android.material.textfield.TextInputLayout;

public class FormularioDialog_TipoAtendimento {

    private final String titulo;
    private final String tituloBotaoPositivo;
    private final ConfirmacaoListener listener;
    private final Context context;
    private final String campoAlterado = "TipoAtendimento";
    private static final String TITULO_BOTAO_NEGATIVO = "Cancelar";
    private TipoAtendimento tipoAtendimento;
    private UnidadeDAO unidadeDAO = new UnidadeDAO();
    private AutoCompleteTextView campoUnidade;
    private ConfiguraCampos configuracampos = new ConfiguraCampos();
    private TextInputLayout ValidacampoPrincipal;
    private TextInputLayout ValidaspinnerUnidade;

    FormularioDialog_TipoAtendimento(Context context,
                               String titulo,
                               String tituloBotaoPositivo,
                               ConfirmacaoListener listener) {
        this.titulo = titulo;
        this.tituloBotaoPositivo = tituloBotaoPositivo;
        this.listener = listener;
        this.context = context;
    }

    FormularioDialog_TipoAtendimento(Context context,
                               String titulo,
                               String tituloBotaoPositivo,
                               ConfirmacaoListener listener,
                               TipoAtendimento tipoAtendimento) {
        this(context, titulo, tituloBotaoPositivo, listener);
        this.tipoAtendimento = tipoAtendimento;
    }

    public void mostra() {
        @SuppressLint("InflateParams") View viewCriada = LayoutInflater.from(context)
                .inflate(R.layout.formulario_dialog, null);
        ValidacampoPrincipal = viewCriada.findViewById(R.id.dialog_campoPrincipal);
        ValidaspinnerUnidade = viewCriada.findViewById(R.id.dialog_Unidade);
        tentaPreencherFormulario(viewCriada);
        campoUnidade = getAutoCompleteTextView(viewCriada, R.id.spinner_dialog_unidade);
        EditText campoPrincipal = getEditText(viewCriada, R.id.dialog_campoPrincipal, campoAlterado);
        configuracampos.configuraCampoObrigatorio(ValidacampoPrincipal);
        configuracampos.configuraCampoObrigatorio(ValidaspinnerUnidade);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titulo);
        builder.setView(viewCriada);
        builder.setPositiveButton(tituloBotaoPositivo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean formularioEstaValido = validaTodosCampos();
                if (formularioEstaValido) {
                    criaProduto(campoPrincipal, campoUnidade);
                    dialog.dismiss();
                }
                else{
                    Toast.makeText(context, "Todos os campos são obrigatórios", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton(TITULO_BOTAO_NEGATIVO, null);
        builder.show();
    }

    private boolean validaTodosCampos() {
        boolean formularioEstaValido = true;
        for (Validador validador : configuracampos.validadores) {
            if (!validador.estaValido()) formularioEstaValido = false;
        }
        return formularioEstaValido;
    }

    @SuppressLint("SetTextI18n")
    private void tentaPreencherFormulario(View viewCriada) {
        if (tipoAtendimento != null) {
            EditText campoPrincipal = getEditText(viewCriada, R.id.dialog_campoPrincipal, campoAlterado);
            campoPrincipal.setText(tipoAtendimento.getAtendimento());
            EditText campoUnidade = getEditText(viewCriada, R.id.dialog_Unidade, "Unidade");
            campoUnidade.setText(tipoAtendimento.getUnidade());
        }
    }

    private void criaProduto(EditText campoPrincipal, EditText campoUnidade) {
        String textoPrincipal = campoPrincipal.getText().toString();
        String unidade = campoUnidade.getText().toString();
        int id = preencheId();
        TipoAtendimento tipoAtendimento = new TipoAtendimento(id, textoPrincipal, unidade);
        listener.quandoConfirmado(tipoAtendimento);
    }


    private int preencheId() {
        if (tipoAtendimento != null) {
            return tipoAtendimento.getId();
        }
        return 0;
    }

    private EditText getEditText(View viewCriada, int idTextInputLayout, String CampoAlterado) {
        TextInputLayout textInputLayout = viewCriada.findViewById(idTextInputLayout);
        textInputLayout.setHint(CampoAlterado);
        return textInputLayout.getEditText();
    }

    private AutoCompleteTextView getAutoCompleteTextView(View viewCriada, int idAutoCompleteTextView) {
        AutoCompleteTextView spinner = viewCriada.findViewById(idAutoCompleteTextView);
        ArrayAdapter<Unidade> arrayAdapterUnidade = new ArrayAdapter<>(context, android.R.layout.simple_selectable_list_item, unidadeDAO.todos());
        spinner.setAdapter(arrayAdapterUnidade);
        return spinner;
    }

    public interface ConfirmacaoListener {
        void quandoConfirmado(TipoAtendimento tipoAtendimento);
    }
}