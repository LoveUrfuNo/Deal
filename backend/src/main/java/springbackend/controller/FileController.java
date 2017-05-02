package springbackend.controller;

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
import springbackend.service.UserService;

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
    private UserService userService;

    private static final String rootPath = "C:\\Users\\kosty\\IntellijProjects\\Deal\\backend\\src\\main\\webapp\\resources\\user's\\images\\";

    private static final Long ROLE_USER = 1L;

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @RequestMapping(value = "/uploadFile/{operation}", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             @PathVariable("operation") String operation,
                             Model model) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName());

        File uploadedFile = null;
        String name = null;
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                name = currentUser.getLogin() + "'s_avatar_" + file.getOriginalFilename();

                File dir = new File(rootPath + File.separator);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.flush();
                stream.close();

                logger.info("uploaded: " + uploadedFile.getAbsolutePath());
                logger.info("You successfully uploaded file=" + name);

            } catch (Exception e) {
                logger.info("You failed to upload " + name + " => " + e.getMessage());
            }
        } else {
            logger.info("You failed to upload " + name + " because the file was empty.");
        }

        if (operation.equals("loadAvatar")) {
            assert uploadedFile != null;
            String path = uploadedFile.getAbsolutePath();
            currentUser.setAvatar(path.substring(path.indexOf("resources") - 1, path.length()));
            userService.saveAndFlush(currentUser, ROLE_USER);
        }

        return "redirect";
    }
}
