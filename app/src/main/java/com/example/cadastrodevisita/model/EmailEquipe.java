package com.example.cadastrodevisita.model;

import java.io.Serializable;
import java.util.ArrayList;

public class EmailEquipe implements Serializable {

    private String email;
    private String Unidade;
    private ArrayList<String> unidadeEmail;
    private int id = 0;

//    public EmailEquipe(int id, String email, ArrayList<String> unidadeEmail) {
//        this.email = email;
//        this.unidadeEmail = unidadeEmail;
//        this.id = id;
//    }

    public ArrayList<String> getUnidadeEmail() {
        return unidadeEmail;
    }

    public void setUnidadeEmail(ArrayList<String> unidadeEmail) {
        this.unidadeEmail = unidadeEmail;
    }

    public String getUnidade() {
        return Unidade;
    }

    public EmailEquipe(int id, String email, String unidade) {
        this.email = email;
        Unidade = unidade;
        this.id = id;
    }

    public void setUnidade(String unidade) {
        Unidade = unidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EmailEquipe(String email) {
        this.email = email;
    }

//    public void RetornaUnidadesDoEmail() {
//        String item = "";
//        for (int i = 0; i < unidadeEmail.size(); i++) {
//            item = item + unidadeEmail[mUserItems.get(i)];
//            if (i != mUserItems.size() - 1) {
//                item = item + ", ";
//            }
//            mItemSelected.setText(item);
//        }
//    }
}
