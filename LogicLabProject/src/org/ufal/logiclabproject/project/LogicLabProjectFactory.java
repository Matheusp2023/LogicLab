/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ufal.logiclabproject.project;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ProjectFactory;
import org.netbeans.spi.project.ProjectState;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.NbBundle;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Matheus Pedro (mps@ic.ufal.br)
 * @author Caio Oliveira (cofa@ic.ufal.br)
 */
@ServiceProvider(service=ProjectFactory.class)
public class LogicLabProjectFactory implements ProjectFactory {
    
    public static final String PROJECT_DIR = "ldproject";
    public static final String PROJECT_PROPFILE = "project.properties";

    @Override
    public boolean isProject(FileObject projectDirectory) {
        return projectDirectory.getFileObject(PROJECT_DIR) != null;
    }

    @Override
    public Project loadProject(FileObject projectDirectory, ProjectState state) throws IOException {
        return isProject(projectDirectory) ? new LogicLabProject(projectDirectory, state) : null;
    }

    @Override
    public void saveProject(Project project) throws IOException, ClassCastException {
        
        // Diretorio raiz do projeto
        FileObject projectRoot = project.getProjectDirectory();
        if (projectRoot.getFileObject(PROJECT_DIR) == null){
            throw new IOException(NbBundle.getMessage(LogicLabProjectFactory.class,
                    "PROJECT_DIR_DELETED", projectRoot.getPath()));
        }
        
        // Forçar a criação do diretorio circuits/ se ele foi excluido
        project.getLookup().lookup(LogicLabProject.class).getCircuitsFolder(true);
        
        // Procurar o arquivo de propriedades ldproject/project.properties,
        // criar se for necessario
        FileObject propertiesFile = projectRoot.getFileObject(PROJECT_DIR + "/" +
                PROJECT_PROPFILE);
        if (propertiesFile == null) {
            // Recriar se for preciso
            propertiesFile = projectRoot.createData(PROJECT_DIR + "/" + PROJECT_PROPFILE);
        }
        
        Properties properties = (Properties) project.getLookup().lookup(Properties.class);
        File file = FileUtil.toFile(propertiesFile);
        properties.store(new FileOutputStream(file), NbBundle.getMessage(
                LogicLabProjectFactory.class, "PROJECT_PROPERTIES_TITLE"));
    }

}
