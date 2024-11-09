/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ufal.logiclabproject.project;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectInformation;
import org.netbeans.spi.project.ProjectState;
import org.openide.filesystems.FileObject;
import org.openide.util.*;
import org.openide.util.lookup.Lookups;
import org.ufal.logiclabproject.actions.ActionProviderImpl;

/**
 *
 * @author Matheus Pedro (mps@ic.ufal.br)
 */
public class LogicLabProject implements Project {
    
    public static final String CIRCUITS_DIR = "circuits";
    
    private final FileObject projectDir;
    private final ProjectState state;
    private Lookup lookup;

    public LogicLabProject(FileObject projectDir, ProjectState state) {
        this.projectDir = projectDir;
        this.state = state;
    }

    @Override
    public FileObject getProjectDirectory() {
        return projectDir;
    }

    @Override
    public Lookup getLookup() {
        if (lookup == null) {
            lookup = Lookups.fixed(new Object[] {
            this,
            state,
            new ActionProviderImpl(),
            new Info()
            });
        }
        return lookup;
    }

    public FileObject getCircuitsFolder(boolean create) {
        FileObject result = projectDir.getFileObject(CIRCUITS_DIR);
        if (result == null && create) {
            try {
                result = projectDir.createFolder(CIRCUITS_DIR);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        return result;
    }
    
    /**
     * Implementação da classe ProjectInformation do sistema de projeto
     */
    private final class Info implements ProjectInformation {

        @Override
        public String getName() {
            return getProjectDirectory().getName();
        }

        @Override
        public String getDisplayName() {
            return getName();
        }

        @Override
        public Icon getIcon() {
            return new ImageIcon(ImageUtilities.loadImage(
                    "org/ufal/logiclabproject/resources/circuit_icon_16.png"));
        }

        @Override
        public Project getProject() {
            return LogicLabProject.this;
        }

        @Override
        public void addPropertyChangeListener(PropertyChangeListener pl) {
            // do nothing, won't change
        }

        @Override
        public void removePropertyChangeListener(PropertyChangeListener pl) {
            // do nothing, won't change
        }
        
    }
    
}
