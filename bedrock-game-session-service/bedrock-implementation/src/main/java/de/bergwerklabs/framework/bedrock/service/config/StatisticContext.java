package de.bergwerklabs.framework.bedrock.service.config;

import java.util.Set;

/**
 * Created by Yannic Rieger on 13.11.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class StatisticContext {

    public Set<String> getDataValues() {
        return dataValues;
    }

    public String getDataGroup() {
        return dataGroup;
    }

    private String dataGroup;
    private Set<String> dataValues;

    public StatisticContext(String dataGroup, Set<String> dataValues) {
        this.dataGroup = dataGroup;
        this.dataValues = dataValues;
    }
}
