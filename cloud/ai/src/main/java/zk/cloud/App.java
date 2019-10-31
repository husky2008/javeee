package zk.cloud;

import zk.cloud.common.ConnUtil;
import zk.cloud.common.DemoException;
import zk.cloud.common.TokenHolder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Hello world!
 *
 */
public class App 
{


    //基础文本文件保存路径
    private  final String baseFilePath = "C:\\Users\\p\\Desktop\\";

    //  填写网页上申请的appkey 如 $apiKey="g8eBUMSokVB1BHGmgxxxxxx"
    private final String appKey = "pGYKODK8cFgf9TGnn7TzZ7PP";

    // 填写网页上申请的APP SECRET 如 $secretKey="94dc99566550d87f8fa8ece112xxxxx"
    private final String secretKey = "7BlXeNBtUZvLUbWhcQB2PwfgAH88nsAZ";

    // text 的内容为"欢迎使用百度语音合成"的urlencode,utf-8 编码
    // 可以百度搜索"urlencode"
    //private final String text = "欢迎使用百度语音";

    // 发音人选择, 0为普通女声，1为普通男生，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女声
    private final int per = 0;
    // 语速，取值0-15，默认为5中语速
    private final int spd = 5;
    // 音调，取值0-15，默认为5中语调
    private final int pit = 5;
    // 音量，取值0-9，默认为5中音量
    private final int vol = 5;

    // 下载的文件格式, 3：mp3(default) 4： pcm-16k 5： pcm-8k 6. wav
    private final int aue = 3;

    public final String url = "http://tsn.baidu.com/text2audio"; // 可以使用https

    private String cuid = "1234567JAVA";


    public static void main(String[] args) throws IOException, DemoException {
        (new App()).run("自媒体");
    }

    private void run(String fileName) throws IOException, DemoException {
        TokenHolder holder = new TokenHolder(appKey, secretKey, TokenHolder.ASR_SCOPE);
        holder.refresh();
        String token = holder.getToken();

        // 此处2次urlencode， 确保特殊字符被正确编码
        StringBuffer buffer = new StringBuffer();
        String path =  String.format("%s\\%s\\%s",baseFilePath,fileName,fileName);
        File file1 = new File(path + ".txt");
        InputStream is = new FileInputStream(file1);
        String line; // 用来保存每行读取的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine(); // 读取第一行
        while (line != null) { // 如果 line 为空说明读完了
            buffer.append(line); // 将读到的内容添加到 buffer 中
            line = reader.readLine(); // 读取下一行
        }
        reader.close();
        is.close();

        String text = buffer.toString();
        String params = "tex=" + ConnUtil.urlEncode(ConnUtil.urlEncode(text));
        params += "&per=" + per;
        params += "&spd=" + spd;
        params += "&pit=" + pit;
        params += "&vol=" + vol;
        params += "&cuid=" + cuid;
        params += "&tok=" + token;
        params += "&aue=" + aue;
        params += "&lan=zh&ctp=1";
        System.out.println(url + "?" + params); // 反馈请带上此url，浏览器上可以测试
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setConnectTimeout(5000);
        PrintWriter printWriter = new PrintWriter(conn.getOutputStream());
        printWriter.write(params);
        printWriter.close();
        String contentType = conn.getContentType();
        if (contentType.contains("audio/")) {
            byte[] bytes = ConnUtil.getResponseBytes(conn);
            String format = getFormat(aue);
            File file = new File(String.format("%s.%s",path,format)); // 打开mp3文件即可播放
            FileOutputStream os = new FileOutputStream(file);
            os.write(bytes);
            os.close();
            System.out.println("audio file write to " + file.getAbsolutePath());
        } else {
            System.err.println("ERROR: content-type= " + contentType);
            String res = ConnUtil.getResponseString(conn);
            System.err.println(res);
        }
    }

    // 下载的文件格式, 3：mp3(default) 4： pcm-16k 5： pcm-8k 6. wav
    private String getFormat(int aue) {
        String[] formats = {"mp3", "pcm", "pcm", "wav"};
        return formats[aue - 3];
    }
}
