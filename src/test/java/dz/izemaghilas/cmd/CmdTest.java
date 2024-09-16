package dz.izemaghilas.cmd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

class CmdTest {
//    private static final Properties properties = new Properties();
//
//    @BeforeAll
//    static void setupBeforeAll() throws IOException {
//        try (InputStream propertiesResource = CmdTest.class.getClassLoader().getResourceAsStream("application.properties")) {
//            if (Objects.isNull(propertiesResource)) {
//                throw new IllegalArgumentException("properties file not found");
//            }
//            properties.load(propertiesResource);
//            Files.createFile(Paths.get(properties.getProperty("pwfile")));
//        }
//    }
//
//    @AfterAll
//    static void setupAfterAll() throws IOException {
//        Files.deleteIfExists(Paths.get(properties.getProperty("pwfile")));
//    }

    @Test
    void testEmptyCmd() {
        assertThrowsExactly(EmptyCmdException.class, () -> {
            Cmd.process(new String[]{});
        });
    }

    @Test
    void testUnknownCmd() {
        assertThrowsExactly(UnknownCmdException.class, () -> {
            Cmd.process(new String[]{"test"});
        });
    }

    // get cmd tests
    @Test
    void testGetCmdNoOptions() {
        assertThrowsExactly(MissingCmdOptionException.class, () -> {
            Cmd.process(new String[]{"get"});
        });
    }

    @Test
    void testGetCmdUnrecognizedOption() {
        assertThrowsExactly(UnknownCmdOptionException.class, () -> {
            Cmd.process(new String[]{"get", "-e", "izem@gmail.com", "-test"});
        });
    }

    @Test
    void testGetCmdDuplicatedOption() {
        assertThrowsExactly(DuplicateCmdOptionException.class, () -> {
            Cmd.process(new String[]{"get", "-e", "izem@gmail.com", "-e", "izem@gmail.com"});
        });

        assertThrowsExactly(DuplicateCmdOptionException.class, () -> {
            Cmd.process(new String[]{"get", "-e", "izem@gmail.com", "-wu", "google.com", "-e", "izem@gmail.com"});
        });
    }

    @Test
    void testGetCmdNoOptionValue() {
        assertThrowsExactly(NoCmdOptionValue.class, () -> {
            Cmd.process(new String[]{"get", "-e"});
        });

        assertThrowsExactly(NoCmdOptionValue.class, () -> {
            Cmd.process(new String[]{"get", "-e", "izem@gmail.com", "-wu"});
        });
    }

    // store cmd tests
    @Test
    void testStoreCmdNoOptions() {
        assertThrowsExactly(MissingCmdOptionException.class, () -> {
            Cmd.process(new String[]{"store"});
        });
    }

    @Test
    void testStoreCmdUnrecognizedOption() {
        assertThrowsExactly(UnknownCmdOptionException.class, () -> {
            Cmd.process(new String[]{"store", "-e", "izem@gmail.com", "-test"});
        });
    }

    @Test
    void testStoreCmdDuplicatedOption() {
        assertThrowsExactly(DuplicateCmdOptionException.class, () -> {
            Cmd.process(new String[]{"store", "-p", "password", "-e", "izem@gmail.com", "-wu", "google.com", "-p", "password"});
        });

        assertThrowsExactly(DuplicateCmdOptionException.class, () -> {
            Cmd.process(new String[]{"store", "-p", "password", "-e", "izem@gmail.com", "-wu", "google.com", "-e", "aghilas@gmail.com"});
        });

        assertThrowsExactly(DuplicateCmdOptionException.class, () -> {
            Cmd.process(new String[]{"store", "-p", "password", "-e", "izem@gmail.com", "-wu", "google.com", "-wu", "github.com"});
        });
    }

    @Test
    void testStoreCmdNoOptionValue() {
        assertThrowsExactly(NoCmdOptionValue.class, () -> {
            Cmd.process(new String[]{"store", "-p", "-e", "izem@gmail.com", "-wu", "google.com"});
        });

        assertThrowsExactly(NoCmdOptionValue.class, () -> {
            Cmd.process(new String[]{"store", "-p", "password", "-e", "-wu", "google.com"});
        });

        assertThrowsExactly(NoCmdOptionValue.class, () -> {
            Cmd.process(new String[]{"store", "-p", "password", "-e", "izem@gmail.com", "-wu"});
        });
    }
}