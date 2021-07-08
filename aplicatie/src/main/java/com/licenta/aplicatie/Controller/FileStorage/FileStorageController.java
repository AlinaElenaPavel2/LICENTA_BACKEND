package com.licenta.aplicatie.Controller.FileStorage;

import com.licenta.aplicatie.Models.Programa.Disciplina;
import com.licenta.aplicatie.Service.FileStorage.FileStorageService;
import com.licenta.aplicatie.Service.FileStorage.UploadFileResponse;
import com.licenta.aplicatie.Service.Programa.DisciplinaService;
import com.licenta.aplicatie.Service.Programa.MaterialeService;
import com.licenta.aplicatie.Service.Users.UserService;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.regex.Pattern;




@RestController
@RequestMapping("api/licenta/fileStorage")
public class FileStorageController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private UserService userService;

    @Autowired
    private MaterialeService materialeService;
    @Autowired
    DisciplinaService disciplinaService;

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
        return new ResponseEntity<>(new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize()), headers, HttpStatus.OK);

    }


    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
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
    public List<UploadFileResponse> uploadMultipleFilesSecond(@RequestParam("file") MultipartFile[] files, @PathVariable("disciplina") String disciplina,
                                                              @PathVariable("componenta") String componenta) throws Exception {
        String[] arr = disciplina.split(" ");
        String path = "";
        StringBuilder dispPath = new StringBuilder();
        if (arr.length > 1) {
            for (String name : arr
            ) {
                dispPath.append(name).append("_");
            }
            path = "/" + dispPath.substring(0, dispPath.length() - 1) + "/" + componenta;
        }
        path = "/" + disciplina + "/" + componenta + "/";
        System.out.println(path);
        List<UploadFileResponse> list = new ArrayList<>();
        for (MultipartFile multipartFile : Arrays.asList(files)) {
            UploadFileResponse uploadFileResponse = uploadFile(multipartFile, path);
            list.add(uploadFileResponse);
        }
        return list;
    }


    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> openFile(@PathVariable String fileName, HttpServletRequest request) throws Exception {
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {

            System.out.println("Could not determine file type.");
        }
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
        String filePath = "/" + getPath(disciplina) + "/" + tip + "/";
        System.out.println(filePath);
        Resource resource = fileStorageService.loadFileAsResourceGivenPath(fileName, filePath);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Could not determine file type.");
        }
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @CrossOrigin
    @GetMapping("/{disciplina}/{tip}/{fileName}/description")
    public ResponseEntity<?> getDescription(@PathVariable("fileName") String fileName, @PathVariable("disciplina") String disciplina, @PathVariable("tip") String tip, HttpServletRequest request) throws Exception {
        String filePath = "/" + getPath(disciplina) + "/" + tip + "/";
        Resource resource = fileStorageService.loadFileAsResourceGivenPath(fileName, filePath);
        try {
            String dbPath = fileStorageService.getFileStoragePath();
            System.out.println(dbPath);
            System.out.println(getDbFilePath(dbPath) + fileName);
            Disciplina discip = disciplinaService.getDisciplinaByTitlu(disciplina);
            String description = materialeService.getDescription(discip.getId_disciplina(), tip, getDbFilePath(dbPath) + fileName);

            return new ResponseEntity<>(description, HttpStatus.OK);

        }catch (Exception ex)
        {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    public String getDbFilePath(String path)
    {
        String[] arr=path.split(Pattern.quote(File.separator));
        return arr[6]+"/"+arr[7]+"/"+arr[8]+"/"+arr[9]+"/"+arr[10]+"/"+arr[11]+"/"+arr[12]+"/";
    }

    @CrossOrigin
    @GetMapping("/{disciplina}/{tip}")
    public ResponseEntity<?> getFile(@PathVariable("disciplina") String disciplina, @PathVariable("tip") String tip, HttpServletRequest request) throws Exception {
        try {
            String filePath = "\\" + getPath(disciplina) + "\\" + tip + "\\";
            System.out.println(filePath);
            List<String> files = fileStorageService.getAllFilesFromDirectory(filePath);
            List<String> filesUri = new ArrayList<>();
            if(files.size()>0) {
                for (String file : files
                ) {
                    filesUri.add("http://localhost:8080/api/licenta/fileStorage/" + disciplina + "/" + tip + "/" + file);
                }
            }
            return new ResponseEntity<>(filesUri, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @CrossOrigin
    @GetMapping("/{disciplina}/{tip}/descriptions")
    public ResponseEntity<?> getFilesDescriptions(@PathVariable("disciplina") String disciplina, @PathVariable("tip") String tip, HttpServletRequest request) throws Exception {
        try {
            String filePath = "/" + getPath(disciplina) + "/" + tip + "/";
            List<String> files = fileStorageService.getAllFilesFromDirectory(filePath);
            List<String> fileDescription = new ArrayList<>();
            if(files.size()>0) {
                for (String file : files
                ) {

                    String dbPath = fileStorageService.getFileStoragePath()+"\\" + getPath(disciplina) + "\\" + tip ;
                    Disciplina discip = disciplinaService.getDisciplinaByTitlu(disciplina);
                    String description = materialeService.getDescription(discip.getId_disciplina(), tip, getDbFilePath(dbPath) + file);
                    fileDescription.add(description);
                }
            }
            return new ResponseEntity<>(fileDescription, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    public String getPath(String disciplina) {
        StringBuilder filePath = new StringBuilder();
        String[] arr = disciplina.split(" ");
        if (arr.length > 1) {
            for (String name : arr
            ) {
                filePath.append(name).append("_");
            }
            return filePath.substring(0,filePath.length()-1);
        }
        else
        {
            return  disciplina;
        }
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws Exception {
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        System.out.println(resource.getFile().getAbsolutePath());
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Could not determine file type.");
        }
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
    public ResponseEntity<?> uploadPictureByRole(@PathVariable("id") int id, @PathVariable("role") String role, @RequestBody String imgBase64) throws Exception {
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
    public ResponseEntity<?> getProfilePictureByRole(@PathVariable("id") int id, @PathVariable("role") String role) throws Exception {
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
