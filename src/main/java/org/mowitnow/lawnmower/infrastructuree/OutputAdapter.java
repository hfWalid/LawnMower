package org.mowitnow.lawnmower.infrastructuree;

import org.mowitnow.lawnmower.domain.entity.Mower;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class OutputAdapter {

    public static void writeOutputFile(String filePath, List<Mower> mowers) throws IOException {
        Path outputPath = Path.of(filePath);

        if (Files.exists(outputPath)) {
            return;
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

        for (Mower mower : mowers) {
            writer.write(mower.getX() + " " + mower.getY() + " " + mower.getOrientation());
            writer.newLine();
        }

        writer.close();
    }
}

