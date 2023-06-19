/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquerque;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.*;
import java.net.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static javafx.scene.media.MediaPlayer.Status.PLAYING;
import javafx.scene.text.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;
import javax.swing.JLabel;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;

/**
 *
 * @author joaop
 */
public class Alquerque extends Application {
    
    public static final int Tabuleiro_T=100;
    public static final int Width=5;
    public static final int Height=5;
    public static final Label label1 = new Label("padrao");
    public  Tabuleiro[][] board1 = new Tabuleiro[Width][Height];
    public  int x_1;
    public  int y_1;
    public double x_1_a;  
    public double y_1_a;
    public double antigo_X_pc;
    public double antigo_Y_pc;
    public int antigo_X_Int;
    public int antigo_Y_Int;
    
    private Group tabuleiro_Group = new Group();   
    private Group pecaGroupW = new Group();
    private Group pecaGroupB = new Group();
    private int k;
    
    private ClienteConnection c_s_c;
    private int jogador_ID;
    private int outroJogador; 
    private int turnosFeitos;
    
    public int id_morto;
    
 
    
    Alert a = new Alert(Alert.AlertType.NONE);
    private boolean movimentoDisabled = false;
    
    Button butao = new Button("Ajuda");
    Button butao2 = new Button("C/som");
    Button butao3 = new Button("S/som");
    
    Media media = null;
    
    Label labelPeca = new Label("Nº de Peças");
    Label labelBranca = new Label("Brancas -  " + pecaGroupW.getChildren().size());
    Label labelPreta = new Label("Pretas   -  " + pecaGroupB.getChildren().size());
    Label suaLabel = new Label("A tua cor");
        
     Circle circuloBrancas = new Circle();
     Circle circuloPretas = new Circle();
    
     
     
    public void conexaoToServer(){
       
        c_s_c = new ClienteConnection(this);
        
        this.jogador_ID = c_s_c.getPlayerID();
        if (this.jogador_ID==1){
            for (Node p : pecaGroupW.getChildren()){
                    ((Peca)p).setMoveDisabled(true);
                }
                for(Node p : pecaGroupB.getChildren()){
                    ((Peca)p).setMoveDisabled(true);
            }
            this.outroJogador = 2;
            
            this.label1.setText("És o jogador 1 podes começar a partida");
            
            
         } else {
            this.label1.setText("És o jogador 2. Espera pelo teu turno");
            this.outroJogador = 1;
           
         }
        
         
    }
    
    
     public Parent criaContent(){
        Pane root = new Pane();
        root.setPrefSize(Width * Tabuleiro_T + 120, Height * Tabuleiro_T + 40);
        label1.setLayoutY(505);
        label1.setLayoutX(5);
        label1.setFont(new Font ("Verdana", 18));
        
        suaLabel.setLayoutY(180);
        suaLabel.setLayoutX(510);
        suaLabel.setFont(new Font ("Verdana", 18));
        
        
        turnosFeitos = 0;
        
        butao.setLayoutY(470);
        butao.setLayoutX(537);
        butao2.setLayoutY(430);
        butao2.setLayoutX(560);
        butao3.setLayoutY(430);
        butao3.setLayoutX(505);
        
        root.getChildren().addAll(tabuleiro_Group, pecaGroupW, pecaGroupB, label1, suaLabel, butao, butao2, butao3);
      
         
       
        
        
        
        for (int y = 0; y < Height; y++) {
            for (int x = 0; x < Width; x++) {
                Tabuleiro tile = new Tabuleiro((x + y) % 2 == 0, x, y);
                board1[x][y] = tile;
                tabuleiro_Group.getChildren().add(tile);

                Peca piece = null;
  
                if (y <= 1 || x <= 1 && y<=2){
                    piece = criaPeca(PecaTipo.Black,x ,y);
                }
                if (y >= 3 || x >= 3 && y>=2){
                    piece = criaPeca(PecaTipo.White,x ,y);
                }
                if (piece != null){
                    tile.setPiece(piece);

                    if (tile.getPiece().getType() == PecaTipo.Black) {
                       
                        if(jogador_ID == 1)
                            piece.setMoveDisabled(true);
                        else
                            piece.setMoveDisabled(false);
                        pecaGroupB.getChildren().add(piece);
                    } else {
                        
                        if(jogador_ID == 2)
                            piece.setMoveDisabled(true);
                        else
                            piece.setMoveDisabled(false);
                        pecaGroupW.getChildren().add(piece);
                    }
                }   

                tile.setOnMouseClicked(event -> {

                    System.out.println("------------------");
                    System.out.println("X : " + tile.getTileX());
                    System.out.println("Y : " + tile.getTileY());
                    System.out.println("------------------");

                });
                

                   
              
            }

        }
        
        // Texto Lateral
        labelBranca.setText("Brancas -  " + pecaGroupW.getChildren().size());
        labelPreta.setText("Pretas   -  " + pecaGroupB.getChildren().size());
        
        labelPeca.setLayoutX(510);
        labelPeca.setLayoutY(10);
        labelBranca.setLayoutX(510);
        labelBranca.setLayoutY(50);
        labelPreta.setLayoutX(510);
        labelPreta.setLayoutY(90);
        labelPeca.setFont(new Font("Verdana", 18));
        
        
        //Peças
        if(this.jogador_ID==1){
            circuloBrancas.setCenterX(560);
            circuloBrancas.setCenterY(250);
            circuloBrancas.setRadius(30);
            circuloBrancas.setFill(Color.WHITE);
        }else{
            circuloPretas.setCenterX(560);
            circuloPretas.setCenterY(250);
            circuloPretas.setRadius(30); 
            circuloPretas.setFill(Color.BLACK);
        }
           
        
        root.getChildren().addAll(labelPeca, labelBranca, labelPreta, circuloPretas, circuloBrancas);

        return root;
    }
    
       
    private void turnosFeitos(){
       turnosFeitos++;
       System.out.println("Turnos feitos: " + turnosFeitos);    
    }
    private void blockearJogadas(){
            for (Node p : pecaGroupW.getChildren()){
                    ((Peca)p).setMoveDisabled(true);
                }
                for(Node p : pecaGroupB.getChildren()){
                    ((Peca)p).setMoveDisabled(true);
                    }
    }
   private void mediaMovimento() {
    String filePath = "C:/Users/User/Desktop/Alquerque/src/alquerque/movimento.wav";
    Media media = new Media("file:///" + filePath.replace("\\", "/"));
    MediaPlayer move = new MediaPlayer(media);
    move.play();
}

