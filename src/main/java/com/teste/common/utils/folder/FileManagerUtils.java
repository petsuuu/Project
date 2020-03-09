package com.serasa.common.utils.folder;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class FileManagerUtils {

	public static List<String> getFolderPathFiles(String pathToFolder) {
		try (Stream<Path> paths = Files.walk(Paths.get(pathToFolder))) {
			return paths.filter(Files::isRegularFile).map(String::valueOf)
					// .map(e -> getFileName(e))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new IllegalStateException("Não foi possível localizar a pasta no caminho" + pathToFolder);
		}
	}

	public static String getFileName(String fullPath) {
		return FilenameUtils.getBaseName(fullPath);
	}
	
	public File tempFeatureCreate(String newLoc, String jarLoc, String[] fileNames) throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		File newFile = null;
			for(String fileName:fileNames) {
			URL resource = classLoader.getResource(jarLoc + fileName);
			File f = new File(newLoc);
			if (!f.exists()) {
				f.mkdirs();
			}
			newFile = new File(newLoc + File.separator + fileName);
			if (!newFile.exists()) {
				newFile.createNewFile();
				org.apache.commons.io.FileUtils.copyURLToFile(resource, newFile);
			}
		}
		return newFile;
	}

	 
	public File tempFileCreate(String newLoc, String jarLoc, String fileName) throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		URL resource = classLoader.getResource(jarLoc + fileName);
		File f = new File(newLoc);
		if (!f.exists()) {
			f.mkdirs();
		}
		File newFile = new File(newLoc + File.separator + fileName);
		if (!newFile.exists()) {
			newFile.createNewFile();
			org.apache.commons.io.FileUtils.copyURLToFile(resource, newFile);
		}

		return newFile;
	}

	public void tempDirectoryDelete(String path) {
		try {
			FileUtils.forceDelete(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
