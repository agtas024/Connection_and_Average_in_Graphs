package grafodev;

import java.util.ArrayList;

public class Yogunluk {

    public ArrayList<Integer> list = new ArrayList<>();
    public int[] d;

    public Yogunluk(int[] d) {
        this.d = d;
    }

    public ArrayList<Integer> yogunluk() {
        for (int i = 0; i < d.length; i++) {
            if (d[i] == 1) {//ağaç dizisindeki en çok çocuğa sahip düğümlerin indisini listeye ekleriz
                list.add(i);
            }
        }
        return list;
    }
}
