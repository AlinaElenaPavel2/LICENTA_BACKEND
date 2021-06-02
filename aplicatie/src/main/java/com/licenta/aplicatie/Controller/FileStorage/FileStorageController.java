package com.licenta.aplicatie.Controller.FileStorage;

import com.licenta.aplicatie.Service.FileStorage.FileStorageService;
import com.licenta.aplicatie.Service.FileStorage.UploadFileResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import com.licenta.aplicatie.Service.Users.UserService;
//import org.apache.commons.io.FileUtils;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


@RestController
@RequestMapping("api/licenta/fileStorage")
public class FileStorageController {
    //    private static final Logger logger = LoggerFactory.getLogger(FileStorageController.class);
    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private UserService userService;

    @CrossOrigin
    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFiles(@RequestParam("file") MultipartFile file) throws Exception {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/licenta/fileStorage/downloadFile/")
                .path(fileName)
                .toUriString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Credentials", "true");
//        return new UploadFileResponse(fileName, fileDownloadUri,
//                file.getContentType(), file.getSize());
        return new ResponseEntity<>(new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize()), headers, HttpStatus.OK);

    }


    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
//       String path="/"+disciplina+"/"+tip;
        fileStorageService.setFileStoragePath("/Marketing/Curs");
        System.out.println(fileStorageService.getFileStoragePath());
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/licenta/fileStorage/downloadFile/")
                .path(fileName)
                .toUriString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Credentials", "true");
        headers.add("Content type", "multipart/*");
        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    public UploadFileResponse uploadFile(MultipartFile file, String path) throws Exception {
        fileStorageService.setFileStoragePath(path);
        System.out.println(fileStorageService.getFileStoragePath());
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/licenta/fileStorage/downloadFile/")
                .path(fileName)
                .toUriString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Credentials", "true");
        headers.add("Content type", "multipart/*");
        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @CrossOrigin
    @PostMapping("/uploadMultipleFiles")
//    @PostMapping("/disciplina={disciplina}/{tip}/uploadMultipleFiles")
//    @RequestMapping(value = "/uploadMultipleFiles", produces =MediaType.MULTIPART_FORM_DATA_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, method = RequestMethod.POST)
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("file") MultipartFile[] files) throws Exception {
        List<UploadFileResponse> list = new ArrayList<>();
        for (MultipartFile multipartFile : Arrays.asList(files)) {
            UploadFileResponse uploadFileResponse = uploadFile(multipartFile);
            list.add(uploadFileResponse);
        }
        return list;
    }

    @CrossOrigin
    @PostMapping("/disciplina={disciplina}/{componenta}/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFilesSecond(@RequestParam("file") MultipartFile[] files, @PathVariable("disciplina") String disciplina, @PathVariable("componenta") String componenta) throws Exception {
        String[] arr = disciplina.split(" ");
        String path="";
        StringBuilder dispPath = new StringBuilder();
        if (arr.length > 1) {
            for (String name : arr
            ) {
                dispPath.append(name).append("_");
            }
            path="/" + dispPath.substring(0, dispPath.length() - 1) + "/" + componenta;
        }
//        String path = "/" + dispPath.substring(0, dispPath.length() - 1) + "/" + componenta;
        path="/"+disciplina+"/"+componenta+"/";
        System.out.println(path);
        List<UploadFileResponse> list = new ArrayList<>();
        for (MultipartFile multipartFile : Arrays.asList(files)) {
            UploadFileResponse uploadFileResponse = uploadFile(multipartFile,path);
            list.add(uploadFileResponse);
        }
        return list;
    }


    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> openFile(@PathVariable String fileName, HttpServletRequest request) throws Exception {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
//            logger.info("Could not determine file type.");
            System.out.println("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @CrossOrigin
    @GetMapping("/{disciplina}/{tip}/{fileName}")
    public ResponseEntity<Resource> openFileForGivenPath(@PathVariable("fileName") String fileName, @PathVariable("disciplina") String disciplina, @PathVariable("tip") String tip, HttpServletRequest request) throws Exception {
        // Load file as Resource
        String filePath = "/" + disciplina + "/" + tip + "/";
        System.out.println(filePath);
        Resource resource = fileStorageService.loadFileAsResourceGivenPath(fileName, filePath);
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
//            logger.info("Could not determine file type.");
            System.out.println("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @CrossOrigin
    @GetMapping("/{disciplina}/{tip}")
    public ResponseEntity<?> getFile(@PathVariable("disciplina") String disciplina, @PathVariable("tip") String tip, HttpServletRequest request) throws Exception {
//        StringBuilder dispPath = new StringBuilder();
//        StringBuilder filePath = new StringBuilder();
//
//        try {
//            String[] arr = disciplina.split(" ");
//            if (arr.length > 1) {
//                for (String name : arr
//                ) {
//                    System.out.println(name);
//                    dispPath.append(name).append("_");
//                }
//
//            }
//             filePath.append("/" + dispPath.substring(0, dispPath.length() - 1) + "/" + tip + "/");
//        } catch (Exception ex) {
//            dispPath.append(disciplina);
//        }
        try {
            String filePath = "/" + disciplina + "/" + tip + "/";
            System.out.println(filePath);
            List<String> files = fileStorageService.getAllFilesFromDirectory(filePath);
            List<String> filesUri = new ArrayList<>();

            for (String file : files
            ) {
                filesUri.add("http://localhost:8080/api/licenta/fileStorage/" + disciplina + "/" + tip + "/" + file);
            }
            return new ResponseEntity<>(filesUri, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws Exception {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        System.out.println(resource.getFile().getAbsolutePath());
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
//            logger.info("Could not determine file type.");
            System.out.println("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @CrossOrigin
    @PostMapping("/uploadProfilePicture/user/id={id}")
    public ResponseEntity<?> uploadPicture(@PathVariable("id") int id, @RequestBody String imgBase64) throws Exception {
        try {
            String partSeparator = ",";
            if (imgBase64.contains(partSeparator)) {
                String encodedImg = imgBase64.split(partSeparator)[1];
                byte[] decodedImg = Base64.getDecoder().decode(encodedImg.getBytes(StandardCharsets.UTF_8));
                ByteArrayInputStream bis = new ByteArrayInputStream(decodedImg);
                BufferedImage bImage2 = ImageIO.read(bis);
                ImageIO.write(bImage2, "png", new File("aplicatie/src/main/resources/ProfilePictures/user_" + id + ".png"));
                System.out.println("image created");
            }
            return new ResponseEntity<>("image created", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @CrossOrigin
    @RequestMapping(value = "/profilePicture/user/id={id}", method = {RequestMethod.GET})
    public ResponseEntity<?> getProfilePicture(@PathVariable("id") int id) throws Exception {
        try {
            System.out.println(userService.getProfilePicturePath(id));
            String imagePath = "aplicatie/src/main/resources/ProfilePictures/user_" + id + ".png";
            File file = new File(imagePath);
            FileInputStream imageInFile = new FileInputStream(file);
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);
            String base64Image = Base64.getEncoder().encodeToString(imageData);
            return new ResponseEntity<>(base64Image, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @CrossOrigin
    @PostMapping("/uploadProfilePicture/user/role={role}/id={id}")
    public ResponseEntity<?> uploadPicture2(@PathVariable("id") int id, @PathVariable("role") String role, @RequestBody String imgBase64) throws Exception {
        try {
            int user_id = userService.getUserIdByRole(id, role);
            String pathname = "aplicatie/src/main/resources/ProfilePictures/user_" + user_id + ".png";
            String partSeparator = ",";
            if (imgBase64.contains(partSeparator)) {
                String encodedImg = imgBase64.split(partSeparator)[1];
                byte[] decodedImg = Base64.getDecoder().decode(encodedImg.getBytes(StandardCharsets.UTF_8));
                ByteArrayInputStream bis = new ByteArrayInputStream(decodedImg);
                BufferedImage bImage2 = ImageIO.read(bis);
                ImageIO.write(bImage2, "png", new File(pathname));
                System.out.println("image created");
            }
            return new ResponseEntity<>("image created at " + pathname, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @CrossOrigin
    @RequestMapping(value = "/profilePicture/user/role={role}/id={id}", method = {RequestMethod.GET})
    public ResponseEntity<?> getProfilePicture2(@PathVariable("id") int id, @PathVariable("role") String role) throws Exception {
        try {
            int user_id = userService.getUserIdByRole(id, role);
            String imagePath = "aplicatie/src/main/resources/ProfilePictures/user_" + user_id + ".png";
            File file = new File(imagePath);
            FileInputStream imageInFile = new FileInputStream(file);
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);
            String base64Image = Base64.getEncoder().encodeToString(imageData);
            return new ResponseEntity<>(base64Image, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
