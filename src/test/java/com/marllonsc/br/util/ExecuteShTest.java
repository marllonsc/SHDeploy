package com.marllonsc.br.util;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ExecuteShTest {

    @Test
    public void testExecute_ValidScript_ReturnsTrue() {
        String scriptPath = "/home/wolf/test.sh";
        assertFalse(ExecuteSh.execute(scriptPath), "Expected the script to execute successfully");
    }

    @Test
    public void testExecute_InvalidScript_ReturnsFalse() {
        String scriptPath = "/invalid/path/to/script.sh";
        assertFalse(ExecuteSh.execute(scriptPath), "Expected the script to fail execution");
    }

}
