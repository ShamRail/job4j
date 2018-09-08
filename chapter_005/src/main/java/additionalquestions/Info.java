package additionalquestions;

public class Info {

    private int addedCount;
    private int deletedCount;
    private int changedCount;

    public void setAddedCount(int addedCount) {
        this.addedCount = addedCount;
    }

    public int getAddedCount() {
        return this.addedCount;
    }

    public int getDeletedCount() {
        return this.deletedCount;
    }

    public int getChangedCount() {
        return this.changedCount;
    }

    public void increaseDeleted() {
        deletedCount++;
    }

    public void increaseChanged() {
        changedCount++;
    }
}
