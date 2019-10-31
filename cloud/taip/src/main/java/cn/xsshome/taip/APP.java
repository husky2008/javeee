package cn.xsshome.taip;

import cn.xsshome.taip.speech.TAipSpeech;
import cn.xsshome.taip.util.FileUtil;
import cn.xsshome.taip.util.Util;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import sun.misc.BASE64Decoder;

/**
 *腾讯语音ai合成
 * 问题点
 * 1.腾讯语音限定每次请求大小不能超过150个字节(50个中文汉字)
 *  我们多次请求，然后将请求返回的base64连接到一起合成最后的语音
 *
 *
 **/
public class APP {


    public static void main(String[] args) throws Exception {




        String appId = "2117874440";
        String appKey = "bpothic8lEyA66Bo";
        String baseFile = "C:\\Users\\p\\Desktop";
        String fileName = "自媒体";
        String inputFilePath = String.format("%s\\%s\\%s.%s", baseFile, fileName, fileName, "txt");
        String outputFilePath = String.format("%s\\%s\\%s.%s", baseFile, fileName, fileName, "mp3");

        String text = new String(FileUtil.readFileByBytes(inputFilePath));


        TAipSpeech tAipSpeech = new TAipSpeech(appId,appKey);





        /**
         * 语音合成（AI Lab） 	非默认值
         * 将文字转换为语音，返回文字的语音数据。
         * @param text 待合成文本
         * @param speaker 语音发音人编码 1普通话男声 5静琪女声	  6欢馨女声	7碧萱女声
         * @param format 合成语音格式编码 1 PCM	2 WAV  3 MP3
         * @param volume 合成语音音量 取值范围[-10, 10]，如-10表示音量相对默认值小10dB，0表示默认音量，10表示音量相对默认值大10dB
         * @param speed 合成语音语速，默认100
         * @param aht 合成语音降低/升高半音个数，即改变音高，默认0
         * @param apc 控制频谱翘曲的程度，改变说话人的音色，默认58
         * @return String
         * @throws Exception
         */


        String[] split = text.split("，");
        StringBuilder sb = new StringBuilder();
        for (String s : split) {
            String s2 = tAipSpeech.TtsSynthesis(s, 6, 3);
            //String s = tAipSpeech.TtsSynthesis("待合成文本",1,3,0,100,0,58);
            JSONObject jsonObject = JSON.parseObject(s2);
            //System.out.println(s2);
            String speech = jsonObject.getJSONObject("data").getString("speech");
            sb.append(speech);
            Thread.sleep(1000);
        }



        byte[] bytes = new BASE64Decoder().decodeBuffer(sb.toString());

        Util.writeBytesToFileSystem(bytes,outputFilePath);

        System.out.println("succ.");



    }




}
