package cl.franciscosolis.simpleconfig

import java.io.*
import java.util.*

/**
 * Representation of a config file
 * @param file The file that contains the settings
 */
class SimpleConfig(val file: File) {
    
    private val props = Properties()
    var comments: String? = null

    init {
        if(!file.exists()) file.createNewFile()
        this.load()
    }

    /**
     * Load the settings from the file
     */
    fun load() {
        props.load(FileInputStream(file))
    }

    /**
     * Saves the settings to the file
     */
    fun save(){
        props.store(FileWriter(file), comments)
    }

    /**
     * Checks if the given key exists in the config
     * @param key the key to check
     * @return true if the config contains the given key, false otherwise
     */
    fun has(key: String): Boolean = keys().contains(key)

    /**
     * Saves the given value to the given key in the config file replacing any existing value
     * and instantly saves the config file
     *
     * @param key the key where to store the given value
     * @param value the value to store in the given key
     */
    fun set(key: String, value: String) {
        props.setProperty(key, value)
        save()
    }

    /**
     * Saves the given value to the given key in the config file but only if the value was not saved before

     * @param key the key where to store the given value
     * @param value the value to store in the given key
     */
    fun add(key: String, value: String){
        if(!has(key)){
            set(key, value)
        }
    }

    /**
     * Retrieve the value from the config using the given key
     *
     * @param key the key that contains the value to retrieve
     * @return the value saved on the given key
     */
    fun get(key: String): String = props.getProperty(key)

    /**
     * Retrieve the value from the config using the given key and
     * if the value doesn't exists it will be saved with the given
     * default value.
     *
     * @param key the key that contains the value to retrieve
     * @param def the default value to save and retrieve if there is no saved data in the given key
     * @return the value saved on the given key
     */
    fun get(key: String, def: String): String {
        add(key, def)
        return get(key)
    }

    /**
     * Retrieve a list with all the keys on the file
     * @return the list containing all the stored keys
     */
    fun keys(): List<String> = props.stringPropertyNames().toList()

    /**
     * Retrieve a list with all the values on the file
     * @return the list containing all the stored values
     */
    fun values(): List<String> = keys().map { get(it) }.toList()

    /**
     * Retrieve a map with all the keys and values on the file
     * @return the map containing all the keys associated with their values
     */
    fun entries(): Map<String, String> = keys().associateWith { get(it) }
}