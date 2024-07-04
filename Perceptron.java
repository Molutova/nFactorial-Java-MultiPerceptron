public class Perceptron {
    private int n;
    private double[] weight;

    public Perceptron(int n) {
        this.n = n;
        weight = new double[n];
    }

    public int numberOfInputs() {
        return n;
    }

    public double weightedSum(double[] x) {
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += x[i] * weight[i];
        }
        return sum;
    }

    public int predict(double[] x) {
        if (weightedSum(x) > 0)
            return +1;
        else
            return -1;
    }

    public void train(double[] x, int label) {
        if (label > predict(x)) {
            for (int i = 0; i < n; i++) {
                weight[i] += x[i];
            }
        }
        else if (label < predict(x)) {
            for (int i = 0; i < n; i++) {
                weight[i] -= x[i];
            }
        }
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < n; i++) {
            if (i < n - 1)
                s += weight[i] + ", ";
            else
                s += weight[i];
        }
        return "(" + s + ")";
    }

    public static void main(String[] args) {
        int n = 3;

        double[] training1 = { 5.0, -4.0, 3.0 };  // yes
        double[] training2 = { 2.0, 3.0, -2.0 };  // no
        double[] training3 = { 4.0, 3.0, 2.0 };  // yes
        double[] training4 = { -6.0, -5.0, 7.0 };  // no

        Perceptron perceptron = new Perceptron(n);
        StdOut.println(perceptron);
        perceptron.train(training1, +1);
        StdOut.println(perceptron);
        perceptron.train(training2, -1);
        StdOut.println(perceptron);
        perceptron.train(training3, +1);
        StdOut.println(perceptron);
        perceptron.train(training4, -1);
        StdOut.println(perceptron);
    }
}
