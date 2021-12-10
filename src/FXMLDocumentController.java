import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

public class FXMLDocumentController implements Initializable {
    
    ArrayList<Dados> listaDados = new ArrayList<>();
    Integer contador = 0;
    FileChooser fileChooser = new FileChooser();
    Scanner leitor;
    ArrayList<Dados> organizados;
    Map<Integer,Integer> collect;

    @FXML
    private Menu Arquivo;

    @FXML
    private MenuItem abrir;

    @FXML
    private Menu ajuda;

    @FXML
    private Label aviso;

    @FXML
    private MenuBar barraMenu;

    @FXML
    private DatePicker dataFinal;

    @FXML
    private DatePicker dataInicial;

    @FXML
    private MenuItem fechar;

    @FXML
    private ComboBox<String> listaTipoDados;

    @FXML
    private MenuItem screenshot;

    @FXML
    private MenuItem sobre;

    @FXML
    private BarChart<String, Integer> tabela;

    @FXML
    private ComboBox<String> tipoFiltro;

    @FXML
    void abrirArquivo(ActionEvent event) throws FileNotFoundException, ParseException {
        File f = fileChooser.showOpenDialog(null);
        leitor = new Scanner(f);
        listaTipoDados.getItems().clear();
        while (leitor.hasNextLine()){
            String palavra = leitor.nextLine();
            String[] separado = palavra.split(";");
            for (int i = 1; i < separado.length; i++) {
                if (contador == 0 ){
                    for (int j = 1; j < separado.length; j++){
                        String str = separado[j];
                        listaTipoDados.getItems().add(str);
                    }
                    contador++;
                    palavra = leitor.nextLine();
                    separado = palavra.split(";");
                }
                else{
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
                    Dados dados = new Dados(data, testesRealizados, testesNegativados, testesConfirmados, casosDia, masculino, feminino, obitos, isolamentoDomiciliar, altaisolamentoDomiciliar, leitosUPP, leitosUPub, leitosUPriv, leitosEnfPP, leitosEnfPub, leitosEnfPriv, internadosPP, ocupacaoUPub, ocupacaoEnfPub, ocupacaoUPriv, ocupacaoEnfPriv);
                    listaDados.add(dados);
                    palavra = leitor.nextLine();
                    separado = palavra.split(";");
                }
            }

        }
    }

