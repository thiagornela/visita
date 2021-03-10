package com.example.cadastrodevisita.dao;

import androidx.annotation.Nullable;

import com.example.cadastrodevisita.model.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private final static ArrayList<Categoria> categorias = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void salva(Categoria categoria) {
        categoria.setId(contadorDeIds);
        categorias.add(categoria);
        atualizaIds();
    }

    public void edita(Categoria categoria) {
        Categoria categoriaEncontrada = buscaPeloId(categoria);
        if (categoriaEncontrada != null) {
            int posicao = categorias.indexOf(categoriaEncontrada);
            categorias.set(posicao, categoria);
        }
    }

    private void atualizaIds() {
        contadorDeIds++;
    }

    public List<Categoria> todos() {
        return new ArrayList<>(categorias);
    }

    public void remove(Categoria categoria) {
        Categoria categoriaDevolvida = buscaPeloId(categoria);
        if(categoriaDevolvida != null){
            categorias.remove(categoriaDevolvida);
        }
    }

    public void removeComPosicao(int posicao) {
        categorias.remove(posicao);
    }

    @Nullable
    private Categoria buscaPeloId(Categoria categoria) {
        for (Categoria a :
                categorias) {
            if (a.getId() == categoria.getId()) {
                return a;
            }
        }
        return null;
    }
}
