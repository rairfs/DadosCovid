import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;

public class Dados implements Comparable{
    private DateFormat dtFormat = new SimpleDateFormat("dd/MM/yyyy");
    public Calendar data = Calendar.getInstance();
    public Integer testesRealizados;
    public Integer testesNegativados;
    public Integer testesConfirmados;
    public Integer casosDia;
    public Integer masculino;
    public Integer feminino;
    public Integer obitos;
    public Integer isolamentoDomiciliar;
    public Integer altaIsolamentoDomiciliar;
    public Integer leitosUTIPublicoPrivado;
    public Integer leitosUTIPublico;
    public Integer leitosUTIPrivado;
    public Integer leitosEnfermariaPublicoPrivado;
    public Integer leitosEnfermariaPublico;
    public Integer leitosEnfermariaPrivado;
    public Integer internadosPublicoPrivado;
    public Integer ocupacaoUTIPublico;
    public Integer ocupacaoEnfermariaPublico;
    public Integer ocupacaoUTIPrivado;
    public Integer ocupacaoEnfermariaPrivado;

    public Dados(String dt, Integer testR, Integer testN, Integer testC, Integer casosD, Integer masc, Integer fem, Integer obit,
    Integer isolD, Integer altaIsolD, Integer leitosUPP, Integer leitosUPub, Integer leitosUPriv, Integer leitosEnfPP, Integer leitosEnfPub,
    Integer leitosEnfPriv, Integer interPP, Integer ocupUPub, Integer ocupEnfPub, Integer ocupUPriv, Integer ocupEnfPriv) throws ParseException{
        data.setTime(dtFormat.parse(dt));
        testesRealizados = testR;
        testesNegativados = testN;
        testesConfirmados = testC;
        casosDia = casosD;
        masculino = masc;
        feminino = fem;
        obitos = obit;
        isolamentoDomiciliar = isolD;
        altaIsolamentoDomiciliar = altaIsolD;
        leitosUTIPublicoPrivado = leitosUPP;
        leitosUTIPublico = leitosUPub;
        leitosUTIPrivado = leitosUPriv;
        leitosEnfermariaPublicoPrivado = leitosEnfPP;
        leitosEnfermariaPublico = leitosEnfPub;
        leitosEnfermariaPrivado = leitosEnfPriv;
        internadosPublicoPrivado = interPP;
        ocupacaoUTIPublico = ocupUPub;
        ocupacaoEnfermariaPublico = ocupEnfPub;
        ocupacaoUTIPrivado = ocupUPriv;
        ocupacaoEnfermariaPrivado = ocupEnfPriv;
    }

    public Dados(){

    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Dados)){
            return 0;
        }
        Dados outro = (Dados) o;
        if (data.after(outro.data)){
            return 1;
        }
        else if (data.before(outro.data)){
            return -1;
        }
        else {
            return 0;
        }

    }

    public boolean equals(Object obj){
        if (!(obj instanceof Dados)){
            return false;
        }
        Dados outro = (Dados) obj;
        if (data.MONTH == outro.data.MONTH){
            return true;
        }
        return false;
    }

}
