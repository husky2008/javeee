package com.zk.phantomjs;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;


/**
 * 将图片转换为对应的PDF文件
 */
public class PdfUtils {
    public static File Pdf(String filePath, String mOutputPdfFileName) throws Exception {
        Image png = Image.getInstance(filePath); //通过文件路径获取image
        Document doc = new Document(null, 20, 20, 20, 20); //new一个pdf文档
        doc.setPageSize(new Rectangle(png.getWidth(), png.getHeight()));
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(mOutputPdfFileName)); //pdf写入
            doc.open();//打开文档
            doc.newPage();  //在pdf创建一页
            doc.add(png);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            doc.close();
        }
        File mOutputPdfFile = new File(mOutputPdfFileName);  //输出流
        if (!mOutputPdfFile.exists()) {
            mOutputPdfFile.deleteOnExit();
            return null;
        }
        return mOutputPdfFile; //反回文件输出流
    }

}
