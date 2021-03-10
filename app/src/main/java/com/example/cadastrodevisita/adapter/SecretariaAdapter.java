package com.example.cadastrodevisita.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.dao.SecretariaDAO;
import com.example.cadastrodevisita.model.Secretaria;

import java.util.ArrayList;
import java.util.List;

public class SecretariaAdapter extends BaseAdapter {

    private List<Secretaria> secretarias = new ArrayList<>();
    private final Context context;
    private final SecretariaAdapter.OnItemClickListener onItemClickListener;
    private int posicao;
    private SecretariaDAO dao = new SecretariaDAO();

    public SecretariaAdapter(Context context, SecretariaAdapter.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getCount() {
        return secretarias.size();
    }

    @Override
    public Secretaria getItem(int posicao) {
        return secretarias.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return secretarias.get(posicao).getId();
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        View viewCriada = criaView(viewGroup);
        Secretaria secretaria = secretarias.get(posicao);
        this.posicao = posicao;
        vincula(viewCriada, secretaria);
        return viewCriada;
    }

    private void vincula(View view, Secretaria secretaria) {
        TextView secretariaPreenche = view.findViewById(R.id.item_nome_ListViewGenerica);
        secretariaPreenche.setText(secretaria.getSecretaria());
        TextView unidade = view.findViewById(R.id.item_unidade_ListViewGenerica);
        unidade.setText(secretaria.getUnidade());
        configuraItemClique(view, secretaria);
    }

    private void configuraItemClique(@NonNull View itemView, Secretaria secretaria) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener
                        .onItemClick(posicao, secretaria);
            }
        });
    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_cadastro, viewGroup, false);
    }

    public void atualiza(List<Secretaria> secretaria) {
        this.secretarias.clear();
        this.secretarias.addAll(secretaria);
        notifyDataSetChanged();
    }

    public void atualiza() {
        this.secretarias.clear();
        this.secretarias.addAll(dao.todos());
        notifyDataSetChanged();
    }

    public void remove(Secretaria secretaria) {
        secretarias.remove(secretaria);
        notifyDataSetChanged();
    }

    public List<Secretaria> getSecretarias() {
        return secretarias;
    }

    public interface OnItemClickListener {
        void onItemClick(int posicao, Secretaria secretaria);
    }
}

