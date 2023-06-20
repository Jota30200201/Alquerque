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
public enum PecaTipo {
    Black(1), White(-1);
    
    final int moveDir;
    PecaTipo(int moveDir){
        this.moveDir = moveDir;
    }
}
