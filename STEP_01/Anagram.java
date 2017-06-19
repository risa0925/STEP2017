/**
 * Created by RISA on 2017/05/28.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Arrays;


public class Anagram {
    public static void main(String[]args){
        String[] dictWord = new String[72412];
        //1. 辞書を読み込む
        try {
            File file = new File("dictionary.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            int count = 0;
            //dicWord[]の配列に辞書の単語を格納していく
            while((str = br.readLine()) != null){
                dictWord[count] = str.toUpperCase();
//                //dictWordSort[count]の単語内をソートする
//                char[] sortWordAlp = str.toCharArray();
//                java.util.Arrays.sort(sortWordAlp);
                count++;
            }
            br.close();

        }catch(Exception e){
            System.out.println(e);
        }
        System.out.println("辞書の読み込みが完了しました");

        //2. ユーザの入力
        String input;
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print("アナグラムを生成したいアルファベット:");
            input = sc.next();
            String inputStrUpper = input.toUpperCase();
//            //入力された文字列inputStrUpperをソートする
//            char[] sortInput = input.toCharArray();
//            java.util.Arrays.sort(sortInput);

            //3. アナグラム作成
            for(int i = 0; i < dictWord.length; i++){
                StringBuffer sb = new StringBuffer(dictWord[i]);
                for(int j = 0; j < inputStrUpper.length(); j++){
                    char inputAlp = inputStrUpper.charAt(j);
                    int alpIndex = sb.indexOf(String.valueOf(inputAlp));
                    if(alpIndex != -1){
                        sb = sb.deleteCharAt(alpIndex);
                        if(sb == null || sb.length() == 0){
                            System.out.println("アナグラム："+dictWord[i]);
                            return;
                        }
                    }
                }
            }
            System.out.println("単語がみつかりませんでした。");
        }
    }
}
