package grafodev;

import java.util.LinkedList;

public class bitisikMi {

    static boolean durum; //grafın birleştirilmiş olup olmadığını tutmak

    static class Graf {

        int dugumSayisi;
        LinkedList<Integer>[] list; //düğüm komşuluklarını tutmak için array liste

        public Graf(int dugumSayisi) { 
            this.dugumSayisi = dugumSayisi;
            list = new LinkedList[dugumSayisi];//liste düğüm sayısı uzunlukta
            for (int i = 0; i < dugumSayisi; i++) {
                list[i] = new LinkedList<>();//liste içinde liste oluşturma
            }
        }

        public void ekleAyrit(int source, int destination) {

            list[source].addFirst(destination);
            //ilgili indislerin komşuluklarının listeye eklenmesi
            //örneğin 0 düğümünün komşuluğu 1 ve 2 ise 0. indise 1 ve 2 yazılır
            //aynı şekilde 1 ve 2 düğümlerine de 0 yazılır
            list[destination].addFirst(source);
        }

    }

    public void birleşikGrafMi(Graf grafim) {

        int dugumS = grafim.dugumSayisi;
        LinkedList<Integer> listem[] = grafim.list;

        boolean[] ziyaretEt = new boolean[dugumS];//ziyaret edilen düğümleri belirlemek için boolean dizisi

        ziyaretEt = DFS(0, listem, ziyaretEt);

        int sayac = 0;
        for (int i = 0; i < ziyaretEt.length; i++) {
            if (ziyaretEt[i]) {//ilgili düğüm ziyaret edildiyse sayac=sayac+1
                sayac++;
            }
        }
        durum = dugumS == sayac; 
        //dugum sayısı sayaca eşitse yani tüm düğümler gezilebildiyse dfs ağacında graf birleşiktir ve durum true olur
        //fakat eşit değilse ayrıktır ve durum false olur
    }

    public boolean[] DFS(int indis, LinkedList<Integer> liste[], boolean[] ziyaret) {

        ziyaret[indis] = true;//ilgili indiste işlem yapıldığınndan true ettik

        for (int i = 0; i < liste[indis].size(); i++) { //ilgili düğümün komşu sayısı kadar tur attı for
            int komsu = liste[indis].get(i); //komsu değişkenine indis düğümünün i. komşusu atıldı
            if (ziyaret[komsu] == false) {//ziyaret edilmemiş ise recursive olarak dfs çalışır
                DFS(komsu, liste, ziyaret);
            }
        }
        return ziyaret;//tüm işlem bitince grafın ziyaret edilen düğümler dizisi return edilir
    }

    public boolean al(int[][] d) {
        Graf graph = new Graf(d.length);
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[i].length; j++) {
                if (d[i][j] == 1) {//okunan grafın ayrıtlarını ekledik
                    graph.ekleAyrit(i, j);
                }
            }
        }
        new bitisikMi().birleşikGrafMi(graph);
        return durum;
    }
}
