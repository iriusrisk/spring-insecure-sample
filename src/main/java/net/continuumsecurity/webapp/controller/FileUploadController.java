package net.continuumsecurity.webapp.controller;

import net.continuumsecurity.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Controller class to upload Files.
 * <p/>
 * <p>
 * <a href="FileUploadFormController.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@Controller
@RequestMapping("/fileupload*")
public class FileUploadController extends BaseFormController {

    public FileUploadController() {
        setCancelView("redirect:/mainMenu");
        setSuccessView("uploadDisplay");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    public FileUpload showForm() {
        return new FileUpload();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(FileUpload fileUpload, BindingResult errors, HttpServletRequest request)
            throws Exception {

        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(fileUpload, errors);

            if (errors.hasErrors()) {
                return "fileupload";
            }
        }

        // validate a file was entered
        if (fileUpload.getFile().length == 0) {
            Object[] args =
                    new Object[]{getText("uploadForm.file", request.getLocale())};
            errors.rejectValue("file", "errors.required", args, "File");

            return "fileupload";
        }

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("file");

        // the directory to upload to
        String uploadDir = getServletContext().getRealPath("/resources") + "/";

        // Create the directory if it doesn't exist
        File dirPath = new File(uploadDir);

        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }

        //retrieve the file data
        InputStream stream = file.getInputStream();

        //write the file to the file specified
        OutputStream bos = new FileOutputStream(uploadDir + file.getOriginalFilename());
        int bytesRead;
        byte[] buffer = new byte[8192];

        while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
            bos.write(buffer, 0, bytesRead);
        }

        bos.close();

        //close the stream
        stream.close();

        // place the data into the request for retrieval on next page
        request.setAttribute("friendlyName", fileUpload.getName());
        request.setAttribute("fileName", file.getOriginalFilename());
        request.setAttribute("contentType", file.getContentType());
        request.setAttribute("size", file.getSize() + " bytes");

        String link = request.getContextPath() + "/viewFile?filename="+ file.getOriginalFilename() ;
        request.setAttribute("link", link);
        request.setAttribute("location", link);

        log.info("Uploaded to: "+link+ file.getOriginalFilename());
        log.info("Full path: "+dirPath.getAbsolutePath() + Constants.FILE_SEP + file.getOriginalFilename());
        return getSuccessView();
    }
}