    private int recebeID(int x, int y){
            for(k = 0; k < (pecaGroupB.getChildren().size() - 1); k++){
                if (x == (int)pecaGroupB.getChildren().get(k).getLayoutX()/100 && y == (int)pecaGroupB.getChildren().get(k).getLayoutY()/100){   
                    return k;
                } 
            }
            for(k = 0; k < (pecaGroupW.getChildren().size() - 1); k++){
                if (x == (int)pecaGroupW.getChildren().get(k).getLayoutX()/100 && y == (int)pecaGroupW.getChildren().get(k).getLayoutY()/100){   
                    return k;
                } 
            }
            
            return -1;   
    }
    
    private MovimentoResultado tentaMovimento(Peca piece, int newX, int newY){
        int x=newX;
        int y=newY;
        recebeID(x,y);


        if ((newX==pecaGroupW.getChildren().get(k).getLayoutX()/100 && newY==pecaGroupW.getChildren().get(k).getLayoutY()/100) || (newX==pecaGroupB.getChildren().get(k).getLayoutX()/100 && newY==pecaGroupB.getChildren().get(k).getLayoutY()/100)){
           this.label1.setText("Jogada Inválida. Repete!");
           turnosFeitos();
           return new MovimentoResultado(MovimentoTipo.None); 
        }
       int x0 = toBoard(piece.getOldX());
       int y0 = toBoard(piece.getOldY());
       
       if ((Math.abs(newX - x0) == 0 && Math.abs(newY - y0) == 1) || (Math.abs(newY - y0) == 0 && Math.abs(newX - x0) == 1) || (Math.abs(newX - x0) == 1 && Math.abs(newY - y0) != 2) ){
                
          this.label1.setText("Fizeste a tua jogada. Espera pela jogada do jogador " + outroJogador);
          turnosFeitos();
          
          String filePath = "C:/Users/User/Desktop/Alquerque/src/alquerque/movimento.wav";
          Media media = new Media("file:///" + filePath.replace("\\", "/"));
          MediaPlayer move = new MediaPlayer(media);
          move.play();
          
            if (this.jogador_ID==1){
            for (Node p : pecaGroupW.getChildren()){
                    ((Peca)p).setMoveDisabled(true);
                }
                for(Node p : pecaGroupB.getChildren()){
                    ((Peca)p).setMoveDisabled(true);
            }}
                           if (this.jogador_ID==2){
            for (Node p : pecaGroupW.getChildren()){
                    ((Peca)p).setMoveDisabled(true);
                }
                for(Node p : pecaGroupB.getChildren()){
                    ((Peca)p).setMoveDisabled(true);
            }}
           return new MovimentoResultado(MovimentoTipo.Normal);
       } else if (Math.abs(newY - y0) == 2 && !(Math.abs(newX - x0) == 1) || Math.abs(newX - x0) == 2 && !(Math.abs(y0 - newY) == 1)) {
            this.label1.setText("Jogada Feita. Espera pela jogada do jogador " + outroJogador);
            turnosFeitos();
            mediaMovimento();
            
           x_1 = x0 + (newX - x0) / 2;
           y_1 = y0 + (newY - y0) / 2;
           
           x_1_a=x_1*100;  
           y_1_a=y_1*100;           
           
           antigo_X_pc = pecaGroupW.getChildren().get(1).getLayoutX();
           antigo_Y_pc = pecaGroupW.getChildren().get(1).getLayoutY();
           antigo_X_Int=(int)antigo_X_pc;
           antigo_Y_Int=(int)antigo_Y_pc;                        
                   
           System.out.println("x1 - " + x_1 + " e y1 - " + y_1 );
           System.out.println("antigo_X_pc " + antigo_X_pc );
           System.out.println("antigo_X " + board1[x0][y0].getPiece().getOldX());

                   
           System.out.println(pecaGroupW.getChildren().get(1).getLayoutX());   
           System.out.println(x_1_a);
           System.out.println(pecaGroupW.getChildren().get(1).getLayoutY());
           System.out.println(y_1_a);
           
           int k;
           if (this.jogador_ID == 1){
              for(k = 0; k < (pecaGroupB.getChildren().size() - 1); k++){    
                if (pecaGroupB.getChildren().get(k).getLayoutX() == x_1_a && pecaGroupB.getChildren().get(k).getLayoutY() == y_1_a){
                    id_morto=k;
                }
            }
           } else {
                   for(k = 0; k < (pecaGroupW.getChildren().size() - 1); k++){
                if (pecaGroupW.getChildren().get(k).getLayoutX() == x_1_a && pecaGroupW.getChildren().get(k).getLayoutY() == y_1_a){
                    id_morto=k;
                }
            }
           }
           
           
            System.out.println("idmorto - " + id_morto);
           if ((pecaGroupW.getChildren().get(id_morto).getLayoutX() == x_1_a && pecaGroupW.getChildren().get(id_morto).getLayoutY() == y_1_a) || (pecaGroupB.getChildren().get(id_morto).getLayoutX() == x_1_a && pecaGroupB.getChildren().get(id_morto).getLayoutY() == y_1_a) ){
           System.out.println("Kill");
           this.label1.setText("Fizeste a tua jogada. Espera pela jogada do jogador " + outroJogador);
           turnosFeitos();
               if (this.jogador_ID==1){
            for (Node p : pecaGroupW.getChildren()){
                    ((Peca)p).setMoveDisabled(false);
                }
                for(Node p : pecaGroupB.getChildren()){
                    ((Peca)p).setMoveDisabled(true);
            }
               }else{ 
            for (Node p : pecaGroupW.getChildren()){
                    ((Peca)p).setMoveDisabled(true);
                }
                for(Node p : pecaGroupB.getChildren()){
                    ((Peca)p).setMoveDisabled(false);
            }}

                
               String filePath = "C:/Users/User/Desktop/Alquerque/src/alquerque/som_comer.mp3";
               Media media = new Media("file:///" + filePath.replace("\\", "/"));
               MediaPlayer captura = new MediaPlayer(media);
               captura.play();
                
               return new MovimentoResultado(MovimentoTipo.mata);
               
           }
       }
       
       
      
       return new MovimentoResultado(MovimentoTipo.None);
    }
    
