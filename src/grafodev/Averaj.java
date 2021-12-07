package grafodev;

import java.util.ArrayList;

public class Averaj {

    ArrayList<Integer> l = new ArrayList();

    public void function(int[][] dizi) {
        int[][] dz = new int[dizi.length][dizi.length];//asıl grafın bozulmaması için üzerinde işlem yapacağımız bir boi dz grafı oluşturduk
        int[][] newDizi = new int[dizi.length - 1][dizi.length - 1];//bir düğümün çıkarıldığı boş graf tanımı
        int top = -1;//toplam connectivity değeri tutar
        for (int i = 0; i < dizi.length; i++) {
            dz = diziKopyalama(dizi);//Asıl grafı geçici boş grafa atma işlemi
            for (int j = 0; j < dizi.length; j++) {
                dz[i][j] = 0;//ilgili düğüm komşuluğu silinir
                dz[j][i] = 0;
            }
            newDizi = yeniDiziOlustur(dz);//oluşturulan yeni dizi (bir düğüm silinmiş halde)
            if (new bitisikMi().al(newDizi)) {//graf bitişikse
                kontrol(newDizi);
            }
            top += l.size();//koparılan düğüm sayısını tutarız
            l.clear();

        }
        System.out.println("Kav(G)=" + ((top + dizi.length) / dizi.length));
    }

    int[][] yeniDiziOlustur(int[][] dizi) {
        //bu fonksiyon asıl graftan atılacak düğümü siler ve yeni graf dizisini döndürür
        int d[][] = new int[dizi.length - 1][dizi.length - 1];
        ArrayList<ArrayList<Integer>> list;
        list = new ArrayList<>();
        for (int[] dizi1 : dizi) {//liste içinde liste tuttuk
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < dizi.length; i++) {
            for (int j = 0; j < dizi.length; j++) {
                list.get(i).add(dizi[i][j]);//parametre grafındaki tüm elemanları listeye ekledik
            }
        }
        int sayac = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (list.get(i).get(j) == 0) {
                    sayac++;//listede silinen ayrıtların bulunduğu düğümü tespit edecek sayac
                }
            }
            if (sayac == dizi.length) {//sayac dizi uzunluğu olduysa ilgili düğüm silinir
                list.remove(i);//ilgili düğüm ve for ile diğer düğümlerin buna denk gelen indislerinin silinmesi
                for (ArrayList<Integer> list1 : list) {
                    list1.remove(i);
                }
                break;
            } else {
                sayac = 0;
            }
        }
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                d[i][j] = list.get(i).get(j);//listeyi yeni oluşturulan grafa klonlama
            }
        }
        return d;//döndür yeni grafı
    }

    int[][] diziKopyalama(int[][] dizi) {
        int d[][] = new int[dizi.length][dizi.length];
        for (int i = 0; i < dizi.length; i++) {
            System.arraycopy(dizi[i], 0, d[i], 0, dizi.length);//asıl grafta bozulmalar olmaması için geçici grafa klon için yapıldı
        }
        return d;
    }

    int[][] kontrol(int[][] dizi) {
        if (new bitisikMi().al(dizi)) {//graf bitişikse hala
            l = new Dijkstra(dizi.length).dijkstra(dizi, 0);//dijkstra ile en yakın yolu bul ve yoğunluğu en büyük olanın düğüm etiketini l listesine at
            dizi = new Kopartma(dizi, l).kopart();//l listesindeki düğümleri diziden kopar
            return kontrol(dizi);//recursive başa dön
        } else {
            return null;
        }
    }
}
