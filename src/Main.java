import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Main {
    public static final List<String> images = List.of(
            "image/001.png",
            "image/002.png",
            "image/003.png",
            "image/004.png",
            "image/005.png"
    );

    public static void main(String[] args) {
        images.forEach((src) -> applyNoise(src, 0.05, 3));
    }

    private static void applyNoise(String src, double noise, int offset) {
        if (noise < 0 || noise > 1) throw new RuntimeException("Noise level should be between 0 and 1");

        File input = new File(src);
        File output = new File("image/noise", input.getName());

        try {
            BufferedImage image = ImageIO.read(input);
            BufferedImage result = Noise.apply(image, noise, offset);
            ImageIO.write(result, "png", output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
