public class MultiPerceptron {
    private int m;
    private int n;
    private Perceptron[] perceptron;

    public MultiPerceptron(int m, int n) {
        this.m = m;
        this.n = n;
        this.perceptron = new Perceptron[m];
        for (int i = 0; i < m; i++) {
            this.perceptron[i] = new Perceptron(n);
        }
    }

    public int numberOfClasses() {
        return m;
    }

    public int numberOfInputs() {
        return n;
    }

    public int predictMulti(double[] x) {
        double max = Integer.MIN_VALUE;
        int index = -1;
        for (int i = 0; i < m; i++) {
            if (perceptron[i].weightedSum(x) > max) {
                index = i;
                max = perceptron[i].weightedSum(x);
            }
        }
        return index;
    }

    public void trainMulti(double[] x, int label) {
        for (int i = 0; i < m; i++) {
            if (label == i) {
                perceptron[i].train(x, 1);
            }
            else {
                perceptron[i].train(x, -1);
            }
        }
    }

    public String toString() {
        String x = "";
        for (int i = 0; i < m; i++) {
            if (i < m - 1) {
                x += perceptron[i] + ", ";
            }
            else {
                x += perceptron[i];
            }
        }
        return "(" + x + ")";
    }


    public static void main(String[] args) {
        int m = 2;
        int n = 3;

        double[] training1 = { 5.0, -4.0, 3.0 };
        double[] training2 = { 2.0, 3.0, -2.0 };
        double[] training3 = { 4.0, 3.0, 2.0 };
        double[] training4 = { -6.0, -5.0, 7.0 };
        
        MultiPerceptron perceptron = new MultiPerceptron(m, n);
        StdOut.println(perceptron);
        perceptron.trainMulti(training1, 1);
        StdOut.println(perceptron);
        perceptron.trainMulti(training2, 0);
        StdOut.println(perceptron);
        perceptron.trainMulti(training3, 1);
        StdOut.println(perceptron);
        perceptron.trainMulti(training4, 0);
        StdOut.println(perceptron);

    }
}
