package com.example.cadastrodevisita.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.dao.CategoriaDAO;
import com.example.cadastrodevisita.model.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaAdapter extends BaseAdapter {

    private List<Categoria> categorias = new ArrayList<>();
    private final Context context;
    private final CategoriaAdapter.OnItemClickListener onItemClickListener;
    private int posicao;
    private CategoriaDAO dao = new CategoriaDAO();

    public CategoriaAdapter(Context context, CategoriaAdapter.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getCount() {
        return categorias.size();
    }

    @Override
    public Categoria getItem(int posicao) {
        return categorias.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return categorias.get(posicao).getId();
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        View viewCriada = criaView(viewGroup);
        Categoria categoria = categorias.get(posicao);
        this.posicao = posicao;
        vincula(viewCriada, categoria);
        return viewCriada;
    }

    private void vincula(View view, Categoria categoria) {
        TextView categoriaPreenche = view.findViewById(R.id.item_nome_ListViewGenerica);
        categoriaPreenche.setText(categoria.getCategoria());
        TextView unidade = view.findViewById(R.id.item_unidade_ListViewGenerica);
        unidade.setText(categoria.getUnidade());
        configuraItemClique(view, categoria);
    }

    private void configuraItemClique(@NonNull View itemView, Categoria categoria) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener
                        .onItemClick(posicao, categoria);
            }
        });
    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_cadastro, viewGroup, false);
    }

    public void atualiza(List<Categoria> categoria) {
        this.categorias.clear();
        this.categorias.addAll(categoria);
        notifyDataSetChanged();
    }

    public void atualiza() {
        this.categorias.clear();
        this.categorias.addAll(dao.todos());
        notifyDataSetChanged();
    }

    public void remove(Categoria categoria) {
        categorias.remove(categoria);
        notifyDataSetChanged();
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public interface OnItemClickListener {
        void onItemClick(int posicao, Categoria categoria);
    }
}

