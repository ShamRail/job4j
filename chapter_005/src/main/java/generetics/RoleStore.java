package generetics;

public class RoleStore extends AbstractStore implements Store<Role> {

    private SimpleArray<Role> store = new SimpleArray<>(10);

    @Override
    public void add(Role model) {
        this.store.add(model);
    }

    @Override
    public boolean replace(String id, Role model) {
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
    public Role findById(String id) {
        return (Role) super.findById(this.store, id);
    }

}

