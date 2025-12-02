import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Scratch {

    public static void main() throws IOException {
        

        AC2 ac2 = new AC2();
        Path path = Path.of("ac2.txt");

        String line = Files.readString(path).trim();

        List<String> numbers = new ArrayList<>();

        for (String part: line.split(",")) {
            numbers.addAll(Arrays.asList(part.split("-")));
        }

        System.out.println(ac2.adventofcode(numbers));
//        System.out.println(ac2.adventofcode2("2121212124"));

    }

    public static void main_ac1(String[] args) throws IOException {

        AC1 op = new  AC1();
        Path path = Path.of("data.txt");

        List<String> lines = Files.readAllLines(path);

        System.out.println(lines.size());
        System.out.println(op.adventofcode(lines));
    }
}

class AC2 {

    public boolean adventofcode2(String is){
        for (int i=0;i<is.length();i++){
            for(int j= i+1;j<=is.length();j++){
//                System.out.println(is.substring(i,j));
                String sub = is.substring(i,j);
                if (is.replaceAll(sub, "").isEmpty() && !sub.equals(is)){
                    System.out.println(is);
                    return true;
                };
            }
        }
        return false;
    }

    public long adventofcode(List<String> numbers) {

        long ans =0;

        for(int j=0;j<numbers.size();j=j+2){
            for(long i = Long.parseLong(numbers.get(j)); i<=Long.parseLong(numbers.get(j+1)); i++){
                String is = String.valueOf(i);
                int secondPointer = is.length()/2;
                int flag = -1;

                if(adventofcode2(is)){
                    ans += i;
                }

//                for(int p=0; p<(is.length()+1)/2; p++, secondPointer++){
//                    if( is.charAt(p) == is.charAt(secondPointer)){
//                        flag = 1;
//                    } else{
//                        flag = -1;
//                        break;
//                    }
//                }
//
//                if(flag == 1 ){//&& is.length()%2==0){
//                    System.out.println(i);
//                    ans += i;
//                } else{
//                    if (adventofcode2(is)){
//                        ans += i;
//                    };
//                }
            }
        }
        return ans;
    }
}

class AC1 {
    public int add(int a, int b){
        return a+b;
    }


    public int adventofcode(List<String> a){
        int ans = 0;
        int v=50;
        for(int i=0;i<a.size();i++){



            if(a.get(i).startsWith("L")){
                if(i>0 & v == 0){
                    ans--;
                }
                String digits = a.get(i).replaceAll("\\D+","");

                String lastTwo;
                String prefix=null;
                if(digits.length()>2){
                    prefix = digits.substring(0, digits.length()-2);
                    lastTwo = digits.substring(digits.length()-2);
                } else{
                    lastTwo = digits;
                }

//                String num = digits.length() <=2?digits:digits.substring(digits.length()-2);

                v=v-Integer.parseInt(lastTwo);
                if(prefix!=null){
                    ans +=Integer.parseInt(prefix);
                }
            }
            if(a.get(i).startsWith("R")){
                String digits = a.get(i).replaceAll("\\D+","");

                String lastTwo;
                String prefix=null;
                if(digits.length()>2){
                    prefix = digits.substring(0, digits.length()-2);
                    lastTwo = digits.substring(digits.length()-2);
                } else{
                    lastTwo = digits;
                }

                v=v+Integer.parseInt(lastTwo);
                if(prefix!=null){
                    ans +=Integer.parseInt(prefix);
                }
            }
            if(v<0) {
                v = 100 + v;
                ans++;
            } else if (v>100) {
                v=v-100;
                ans++;
            } else if (v==100) {
                v=0;
            }
            if (v==0) {
                ans++;
            }
            System.out.println(v + " -> "+ ans);
        }
        return ans;
    }
}
