public class Scoreboard {
    private int[] scores;
    int maximum;
    int trackedScores = 0;

    public Scoreboard(int maximum) {
        scores = new int[maximum];
    }

    public void addScore(int score) {
        if (trackedScores < maximum) {
            scores[trackedScores] = score;
            trackedScores++;
        } else {
            System.out.println("Scorecard is full.");
        }
    }

    public int getHighest() {
        if (trackedScores >= 1) {
            int maxScore = scores[0];
            for (int i = 0; i < trackedScores; i++) {
                if (scores[i] > maxScore) {
                maxScore = scores[i];
                } 
            }
            return maxScore;
        } else {
            return 0;
        }
    }

    public int getCount() {
        return trackedScores;
    }

    public static void main(String[] args) {
        Scoreboard sb = new Scoreboard(4);

        System.out.println(sb.getCount());    // Expected: 0

        sb.addScore(42);
        sb.addScore(17);
        sb.addScore(95);
        System.out.println(sb.getCount());    // Expected: 3
        System.out.println(sb.getHighest());  // Expected: 95

        sb.addScore(60);
        System.out.println(sb.getCount());    // Expected: 4
        System.out.println(sb.getHighest());  // Expected: 95

        // This should be discarded — already at capacity
        sb.addScore(100);
        System.out.println(sb.getCount());    // Expected: 4 (unchanged)
        System.out.println(sb.getHighest());  // Expected: 95 (unchanged)
    }

}