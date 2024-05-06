package org.mowitnow.lawnmower.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lawn {
    private int width;
    private int height;
    private List<Mower> mowers;

    public Lawn(int width, int height) {
        this.width = width;
        this.height = height;
        this.mowers = new ArrayList<>();
    }

    public void addMower(Mower mower) {
        mowers.add(mower);
    }

}