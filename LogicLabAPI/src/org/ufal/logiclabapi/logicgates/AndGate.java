/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ufal.logiclabapi.logicgates;

import javax.swing.ImageIcon;
import org.ufal.logiclabapi.VisualLogicGate;

/**
 *
 * @author Matheus Pedro (mps@ic.ufal.br)
 */
public class AndGate extends VisualLogicGate {
    
    private boolean input1;
    private boolean input2;

    public AndGate(String id, int x, int y, ImageIcon image, int maxInput) {
        super(id, x, y, image, maxInput);
    }
    
    @Override
    public boolean computeOutput() {
        return input1 && input2;
    }
    
}
