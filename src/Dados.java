import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Dados implements Comparable {
    private DateFormat dtFormat = new SimpleDateFormat("dd/MM/yyyy");
    private Calendar data;
    private Integer testesRealizados;
    private Integer testesNegativados;
    private Integer testesConfirmados;
    private Integer casosDia;
    private Integer masculino;
    private Integer feminino;
    private Integer obitos;
    private Integer isolamentoDomiciliar;
    private Integer altaIsolamentoDomiciliar;
    private Integer leitosUTIPublicoPrivado;
    private Integer leitosUTIPublico;
    private Integer leitosUTIPrivado;
    private Integer leitosEnfermariaPublicoPrivado;
    private Integer leitosEnfermariaPublico;
    private Integer leitosEnfermariaPrivado;
    private Integer internadosPublicoPrivado;
    private Integer ocupacaoUTIPublico;
    private Integer ocupacaoEnfermariaPublico;
    private Integer ocupacaoUTIPrivado;
    private Integer ocupacaoEnfermariaPrivado;

    public Dados(String dt, Integer testR, Integer testN, Integer testC, Integer casosD, Integer masc, Integer fem, Integer obit,
    Integer isolD, Integer altaIsolD, Integer leitosUPP, Integer leitosUPub, Integer leitosUPriv, Integer leitosEnfPP, Integer leitosEnfPub,
    Integer leitosEnfPriv, Integer interPP, Integer ocupUPub, Integer ocupEnfPub, Integer ocupUPriv, Integer ocupEnfPriv) throws ParseException{
        data = Calendar.getInstance();
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
        // TODO Auto-generated method stub
        return 0;
    }
}
