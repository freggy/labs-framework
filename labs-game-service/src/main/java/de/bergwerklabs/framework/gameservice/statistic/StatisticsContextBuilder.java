package de.bergwerklabs.framework.gameservice.statistic;

/**
 * Created by Yannic Rieger on 19.09.2017.
 * <p>
 * Builds the {@link StatisticsContext}.
 *
 * @author Yannic Rieger
 */
public class StatisticsContextBuilder {

    private StatisticsContext context = new StatisticsContext();

    /**
     * Adds a new {@link Statistic} to the context.
     *
     * @param statistic {@link Statistic} to add.
     * @return the {@link StatisticsContextBuilder} for method chaining
     */
    public StatisticsContextBuilder newStatistic(Statistic statistic) {
        this.context.addStatistic(statistic);
        return this;
    }

    /**
     * Returns a {@link StatisticsContext}.
     */
    public StatisticsContext build() {
        return this.context;
    }
}
