package com.control;


import com.connection.AudienciaSQL;
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

import static com.connection.Properties.PATH_CONNECTION;
import static com.connection.Properties.PATH_FILE;

public class ListaAudienciasPG {

    private ConnectionModel connectionModel;

    private static List<String> listaProcessos = new ArrayList();
    private static List<String> listaProcessosNaoIndice = new ArrayList();


    public ListaAudienciasPG() {
        connectionModel = (ConnectionModel) new XMLToObject(new ConnectionModel(), PATH_CONNECTION).instanceObjectParsed();
    }


    private List<String> getListaAudiencias() {

        OracleConnectionControl oracleIND = new OracleConnectionControl(connectionModel.getDatabaseIP(), connectionModel.getDatabaseUser(), connectionModel.getDatabasePasswd(), connectionModel.getDatabaseINDTINT());
        oracleIND.connect();
        ResultSet resultSetIND = oracleIND.executar(new AudienciaSQL().sqlAudienciaIND());
        ArrayList<String> listaAudienciasIND = new ArrayList<String>();
        try {
            while (resultSetIND.next()) {
                String CDPROCESSO = resultSetIND.getString("CDPROCESSO");
                String CDTIPOAUDIENCIA = resultSetIND.getString("CDTIPOAUDIENCIA");
                String NUDOCUMENTO = resultSetIND.getString("NUDOC");
                String DTINICIO = resultSetIND.getString("DTINICIO");
                String DTFIM = resultSetIND.getString("DATAFIM");
                String DTMARCOUAUDIENCIA = resultSetIND.getString("DTMARCOUAUDIENCIA");
                listaAudienciasIND.add(CDPROCESSO + "_" + CDTIPOAUDIENCIA + "_" + NUDOCUMENTO + "_" + DTINICIO + "_" + DTFIM + "_" + DTMARCOUAUDIENCIA);
                System.out.println(listaAudienciasIND.get(listaAudienciasIND.size() - 1));
            }
            oracleIND.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        OracleConnectionControl oraclePG = new OracleConnectionControl(connectionModel.getDatabaseIP(), connectionModel.getDatabaseUser(), connectionModel.getDatabasePasswd(), connectionModel.getDatabasePG5TINT());
        oraclePG.connect();
        ResultSet resultSetPG = oraclePG.executar(new AudienciaSQL().sqlAudienciaPG());
        ArrayList<String> listaAudienciasPG = new ArrayList<String>();
        try {
            while (resultSetPG.next()) {
                String CDPROCESSO = resultSetPG.getString("CDPROCESSO");
                String CDTIPOAUDIENCIA = resultSetPG.getString("CDTIPOAUDIENCIA");
                String NUDOCUMENTO = resultSetPG.getString("NUDOCUMENTO");
                String DTINICIO = resultSetPG.getString("DTINICIO");
                String DTFIM = resultSetPG.getString("DTFIM");
                String DTMARCOUAUDIENCIA = resultSetPG.getString("DTMARCOUAUDIENCIA");
                listaAudienciasPG.add(CDPROCESSO + "_" + CDTIPOAUDIENCIA + "_" + NUDOCUMENTO + "_" + DTINICIO + "_" + DTFIM + "_" + DTMARCOUAUDIENCIA);
                System.out.println(listaAudienciasPG.get(listaAudienciasPG.size() - 1));
            }
            oraclePG.disconnect();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }


        return getDiferencaAudiencias(listaAudienciasPG, listaAudienciasIND);
    }


    private List<String> getDiferencaAudiencias(List<String> listaPG, List<String> listaIND) {

        ArrayList<String> listaDiferencaAudiencias = new ArrayList<String>();
        for (String audiencia : listaPG) {
            if (!listaIND.contains(audiencia)) {
                listaDiferencaAudiencias.add(audiencia);
                String processo = audiencia.split("_")[0];
                if (!listaProcessos.contains(processo)) {
                    listaProcessos.add(processo);
                }

            }




        }

        ArrayList<String> listaTemp = new ArrayList<String>();
        for (String processo : listaIND) {
            listaTemp.add(processo.split("_")[0]);
        }

        for (String processo : listaProcessos) {
            if (!listaTemp.contains(processo)) {
                listaProcessosNaoIndice.add(processo);
            }
        }
        return listaDiferencaAudiencias;
    }


    public void geraLog() {
        List<String> listaAudiencias = new ListaAudienciasPG().getListaAudiencias();
        File file = new File(PATH_FILE);
        file.delete();
        try {
            FileWriter arquivo = new FileWriter(new File(PATH_FILE));
            arquivo.write("CDPROCESSO  CDTIPOAUDIENCIA  NUDOCUMENTO  DTINICIO  DTFIM  DTMARCOUAUDIENCIA" + "\n");
            for (String audiencia : listaAudiencias) {
                System.out.println(audiencia);
                arquivo.write(audiencia + "\n");
            }
            for (String processo : listaProcessos) {
                System.out.println(processo);
                arquivo.write(processo + "\n");
            }
            System.out.println("Filtrada");
            for (String processo : listaProcessosNaoIndice) {
                System.out.println(processo);
                arquivo.write(processo + "\n");
            }
            arquivo.close();
            JOptionPane.showMessageDialog(null, "Log gerado");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }
}
