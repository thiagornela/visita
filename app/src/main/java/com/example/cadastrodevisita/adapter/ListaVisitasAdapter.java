package com.example.cadastrodevisita.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.model.Visita;

import java.util.ArrayList;
import java.util.List;

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
        vincula(viewCriada, visita);
        return viewCriada;
    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater.from(context)
                .inflate(R.layout.item_visita, viewGroup, false);
    }

    private void vincula(View view, Visita visita) {
        ImageView imagemFamilia = view.findViewById(R.id.formulario_foto_familia);
        imagemFamilia.setImageBitmap(visita.getFoto_familia());
        TextView unidade = view.findViewById(R.id.item_visita_unidade);
        unidade.setText(visita.getUnidade());
        TextView secretaria = view.findViewById(R.id.item_secretaria);
        secretaria.setText(visita.getSecrearia());
        TextView nome_crianca = view.findViewById(R.id.item_visita_nome_crianca);
        nome_crianca.setText(visita.getNome_crianca());
        TextView turma = view.findViewById(R.id.item_visita_turma);
        turma.setText(visita.getTurma_crianca());
        TextView turno = view.findViewById(R.id.item_visita_turno);
        turno.setText(visita.getTurno_crianca());
        TextView nome_responsavel1 = view.findViewById(R.id.item_visita_nome_responsavel_1);
        nome_responsavel1.setText(visita.getNome_responsavel_1());
        TextView telefone_fixo_responsavel1 = view.findViewById(R.id.item_visita_telefone_fixo);
        telefone_fixo_responsavel1.setText(visita.getTelefone_fixo_responsavel_1());
        TextView telefone_celular_responsavel1 = view.findViewById(R.id.item_visita_telefone_celular);
        telefone_celular_responsavel1.setText(visita.getTelefone_celular_responsavel_1());
        TextView email_responsavel1 = view.findViewById(R.id.item_visita_email_responsavel_1);
        email_responsavel1.setText(visita.getEmail_responsavel_1());
        TextView situacao = view.findViewById(R.id.item_visita_situacao);
        situacao.setText(visita.getSituacao());

        TextView data_limite_situacao = view.findViewById(R.id.item_data_limite);
        if (visita.getSituacao().equals("Contato da escola para ") || visita.getSituacao().equals("Ambientação para ")) {
            data_limite_situacao.setVisibility(view.VISIBLE);
            data_limite_situacao.setText(visita.getDataLimite());
        }

        Switch temIrmao = view.findViewById(R.id.item_switch_tem_irmao);
        if(visita.getTemIrmao()){
            temIrmao.setVisibility(view.VISIBLE);
        }
    }

    public void atualiza(List<Visita> visitas) {
        List<Visita> visitas_filtradas = new ArrayList<>();
        for (int i = 0; i < visitas.size(); i++) {
            if (visitas.get(i).getSituacao().equals("Contato da escola para ") || visitas.get(i).getSituacao().equals("Ambientação para ")) {
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