package leetcode;

/**
 * 79. 单词搜索（中等，回溯算法）
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/2/5
 * @version 8.0
 */
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || word.length() == 0)
            return false;
        boolean[][] marked = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if (backTrace(board, word, 0, i, j, marked)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean backTrace(char[][] board, String word, int index, int row, int col, boolean[][] marked) {
        if (index == word.length()) {
            return true;
        }
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length ||
                board[row][col] != word.charAt(index)) {
            return false;
        }

        if (!marked[row][col]) {
            marked[row][col] = true;
            if (backTrace(board, word, index + 1, row + 1, col, marked) ||
                    backTrace(board, word, index + 1, row, col + 1, marked) ||
                    backTrace(board, word, index + 1, row - 1, col, marked) ||
                    backTrace(board, word, index + 1, row, col - 1, marked)) {
                return true;
            }
            marked[row][col] = false;         // 回退
        }
        return false;
    }

}
