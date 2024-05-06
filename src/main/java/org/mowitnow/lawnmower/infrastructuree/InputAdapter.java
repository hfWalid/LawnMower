package org.mowitnow.lawnmower.infrastructuree;

import org.mowitnow.lawnmower.domain.entity.Lawn;
import org.mowitnow.lawnmower.domain.entity.Mower;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InputAdapter {

    public static Lawn readInputFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String[] lawnDimensions = reader.readLine().split(" ");

        int width = Integer.parseInt(lawnDimensions[0]);
        int height = Integer.parseInt(lawnDimensions[1]);

        Lawn lawn = new Lawn(width, height);
        String line;

        while ((line = reader.readLine()) != null) {
            String[] mowerData = line.split(" ");

            int x = Integer.parseInt(mowerData[0]);
            int y = Integer.parseInt(mowerData[1]);
            char orientation = mowerData[2].charAt(0);

            Mower mower = new Mower(x, y, orientation);
            mower.setInstructions(reader.readLine());

            lawn.addMower(mower);
        }
        reader.close();
        return lawn;
    }
}

