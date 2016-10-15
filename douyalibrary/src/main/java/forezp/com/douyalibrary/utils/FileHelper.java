package forezp.com.douyalibrary.utils;




import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;

/**
 * 定义文件存储路径; 创建、删除、判断文件等；
 * 根据时间来判断文件是否该删除
 *
 *
 * @author Forezp
  2015-9-11-上午10:06:45
 *
 */
public class FileHelper {
    private boolean hasSD=false;
    private Context context;
    private String SDPATH;
    private String rootPath;//app文件存储根目录
    private String mediaPath;//app 文件存储媒体类
   
    private static FileHelper instance;

    public FileHelper() {
        hasSD = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        SDPATH = Environment.getExternalStorageDirectory().getPath();
        if (hasSD) {
            rootPath = SDPATH + File.separator+"banya"+ File.separator+"banya"+ File.separator;
            mediaPath = SDPATH + File.separator+ "banya"+ File.separator+"banya"+ File.separator+"media"+ File.separator;
           
        } else {
            SDPATH = Environment.getRootDirectory().getPath();
            rootPath = SDPATH + File.separator+"banya"+ File.separator+"banya"+ File.separator;
            mediaPath = SDPATH + File.separator+"banya"+ File.separator+"banya"+ File.separator+"media"+ File.separator;
            
        }
        hasFileDir(mediaPath);
        
    }

    //没有私有化构造函数，单例。
    public static FileHelper getInstance() {
        if (instance == null) {
            instance = new FileHelper();
        }
        return instance;
    }

    /**
     *
     * @param bm 要保存的bitmap
     * @param picName  保存文件的名字；默认存储在mediapath路径下。
     */
    public void saveBitmap(Bitmap bm, String picName) {
        Log.e("", "保存图片");
        try {
            File f = new File(mediaPath, picName + ".JPEG");
            if (f.exists()) {
                f.delete();
            }
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            Log.e("", "已经保存");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //创建文件
    public File createFile(String path) {
        File file = new File(path);
        try {
            if (file.exists()) {
                deleteFile(file);
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    //判断mediapath下的文件是否存在
    public boolean isFileExist(String fileName) {
        File file = new File(mediaPath + fileName);
        file.isFile();
        return file.exists();
    }

    //删除File
    public static void deleteFile(File f) {
        if (f.isDirectory()) {
            File[] files = f.listFiles();
            if (files != null && files.length > 0) {
                for (int i = 0; i < files.length; ++i) {
                    deleteFile(files[i]);
                }
            }
        }
        f.delete();
    }

    //删除文件
    public void delFile(String fileName) {
        File file = new File(mediaPath + fileName);
        if (file.isFile()) {
            file.delete();
        }
    }

    //删除文件夹
    public void deleteDir(String path) {
        File dir = new File(path);
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;
        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDir(path); // 递规的方式删除文件夹
        }
        dir.delete();// 删除目录本身
    }

    //判断文件是否存在
    public boolean fileIsExists(String path) {
        try {
            File f = new File(path);
            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 删除多少天前的文件
     * @param path 文件路径
     * @param day  多少天的前的文件需要删除
     */
    public void DeleteFileInDirectoryWithBeforeDays(String path, int day) {
        File file = new File(path);
        if (file.isFile()) {
            if (isNeedDelete(file, day))
                file.delete();
            return;
        }
        if (file.isDirectory()) {
            File[] childFile = file.listFiles();
            for (File f : childFile) {
                DeleteFileInDirectoryWithBeforeDays(f.getPath(), day);
            }
            if (isNeedDelete(file, day))
                file.delete();
        }
    }

    private static boolean isNeedDelete(File file, int day) {
        long time = file.lastModified();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        Date fileDate = cal.getTime();
        Date nowDate = new Date();
        long tf = fileDate.getTime();
        long tn = nowDate.getTime();
        long millis = tn - tf;
        int offset = (int) (millis / (1000 * 60 * 60 * 24));
        if (offset >= day)
            return true;
        else
            return false;
    }



    /**
     * 合并路径
     * @param path
     * @param fileName
     * @return
     */
    public String combinPath(String path, String fileName) {
        return path + (path.endsWith(File.separator) ? "" : File.separator) + fileName;
    }

    /**
     * 复制文件
     * @param src
     * @param tar
     * @return
     * @throws Exception
     */
    public boolean copyFile(File src, File tar) throws Exception {
        if (src.isFile()) {
            InputStream is = new FileInputStream(src);
            OutputStream op = new FileOutputStream(tar);
            BufferedInputStream bis = new BufferedInputStream(is);
            BufferedOutputStream bos = new BufferedOutputStream(op);
            byte[] bt = new byte[1024 * 8];
            int len = bis.read(bt);
            while (len != -1) {
                bos.write(bt, 0, len);
                len = bis.read(bt);
            }
            bis.close();
            bos.close();
        }
        if (src.isDirectory()) {
            File[] f = src.listFiles();
            tar.mkdir();
            for (int i = 0; i < f.length; i++) {
                copyFile(f[i].getAbsoluteFile(), new File(tar.getAbsoluteFile() + File.separator + f[i].getName()));
            }
        }
        return true;
    }

    /**
     * 移动文件
     * @param src
     * @param tar
     * @return
     * @throws Exception
     */
    public boolean moveFile(File src, File tar) throws Exception {
        if (copyFile(src, tar)) {
            deleteFile(src);
            return true;
        }
        return false;
    }

    /**
     * 获取最后的‘/’后的文件名
     *
     * @param name
     * @return
     */
    public String getLastName(String name) {
        int lastIndexOf = 0;
        try {
            lastIndexOf = name.lastIndexOf('/');
        } catch (Exception e) {
            e.printStackTrace();
        }
        return !name.equals("") ? name.substring(lastIndexOf + 1) : "";
    }

    /**
     * @param url
     *            保存文件的文字
     * @return 文件名
     */
    public static String getFileName(String url) {
        String fileName = null;
        if (url != null && url.contains("/")) {
            String[] data = url.split("/");
            fileName = data[data.length - 1];
        }
        return fileName;
    }

    /**
     * 判断文件夹是否存在,不存在就创建
     * @param path
     * @return true
     */
    public boolean hasFileDir(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.exists();
    }

    public String getSDPATH() {
        return SDPATH;
    }

    public String getRootPath() {
        return rootPath;
    }



    public boolean isHasSD() {
        return hasSD;
    }



    public String getMediaPath() {
        return mediaPath;
    }

    

}

