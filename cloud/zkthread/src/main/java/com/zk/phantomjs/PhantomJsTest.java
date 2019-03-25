package com.zk.phantomjs;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 通过PhantomJs 调用前端界面,将生产的图片保存到本地
 * html:能通过浏览器打开,才能加载
 * @ClassName PhantomJsTest
 * @Description TODO
 * @Author zhangkai
 * @Date 2019/3/20 10:35
 **/
public class PhantomJsTest {


   public static String surfData = "[\n" +
            "  {\"dataTime\":1524758400000,\"dpt\":\"99999\",\"pre24h\":\"999\",\"prs\":\"99999\",\"rhu\":\"0\",\"station\":\"Y1248\",\"sunlight\":\"99999\",\"tem\":\"227\",\"temMax\":\"99999\",\"temMin\":\"99999\",\"vis\":\"99999\",\"windDAvg10mi\":\"53\",\"windSAvg10mi\":\"99999\"},\n" +
            "  {\"dataTime\":1524844800000,\"dpt\":\"99999\",\"pre24h\":\"399\",\"prs\":\"99999\",\"rhu\":\"0\",\"station\":\"Y1248\",\"sunlight\":\"99999\",\"tem\":\"197\",\"temMax\":\"99999\",\"temMin\":\"99999\",\"vis\":\"99999\",\"windDAvg10mi\":\"66\",\"windSAvg10mi\":\"99999\"},\n" +
            "  {\"dataTime\":1524931200000,\"dpt\":\"99999\",\"pre24h\":\"99999\",\"prs\":\"299\",\"rhu\":\"0\",\"station\":\"Y1248\",\"sunlight\":\"99999\",\"tem\":\"207\",\"temMax\":\"99999\",\"temMin\":\"99999\",\"vis\":\"99999\",\"windDAvg10mi\":\"62\",\"windSAvg10mi\":\"99999\"},\n" +
            "  {\"dataTime\":1525017600000,\"dpt\":\"99999\",\"pre24h\":\"99999\",\"prs\":\"99999\",\"rhu\":\"0\",\"station\":\"Y1248\",\"sunlight\":\"99999\",\"tem\":\"191\",\"temMax\":\"99999\",\"temMin\":\"99999\",\"vis\":\"99999\",\"windDAvg10mi\":\"49\",\"windSAvg10mi\":\"99999\"},\n" +
            "  {\"dataTime\":1525190400000,\"dpt\":\"99999\",\"pre24h\":\"959\",\"prs\":\"99999\",\"rhu\":\"0\",\"station\":\"Y1248\",\"sunlight\":\"\",\"tem\":\"57\",\"temMax\":\"99999\",\"temMin\":\"99999\",\"vis\":\"99999\",\"windDAvg10mi\":\"68\",\"windSAvg10mi\":\"99999\"},\n" +
            "  {\"dataTime\":1525276800000,\"rhu\":\"0\",\"station\":\"Y1248\",\"sunlight\":\"\",\"tem\":\"188\",\"windDAvg10mi\":\"97\",\"windSAvg10mi\":\"99999\"},{\"dataTime\":1525363200000,\"rhu\":\"0\",\"station\":\"Y1248\",\"sunlight\":\"\",\"tem\":\"216\",\"windDAvg10mi\":\"51\",\"windSAvg10mi\":\"99999\"},\n" +
            "  {\"dataTime\":1525449600000,\"rhu\":\"0\",\"station\":\"Y1248\",\"sunlight\":\"\",\"tem\":\"183\",\"windDAvg10mi\":\"49\",\"windSAvg10mi\":\"99999\"},\n" +
            "  {\"dataTime\":1525536000000,\"rhu\":\"0\",\"station\":\"Y1248\",\"sunlight\":\"\",\"tem\":\"176\",\"windDAvg10mi\":\"91\",\"windSAvg10mi\":\"99999\"},{\"dataTime\":1525622400000,\"rhu\":\"0\",\"station\":\"Y1248\",\"sunlight\":\"\",\"tem\":\"178\",\"windDAvg10mi\":\"90\",\"windSAvg10mi\":\"99999\"},\n" +
            "  {\"dataTime\":1525708800000,\"rhu\":\"0\",\"station\":\"Y1248\",\"sunlight\":\"\",\"tem\":\"190\",\"windDAvg10mi\":\"94\",\"windSAvg10mi\":\"99999\"},{\"dataTime\":1525795200000,\"rhu\":\"0\",\"station\":\"Y1248\",\"sunlight\":\"\",\"tem\":\"181\",\"windDAvg10mi\":\"73\",\"windSAvg10mi\":\"99999\"}\n" +
            "]\n";


    public static void main(String[] args) {

        try {
             PhantomJsTest.createImg();

            //通过执行命令,生成pdf截图
            /*String shell = "D:\\soft\\phantomjs-2.1.1\\bin\\phantomjs.exe  D:\\soft\\phantomjs-2.1.1\\examples\\rasterize.js https://www.baidu.com/ d:/baidu.pdf";
            Runtime runtime = Runtime.getRuntime();
            Process exec = runtime.exec(shell);
            System.out.println(exec.waitFor());*/

        } catch (Exception e) {
            e.printStackTrace();
        }


    }




    public static List<String> createImg() throws Exception {
        List<String> imageBase64List = new ArrayList<>();
        PhantomJSDriver driver = getPhantomJSDriver();
        // 让浏览器访问空间主页
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //driver.get("file:///C:/Users/paratera/Desktop/echarjava.html");
        driver.get("file:////home/echarjava.html");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //设置surf数据到页面
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //展示echarts
        js.executeScript("showImg(" + surfData + ")");
        //加入一段休眠时间，防止js执行没完成就进行的 读取echart图片数据的功能。
        Thread.sleep(3000);
        //获取echart图片数据
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String temTxt = (String) js.executeScript("return returnEchartImg(temEcharts)");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String phTxt = (String) js.executeScript("return returnEchartImg(rhEcharts)");
        //imageBase64放到list中
        imageBase64List.add(temTxt.replace("data:image/png;base64,", ""));
        imageBase64List.add(phTxt.replace("data:image/png;base64,", ""));
        //ImgFileUtils.GenerateImage(temTxt,"d://tem01.png");
        //ImgFileUtils.GenerateImage(phTxt,"d://ph01.png");

        driver.close();
        driver.quit();
        return imageBase64List;
    }


    private static PhantomJSDriver getPhantomJSDriver() {
        //设置必要参数
        DesiredCapabilities dcaps = new DesiredCapabilities();
        //ssl证书支持
        dcaps.setCapability("acceptSslCerts", true);
        //截屏支持
        dcaps.setCapability("takesScreenshot", true);
        //css搜索支持
        dcaps.setCapability("cssSelectorsEnabled", true);
        //js支持
        dcaps.setJavascriptEnabled(true);
        //驱动支持
        String osname = System.getProperties().getProperty("os.name");
        if (osname.contains("Linux")) {//判断系统的环境win or Linux
            dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/home/phantomjs-2.1.1/bin/phantomjs");
        } else {
            dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "D:\\soft\\phantomjs-2.1.1\\bin\\phantomjs.exe");
        }
        //创建无界面浏览器对象
        PhantomJSDriver driver = new PhantomJSDriver(dcaps);
        return driver;
    }
}
