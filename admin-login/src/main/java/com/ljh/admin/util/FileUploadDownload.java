package com.ljh.admin.util;


import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 文件上传下载
 */
public class FileUploadDownload {

    /**
     *
     * @param file 原文件
     * @param filePath 新文件路径
     * @param fileName 新文件名
     * @return
     * @throws IOException
     */
    public String[] fileUpload(MultipartFile file, String filePath, String fileName) throws IOException {
        if (file.isEmpty()) {
            return new String[]{"success"};
        }
        //原文件名
        String oldFileName = file.getOriginalFilename();  //原文件名
        String fileSuffix = oldFileName.substring(oldFileName.lastIndexOf(".")); //原文件后缀
        String size = String.valueOf(file.getSize());
        //文件类型
        file.getContentType();
        //创建新文件file对象
        File newFile = new File(filePath + File.separator + fileName);
        file.transferTo(newFile);
        return new String[]{"success", oldFileName, fileSuffix, size};
    }


    /**
     * @param file 原文件
     * @param filePath 新文件路径
     * @param fileName 新文件名
     * @param size  大小限制
     * @param suffix 后缀
     * @return
     * @throws IOException
     */
    public String fileUpload(MultipartFile file, String filePath, String fileName,
                             long size, String[] suffix) throws IOException {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        //判断文件大小
        if (file.getSize() > size) {
            return "文件过大";
        }
        //判断后缀
        String oldFileName = file.getOriginalFilename();  //原文件名
        String fileSuffix = oldFileName.substring(oldFileName.lastIndexOf(".")); //原文件后缀
        boolean isSuffix = false;
        for (String s : suffix) {
            if (s.equals(fileSuffix)) {
                isSuffix = true;
                break;
            }
        }
        if (!isSuffix) {
            return "后缀不匹配";
        }

        return "ok";
    }

}
