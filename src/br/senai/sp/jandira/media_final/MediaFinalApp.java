package br.senai.sp.jandira.media_final;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;


public class MediaFinalApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Determinar o tamanho do stage
        stage.setWidth(600);
        stage.setHeight(680);

        // Determinar o título do stage (tela/janela)
        stage.setTitle("Média Final");

        // Painel Raiz (root)
        BorderPane root = new BorderPane();

        Label labelTitulo = new Label();
        labelTitulo.setText("Escola \"Prof. Vicente Amato\"");
        // Formatação do Titulo
        labelTitulo.setStyle("-fx-text-fill: #005AFF;-fx-font-size: 32;-fx-font-weight: bold;");
        labelTitulo.setPadding(new Insets(10, 0, 10, 10));

        // Paine de resultado - parte de baixo
        VBox painelResultado = new VBox();
        painelResultado.setPadding(new Insets(0, 0, 10, 10));
        Label labelAluno = new Label("Nome do Aluno: ");
        Label labelMediaFinal = new Label("Média Final: ");
        Label labelSituacao = new Label("Situação: ");
        painelResultado.getChildren().addAll(labelAluno, labelMediaFinal, labelSituacao);

        // Painel de botões
        VBox painelDeBotoes = new VBox();
        painelDeBotoes.setPadding(new Insets(0, 10, 10, 0));
        painelDeBotoes.setSpacing(10);
        Button buttonCalcularMedia = new Button("Calcular média");
        buttonCalcularMedia.setPrefWidth(150);
        buttonCalcularMedia.setPrefHeight(50);
        Button buttonLimpar = new Button("Limpar");
        buttonLimpar.setPrefWidth(150);
        buttonLimpar.setPrefHeight(50);
        Button buttonSair = new Button("Sair");
        buttonSair.setPrefWidth(150);
        buttonSair.setPrefHeight(50);
        painelDeBotoes.getChildren().addAll(buttonCalcularMedia, buttonLimpar, buttonSair);

        // Painel de formulários
        VBox painelFormulario = new VBox();
        painelFormulario.setPadding(new Insets(0, 0, 10, 10));
        Label labelNome = new Label("Nome do Aluno: ");
        Label labelNota1 = new Label("Nota1: ");
        Label labelNota2 = new Label("Nota2: ");
        Label labelNota3 = new Label("Nota3: ");
        Label labelNota4 = new Label("Nota4: ");
        TextField textFieldNome = new TextField();
        TextField textFieldNota1 = new TextField();
        TextField textFieldNota2 = new TextField();
        TextField textFieldNota3 = new TextField();
        TextField textFieldNota4 = new TextField();
        painelFormulario.getChildren().addAll(
                labelNome, textFieldNome,
                labelNota1, textFieldNota1,
                labelNota2, textFieldNota2,
                labelNota3, textFieldNota3,
                labelNota4, textFieldNota4
        );

        root.setTop(labelTitulo);
        root.setBottom(painelResultado);
        root.setRight(painelDeBotoes);
        root.setLeft(painelFormulario);

        Scene scene = new Scene(root);

        stage.setScene(scene);

        // Mostrar o stage (tela/janela)
        stage.show();

        // Eventos de clique dos botões
        buttonCalcularMedia.setOnAction(click ->{
            System.out.println("Botão do Brayan clicado!");
            String nomeDigitado =textFieldNome.getText();
            labelAluno.setText("Nome do Aluno: " + nomeDigitado);

            // CALCULAR MÉDIA
            // OBTER AAS NOTAS

            //Criar um vetor (array) de notas
            double notas[] = new double[4];
            String notasStr[] = new String[4];

            notasStr[0] = textFieldNota1.getText();
            notas[0] = Double.parseDouble(notasStr[0]);

            notasStr[1] = textFieldNota2.getText();
            notas[1] = Double.parseDouble(notasStr[1]);

            notasStr[2] = textFieldNota3.getText();
            notas[2] = Double.parseDouble(notasStr[2]);

            notasStr[3] = textFieldNota4.getText();
            notas[3] = Double.parseDouble(notasStr[3]);

            // USO DE LOOP while (ENQUANTO)
            double mediaFinal = 0.0;
            int i =0;
            while (i < notas.length){
                mediaFinal = mediaFinal + notas[i];
                i = i + 1;
            }

            mediaFinal = mediaFinal / notas.length;
            // double mediaFinal = (notas[0] + notas[1] + notas[2] + notas[3]) / notas.length;
            String mediaFinalStr = String.format("%.2f", mediaFinal);

            labelMediaFinal.setText("Média Final: " + mediaFinalStr);

            String situacao;
            if (mediaFinal >= 6) {
                situacao = "Aprovado";
            } else if (mediaFinal < 4) {
                situacao = "Reprovado";
            } else {
                situacao = "Recuperação";
            }

            labelSituacao.setText("Situação: " + situacao);

        });

        buttonLimpar.setOnAction(click ->{
            textFieldNome.clear();
            textFieldNota1.clear();
            textFieldNota2.clear();
            textFieldNota3.clear();
            textFieldNota4.clear();
            labelMediaFinal.setText("Média Final:");
            labelSituacao.setText("Situação: ");
            labelAluno.setText("Nome do Aluno: ");
            textFieldNome.requestFocus();
        });
        buttonSair.setOnAction(click ->{
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Fechar aplicação", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> botaoPressionado = alerta.showAndWait();

            if (botaoPressionado.get() == ButtonType.YES){
                Alert alerta2 = new Alert(Alert.AlertType.INFORMATION, "Até logo grupo");
                alerta2.showAndWait();
                System.exit(0);
            }
        });
    }
}