    @FXML
    void escolheDado(ActionEvent event) {
        tabela.setTitle(listaTipoDados.getValue());
        tabela.setLegendVisible(false);
        tabela.getData().clear();
        XYChart.Series serie = new XYChart.Series();
        organizados = Organizador.agrupar(listaDados, dataInicial.getValue(), dataFinal.getValue());
        if (listaTipoDados.getValue().equals("TESTES REALIZADOS")){
            collect = organizados.stream().collect(Collectors.groupingBy(Dados::getMesInt, Collectors.summingInt((Dados d) -> d.getTestesRealizados())));
            Set<Integer> chaves = collect.keySet();
            for (Integer chave : chaves){
                String mes = Organizador.nomeMes(chave);
                Integer valor = collect.get(chave);
                serie.getData().add(new XYChart.Data(mes,valor));
            }
            tabela.getData().add(serie);
        }
        else if (listaTipoDados.getValue().equals("TESTES NEGATIVADOS")){
            collect = organizados.stream().collect(Collectors.groupingBy(Dados::getMesInt, Collectors.summingInt((Dados d) -> d.getTestesNegativados())));
            Set<Integer> chaves = collect.keySet();
            for (Integer chave : chaves){
                String mes = Organizador.nomeMes(chave);
                Integer valor = collect.get(chave);
                serie.getData().add(new XYChart.Data(mes,valor));
            }
            tabela.getData().add(serie);     
        }
        else if (listaTipoDados.getValue().equals("TESTES CONFIRMADOS")){
            collect = organizados.stream().collect(Collectors.groupingBy(Dados::getMesInt, Collectors.summingInt((Dados d) -> d.getTestesConfirmados())));
            Set<Integer> chaves = collect.keySet();
            for (Integer chave : chaves){
                String mes = Organizador.nomeMes(chave);
                Integer valor = collect.get(chave);
                serie.getData().add(new XYChart.Data(mes,valor));
            }
            tabela.getData().add(serie);          
        }
        else if (listaTipoDados.getValue().equals("Nº DE CASOS POR DIA")){
            collect = organizados.stream().collect(Collectors.groupingBy(Dados::getMesInt, Collectors.summingInt((Dados d) -> d.getCasosDia())));
            Set<Integer> chaves = collect.keySet();
            for (Integer chave : chaves){
                String mes = Organizador.nomeMes(chave);
                Integer valor = collect.get(chave);
                serie.getData().add(new XYChart.Data(mes,valor));
            }
            tabela.getData().add(serie);          
        }
        else if (listaTipoDados.getValue().equals("MASCULINO")){
            collect = organizados.stream().collect(Collectors.groupingBy(Dados::getMesInt, Collectors.summingInt((Dados d) -> d.getMasculino())));
            Set<Integer> chaves = collect.keySet();
            for (Integer chave : chaves){
                String mes = Organizador.nomeMes(chave);
                Integer valor = collect.get(chave);
                serie.getData().add(new XYChart.Data(mes,valor));
            }
            tabela.getData().add(serie);          
        }
        else if (listaTipoDados.getValue().equals("FEMININO")){
            collect = organizados.stream().collect(Collectors.groupingBy(Dados::getMesInt, Collectors.summingInt((Dados d) -> d.getFeminino())));
            Set<Integer> chaves = collect.keySet();
            for (Integer chave : chaves){
                String mes = Organizador.nomeMes(chave);
                Integer valor = collect.get(chave);
                serie.getData().add(new XYChart.Data(mes,valor));
            }
            tabela.getData().add(serie);          
        }
        else if (listaTipoDados.getValue().equals("ÓBITOS")){
            collect = organizados.stream().collect(Collectors.groupingBy(Dados::getMesInt, Collectors.summingInt((Dados d) -> d.getObitos())));
            Set<Integer> chaves = collect.keySet();
            for (Integer chave : chaves){
                String mes = Organizador.nomeMes(chave);
                Integer valor = collect.get(chave);
                serie.getData().add(new XYChart.Data(mes,valor));
            }
            tabela.getData().add(serie);          
        }
        else if (listaTipoDados.getValue().equals("ISOLAMENTO DOMICILIAR")){
            collect = organizados.stream().collect(Collectors.groupingBy(Dados::getMesInt, Collectors.summingInt((Dados d) -> d.getIsolamentoDomiciliar())));
            Set<Integer> chaves = collect.keySet();
            for (Integer chave : chaves){
                String mes = Organizador.nomeMes(chave);
                Integer valor = collect.get(chave);
                serie.getData().add(new XYChart.Data(mes,valor));
            }
            tabela.getData().add(serie);          
        }
        else if (listaTipoDados.getValue().equals("Nº DE LEITOS UTI PÚBLICO E PRIVADO")){
            collect = organizados.stream().collect(Collectors.groupingBy(Dados::getMesInt, Collectors.summingInt((Dados d) -> d.getLeitosUTIPublicoPrivado())));
            Set<Integer> chaves = collect.keySet();
            for (Integer chave : chaves){
                String mes = Organizador.nomeMes(chave);
                Integer valor = collect.get(chave);
                serie.getData().add(new XYChart.Data(mes,valor));
            }
            tabela.getData().add(serie);          
        }
        else if (listaTipoDados.getValue().equals("Nº DE LEITOS DE UTI PÚBLICO")){
            collect = organizados.stream().collect(Collectors.groupingBy(Dados::getMesInt, Collectors.summingInt((Dados d) -> d.getLeitosUTIPublico())));
            Set<Integer> chaves = collect.keySet();
            for (Integer chave : chaves){
                String mes = Organizador.nomeMes(chave);
                Integer valor = collect.get(chave);
                serie.getData().add(new XYChart.Data(mes,valor));
            }
            tabela.getData().add(serie);          
        }
        else if (listaTipoDados.getValue().equals("Nº DE LEITOS DE UTI PRIVADO")){
            collect = organizados.stream().collect(Collectors.groupingBy(Dados::getMesInt, Collectors.summingInt((Dados d) -> d.getLeitosUTIPrivado())));
            Set<Integer> chaves = collect.keySet();
            for (Integer chave : chaves){
                String mes = Organizador.nomeMes(chave);
                Integer valor = collect.get(chave);
                serie.getData().add(new XYChart.Data(mes,valor));
            }
            tabela.getData().add(serie);          
        }
        else if (listaTipoDados.getValue().equals("Nº DE LEITOS  ENFERMARIA  PÚBLICO E PRIVADO")){
            collect = organizados.stream().collect(Collectors.groupingBy(Dados::getMesInt, Collectors.summingInt((Dados d) -> d.getLeitosEnfermariaPublicoPrivado())));
            Set<Integer> chaves = collect.keySet();
            for (Integer chave : chaves){
                String mes = Organizador.nomeMes(chave);
                Integer valor = collect.get(chave);
                serie.getData().add(new XYChart.Data(mes,valor));
            }
            tabela.getData().add(serie);          
        }
        else if (listaTipoDados.getValue().equals("Nº DE LEITOS DE ENFERMARIA PÚBLICO")){
            collect = organizados.stream().collect(Collectors.groupingBy(Dados::getMesInt, Collectors.summingInt((Dados d) -> d.getLeitosEnfermariaPublico())));
            Set<Integer> chaves = collect.keySet();
            for (Integer chave : chaves){
                String mes = Organizador.nomeMes(chave);
                Integer valor = collect.get(chave);
                serie.getData().add(new XYChart.Data(mes,valor));
            }
            tabela.getData().add(serie);          
        }
        else if (listaTipoDados.getValue().equals("Nº DE LEITOS DE ENFERMARIA PRIVADO")){
            collect = organizados.stream().collect(Collectors.groupingBy(Dados::getMesInt, Collectors.summingInt((Dados d) -> d.getLeitosEnfermariaPrivado())));
            Set<Integer> chaves = collect.keySet();
            for (Integer chave : chaves){
                String mes = Organizador.nomeMes(chave);
                Integer valor = collect.get(chave);
                serie.getData().add(new XYChart.Data(mes,valor));
            }
            tabela.getData().add(serie);          
        }
        else if (listaTipoDados.getValue().equals("INTERNADOS PÚBLICO E PRIVADO")){
            collect = organizados.stream().collect(Collectors.groupingBy(Dados::getMesInt, Collectors.summingInt((Dados d) -> d.getInternadosPublicoPrivado())));
            Set<Integer> chaves = collect.keySet();
            for (Integer chave : chaves){
                String mes = Organizador.nomeMes(chave);
                Integer valor = collect.get(chave);
                serie.getData().add(new XYChart.Data(mes,valor));
            }
            tabela.getData().add(serie);          
        }
        else if (listaTipoDados.getValue().equals("UTI SUS OCUPAÇÃO")){
            collect = organizados.stream().collect(Collectors.groupingBy(Dados::getMesInt, Collectors.summingInt((Dados d) -> d.getOcupacaoUTIPublico())));
            Set<Integer> chaves = collect.keySet();
            for (Integer chave : chaves){
                String mes = Organizador.nomeMes(chave);
                Integer valor = collect.get(chave);
                serie.getData().add(new XYChart.Data(mes,valor));
            }
            tabela.getData().add(serie);          
        }
        else if (listaTipoDados.getValue().equals("ENFERMARIA SUS OCUPAÇÃO")){
            collect = organizados.stream().collect(Collectors.groupingBy(Dados::getMesInt, Collectors.summingInt((Dados d) -> d.getOcupacaoEnfermariaPublico())));
            Set<Integer> chaves = collect.keySet();
            for (Integer chave : chaves){
                String mes = Organizador.nomeMes(chave);
                Integer valor = collect.get(chave);
                serie.getData().add(new XYChart.Data(mes,valor));
            }
            tabela.getData().add(serie);          
        }
        else if (listaTipoDados.getValue().equals("UTI PRIVADO OCUPAÇÃO")){
            collect = organizados.stream().collect(Collectors.groupingBy(Dados::getMesInt, Collectors.summingInt((Dados d) -> d.getOcupacaoUTIPrivado())));
            Set<Integer> chaves = collect.keySet();
            for (Integer chave : chaves){
                String mes = Organizador.nomeMes(chave);
                Integer valor = collect.get(chave);
                serie.getData().add(new XYChart.Data(mes,valor));
            }
            tabela.getData().add(serie);          
        }
        else if (listaTipoDados.getValue().equals("ENFERMARIA PRIVADO OCUPAÇÃO")){
            collect = organizados.stream().collect(Collectors.groupingBy(Dados::getMesInt, Collectors.summingInt((Dados d) -> d.getOcupacaoEnfermariaPrivado())));
            Set<Integer> chaves = collect.keySet();
            for (Integer chave : chaves){
                String mes = Organizador.nomeMes(chave);
                Integer valor = collect.get(chave);
                serie.getData().add(new XYChart.Data(mes,valor));
            }
            tabela.getData().add(serie);          
        }
                
    }

    @FXML
    void fecharAplicacao(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void selecionaFiltro(ActionEvent event) {
        dataInicial.setVisible(true);
        dataFinal.setVisible(true);
    }

    @FXML
    void sobre(ActionEvent event) {

    }

    @FXML
    void tirarScreenshot(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tipoFiltro.getItems().add("Data");
    }

}
