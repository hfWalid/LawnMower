package org.mowitnow.lawnmower.domain.servicee;

import org.mowitnow.lawnmower.domain.entity.Lawn;
import org.mowitnow.lawnmower.domain.entity.Mower;

import java.util.ArrayList;
import java.util.List;

public class MowItNowService {

    public static List<Mower> processInstructions(Lawn lawn) {
        List<Mower> mowers = new ArrayList<>();

        for (Mower mower : lawn.getMowers()) {
            processMowerInstructions(lawn, mower);
            mowers.add(mower);
        }

        return mowers;
    }

    private static void processMowerInstructions(Lawn lawn, Mower mower) {
        for (char instruction : mower.getInstructions().toCharArray()) {
            switch (instruction) {
                case 'G':
                    turnLeft(mower);
                    break;
                case 'D':
                    turnRight(mower);
                    break;
                case 'A':
                    moveForward(lawn, mower);
                    break;
                default:
                    break;
            }
        }
    }

    public static void turnLeft(Mower mower) {
        switch (mower.getOrientation()) {
            case 'N':
                mower.setOrientation('W');
                break;
            case 'W':
                mower.setOrientation('S');
                break;
            case 'S':
                mower.setOrientation('E');
                break;
            case 'E':
                mower.setOrientation('N');
                break;
        }
    }

    public static void turnRight(Mower mower) {
        switch (mower.getOrientation()) {
            case 'N':
                mower.setOrientation('E');
                break;
            case 'E':
                mower.setOrientation('S');
                break;
            case 'S':
                mower.setOrientation('W');
                break;
            case 'W':
                mower.setOrientation('N');
                break;
        }
    }

    private static void moveForward(Lawn lawn, Mower mower) {
        int newX = mower.getX();
        int newY = mower.getY();

        switch (mower.getOrientation()) {
            case 'N':
                newY++;
                break;
            case 'E':
                newX++;
                break;
            case 'S':
                newY--;
                break;
            case 'W':
                newX--;
                break;
        }

        if (newX >= 0 && newX <= lawn.getWidth() && newY >= 0 && newY <= lawn.getHeight()) {
            mower.setX(newX);
            mower.setY(newY);
        }
    }
}
