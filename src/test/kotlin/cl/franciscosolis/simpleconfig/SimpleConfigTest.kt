package cl.franciscosolis.simpleconfig

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File

internal class SimpleConfigTest {

    private val config = SimpleConfig(File("settings.cfg"))

    @BeforeEach
    internal fun setUp() {
        config.add("test.key", "testValue")
    }

    @Test
    internal fun hasKeyTest() {
        assertTrue(config.has("test.key"))
    }

    @Test
    internal fun getValueTest() {
        val data = config.get("test.key")
        assertEquals("testValue", data)
    }
}