public class NQueens extends Game{
    public boolean checkpos(int[] q, int n) {
        for (int i = 0; i < n; i++) {
            if ((q[i] == q[n])||((q[i] - q[n]) == (n - i))||((q[n] - q[i]) == (n - i)))
                return false;
        }
        return true;
    }

    public NQueens(int n) {
        super(n);
    }

    public void insertSolutions(int[] q) {
        int n = q.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (q[i] == j) System.out.println("i,j = "+i+""+j);;;
            }

        }

    }

    public void solve(int n) {
        solutionSequance.clear();
        int[] a = new int[n];
        solve(a, 0);
    }

    public void solve(int[] q, int k) {
        int n = q.length;
        if (k == n) insertSolutions(q);
        else {
            for (int i = 0; i < n; i++) {
                q[k] = i;
                if (checkpos(q, k))
                    solve(q, k+1);
            }
        }
    }
}
