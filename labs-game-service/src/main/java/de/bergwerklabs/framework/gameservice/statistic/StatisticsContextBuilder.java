package de.bergwerklabs.framework.gameservice.statistic;

/**
 * Created by Yannic Rieger on 19.09.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class StatisticsContextBuilder {

    private StatisticsContext context = new StatisticsContext();

    public StatisticsContextBuilder newStatistic(Statistic statistic) {
        this.context.addStatistic(statistic);
        return this;
    }

    public StatisticsContext build() {
        return this.context;
    }
}
