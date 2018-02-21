public class Test{
	public static void main(String[]args){
		NWaySetCache<Integer, String> c = new NWaySetCache<>(4,2);
		c.put(0,"hey");
		c.put(4,"ayo");
		if(c.containsKey(0)){
			System.out.println("Contains 0");
			System.out.println("c.get(0) is "+ c.get(0));
		}
		if(c.containsKey(4)){
			System.out.println("Contains 4");
			System.out.println("c.get(4) is "+ c.get(4));			
		}

		System.out.println("Done");
	}
}