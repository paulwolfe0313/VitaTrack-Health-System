package vitatrack.controller;

import vitatrack.service.ChartingService;

public class ChartingController {

    ChartingService chartingService;

    public ChartingController(ChartingService chartingService) {
        this.chartingService = chartingService;
    }
}
