import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Dados{
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private LocalDate data;
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

    public Integer getTestesRealizados(){
        return testesRealizados;
    }

    public Integer getTestesNegativados(){
        return testesNegativados;
    }

    public Integer getTestesConfirmados(){
        return testesConfirmados;
    }

    public Integer getCasosDia(){
        return casosDia;
    }

    public Integer getMasculino(){
        return masculino;
    }

    public Integer getFeminino(){
        return feminino;
    }

    public Integer getObitos(){
        return obitos;
    }

    public Integer getIsolamentoDomiciliar(){
        return isolamentoDomiciliar;
    }

    public Integer getAltaIsolamentoDomiciliar(){
        return altaIsolamentoDomiciliar;
    }

    public Integer getLeitosUTIPublicoPrivado(){
        return leitosUTIPublicoPrivado;
    }

    public Integer getLeitosUTIPublico(){
        return leitosUTIPublico;
    }

    public Integer getLeitosUTIPrivado(){
        return leitosUTIPrivado;
    }

    public Integer getLeitosEnfermariaPublicoPrivado(){
        return leitosEnfermariaPublicoPrivado;
    }

    public Integer getLeitosEnfermariaPublico(){
        return leitosEnfermariaPublico;
    }

    public Integer getLeitosEnfermariaPrivado(){
        return leitosEnfermariaPrivado;
    }

    public Integer getInternadosPublicoPrivado(){
        return internadosPublicoPrivado;
    }

    public Integer getOcupacaoUTIPublico(){
        return ocupacaoUTIPublico;
    }

    public Integer getOcupacaoEnfermariaPublico(){
        return ocupacaoEnfermariaPublico;
    }

    public Integer getOcupacaoUTIPrivado(){
        return ocupacaoUTIPrivado;
    }

    public Integer getOcupacaoEnfermariaPrivado(){
        return ocupacaoEnfermariaPrivado;
    }

    public Integer getMesInt(){
        return data.getMonthValue();
    }

}
