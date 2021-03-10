package com.example.cadastrodevisita.adapter;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.telephony.PhoneNumberUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.formatter.FormataData;
import com.example.cadastrodevisita.model.Visita;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.cadastrodevisita.ui.Constantes.SITUACAO_AMBIENTACAO_PARA;
import static com.example.cadastrodevisita.ui.Constantes.SITUACAO_CONTATO_ESCOLA_PARA;

public class VisitasAdapter extends RecyclerView.Adapter<VisitasAdapter.VisitaViewHolder> {

    private final List<Visita> visitas;
    private final Context context;
    private OnItemClickListener onItemClickListener;
    private String unidade = "";
    private String dataInicial = "";
    private String dataFinal = "";
    private String crianca = "";
    private String responsavel = "";
    private String turma = "";
    private String turno = "";

    public VisitasAdapter(List<Visita> visitas, Context context) {
        this.visitas = visitas;
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public VisitasAdapter.VisitaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context)
                .inflate(R.layout.item_visita, parent, false);
        return new VisitaViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(VisitasAdapter.VisitaViewHolder holder, int position) {
        Visita visita = visitas.get(position);
        try {
            holder.vincula(visita);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return visitas.size();
    }

    @Override
    public long getItemId(int posicao) {
        return visitas.get(posicao).getId();
    }

    public void atualiza(List<Visita> visitas) {
        List<Visita> visitas_filtradas = new ArrayList<>();
        for (int i = 0; i < visitas.size(); i++) {
            if (visitas.get(i).getSituacao().equals(SITUACAO_CONTATO_ESCOLA_PARA) || visitas.get(i).getSituacao().equals(SITUACAO_AMBIENTACAO_PARA)) {
                visitas_filtradas.add(visitas.get(i));
            }
        }
        this.visitas.clear();
        this.visitas.addAll(visitas_filtradas);
        notifyDataSetChanged();
    }

    public void buscadorVisitas(String unidade, String dataInicial, String dataFinal, String crianca, String responsavel, String turma, String turno, List<Visita> visitas) throws ParseException {
        this.unidade = unidade;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.crianca = crianca;
        this.responsavel = turma;
        this.turno = turno;
        List<Visita> visitas_filtradas = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataInicialFormatada = sdf.parse(dataInicial);
        Date dataFinalFormatada = sdf.parse(dataFinal);

        for (int i = 0; i < visitas.size(); i++) {
            if (visitas.get(i).getUnidade().equals(unidade) && visitas.get(i).getNome_crianca().contains(crianca) && visitas.get(i).getNome_responsavel_1().contains(responsavel)) {
                Date dataDoCadastroFormatada = sdf.parse(visitas.get(i).getDataCadastro());
                if (dataDoCadastroFormatada.before(dataInicialFormatada) || dataDoCadastroFormatada.after(dataFinalFormatada)) {
                    this.visitas.clear();
                    notifyDataSetChanged();
                } else if (turma.equals("")) {
                    visitas_filtradas.add(visitas.get(i));
                } else if (visitas.get(i).getTurma_crianca().equals(turma) && visitas.get(i).getTurno_crianca().equals(turno)) {
                    visitas_filtradas.add(visitas.get(i));
                }
            }
        }
        this.visitas.clear();
        this.visitas.addAll(visitas_filtradas);
        notifyDataSetChanged();
    }


    public void atualizaActivityPesquisa(List<Visita> visitas) {
        try {
            buscadorVisitas(unidade, dataInicial, dataFinal, crianca, responsavel, turma, turno, visitas);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void buscaFiltro(String unidade, String secretaria, String situacao, List<Visita> visitas) {
        List<Visita> visitas_filtradas = new ArrayList<>();
        for (int i = 0; i < visitas.size(); i++) {
            if (visitas.get(i).getUnidade().contains(unidade) && visitas.get(i).getSecretaria().contains(secretaria) && visitas.get(i).getSituacao().contains(situacao)){
            //&& (visitas.get(i).getSituacao().equals(SITUACAO_CONTATO_ESCOLA_PARA) || visitas.get(i).getSituacao().equals(SITUACAO_AMBIENTACAO_PARA))) {
                visitas_filtradas.add(visitas.get(i));
            }
        }
        this.visitas.clear();
        this.visitas.addAll(visitas_filtradas);
        notifyDataSetChanged();
    }

    public List<Visita> getVisitas() {
        return visitas;
    }

    public void removeTudo() {
        visitas.clear();
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    class VisitaViewHolder extends RecyclerView.ViewHolder {

        FormataData dataBrasileira = new FormataData();

        private String celular;
        private String fixo;
        private final ImageView imagemFamilia;
        private final TextView unidade;
        private final TextView secretaria;
        private final TextView data_cadastro;
        private final TextView nome_crianca;
        private final TextView turma;
        private final TextView turno;
        private final TextView nome_responsavel1;
        private final TextView telefone_fixo_responsavel1;
        private final TextView telefone_celular_responsavel1;
        private final TextView email_responsavel1;
        private final TextView situacao;
        private final TextView data_agendada;
        private final Switch temIrmao;
        private final View barra_indicadora;
        private Visita visita;

        public VisitaViewHolder(@NonNull View itemView) {
            super(itemView);
            imagemFamilia = itemView.findViewById(R.id.item_foto_familia);
            unidade = itemView.findViewById(R.id.item_visita_unidade);
            secretaria = itemView.findViewById(R.id.item_secretaria);
            data_cadastro = itemView.findViewById(R.id.item_data_do_cadastro);
            nome_crianca = itemView.findViewById(R.id.item_visita_nome_crianca);
            turma = itemView.findViewById(R.id.item_visita_turma);
            turno = itemView.findViewById(R.id.item_visita_turno);
            nome_responsavel1 = itemView.findViewById(R.id.item_visita_nome_responsavel_1);
            telefone_fixo_responsavel1 = itemView.findViewById(R.id.item_visita_telefone_fixo);
            telefone_celular_responsavel1 = itemView.findViewById(R.id.item_visita_telefone_celular);
            email_responsavel1 = itemView.findViewById(R.id.item_visita_email_responsavel_1);
            situacao = itemView.findViewById(R.id.item_visita_situacao);
            data_agendada = itemView.findViewById(R.id.item_data_agendada);
            temIrmao = itemView.findViewById(R.id.item_switch_tem_irmao);
            barra_indicadora = itemView.findViewById(R.id.item_barra_indicadora_da_situacao);
            itemView.setOnClickListener(v -> {
                onItemClickListener.OnItemClick(visita,getAdapterPosition());
            });

        }

        private void vincula(Visita visita) throws ParseException {
            this.visita = visita;
            //if (visita.getFoto_familia() != null){
                imagemFamilia.setImageBitmap(visita.getFoto_familia());
            unidade.setText(visita.getUnidade());
            secretaria.setText(visita.getSecretaria());
            data_cadastro.setText(dataBrasileira.formataStringParaBrasileiro(visita.getDataCadastro()));
            nome_crianca.setText(visita.getNome_crianca());
            turma.setText(visita.getTurma_crianca());
            turno.setText(visita.getTurno_crianca());
            nome_responsavel1.setText(visita.getNome_responsavel_1());
            telefone_fixo_responsavel1.setText(visita.getTelefone_fixo_responsavel_1());
            telefone_celular_responsavel1.setText(visita.getTelefone_celular_responsavel_1());
            email_responsavel1.setText(visita.getEmail_responsavel_1());
            situacao.setText(visita.getSituacao());
            data_agendada.setText(dataBrasileira.formataStringParaBrasileiro(visita.getDataAgendada()));
            SeTemIrmao(visita);
            DataAgendadaAparece(visita, data_agendada);
            verificaDataMudaCor(visita);
            abreDialogContatoCelular(visita, telefone_celular_responsavel1);
            abreDialogContatoFixo(visita, telefone_fixo_responsavel1);
            abreAplicativoParaEnviarEmail(visita, email_responsavel1);
            SeFixoNulo(itemView, visita, telefone_fixo_responsavel1);
        }

        private void DataAgendadaAparece(Visita visita, TextView data_agendada) {
            if (visita.getSituacao().equals(SITUACAO_CONTATO_ESCOLA_PARA) || visita.getSituacao().equals(SITUACAO_AMBIENTACAO_PARA)) {
                data_agendada.setVisibility(View.VISIBLE);
            }
        }

        private void abreDialogContatoCelular(Visita visita, TextView telefone_celular_responsavel1) {
            telefone_celular_responsavel1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    celular = visita.getTelefone_celular_responsavel_1();
                    abreEntrarEmContato(celular);
                }
            });
        }

        private void abreDialogContatoFixo(Visita visita, TextView telefone_fixo_responsavel1) {
            telefone_fixo_responsavel1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fixo = visita.getTelefone_fixo_responsavel_1();
                    abreEntrarEmContato(fixo);
                }
            });
        }

        private void abreAplicativoParaEnviarEmail(Visita visita, TextView email_responsavel1) {
            email_responsavel1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("mailto:" + visita.getEmail_responsavel_1()));
                    context.startActivity(intent);
                }
            });
        }

        private void SeTemIrmao(Visita visita) {
            if (visita.getTemIrmao())
                temIrmao.setVisibility(View.VISIBLE);
        }

        private void SeFixoNulo(View view, Visita visita, TextView telefone_fixo_responsavel1) {
            if (visita.getTelefone_fixo_responsavel_1() != null) {
                telefone_fixo_responsavel1.setText(visita.getTelefone_fixo_responsavel_1());
            } else {
                telefone_fixo_responsavel1.setVisibility(View.GONE);
                view.findViewById(R.id.item_tracinho).setVisibility(View.GONE);
            }
        }

        private void verificaDataMudaCor(Visita visita) throws ParseException {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataAgendadaFormatada = sdf.parse(visita.getDataAgendada());
            String DataAtualString = sdf.format(cal.getTime());
            Date dataAtualFormatada = sdf.parse(DataAtualString);
            mudarCor(visita, dataAgendadaFormatada, dataAtualFormatada, barra_indicadora);
        }

        private void mudarCor(Visita visita, Date dataAgendadaFormatada, Date dataAtualFormatada, View barra_indicadora) {
            if (visita.getSituacao().equals(SITUACAO_CONTATO_ESCOLA_PARA) || visita.getSituacao().equals(SITUACAO_AMBIENTACAO_PARA)) {
                if (dataAtualFormatada.equals(dataAgendadaFormatada)) {
                    barra_indicadora.setBackgroundColor(Color.parseColor("#FA0101"));
                } else if (dataAtualFormatada.after(dataAgendadaFormatada)) {
                    barra_indicadora.setBackgroundColor(Color.parseColor("#FA0101"));
                } else if (dataAtualFormatada.before(dataAgendadaFormatada)) {
                    barra_indicadora.setBackgroundColor(Color.parseColor("#FFE500"));
                }
                //notifyDataSetChanged();
            }
        }

        private void abreEntrarEmContato(String celular) {
            if (!celular.isEmpty()) {
                dialogContato(celular);
            } else {
                Toast.makeText(context, "Número indisponível", Toast.LENGTH_SHORT).show();
            }
        }

        private void dialogContato(String celular) {
            String celularSemFormato = celular.replace(" ", "").replace("-", "").replace("(", "").replace(")", "");
            StringBuffer celularContato = new StringBuffer(celularSemFormato);
            AlertDialog.Builder builder = new AlertDialog.Builder(context).setTitle("Entrar em contato")
                    .setMessage("O que você  gostaria de fazer?")
                    .setPositiveButton("WhatsApp", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            entrarEmContatoWhatsApp(celularContato);
                        }
                    }).setNegativeButton("Ligar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            entrarEmContatoLigacao(celularContato);
                        }
                    });

            builder.show();
        }

        private void entrarEmContatoLigacao(StringBuffer celularContato) {
            Uri url = Uri.parse("tel:" + celularContato.insert(0, "0"));
            Intent intent = new Intent(Intent.ACTION_DIAL, url);
            context.startActivity(intent);
        }

        private void entrarEmContatoWhatsApp(StringBuffer celularContato) {
            celularContato.deleteCharAt(2);

            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
            intent.putExtra("jid", PhoneNumberUtils.stripSeparators("55" + celularContato) + "@s.whatsapp.net");
            context.startActivity(intent);
        }
    }

}