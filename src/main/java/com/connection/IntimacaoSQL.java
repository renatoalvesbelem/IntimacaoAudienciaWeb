package com.connection;

public class IntimacaoSQL {

    public String sqlIntimacaoPG(String data) {
        String sqlIntimacoes = "SELECT INT.CDPROCESSO,  " +
                "       ROW_NUMBER() OVER (PARTITION BY INT.CDPROCESSO ORDER BY INT.CDPROCESSO, INT.DTINICIOPRAZO) AS NUSEQINTIMACAO,  " +
                "       NVL(INT.NUDIASPRAZO, 0) AS NUDIASPRAZO,  " +
                "       INT.DTINICIOPRAZO,  " +
                "       INT.DTFIMPRAZO,  " +
                "       INT.NUSEQPROCESSOMV,  " +
                "       CASE WHEN PUN.CDUSUARIONET IS NULL THEN -999 ELSE PUN.CDUSUARIONET END AS CDUSUARIONET,  " +
                "       PES.NUDOCUMENTO,  " +
                "       PMV.CDTIPOMVPROCESSO,  " +
                "       PMV.DTMOVIMENTO,  " +
                "       PMV.DECOMPLEMENTOMV,  " +
                "       'N' AS FLCUMPRIDO,  " +
                "       'SAJ' AS CDUSUINCLUSAO,  " +
                "       SYSDATE AS DTUSUINCLUSAO,  " +
                "       SYSDATE AS DTREGISTRO  " +
                "FROM SAJ.EFPGPUBINTIMACAO INT  " +
                "LEFT JOIN SAJ.EFPGPESSOAUSUNET PUN ON PUN.CDPESSOA = INT.CDPESSOAADV  " +
                "JOIN SAJ.ESAJPESSOADOC PES ON PES.CDPESSOA = INT.CDPESSOAADV AND PES.SGTIPODOCUMENTO = 'OAB' AND PES.FLPRINCIPAL = 'S'  " +
                "JOIN SAJ.EFPGPROCESSOMV PMV ON PMV.CDPROCESSO = INT.CDPROCESSO AND PMV.NUSEQPROCESSOMV = INT.NUSEQPROCESSOMV  " +
                "WHERE INT.DTFIMPRAZO > TO_DATE('"+data+" 00:00:01', 'DD/MM/YYYY HH24:MI:SS')   " +
                "ORDER BY INT.CDPROCESSO, INT.DTINICIOPRAZO";
        return sqlIntimacoes;
    }

    public String sqlIntimacaoIND(String data) {
        String sqlIntimacoes = "SELECT CDPROCESSO,NUSEQPROCESSOMV,NUDOCOAB FROM SAJ.EINDINTIMACAO WHERE DTFIMPRAZO > TO_DATE('"+data+" 00:00:01', 'DD/MM/YYYY HH24:MI:SS')   ";
        return sqlIntimacoes;
    }
}
