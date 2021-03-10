package com.example.cadastrodevisita.formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormataData {

    public String formataStringParaBrasileiro(String data) throws ParseException {
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        Date DataFormatada = formatador.parse(data);
        return formatador.format(DataFormatada);
    }
}
