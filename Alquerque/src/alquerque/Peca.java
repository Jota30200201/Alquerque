/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquerque;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

import static alquerque.Alquerque.Tabuleiro_T;
/**
 *
 * @author joaorosa
 */
public class Peca extends StackPane {
    private PecaTipo type;
    private boolean moveDisabled = false;
    
    private double mouseX, mouseY;
    private double oldX, oldY;
    public int x,y;
    
    public PecaTipo getType(){
        return type;
    }
    
    public double getOldX(){
        return oldX;
    }
    public double getOldY(){
        return oldY;
    }
    
    public Peca(PecaTipo type, int x, int y){
        this.type= type;
        this.x = x;
        this.y = y;
        
        move(x, y);
        
        Ellipse bg = new Ellipse(Tabuleiro_T*0.33, Tabuleiro_T*0.33);
                bg.setFill(Color.BLACK);
                bg.setStroke(Color.BLACK);
                bg.setStrokeWidth(Tabuleiro_T*0.03);
        
                bg.setTranslateX((Tabuleiro_T - Tabuleiro_T * 0.33 * 2 ) / 2);
                bg.setTranslateY((Tabuleiro_T - Tabuleiro_T * 0.33 * 2 ) / 2);
                
        Ellipse ellipse = new Ellipse(Tabuleiro_T*0.33, Tabuleiro_T*0.33);
                bg.setFill(type == PecaTipo.Black 
                        ? Color.valueOf("#000000") : Color.valueOf("#ffffff"));
                bg.setStroke(type == PecaTipo.Black 
                        ? Color.valueOf("#ffffff") : Color.valueOf("#000000"));
                bg.setStrokeWidth(Tabuleiro_T*0.03);
        
                bg.setTranslateX((Tabuleiro_T - Tabuleiro_T * 0.33 * 2 ) / 2);
                bg.setTranslateY((Tabuleiro_T - Tabuleiro_T * 0.33 * 2 ) / 2);
                
                getChildren().addAll(bg);
                
                setOnMousePressed(e -> {
                    mouseX = e.getSceneX();
                    mouseY = e.getSceneY();
                });
                setOnMouseDragged(e -> {
                    if(!moveDisabled)
                        relocate(e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldY);
                    else
                        System.out.println("Movimento da peça desativado");
                    
                    
                });
    }
    
    public void setMoveDisabled(boolean m){
        this.moveDisabled = m;
    }
    
    public void move(int x, int y){
        oldX = x * Tabuleiro_T;
        oldY = y * Tabuleiro_T;
        relocate(oldX, oldY);
    }
    public boolean isMoveDisabled(){
        return this.moveDisabled;
    }
    
    public void abortMove(){
        relocate(oldX, oldY);
    }
}
