package com.msr.site;

import com.msr.siteuse.SiteUse;
import com.msr.siteuse.SiteUseService;
import com.msr.usetype.UseTypeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class SiteController
{

    // Sample Output messages
    private final static String SAMPLE_RESPONSE_BASE = "This is a sample response to test if SiteController is responding appropriately. ";
    static final String SAMPLE_PARAM_PROVIDED = SAMPLE_RESPONSE_BASE + "The request param you passed was: ";
    static final String NO_SAMPLE_PARAM_PROVIDED = SAMPLE_RESPONSE_BASE + "No request param was provided.";
    static final String SAMPLE_EXCEPTION_MESSAGE = SAMPLE_RESPONSE_BASE + "An expected error was thrown.";

    @Autowired
    SiteService service;

    @Autowired
    private SiteUseService useService;

    @Autowired
    private UseTypeService useTypeService;

    @RequestMapping(method = RequestMethod.GET, value = "/sites")
    public List<Site> getSitesForZipcode(@RequestParam(required = false) Optional<String> zipcode)
    {
        if (zipcode.isPresent())
        {
            return service.getSitesForZipcode(zipcode.get());
        }
        return service.getAllSites();

    }

    @RequestMapping(method = RequestMethod.POST, value = "/sites")
    public void addSite(@RequestBody Site site)
    {
        service.addSite(site);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/sites/{siteId}")
    public void deleteSite(@PathVariable int siteId)
    {
        service.deleteSite(siteId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/sites/{siteId}")
    public void updateSite(@PathVariable int siteId, @RequestBody Site site)
    {
        site.setId(siteId);
        service.updateSite(site);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sites/{siteId}")
    public SiteDetails getSite(@PathVariable int siteId)
    {
        Site site = service.getSite(siteId);

        List<SiteUse> siteUses = useService.getSiteUses(siteId);
        long totalSize = 0;
        HashMap<Integer, Long> sizeMap = new HashMap<>();
        for (SiteUse siteUse : siteUses)
        {
            long curSize = siteUse.getSizeSqft();
            totalSize = totalSize + siteUse.getSizeSqft();
            sizeMap.put(siteUse.getUseTypeId(), sizeMap.getOrDefault(siteUse.getUseTypeId(), 0L) + curSize);
        }

        SiteDetails siteDetails = new SiteDetails();
        siteDetails.setSite(site);

        if (!sizeMap.isEmpty())
        {
            Map.Entry<Integer, Long> maxUseType = sizeMap.entrySet().stream().max(Map.Entry.comparingByValue()).get();

            siteDetails.setTotalSize(totalSize);
            siteDetails.setUseType(useTypeService.getUseType(maxUseType.getKey()));
        }

        return siteDetails;
    }

    /**
     * Used simply to check if this controller is responding to requests.
     * Has no function other than echoing.
     *
     * @return A sample message based on the input parameters.
     * @throws RuntimeException Only when 'throwError' is true.
     */
    @ApiOperation("Returns a sample message for baseline controller testing.")
    @GetMapping("/sites/sample")
    public String getSampleResponse(@ApiParam("The message that will be echoed back to the user.")
                                    @RequestParam(required = false) final String message,
                                    @ApiParam("Forces this endpoint to throw a generic error.")
                                    @RequestParam(required = false) final boolean throwError) {
        String response;
        if (throwError) {
            throw new RuntimeException(SAMPLE_EXCEPTION_MESSAGE);
        } else if (!StringUtils.hasLength(message)) {
            response = NO_SAMPLE_PARAM_PROVIDED;
        } else {
            response = SAMPLE_PARAM_PROVIDED + message;
        }
        return response;
    }

}
