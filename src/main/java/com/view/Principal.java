package com.view;

import com.control.ListaAudienciasPG;
import com.control.ListaIntimacoesPG;
import com.model.XmlExemploModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal {

    private static JButton btGerarXML, btGerarLogIntimacaoes, btGerarLogAudiencia;


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

    btGerarLogIntimacaoes =new JButton("Gerar Log\n intimacões");
    btGerarLogIntimacaoes.setSize(new Dimension(150,40));
    btGerarLogIntimacaoes.setLocation(1,50);
    btGerarLogIntimacaoes.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            new ListaIntimacoesPG().geraLog();
        }
    } );

    btGerarLogAudiencia =new JButton("Gerar Log\n Audiência");
    btGerarLogAudiencia.setSize(new Dimension(150,40));
    btGerarLogAudiencia.setLocation(1,100);
    btGerarLogAudiencia.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            new ListaAudienciasPG().geraLog();
        }
    } );

    jPanel.add(btGerarXML);
    jPanel.add(btGerarLogIntimacaoes);
    jPanel.add(btGerarLogAudiencia);
    jPanel.setLayout(null);
    jFrame.add(jPanel);
    jFrame.show();
    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }
}
