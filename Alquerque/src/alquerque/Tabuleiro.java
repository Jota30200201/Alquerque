/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquerque;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 *
 * @author joaorosa
 */
public class Tabuleiro extends Rectangle {
    private Peca piece;
    private int tileX, tileY;
    
    public boolean hasPiece(){
        return piece !=null;
    }
    public Peca getPiece(){
        return piece;
    }
    public void setPiece(Peca piece){
        this.piece=piece;
    }

    public int getTileX() {
        return tileX;
    }

    public int getTileY() {
        return tileY;
    }
    
    
    public Tabuleiro(boolean light, int x, int y){
        setWidth(Alquerque.Tabuleiro_T);
        setHeight(Alquerque.Tabuleiro_T);
        tileX=x;
        tileY=y;
        relocate(x * Alquerque.Tabuleiro_T, y * Alquerque.Tabuleiro_T);
        
        setFill(light ? Color.valueOf("#B0C4DE") : Color.valueOf("FF8C00"));
        
        //setOpacity(0.5);
    }
}
