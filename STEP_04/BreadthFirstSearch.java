import java.util.*;
import java.io.*;

public class BreadthFirstSearch {
    public static void main(String[] args) {
        ReadFile rf = new ReadFile();
        rf.readPagesFile();
        rf.readLinksFile();
        Scanner scanner = new Scanner(System.in);
        System.out.print("スタートするページのタイトル：");
        String start_pageTitle = scanner.next();
        System.out.print("ゴールするページのタイトル：");
        String goal_pageTitle = scanner.next();
    }

    static class ReadFile {

        void readPagesFile() {
            try {
                File file = new File("wikipedia_links/pages.txt");
                BufferedReader br = new BufferedReader(new FileReader(file));
                ArrayList<String> pageTitleArray = new ArrayList<String>();
                String pageTitle;
                int num = 0;
                while ((pageTitle = br.readLine()) != null) {
                    //配列にいれる
                    String pageTitleSplit[] = pageTitle.split("\\t", 0);
                    pageTitleArray.add(pageTitleSplit[1]);
                }
                br.close();
                System.out.println(pageTitleArray.get(3));
                System.out.println(pageTitleArray.get(4));
                System.out.println("pages.txtファイル読み込み完了しました");
            } catch (FileNotFoundException e) {
                System.out.println(e);
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        void readLinksFile() {
            try {
                File file = new File("wikipedia_links/miniLinks.txt");
                String linkLine;//1行全体
                BufferedReader br = new BufferedReader(new FileReader(file));
                int links[][] = new int[150000][500000];
                int edge = 0;
                int tmp = 0;

                while ((linkLine = br.readLine()) != null) {
                    String linkLineSplit[] = linkLine.split("\\t", 0);
                    int linkNum = Integer.parseInt(linkLineSplit[0]);
                    if (tmp == linkNum) {
                    } else if (tmp != linkNum) {
                        tmp = linkNum;
                        edge = 0;
                    }
                    links[tmp][edge] = Integer.parseInt(linkLineSplit[1]);
                    edge++;
                }
                br.close();
                System.out.println("links.txtファイル読み込み完了しました");

            } catch (FileNotFoundException e) {
                System.out.println(e);
            } catch (IOException e) {
                System.out.println(e);
            }
        }

    }
}