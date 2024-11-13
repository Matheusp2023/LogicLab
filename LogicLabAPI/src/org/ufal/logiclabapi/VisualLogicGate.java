/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ufal.logiclabapi;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Matheus Pedro (mps@ic.ufal.br)
 */
public abstract class VisualLogicGate implements LogicGate {
    
    protected Point position; // Posição no grid (x, y)
    protected String id; // Identificador único
    protected ImageIcon image; // Imagem que representa a porta lógica
    protected List<VisualLogicGate> inputs; // Conexões de entrada
    protected VisualLogicGate output; // Conexão de saída
    protected JPanel gatePanel; // Componente de interface para a porta lógica
    protected int maxInputs; // Número máximo de conexões de entrada

    public VisualLogicGate(String id, int x, int y, ImageIcon image, int maxInputs) {
        this.id = id;
        this.position = new Point(x, y);
        this.image = image;
        this.inputs = new ArrayList<>();
        this.maxInputs = maxInputs;
    }

    /**
     * Define a posição da porta lógica no grid.
     * @param x Coordenada X.
     * @param y Coordenada Y.
     */
    public void setPosition(int x, int y) {
        this.position.setLocation(x, y);
        updatePanelPosition();
    }

    /**
     * Atualiza a posição do painel da porta lógica para refletir a posição atual.
     */
    private void updatePanelPosition() {
        if (gatePanel != null) {
            gatePanel.setLocation(position);
        }
    }

    public int getX() {
        return position.x;
    }

    public int getY() {
        return position.y;
    }

    public String getId() {
        return id;
    }

    public ImageIcon getImage() {
        return image;
    }
    
    public JPanel getGatePanel() {
        return gatePanel;
    }

    public void setGatePanel(JPanel gatePanel) {
        this.gatePanel = gatePanel;
        updatePanelPosition(); // Atualiza a posição do painel ao definir
    }

    /**
     * Adiciona uma porta lógica como entrada, se não exceder o número máximo de entradas.
     * @param inputGate A porta lógica de entrada.
     * @throws IllegalArgumentException Se o número de entradas exceder o máximo permitido.
     */
    public void addInput(VisualLogicGate inputGate) {
        if (inputGate == null) {
            throw new IllegalArgumentException("A porta de entrada não pode ser nula.");
        }
        if (inputs.size() >= maxInputs) {
            throw new IllegalArgumentException("Número máximo de entradas excedido.");
        }
        inputs.add(inputGate);
    }

    /**
     * Define a saída da porta lógica.
     * @param outputGate A porta lógica de saída.
     */
    public void setOutput(VisualLogicGate outputGate) {
        this.output = outputGate;
    }

    public List<VisualLogicGate> getInputs() {
        return inputs;
    }

    public VisualLogicGate getOutput() {
        return output;
    }

}
