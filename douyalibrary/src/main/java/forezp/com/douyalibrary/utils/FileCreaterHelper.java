package forezp.com.douyalibrary.utils;


import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * 创建文件类、图片类、MP3、
 * Created by b508a on 2015/12/28.
 */
public class FileCreaterHelper {
    private static final String JPEG_FILE_PREFIX = "IMG_";
    private static final String JPEG_FILE_SUFFIX = ".jpg";

    /**
     * 创建jpg 文件
     * @return
     * @throws IOException
     */
    public static File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = JPEG_FILE_PREFIX + timeStamp;
        File albumF = new File(FileHelper.getInstance().getMediaPath());
        File imageF = File.createTempFile(imageFileName, JPEG_FILE_SUFFIX, albumF);
        return imageF;
    }

    /**
     * 需求:将两个amr格式音频文件合并为1个
     * 注意:amr格式的头文件为6个字节的长度
     * @param list      各部分路径
     * @param unitedFilePath  合并后路径
     */
    public static void uniteAMRFile(ArrayList<String> list, String unitedFilePath) {
        try {
            File unitedFile = new File(unitedFilePath);
            FileOutputStream fos = new FileOutputStream(unitedFile);
            RandomAccessFile ra = null;
            for (int i = 0; i < list.size(); i++) {
                ra = new RandomAccessFile(list.get(i), "r");
                if (i != 0) {
                    ra.seek(6);
                }
                byte[] buffer = new byte[1024 * 8];
                int len = 0;
                while ((len = ra.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
            }
            ra.close();
            fos.close();
        } catch (Exception e) {
        }
    }


    /**
     * 创建wav格式文件
     * @return
     * @throws IOException
     */
    public static File createRecordingFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName =  timeStamp;
        File albumF = new File(FileHelper.getInstance().getMediaPath());
        File imageF = File.createTempFile(imageFileName, ".wav", albumF);
        //        File imageF = File.createTempFile(imageFileName, "audiorecord.amr", albumF);

        return imageF;
    }
    /**
     * 创建amr格式文件
     * @return
     * @throws IOException
     */
    public static File createAmrFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName =  timeStamp;
        File albumF = new File(FileHelper.getInstance().getMediaPath());
        File imageF = File.createTempFile(imageFileName, ".amr", albumF);
        //        File imageF = File.createTempFile(imageFileName, "audiorecord.amr", albumF);

        return imageF;
    }

    /**
     * 创建mp3格式文件
     * @return
     * @throws IOException
     */
    public static File createMp3File() throws IOException {
        // Create an image file name
        // String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        UUID uuid= UUID.randomUUID();
        String imageFileName =  uuid.toString()+".mp3";
        File albumF = new File(FileHelper.getInstance().getMediaPath());
        //File imageF = File.createTempFile(imageFileName, ".mp3", albumF);
        File imageF=new File(albumF, imageFileName);
        //        File imageF = File.createTempFile(imageFileName, "audiorecord.amr", albumF);

        return imageF;
    }

    /**
     * 更改mp3文件名字
     * @param context
     * @param file
     * @return
     */
    public static File renameTo(Context context, File file){
        UUID uuid= UUID.randomUUID();
        String uuidStr=uuid.toString();
        int mills= CommonUtils.getAudioTime(context, file);
        String secondStr= String.valueOf(mills/1000);
        String newName= FileHelper.getInstance().getMediaPath()+uuidStr+secondStr+".mp3";
        File newfile=new File(newName);
        try {
            if(FileHelper.getInstance().copyFile(file, newfile)){
                file.delete();
                return newfile;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 更改mp4文件名字
     * @param context
     * @param file
     * @return
     */
    public static File renameTo2(Context context, File file){
        UUID uuid= UUID.randomUUID();
        String uuidStr=uuid.toString();
        int mills= CommonUtils.getAudioTime(context, file);
        String secondStr= String.valueOf(mills/1000);
        String newName= FileHelper.getInstance().getMediaPath()+uuidStr+secondStr+".mp4";
        File newfile=new File(newName);
        try {
            if(FileHelper.getInstance().copyFile(file, newfile)){
                file.delete();
                return newfile;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }


}
