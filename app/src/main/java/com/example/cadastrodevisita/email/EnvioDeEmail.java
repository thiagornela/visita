package com.example.cadastrodevisita.email;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EnvioDeEmail extends AppCompatActivity {
    //Context context;

    public void enviarEmail() {
        final String nome = "Thiago Nome teste";
        final String email = "thiagornela@hotmail.com";
        final String subject = "assunto do email";
        final String body = "corpo do email do " + nome;


        if (!isOnline()) {
            //Toast.makeText(context, "Não estava online para enviar e-mail!", Toast.LENGTH_SHORT).show();
            System.exit(0);
        }


        new Thread(new Runnable() {
            @Override
            public void run() {
                Mail m = new Mail();

                String[] toArr = {email};
                m.setTo(toArr);

                //m.setFrom("seunome@seuemail.com.br"); //caso queira enviar em nome de outro
                m.setSubject(subject);
                m.setBody(body);

                try {
                    //m.addAttachment("pathDoAnexo");//anexo opcional
                    m.send();
                } catch (RuntimeException rex) {
                }//erro ignorado
                catch (Exception e) {
                    e.printStackTrace();
                    System.exit(0);
                }

                //Toast.makeText(getApplicationContext(), "Email enviado!", Toast.LENGTH_SHORT).show();
            }
        }).start();


    }
    private boolean isOnline () {
        try {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return netInfo != null && netInfo.isConnectedOrConnecting();
        } catch (Exception ex) {
            //Toast.makeText(getApplicationContext(), "Erro ao verificar se estava online! (" + ex.getMessage() + ")", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}


