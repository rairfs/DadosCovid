import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javax.imageio.ImageIO;

public class FXMLDocumentController implements Initializable {
    
    ArrayList<Dados> listaDados = new ArrayList<>();
    Integer contador = 0;
    FileChooser fileChooser = new FileChooser();
    Scanner leitor;
    ArrayList<Dados> organizados;
    Map<Integer,Integer> collect;
    LocalDate verifica;

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
        //Chamada referente ao botão abrir
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
                    // Pega todos as colunas do arquivo CSV e cria um objeto da classe Dados
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
                    //Pega essa instância da classe Dados e adiciona a uma lista de Dados
                    listaDados.add(dados);
                    palavra = leitor.nextLine();
                    separado = palavra.split(";");
                }
            }

        }
    }

    @FXML
    void escolheDado(ActionEvent event) {
        //Configuração inicial do gráfico
        tabela.setTitle(listaTipoDados.getValue());
        tabela.setLegendVisible(false);
        tabela.getData().clear();
        XYChart.Series serie = new XYChart.Series();
        //Essa variável organizados é gerada a partir do retorno da classe organizador, na qual filtra os objetos a partir das datas selecionadas nos datapickers
        //A variável collect, que está logo abaixo, pega cada uma das colunas do csv, agrupa por mês e soma os valores de suas colunas. O processo se repete para cada um dos if.
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
    void escolheData(ActionEvent event) {
        //Configuração básica dos datapickers
        aviso.setVisible(false);
        LocalDate inicio = dataInicial.getValue();
        LocalDate fim = dataFinal.getValue();
        verifica = fim.minusMonths(inicio.getMonthValue());
        if (inicio.isAfter(fim)){
            aviso.setText("Selecione uma data válida!");
            aviso.setVisible(true);
        }
        else if (fim.minusMonths(11).isAfter(verifica)){
            aviso.setText("Selecione filtros inferior a 11 meses");
            aviso.setVisible(true);
        }
    }

    @FXML
    void fecharAplicacao(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void selecionaFiltro(ActionEvent event) {
        //Ativa os datapickers quando o filtro data é selecionado
        dataInicial.setVisible(true);
        dataFinal.setVisible(true);
    }

    @FXML
    void sobre(ActionEvent event) {
        //Ação não implementada
    }

    @FXML
    void tirarScreenshot(ActionEvent event) throws IOException {
        //Gera, a partir do gráfico, uma imagem onde pode ser salva pelo usuário usando uma janela de diálogo.
        SnapshotParameters params = new SnapshotParameters();
        WritableImage screenshot = tabela.snapshot(params, null);
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        fileChooser.setInitialFileName("screenshot.png");
        File file = fileChooser.showSaveDialog(null);
        String fileName = file.toString();
        fileName += ".png";
        ImageIO.write(SwingFXUtils.fromFXImage(screenshot, null), "png", file);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Inicialização da aplicação, desativando avisos e criando filtros.
        aviso.setVisible(false);
        tipoFiltro.getItems().add("Data");
    }

}
