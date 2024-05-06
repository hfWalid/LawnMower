package org.mowitnow.lawnmower.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Mower {
    private int x;
    private int y;
    private char orientation;
    private String instructions;

}