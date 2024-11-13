/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ufal.logiclabfilesupport.file;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataNode;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.ufal.logiclabapi.controller.AbstractLogicLabController;
import org.ufal.logiclabapi.MainFileProvider;
import org.ufal.logiclabfilesupport.ui.CircuitEditorTopComponent;

/**
 *
 * @author Matheus Pedro (mps@ic.ufal.br)
 */
public class LogicDiagramDataNode extends DataNode {
    
    public LogicDiagramDataNode(LogicDiagramDataObject obj) {
        super(obj, Children.LEAF);
    }
    
    private FileObject getFile() {
        return getDataObject().getPrimaryFile();
    }

    private Object getFromProject (Class clazz) {
        Object result;
        Project p = FileOwnerQuery.getOwner(getFile());
        if (p != null) {
            result = p.getLookup().lookup (clazz);
        } else {
            result = null;
        }
        return result;
    }

    private boolean isMainFile() {
        MainFileProvider prov = (MainFileProvider)getFromProject (MainFileProvider.class);
        boolean result;
        if (prov == null) {
            result = false;
        } else {
            FileObject myFile = getFile();
            result = prov.isMainFile(myFile);
        }
        return result;
    }
    
    @Override
    public void setDisplayName(String name) {
        if (name.endsWith(AbstractLogicLabController.LOGIC_DIAGRAM_EXTENSION)) {
            super.setDisplayName(name.replace(AbstractLogicLabController.LOGIC_DIAGRAM_EXTENSION, ""));
        }
    }

    @Override
    public String getHtmlDisplayName() {
        return isMainFile() ? "<b>" + getDisplayName() + "</b>" : null;
    }
    
    @Override
    public Action[] getActions (boolean popup) {
        Action[] actions = super.getActions(popup);
        Action[] result;
        if (actions.length > 0) { //should always be > 0
            result = new Action[ actions.length + 1 ];
            int i = 0;
            for (Action action : actions) {
                result[i] = action;
                i++;
            }
            result[actions.length] = new SetMainFileAction();
        } else {
            //Isolated file in the favorites window or something
            result = actions;
        }
        return result;
    }
    
    @Override
    public Action getPreferredAction() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e)  {
                FileObject fileObject = getFile();
                if (fileObject != null) {
                    openTopComponent(fileObject);
                }
            }
        };
    }
    
    private void openTopComponent(FileObject fileObject) {
        CircuitEditorTopComponent topComponent = findTopComponent(fileObject);
        if (topComponent == null) {
            topComponent = new CircuitEditorTopComponent(fileObject);
            topComponent.open();
        }
        topComponent.requestActive();
    }
    
    private CircuitEditorTopComponent findTopComponent(FileObject fileObject) {
        for (TopComponent tc : TopComponent.getRegistry().getOpened()) {
            if (tc instanceof CircuitEditorTopComponent &&
                    ((CircuitEditorTopComponent) tc).getFileObject().equals(fileObject)) {
                return (CircuitEditorTopComponent) tc;
            }
        }
        return null;
    }
    
    @NbBundle.Messages("CTL_SetMainFile=Set Main File")
    private final class SetMainFileAction extends AbstractAction {

        public SetMainFileAction() {
            putValue(NAME, Bundle.CTL_SetMainFile());
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            MainFileProvider provider = (MainFileProvider) getFromProject(MainFileProvider.class);
            FileObject oldMain = provider.getMainFile();
            provider.setMainFile(getFile());
            fireDisplayNameChange(getDisplayName(), getHtmlDisplayName());
            if (oldMain != null) {
                try {
                    Node oldMainFilesNode = DataObject.find(oldMain).getNodeDelegate();
                    if (oldMainFilesNode instanceof LogicDiagramDataNode) {
                        ((LogicDiagramDataNode) oldMainFilesNode).fireDisplayNameChange(null, oldMainFilesNode.getDisplayName());
                    }
                } catch (DataObjectNotFoundException donfe) { //Should never happen
                    Exceptions.printStackTrace(donfe);
                }
            }
        }

        @Override
        public boolean isEnabled() {
            return !isMainFile() && getFromProject(MainFileProvider.class) != null;
        }

    }
    
}
