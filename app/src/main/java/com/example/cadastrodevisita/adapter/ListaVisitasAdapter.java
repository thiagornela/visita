package com.example.cadastrodevisita.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.model.Visita;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.cadastrodevisita.ui.activities.Constantes.SITUACAO_AMBIENTACAO_PARA;
import static com.example.cadastrodevisita.ui.activities.Constantes.SITUACAO_CONTATO_ESCOLA_PARA;

public class ListaVisitasAdapter extends BaseAdapter {

    private List<Visita> visitas;
    private final Context context;

    public ListaVisitasAdapter(List<Visita> visitas, Context context) {
        this.visitas = visitas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return visitas.size();
    }

    @Override
    public Visita getItem(int posicao) {
        return visitas.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return visitas.get(posicao).getId();
    }


    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        View viewCriada = criaView(viewGroup);
        Visita visita = visitas.get(posicao);
        try {
            vincula(viewCriada, visita);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return viewCriada;
    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater.from(context)
                .inflate(R.layout.item_visita, viewGroup, false);
    }

    private void vincula(View view, Visita visita) throws ParseException {
        ImageView imagemFamilia = view.findViewById(R.id.item_foto_familia);
        if (visita.getFoto_familia() != null)
            imagemFamilia.setImageBitmap(visita.getFoto_familia());
        TextView unidade = view.findViewById(R.id.item_visita_unidade);
        unidade.setText(visita.getUnidade());
        TextView secretaria = view.findViewById(R.id.item_secretaria);
        secretaria.setText(visita.getSecrearia());
//        TextView dataVisita = view.findViewById(R.id.item_data_visita);
//        dataVisita.setText(visita.getDataCadastro());
        TextView nome_crianca = view.findViewById(R.id.item_visita_nome_crianca);
        nome_crianca.setText(visita.getNome_crianca());
        TextView turma = view.findViewById(R.id.item_visita_turma);
        turma.setText(visita.getTurma_crianca());
        TextView turno = view.findViewById(R.id.item_visita_turno);
        turno.setText(visita.getTurno_crianca());
        TextView nome_responsavel1 = view.findViewById(R.id.item_visita_nome_responsavel_1);
        nome_responsavel1.setText(visita.getNome_responsavel_1());

        TextView telefone_fixo_responsavel1 = view.findViewById(R.id.item_visita_telefone_fixo);
        if (visita.getTelefone_fixo_responsavel_1() != null) {
            telefone_fixo_responsavel1.setText(visita.getTelefone_fixo_responsavel_1());
        } else {
            telefone_fixo_responsavel1.setVisibility(View.GONE);
            view.findViewById(R.id.item_tracinho).setVisibility(View.GONE);
        }

        TextView telefone_celular_responsavel1 = view.findViewById(R.id.item_visita_telefone_celular);
        telefone_celular_responsavel1.setText(visita.getTelefone_celular_responsavel_1());
        TextView email_responsavel1 = view.findViewById(R.id.item_visita_email_responsavel_1);
        email_responsavel1.setText(visita.getEmail_responsavel_1());
        TextView situacao = view.findViewById(R.id.item_visita_situacao);
        situacao.setText(visita.getSituacao());

        TextView data_limite_situacao = view.findViewById(R.id.item_data_agendada);
        if (visita.getSituacao().equals(SITUACAO_CONTATO_ESCOLA_PARA) || visita.getSituacao().equals(SITUACAO_AMBIENTACAO_PARA)) {
            data_limite_situacao.setVisibility(view.VISIBLE);
            data_limite_situacao.setText(visita.getDataAgendada());
        }

        Switch temIrmao = view.findViewById(R.id.item_switch_tem_irmao);
        if (visita.getTemIrmao()) {
            temIrmao.setVisibility(view.VISIBLE);
        }

        verificaDataMudaCor(view, visita);

    }


//    private void verificaDataMudaCor(View view, Visita visita) throws ParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY", Locale.getDefault());
//        dataAtual.setTime(sdf.parse(visita.getDataAgendada()));
//        View barra_indicadora = view.findViewById(R.id.item_barra_indicadora_situacao);
//        if (visita.getSituacao().equals(SITUACAO_CONTATO_ESCOLA_PARA) || visita.getSituacao().equals(SITUACAO_AMBIENTACAO_PARA)) {
//            if (dataAtual.equals(visita.getDataAgendada())) {
//                barra_indicadora.setBackgroundColor(Color.parseColor("#FA0101"));
//            } else if (dataAtual.after(visita.getDataAgendada())) {
//                barra_indicadora.setBackgroundColor(Color.parseColor("#FA0101"));
//            } else if (dataAtual.before(visita.getDataAgendada())) {
//                barra_indicadora.setBackgroundColor(Color.parseColor("#FFE500"));
//            } else
//                barra_indicadora.setBackgroundColor(Color.parseColor("#12C119"));
//        }
//
//        notifyDataSetChanged();
//    }

    private void verificaDataMudaCor(View view, Visita visita) throws ParseException {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Date dataAgendadaFormatada = sdf.parse(visita.getDataAgendada());

        String DataAtualString = sdf.format(cal.getTime());
        Date dataAtualFormatada = sdf.parse(DataAtualString);

        View barra_indicadora = view.findViewById(R.id.item_barra_indicadora_situacao);

        if (visita.getSituacao().equals(SITUACAO_CONTATO_ESCOLA_PARA) || visita.getSituacao().equals(SITUACAO_AMBIENTACAO_PARA)) {
            if (dataAtualFormatada.equals(dataAgendadaFormatada)) {
                barra_indicadora.setBackgroundColor(Color.parseColor("#FA0101"));
            } else if (dataAtualFormatada.after(dataAgendadaFormatada)) {
                barra_indicadora.setBackgroundColor(Color.parseColor("#FA0101"));
            } else if (dataAtualFormatada.before(dataAgendadaFormatada)) {
                barra_indicadora.setBackgroundColor(Color.parseColor("#FFE500"));
            }
            notifyDataSetChanged();
        }
    }


    public void atualiza(List<Visita> visitas) {
        List<Visita> visitas_filtradas = new ArrayList<>();
        for (int i = 0; i < visitas.size(); i++) {
            if (visitas.get(i).getSituacao().equals(SITUACAO_CONTATO_ESCOLA_PARA) || visitas.get(i).getSituacao().equals(SITUACAO_AMBIENTACAO_PARA)) {
                visitas_filtradas.add(visitas.get(i));
            }
        }
        this.visitas.clear();
        this.visitas.addAll(visitas_filtradas);
        notifyDataSetChanged();
    }

    public List<Visita> getVisitas() {
        return visitas;
    }

}