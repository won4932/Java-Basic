import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

/**
* cpu��뷮 ����
* @author kay
*
*/
public class Memory {

     public static void main(String[] args) throws SigarException {
          Sigar sigar = new Sigar(); //1. sigar��ü ����

          CpuPerc cpu = sigar.getCpuPerc(); //2. ��ü cpu�� ���� ��뷮
          CpuPerc[] cpus = sigar.getCpuPercList(); //3. �� cpu�� ���� ��뷮

          //4. cpu��뷮 ���
          System.out.println("Total cpu----");
          cpu_output(cpu);
        
          for(int i=0; i < 2; i++) {
               System.out.println("cpu"+i+"----");
               cpu_output(cpus[i]);
          }
     }
   
     public static void cpu_output(CpuPerc cpu) {
          System.out.println("User Time\t :"+CpuPerc.format(cpu.getUser()));
          System.out.println("Sys Time\t :"+CpuPerc.format(cpu.getSys()));
          System.out.println("Idle Time\t :"+CpuPerc.format(cpu.getSys()));        
     }
}