import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ProjetoListaDeCompras extends Application {
	private ArrayList<String> listaDeCompras = new ArrayList<>();
	private ListView<String> listaVisualizavel = new ListView<>(); // exibe os itens da lista de compras

	@Override
	public void start(Stage palco) {
		palco.setTitle("Aplicativo de lista de compras");

		TextField textFieldDescricaoItem = new TextField();

		Button botaoAdicionar = new Button("Adicionar");
		Button botaoExportar = new Button("Exportar Lista");

		Label labelAdicionar = new Label("Digite o item que deseja adicionar.");
		Label labelListaDeCompras = new Label("Lista de compras:");

		// criando objeto ObservableList a partir da lista de compras
		ObservableList<String> observableListaDeCompras = FXCollections.observableArrayList(listaDeCompras);
		listaVisualizavel.setItems(observableListaDeCompras);

		VBox vBox = new VBox();
		vBox.getChildren().addAll(labelAdicionar, textFieldDescricaoItem, botaoAdicionar);
		vBox.getChildren().addAll(labelListaDeCompras, listaVisualizavel, botaoExportar);
		vBox.setSpacing(10); //espacamento vertical entre os componentes
		vBox.setPadding(new Insets(10)); //espacamento para que os itens nao fiquem colados na borda

		botaoAdicionar.setOnAction(e -> {
			String item = textFieldDescricaoItem.getText(); //obtem o texto digitado e armazena na variavel item;
				if (!item.isEmpty()) { //entra no if se o texto nao estiver vazio
					listaDeCompras.add(item); //texto digitado é adicionado a listaDeCompras
					listaVisualizavel.getItems().add(item); //texto é adicionado a listaVisualizavel
					textFieldDescricaoItem.clear(); //campo de texto é limpo, permitindo uma nova digitação
				} 
		});

		botaoExportar.setOnAction(e -> {
			try {
				File arquivo = new File("listaDeCompras.txt");
				PrintWriter writer = new PrintWriter(arquivo); //o printwriter grava os itens da lista no arquivo
				for (String item : listaDeCompras) { //percorre todos os itens da compra
					writer.println(item); //cada item da lista e escrita no arquivo
				}
				writer.close();
			} catch (Exception ex) {
				System.out.println("Erro ocorrido: " + ex.getMessage());
			}
		});

		Scene scene = new Scene(vBox, 350, 300);
		palco.setScene(scene);
		palco.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}