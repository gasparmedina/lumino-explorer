package org.rif.lumino.explorer.scheduled;

import org.rif.lumino.explorer.managers.LuminoNodeManager;
import org.rif.lumino.explorer.models.documents.LuminoNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Component
public class AliveNodeCheckTask {


    private final String CRON_CHECK_NODE_ALIVE = "5 * * * * ?";

    @Autowired
    private LuminoNodeManager luminoNodeManager;

    @Value("${lumino.explorer.api.scheduled.task.alivenodecheck.delete.time.condition.in.minutes}")
    private Integer deleteTimeConditionInMinutes;

    private static final Logger logger = LoggerFactory.getLogger(AliveNodeCheckTask.class);

    @Scheduled(cron = CRON_CHECK_NODE_ALIVE)
    public void checkNodesAlive() {

        List<LuminoNode> luminoNodeList = luminoNodeManager.getAll();
        for (LuminoNode luminoNode : luminoNodeList) {
            if (luminoNode.getLastAliveSignal() != null) {
                LocalDateTime localDateTimeNow = LocalDateTime.now(Clock.systemUTC());
                Date nodeLocalDateTime = luminoNode.getLastAliveSignal();
                ZonedDateTime zonedDateTime = nodeLocalDateTime.toInstant().atZone(ZoneId.of("UTC"));
                LocalDateTime test = zonedDateTime.toLocalDateTime();

                long minutes = ChronoUnit.MINUTES.between(test, localDateTimeNow);
                if (minutes > deleteTimeConditionInMinutes) {
                    luminoNodeManager.deleteNodeById(luminoNode.getNodeAddress());
                    logger.info("The node with address: " +
                            luminoNode.getNodeAddress() +
                            "has been deleted because not exist alive signal on the last " +
                            deleteTimeConditionInMinutes + " minutes");
                }
            }
        }
    }

}
