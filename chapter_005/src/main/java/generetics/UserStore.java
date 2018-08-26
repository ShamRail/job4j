package generetics;

public class UserStore extends AbstractStore implements Store<User> {

    private SimpleArray<User> store = new SimpleArray<>(10);

    @Override
    public void add(User model) {
        this.store.add(model);
    }

    @Override
    public boolean replace(String id, User model) {
        boolean result = false;
        int indexById = super.findIndexByValue(this.store, id);
        if (indexById != -1) {
            this.store.set(indexById, model);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        return super.delete(this.store, id);
    }

    @Override
    public User findById(String id) {
        return (User) super.findById(this.store, id);
    }

}
