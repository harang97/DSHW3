package hw3;

public class Entry {
	private int frequency;
	private String word;
	private Entry left;
	private Entry right;
	private String code;
	
	public Entry (int newFreq, String newValue, Entry l, Entry r, String s) {
		frequency = newFreq;
		word = newValue;
		left = l;
		right = r;
		code = s;
	}
	public int getKey() { return frequency; }
	public String getValue() { return word; }
	public String getCode() { return code; }
	public Entry getLeft() { return left; }
	public Entry getRight() { return right; }
	
	public void setCode(String newCode) { code = newCode; }
}
