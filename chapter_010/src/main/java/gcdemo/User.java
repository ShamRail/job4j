package gcdemo;

public class User {

    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println(String.format("Object %s has destroyed", name));
    }

    public static void main(String[] args) throws InterruptedException {
        int mb = 1024 * 1024;
        Runtime runtime = Runtime.getRuntime();
        System.out.println(String.format("Max memory : %s", runtime.maxMemory() / mb));
        System.out.println(String.format("Total memory : %s", runtime.totalMemory() / mb));
        User[] users = new User[16];
        for (int i = 0; i < users.length; i++) {
            users[i] = new User(String.format("Object %s", i + 1));
        }

        System.out.println(String.format("Using memory : %s%%",
                (int) ((1 - runtime.freeMemory() / (double) runtime.totalMemory()) * 100)));
        users = null;

        Thread.sleep(1000);
    }
}
