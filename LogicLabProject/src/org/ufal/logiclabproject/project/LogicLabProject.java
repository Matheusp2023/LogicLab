/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ufal.logiclabproject.project;

import java.io.IOException;
import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ProjectState;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;

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
            state});
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
    
}
