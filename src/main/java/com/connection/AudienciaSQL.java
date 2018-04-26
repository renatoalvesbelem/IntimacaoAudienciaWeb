package com.connection;

public class AudienciaSQL {
    String sqlAudiencia;
    public String sqlAudienciaPG( ) {
        sqlAudiencia ="SELECT AUDIENCIA.CDAUDIENCIA,CDTIPOAUDIENCIA,AUDIENCIA.CDPROCESSO,PARTE.CDPESSOA,DOC.NUDOCUMENTO,DOC.NUDOCFORMATADO,AUDIENCIA.DTINICIO,DTFIM,DTMARCOUAUDIENCIA " +
                " FROM SAJ.EGGPAUDIENCIA audiencia " +
                " JOIN SAJ.EFPGPARTE parte " +
                " ON audiencia.CDPROCESSO = parte.CDPROCESSO " +
                " JOIN SAJ.ESAJPESSOADOC DOC" +
                " ON parte.CDPESSOA = doc.CDPESSOA" +
                " WHERE audiencia.CDTIPOAUDIENCIA IN (1, 2, 3) " +
                " AND DOC.SGTIPODOCUMENTO = 'OAB'" +
                " AND DOC.FLPRINCIPAL = 'S'" +
                " AND DTFIM >= SYSDATE" +
                " ORDER BY AUDIENCIA.DTINICIO, CDPROCESSO ASC";
        return sqlAudiencia;
    }

    public String sqlAudienciaIND() {
        sqlAudiencia = "SELECT AUDIENCIA.CDPROCESSO,AUDIENCIA.CDTIPOAUDIENCIA,DOC.NUDOC,DOC.NUDOCFORMATADO,AUDIENCIA.DTINICIO,DATAFIM,DTMARCOUAUDIENCIA " +
                " FROM SAJ.EINDAUDIENCIA AUDIENCIA " +
                " JOIN SAJ.EINDDOCUMENTO DOC " +
                " ON AUDIENCIA.CDPROCESSO = DOC.CDPROCESSO" +
                " AND DOC.SGTIPODOCUMENTO = 'OAB'"+
                " AND AUDIENCIA.CDTIPOAUDIENCIA IN (1, 2, 3) " +
                " AND DATAFIM >= SYSDATE"+
                " ORDER BY AUDIENCIA.DTINICIO, AUDIENCIA.CDPROCESSO ASC";
        return sqlAudiencia;
    }
}
