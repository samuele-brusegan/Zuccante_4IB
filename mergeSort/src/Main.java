import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        boolean f = true;
        for (int i = 0; i < 1000; i++) {
            if (!test()) {
                System.out.println("!!!!!! Test fallito: i = " + i);
                f = false;
                break;
            }
        }
        System.out.println("Test completato: " + f);
    }

    private static boolean test() {
        int len = 100000;
        int[] a = new int[len];
        for (int i = 0; i < a.length; i++) a[i] = (int)(Math.random()*len);

        List<Integer> uList = new ArrayList<>();
        for (int i : a) uList.add(i);

        List<Integer> oList = mergeSort(uList);
        return isOrdered(oList);
    }

    private static <T extends Comparable<T>> boolean isOrdered(List<T> oList) {
        for (int i = 0; i < oList.size() - 1; i++) {
            //Il successivo è maggiore di quello corrente
            if (oList.get(i).compareTo(oList.get(i + 1)) > 0) return false;
        }
        return true;
    }
    
    // Sostituire `Comparable<Object>` con il tipo di oggetto da ordinare
    public static <T extends Comparable<T>> List<T> mergeSort(List<T> uList) {
        if (uList.size() <= 1) return uList;

        //Inizializzo e popolo un array sinistro e un array destro
        List<T> uSx = uList.subList(0,              uList.size()/2);
        List<T> uDx = uList.subList(uList.size()/2, uList.size());

        List<T> sx = mergeSort(uSx);
        List<T> dx = mergeSort(uDx);

        List<T> ord = new ArrayList<>();

        int iSx = 0;
        int iDx = 0;

        //
        while ( iDx < dx.size() || iSx < sx.size() ) {
            // Define objects
            T oSx = null;
            T oDx = null;

            // Set Flags
            boolean isDxEnded = (iDx == dx.size());
            boolean isSxEnded = (iSx == sx.size());

            //Add alias
            if (!isDxEnded) oDx = dx.get(iDx);
            if (!isSxEnded) oSx = sx.get(iSx);

            if (isDxEnded || (!isSxEnded && oSx.compareTo( oDx ) <= 0)) {
                ord.add( oSx );
                iSx++;
            } else {
                ord.add( oDx );
                iDx++;
            }
        }
        return ord;
    }
}