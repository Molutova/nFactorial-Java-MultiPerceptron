import java.awt.Color;
//(при Color color = picture.get(col, row);  импорт сам появляется )

public class ImageClassifier {
    public static double[] extractFeatures(Picture picture) {
        int width = picture.width();
        int height = picture.height();
        int n = width * height;
        double[] grayscaleValues = new double[n];
        int counter = 0;

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Color color = picture.get(col, row);
                int gray = color.getRed();
                grayscaleValues[counter] = gray;
                counter++;
            }
        }
        return grayscaleValues;
    }


    public static void main(String[] args) {
        In training = new In(args[0]);
        int m1 = training.readInt();
        int width1 = training.readInt();
        int height1 = training.readInt();

        MultiPerceptron multiPerceptron = new MultiPerceptron(m1, width1 * height1);
        while (!training.isEmpty()) {
            String filename = training.readString();
            Picture picture = new Picture(filename);
            int label = training.readInt();
            multiPerceptron.trainMulti(extractFeatures(picture), label);
        }

        In testing = new In(args[1]);
        testing.readInt();
        testing.readInt();
        testing.readInt();
        int sum = 0;
        int total = 0;
        while (!testing.isEmpty()) {
            String filename = testing.readString();
            Picture picture = new Picture(filename);
            int label = testing.readInt(); // reads label
            int predict = multiPerceptron.predictMulti(extractFeatures(picture));
            if (predict != label) {
                sum++;
                StdOut.println(filename + ", label = " + label + ", predict = " + predict);
            }
            total++;
        }
        double error = (double) sum / total;
        StdOut.println("Test error rate = " + error);
    }
}
