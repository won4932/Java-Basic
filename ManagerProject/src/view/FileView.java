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
        // swing.JFileChooser���̺귯�� �̿� - ���� Ž��â���
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); // ���丮 ����
        chooser.setCurrentDirectory(new File("/")); // ���� ��� ���丮�� ����
        chooser.setAcceptAllFileFilterUsed(true);   // Fileter ��� ���� ���� 
        chooser.setDialogTitle("File Manager"); // â�� ����
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // ���� ���� ���
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Binary File", "cd11"); // filter Ȯ���� �߰�
        FileNameExtensionFilter filter2 = new FileNameExtensionFilter("txt File", "txt");
        chooser.setFileFilter(filter); // ���� ���͸� �߰�
        chooser.setFileFilter(filter2);
       
        int returnVal = chooser.showOpenDialog(null); // ����� â ����
        
        if(returnVal == JFileChooser.APPROVE_OPTION) { // ���⸦ Ŭ�� 
            folderPath = chooser.getSelectedFile().toString();
        }else if(returnVal == JFileChooser.CANCEL_OPTION){ // ��Ҹ� Ŭ��
            folderPath = "";
        }
        
        return folderPath;
        
    }
}

