public class TempLog {
    int trackedTemps = 0;
    double[] temps;

    public TempLog(int numTemps) {
        temps = new double[numTemps];
    }

    public void addTemp(double newTemp) {
        if (trackedTemps < temps.length) {
            temps[trackedTemps] = newTemp;
            trackedTemps += 1;
        } else {
            System.out.println("Full Capacity");
        }
    }

    public double rangeMax(int start, int end) {
        double max = temps[start];
        for (int i = start + 1; i <= end; i++) {
            if (temps[i] > max) {
                max = temps[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        TempLog log = new TempLog(10); // Capacity for 10 measurements
        log.addTemp(-4.3);
        log.addTemp(10.5);
        log.addTemp(18.6);
        log.addTemp(27.9);
        log.addTemp(22.6);
        System.out.println(log.rangeMax(0, 0)); // Should return -4.3
        System.out.println(log.rangeMax(1, 2)); // Should return 18.6
        System.out.println(log.rangeMax(1, 4)); // Should return 27.9
        log.addTemp(35.0);
        System.out.println(log.rangeMax(2, 5)); // Should return 35.0
    }

}