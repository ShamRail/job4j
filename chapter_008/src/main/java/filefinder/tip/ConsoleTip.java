package filefinder.tip;

/**
 * Подсказки в консоли.
 */
public class ConsoleTip implements Tip {

    /**
     * Выводит подсказку в консоль.
     * @param message сообщение.
     */
    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }
}
