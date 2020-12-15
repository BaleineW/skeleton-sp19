import es.datastructur.synthesizer.GuitarString;

/* @author: Jingjing */

public class GuitarHero {
    private static String keyboard = "q2we4r5ty7u8i9op-[zxdcfvgbnjmk,.;/'";

    private static final double CONCERT_A = 440.0;
    private static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);

    public static void main(String[] args) {
        /* create guitar */
        GuitarString[] guitarStrings = new GuitarString[keyboard.length()];
        for (int i = 0; i < keyboard.length(); i++) {
            double freq = 440 * Math.pow(2, (i-24.0)/12.0);
            guitarStrings[i] = new GuitarString(freq);
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (keyboard.contains(String.valueOf(key))) {
                    guitarStrings[keyboard.indexOf(key)].pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = 0;
            for (int i = 0; i < keyboard.length(); i++) {
                sample += guitarStrings[i].sample();
                guitarStrings[i].tic();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

        }
    }
}
