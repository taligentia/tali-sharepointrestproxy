package com.taligentia.sharepointrestproxy.proxy;

import com.taligentia.base.dropwizard.BaseBuilder;
import com.taligentia.base.dropwizard.BaseBuilderImpl;
import io.dropwizard.setup.Environment;

import java.io.IOException;

public class ProxyManagerBuilder extends BaseBuilderImpl implements BaseBuilder {

    private ProxyConfiguration proxyConfiguration;

    public ProxyManagerBuilder() {
        super();
    }

    public ProxyManagerBuilder(Environment environment) {
        super(environment,"");
    }

    public ProxyManagerBuilder using(ProxyConfiguration proxyConfiguration) {
        this.proxyConfiguration = proxyConfiguration;
        return this;
    }

    @Override
    public ProxyManager build(String s) {
        ProxyManager manager;
        try {
            manager = new ProxyManager(proxyConfiguration);
        } catch (IllegalArgumentException | ClassNotFoundException | IOException e) {
            throw new IllegalArgumentException(e);
        }
        System.setProperty("java.security.auth.login.config", proxyConfiguration.getJavaSecurityAuthLoginConfig());
        System.setProperty("java.security.krb5.conf", proxyConfiguration.getJavaSecurityKrb5Conf());
        System.setProperty("sun.security.krb5.debug", proxyConfiguration.getSunSecurityKrb5Debug());
        System.setProperty("sun.security.jgss.debug", proxyConfiguration.getSunSecurityJgssDebug());
        System.setProperty("sun.security.spnego.debug", proxyConfiguration.getSunSecuritySpnegoDebug());
        System.setProperty("javax.security.auth.useSubjectCredsOnly", proxyConfiguration.getJavaxSecurityAuthUseSubjectCredsOnly());
        return manager;
    }
}
