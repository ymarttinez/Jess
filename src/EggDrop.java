import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Math.*;

public class EggDrop {

	public static void main(String[] args) {
		
		try {
			BufferedReader in = new BufferedReader(new FileReader("C-small-practice.in"));
			BufferedWriter out = new BufferedWriter(new FileWriter("C-small-practice.out"));
			EggDrop x = new EggDrop();
			x.init();
			String str;
			in.readLine();
			while ((str = in.readLine()) != null) {
				String res = x.convert(str);
				out.write(res+"\n");

			}
			in.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	void init()
	{
		
		/*for(int D=1; D<memo.length; D++)
		{
			for(int B=1; B<=D; B++)
			{
				get(D, B);
			}
		}*/
		/*for(int D=2; D<memo.length; D++)
			System.out.println(get(D, D-1));*/
		/*for(int b=2; b<100; b++)
			System.out.println("for b: "+b+"->"+valid(b)+" ,"+get((int)valid(b), b)+"<->"+get((int)valid(b)+1, b));*/
	}
	long memo[][] = new long[129000][33];
	long get(int D, int B)
	{
		B = min(D, B);
		//assert(B<=D);
		if(B==0) return 0;
		
		//assert(B>=0);assert(D>=0);
		if(memo[D][B]>0)return memo[D][B];
		return memo[D][B] = get(D-1, B-1)+get(D-1, Math.min(D-1, B))+1;
		
	}
	int count=1;
	String convert(String in)
	{
		String[] sp = in.split(" ");
		int f = Integer.parseInt(sp[0]);
		int d = Integer.parseInt(sp[1]);
		int b = Integer.parseInt(sp[2]);
		b = min(b, d);
		//let the battle begin!
		long ff=0;
		int dd=0,bb=0;
		
		//find ff
		if(b>=33)ff=-1;
		else if(b==1)ff=d;
		else if(d>valid(b))ff=-1;
		else ff = get(d, b);
		
		//find dd
		if(b==1)
			dd=f;
		else
			for(dd=1; dd<=d; dd++)
			{
				if(get(dd, b)>=f)break;
			}
		
		//find bb
		if(d>=f)bb=1;
		else
			for(bb=2; bb<=min(b,32); bb++)
			{
				if(d>valid(bb))break;
				if(get(d, bb)>=f)break;
			}
		
		return "Case #"+(count++)+": "+ff+" "+dd+" "+bb;
	}
	int valid[]=new int[34];
	final long inf = 4294967296L-1;
	long valid(int b)
	{
		if(b==1)return inf;
		if(b>=33)return 32;
		if(valid[b]>0)return valid[b];
		int d=1;
		for(d=1; d<memo.length; d++)
		{
			if(get(d, b)>inf)break;
		}
		return valid[b]=d-1;
	}
}