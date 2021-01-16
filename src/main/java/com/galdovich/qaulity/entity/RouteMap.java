package com.galdovich.qaulity.entity;

import java.io.Serializable;

/**
 * The class represents route map entity.
 * <p>
 * It is class containing the the name of the operation and its serial number.
 * Route map is an integral element of production Map.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class RouteMap implements Comparable<RouteMap>, Serializable {
    /**
     * Serial number of the process.
     */
    private Integer queue;
    /**
     * Name of process.
     */
    private String process;

    /**
     * Instantiates a new production.
     */
    public RouteMap() {
    }

    /**
     * Instantiates a new production.
     *
     * @param queue   serial number of the process
     * @param process name of process
     */
    public RouteMap(int queue, String process) {
        this.queue = queue;
        this.process = process;
    }

    /**
     * Gets queue.
     *
     * @return the queue
     */
    public int getQueue() {
        return queue;
    }

    /**
     * Sets queue.
     *
     * @param queue the queue
     */
    public void setQueue(int queue) {
        this.queue = queue;
    }

    /**
     * Gets process.
     *
     * @return the process
     */
    public String getProcess() {
        return process;
    }

    /**
     * Sets process.
     *
     * @param process the process
     */
    public void setProcess(String process) {
        this.process = process;
    }

    @Override
    public int compareTo(RouteMap o) {
        return this.queue.compareTo(o.queue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RouteMap routeMap = (RouteMap) o;

        if (queue != routeMap.queue) return false;
        return process != null ? process.equals(routeMap.process) : routeMap.process == null;
    }

    @Override
    public int hashCode() {
        int result = queue;
        result = 31 * result + (process != null ? process.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RouteMap{");
        sb.append("queue=").append(queue);
        sb.append(", process='").append(process).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
