package com.example.cadastrodevisita.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.model.Unidade;
import com.example.cadastrodevisita.model.Unidade;

import java.util.List;

public class UnidadeAdapter extends BaseAdapter {

    private final List<Unidade> unidades;
    private final Context context;

    public List<Unidade> getUnidades() {
        return unidades;
    }

    public Context getContext() {
        return context;
    }

    public UnidadeAdapter(List<Unidade> unidades, Context context) {
        this.unidades = unidades;
        this.context = context;
    }

    @Override
    public int getCount() {
        return unidades.size();
    }

    @Override
    public Object getItem(int position) {
        return unidades.get(position);
    }

    @Override
    public long getItemId(int position) {
        return unidades.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewCriada = criaView(parent);
        Unidade unidadeDevolvida = unidades.get(position);
        vincula(viewCriada, unidadeDevolvida);
        return viewCriada;
    }

    private void vincula(View view, Unidade unidade) {
        TextView unidadeCadastrada = view.findViewById(R.id.item_unidade);
        unidadeCadastrada.setText(unidade.getUnidade());
    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_unidade, viewGroup, false);
    }

    public void atualiza(List<Unidade> unidades){
        this.unidades.clear();
        this.unidades.addAll(unidades);
        notifyDataSetChanged();
    }

    public void remove(Unidade mensagem) {
        unidades.remove(mensagem);
        notifyDataSetChanged();
    }
}
