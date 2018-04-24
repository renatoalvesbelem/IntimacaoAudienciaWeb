package com.model;

import com.connection.Properties;
import com.util.ObjectToXML;

import javax.swing.*;

import java.io.File;


public class XmlExemploModel {

    public void gerarXMlExemplo(){
        ConnectionModel connectionModel =  new ConnectionModel();
        connectionModel.setDatabaseIP("192.168.225.172");
        connectionModel.setDatabasePort(1521);
        connectionModel.setDatabaseUser("saj");
        connectionModel.setDatabasePasswd("agesune1");
        connectionModel.setDatabasePG5TINT("PG5TINT");
        connectionModel.setDatabaseINDTINT("INTTING");
        connectionModel.setDataDeCorte("02/03/2018");
        new ObjectToXML().createXML(connectionModel,Properties.PATH_CONNECTION);
        if (new File(Properties.PATH_CONNECTION).exists()){
            JOptionPane.showMessageDialog(null,"Arquivo de exemplo Gerado");
        }

    }
}
