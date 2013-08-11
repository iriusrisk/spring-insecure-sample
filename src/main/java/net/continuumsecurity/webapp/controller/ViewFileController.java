package net.continuumsecurity.webapp.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/viewFile*")
public class ViewFileController {
    protected final transient Log log = LogFactory.getLog(getClass());

    @Autowired
    private ServletContext servletContext;

    @RequestMapping(method = RequestMethod.GET)
    public void getFile(@RequestParam(required = true, value = "filename") String fileName,
                        HttpServletResponse response) {
        String path = getServletContext().getRealPath("/resources") + "/" + fileName;
        log.debug("Getting file: " + path);
        File file = new File(path);
        try {
            InputStream is = new FileInputStream(path);
            log.debug("read");
            FileCopyUtils.copy(is, response.getOutputStream());
            log.debug("copy");

            response.flushBuffer();
            log.debug("flushed");

        } catch (IOException ex) {
            log.info("Error writing file to output stream. Filename was '" + path + "'");
            throw new RuntimeException("IO Error writing file to output stream");
        }
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    protected ServletContext getServletContext() {
        return servletContext;
    }
}
