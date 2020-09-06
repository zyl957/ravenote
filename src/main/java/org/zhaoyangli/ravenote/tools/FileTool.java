package org.zhaoyangli.ravenote.tools;

import java.io.File;

public class FileTool {

    protected File root;

    public FileTool(String pathName) {
        this.root = new File(pathName);
    }

    public void renameFiles(String unitId, String lectureId, String randomId, int totalNum){

        try {
            for(int i=0; i<totalNum;i++) {
                File file = new File("D:\\Msc Computer Science\\ravenote\\src\\main\\resources\\static\\slide\\"+unitId+"\\"+lectureId+"\\"+randomId+i+".jpg");
                File file1 = new File("D:\\Msc Computer Science\\ravenote\\src\\main\\resources\\static\\slide\\"+unitId+"\\"+lectureId+"\\"+unitId+"_"+lectureId+"_"+i+".jpg");
                boolean te = file.renameTo(file1);
                System.out.println(i+" "+te);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        try {
            String unitId = "CM50262";
            String lectureId = "1B";
            String randomId = "151086bd8bf64dd1e5d7923d27b21953dkwhmWOUqYbYqy4G-";
            FileTool fileTool = new FileTool("D:\\Msc Computer Science\\ravenote\\src\\main\\resources\\static\\slide\\"+unitId+"\\"+lectureId);
            File[] files = fileTool.root.listFiles();
            if ( files != null) {
                int totalNum = files.length;
                fileTool.renameFiles(unitId, lectureId,randomId,totalNum);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
