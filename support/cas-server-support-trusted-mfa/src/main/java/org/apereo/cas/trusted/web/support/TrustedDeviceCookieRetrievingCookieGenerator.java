package org.apereo.cas.trusted.web.support;

import org.apereo.cas.web.support.gen.CookieGenerationContext;
import org.apereo.cas.web.support.gen.CookieRetrievingCookieGenerator;
import org.apereo.cas.web.support.mgmr.CookieValueManager;

/**
 * {@link CookieRetrievingCookieGenerator} for trusted device cookies.
 *
 * @author Daniel Frett
 * @since 5.3.0
 */
public class TrustedDeviceCookieRetrievingCookieGenerator extends CookieRetrievingCookieGenerator {
    private static final long serialVersionUID = 3555244208199798618L;

    public TrustedDeviceCookieRetrievingCookieGenerator(final CookieGenerationContext context) {
        super(context);
    }

    public TrustedDeviceCookieRetrievingCookieGenerator(final CookieGenerationContext context,
                                                        final CookieValueManager casCookieValueManager) {
        super(context, casCookieValueManager);
    }
}
