package de.bergwerklabs.framework.party.client.api.event;

import java.util.UUID;

/**
 * Created by Yannic Rieger on 04.09.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class PlayerLeavePartyEvent extends AbstractPartyEvent {

    private UUID member;
    private boolean isOwner;

}
