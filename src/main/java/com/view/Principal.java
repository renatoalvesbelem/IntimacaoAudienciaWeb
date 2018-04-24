package com.view;

import com.control.ListaIntimacoesPG;
import com.model.XmlExemploModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal {

    private static JButton btGerarXML, gerarLogIntimacaoes;


public static void main(String args[]){
    JFrame jFrame = new JFrame();
    jFrame.setSize(new Dimension(600,400));
    JPanel jPanel = new JPanel();
    jPanel.setSize(new Dimension(600,400));

    btGerarXML = new JButton("Gerar\nXML Exemplo");
    btGerarXML.setSize(new Dimension(150,40));
    btGerarXML.setLocation(1,1);
    btGerarXML.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            new XmlExemploModel().gerarXMlExemplo();
        }
    } );

    gerarLogIntimacaoes =new JButton("Gerar Log\n intimacões");
    gerarLogIntimacaoes.setSize(new Dimension(150,40));
    gerarLogIntimacaoes.setLocation(1,50);
    gerarLogIntimacaoes.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            new ListaIntimacoesPG().geraLog();
        }
    } );

    jPanel.add(btGerarXML);
    jPanel.add(gerarLogIntimacaoes);
    jPanel.setLayout(null);
    jFrame.add(jPanel);
    jFrame.show();
    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }
}
