/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicalrefactorings.floodfill;

import java.awt.Color;

/**
 *
 * @author SHELDON
 */
public interface NewInterface {

    Grid<Color> fillAt(Grid<Color> original, int startX, int startY, Color color);
    
}
