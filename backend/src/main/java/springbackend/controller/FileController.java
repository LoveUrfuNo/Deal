package springbackend.controller;

import com.google.common.io.Closer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springbackend.model.User;
import springbackend.model.UserFile;
import springbackend.service.UserFileService;
import springbackend.service.UserService;
import sun.util.resources.OpenListResourceBundle;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Controller for operations with download files
 */

@Controller
public class FileController {
    @Autowired
    private UserFileService userFileService;

    @Autowired
    private UserService userService;

    private static final String rootPath = "C:\\Users\\kosty\\IntellijProjects\\Deal\\backend\\src\\main\\webapp\\resources\\user's\\images\\";

    private static final Long ROLE_USER = 1L;

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @RequestMapping(value = "/uploadFile/{operation}", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             @PathVariable("operation") String operation) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = this.userService.findByUsername(auth.getName());

        File uploadedFile = null;
        String name = null;
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                File dir = null;
                if (operation.equals("loadAvatar")) {
                    name = currentUser.getLogin() + "'s_avatar_" + file.getOriginalFilename();
                    dir = new File(rootPath + "/avatars" + File.separator);
                } else if (operation.equals("loadServicePhoto")) {
                    name = currentUser.getLogin() + "'s_service_photo_" + file.getOriginalFilename();
                    dir = new File(rootPath + "/for_services" + File.separator);
                }

                if (!dir.exists()) {
                    dir.mkdirs();
                }

                uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);
                try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile))) {
                    stream.write(bytes);
                    stream.flush();
                }

                logger.info("uploaded: " + uploadedFile.getAbsolutePath());
                logger.info("You successfully uploaded file=" + name);
            } catch (Throwable e) {
                logger.info("You failed to upload " + name + " => " + e.getMessage());
            }
        } else {
            logger.info("You failed to upload " + name + " because the file was empty.");
        }

        assert uploadedFile != null;
        String path = uploadedFile.getAbsolutePath();
        if (operation.equals("loadAvatar")) {
            currentUser.setAvatar(path.substring(path.indexOf("resources") - 1, path.length()));
            this.userService.saveAndFlush(currentUser, ROLE_USER);
        } else if (operation.equals("loadServicePhoto")) {
            UserFile userFile = new UserFile();
            userFile.setTypeOfFile("photo");
            userFile.setPathToFile(path);
            //this.userFileService.save(userFile);                     //TODO:!!!!!!!!!
        }

        return "redirect";
    }
}
