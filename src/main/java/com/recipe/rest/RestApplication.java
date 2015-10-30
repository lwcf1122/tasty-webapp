package com.recipe.rest;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by flao on 5/18/15.
 */
public class RestApplication extends ResourceConfig {

    /**
     * Register JAX-RS application components.
     */
    public RestApplication() {
        packages("com.recipe.rest");
    }

}
