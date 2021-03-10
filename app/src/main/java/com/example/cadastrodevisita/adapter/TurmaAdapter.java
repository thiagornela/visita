package com.example.cadastrodevisita.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.dao.TurmaDAO;
import com.example.cadastrodevisita.model.Turma;

import java.util.ArrayList;
import java.util.List;

public class TurmaAdapter extends BaseAdapter {

    private List<Turma> turmas = new ArrayList<>();
    private final Context context;
    private final TurmaAdapter.OnItemClickListener onItemClickListener;
    private int posicao;
    private TurmaDAO dao = new TurmaDAO();

    public TurmaAdapter(Context context, TurmaAdapter.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getCount() {
        return turmas.size();
    }

    @Override
    public Turma getItem(int posicao) {
        return turmas.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return turmas.get(posicao).getId();
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        View viewCriada = criaView(viewGroup);
        Turma turma = turmas.get(posicao);
        this.posicao = posicao;
        vincula(viewCriada, turma);
        return viewCriada;
    }

    private void vincula(View view, Turma turma) {
        TextView turmaPreenche = view.findViewById(R.id.item_nome_ListViewGenerica);
        turmaPreenche.setText(turma.getTurma());
        TextView unidade = view.findViewById(R.id.item_unidade_ListViewGenerica);
        unidade.setText(turma.getUnidade());
        configuraItemClique(view, turma);
    }

    private void configuraItemClique(@NonNull View itemView, Turma turma) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener
                        .onItemClick(posicao, turma);
            }
        });
    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_cadastro, viewGroup, false);
    }

    public void atualiza(List<Turma> turma) {
        this.turmas.clear();
        this.turmas.addAll(turma);
        notifyDataSetChanged();
    }

    public void atualiza() {
        this.turmas.clear();
        this.turmas.addAll(dao.todos());
        notifyDataSetChanged();
    }

    public void remove(Turma turma) {
        turmas.remove(turma);
        notifyDataSetChanged();
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public interface OnItemClickListener {
        void onItemClick(int posicao, Turma turma);
    }
}
