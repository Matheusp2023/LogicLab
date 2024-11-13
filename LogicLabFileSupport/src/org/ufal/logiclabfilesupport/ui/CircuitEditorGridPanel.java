/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.ufal.logiclabfilesupport.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;
import org.ufal.logiclabapi.VisualLogicGate;
import org.ufal.logiclabapi.logicgates.AndGate;
import org.ufal.logiclabapi.logicgates.NotGate;
import org.ufal.logiclabapi.logicgates.OrGate;

/**
 *
 * @author Matheus Pedro (mps@ic.ufal.br)
 */
public class CircuitEditorGridPanel extends javax.swing.JPanel {
    
    private final static int GRID_SIZE = 20;
    private final static int GATE_PANEL_WIDTH_SIZE = 60;
    private final static int GATE_PANEL_HEIGHT_SIZE = 60;

    private final Map<String, VisualLogicGate> gateMap = new HashMap<>();

    private int offsetX = 0, offsetY = 0; // Deslocamento do grid
    private Point dragStartPoint; // Ponto de início do clique para arrastar
    private BufferedImage gridImage;

    public CircuitEditorGridPanel() {
        initComponents();
        setLayout(null);
        createGridImage();

        // Adiciona arraste global para o fundo do grid
        addGridDragFunctionality();
    }

    public void addGate(String gateType, int x, int y) {
        int alignedX = (x / GRID_SIZE) * GRID_SIZE;
        int alignedY = (y / GRID_SIZE) * GRID_SIZE;

        String gateName;
        do {
            gateName = requestGateName();
            if (gateName == null) {
                return;
            }

            if (!gateName.trim().isEmpty()) {
                if (gateMap.containsKey(gateName)) {
                    showErrorMessage("LBL_LOGIC_GATE_NAME_SAME", "LBL_LOGIC_GATE_NAME_INVALID_ERROR");
                    gateName = null;
                }
            }
        } while (gateName == null || gateName.trim().isEmpty());

        JPanel gatePanel = new JPanel();
        gatePanel.setBounds(alignedX + offsetX, alignedY + offsetY, GATE_PANEL_WIDTH_SIZE, GATE_PANEL_HEIGHT_SIZE);
        gatePanel.setOpaque(false);

        VisualLogicGate gate = createGate(gateType, gateName, alignedX, alignedY);
        if (gate != null) {
            addGatePanel(gatePanel, gate);
        }
        
        addDragFunctionality(gate);

        add(gatePanel);
        gateMap.put(gateName, gate);
        revalidate();
        repaint();
    }

    private void addDragFunctionality(VisualLogicGate gate) {
        final Point[] dragStartPointPanel = {null};
        
        JPanel gatePanel = gate.getGatePanel();

        gatePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dragStartPointPanel[0] = e.getPoint();
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                gatePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                gatePanel.setBorder(BorderFactory.createEmptyBorder());
            }
        });

        gatePanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (dragStartPointPanel[0] != null) {
                    int deltaX = e.getX() - dragStartPointPanel[0].x;
                    int deltaY = e.getY() - dragStartPointPanel[0].y;

                    Point currentLocation = gatePanel.getLocation();
                    int newX = currentLocation.x + deltaX;
                    int newY = currentLocation.y + deltaY;

                    // Alinha a posição do painel com o grid
                    newX = (newX / GRID_SIZE) * GRID_SIZE;
                    newY = (newY / GRID_SIZE) * GRID_SIZE;

                    gate.setPosition(newX, newY);
                    repaint();
                }
            }
        });
    }

    private void addGridDragFunctionality() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dragStartPoint = e.getPoint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (dragStartPoint != null) {
                    int deltaX = e.getX() - dragStartPoint.x;
                    int deltaY = e.getY() - dragStartPoint.y;

                    offsetX += deltaX;
                    offsetY += deltaY;

                    // Atualiza a posição de todas as portas lógicas com o deslocamento global
                    for (VisualLogicGate gate : gateMap.values()) {
                        JPanel gatePanel = gate.getGatePanel();
                        Point currentLocation = gatePanel.getLocation();
                        gate.setPosition(currentLocation.x + deltaX, currentLocation.y + deltaY);
                    }

                    dragStartPoint = e.getPoint();
                    repaint();
                }
            }
        });
    }

    private String requestGateName() {
        return JOptionPane.showInputDialog(this, 
                NbBundle.getMessage(CircuitEditorGridPanel.class, "LBL_LOGIC_GATE_NAME_REQUEST"), 
                NbBundle.getMessage(CircuitEditorGridPanel.class, "LBL_LOGIC_GATE_NAME_TITLE_DIALOG"), 
                JOptionPane.PLAIN_MESSAGE);
    }

    private void showErrorMessage(String messageKey, String titleKey) {
        JOptionPane.showMessageDialog(this, 
                NbBundle.getMessage(CircuitEditorGridPanel.class, messageKey), 
                NbBundle.getMessage(CircuitEditorGridPanel.class, titleKey), 
                JOptionPane.ERROR_MESSAGE);
    }

    private VisualLogicGate createGate(String gateType, String gateName, int x, int y) {
        switch (gateType) {
            case "AND" -> { return new AndGate(gateName, x, y, getGateIcon(gateType), 2); }
            case "OR" -> { return new OrGate(gateName, x, y, getGateIcon(gateType), 2); }
            case "NOT" -> { return new NotGate(gateName, x, y, getGateIcon(gateType), 2); }
            default -> { return null; }
        }
    }

    private void addGatePanel(JPanel gatePanel, VisualLogicGate gate) {
        JLabel gateImage = new JLabel();
        ImageIcon icon = gate.getImage();
        if (icon != null) {
            gateImage.setIcon(icon);
        }
        gatePanel.add(gateImage);
        gate.setGatePanel(gatePanel);
    }

    private ImageIcon getGateIcon(String gateType) {
        String imagePath = switch (gateType) {
            case "AND" -> "org/ufal/logiclabfilesupport/resources/logic-gate-and.png";
            case "OR" -> "org/ufal/logiclabfilesupport/resources/logic-gate-or.png";
            case "NOT" -> "org/ufal/logiclabfilesupport/resources/logic-gate-not.png";
            default -> null;
        };
        try {
            return imagePath != null ? new ImageIcon(ImageUtilities.loadImage(imagePath)) : null;
        } catch (Exception e) {
            Logger.getLogger(CircuitEditorGridPanel.class.getName()).log(Level.SEVERE, "Erro ao carregar ícone", e);
            return null;
        }
    }

    private void createGridImage() {
        gridImage = new BufferedImage(GRID_SIZE, GRID_SIZE, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = gridImage.createGraphics();
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillOval(0, 0, 2, 2);
        g2d.dispose();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();

        for (int x = offsetX % GRID_SIZE; x < width; x += GRID_SIZE) {
            for (int y = offsetY % GRID_SIZE; y < height; y += GRID_SIZE) {
                g2d.drawImage(gridImage, x, y, null);
            }
        }
    }

    private void resetOffset() {
        offsetX = 0;
        offsetY = 0;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
