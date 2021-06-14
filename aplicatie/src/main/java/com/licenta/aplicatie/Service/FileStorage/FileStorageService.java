package com.licenta.aplicatie.Service.FileStorage;

import com.licenta.aplicatie.POJO.FileStorageProperties;
import com.licenta.aplicatie.POJO.FilesExceptions.FileNotFoundException;
import com.licenta.aplicatie.POJO.FilesExceptions.FileStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.Slf4JLoggingSystem;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class FileStorageService {
    private Path fileStorageLocation;
    private String globalDirector;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        this.globalDirector = fileStorageProperties.getUploadDir();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public void setFileStoragePath(String path) {

        this.fileStorageLocation = Paths.get(this.globalDirector + path)
                .toAbsolutePath().normalize();
    }

    public String getFileStoragePath() {
        return this.fileStorageLocation.toString();
    }

    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("File not found " + fileName, ex);
        }
    }

    public Resource loadFileAsResourceGivenPath(String fileName, String path) {
        try {
            this.fileStorageLocation = Paths.get(this.globalDirector + path)
                    .toAbsolutePath().normalize();
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("File not found " + fileName, ex);
        }
    }

    public List<String> getAllFilesFromDirectory(String path) {
//        List<String> fileNames = new ArrayList<>();
//        File directoryPath = new File(this.fileStorageLocation.toString() + path);
//        //List of all files and directories
//        System.out.println(this.fileStorageLocation.toString()+path);
//        File[] filesList = directoryPath.listFiles();
//        if (filesList != null) {
//            System.out.println("List of files and directories in the specified directory:");
//            for (File file : filesList) {
//                System.out.println("File name: " + file.getName());
//                fileNames.add(file.getName());
//            }
//        }
//        return fileNames;
        List<String> fileList = new ArrayList<>();
//        System.out.println("************");
//        System.out.println(this.fileStorageLocation.toString());
//        System.out.println(checkPath(this.fileStorageLocation.toString()));
//        System.out.println(normalizePath(this.fileStorageLocation.toString()));
//        System.out.println("************");
        String fullPath=normalizePath(this.fileStorageLocation.toString()) + path;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(fullPath))) {
            for (Path pathFile : stream) {
                if (!Files.isDirectory(pathFile)) {
                    fileList.add(pathFile.getFileName()
                            .toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileList;
    }

    private boolean checkPath(String path) {
        String[] arr = path.split(Pattern.quote(File.separator));
        if (arr.length > 10) {
            return false;
        } else {
            return true;
        }
    }

    private String normalizePath(String path) {
        String[] arr = path.split(Pattern.quote(File.separator));

        return arr[0] + "/" + arr[1] + "/" + arr[2] + "/" + arr[3] + "/" + arr[4] + "/" + arr[5] + "/" + arr[6] + "/" + arr[7] + "/" + arr[8] + "/" + arr[9] ;

    }
}
