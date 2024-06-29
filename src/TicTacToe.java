import java.io.FileOutputStream;
import java.io.IOException;

public class TicTacToe {

    public static void saveGameState(int[] gameState, String filePath) {
        if (gameState.length != 9) {
            throw new IllegalArgumentException("Массив gameState должен содержать ровно 9 элементов");
        }

        int packedState = 0;
        for (int i = 0; i < 9; i++) {
            packedState |= (gameState[i] & 0b11) << (i * 2);
        }

        byte[] bytes = new byte[3];
        for (int i = 0; i < 3; i++) {
            bytes[i] = (byte) ((packedState >> (i * 8)) & 0xFF);
        }

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(bytes);
        } catch (IOException e) {
            System.out.println("Ошибка при записи файла: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        int[] gameState = {0, 1, 2, 0, 1, 2, 0, 1, 2};
        saveGameState(gameState, "gameState.dat");
    }
}
