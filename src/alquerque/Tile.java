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
 * @author MSI Gaming
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
        setWidth(Alquerque.Tile_Size);
        setHeight(Alquerque.Tile_Size);
        tileX=x;
        tileY=y;
        relocate(x * Alquerque.Tile_Size, y * Alquerque.Tile_Size);
        
        setFill(light ? Color.valueOf("#89CFF0") : Color.valueOf("#FFA500"));
        
        //setOpacity(0.5);
    }
    
    
    
}