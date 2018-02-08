package de.bergwerklabs.framework.bedrock.api.event.voting;

import de.bergwerklabs.framework.bedrock.api.voting.MapEntry;
import de.bergwerklabs.framework.commons.spigot.general.LabsEvent;

import java.util.List;

/**
 * Created by Yannic Rieger on 08.02.2018.
 * <p>
 *
 * @author Yannic Rieger
 */
public class VotingStoppedEvent extends LabsEvent {

    private List<MapEntry> availableMaps;
    private MapEntry pickedMap;

    public VotingStoppedEvent(List<MapEntry> availableMaps, MapEntry picked) {
        this.availableMaps = availableMaps;
        this.pickedMap = picked;
    }

    public List<MapEntry> getAvailableMaps() {
        return availableMaps;
    }

    public MapEntry getPickedMap() {
        return pickedMap;
    }
}
