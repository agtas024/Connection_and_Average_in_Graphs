package grafodev;

import java.util.ArrayList;

public class Main {

    ArrayList<Integer> l;

    public Main() {
        this.l = new ArrayList();//boş liste oluşturma (null olmayan boş)
    }

    public int[][] islem(int[][] d) {
        bitisikMi b = new bitisikMi();
        Dijkstra t = new Dijkstra(d.length);

        if (!b.al(d)) {//graf birleştirilmemiş ise
            if (l.isEmpty()) {//eğer koparılan düğümlerin tutulduğu l boş ise graf birleştirilmemiştir zaten
                System.out.println("Graf Zaten Birleştirilmemiş Graftır.");
                return null;
            }
            System.out.println(l + " Düğümleri koparıldı.");
            System.out.println();
            System.out.println("K(G)=" + l.size());
            return null;
        } else {//birleşikse graf
            l = t.dijkstra(d, 0);//grafın matrisini dijkstra algoritmasına tabi tut ve yoğunluk listesini l ye at
            d = new Kopartma(d, l).kopart();//yoğunluk listesine göre d grafını parçala ve güncelle
        }
        return islem(d);//işlemi recursive olarak devam ettir. amaç parçalı graf elde etmek
    }

    public static void main(String[] args) {
        long StartTime = System.currentTimeMillis();
        Main m = new Main();
        Averaj a = new Averaj();
        /*
        int graph1[][] = new int[][]{
            {0, 1, 0, 0, 1},
            {1, 0, 1, 1, 0},
            {0, 1, 0, 0, 0},
            {0, 1, 0, 0, 1},
            {1, 0, 0, 1, 0}};
         */
        int graph1[][] = new int[][]{//cycle
            {0, 1, 0, 1},
            {1, 0, 1, 0},
            {0, 1, 0, 1},
            {1, 0, 1, 0}};/*
        int graph1[][] = new int[][]{//yıldız
            {0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1},
            {1, 1, 1, 1, 0}};
        /*    int graph1[][] = new int[][]{//ornek
            {0, 1, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 1, 0, 0, 0, 0, 1},
            {0, 0, 1, 0, 1, 0, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 1, 0}};*/

 /*   int graph1[][] = new int[][]{//path
            {0,1,0,0},
            {1,0,1,0},
            {0,1,0,1},
            {0,0,1,0}
      };*/
 /*
        int graph1[][] = new int[][]{
            /*
            1------0------3
            |    /        |
            |  /          |
            | /           |
            2             4
            
            {0, 1, 1, 1, 0},
            {1, 0, 1, 0, 0},
            {1, 1, 0, 0, 0},
            {1, 0, 0, 0, 1},
            {0, 0, 0, 1, 0}}; */
 /* int graph1[][] = new int[][]{ //K4
            {0, 1, 1, 1},
            {1, 0, 1, 1},
            {1, 1, 0, 1},
            {1, 1, 1, 0}}; 
        /*int graph1[][] = new int[][]{ //K2,3
            {0, 0, 0, 1, 1},
            {0, 0, 0, 1, 1},
            {0, 0, 0, 1, 1},
            {1, 1, 1, 0, 0},
            {1, 1, 1, 0, 0}};*/
        int graph2[][] = a.diziKopyalama(graph1);
        m.islem(graph1);
        System.out.println();
        a.function(graph2);
        System.out.println();
        long EndTime = System.currentTimeMillis();
        long EstTime = StartTime - EndTime;
        double seconds = (double) EstTime / 1000;
        System.out.println("Geçen süre : " + -seconds + " saniye");
    }

}
