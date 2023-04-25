package com.example.springproje.service.serviceimpl;

import com.example.springproje.dto.CollectionDTO;
import com.example.springproje.dto.LikeInfoDTO;
import com.example.springproje.dto.TalkDTO;

import com.example.springproje.service.PythonService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@Service
public class PythonServiceImpl implements PythonService {

    /**
     * 测试连接python出错的问题
     * @throws IOException
     */
    public void modelpredicttest(List<LikeInfoDTO> like_talk, List<CollectionDTO> collect_talk, List<TalkDTO> all_talk) throws IOException{
        {
            //前面一半是本地环境下的python的启动文件地址，后面一半是要执行的python脚本地址
            String[] arguments = new String[] {"F:\\Python\\Python37\\python.exe", "D:\\Java\\IDEA 2020\\springproj\\springproje\\src\\main\\resources\\recommend.py",
                    String.valueOf(like_talk),String.valueOf(collect_talk),String.valueOf(all_talk)};
            Process proc;
            try {
                proc = Runtime.getRuntime().exec(arguments);// 执行py文件
                //用输入输出流来截取结果
                FileInputStream errorStream = (FileInputStream)proc.getErrorStream();
                InputStreamReader isr = new InputStreamReader(errorStream,"gbk");//读取
                System.out.println(isr.getEncoding());
                BufferedReader in = new BufferedReader(isr);//缓冲
                String line = null;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
                in.close();
                //waitFor是用来显示脚本是否运行成功，1表示失败，0表示成功，还有其他的表示其他错误
                int re = proc.waitFor();
                System.out.println(re);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *得到python推荐算法结果
     * @throws IOException
     */
    public String modelpredict(List<LikeInfoDTO> like_talk, List<CollectionDTO> collect_talk, List<TalkDTO> all_talk) throws IOException{

        //前面一半是本地环境下的python的启动文件地址，后面一半是要执行的python脚本地址
        String[] arguments = new String[] {"F:\\Python\\Python37\\python.exe", "D:\\Java\\IDEA 2020\\springproj\\springproje\\src\\main\\resources\\recommend.py",
                String.valueOf(like_talk),String.valueOf(collect_talk),String.valueOf(all_talk)};
        Process proc;
        try {
            proc = Runtime.getRuntime().exec(arguments);// 执行py文件
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = in.readLine();
            in.close();
//            System.out.println(line);
            //waitFor是用来显示脚本是否运行成功，1表示失败，0表示成功，还有其他的表示其他错误
            int re = proc.waitFor();
            System.out.println(re);
            return line;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "true";
    }
}
