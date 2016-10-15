package forezp.com.douyalibrary.utils;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * sd卡
 * Created by b508a on 2015/12/28.
 */
public class SdCardUtisl {

    /**
     * 设备是否读到SD卡
     *
     * @return true 能读到SD卡 false无法读到SD卡
     */
    public static boolean checkSDCard() {
        String strStorageState = android.os.Environment.getExternalStorageState();
        if (android.os.Environment.MEDIA_MOUNTED.equals(strStorageState)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 显示文件大小信息
     *
     * @param file
     * @return 文件大小
     */
    public static String fileSizeMsg(File file) {
        int subIndex = 0;
        String fileSize = "";
        if (file.isFile()) {
            long length = file.length();
            if (length >= 1073741824) {
                subIndex = (String.valueOf((float) length / 1073741824)).indexOf(".");
                fileSize = ((float) length / 1073741824 + "000").substring(0, subIndex + 3) + "GB";
            } else if (length >= 1048576) {
                subIndex = (String.valueOf((float) length / 1048576)).indexOf(".");
                fileSize = ((float) length / 1048576 + "000").substring(0, subIndex + 3) + "MB";
            } else if (length >= 1024) {
                subIndex = (String.valueOf((float) length / 1024)).indexOf(".");
                fileSize = ((float) length / 1024 + "000").substring(0, subIndex + 3) + "KB";
            } else if (length < 1024) {
                fileSize = String.valueOf(length) + "B";
            }
        }
        return fileSize;
    }


    /**
     * 将数据写入指定的文件夹
     *
     * @param data
     * @param path
     * @throws Exception
     */
    public static void writeToFile(byte[] data, String path) throws Exception {
        if (data.length == 0) {
            return;
        }

        File file = new File(path);
        FileOutputStream fos = null;
        try {
            if (!file.exists()) {
                // file.delete();
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            fos.write(data);
            fos.flush();
        } catch (Exception e) {

            throw e;
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e2) {

            }
        }
    }

    /**
     * 对象序列
     *
     * @param obj
     * @return
     */
    public static byte[] serialIn(Object obj) {
        if (obj == null) {
            return null;
        }
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            return baos.toByteArray();
        } catch (Exception e) {

        } finally {
            try {
                if (baos != null)
                    baos.close();
                if (oos != null)
                    oos.close();
            } catch (IOException e) {

            }
        }
        return null;
    }

    /**
     * 返序列化
     *
     * @param buf
     * @return
     */
    public static Object serialOut(byte[] buf) {
        if (buf == null) {
            return null;
        }
        ByteArrayInputStream baos = null;
        ObjectInputStream ois = null;
        try {
            baos = new ByteArrayInputStream(buf);
            ois = new ObjectInputStream(baos);
            Object o = ois.readObject();
            if (o != null) {
                return o;
            } else {
                return null;
            }
        } catch (Exception e) {

            return null;
        } finally {
            try {
                if (baos != null)
                    baos.close();
                if (ois != null)
                    ois.close();
            } catch (IOException e) {

            }
        }
    }
}

