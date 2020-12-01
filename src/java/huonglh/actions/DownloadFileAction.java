/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonglh.actions;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 *
 * @author Hau Huong
 */
public class DownloadFileAction extends ActionSupport {

    private InputStream inputStream;
    private String fileName;
    private long contentLength;
    private String script;

    public DownloadFileAction() {
    }

    public String execute() throws FileNotFoundException {
        File fileToDownload = new File("D:/FPT/CN4/PRJ321/HW/TayDuKyASM3/" + script);

        inputStream = new FileInputStream(fileToDownload);
        fileName = fileToDownload.getName();
        contentLength = fileToDownload.length();

        return "success";
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public String getFileName() {
        return fileName;
    }

    public long getContentLength() {
        return contentLength;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

}
