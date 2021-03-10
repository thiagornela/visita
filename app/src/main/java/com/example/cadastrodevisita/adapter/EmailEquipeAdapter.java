package com.example.cadastrodevisita.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.dao.EmailEquipeDAO;
import com.example.cadastrodevisita.model.EmailEquipe;

import java.util.ArrayList;
import java.util.List;

public class EmailEquipeAdapter extends BaseAdapter {

    private List<EmailEquipe> emailEquipes = new ArrayList<>();
    private final Context context;
    private final OnItemClickListener onItemClickListener;
    private int posicao;
    private EmailEquipeDAO dao = new EmailEquipeDAO();

    public EmailEquipeAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getCount() {
        return emailEquipes.size();
    }

    @Override
    public EmailEquipe getItem(int posicao) {
        return emailEquipes.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return emailEquipes.get(posicao).getId();
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        View viewCriada = criaView(viewGroup);
        EmailEquipe emailDevolvido = emailEquipes.get(posicao);
        this.posicao = posicao;
        vincula(viewCriada, emailDevolvido);
        return viewCriada;
    }

    private void vincula(View view, EmailEquipe emailEquipe) {
        TextView email = view.findViewById(R.id.item_nome_ListViewGenerica);
        email.setText(emailEquipe.getEmail());
        TextView unidade = view.findViewById(R.id.item_unidade_ListViewGenerica);
        unidade.setText(emailEquipe.getUnidade());
        //this.emailEquipe = emailEquipe;
        configuraItemClique(view, emailEquipe);
    }

    private void configuraItemClique(@NonNull View itemView, EmailEquipe emailEquipe) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener
                        .onItemClick(posicao, emailEquipe);
            }
        });
    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_cadastro, viewGroup, false);
    }

    public void atualiza(List<EmailEquipe> emailEquipes) {
        this.emailEquipes.clear();
        this.emailEquipes.addAll(emailEquipes);
        notifyDataSetChanged();
    }

    public void atualiza() {
        this.emailEquipes.clear();
        this.emailEquipes.addAll(dao.todos());
        notifyDataSetChanged();
    }

    public void remove(EmailEquipe emailEquipe) {
        emailEquipes.remove(emailEquipe);
        notifyDataSetChanged();
    }

    public List<EmailEquipe> getEmailEquipes() {
        return emailEquipes;
    }

    public interface OnItemClickListener {
        void onItemClick(int posicao, EmailEquipe emailEquipe);
    }
}

