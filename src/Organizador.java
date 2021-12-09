import java.util.ArrayList;
import java.util.Calendar;
public class Organizador {

    public static ArrayList<Dados> agrupar(ArrayList<Dados> itens, Calendar mes, Calendar inicio, Calendar fim){
        ArrayList<Dados> organizados = new ArrayList<Dados>();
        for (Dados i : itens) {
            if (Dados.data.after(inicio) && Dados.data.before(fim) && Dados.data.MONTH == mes.MONTH){
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
