/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ufal.logiclabproject;

import java.util.Properties;
import org.openide.filesystems.FileObject;
import org.ufal.logiclabapi.MainFileProvider;
import org.ufal.logiclabproject.project.LogicLabProject;

/**
 *
 * @author Matheus Pedro (mps@ic.ufal.br)
 */
public class MainFileProviderImpl extends MainFileProvider {
    
    private final LogicLabProject proj;

    private FileObject mainFile = null;

    private boolean checked = false;

    public MainFileProviderImpl(LogicLabProject proj) {
        this.proj = proj;
    }

    @Override
    public FileObject getMainFile() {
        //Try to look up the main file in the project properties
        //the first time this is called;  no need to look it up every
        //time, either it's there or it's not and when the user sets it
        //we'll save it when the project is closed
        if (mainFile == null && !checked) {
            checked = true;
            Properties props = (Properties) proj.getLookup().lookup(Properties.class);
            String path = props.getProperty(LogicLabProject.KEY_MAINFILE);
            if (path != null) {
                FileObject projectDir = proj.getProjectDirectory();
                mainFile = projectDir.getFileObject(path);
            }
        }
        if (mainFile != null && !mainFile.isValid()) {
            return null;
        }
        return mainFile;
    }

    @Override
    public void setMainFile(FileObject file) {
        String projPath = proj.getProjectDirectory().getPath();
        assert file == null ||
                file.getPath().startsWith(projPath) :
                "Main file not under project";
        boolean change = ((mainFile == null) != (file == null)) ||
                (mainFile != null && !mainFile.equals(file));
        if (change) {
            mainFile = file;
            //Get the project properties (loaded from
            //$PROJECT/pvproject/project.properties)
            Properties props = (Properties) proj.getLookup().lookup(
                    Properties.class);
            //Store the relative path from the project root as the main file
            if (file != null) {
                String relPath = file.getPath().substring(projPath.length());
                props.put(LogicLabProject.KEY_MAINFILE, relPath);
            }
        }
    }
    
}
