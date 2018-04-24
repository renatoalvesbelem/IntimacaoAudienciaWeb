package com.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "database")
public class ConnectionModel {

    private String databaseIP;
    private int databasePort;
    private String databaseUser;
    private String databaseServicePG5TINT;
    private String databaseServiceINDTINT;
    private String dataDeCorte;
    private String databasePasswd;



    public String getDatabaseIP() {
        return databaseIP;
    }

    public void setDatabaseIP(String databaseIP) {
        this.databaseIP = databaseIP;
    }
    @XmlElement(name = "databasePort")
    public int getDatabasePort() {
        return databasePort;
    }

    public void setDatabasePort(int databasePort) {
        this.databasePort = databasePort;
    }
    @XmlElement(name = "databaseUser")
    public String getDatabaseUser() {
        return databaseUser;
    }

    public void setDatabaseUser(String databaseUser) {
        this.databaseUser = databaseUser;
    }
    @XmlElement(name = "databaseServicePG5TINT")
    public String getDatabasePG5TINT() {
        return databaseServicePG5TINT;
    }

    public void setDatabasePG5TINT(String databaseServicePG5TINT) {
        this.databaseServicePG5TINT = databaseServicePG5TINT;
    }
    @XmlElement(name = "databaseServiceINDTINT")
    public String getDatabaseINDTINT() {
        return databaseServiceINDTINT;
    }

    public void setDatabaseINDTINT(String databaseServiceINDTINT) {
        this.databaseServiceINDTINT = databaseServiceINDTINT;
    }
    @XmlElement(name = "dataDeCorte")
    public String getDataDeCorte() {
        return dataDeCorte;
    }

    public void setDataDeCorte(String dataDeCorte) {
        this.dataDeCorte = dataDeCorte;
    }

    @XmlElement(name = "databasePasswd")
    public String getDatabasePasswd() {
        return databasePasswd;
    }

    public void setDatabasePasswd(String databasePasswd) {
        this.databasePasswd = databasePasswd;
    }
}
