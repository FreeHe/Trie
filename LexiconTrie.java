import java.io.*;
import java.util.Vector;
import java.util.Iterator;

/*
 * [To be edited]
 */
public class LexiconTrie{
  private LTNode root;
  private int numWords;
  private int numNodes;
  public LexiconTrie(){
    this.root = new LTNode(' ', false);
    numNodes = 1;
    numWords = 0;
  }
  public int getNumWords(){
    return this.numWords;
  }
  public int getNumNodes(){
    return this.numNodes;
  }
  public void add(String s){
    char[] c = s.toCharArray();
    LTNode tmp = this.root;
    for(int i=0; i < c.length; i++){
      if(i != c.length-1){
        if(this.root.children.size() == 0){
          this.root.children.add(new LTNode(c[i], false));
          this.numNodes++;
        }
        else{
          if(tmp.children.contains(new LTNode(c[i], false))){
            tmp = tmp.children.elementAt(tmp.children.indexOf(new LTNode(c[i], false)));
          }
          else{
            LTNode lc = new LTNode(c[i], false);
            tmp.children.add(lc);
            this.numNodes++;
            tmp = tmp.children.elementAt(tmp.children.indexOf(lc));
          }
        }
      }
      else{
        LTNode lc = new LTNode(c[i], true);
        if(this.root.children.size() == 0){
          this.root.children.add(lc);
          this.numWords++;
          this.numNodes++;
        }
        else{
          if(tmp.children.contains(lc)){
            tmp = tmp.children.elementAt(tmp.children.indexOf(lc));
            tmp.setIsWord(true);
            this.numWords++;
          }
          else{
            tmp.children.add(lc);
            tmp = tmp.children.elementAt(tmp.children.indexOf(lc));
            tmp.setIsWord(true);
            this.numNodes++;
            this.numWords++;
          }
        }
      }
    }
  }
  public void addFromFile(String filename) throws IOException {
    File f = new File(filename);
    InputStreamReader reader = new InputStreamReader(new FileInputStream(f));
    BufferedReader br = new BufferedReader(reader);
    String line = "";
    line = br.readLine();
    while(line != null){
      this.add(line);
      line = br.readLine();
    }
  }
  private LTNode find(String word){
    char[] c = word.toCharArray();
    LTNode tmp = this.root;
    for(int i = 0; i<c.length; i++){
      LTNode x = new LTNode(c[i], false);
      if(tmp.children.contains(x)){
        tmp = tmp.children.elementAt(tmp.children.indexOf(x));
      }
      else{
        return null;
      }
    }
    return tmp;
  }
  public boolean contains(String word){
    LTNode res = this.find(word);
    if(res != null && res.getIsWord()){
      return true;
    }
    return false;
  }
  public boolean containsPrefix(String prefix){
    if(this.find(prefix) != null){
      return true;
    }
    return false;
  }
  public void remove(String word){
    LTNode x = this.find(word);
    if(x != null && x.getIsWord()){
      x.setIsWord(false);
    }
  }
  public Iterator<String> iterator() {
    Vector<String> v = new Vector<String>();
    gatherWords(v, root, "");
    return v.iterator();
  }

  private void gatherWords(Vector<String> v, LTNode cur, String prefix) {
    prefix += cur.getLetter();
    if (cur.getIsWord()) {
      v.add(prefix);
    }

    Iterator<LTNode> iter = cur.iterator();
    while(iter.hasNext())
      gatherWords(v, iter.next(), prefix);
  }
}
