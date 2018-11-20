package com.github.mkopylec.charon.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

import static org.apache.commons.lang3.builder.ToStringStyle.NO_CLASS_NAME_STYLE;

public class MappingProperties {

    /**
     * Name of the mapping.
     */
    private String name;
    /**
     * Path for mapping incoming HTTP requests URIs.
     */
    private String path = "/";
    /**
     * Path to rewrite the original path from incoming HTTP requests URIs.
     */
    private String rewrittenPath;
    /**
     * List of destination hosts where HTTP requests will be forwarded.
     */
    private List<String> destinations = new ArrayList<>();
    /**
     * Flag for enabling and disabling asynchronous HTTP request forwarding.
     */
    private boolean asynchronous = false;
    /**
     * Flag for enabling and disabling mapped path stripping from forwarded request URI.
     */
    private boolean stripPath = true;
    /**
     * Flag for enabling and disabling retrying of HTTP requests forwarding.
     */
    private boolean retryable = false;
    /**
     * Properties responsible for timeouts while forwarding HTTP requests.
     */
    private TimeoutProperties timeout = new TimeoutProperties();

    /**
     * Custom properties placeholder.
     */
    private Map<String, Object> customConfiguration = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRewrittenPath() {
        return rewrittenPath;
    }

    public void setRewrittenPath(String rewrittenPath) {
        this.rewrittenPath = rewrittenPath;
    }

    public List<String> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<String> destinations) {
        this.destinations = destinations;
    }

    public boolean isAsynchronous() {
        return asynchronous;
    }

    public void setAsynchronous(boolean asynchronous) {
        this.asynchronous = asynchronous;
    }

    public boolean isStripPath() {
        return stripPath;
    }

    public void setStripPath(boolean stripPath) {
        this.stripPath = stripPath;
    }

    public boolean isRetryable() {
        return retryable;
    }

    public void setRetryable(boolean retryable) {
        this.retryable = retryable;
    }

    public TimeoutProperties getTimeout() {
        return timeout;
    }

    public void setTimeout(TimeoutProperties timeout) {
        this.timeout = timeout;
    }

    public Map<String, Object> getCustomConfiguration() {
        return customConfiguration;
    }

    public void setCustomConfiguration(Map<String, Object> customConfiguration) {
        this.customConfiguration = customConfiguration;
    }

    public MappingProperties copy() {
        MappingProperties clone = new MappingProperties();
        clone.setName(name);
        clone.setPath(path);
        clone.setRewrittenPath(rewrittenPath);
        clone.setDestinations(destinations == null ? null : new ArrayList<>(destinations));
        clone.setAsynchronous(asynchronous);
        clone.setStripPath(stripPath);
        clone.setRetryable(retryable);
        clone.setTimeout(timeout);
        clone.setCustomConfiguration(customConfiguration == null ? null : new HashMap<>(customConfiguration));
        return clone;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, NO_CLASS_NAME_STYLE)
                .append("name", name)
                .append("path", path)
                .append("rewrittenPath", rewrittenPath)
                .append("destinations", destinations)
                .append("asynchronous", asynchronous)
                .append("stripPath", stripPath)
                .append("retryable", retryable)
                .append("timeout", timeout)
                .append("customConfiguration", customConfiguration)
                .toString();
    }

    public static class TimeoutProperties {

        /**
         * Connect timeout for HTTP requests forwarding.
         */
        private int connect = 200;
        /**
         * Read timeout for HTTP requests forwarding.
         */
        private int read = 2000;

        public int getConnect() {
            return connect;
        }

        public void setConnect(int connect) {
            this.connect = connect;
        }

        public int getRead() {
            return read;
        }

        public void setRead(int read) {
            this.read = read;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this, NO_CLASS_NAME_STYLE)
                    .append("connect", connect)
                    .append("read", read)
                    .toString();
        }
    }
}
