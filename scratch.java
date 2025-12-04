import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import static jdk.vm.ci.meta.JavaKind.Char;

class Scratch {

    public static void main() throws IOException {
        AC4 ac = new AC4();
        Path path = Path.of("ac4.txt");

        List<String> lines = Files.readAllLines(path);

        System.out.println(ac.adventofcode(lines));
    }

    public static void main_ac3() throws IOException {
        AC3 ac = new AC3();
        Path path = Path.of("ac3.txt");

        String line = Files.readString(path).trim();


        List<String> lines = Files.readAllLines(path);

//        System.out.println(ac.adventofcode(lines));
        System.out.println(ac.adventofcode2(lines));
    }

    public static void main_ac2() throws IOException {


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

        System.out.println(op.adventofcode(lines));
    }
}

class AC4 {
    public long adventofcode(List<String> numbers) {

        int ans = 0;
        List<List<String>> rolls = new  ArrayList<>();

        for(int i = 0; i < numbers.size(); i++){
            String[] a = numbers.get(i).split("");
            rolls.add(Arrays.asList(a));
        }


        while(true) {
            int before = ans;
            for (int i = 0; i < rolls.size(); i++) {
                for (int j = 0; j < rolls.get(i).size(); j++) {
                    String roll = rolls.get(i).get(j);
                    int count = 0;
                    if (roll.equals("@")) {
                        if (i - 1 >= 0 && j - 1 >= 0 && rolls.get(i - 1).get(j - 1).equals("@")) {
                            count++;
                        }
                        if (j - 1 >= 0 && rolls.get(i).get(j - 1).equals("@")) {
                            count++;
                        }
                        if (i + 1 < rolls.size() && j - 1 >= 0 && rolls.get(i + 1).get(j - 1).equals("@")) {
                            count++;
                        }
                        if (i - 1 >= 0 && rolls.get(i - 1).get(j).equals("@")) {
                            count++;
                        }
                        if (i - 1 >= 0 && j + 1 < rolls.get(i - 1).size() && rolls.get(i - 1).get(j + 1).equals("@")) {
                            count++;
                        }
                        if (i + 1 < rolls.size() && rolls.get(i + 1).get(j).equals("@")) {
                            count++;
                        }
                        if (i + 1 < rolls.size() && j + 1 < rolls.get(i + 1).size() && rolls.get(i + 1).get(j + 1).equals("@")) {
                            count++;
                        }
                        if (j + 1 < rolls.get(i).size() && rolls.get(i).get(j + 1).equals("@")) {
                            count++;
                        }
                        if (count < 4) {
                            ans++;
                            rolls.get(i).set(j, ".");
                        }
                    }
//                System.out.print(rolls.get(i).get(j)+" ");
                }
            }
            if(before == ans){break;}
        }
//            System.out.println("\n");
        return ans;
    }
}

class AC3{

    public long adventofcode2(List<String> numbers) {
        long ans = 0;

        for(int i =0;i<numbers.size();i++){
            char ch = '0';
            StringBuilder ansCh = new StringBuilder();
            String number = numbers.get(i);

            int index = 0;
            int needToFind = 12;
            int lastMax = 0;
            while(needToFind>0){
                while(index<number.length()-needToFind+1){
                    if(ch<number.charAt(index)){
                        ch = number.charAt(index);
                        lastMax = index;
                    }
                    index++;
                }
                ansCh.append(ch);
                index = lastMax+1;
                ch = '0';
                needToFind--;
            }
//            System.out.println(ansCh);
            ans += Long.parseLong(ansCh.toString());
        }

        return ans;
    }

    public int adventofcode(List<String> numbers) {
        int ans = 0;


        for(int i = 0; i < numbers.size(); i++){
            char ch = '0';
            String number = numbers.get(i);
            System.out.println(number.toCharArray());
            int index = 0;
            for(int j=0;j<number.length()-1;j++){
                if (number.charAt(j) > ch){
                    ch = number.charAt(j);
                    index = j;
                }
            }

            char newCh = number.charAt(index+1);
            for(int j =index+1;j<number.length();j++){
                if (number.charAt(j) > newCh){
                    newCh = number.charAt(j);
                }
            }
            System.out.println(ch+""+newCh);
            String ansCh = ch+""+newCh;
            ans += Integer.parseInt(ansCh);
//            for(Character ch : number.toCharArray()){
//                System.out.println(ch);
//                if
//            }
        }

        return ans;
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
//                    if( is.index(p) == is.index(secondPointer)){
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
