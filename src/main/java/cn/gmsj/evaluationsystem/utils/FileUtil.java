package cn.gmsj.evaluationsystem.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

public class FileUtil {

    public static void save(byte[] files, String path, String fileName) throws IOException {
        // 目标目录
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        // 二进制流写入
        File file1 = new File(path + File.separator + fileName);
        if (!file1.exists()) {
            file1.createNewFile();
        }
        FileOutputStream fileOut = new FileOutputStream(file1);
        fileOut.write(files);
        fileOut.flush();
        fileOut.close();
    }

    public static void downloadFile(String correspondName, String filePath, HttpServletRequest request,
            HttpServletResponse response) {
        File file = new File(filePath);
        if (file.exists()) {
            // 设置强制下载不打开
            response.setContentType("application/force-download");
            // 设置文件名
            try {
                response.setHeader("Content-Disposition", "attachment;filename*="
                        + URLEncoder.encode(correspondName, "UTF-8").replaceAll("\\+", "%20"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void deleteByFilePath(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    public static boolean fileNamePostfixCheck(String filePostfix, String postfix) {
        if (StringUtil.isEmpty(filePostfix)) {
            return false;
        }
        String[] filePostfixs = filePostfix.split(";");
        for (String str : filePostfixs) {
            if (StringUtil.equals(str, postfix)) {
                return true;
            }
        }
        return false;
    }

}
