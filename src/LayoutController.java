import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.time.Month;
import java.util.Calendar;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import java.util.ArrayList;

public class LayoutController {
    
    ArrayList<Dados> listaDados = new ArrayList<Dados>();
    Integer contador = 0;
    FileChooser fileChooser = new FileChooser();
    Scanner leitor;
    @FXML
    private MenuItem fxClose;

    @FXML
    private ComboBox<String> fxComboBox;

    @FXML
    private DatePicker fxStartDate;

    @FXML
    private DatePicker fxEndDate;

    @FXML
    private BarChart<Calendar, Integer> fxHistogram;

    @FXML
    private CategoryAxis fxMesesBarras;

    @FXML
    private NumberAxis fxNumberBarras;

    @FXML
    private ListView<String> fxListView;

    @FXML
    private MenuItem fxOpen;

    @FXML
    private MenuItem fxSnapShot;

    @FXML
    private Label fxWarning;

    Integer inicio;
    Integer fim;
    Integer mesAtual;

    @FXML
    void closeFile(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void openFile(ActionEvent event) throws FileNotFoundException, ParseException {
        this.fxComboBox.getItems().add("Data");
        File f = fileChooser.showOpenDialog(null);
        leitor = new Scanner(f);
        fxListView.getItems().clear();
        while(leitor.hasNextLine()){
            String palavra = leitor.nextLine();
            String[] separado = palavra.split(";");
            for (int i = 1; i < separado.length; i++) {
                if (contador == 0 ){
                    for (int j = 1; j < separado.length; j++){
                        String str = separado[j];
                        fxListView.getItems().add(str);
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
                    Dados dados = new Dados(data, testesRealizados, testesNegativados, testesConfirmados, casosDia, masculino, feminino, obitos, 
                    isolamentoDomiciliar, altaisolamentoDomiciliar, leitosUPP, leitosUPub, leitosUPriv, leitosEnfPP, leitosEnfPub, leitosEnfPriv, 
                    internadosPP, ocupacaoUPub, ocupacaoEnfPub, ocupacaoUPriv, ocupacaoEnfPriv);
                    listaDados.add(dados);
                    palavra = leitor.nextLine();
                    separado = palavra.split(";");
                }
            }

        }
        contador = 0;

    }

    @FXML
    void comboBoxUpdate(ActionEvent event) {
        this.fxStartDate.setVisible(true);
        this.fxEndDate.setVisible(true);
    }
    
    @FXML
    void atualizarTabela(MouseEvent event) {
        inicio = this.fxStartDate.getValue().getMonthValue();
        fim = this.fxEndDate.getValue().getMonthValue();
        mesAtual = this.fxStartDate.getValue().getMonthValue();
        Integer soma = 0;
        ArrayList<Dados> organizados;
        XYChart.Series set1 = new XYChart.Series<>();
        this.fxHistogram.setTitle(this.fxListView.getSelectionModel().getSelectedItem());
        switch (this.fxListView.getSelectionModel().getSelectedItem().toString()) {
            case "TESTES REALIZADOS":
                organizados = Organizador.agrupar(listaDados, mesAtual, inicio, fim);
                for (int i = 0; i < fim - inicio; i++){
                    for (Dados d : organizados) {
                        soma += d.testesRealizados;
                    }
                    set1.getData().add(new XYChart.Data(Organizador.nomeMes(mesAtual), soma));
                    if (mesAtual == 12){
                        mesAtual = 1;
                    }
                    else {
                        mesAtual++;
                    }
                    fxHistogram.getData().add(set1);
                    set1.getData().clear();
                    soma = 0;
                }
                break;
            case "TESTES NEGATIVADOS":
                organizados = Organizador.agrupar(listaDados, mesAtual, inicio, fim);
                for (int i = 0; i < fim - inicio; i++){
                    for (Dados d : organizados) {
                        soma += d.testesNegativados;
                    }
                    set1.getData().add(new XYChart.Data(Organizador.nomeMes(mesAtual), soma));
                    if (mesAtual == 12){
                        mesAtual = 1;
                    }
                    else {
                        mesAtual++;
                    }
                    fxHistogram.getData().add(set1);
                    set1.getData().clear();
                }
                soma = 0;
                break;
            case "TESTES CONFIRMADOS":
                organizados = Organizador.agrupar(listaDados, mesAtual, inicio, fim);
                for (int i = 0; i < fim - inicio; i++){
                    for (Dados d : organizados) {
                        soma += d.testesNegativados;
                    }
                    set1.getData().add(new XYChart.Data(Organizador.nomeMes(mesAtual), soma));
                    if (mesAtual == 12){
                        mesAtual = 1;
                    }
                    else {
                        mesAtual++;
                    }
                    fxHistogram.getData().add(set1);
                    set1.getData().clear();
                }
                soma = 0;
                break;
            case "Nº DE CASOS POR DIA":
                organizados = Organizador.agrupar(listaDados, mesAtual, inicio, fim);
                for (int i = 0; i < fim - inicio; i++){
                    for (Dados d : organizados) {
                        soma += d.casosDia;
                    }
                    set1.getData().add(new XYChart.Data(Organizador.nomeMes(mesAtual), soma));
                    if (mesAtual == 12){
                        mesAtual = 1;
                    }
                    else {
                        mesAtual++;
                    }
                    fxHistogram.getData().add(set1);
                    set1.getData().clear();
                }
                soma = 0;
                break;
            case "MASCULINO":
                organizados = Organizador.agrupar(listaDados, mesAtual, inicio, fim);
                for (int i = 0; i < fim - inicio; i++){
                    for (Dados d : organizados) {
                        soma += d.masculino;
                    }
                    set1.getData().add(new XYChart.Data(Organizador.nomeMes(mesAtual), soma));
                    if (mesAtual == 12){
                        mesAtual = 1;
                    }
                    else {
                        mesAtual++;
                    }
                    fxHistogram.getData().add(set1);
                    set1.getData().clear();
                }
                soma = 0;
                break;
            case "FEMININO":
                organizados = Organizador.agrupar(listaDados, mesAtual, inicio, fim);
                for (int i = 0; i < fim - inicio; i++){
                    for (Dados d : organizados) {
                        soma += d.feminino;
                    }
                    set1.getData().add(new XYChart.Data(Organizador.nomeMes(mesAtual), soma));
                    if (mesAtual == 12){
                        mesAtual = 1;
                    }
                    else {
                        mesAtual++;
                    }
                    fxHistogram.getData().add(set1);
                    set1.getData().clear();
                }
                soma = 0;
                break;
            case "ÓBITOS":
                organizados = Organizador.agrupar(listaDados, mesAtual, inicio, fim);
                for (int i = 0; i < fim - inicio; i++){
                    for (Dados d : organizados) {
                        soma += d.obitos;
                    }
                    set1.getData().add(new XYChart.Data(Organizador.nomeMes(mesAtual), soma));
                    if (mesAtual == 12){
                        mesAtual = 1;
                    }
                    else {
                        mesAtual++;
                    }
                    fxHistogram.getData().add(set1);
                    set1.getData().clear();
                }
                soma = 0;
                break;
            case "ISOLAMENTO DOMICILIAR":
                organizados = Organizador.agrupar(listaDados, mesAtual, inicio, fim);
                for (int i = 0; i < fim - inicio; i++){
                    for (Dados d : organizados) {
                        soma += d.isolamentoDomiciliar;
                    }
                    set1.getData().add(new XYChart.Data(Organizador.nomeMes(mesAtual), soma));
                    if (mesAtual == 12){
                        mesAtual = 1;
                    }
                    else {
                        mesAtual++;
                    }
                    fxHistogram.getData().add(set1);
                    set1.getData().clear();
                }
                soma = 0;
                break;
            case "ALTA ISOLAMENTO DOMICILIAR":
                organizados = Organizador.agrupar(listaDados, mesAtual, inicio, fim);
                for (int i = 0; i < fim - inicio; i++){
                    for (Dados d : organizados) {
                        soma += d.altaIsolamentoDomiciliar;
                    }
                    set1.getData().add(new XYChart.Data(Organizador.nomeMes(mesAtual), soma));
                    if (mesAtual == 12){
                        mesAtual = 1;
                    }
                    else {
                        mesAtual++;
                    }
                    fxHistogram.getData().add(set1);
                    set1.getData().clear();
                }
                soma = 0;
                break;
            case "Nº DE LEITOS UTI PÚBLICO E PRIVADO":
                organizados = Organizador.agrupar(listaDados, mesAtual, inicio, fim);
                for (int i = 0; i < fim - inicio; i++){
                    for (Dados d : organizados) {
                        soma += d.leitosUTIPublicoPrivado;
                    }
                    set1.getData().add(new XYChart.Data(Organizador.nomeMes(mesAtual), soma));
                    if (mesAtual == 12){
                        mesAtual = 1;
                    }
                    else {
                        mesAtual++;
                    }
                    fxHistogram.getData().add(set1);
                    set1.getData().clear();
                }
                soma = 0;
                break;
            case "Nº DE LEITOS DE UTI PÚBLICO":
                organizados = Organizador.agrupar(listaDados, mesAtual, inicio, fim);
                for (int i = 0; i < fim - inicio; i++){
                    for (Dados d : organizados) {
                        soma += d.leitosUTIPublico;
                    }
                    set1.getData().add(new XYChart.Data(Organizador.nomeMes(mesAtual), soma));
                    if (mesAtual == 12){
                        mesAtual = 1;
                    }
                    else {
                        mesAtual++;
                    }
                    fxHistogram.getData().add(set1);
                    set1.getData().clear();
                }
                soma = 0;
                break;
            case "Nº DE LEITOS DE UTI PRIVADO":
                organizados = Organizador.agrupar(listaDados, mesAtual, inicio, fim);
                for (int i = 0; i < fim - inicio; i++){
                    for (Dados d : organizados) {
                        soma += d.leitosUTIPrivado;
                    }
                    set1.getData().add(new XYChart.Data(Organizador.nomeMes(mesAtual), soma));
                    if (mesAtual == 12){
                        mesAtual = 1;
                    }
                    else {
                        mesAtual++;
                    }
                    fxHistogram.getData().add(set1);
                    set1.getData().clear();
                }
                soma = 0;
                break;
            case "Nº DE LEITOS  ENFERMARIA  PÚBLICO E PRIVADO":
                organizados = Organizador.agrupar(listaDados, mesAtual, inicio, fim);
                for (int i = 0; i < fim - inicio; i++){
                    for (Dados d : organizados) {
                        soma += d.leitosEnfermariaPublicoPrivado;

                    }
                    set1.getData().add(new XYChart.Data(Organizador.nomeMes(mesAtual), soma));
                    if (mesAtual == 12){
                        mesAtual = 1;
                    }
                    else {
                        mesAtual++;
                    }
                    fxHistogram.getData().add(set1);
                    set1.getData().clear();
                }
                soma = 0;
                break;
            case "Nº DE LEITOS DE ENFERMARIA PÚBLICO":
                organizados = Organizador.agrupar(listaDados, mesAtual, inicio, fim);
                for (int i = 0; i < fim - inicio; i++){
                    for (Dados d : organizados) {
                        soma += d.leitosEnfermariaPublico;
                    }
                    set1.getData().add(new XYChart.Data(Organizador.nomeMes(mesAtual), soma));
                    if (mesAtual == 12){
                        mesAtual = 1;
                    }
                    else {
                        mesAtual++;
                    }
                    fxHistogram.getData().add(set1);
                    set1.getData().clear();
                }
                soma = 0;
                break;
            case "Nº DE LEITOS DE ENFERMARIA PRIVADO":
                organizados = Organizador.agrupar(listaDados, mesAtual, inicio, fim);
                for (int i = 0; i < fim - inicio; i++){
                    for (Dados d : organizados) {
                        soma += d.leitosEnfermariaPrivado;
                    }
                    set1.getData().add(new XYChart.Data(Organizador.nomeMes(mesAtual), soma));
                    if (mesAtual == 12){
                        mesAtual = 1;
                    }
                    else {
                        mesAtual++;
                    }
                    fxHistogram.getData().add(set1);
                    set1.getData().clear();
                }
                soma = 0;
                break;
            case "INTERNADOS PÚBLICO E PRIVADO":
                organizados = Organizador.agrupar(listaDados, mesAtual, inicio, fim);
                for (int i = 0; i < fim - inicio; i++){
                    for (Dados d : organizados) {
                        soma += d.internadosPublicoPrivado;
                    }
                    set1.getData().add(new XYChart.Data(Organizador.nomeMes(mesAtual), soma));
                    if (mesAtual == 12){
                        mesAtual = 1;
                    }
                    else {
                        mesAtual++;
                    }
                    fxHistogram.getData().add(set1);
                    set1.getData().clear();
                }
                soma = 0;
                break;
            case "UTI SUS OCUPAÇÃO":
                organizados = Organizador.agrupar(listaDados, mesAtual, inicio, fim);
                for (int i = 0; i < fim - inicio; i++){
                    for (Dados d : organizados) {
                        soma += d.ocupacaoUTIPublico;
                    }
                    set1.getData().add(new XYChart.Data(Organizador.nomeMes(mesAtual), soma));
                    if (mesAtual == 12){
                        mesAtual = 1;
                    }
                    else {
                        mesAtual++;
                    }
                    fxHistogram.getData().add(set1);
                    set1.getData().clear();
                }
                soma = 0;
                break;
            case "ENFERMARIA SUS OCUPAÇÃO":
                organizados = Organizador.agrupar(listaDados, mesAtual, inicio, fim);
                for (int i = 0; i < fim - inicio; i++){
                    for (Dados d : organizados) {
                        soma += d.ocupacaoEnfermariaPublico;
                    }
                    set1.getData().add(new XYChart.Data(Organizador.nomeMes(mesAtual), soma));
                    if (mesAtual == 12){
                        mesAtual = 1;
                    }
                    else {
                        mesAtual++;
                    }
                    fxHistogram.getData().add(set1);
                    set1.getData().clear();
                }
                soma = 0;
                break;
            case "UTI PRIVADO OCUPAÇÃO":
                organizados = Organizador.agrupar(listaDados, mesAtual, inicio, fim);
                for (int i = 0; i < fim - inicio; i++){
                    for (Dados d : organizados) {
                        soma += d.ocupacaoUTIPrivado;
                    }
                    set1.getData().add(new XYChart.Data(Organizador.nomeMes(mesAtual), soma));
                    if (mesAtual == 12){
                        mesAtual = 1;
                    }
                    else {
                        mesAtual++;
                    }
                    fxHistogram.getData().add(set1);
                    set1.getData().clear();
                }
                soma = 0;
                break;
            case "ENFERMARIA PRIVADO OCUPAÇÃO":
                organizados = Organizador.agrupar(listaDados, mesAtual, inicio, fim);
                for (int i = 0; i < fim - inicio; i++){
                    for (Dados d : organizados) {
                        soma += d.ocupacaoEnfermariaPrivado;
                    }
                    set1.getData().add(new XYChart.Data(Organizador.nomeMes(mesAtual), soma));
                    if (mesAtual == 12){
                        mesAtual = 1;
                    }
                    else {
                        mesAtual++;
                    }
                    fxHistogram.getData().add(set1);
                    set1.getData().clear();
                }
                soma = 0;
                break;
        
            default:
                break;
        }
    }

}

