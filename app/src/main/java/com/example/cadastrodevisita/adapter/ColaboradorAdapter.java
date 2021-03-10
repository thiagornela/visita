package com.example.cadastrodevisita.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.dao.ColaboradorDAO;
import com.example.cadastrodevisita.model.Colaborador;

import java.util.ArrayList;
import java.util.List;

public class ColaboradorAdapter extends BaseAdapter {

    private List<Colaborador> colaboradores = new ArrayList<>();
    private final Context context;
    private final ColaboradorAdapter.OnItemClickListener onItemClickListener;
    private int posicao;
    private ColaboradorDAO dao = new ColaboradorDAO();

    public ColaboradorAdapter(Context context, ColaboradorAdapter.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getCount() {
        return colaboradores.size();
    }

    @Override
    public Colaborador getItem(int posicao) {
        return colaboradores.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return colaboradores.get(posicao).getId();
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        View viewCriada = criaView(viewGroup);
        Colaborador colaborador = colaboradores.get(posicao);
        this.posicao = posicao;
        vincula(viewCriada, colaborador);
        return viewCriada;
    }

    private void vincula(View view, Colaborador colaborador) {
        TextView colaboradorPreenche = view.findViewById(R.id.item_nome_ListViewGenerica);
        colaboradorPreenche.setText(colaborador.getColaborador());
        TextView unidade = view.findViewById(R.id.item_unidade_ListViewGenerica);
        unidade.setText(colaborador.getUnidade());
        configuraItemClique(view, colaborador);
    }

    private void configuraItemClique(@NonNull View itemView, Colaborador colaborador) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener
                        .onItemClick(posicao, colaborador);
            }
        });
    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_cadastro, viewGroup, false);
    }

    public void atualiza(List<Colaborador> colaborador) {
        this.colaboradores.clear();
        this.colaboradores.addAll(colaborador);
        notifyDataSetChanged();
    }

    public void atualiza() {
        this.colaboradores.clear();
        this.colaboradores.addAll(dao.todos());
        notifyDataSetChanged();
    }

    public void remove(Colaborador colaborador) {
        colaboradores.remove(colaborador);
        notifyDataSetChanged();
    }

    public List<Colaborador> getcolaboradores() {
        return colaboradores;
    }

    public interface OnItemClickListener {
        void onItemClick(int posicao, Colaborador colaborador);
    }
}

