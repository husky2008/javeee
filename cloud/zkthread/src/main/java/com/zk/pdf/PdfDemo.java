package com.zk.pdf;

import com.itextpdf.text.pdf.BaseFont;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextOutputDevice;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.pdf.ITextUserAgent;
import org.xhtmlrenderer.resource.XMLResource;
import org.xml.sax.InputSource;

import java.io.*;

/**
 * @ClassName PdfDemo
 * @Description TODO
 * @Author zhangkai
 * @Date 2019/3/20 16:41
 **/
public class PdfDemo {


    public static void main(String[] args)throws Exception {
        PdfDemo pdfDemo = new PdfDemo();
        pdfDemo.craetePdf();
    }

    /**
     * 指定 xhtml 路径形式
     */
    public void craetePdf() throws Exception {
        String url = "D:\\test.xhtml";
        String pdf = "D:\\craetePdf.pdf";


        OutputStream os = null;
        try {
            if (url.indexOf("://") == -1) {
                // maybe it's a file
                File f = new File(url);
                if (f.exists()) {
                    url = f.toURI().toURL().toString();
                }
            }
            System.err.println("xhtml 地址：" + url);
            System.err.println("pdf 地址：" + url);

            os = new FileOutputStream(pdf);


            /*
             * standard approach ITextRenderer renderer = new ITextRenderer();
             *
             * renderer.setDocument(url); renderer.layout();
             * renderer.createPDF(os);
             */


            ITextRenderer renderer = new ITextRenderer();
            ResourceLoaderUserAgent callback = new ResourceLoaderUserAgent(renderer.getOutputDevice());
            callback.setSharedContext(renderer.getSharedContext());
            renderer.getSharedContext().setUserAgentCallback(callback);


            Document doc = XMLResource.load(new InputSource(url)).getDocument();

            renderer.setDocument(doc, url);

            renderer.layout();
            renderer.createPDF(os);


            os.close();
            os = null;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }


    /**
     * 将 xhtml 转为 string 形式
     */
    public void craetePdfByHtmlStr() {
        String url = "D:/Temp/pdf/template/about.xhtml";
        String pdf = "D:/Temp/pdf/craetePdfByHtmlStr.pdf";
        String font1 = "C:/Windows/Fonts/simsun.ttc";//宋体（对应css中的 属性 font-family: SimSun; /*宋体*/）


        String html = readFile(url);

        OutputStream os = null;
        try {
            if (url.indexOf("://") == -1) {
                File f = new File(url);
                if (f.exists()) {
                    url = f.toURI().toURL().toString();
                }
            }
            System.err.println("xhtml 地址：" + url);
            System.err.println("pdf 地址：" + pdf);

            os = new FileOutputStream(pdf);


            ITextRenderer renderer = new ITextRenderer();
            ResourceLoaderUserAgent callback = new ResourceLoaderUserAgent(renderer.getOutputDevice());
            renderer.getSharedContext().setUserAgentCallback(callback);
            callback.setSharedContext(renderer.getSharedContext());
            //添加中文字体
            renderer.getFontResolver().addFont(font1, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);


            renderer.setDocumentFromString(html, url);

            renderer.layout();
            renderer.createPDF(os);


            os.close();
            os = null;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }

    /**
     * 资源加载代理
     */
    private static class ResourceLoaderUserAgent extends ITextUserAgent {
        public ResourceLoaderUserAgent(ITextOutputDevice outputDevice) {
            super(outputDevice);
        }


        protected InputStream resolveAndOpenStream(String uri) {
            InputStream is = super.resolveAndOpenStream(uri);
            System.out.println("加载资源文件： " + uri);
            return is;
        }
    }

    /**
     * 将文件转为 字符串
     *
     * @param fileName
     * @return
     */
    public static String readFile(String fileName) {
        FileInputStream file = null;
        BufferedReader reader = null;
        InputStreamReader inputFileReader = null;
        String content = "";
        String tempString = null;
        try {
            file = new FileInputStream(fileName);
            inputFileReader = new InputStreamReader(file, "utf-8");
            reader = new BufferedReader(inputFileReader);
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                content += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return content;
    }

}
