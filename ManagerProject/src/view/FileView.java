package view;

import java.io.File;
 
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
 
public class FileView {
	public static void main(String[] args) {
		jFileChooserUtil();
	}
 
    public static String jFileChooserUtil(){
        
        String folderPath = "";
        // swing.JFileChooser라이브러리 이용 - 파일 탐색창기능
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); // 디렉토리 설정
        chooser.setCurrentDirectory(new File("/")); // 현재 사용 디렉토리를 지정
        chooser.setAcceptAllFileFilterUsed(true);   // Fileter 모든 파일 적용 
        chooser.setDialogTitle("File Manager"); // 창의 제목
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // 파일 선택 모드
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Binary File", "cd11"); // filter 확장자 추가
        FileNameExtensionFilter filter2 = new FileNameExtensionFilter("txt File", "txt");
        chooser.setFileFilter(filter); // 파일 필터를 추가
        chooser.setFileFilter(filter2);
       
        int returnVal = chooser.showOpenDialog(null); // 열기용 창 오픈
        
        if(returnVal == JFileChooser.APPROVE_OPTION) { // 열기를 클릭 
            folderPath = chooser.getSelectedFile().toString();
        }else if(returnVal == JFileChooser.CANCEL_OPTION){ // 취소를 클릭
            folderPath = "";
        }
        
        return folderPath;
        
    }
}

