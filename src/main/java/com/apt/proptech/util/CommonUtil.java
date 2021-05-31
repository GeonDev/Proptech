package com.apt.proptech.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

public class CommonUtil {
    public static String null2str(String org, String converted) {
        if (org == null || org.trim().length() == 0)
            return converted;
        else
            return org.trim();
    }

    public static String null2str(String org) {
        return CommonUtil.null2str(org, "");
    }

    public static String null2str(Object org) {
        if (org != null && org instanceof java.math.BigDecimal) {
            return CommonUtil.null2str((java.math.BigDecimal) org, "");
        } else {
            return CommonUtil.null2str((String) org, "");
        }
    }

    public static String null2str(java.math.BigDecimal org, String converted) {
        if (org == null)
            return converted;
        else
            return org.toString();
    }

    public static String null2str(java.math.BigDecimal org) {
        return CommonUtil.null2str(org, "");
    }

    public static String toDateStr(String dateStr) {
        if (dateStr == null)
            return "";
        else if (dateStr.length() != 8)
            return dateStr;
        else
            return dateStr.substring(0, 4) + "/" + dateStr.substring(4, 6)
                    + "/" + dateStr.substring(6, 8);
    }

    public static String toDateStr(Timestamp date) {
        if (date == null)
            return "";
        else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            return sdf.format(new Date(date.getTime()));
        }
    }

    public static String toDateStr(Date date) {
        if (date == null)
            return "";
        else {
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
            return sdf.format(new Date(date.getTime()));
        }
    }




    public static String toSsnStr(String ssnStr) {
        if (ssnStr == null)
            return "";
        else if (ssnStr.length() != 13)
            return ssnStr;
        else
            return ssnStr.substring(0, 6) + "-" + ssnStr.substring(6, 13);
    }

    public static String toAmountStr(String amountStr) {
        String returnValue = "";
        if (amountStr == null)
            return returnValue;
        else {
            int strLength = amountStr.length();

            if (strLength <= 3)
                return amountStr;
            else {
                String s1 = "";
                String s2 = "";
                for (int i = strLength - 1; i >= 0; i--)
                    s1 += amountStr.charAt(i);

                for (int i = strLength - 1; i >= 0; i--) {
                    s2 += s1.charAt(i);
                    if (i % 3 == 0 && i != 0)
                        s2 += ",";
                }

                return s2;
            }
        }
    }

    public static String toAmountStr(java.math.BigDecimal amount) {
        if (amount == null) {
            return "";
        } else {
            return toAmountStr(amount.toString());
        }
    }

    public static boolean checkNumber(String str) {

        if(str.equals("") || str==null) {

            return false;
        }


        for(int i =0; i<str.length();i++) {
            char c = str.charAt(i);
            if(c<48 || c>59) {
                return false;
            }
        }

        return true;

    }


    public static String checkOvperlap(String csv ,String src, String str) {

        String [] list =  src.split(csv);
        String result = new String();

        for(String s : list) {
            if (!(s.equals(str))) {
                result += (s+csv);
            }
        }

        return result;
    }

    public static void writeFile(MultipartFile multipartFile, String saveFileName, String SAVE_PATH) throws IOException{

        byte[] data = multipartFile.getBytes();
        FileOutputStream fos = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
        fos.write(data);
        fos.close();

    }


    public static String getUserIp() throws Exception {

        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = null;

        ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-RealIP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("REMOTE_ADDR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }



    public static String getHaxcode(int num) {

        String hax = Integer.toHexString(num);
        if(hax.length() == 1) {
            String temp = "0";
            temp += hax;
            hax = temp;
        }

        return hax;
    }

    public static String generateMD5(String message) {
        return hashString(message, "MD5","UTF-8");
    }

    public static String generateSHA256(String message)  {
        return hashString(message, "SHA-256","UTF-8");
    }

    public static String generateSHA512(String message)  {
        return hashString(message, "SHA-512","UTF-8" );
    }



    private static String hashString(String message, String algorithm, String charest) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hashedBytes = digest.digest(message.getBytes(charest));
            return convertByteArrayToHexString(hashedBytes);

        } catch (Exception ex) {
            return "Fail to hashString";
        }
    }



    private static String convertByteArrayToHexString(byte[] arrayBytes) {

        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < arrayBytes.length; i++) {

            stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16).substring(1));

        }

        return stringBuffer.toString();

    }


    public static String getFilePath(ServletContext context, String uploadPath, String realPath) {
        String path = context.getRealPath("/");
        String OS = System.getProperty("os.name").toLowerCase();

        if(OS.contains("win")) {
            //워크스페이스 경로를 받아온다.
            path = path.substring(0,path.indexOf("\\.metadata"));
            path +=  uploadPath;
        }else {
            //실제 톰켓 데이터가 저장되는 경로를 가리킨다.
            path =  realPath;
        }

        return path;
    }
}