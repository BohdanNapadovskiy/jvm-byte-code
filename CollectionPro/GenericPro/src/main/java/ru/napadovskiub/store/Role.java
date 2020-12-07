package ru.napadovskiub.store;

/**
 *Role class extends base.
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 03.07.2017
 */
public class Role extends Base {

    /**
     * role id.
     */
    private String id;

    /**
     * Method return role id.
     * @return
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     *Method set id.
     * @param id base id.
     */
    @Override
    public void setId(String id) {
        this.id = id;

    }
}
