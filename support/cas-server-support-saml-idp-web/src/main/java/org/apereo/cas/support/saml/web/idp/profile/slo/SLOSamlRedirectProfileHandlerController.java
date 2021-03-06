package org.apereo.cas.support.saml.web.idp.profile.slo;

import org.apereo.cas.authentication.AuthenticationSystemSupport;
import org.apereo.cas.authentication.principal.Service;
import org.apereo.cas.authentication.principal.ServiceFactory;
import org.apereo.cas.authentication.principal.WebApplicationService;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.services.ServicesManager;
import org.apereo.cas.support.saml.OpenSamlConfigBean;
import org.apereo.cas.support.saml.SamlIdPConstants;
import org.apereo.cas.support.saml.services.idp.metadata.cache.SamlRegisteredServiceCachingMetadataResolver;
import org.apereo.cas.support.saml.web.idp.profile.builders.SamlProfileObjectBuilder;
import org.apereo.cas.support.saml.web.idp.profile.builders.enc.SamlIdPObjectSigner;
import org.apereo.cas.support.saml.web.idp.profile.builders.enc.validate.SamlObjectSignatureValidator;
import org.apereo.cas.support.saml.web.idp.profile.sso.request.SSOSamlHttpRequestExtractor;

import lombok.val;
import org.opensaml.messaging.decoder.servlet.BaseHttpServletRequestXMLMessageDecoder;
import org.opensaml.saml.saml2.core.Response;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * This is {@link SLOSamlRedirectProfileHandlerController}, responsible for
 * handling requests for SAML2 SLO Redirects.
 *
 * @author Misagh Moayyed
 * @since 5.1.0
 */
public class SLOSamlRedirectProfileHandlerController extends AbstractSamlSLOProfileHandlerController {
    private final Map<HttpMethod, BaseHttpServletRequestXMLMessageDecoder> samlMessageDecoders;

    public SLOSamlRedirectProfileHandlerController(final SamlIdPObjectSigner samlObjectSigner,
                                                   final AuthenticationSystemSupport authenticationSystemSupport,
                                                   final ServicesManager servicesManager,
                                                   final ServiceFactory<WebApplicationService> webApplicationServiceFactory,
                                                   final SamlRegisteredServiceCachingMetadataResolver samlRegisteredServiceCachingMetadataResolver,
                                                   final OpenSamlConfigBean configBean,
                                                   final SamlProfileObjectBuilder<Response> responseBuilder,
                                                   final CasConfigurationProperties casProperties,
                                                   final SamlObjectSignatureValidator samlObjectSignatureValidator,
                                                   final SSOSamlHttpRequestExtractor samlHttpRequestExtractor,
                                                   final Service callbackService,
                                                   final Map<HttpMethod, BaseHttpServletRequestXMLMessageDecoder> samlMessageDecoders) {
        super(samlObjectSigner,
            authenticationSystemSupport,
            servicesManager,
            webApplicationServiceFactory,
            samlRegisteredServiceCachingMetadataResolver,
            configBean,
            responseBuilder,
            casProperties,
            samlObjectSignatureValidator,
            samlHttpRequestExtractor,
            callbackService);
        this.samlMessageDecoders = samlMessageDecoders;
    }

    /**
     * Handle SLO Redirect profile request.
     *
     * @param response the response
     * @param request  the request
     * @throws Exception the exception
     */
    @GetMapping(path = SamlIdPConstants.ENDPOINT_SAML2_SLO_PROFILE_REDIRECT)
    protected void handleSaml2ProfileSLOPostRequest(final HttpServletResponse response,
                                                    final HttpServletRequest request) throws Exception {
        val decoder = this.samlMessageDecoders.get(HttpMethod.GET);
        handleSloProfileRequest(response, request, decoder);
    }
}
