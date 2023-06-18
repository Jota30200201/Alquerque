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
 * @author joaop
 */
public class Tile extends Rectangle {
    private Piece piece;
    private int tileX, tileY;
    
    public boolean hasPiece(){
        return piece !=null;
    }
    public Piece getPiece(){
        return piece;
    }
    public void setPiece(Piece piece){
        this.piece=piece;
    }

    public int getTileX() {
        return tileX;
    }

    public int getTileY() {
        return tileY;
    }
    
    
    public Tile(boolean light, int x, int y){
        setWidth(TrabalhoPratico.Tile_Size);
        setHeight(TrabalhoPratico.Tile_Size);
        tileX=x;
        tileY=y;
        relocate(x * TrabalhoPratico.Tile_Size, y * TrabalhoPratico.Tile_Size);
        
        setFill(light ? Color.valueOf("#B0C4DE") : Color.valueOf("FF8C00"));
        
        //setOpacity(0.5);
    }
}
