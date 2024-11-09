/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ufal.logiclabproject;

import java.awt.Image;
import org.netbeans.spi.project.ui.LogicalViewProvider;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataFolder;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.ImageUtilities;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ProxyLookup;
import org.ufal.logiclabproject.project.LogicLabProject;

/**
 *
 * @author Matheus Pedro (mps@ic.ufal.br)
 */
public class LogicLabLogicalView implements LogicalViewProvider {
    
    private final LogicLabProject project;
    
    public LogicLabLogicalView(LogicLabProject project) {
        this.project = project;
    }

    @Override
    public Node createLogicalView() {
        // Obter o diretório de cenas, criando se excluído:
        FileObject circuits = project.getCircuitsFolder(true);
        // Obtenha o DataObject que o representa:
        DataFolder circuitsDataObject = DataFolder.findFolder(circuits);
        //Obtem o nó padrão
        //nome de exibição, ícone, etc.
        Node realCircuitsFolderNode = circuitsDataObject.getNodeDelegate();
        // Este FilterNode será o nó do nosso projeto:
        return new CircuitsNode(realCircuitsFolderNode, project);
    }

    @Override
    public Node findPath(Node root, Object target) {
        //deixe sem implementar por enquanto
        return null;
    }
    
    /**
    * Este é o nó que você realmente vê na janela Projetos para o projeto
    */
    private static final class CircuitsNode extends FilterNode {
        
        final LogicLabProject project;

        public CircuitsNode(Node node, LogicLabProject project) {
            super(node, new FilterNode.Children(node),
                    //O sistema de projetos quer o projeto na pesquisa do Node.
                    //NewAction e amigos querem a pesquisa do Node original.
                    //Faça uma mesclagem de ambos:
                    new ProxyLookup(
                        Lookups.singleton(project),
                        node.getLookup())
                    );
            this.project = project;
        }
        
        @Override
        public Image getIcon(int type) {
            return ImageUtilities.loadImage(
                    "org/ufal/logiclabproject/resources/circuit_icon_16.png");
        }

        @Override
        public Image getOpenedIcon(int type) {
            return getIcon(type);
        }

        @Override
        public String getDisplayName() {
            return project.getProjectDirectory().getName();
        }
    }
    
}
