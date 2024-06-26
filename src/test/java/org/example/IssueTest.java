package org.example;

import org.example.models.Issue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class IssueTest {

    private Issue issue;

    @BeforeEach
    void setUp() {
        issue = new Issue("Book", "Test Issue", 100, 10, "A4", "normal", false);
    }

    @Test
    void testGetPageSize() {
        assertEquals("A4", issue.getPageSize());
    }

    @Test
    void testGetPaperType() {
        assertEquals("normal", issue.getPaperType());
    }

    @Test
    void testGetTitle() {
        assertEquals("Test Issue", issue.getTitle());
    }

    @Test
    void testGetAmount() {
        assertEquals(100, issue.getAmount());
    }

    @Test
    void testGetPages() {
        assertEquals(10, issue.getPages());
    }

    @Test
    void testGetIsColored() {
        assertFalse(issue.getIsColored());
    }

    @Test
    void testInvalidPageSize() {
        assertThrows(Error.class, () -> new Issue("Book", "Invalid Page Size", 100, 10, "A10", "normal", false));
    }

    @Test
    void testInvalidPaperType() {
        assertThrows(Error.class, () -> new Issue("Book", "Invalid Paper Type", 100, 10, "A4", "ultra", false));
    }

    @Test
    void testGetPageSizeTable() {
        List<String> pageSizeTable = issue.getPageSizeTable();
        assertEquals(List.of("A1", "A2", "A3", "A4", "A5"), pageSizeTable);
    }

    @Test
    void testGetPaperTypeTable() {
        List<String> paperTypeTable = issue.getPaperTypeTable();
        assertEquals(List.of("normal", "glossy", "newspaper"), paperTypeTable);
    }
}
