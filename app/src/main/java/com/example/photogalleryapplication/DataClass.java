package com.example.photogalleryapplication;

/**
 * Represents a data item in the photo gallery application.
 */
public class DataClass {

    // Title of the data item
    private String dataTitle;
    
    // Description of the data item
    private String dataDesc;
    
    // Language related to the data item
    private String dataLang;
    
    // URL or path to the image associated with the data item
    private String dataImage;
    
    // Unique key for the data item, used for identification in the database
    private String key;

    /**
     * Gets the unique key for the data item.
     *
     * @return the unique key
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the unique key for the data item.
     *
     * @param key the unique key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Gets the title of the data item.
     *
     * @return the title of the data item
     */
    public String getDataTitle() {
        return dataTitle;
    }

    /**
     * Gets the description of the data item.
     *
     * @return the description of the data item
     */
    public String getDataDesc() {
        return dataDesc;
    }

    /**
     * Gets the language related to the data item.
     *
     * @return the language of the data item
     */
    public String getDataLang() {
        return dataLang;
    }

    /**
     * Gets the image URL or path of the data item.
     *
     * @return the image URL or path
     */
    public String getDataImage() {
        return dataImage;
    }

    /**
     * Constructs a new DataClass with the specified values.
     *
     * @param dataTitle the title of the data item
     * @param dataDesc the description of the data item
     * @param dataLang the language related to the data item
     * @param dataImage the image URL or path of the data item
     */
    public DataClass(String dataTitle, String dataDesc, String dataLang, String dataImage) {
        this.dataTitle = dataTitle;
        this.dataDesc = dataDesc;
        this.dataLang = dataLang;
        this.dataImage = dataImage;
    }

    /**
     * Default constructor for DataClass.
     * Required for Firebase deserialization.
     */
    public DataClass() {
    }
}
