package com.control;


import com.connection.IntimacaoSQL;
import com.connection.OracleConnectionControl;
import com.model.ConnectionModel;
import com.util.XMLToObject;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.connection.Properties.*;
public class ListaIntimacoesPG {
    private ConnectionModel connectionModel;

    public ListaIntimacoesPG() {
        connectionModel = (ConnectionModel) new XMLToObject(new ConnectionModel(), PATH_CONNECTION).instanceObjectParsed();
    }

   private List<String> getDiferencaIntimacoes(List<String> listaPG, List<String> listaIND) {

        ArrayList<String> listaDiferencaIntimacoes = new ArrayList<String>();
        for (String intimacao : listaPG) {
            if (!listaIND.contains(intimacao)) {
                listaDiferencaIntimacoes.add(intimacao);
            }

        }
        return listaDiferencaIntimacoes;
    }

    private List<String> getListaIntimacoes() {
        OracleConnectionControl oraclePG = new OracleConnectionControl(connectionModel.getDatabaseIP(), connectionModel.getDatabaseUser(), connectionModel.getDatabasePasswd(), connectionModel.getDatabasePG5TINT());

        oraclePG.connect();
        ResultSet resultSetPG = oraclePG.executar(new IntimacaoSQL().sqlIntimacaoPG(connectionModel.getDataDeCorte()));
        ArrayList<String> listaIntimacoesPG = new ArrayList<String>();
        try {
            while (resultSetPG.next()) {
                String CDPROCESSO = resultSetPG.getString("CDPROCESSO");
                String NUSEQPROCESSOMV = resultSetPG.getString("NUSEQPROCESSOMV");
                String NUDOCUMENTO = resultSetPG.getString("NUDOCUMENTO");
                listaIntimacoesPG.add(CDPROCESSO + " " + NUSEQPROCESSOMV + " " + NUDOCUMENTO);
            }
            oraclePG.disconnect();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }


        OracleConnectionControl oracleIND = new OracleConnectionControl(connectionModel.getDatabaseIP(), connectionModel.getDatabaseUser(), connectionModel.getDatabasePasswd(), connectionModel.getDatabaseINDTINT());
        oracleIND.connect();
        ResultSet resultSetIND = oracleIND.executar(new IntimacaoSQL().sqlIntimacaoIND(connectionModel.getDataDeCorte()));
        ArrayList<String> listaIntimacoesIND = new ArrayList<String>();
        try {
            while (resultSetIND.next()) {
                String CDPROCESSO = resultSetIND.getString("CDPROCESSO");
                String NUSEQPROCESSOMV = resultSetIND.getString("NUSEQPROCESSOMV");
                String NUDOCOAB = resultSetIND.getString("NUDOCOAB");
                listaIntimacoesIND.add(CDPROCESSO + " " + NUSEQPROCESSOMV + " " + NUDOCOAB);
            }
            oracleIND.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return getDiferencaIntimacoes(listaIntimacoesPG, listaIntimacoesIND);
    }


    public void geraLog() {
        List<String> listaIntimacoes = new ListaIntimacoesPG().getListaIntimacoes();
        File file = new File(PATH_FILE);
            file.delete();
            try {
                FileWriter arquivo = new FileWriter(new File(PATH_FILE));
                arquivo.write("CDPROCESSO NUSEQPROCESSOMV NUDOCOAB"+"\n");
                for (String intimacao : listaIntimacoes) {
                    System.out.println(intimacao);
                    arquivo.write(intimacao+"\n");
                }
                arquivo.close();
                JOptionPane.showMessageDialog(null,"Log gerado");

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }

    }
}
