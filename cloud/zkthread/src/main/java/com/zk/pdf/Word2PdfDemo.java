package com.zk.pdf;


import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

import java.io.File;

/**
 * 通过调用openoffice服务生成pdf
 * @ClassName Word2PdfDemo
 * @Description TODO
 * @Author zhangkai
 * @Date 2019/3/20 14:29
 **/
public class Word2PdfDemo {
    public static void main(String[] args) throws Exception {
        File inputFile = new File("D:/abc.doc");
        File outputFile = new File("D:/document2.pdf");
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
        connection.connect();
        DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
        converter.convert(inputFile, outputFile);
        connection.disconnect();
    }

}
