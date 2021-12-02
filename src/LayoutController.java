import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

public class LayoutController {
    
    Integer contador = 0;
    FileChooser fileChooser = new FileChooser();
    Scanner leitor;
    @FXML
    private MenuItem fxClose;

    @FXML
    private DatePicker fxStartDate;

    @FXML
    private DatePicker fxEndDate;

    @FXML
    private BarChart<Calendar, Integer> fxHistogram;

    @FXML
    private ListView<String> fxListView;

    @FXML
    private MenuItem fxOpen;

    @FXML
    void closeFile(ActionEvent event) {

    }

    @FXML
    void openFile(ActionEvent event) throws FileNotFoundException, ParseException {
        File f = fileChooser.showOpenDialog(null);
        leitor = new Scanner(f);
        fxListView.getItems().clear();
        while(leitor.hasNextLine()){
            String palavra = leitor.nextLine();
            String[] separado = palavra.split(";");
            for (int i = 1; i <= separado.length; i++) {
                String s = separado[i];
                if (contador == 0 ){
                    fxListView.getItems().add(s);
                }
                else{
                    fxListView.getItems().remove(0);
                    String data = separado[0];
                    Integer testesRealizados = Integer.parseInt(separado[1]);
                    Integer testesNegativados = Integer.parseInt(separado[2]);
                    Integer testesConfirmados = Integer.parseInt(separado[3]);
                    Integer casosDia = Integer.parseInt(separado[4]);
                    Integer masculino = Integer.parseInt(separado[5]);
                    Integer feminino = Integer.parseInt(separado[6]);
                    Integer obitos = Integer.parseInt(separado[7]);
                    Integer isolamentoDomiciliar = Integer.parseInt(separado[8]);
                    Integer altaisolamentoDomiciliar = Integer.parseInt(separado[9]);
                    Integer leitosUPP = Integer.parseInt(separado[10]);
                    Integer leitosUPub = Integer.parseInt(separado[11]);
                    Integer leitosUPriv = Integer.parseInt(separado[12]);
                    Integer leitosEnfPP = Integer.parseInt(separado[13]);
                    Integer leitosEnfPub = Integer.parseInt(separado[14]);
                    Integer leitosEnfPriv = Integer.parseInt(separado[15]);
                    Integer internadosPP = Integer.parseInt(separado[16]);
                    Integer ocupacaoUPub = Integer.parseInt(separado[17]);
                    Integer ocupacaoEnfPub = Integer.parseInt(separado[18]);
                    Integer ocupacaoUPriv = Integer.parseInt(separado[19]);
                    Integer ocupacaoEnfPriv = Integer.parseInt(separado[20]);
                    Dados dados = new Dados(data, testesRealizados, testesNegativados, testesConfirmados, casosDia, masculino, feminino, obitos, 
                    isolamentoDomiciliar, altaisolamentoDomiciliar, leitosUPP, leitosUPub, leitosUPriv, leitosEnfPP, leitosEnfPub, leitosEnfPriv, 
                    internadosPP, ocupacaoUPub, ocupacaoEnfPub, ocupacaoUPriv, ocupacaoEnfPriv);
                }
            }
            contador++;
        }
        contador = 0;
    }

}
