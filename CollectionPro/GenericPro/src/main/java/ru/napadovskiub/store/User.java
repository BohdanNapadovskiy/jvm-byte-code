package ru.napadovskiub.store;

/**
 * Class extends Base.
 */
public class User extends Base {

    /**
     * user id.
     */
    private String id;

    /**
     * Method return user id.
     * @returnuser id.
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * Method set user id.
     * @param id base id.
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }
}
