import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

public class LTNode implements Comparable {
    private char letter;
    private boolean isWord;
    protected Vector<LTNode> children;
    public LTNode(char c, boolean b){
        this.letter = c;
        this.isWord = b;
        this.children = new Vector<LTNode>();
    }
    public char getLetter(){
        return this.letter;
    }
    public boolean getIsWord(){
        return this.isWord;
    }
    public void setIsWord(boolean iw){
        this.isWord = iw;
    }
    public Iterator<LTNode> iterator(){
        return this.children.iterator();
    }
    public LTNode addChild(char c){
        LTNode lc = new LTNode(c, false);
        if(this.children.contains(lc)){
            return null;
        }
        else{
            this.children.add(lc);
            Collections.sort(this.children);
            return lc;
        }
    }
    public LTNode getChild(char c){
        LTNode lc = new LTNode(c, false);
        if(this.children.contains(lc)){
            return this.children.elementAt(this.children.indexOf(lc));
        }
        return null;
    }
    public void removeChild(char c){
        LTNode lc = new LTNode(c, false);
        if(this.children.contains(lc)){
            this.children.removeElement(lc);
        }
    }
    @Override
    public int compareTo(Object o) {
        LTNode lt = (LTNode) o;
        if(this.letter == lt.letter){
            return 0;
        }
        else if(this.letter < lt.letter){
            return -1;
        }
        else{
            return 1;
        }
    }
    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        else{
            LTNode l = (LTNode) o;
            char local = this.letter;
            char other = l.letter;
            if(local == other){
                return true;
            }
            return false;
        }
    }
}
