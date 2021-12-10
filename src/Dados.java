import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Dados{
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private LocalDate data;
    private int testesRealizados;
    private int testesNegativados;
    private int testesConfirmados;
    private int casosDia;
    private int masculino;
    private int feminino;
    private int obitos;
    private int isolamentoDomiciliar;
    private int altaIsolamentoDomiciliar;
    private int leitosUTIPublicoPrivado;
    private int leitosUTIPublico;
    private int leitosUTIPrivado;
    private int leitosEnfermariaPublicoPrivado;
    private int leitosEnfermariaPublico;
    private int leitosEnfermariaPrivado;
    private int internadosPublicoPrivado;
    private int ocupacaoUTIPublico;
    private int ocupacaoEnfermariaPublico;
    private int ocupacaoUTIPrivado;
    private int ocupacaoEnfermariaPrivado;

    public Dados(String dt, Integer testR, Integer testN, Integer testC, Integer casosD, Integer masc, Integer fem, Integer obit,
    Integer isolD, Integer altaIsolD, Integer leitosUPP, Integer leitosUPub, Integer leitosUPriv, Integer leitosEnfPP, Integer leitosEnfPub,
    Integer leitosEnfPriv, Integer interPP, Integer ocupUPub, Integer ocupEnfPub, Integer ocupUPriv, Integer ocupEnfPriv) throws ParseException{
        data = data.parse(dt, formatter);
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

    public LocalDate getData(){
        return data;
    }

    public int getTestesRealizados(){
        return testesRealizados;
    }

    public int getTestesNegativados(){
        return testesNegativados;
    }

    public int getTestesConfirmados(){
        return testesConfirmados;
    }

    public int getCasosDia(){
        return casosDia;
    }

    public int getMasculino(){
        return masculino;
    }

    public int getFeminino(){
        return feminino;
    }

    public int getObitos(){
        return obitos;
    }

    public int getIsolamentoDomiciliar(){
        return isolamentoDomiciliar;
    }

    public int getAltaIsolamentoDomiciliar(){
        return altaIsolamentoDomiciliar;
    }

    public int getLeitosUTIPublicoPrivado(){
        return leitosUTIPublicoPrivado;
    }

    public int getLeitosUTIPublico(){
        return leitosUTIPublico;
    }

    public int getLeitosUTIPrivado(){
        return leitosUTIPrivado;
    }

    public int getLeitosEnfermariaPublicoPrivado(){
        return leitosEnfermariaPublicoPrivado;
    }

    public int getLeitosEnfermariaPublico(){
        return leitosEnfermariaPublico;
    }

    public int getLeitosEnfermariaPrivado(){
        return leitosEnfermariaPrivado;
    }

    public int getInternadosPublicoPrivado(){
        return internadosPublicoPrivado;
    }

    public int getOcupacaoUTIPublico(){
        return ocupacaoUTIPublico;
    }

    public int getOcupacaoEnfermariaPublico(){
        return ocupacaoEnfermariaPublico;
    }

    public int getOcupacaoUTIPrivado(){
        return ocupacaoUTIPrivado;
    }

    public int getOcupacaoEnfermariaPrivado(){
        return ocupacaoEnfermariaPrivado;
    }

    public int getMesInt(){
        return data.getMonthValue();
    }

    public int getYear(){
        return data.getYear();
    }

}
