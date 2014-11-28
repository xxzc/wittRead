package skyxxzc.wittread;

import java.io.*;
import java.util.ArrayList;
import android.content.Context;

public class ResProvider {
	class Item{
		public String text;
		public int nLevel;
		public boolean expandable;
	}
	static public Item items[];

	static private boolean init;
	public int pos;
	public ArrayList<Integer> indexMap;
	public ArrayList<String> contents;
	public ResProvider(Context con, int p) throws IOException{

		if(!init){
			String res[];
			res = slurp(con.getAssets().open("logicE.txt"),100).split("\\n");
			items = new Item[res.length];
			for(int i=0;i<res.length;i++){
				items[i] = new Item();
				items[i].nLevel = levelOf(res[i]);
				items[i].text = res[i].substring(items[i].nLevel);
				if(i==0) continue;
				items[i-1].expandable = (items[i-1].nLevel<items[i].nLevel);
			}
			items[res.length-1].expandable = false;
		init =true;
		}

		pos = p;
		indexMap = new ArrayList<Integer>();
		contents = new ArrayList<String>();
		prepareConts();
	}
	private void prepareConts()
	{
		int nstar = items[pos].nLevel;
		for(int p=pos;p<items.length;p++){
			int pstar = items[p].nLevel;
			if(pstar<nstar)	break;
			if(pstar>nstar) continue;
			
			indexMap.add(p);
			contents.add(items[p].text);
		}
	}
	private int levelOf(String s){return s.lastIndexOf('#')+1;}
	private static String slurp(final InputStream is, final int bufferSize) throws IOException
	{
	  final char[] buffer = new char[bufferSize];
	  final StringBuilder out = new StringBuilder();
	  final Reader in = new InputStreamReader(is, "UTF-8");
	  for (;;) {
	    int rsz = in.read(buffer, 0, buffer.length);
	    if (rsz < 0)
	      break;
	    out.append(buffer, 0, rsz);
	  }
	  return out.toString();
	}

}