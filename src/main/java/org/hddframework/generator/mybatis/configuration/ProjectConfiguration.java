package org.hddframework.generator.mybatis.configuration;

import lombok.Data;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by SongWei on 10/01/2018.
 */
@Data
public class ProjectConfiguration {
    private final String relativeJavaDirectoryPath = "src" + File.separator + "main" + File.separator + "java";
    private final String relativeResourcesDirectoryPath = "src" + File.separator + "main" + File.separator + "resources";

    private String rootAbsolutePath;
    private String rootPackageName;
    private String projectName;
    private String mybatisXmlDirectoryName = "mybatis";

    private boolean useMaven;
    private boolean useGradle;

    public ProjectConfiguration() {
        this.useGradle = true;
        this.useMaven = false;
        this.mybatisXmlDirectoryName = "mybatis";
    }

    public void setUseMaven(boolean useMaven) {
        this.useMaven = useMaven;
        if (useMaven) this.useGradle = false;
    }

    public void setUseGradle(boolean useGradle) {
        this.useGradle = useGradle;
        if (useGradle) this.useMaven = false;
    }

    public String getProjectName() {
        if (StringUtils.isBlank(this.projectName)) {
            List<String> directoryList = Arrays.asList(rootAbsolutePath.split(File.separator));
            Collections.reverse(directoryList);
            return directoryList.get(0);
        }
        return projectName;
    }

    public File getJavaDirectory() {
        String absoluteJavaDirectoryPath = (this.rootAbsolutePath + File.separator + relativeJavaDirectoryPath)
                .replaceAll(File.separator + File.separator + "1+", File.separator);
        File javaDirectory = new File(absoluteJavaDirectoryPath);
        try {
            FileUtils.forceMkdir(javaDirectory);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return javaDirectory;
    }

    public File getResourcesDirectory() {
        String absoluteResourcesDirectoryPath = (this.rootAbsolutePath + File.separator + relativeResourcesDirectoryPath)
                .replaceAll(File.separator + File.separator + "1+", File.separator);
        File resourcesDirectory = new File(absoluteResourcesDirectoryPath);
        try {
            FileUtils.forceMkdir(resourcesDirectory);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resourcesDirectory;
    }

    public File getBasePackageDirectory() {
        String absoluteBasePackageDirectoryPath = (getJavaDirectory().getAbsolutePath() + File.separator + this.rootPackageName.replaceAll("\\.", File.separator))
                .replaceAll(File.separator + File.separator + "1+", File.separator);

        File basePackageDirectory = new File(absoluteBasePackageDirectoryPath);
        try {
            FileUtils.forceMkdir(basePackageDirectory);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return basePackageDirectory;
    }

}
