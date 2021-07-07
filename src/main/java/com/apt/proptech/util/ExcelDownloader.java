package com.apt.proptech.util;


import com.apt.proptech.domain.dto.AssociateDto;
import com.apt.proptech.domain.dto.UserDto;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class ExcelDownloader extends AbstractView {

    private static final Logger logger = LoggerFactory.getLogger(ExcelDownloader.class);

    InputStream is = null;
    String title = "non-title";

    SXSSFWorkbook workbook = null;

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String type = (String) model.get("contentType");

        switch (type){
            case "User":
                title = "사용자내역조회";
                workbook = generateUserExcel(model);
                break;

            case "Associate":
                title = "조합현황조회";
                workbook = generateAssociateExcel(model);
                break;
        }

        try {
            response.setContentType("application/msexcel;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachement; filename=\""+ java.net.URLEncoder.encode(title+".xlsx", "UTF-8") + "\";charset=\"UTF-8\"");

            ServletOutputStream out = response.getOutputStream();
            workbook.write(out);
            workbook.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public SXSSFWorkbook generateUserExcel(Map<String, Object> model) throws Exception{
        //테이블 기본 템플릿 생성
        SXSSFWorkbook wb = new SXSSFWorkbook(100);

        //엑셀 테이블 헤더
        String[] header = {
                "Name" ,
                "Email" ,
                "Phone Number" ,
                "Provider" ,
                "Role" ,
                "State",
                "Reg Date",
                "Retire Date"
        };

        List<UserDto> data = (List<UserDto>) model.get("data");


        if(data.size() == 0) {
            wb.createSheet("sheet");
        }else{
            int startRow = 0;
            SXSSFSheet sheet = wb.createSheet("sheet");
            SXSSFRow row = sheet.createRow(startRow);

            //첫줄 (헤더) 그리기
            for (int i = 0; i < header.length; i++) {
                row.createCell(i).setCellValue(header[i]);
            }
            startRow++;

            //테이블 내용 작성
            for(UserDto item : data){
                int i =0;
                row = sheet.createRow(startRow);
                row.createCell(i++).setCellValue(item.getName() );
                row.createCell(i++).setCellValue(item.getEmail() );
                row.createCell(i++).setCellValue(item.getPhoneNumber() );
                row.createCell(i++).setCellValue(item.getProvider() );
                row.createCell(i++).setCellValue(item.getRole() );
                row.createCell(i++).setCellValue(item.getState());
                row.createCell(i++).setCellValue(item.getRegisterDate() );
                row.createCell(i++).setCellValue(item.getRetiredDate() );
                startRow++;
            }
        }

        return  wb;
    }

    public SXSSFWorkbook generateAssociateExcel(Map<String, Object> model) throws Exception{
        //테이블 기본 템플릿 생성
        SXSSFWorkbook wb = new SXSSFWorkbook(100);

        //엑셀 테이블 헤더
        String[] header = {
                "Name" ,
                "Round" ,
                "Register" ,
                "End Expect Date" ,
                "End Real Date" ,
                "Fee(%)",
                "City",
                "State",
                "Address"
        };

        List<AssociateDto> data = (List<AssociateDto>) model.get("data");
        if(data.size() == 0) {
            wb.createSheet("sheet");
        }else{
            int startRow = 0;
            SXSSFSheet sheet = wb.createSheet("sheet");
            SXSSFRow row = sheet.createRow(startRow);

            //첫줄 (헤더) 그리기
            for (int i = 0; i < header.length; i++) {
                row.createCell(i).setCellValue(header[i]);
            }
            startRow++;

            //테이블 내용 작성
            for(AssociateDto item : data){
                int i =0;
                row = sheet.createRow(startRow);
                row.createCell(i++).setCellValue(item.getName() );
                row.createCell(i++).setCellValue(item.getRound() );
                row.createCell(i++).setCellValue(item.getRegisterDate() );
                row.createCell(i++).setCellValue(item.getExpectDate() );
                row.createCell(i++).setCellValue(item.getRealDate());
                row.createCell(i++).setCellValue(item.getFeeRatio());
                row.createCell(i++).setCellValue(item.getCity());
                row.createCell(i++).setCellValue(item.getState());
                row.createCell(i++).setCellValue(item.getAddress());

                startRow++;
            }
        }

        return  wb;
    }
}
