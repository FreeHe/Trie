import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

public class TestTrie {
    @Test
    public void test() throws IOException {
        LexiconTrie lt = new LexiconTrie();
        // 请把文件路径改成你的电脑上的路径
        lt.addFromFile("E:\\接单代码\\lab8\\src\\long.txt");
        Iterator v = lt.iterator();
        System.out.println("long.txt contains as followed:");
        while(v.hasNext()){
            System.out.println(v.next());
        }
        System.out.println("numNodes:"+lt.getNumNodes());
        System.out.println("numWords:"+lt.getNumWords());
        System.out.println("is Trie contains word (buy):");
        System.out.println(lt.contains("buy"));
        System.out.println("is Trie contains prefix (al):");
        System.out.println(lt.containsPrefix("al"));
    }
}
