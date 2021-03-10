package com.example.cadastrodevisita.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.dao.ComoNosConheceuDAO;
import com.example.cadastrodevisita.model.ComoNosConheceu;

import java.util.ArrayList;
import java.util.List;

public class ComoNosConheceuAdapter extends BaseAdapter {

    private List<ComoNosConheceu> comoNosConheceuList = new ArrayList<>();
    private final Context context;
    private final ComoNosConheceuAdapter.OnItemClickListener onItemClickListener;
    private int posicao;
    private ComoNosConheceuDAO dao = new ComoNosConheceuDAO();

    public ComoNosConheceuAdapter(Context context, ComoNosConheceuAdapter.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getCount() {
        return comoNosConheceuList.size();
    }

    @Override
    public ComoNosConheceu getItem(int posicao) {
        return comoNosConheceuList.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return comoNosConheceuList.get(posicao).getId();
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        View viewCriada = criaView(viewGroup);
        ComoNosConheceu comoNosConheceu = comoNosConheceuList.get(posicao);
        this.posicao = posicao;
        vincula(viewCriada, comoNosConheceu);
        return viewCriada;
    }

    private void vincula(View view, ComoNosConheceu comoNosConheceu) {
        TextView comoNosConheceuPreenche = view.findViewById(R.id.item_nome_ListViewGenerica);
        comoNosConheceuPreenche.setText(comoNosConheceu.getComoNosConheceu());
        TextView unidade = view.findViewById(R.id.item_unidade_ListViewGenerica);
        unidade.setText(comoNosConheceu.getUnidade());
        configuraItemClique(view, comoNosConheceu);
    }

    private void configuraItemClique(@NonNull View itemView, ComoNosConheceu comoNosConheceu) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener
                        .onItemClick(posicao, comoNosConheceu);
            }
        });
    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_cadastro, viewGroup, false);
    }

    public void atualiza(List<ComoNosConheceu> comoNosConheceu) {
        this.comoNosConheceuList.clear();
        this.comoNosConheceuList.addAll(comoNosConheceu);
        notifyDataSetChanged();
    }

    public void atualiza() {
        this.comoNosConheceuList.clear();
        this.comoNosConheceuList.addAll(dao.todos());
        notifyDataSetChanged();
    }

    public void remove(ComoNosConheceu comoNosConheceu) {
        comoNosConheceuList.remove(comoNosConheceu);
        notifyDataSetChanged();
    }

    public List<ComoNosConheceu> getComoNosConheceuList() {
        return comoNosConheceuList;
    }

    public interface OnItemClickListener {
        void onItemClick(int posicao, ComoNosConheceu comoNosConheceu);
    }
}

