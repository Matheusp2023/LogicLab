/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/NetBeansModuleDevelopment-files/templateDataObjectAnno.java to edit this template
 */
package org.ufal.logiclabfilesupport.file;

import java.io.IOException;
import org.netbeans.core.spi.multiview.MultiViewElement;
import org.netbeans.core.spi.multiview.text.MultiViewEditorElement;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.MIMEResolver;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

@Messages({
    "LBL_LogicDiagram_LOADER=Files of LogicDiagram"
})
@MIMEResolver.ExtensionRegistration(
        displayName = "#LBL_LogicDiagram_LOADER",
        mimeType = "text/logicdiagram-xml",
        extension = {"xld"}
)
@DataObject.Registration(
        mimeType = "text/logicdiagram-xml",
        iconBase = "org/ufal/logiclabfilesupport/resources/logic-diagram-icon.png",
        displayName = "#LBL_LogicDiagram_LOADER",
        position = 300
)
@ActionReferences({
    @ActionReference(
            path = "Loaders/text/logicdiagram-xml/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.OpenAction"),
            position = 100,
            separatorAfter = 200
    ),
    @ActionReference(
            path = "Loaders/text/logicdiagram-xml/Actions",
            id = @ActionID(category = "Edit", id = "org.openide.actions.CutAction"),
            position = 300
    ),
    @ActionReference(
            path = "Loaders/text/logicdiagram-xml/Actions",
            id = @ActionID(category = "Edit", id = "org.openide.actions.CopyAction"),
            position = 400,
            separatorAfter = 500
    ),
    @ActionReference(
            path = "Loaders/text/logicdiagram-xml/Actions",
            id = @ActionID(category = "Edit", id = "org.openide.actions.DeleteAction"),
            position = 600
    ),
    @ActionReference(
            path = "Loaders/text/logicdiagram-xml/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.RenameAction"),
            position = 700,
            separatorAfter = 800
    )
})
public class LogicDiagramDataObject extends MultiDataObject {

    public LogicDiagramDataObject(FileObject pf, MultiFileLoader loader) throws DataObjectExistsException, IOException {
        super(pf, loader);
        registerEditor("text/logicdiagram-xml", true);
    }

    @Override
    protected int associateLookup() {
        return 1;
    }
    
    @Override
    protected Node createNodeDelegate() {
        return new LogicDiagramDataNode(this);
    }

    @MultiViewElement.Registration(
            displayName = "#LBL_LogicDiagram_EDITOR",
            iconBase = "org/ufal/logiclabfilesupport/resources/logic-diagram-icon.png",
            mimeType = "text/logicdiagram-xml",
            persistenceType = TopComponent.PERSISTENCE_ONLY_OPENED,
            preferredID = "LogicDiagram",
            position = 1000
    )
    @Messages("LBL_LogicDiagram_EDITOR=Source")
    public static MultiViewEditorElement createEditor(Lookup lkp) {
        return new MultiViewEditorElement(lkp);
    }

}
