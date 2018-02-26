package de.bergwerklabs.framework.commons.spigot.timing;

import de.bergwerklabs.framework.commons.misc.Triplet;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class TaskQueue {

    private final List<Triplet<Long, Runnable, Boolean>> taskDefinitions = new ArrayList<>();

    private boolean running = false;
    private boolean stopping = false;

    TaskQueue(List<Triplet<Long, Runnable, Boolean>> taskDefinitions) {
        this.taskDefinitions.addAll(taskDefinitions);
    }

    public void run(Plugin plugin) {
        if (isBusy()) return;

        running = true;
        stopping = false;
        runTask(plugin, 0);
    }

    private void runTask(Plugin plugin, int index) {
        if (index >= taskDefinitions.size()) {
            cancel();
        }

        if (stopping) {
            stopping = false;
            return;
        }

        Triplet<Long, Runnable, Boolean> entry = taskDefinitions.get(index);

        Runnable task = entry.getValue2();
        boolean sync = entry.getValue3();
        long delay = entry.getValue1();

        Runnable runnable = () -> {
            task.run();
            runTask(plugin, index + 1);
        };

        if (sync) {
            Bukkit.getScheduler().runTaskLater(plugin, runnable, delay);
        } else {
            Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, runnable, delay);
        }
    }

    public void cancel() {
        if (stopping || !running) return;
        stopping = true;
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isStopping() {
        return stopping;
    }

    public boolean isBusy() {
        return isRunning() || isStopping();
    }
}
