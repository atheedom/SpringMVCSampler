package com.springmvcsampler.signup;

import com.springmvcsampler.config.WebAppConfigurationAware;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class SignupControllerTest extends WebAppConfigurationAware {
    @Test
    @Ignore
    public void displaysSignupForm() throws Exception {
        mockMvc.perform(get("/createAccount"))
                .andExpect(model().attributeExists("accountCreateForm"))
                .andExpect(view().name("createAccount/createAccount"))
                .andExpect(content().string(
                        allOf(
                                containsString("<title>Signup</title>"),
                                containsString("<legend>Please Sign Up</legend>")
                        ))
                );
    }
}