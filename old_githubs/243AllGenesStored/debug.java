//quiz debug practice quiz
//
public class debug {
    public void findAbc(String input) {
    int index = input.indexOf("abc");
    //System.out.println("index " + index);
    //System.out.println(input.length());
    
    while (true) {
        if (index == -1 || index >= input.length() - 3) {
            break;
        }
        String found = input.substring(index+1, index+4);
        //System.out.println((index+1)+", "+(index+4));
        System.out.println(found);
        index = input.indexOf("abc", index+3);
        int indexq6 = input.indexOf("abc", index+4);
        System.out.println("index after updating " +indexq6);
    }
   }
   public void test() {
    //no code yet
    //findAbc("abcd");
    //findAbc("abcdabc");
    //findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
    findAbc("abcabcabcabca");
   }
}
