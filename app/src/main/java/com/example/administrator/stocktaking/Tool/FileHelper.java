package com.example.administrator.stocktaking.Tool;

import android.Manifest;
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.stocktaking.ScanQRCodeActivity;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/5/4.
 */




public class FileHelper {


    /**
     * 主地址
     */
    public static String MainAddress = Environment.getExternalStorageDirectory().getPath() + "/stock/";
    public static String Recordaddress = MainAddress + "/Record/";
    public static String MarksListdaddress = MainAddress + "MarksList.txt";
  //  public static String Recordaddress = MainAddress + "/MarksList.txt";
    public static String GetDataBasePath(){
        //获取程序默认数据库路径
      return  Environment.getExternalStorageDirectory().getPath();
    }



    public static class TxtFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            return (name.endsWith(".txt"));
        }}
    private static void writeDataDemo() {
        String filePath = "/sdcard/Gyt/";
        String fileName = "data.txt";
       // writeTxtToFile("Wx:lcti1314", filePath, fileName);
    }


    public static   ArrayList<String> GetFiles(String FileDirectory){
        ArrayList<String> FileNames=new ArrayList<>();
        File sceneFile = new File(FileDirectory);
        File[] files = sceneFile.listFiles(new TxtFilter());
        if (null != files) {
            for (int i = 0; i < files.length; i++) {
                FileNames.add(files[i].getName());
               // System.out.println("文件夹下的文件：" + files[i].getName());
            }
        }
        return  FileNames;
    }
    // 将字符串写入到文本文件中
    public static void writeTxtToFile(String strcontent, String filePath, String fileName,boolean append,boolean autoLine) {
        //生成文件夹之后，再生成文件，不然会出错
        makeFilePath(filePath, fileName);

        String strFilePath = filePath + fileName;
        // 每次写入时，都换行写

        try {
            File file = new File(strFilePath);
            if (!file.exists()) {
                Log.d("TestFile", "Create the file:" + strFilePath);
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            RandomAccessFile raf ;
            FileOutputStream out;
            if (append) {
                //如果为追加则在原来的基础上继续写文件
                raf = new RandomAccessFile(file, "rwd");
                raf.seek(file.length());
                raf.write(strcontent.getBytes());
                if (autoLine) {
                    raf.write("\r\n".getBytes());
                }
            } else {
                //重写文件，覆盖掉原来的数据
                out = new FileOutputStream(file);
                out.write(strcontent.getBytes());
                out.flush();
            }
        } catch (Exception e) {
            Log.e("TestFile", "Error on write File:" + e);
        }
    }




    /**
     * 根据行读取内容
     * @return
     */
    public static List<String> ReadAlines(String FilePath ) {
        //将读出来的一行行数据使用List存储
        String filePath = FilePath;

        List newList=new ArrayList<String>();
        try {
            File file = new File(filePath);
            int count = 0;//初始化 key值
            if (file.isFile() && file.exists()) {//文件存在
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
                BufferedReader br = new BufferedReader(isr);
                String lineTxt = null;
                while ((lineTxt = br.readLine()) != null) {
                    if (!"".equals(lineTxt)) {
                        String reds = lineTxt.split("\\+")[0];  //java 正则表达式
                        newList.add(count, reds);
                        count++;
                    }
                }
                isr.close();
                br.close();
            }else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newList;
    }
//生成文件

    public static File makeFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

//生成文件夹

    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            Log.i("error:", e + "");
        }
    }


    //读取指定目录下的所有TXT文件的文件内容
    public static String getFileContent(File file) {
        String content = "";
        if (!file.isDirectory()) {  //检查此路径名的文件是否是一个目录(文件夹)
            if (file.getName().endsWith("txt")) {//文件格式为""文件
                try {
                    InputStream instream = new FileInputStream(file);
                    if (instream != null) {
                        InputStreamReader inputreader
                                = new InputStreamReader(instream, "UTF-8");
                        BufferedReader buffreader = new BufferedReader(inputreader);
                        String line = "";
                        //分行读取
                        while ((line = buffreader.readLine()) != null) {
                            content += line + "\n";
                        }
                        instream.close();//关闭输入流
                    }
                } catch (java.io.FileNotFoundException e) {
                    Log.d("TestFile", "The File doesn't not exist.");
                } catch (IOException e) {
                    Log.d("TestFile", e.getMessage());
                }
            }
        }
        return content;
    }

//    public static void requestPessions(final Throwable ex, final Context mContext, final Map<String, String> infos) {
//        //申请权限
//        RxPermissions.getInstance(mContext)
//                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
//                .subscribe(new Action1<Boolean>() {
//                    @Override
//                    public void call(Boolean aBoolean) {
//                        if (aBoolean) {
//                            Toast.makeText(mContext,"resume",Toast.LENGTH_LONG).show();
//                            com.wedding.boss.utils.LogUtils.i("保存录音错误日志文件---------授权成功");
//                            saveRecordInfo2File(ex,infos);
//                        } else {
//                            com.wedding.boss.utils.LogUtils.i("保存录音错误日志文件---------授权失败");
//                        }
//                    }
//                });
//    }
}
