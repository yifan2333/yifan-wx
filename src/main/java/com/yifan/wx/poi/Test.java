package com.yifan.wx.poi;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author yifan
 * @since 2018年05月04日
 */
public class Test {
    HSSFWorkbook hssfWorkbook;

    public static void main(String[] args) throws IOException {

        POIFSFileSystem fs=new POIFSFileSystem(new FileInputStream("d:/test.xls"));
        //得到Excel工作簿对象
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        //得到Excel工作表对象
        HSSFSheet sheet = wb.getSheetAt(0);
        //得到Excel工作表的行
        HSSFRow row = sheet.getRow(0);
        //得到Excel工作表指定行的单元格
        HSSFCell cell = row.getCell((short) 5);
        HSSFCellStyle cellStyle = cell.getCellStyle();//得到单元格样式

    }
}
