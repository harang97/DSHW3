package hw3;

public class Entry {
	private int frequency;
	private String word;
	private Entry left;
	private Entry right;
	private String code;
	private int lxpos;
	private int rxpos;
	
	public Entry (int newFreq, String newValue, Entry l, Entry r, String s) {
		frequency = newFreq;
		word = newValue;
		left = l;
		right = r;
		code = s;
		lxpos = 0;
		if (s != null)
			rxpos = code.length()+3;
		else
			rxpos = 0;
	}
	public int getKey() { return frequency; }
	public String getValue() { return word; }
	public String getCode() { return code; }
	public Entry getLeft() { return left; }
	public Entry getRight() { return right; }
	public int getlxpos() { return lxpos; }
	public int getrxpos() { return rxpos; }
	
	public void setlxpos(int pos) { lxpos = pos; }
	public void setrxpos(int pos) { rxpos = pos; }	
	public void setCode(String newCode) { code = newCode; }
}
