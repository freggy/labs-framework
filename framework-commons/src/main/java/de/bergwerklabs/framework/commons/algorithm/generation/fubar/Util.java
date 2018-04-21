package de.bergwerklabs.framework.commons.algorithm.generation.fubar;

import java.awt.Color;
import java.awt.image.BufferedImage;

/** Created by Benedikt on 19.06.2017. */
public class Util {

  public static BufferedImage createImageFromBoard(TileType[] board, int boardSize) {
    BufferedImage image =
        new BufferedImage(boardSize * 6 + 1, boardSize * 6 + 1, BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < boardSize * 6 + 1; i++) {
      image.setRGB(i, 0, new Color(224, 224, 244).getRGB());
      image.setRGB(0, i, new Color(224, 224, 224).getRGB());
    }

    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        TileType tileType = board[GridCoordinate.toIndex(row, col, boardSize)];

        int x = col * 6 + 1;
        int y = row * 6 + 1;

        for (int i = 0; i < 6; i++) {
          for (int j = 0; j < 6; j++) {
            image.setRGB(x + j, y + i, Color.WHITE.getRGB());
          }
        }

        for (int i = 0; i < 6; i++) {
          image.setRGB(x + i, y + 5, new Color(224, 224, 224).getRGB());
          image.setRGB(x + 5, y + i, new Color(224, 224, 224).getRGB());
        }

        if (tileType == null) continue;

        if (tileType.getDirections().contains(Direction.UP)) {
          image.setRGB(x + 2, y, Color.BLACK.getRGB());
          image.setRGB(x + 2, y + 1, Color.BLACK.getRGB());
          image.setRGB(x + 2, y + 2, Color.BLACK.getRGB());
        }

        if (tileType.getDirections().contains(Direction.DOWN)) {
          image.setRGB(x + 2, y + 2, Color.BLACK.getRGB());
          image.setRGB(x + 2, y + 3, Color.BLACK.getRGB());
          image.setRGB(x + 2, y + 4, Color.BLACK.getRGB());
        }

        if (tileType.getDirections().contains(Direction.LEFT)) {
          image.setRGB(x, y + 2, Color.BLACK.getRGB());
          image.setRGB(x + 1, y + 2, Color.BLACK.getRGB());
          image.setRGB(x + 2, y + 2, Color.BLACK.getRGB());
        }

        if (tileType.getDirections().contains(Direction.RIGHT)) {
          image.setRGB(x + 2, y + 2, Color.BLACK.getRGB());
          image.setRGB(x + 3, y + 2, Color.BLACK.getRGB());
          image.setRGB(x + 4, y + 2, Color.BLACK.getRGB());
        }
      }
    }

    return image;
  }
}
