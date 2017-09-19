package de.bergwerklabs.framework.gameservice.statistic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yannic Rieger on 19.09.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class StatisticsContext {

    public List<Statistic> getStatistics() { return this.statistics; }

    private List<Statistic> statistics = new ArrayList<>();

    void addStatistic(Statistic statistic) {
        this.statistics.add(statistic);
    }
}
