package com.springmvcsampler.config;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;


import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public abstract class WebSecurityConfigurationAware extends WebAppConfigurationAware {

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Before
    public void before() {
        this.mockMvc = webAppContextSetup(this.wac)
                .addFilters(this.springSecurityFilterChain).build();
    }
}
