package Aufgabe1;

public class Test {
	
	
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	public static void main(final String[] args) {
		Dictionary dic = new HashDictionary();
		dic.insert(0,"Halli");
		dic.insert(1,"hallo");
		dic.insert(2,"das");
		dic.insert(3,"ist");
		dic.insert(4,"ein");
		dic.insert(5,"Test");
		dic.insert(13, "2. Halli");
		dic.insert(14, "2. Hallo");
		dic.search(5);
		
		System.out.println(dic.toString());
	}
}
