package grafodev;

import java.util.ArrayList;

public class Kopartma {

    public int[][] d;
    public ArrayList<Integer> list;

    public Kopartma(int[][] d, ArrayList<Integer> list) {
        this.list = list;
        this.d = d;
    }

    public int[][] kopart() {
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < list.size(); j++) {
                boolean b = s(i, j);
                if (!b) {//tek düğüm koparılınca birleştirilmemiş olduysa
                    return d;
                }
            }
        }
        //eğer tek düğümle bu işlem başarılmadıysa tüm düğümleri çıkarırız
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < list.size(); j++) {
                d[i][list.get(j)] = 0;
                d[list.get(j)][i] = 0;
            }
        }
        return d;
    }

    public boolean s(int i, int j) {
        bitisikMi b = new bitisikMi();
        d[i][list.get(j)] = 0;//grafın listede yer alan düğüm komşulukları sıfır oldu yani koparıldı
        d[list.get(j)][i] = 0;
        if (b.al(d)) {//koparma işlemi soonrası graf hala birleştirilmiştir ve diğer düğümleri tek tek çıkarıp denemek için yenden edge eklenir
            d[i][list.get(j)] = 1;
            d[list.get(j)][i] = 1;
            return true;
        } else {//graf bağlı değilse ilgili düğüm koparılınca işlem tamamlanmıştır
            return false;
        }
    }
}
