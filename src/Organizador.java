import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class Organizador {

    public static ArrayList<Dados> agrupar(ArrayList<Dados> itens, LocalDate inicio, LocalDate fim){
        ArrayList<Dados> organizados = new ArrayList<Dados>();
        // if Não funcionando, verificar!

        for (Dados i : itens) {
            if (i.getData().isAfter(inicio) && i.getData().isBefore(fim)){
                organizados.add(i);
            }
        }
        return organizados;
    }

    public static String nomeMes(int mes){
        switch (mes) {
            case 1:
                return "janeiro";
            case 2:
                return "feveiro";
            case 3:
                return "março";
            case 4:
                return "abril";
            case 5:
                return "maio";
            case 6:
                return "junho";
            case 7:
                return "julho";
            case 8:
                return "agosto";
            case 9:
                return "setembro";
            case 10:
                return "outubro";
            case 11:
                return "novembro";
            case 12:
                return "dezembro";
            default:
                return "Valor inválido";
        }
    }
}
