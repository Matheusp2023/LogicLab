/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ufal.logiclabproject.project;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Properties;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectInformation;
import org.netbeans.spi.project.ProjectState;
import org.netbeans.spi.project.ui.LogicalViewProvider;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;
import org.ufal.logiclabproject.LogicLabLogicalView;
import org.ufal.logiclabproject.MainFileProviderImpl;
import org.ufal.logiclabproject.actions.ActionProviderImpl;

/**
 *
 * @author Matheus Pedro (mps@ic.ufal.br)
 * @author Caio Oliveira (cofa@ic.ufal.br)
 */
public class LogicLabProject implements Project {
    
    public static final String CIRCUITS_DIR = "circuits";
    public static final String KEY_MAINFILE = "main.file";
    
    private final FileObject projectDir;
    private final ProjectState state;
    private Lookup lookup;
    
    LogicalViewProvider logicalView = new LogicLabLogicalView(this);

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
            new Info(),
            loadProperties(),
            logicalView,
            new MainFileProviderImpl(this)
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
    
    private Properties loadProperties() {
        FileObject fob = projectDir.getFileObject(LogicLabProjectFactory.PROJECT_DIR
                + "/" + LogicLabProjectFactory.PROJECT_PROPFILE);
        Properties properties = new NotifyProperties(state);
        
        if (fob != null) {
            try {
                properties.load(fob.getInputStream());
            } catch (IOException e) {
                Exceptions.printStackTrace(e);
            }
        }
        
        return properties;
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

    private static class NotifyProperties extends Properties {
        private final ProjectState state;

        NotifyProperties(ProjectState state) {
            this.state = state;
        }

        @Override
        public Object put(Object key, Object val) {
            Object result = super.put(key, val);

            if (((result == null) != (val == null)) || (result != null && val != null && !val.equals(result))) {
                state.markModified();
            }

            return result;
        }
    }
    
}
