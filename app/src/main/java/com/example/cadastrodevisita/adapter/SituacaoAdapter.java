package com.example.cadastrodevisita.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.dao.SituacaoDAO;
import com.example.cadastrodevisita.model.Situacao;

import java.util.ArrayList;
import java.util.List;

public class SituacaoAdapter extends BaseAdapter {

    private List<Situacao> situacaos = new ArrayList<>();
    private final Context context;
    private final SituacaoAdapter.OnItemClickListener onItemClickListener;
    private int posicao;
    private SituacaoDAO dao = new SituacaoDAO();

    public SituacaoAdapter(Context context, SituacaoAdapter.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getCount() {
        return situacaos.size();
    }

    @Override
    public Situacao getItem(int posicao) {
        return situacaos.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return situacaos.get(posicao).getId();
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        View viewCriada = criaView(viewGroup);
        Situacao situacao = situacaos.get(posicao);
        this.posicao = posicao;
        vincula(viewCriada, situacao);
        return viewCriada;
    }

    private void vincula(View view, Situacao situacao) {
        TextView situacaoPreenche = view.findViewById(R.id.item_nome_ListViewGenerica);
        situacaoPreenche.setText(situacao.getSituacao());
        TextView unidade = view.findViewById(R.id.item_unidade_ListViewGenerica);
        unidade.setText(situacao.getUnidade());
        configuraItemClique(view, situacao);
    }

    private void configuraItemClique(@NonNull View itemView, Situacao situacao) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener
                        .onItemClick(posicao, situacao);
            }
        });
    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_cadastro, viewGroup, false);
    }

    public void atualiza(List<Situacao> situacao) {
        this.situacaos.clear();
        this.situacaos.addAll(situacao);
        notifyDataSetChanged();
    }

    public void atualiza() {
        this.situacaos.clear();
        this.situacaos.addAll(dao.todos());
        notifyDataSetChanged();
    }

    public void remove(Situacao situacao) {
        situacaos.remove(situacao);
        notifyDataSetChanged();
    }

    public List<Situacao> getSituacaos() {
        return situacaos;
    }

    public interface OnItemClickListener {
        void onItemClick(int posicao, Situacao situacao);
    }
}

