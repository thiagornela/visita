package com.example.cadastrodevisita.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.dao.TipoAtendimentoDAO;
import com.example.cadastrodevisita.model.TipoAtendimento;

import java.util.ArrayList;
import java.util.List;

public class TipoAtendimentoAdapter extends BaseAdapter {

    private List<TipoAtendimento> tipoAtendimentos = new ArrayList<>();
    private final Context context;
    private final TipoAtendimentoAdapter.OnItemClickListener onItemClickListener;
    private int posicao;
    private TipoAtendimentoDAO dao = new TipoAtendimentoDAO();

    public TipoAtendimentoAdapter(Context context, TipoAtendimentoAdapter.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getCount() {
        return tipoAtendimentos.size();
    }

    @Override
    public TipoAtendimento getItem(int posicao) {
        return tipoAtendimentos.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return tipoAtendimentos.get(posicao).getId();
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        View viewCriada = criaView(viewGroup);
        TipoAtendimento tipoAtendimento = tipoAtendimentos.get(posicao);
        this.posicao = posicao;
        vincula(viewCriada, tipoAtendimento);
        return viewCriada;
    }

    private void vincula(View view, TipoAtendimento tipoAtendimento) {
        TextView tipoAtendimentoPreenche = view.findViewById(R.id.item_nome_ListViewGenerica);
        tipoAtendimentoPreenche.setText(tipoAtendimento.getAtendimento());
        TextView unidade = view.findViewById(R.id.item_unidade_ListViewGenerica);
        unidade.setText(tipoAtendimento.getUnidade());
        configuraItemClique(view, tipoAtendimento);
    }

    private void configuraItemClique(@NonNull View itemView, TipoAtendimento tipoAtendimento) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener
                        .onItemClick(posicao, tipoAtendimento);
            }
        });
    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_cadastro, viewGroup, false);
    }

    public void atualiza(List<TipoAtendimento> tipoAtendimento) {
        this.tipoAtendimentos.clear();
        this.tipoAtendimentos.addAll(tipoAtendimento);
        notifyDataSetChanged();
    }

    public void atualiza() {
        this.tipoAtendimentos.clear();
        this.tipoAtendimentos.addAll(dao.todos());
        notifyDataSetChanged();
    }

    public void remove(TipoAtendimento tipoAtendimento) {
        tipoAtendimentos.remove(tipoAtendimento);
        notifyDataSetChanged();
    }

    public List<TipoAtendimento> getTipoAtendimentos() {
        return tipoAtendimentos;
    }

    public interface OnItemClickListener {
        void onItemClick(int posicao, TipoAtendimento tipoAtendimento);
    }
}
