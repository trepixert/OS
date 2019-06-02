package labaratory.fourth;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TablePanel extends JPanel {

    private PhysicalMemoryTable physicalMemoryTable = PhysicalMemoryTable.getInstance();

    private int rowsCount;

    private int columnsCount;

    private int lastRowBlocks;

    private int sizeCell = 15;

    public TablePanel(int neededBlocks, int width) {
        columnsCount = width / 15;
        rowsCount = neededBlocks / columnsCount;
        lastRowBlocks = neededBlocks - rowsCount * columnsCount;
    }

    @Override
    protected void paintComponent(Graphics gh) {
        ArrayList<MemoryBlock> memoryBlocks = physicalMemoryTable.getMemoryBlocks();

        for (int i = 0; i < memoryBlocks.size(); i++) {
            int rowIndex = i / columnsCount;
            int columnIndex = i % columnsCount;

            int y = sizeCell * rowIndex;
            int x = sizeCell * columnIndex;

            drawCell(gh, x, y, memoryBlocks.get(i).getState());
        }

        drawGrid(gh, sizeCell);
    }

    private void drawGrid(Graphics drp, int sizeCell) {
        drp.setColor(Color.BLACK);

        for (int i = 0; i < columnsCount; i++) {
            drp.drawLine(i * sizeCell, 0, i * sizeCell, rowsCount * sizeCell);
        }

        for (int i = 0; i < rowsCount; i++) {
            drp.drawLine(0, i * sizeCell, columnsCount * sizeCell, i * sizeCell);
        }

        for (int i = 0; i <= lastRowBlocks; i++) {
            drp.drawLine(0, sizeCell * rowsCount, i * sizeCell, sizeCell * rowsCount);
        }

        for (int i = 0; i <= lastRowBlocks; i++) {
            drp.drawLine(i * sizeCell, sizeCell * rowsCount, i * sizeCell, sizeCell * rowsCount + sizeCell);
        }
    }

    private void drawCell(Graphics drp, int x, int y, MemoryBlockStatus state) {
        switch (state) {
            case FREE:
                drp.setColor(new Color(238, 230, 231));
                break;
            case BUSY:
                drp.setColor(new Color(255, 29, 51));
                break;
            case SELECTED:
                drp.setColor(new Color(13, 195, 7));
        }

        drp.fillRect(x, y, sizeCell, sizeCell);
    }
}
