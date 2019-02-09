package ru.job4j.lsp.food;

import java.util.Date;

/**
 * Food.
 */

public class Food {

    /**
     * food's name.
     */
    private final String name;

    /**
     * date of producing.
     */
    private final Date createDate;

    /**
     * date of expiration's end.
     */
    private final Date expiredDate;

    /**
     * price.
     */
    private float price;

    public Food(String name, Date createDate, Date expiredDate, float price) {
        this.name = name;
        this.createDate = createDate;
        this.expiredDate = expiredDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public float getPrice() {
        return price;
    }

    /**
     * reduce price on particular percent.
     * @param percent percent.
     * @return true if it has done else false.
     */
    public boolean reducePrice(int percent) {
        boolean result = false;
        if (percent >= 0 && percent <= 100) {
            price = (1 - (float) percent / 100) * price;
            result = true;
        }
        return result;
    }

    /**
     * calculate percentage of common days count.
     * @param currentTime
     * @return
     */
    public short getUsingPercentage(Date currentTime) {
        double commonTime = expiredDate.getTime() - createDate.getTime();
        double timeAgo = currentTime.getTime() - createDate.getTime();
        return (short) ((timeAgo / commonTime) * 100);
    }
}
