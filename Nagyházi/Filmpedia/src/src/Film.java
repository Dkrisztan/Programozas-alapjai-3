package src;

import java.io.Serializable;

/**
 * Class that contains the data of a Film
 */
public class Film implements Serializable {
    private String title;               //* Title of a film
    private Integer publication_year;   //* Publication year of a film
    private Integer length;             //* Length of a film in minutes
    private String producer;            //* The producer of a Film
    private String description;         //* Description of a film
    private AgeRating ageRating;        //* Age Rating of a film
    private Categories category;        //* Category of a film

    /**
     *  Constructor of a Film
     * @param title
     * @param publication_year
     * @param length
     * @param producer
     * @param description
     * @param ageRating
     * @param category
     */
    public Film(String title, Integer publication_year, Integer length, String producer, String description, AgeRating ageRating, Categories category) {
        this.title = title;
        this.publication_year = publication_year;
        this.length = length;
        this.producer = producer;
        this.description = description;
        this.ageRating = ageRating;
        this.category = category;
    }

    //! Getters
    public String getTitle() {
        return title;
    }

    public Integer getPublication_year() {
        return publication_year;
    }

    public Integer getLength() {
        return length;
    }

    public String getProducer() {
        return producer;
    }

    public String getDescription() {
        return description;
    }

    public AgeRating getAgeRating() {
        return ageRating;
    }

    public Categories getCategory() {
        return category;
    }

    //! Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublication_year(Integer publication_year) {
        this.publication_year = publication_year;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAgeRating(AgeRating ageRating) {
        this.ageRating = ageRating;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }
}
