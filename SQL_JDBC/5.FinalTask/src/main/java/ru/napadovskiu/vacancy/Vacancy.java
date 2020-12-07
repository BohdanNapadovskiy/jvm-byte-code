package ru.napadovskiu.vacancy;

import java.sql.Timestamp;


/**
 * Package of final task SQL_JDBC.
 * Class vacancy
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 28.02.2018
 */
public class Vacancy {

    /**
     *  date vacancy.
     */
    private Timestamp vac_Date;

    /**
     * link vacancy.
     */
    private String vac_Link;

    /**
     * vacancy author.
     */
    private String vac_author;

    /**
     * description vacancy.
     */
    private String vac_Description;

     /**
     * Constructor for class.
     * @param vac_Date
     * @param vac_Link
     * @param vac_author
     * @param vac_Description
     */
    public Vacancy(Timestamp vac_Date, String vac_Link, String vac_author, String vac_Description) {
        this.vac_Date = vac_Date;
        this.vac_Link = vac_Link;
        this.vac_author = vac_author;
        this.vac_Description = vac_Description;
    }

    /**
     *
     * @return
     */
    public Timestamp getVac_Date() {
        return vac_Date;
    }

    /**
     *
     * @return
     */
    public String getVac_Link() {
        return vac_Link;
    }

    /**
     *
     * @return
     */
    public String getVac_author() {
        return vac_author;
    }

    /**
     *
     * @return
     */
    public String getVac_Description() {
        return vac_Description;
    }

}
