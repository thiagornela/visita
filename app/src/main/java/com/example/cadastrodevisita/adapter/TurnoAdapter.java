package com.example.cadastrodevisita.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.dao.TurnoDAO;
import com.example.cadastrodevisita.model.Turno;

import java.util.ArrayList;
import java.util.List;

public class TurnoAdapter extends BaseAdapter {

    private List<Turno> turnos = new ArrayList<>();
    private final Context context;
    private final TurnoAdapter.OnItemClickListener onItemClickListener;
    private int posicao;
    private TurnoDAO dao = new TurnoDAO();

    public TurnoAdapter(Context context, TurnoAdapter.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getCount() {
        return turnos.size();
    }

    @Override
    public Turno getItem(int posicao) {
        return turnos.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return turnos.get(posicao).getId();
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        View viewCriada = criaView(viewGroup);
        Turno turno = turnos.get(posicao);
        this.posicao = posicao;
        vincula(viewCriada, turno);
        return viewCriada;
    }

    private void vincula(View view, Turno turno) {
        TextView turnoPreenche = view.findViewById(R.id.item_nome_ListViewGenerica);
        turnoPreenche.setText(turno.getTurno());
        TextView unidade = view.findViewById(R.id.item_unidade_ListViewGenerica);
        unidade.setText(turno.getUnidade());
        configuraItemClique(view, turno);
    }

    private void configuraItemClique(@NonNull View itemView, Turno turno) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener
                        .onItemClick(posicao, turno);
            }
        });
    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_cadastro, viewGroup, false);
    }

    public void atualiza(List<Turno> turno) {
        this.turnos.clear();
        this.turnos.addAll(turno);
        notifyDataSetChanged();
    }

    public void atualiza() {
        this.turnos.clear();
        this.turnos.addAll(dao.todos());
        notifyDataSetChanged();
    }

    public void remove(Turno turno) {
        turnos.remove(turno);
        notifyDataSetChanged();
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    public interface OnItemClickListener {
        void onItemClick(int posicao, Turno turno);
    }
}
