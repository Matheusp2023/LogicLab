/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ufal.logiclabproject.actions;

import org.netbeans.spi.project.ActionProvider;
import org.openide.util.Lookup;

/**
 *
 * @author Matheus Pedro (mps@ic.ufal.br)
 */
public class ActionProviderImpl implements ActionProvider {

    @Override
    public String[] getSupportedActions() {
        return new String[0];
    }

    @Override
    public void invokeAction(String string, Lookup lkp) throws IllegalArgumentException {
        // do nothing
    }

    @Override
    public boolean isActionEnabled(String string, Lookup lkp) throws IllegalArgumentException {
        return false;
    }
    
}
