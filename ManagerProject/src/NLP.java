import java.util.List;
import java.util.Scanner;

import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;
import org.snu.ids.kkma.ma.MExpression;
import org.snu.ids.kkma.ma.MorphemeAnalyzer;
import org.snu.ids.kkma.ma.Sentence;
import org.snu.ids.kkma.util.Timer;

public class NLP {

	public static void main(String[] args) {
		maTest();

	}
	
	public static void maTest()
    {
//          String string = "아버지가방에들어가셨다.";
		String string = null;
          Scanner sc = new Scanner(System.in);
          string = sc.nextLine();
          try {
                MorphemeAnalyzer ma = new MorphemeAnalyzer();
                ma.createLogger(null);
                Timer timer = new Timer();
                timer.start();
                List<MExpression> ret = ma.analyze(string);
                timer.stop();
                timer.printMsg("Time");
                ret = ma.postProcess(ret);
                ret = ma.leaveJustBest(ret);
                List<Sentence> stl = ma.divideToSentences(ret);
                for( int i = 0; i < stl.size(); i++ ) {
                      Sentence st = stl.get(i);
                      System.out.println("=============================================  " + st.getSentence());
                      for( int j = 0; j < st.size(); j++ ) {
                            System.out.println(st.get(j));
                      }
                }
                KeywordExtractor ke = new KeywordExtractor();
                KeywordList kl = ke.extractKeyword(string, true);
                for( int i = 0; i < kl.size(); i++ ) {
                	Keyword kwrd = kl.get(i);
                	if(kwrd.getString().equals("아버지")) 
                		System.out.println("아버지라고 하셧나요?");
                	System.out.println(kwrd.getString() + "\t" + kwrd.getCnt());
                }
                ma.closeLogger();
          } catch (Exception e) {
                e.printStackTrace();
          }
    }


}