    private int toBoard(double pixel){
        return (int)(pixel+Tabuleiro_T / 2) / Tabuleiro_T;
        
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        
        String backgroundMusicPath = null;
        try {
            backgroundMusicPath = Paths.get(getClass().getResource("musica_fundo.mp3").toURI()).toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            
        }
        Media media = new Media(new File(backgroundMusicPath).toURI().toString());
        MediaPlayer player = new MediaPlayer(media);
        player.play();
        
        
         EventHandler<ActionEvent> Evento = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                Stage stage = new Stage();
                stage.setTitle("Ajuda");
                stage.setScene(new Scene(root1));  
                stage.show();
               
             
            }
        };
         
        EventHandler<ActionEvent> evento2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
               Platform.exit();
            }
        };
        
        EventHandler<ActionEvent> evento3 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                     if (player.getStatus()==PLAYING){
                        player.stop(); 
                        player.play();
                     }else {
                     player.play();
                    }
            }
        };
        
       EventHandler<ActionEvent> evento4 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
               player.stop();
            }
        };

        butao.setOnAction(Evento);
        butao2.setOnAction(evento3);
        butao3.setOnAction(evento4);
        Scene scene2 = new Scene(criaContent());
        
        stage.setTitle("Alquerque");
        stage.setScene(scene2);
        stage.show();

    }

    private Peca criaPeca(PecaTipo type, int x, int y) {
        Peca peca = new Peca(type, x, y);

        peca.setOnMouseReleased(e -> {
            if (!peca.isMoveDisabled()) {
                int newX = toBoard(peca.getLayoutX());
                int newY = toBoard(peca.getLayoutY());

                MovimentoResultado result = tentaMovimento(peca, newX, newY);

                int x0 = toBoard(peca.getOldX());
                int y0 = toBoard(peca.getOldY());

                switch (result.getType()) {
                    case None:
                        peca.abortMove();
                        break;
                    case Normal:
                        peca.x = newX;
                        peca.y = newY;
                        peca.move(newX, newY);
                        board1[x0][y0].setPiece(null);
                        board1[newX][newY].setPiece(peca);
                        enviaDados();
                        break;
                    case mata:
                        peca.x = newX;
                        peca.y = newY;
                        peca.move(newX, newY);
                        board1[x0][y0].setPiece(null);
                        board1[newX][newY].setPiece(peca);
                        if (this.jogador_ID == 1){
                            pecaGroupB.getChildren().remove(id_morto);
                        } else {
                            pecaGroupW.getChildren().remove(id_morto);
                        }
                        enviaDados();
                        verificaFim(pecaGroupW.getChildren().size(), peca);
                        verificaFim(pecaGroupB.getChildren().size(), peca);
                        break;    
                }
            }
        });

        return peca;
    }
     
    public void verificaFim(int n,Peca peca){
        if (n == 0){ 
            a.setAlertType(Alert.AlertType.INFORMATION);
            if (peca.getType() == PecaTipo.White){
                a.setContentText("O Jogador 1 (Brancas) - foi o vencedor!");
            } else {
                a.setContentText("O Jogador 2 (Pretas) - foi o vencedor!");
            }  
            a.show();
        }
    }
    
    public void enviaDados() {
        try {
            int qntPecas = this.pecaGroupB.getChildren().size() + this.pecaGroupW.getChildren().size(); 
            c_s_c.getDataOutputStream().writeInt(qntPecas);

            // enviar peças pretas
            for(Node nodePeca : this.pecaGroupB.getChildren()){
                Peca peca = ((Peca)nodePeca);
                // quantidade de valores do array (X,Y,COR)
                c_s_c.getDataOutputStream().writeInt(3);

                c_s_c.getDataOutputStream().writeInt(peca.x);
                c_s_c.getDataOutputStream().writeInt(peca.y);
                c_s_c.getDataOutputStream().writeInt(0);
            }

            // enviar peças brancas
            for(Node nodePeca : this.pecaGroupW.getChildren()){
                Peca peca = ((Peca)nodePeca);
                // quantidade de valores do array (X,Y,COR)
                c_s_c.getDataOutputStream().writeInt(3);

                c_s_c.getDataOutputStream().writeInt(peca.x);
                c_s_c.getDataOutputStream().writeInt(peca.y);
                c_s_c.getDataOutputStream().writeInt(1);
            }
            
            atualizaDados();
       
            c_s_c.getDataOutputStream().flush();
        } catch(Exception e){
            
        }
    }
    
    private void atualizaDados(){
        labelBranca.setText("Brancas -  " + pecaGroupW.getChildren().size());
        labelPreta.setText("Pretas   -  " + pecaGroupB.getChildren().size());
    }
    public Alquerque (){
        this.conexaoToServer();
        
       
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
               
        launch(args);
    }

    void adicionarPecasRecebidas(int[][] posicoesPecas){
        ArrayList<Node> pPretas = new ArrayList();
        ArrayList<Node> pBrancas = new ArrayList();
        int x = 0;
        int y = 0;
        // para cada peça
        for(int[] peca : posicoesPecas){
            
            if(peca[2] == 0){
                // preta
                
                Peca p = criaPeca(PecaTipo.Black, peca[0], peca[1]);
                if(jogador_ID == 1)
                    p.setMoveDisabled(true);
                
                else
                    p.setMoveDisabled(false);
                pPretas.add(p);
            } else {
                // branca
                Peca p = criaPeca(PecaTipo.White, peca[0], peca[1]);
                if(jogador_ID == 2)
                    p.setMoveDisabled(true);
                else
                    p.setMoveDisabled(false);
                pBrancas.add(p);
            }
        }
        
        System.out.println(pecaGroupB.getChildren().size());
        System.out.println(pecaGroupW.getChildren().size());
        atualizaDados();
                
        Platform.runLater(new Runnable() {
            @Override public void run() {
                
               
                // apagar
                pecaGroupB.getChildren().clear();
                pecaGroupW.getChildren().clear();
                label1.setText("O jogador " + outroJogador + " jogou. É a tua vez!");
                // adicionar
                pecaGroupB.getChildren().addAll(pPretas);
                pecaGroupW.getChildren().addAll(pBrancas);
                 pPretas.removeAll(pPretas);
                pBrancas.removeAll(pBrancas);
              
            }
        });
    }
}
