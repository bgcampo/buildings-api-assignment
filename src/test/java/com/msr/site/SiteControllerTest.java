package com.msr.site;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Intended as a starting point for unit testing SiteController
 */
class SiteControllerTest
{

    private final SiteController siteController = new SiteController();

    @Test
    void testSampleResponse_NullMessageParameter() {
        String response = siteController.getSampleResponse(null, false);
        assertEquals(siteController.NO_SAMPLE_PARAM_PROVIDED, response);
    }

    @Test
    void testSampleResponse_EmptyMessageParameter() {
        String response = siteController.getSampleResponse("", false);
        assertEquals(siteController.NO_SAMPLE_PARAM_PROVIDED, response);
    }

    @Test
    void testSampleResponse_MessageParameterProvided() {
        String expectedString = "This is the expected output parameter.";
        String response = siteController.getSampleResponse(expectedString, false);
        assertEquals(siteController.SAMPLE_PARAM_PROVIDED + expectedString, response);
    }

    @Test
    void testSampleResponse_ThrowsWhenThrowErrorIsTrue() {
        assertThrows(RuntimeException.class, () -> siteController.getSampleResponse(null, true));
    }

    /**
     * Intended to test the controller's get all sites functionality.
     */
    @Test
    void testGetAllSites() {
        // TODO: Flesh out test based on implementation
        // List<Site> sites = buildingsController.getAllSites();
        // assertEquals(expectedListSize, sites.getLength());
    }

    /**
     * Intended to test the controller's get site by ID functionality.
     */
    @Test
    void testGetSiteById() {
        // TODO: Flesh out test based on implementation
        // Site site = buildingsController.getSite(siteId);
        // assertEquals(expectedSite, site);
    }
}