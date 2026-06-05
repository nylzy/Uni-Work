class MultFinder extends Thread {
    private List<Integer> output;
    private int number, limit;

    public void run() {
        for (int i = 0; i < limit; i++) {
            if (i % number != 0) continue;
            synchronized (output) {
                output.add(i);
            }
        }
    }
}

// In main:
Thread threeFinder = new MultFinder(mults, 3, limit);
Thread sevenFinder = new MultFinder(mults, 7, limit);
threeFinder.start();
sevenFinder.start();