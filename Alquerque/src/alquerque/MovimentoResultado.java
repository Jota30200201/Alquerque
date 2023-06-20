/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquerque;

/**
 *
 * @author joaorosa
 */
public class MovimentoResultado {
    
    private MovimentoTipo type;
    
    public MovimentoTipo getType(){
        return type;
    }
    
    private Peca piece;
    
    public Peca getPiece(){
        return piece;
    }
    
    public MovimentoResultado(MovimentoTipo type){
        this(type, null);
    }
    
    public MovimentoResultado(MovimentoTipo type, Peca piece){
        this.type = type;
        this.piece = piece;
    }  
}

