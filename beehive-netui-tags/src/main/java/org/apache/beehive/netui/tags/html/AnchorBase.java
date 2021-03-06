/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * $Header:$
 */
package org.apache.beehive.netui.tags.html;

import org.apache.beehive.netui.pageflow.scoping.ScopedServletUtils;
import org.apache.beehive.netui.tags.ByRef;
import org.apache.beehive.netui.tags.HtmlUtils;
import org.apache.beehive.netui.tags.internal.PageFlowTagUtils;
import org.apache.beehive.netui.tags.javascript.CoreScriptFeature;
import org.apache.beehive.netui.tags.javascript.IScriptReporter;
import org.apache.beehive.netui.tags.javascript.ScriptRequestState;
import org.apache.beehive.netui.tags.rendering.*;
import org.apache.beehive.netui.util.Bundle;
import org.apache.beehive.netui.util.ParamHelper;
import org.apache.beehive.netui.util.internal.InternalStringBuilder;
import org.apache.beehive.netui.util.logging.Logger;
import org.apache.struts.taglib.html.Constants;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * This is the base class that provides most of the features necessary to create an anchor and an area. The Anchor
 * and Area tags are created as subclasses of this tag.  The Area tag is really a subset of the features, so certain
 * attributes are not defined here, even though the backing fields are defined here and the utility code knows them.
 * This may not be the best OO design, but the design is optimized for performance of rendering anchor elements.
 */
abstract public class AnchorBase extends HtmlBaseTag
        implements IUrlParams, IHasPopupSupport
{
    protected static final String REQUIRED_ATTR = "href, action, linkName, clientAction";
    private static final Logger logger = Logger.getInstance(AnchorBase.class);

    protected AnchorTag.State _state = new AnchorTag.State();
    protected String _linkName;     // name of the link
    protected String _clientAction; // The client action (javascript)

    private String _action;
    private String _href;
    private String _targetScope;                // target page flow scope; see comments on setTargetScope()
    private String _location;                   // anchor to be added to the end of the hyperlink.
    private Map _params;                        // Parameters
    private Form _form;                         // the nearest form
    private boolean _formSubmit = false;        // should the anchor submit an enclosing form?
    private PopupSupport _popupSupport = null;  // popup support, if the popup attribute is set to true
    private boolean _disableSecondClick = false;    // When clicked, this anchor will disable itself after being clicked.

    /**
     * Base support for the attribute tag.  This is overridden to prevent setting the <code>href</code>
     * attribute.
     * @param name  The name of the attribute.  This value may not be null or the empty string.
     * @param value The value of the attribute.  This may contain an expression.
     * @param facet The name of a facet to which the attribute will be applied.  This is optional.
     * @throws JspException A JspException may be thrown if there is an error setting the attribute.
     */
    public void setAttribute(String name, String value, String facet)
            throws JspException
    {
        if (name != null && name.equals(HREF)) {
            String s = Bundle.getString("Tags_AttributeMayNotBeSet", new Object[]{name});
            registerTagError(s, null);
        }
        super.setAttribute(name, value, facet);
    }

    /**
     * Sets <code>shape</code> attribute for the area.
     * @param shape the window target.
     * @jsptagref.attributedescription The shape.
     * @jsptagref.databindable false
     * @jsptagref.attributesyntaxvalue <i>string_shape</i>
     * @netui:attribute required="false"  rtexprvalue="true"
     * description="The shape."
     */
    public void setShape(String shape)
    {
        _state.registerAttribute(AbstractHtmlState.ATTR_GENERAL, SHAPE, shape);
    }

    /**
     * Sets <code>coords</code> attribute for the area.
     * @param coords the window target.
     * @jsptagref.attributedescription The coordinates.
     * @jsptagref.databindable false
     * @jsptagref.attributesyntaxvalue <i>string_coordinates</i>
     * @netui:attribute required="false"  rtexprvalue="true"
     * description="The coordinates."
     */
    public void setCoords(String coords)
    {
        _state.registerAttribute(AbstractHtmlState.ATTR_GENERAL, COORDS, coords);
    }

    /**
     * Set the name of the action for the Area.
     * @param action the name of the action to set for the Area.
     * @jsptagref.attributedescription The action method to invoke.  The action method must be in the Controller file
     * of the Page Flow directory.
     * @jsptagref.databindable false
     * @jsptagref.attributesyntaxvalue <i>string_action</i>
     * @netui:attribute required="false" rtexprvalue="true"
     * description="The action method to invoke.  The action method must be in the Controller file of the Page Flow directory."
     */
    public void setAction(String action)
            throws JspException
    {
        _action = setRequiredValueAttribute(action, "action");
    }

    /**
     * Sets the href of the Anchor. This attribute will accept the empty String as a legal value.
     * @param href the hyperlink URI for the Area.
     * @jsptagref.attributedescription The URL to go to; if the URL needs
     * parameters, use the parameter tag to get proper encoding of special
     * characters in the parameters.
     * @jsptagref.databindable false
     * @jsptagref.attributesyntaxvalue <i>string_href</i>
     * @netui:attribute required="false" rtexprvalue="true"
     * description="The URL to go to."
     * reftype="url"
     */
    public void setHref(String href)
            throws JspException
    {
        _href = href;
    }

    /**
     * Sets the anchor to be added to the end of the generated hyperlink.
     * @param location the name of the location anchor.
     * @jsptagref.attributedescription Location within the URI to visit.
     * @jsptagref.databindable false
     * @jsptagref.attributesyntaxvalue <i>string_location</i>
     * @netui:attribute required="false"  rtexprvalue="true"
     * description="Location within the URI to visit."
     */
    public void setLocation(String location)
    {
        _location = setNonEmptyValueAttribute(location);
    }

    /**
     * Set the target "scope" for the anchor's action.  Multiple active page flows may exist concurrently within named
     * scopes.  This attribute selects which named scope to use.  If omitted, the default scope is assumed.
     * @param targetScope the name of the target scope in which the associated action's page flow resides.
     * @jsptagref.attributedescription The target scope in which the associated action's page flow resides.
     * @jsptagref.databindable true
     * @jsptagref.attributesyntaxvalue <i>string_targetScope</i>
     * @netui:attribute required="false" rtexprvalue="true"
     * description="The target scope in which the associated action's page flow resides"
     */
    public void setTargetScope(String targetScope)
    {
        _targetScope = targetScope;
    }

    /**
     * Sets the formSubmit indicator.
     * @param formSubmit whether or not the enclosing Form should be submitted.
     * @jsptagref.attributedescription Boolean.  If <code>formSubmit</code> is set to true, and the &lt;netui:anchor> tag
     * is within a &lt;netui:form> tag,
     * then the form data will be submitted to the method named in the
     * &lt;netui:form> tag's <code>action</code> attribute.
     * @jsptagref.databindable false
     * @jsptagref.attributesyntaxvalue <i>boolean_formSubmit</i>
     * @netui:attribute required="false" rtexprvalue="true" type="boolean"
     * description="If formSubmit is set to true, and the <netui:anchor> tag
     * is within a <netui:form> tag,
     * then the form data will be submitted to the method named in the
     * <netui:form> tag's action attribute."
     */
    public void setFormSubmit(boolean formSubmit)
    {
        _formSubmit = formSubmit;
    }

    /**
     * Sets the popup indicator.
     * @param popup whether or not the anchor should open a popup window.
     * @jsptagref.attributedescription Boolean.  If <code>popup</code> is set to true,
     * the anchor will open a popup window.
     * @jsptagref.databindable true
     * @jsptagref.attributesyntaxvalue <i>boolean_popup</i>
     * @netui:attribute required="false" rtexprvalue="true" type="boolean"
     * description="If popup is set to true, the anchor will open a popup window.
     */
    public void setPopup(boolean popup)
    {
        _popupSupport = (popup ? new PopupSupport() : null);
    }


    /**
     * When true, this anchor will disable itself after being clicked.
     * @param disableSecondClick when true, this anchor will disable itself after being clicked.
     * @jsptagref.attributedescription Boolean.  If <code>disableSecondClick</code> is set to true,
     * the anchor will disable itself after being clicked.
     * @jsptagref.databindable true
     * @jsptagref.attributesyntaxvalue <i>boolean_disableSecondClick</i>
     * @netui:attribute required="false" rtexprvalue="true" type="boolean"
     * description="When true, this anchor will disable itself after being clicked."
     */
    public void setDisableSecondClick(boolean disableSecondClick)
    {
        _disableSecondClick = disableSecondClick;
    }


    /**
     * Sets the tabIndex of the rendered html tag.
     * @param tabindex the tab index.
     * @jsptagref.attributedescription The tabIndex of the rendered HTML tag.  This attribute determines the position of the
     * rendered HTML tag in the sequence of tags that the user may advance through by pressing the TAB key.
     * @jsptagref.databindable false
     * @jsptagref.attributesyntaxvalue <i>string_tabIndex</i>
     * @netui:attribute required="false"  rtexprvalue="true" type="int"
     * description="The tabIndex of the rendered HTML tag.  This attribute determines the position of the
     * rendered HTML tag in the sequence of tags that the user may advance through by pressing the TAB key."
     */
    public void setTabindex(int tabindex)
    {
        _state.registerAttribute(AbstractHtmlState.ATTR_GENERAL, TABINDEX, Integer.toString(tabindex));
    }


    /**
     * Sets the accessKey attribute value.  This should key value of the
     * keyboard navigation key.  It is recommended not to use the following
     * values because there are often used by browsers <code>A, C, E, F, G,
     * H, V, left arrow, and right arrow</code>.
     * @param accessKey the accessKey value.
     * @jsptagref.attributedescription The keyboard navigation key for the element.
     * The following values are not recommended because they
     * are often used by browsers: <code>A, C, E, F, G,
     * H, V, left arrow, and right arrow</code>
     * @jsptagref.databindable false
     * @jsptagref.attributesyntaxvalue <i>string_accessKey</i>
     * @netui:attribute required="false" rtexprvalue="true"  type="char"
     * description=" The keyboard navigation key for the element.
     * The following values are not recommended because they
     * are often used by browsers: A, C, E, F, G,
     * H, V, left arrow, and right arrow."
     */
    public void setAccessKey(char accessKey)
    {
        if (accessKey == 0x00)
            return;
        _state.registerAttribute(AbstractHtmlState.ATTR_GENERAL, ACCESSKEY, Character.toString(accessKey));
    }

    /**
     * Sets the onBlur javascript event.
     * @param onblur the onBlur event.
     * @jsptagref.attributedescription The onBlur JavaScript event.
     * @jsptagref.databindable false
     * @jsptagref.attributesyntaxvalue <i>string_onBlur</i>
     * @netui:attribute required="false" rtexprvalue="true"
     * description="The onBlur JavaScript event."
     */
    public void setOnBlur(String onblur)
    {
        _state.registerAttribute(AbstractHtmlState.ATTR_JAVASCRIPT, ONBLUR, onblur);
    }

    /**
     * Sets the onFocus javascript event.
     * @param onfocus the onFocus event.
     * @jsptagref.attributedescription The onFocus JavaScript event.
     * @jsptagref.databindable false
     * @jsptagref.attributesyntaxvalue <i>string_onFocus</i>
     * @netui:attribute required="false" rtexprvalue="true"
     * description="The onFocus JavaScript event."
     */
    public void setOnFocus(String onfocus)
    {
        _state.registerAttribute(AbstractHtmlState.ATTR_JAVASCRIPT, ONFOCUS, onfocus);
    }

    //**************************** Helper Routines  *********************************************

    /**
     * Adds a URL parameter to the generated hyperlink.
     * @param name  the name of the parameter to be added.
     * @param value the value of the parameter to be added (a String or String[]).
     * @param facet
     */
    public void addParameter(String name, Object value, String facet) throws JspException
    {
        assert(name != null) : "Parameter 'name' must not be null";

        if (_params == null) {
            _params = new HashMap();
        }
        ParamHelper.addParam(_params, name, value);
    }

    /**
     * This method will create the &lt;a> portion of an anchor.  It is called by subclasses, for example, the
     * <code>ImageAnchor</code> relies on this code to generate the  &lt;a>.
     * @param scriptRef a <code>ByRef&lt;String></code> that will contain any JavaScript that may need to be added
     *                  to the generated HTML response.
     * @return a boolean value indicating if an error occur creating the anchor.
     * @throws JspException
     */
    protected final boolean createAnchorBeginTag(HttpServletRequest request, ByRef scriptRef,
                                                 TagRenderingBase trb, AbstractRenderAppender writer, String requiredAttrs)
            throws JspException
    {
        int have = 0;
        String formAction = null;
        String idScript = null;

        if (_formSubmit)
            _form = getNearestForm();

        ScriptRequestState srs = ScriptRequestState.getScriptRequestState(request);

        // check the parameters that the user provided
        if (_href != null) have++;
        if (_action != null) have++;
        if (_clientAction != null) have++;
        if (_linkName != null) have++;

        String tagId = getTagId();

        // if only the _linkName or _tagId is set then we are creating the name attribute only.
        if (have == 0 && !_formSubmit && tagId != null) {
            return createNameAnchor(request, trb);
        }

        // if the anchor is submitting a consider this a submit level problem
        // set the action to the form action.
        if (_formSubmit) {
            formAction = getFormAction();
            if ((formAction != null) && (have == 0)) {
                have++;
            }
            if (_action == null) {
                _action = formAction;
                _location = getFormLocation();
            }
        }

        // if we have not specified a destination or we've specified too many
        // then we need to report an error.
        if (have == 0 || have > 1) {
            String s = Bundle.getString("Tags_Anchor_InvalidAnchorURI",
                    new Object[]{getTagName(), requiredAttrs});
            registerTagError(s, null);
            return false;
        }

        if (_linkName != null) {
            return createPageAnchor(request, trb);
        }

        if (_action != null) {
            // report that action is not an action
            if (!PageFlowTagUtils.isAction(request, _action)) {
                String s = null;
                if (_action.equals("")) {
                    s = Bundle.getString("Tags_NullBadAction", null);
                }
                else {
                    s = Bundle.getString("Tags_BadAction", _action);
                }
                registerTagError(s, null);
            }
            else {
                //
                // If the action we're submitting to is checking for double-submits, save a token in the session.
                // This will be written out as a param (below), and will be checked in PageFlowRequestProcessor.
                //
                String token = PageFlowTagUtils.getToken(request, _action);
                if (token != null) {
                    addParamInternal(Constants.TOKEN_KEY, token);
                }
            }
        }
        
        // we assume that tagId will over have override id if both
        // are defined.
        if (tagId != null) {
            _state.id = tagId;
            idScript = renderNameAndId(request, _state, null);
        }

        // Special case for name anchors
        if (_clientAction != null) {
            _state.href = "";
        }
        else {
            // Add the scope-ID parameter, if the targetScope attribute is present.
            if (_targetScope != null) {
                addParamInternal(ScopedServletUtils.SCOPE_ID_PARAM, _targetScope);
            }

            if (_popupSupport != null) {
                _popupSupport.addParams(this, request);
            }
            
            // Generate the opening anchor element
            String uri = null;
            try {
                if (_action != null) {
                    uri = PageFlowTagUtils.rewriteActionURL(pageContext, _action, _params, _location);
                }
                else if (_href != null) {
                    uri = PageFlowTagUtils.rewriteHrefURL(pageContext, _href, _params, _location);
                }
            }
            catch (URISyntaxException e) {
                // report the error...
                logger.error(Bundle.getString("Tags_URISyntaxException"));
                String s = Bundle.getString("Tags_Anchor_URLException",
                        new Object[]{e.getMessage()});
                registerTagError(s, e);
            }

            if (uri == null) {
                if (hasErrors())
                    return false;
            }
            else {
                HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
                _state.href = response.encodeURL(uri);
            }
        }

        // We need to combine the onclick features
        IScriptReporter sr = getScriptReporter();
        if (_clientAction != null && srs.isFeatureWritten(CoreScriptFeature.DYNAMIC_INIT)) {
            //@todo: we need to support onclick chaining here also...
            String action = HtmlUtils.escapeEscapes(_clientAction);
            String entry = ScriptRequestState.getString("netuiAction",
                    new Object[]{action});
            _state.registerAttribute(AbstractHtmlState.ATTR_JAVASCRIPT, ONCLICK, entry);
            // Jira 299
            //_state.onClick = entry;
        }

        // if the user overrides the onclick we will ignore this
        String onclick = _state.getAttribute(AbstractHtmlState.ATTR_JAVASCRIPT, ONCLICK);
        if (onclick == null) {
            if (_formSubmit && formAction != null) {
                String realFormName = getFormId();
                String key = (_disableSecondClick ? "anchorDisableAndSubmitFormAction" : "anchorFormSubmitAction");
                String entry = ScriptRequestState.getString(key, new Object[]{realFormName, _state.href});
                _state.registerAttribute(AbstractHtmlState.ATTR_JAVASCRIPT, ONCLICK, entry);
                // Jira 299
                //_state.onClick = ScriptRequestState.getString("anchorFormSubmitAction",
                //        new Object[]{realFormName, _state.href});
                if (_form != null)
                    _form.insureRealId();
            }
            else if (_disableSecondClick) {
                String entry = ScriptRequestState.getString("anchorDisableAction", null);
                _state.registerAttribute(AbstractHtmlState.ATTR_JAVASCRIPT, ONCLICK, entry);
            }
            else if (_popupSupport != null) {
                _state.registerAttribute(AbstractHtmlState.ATTR_JAVASCRIPT, ONCLICK, _popupSupport.getOnClick(request,_state.href));
                // Jira 299
                //_state.onClick = _popupSupport.getOnClick(_state.href);
            }
        }

        if (hasErrors())
            return false;

        trb.doStartTag(writer, _state);

        // Emit javascript if this anchor needs to sumbit the form or open a popup window
        if (_formSubmit && formAction != null || idScript != null || _popupSupport != null) {
            InternalStringBuilder script = new InternalStringBuilder(32);
            StringBuilderRenderAppender scriptWriter = new StringBuilderRenderAppender(script);

            if (_formSubmit && formAction != null)
                srs.writeFeature(sr, scriptWriter, CoreScriptFeature.ANCHOR_SUBMIT, true, false,
                        null);
            if (_popupSupport != null)
                _popupSupport.writeScript(request, srs, getScriptReporter(), scriptWriter);
            if (idScript != null)
                scriptWriter.append(idScript);
            scriptRef.setRef(script.toString());
        }

        // create the javaScript
        return true;
    }

    private void addParamInternal(String paramName, String paramVal)
    {
        if (_params == null) {
            _params = new HashMap();
        }
        _params.put(paramName, paramVal);
    }

    /**
     * @param req
     * @param trb
     * @return a boolean indicating if an error has occurred or not
     */
    private boolean createNameAnchor(ServletRequest req, TagRenderingBase trb)
    {
        assert(_state.id != null) : "tagId must not be nulll";

        WriteRenderAppender writer = new WriteRenderAppender(pageContext);

        // the tagId must be qualified and we need to output the lookup methods
        // @todo: need to output the Script?
        // @todo: I don't believe that this works.  The id is not encoded correctly
        _state.id = getIdForTagId(_state.id);
        String script = renderDefaultNameAndId((HttpServletRequest) req, _state, _state.id, null);

        //set the name so legacy browsers can support this.
        _state.name = _state.id;

        // output the tag.
        trb = TagRenderingBase.Factory.getRendering(TagRenderingBase.ANCHOR_TAG, req);
        trb.doStartTag(writer, _state);
        return !hasErrors();
    }

    /**
     * This method will output an anchor with a fragment identifier.  This should
     * be a valid ID within the document.  If the name begins with the "#" we will
     * not qualify the set link name.  If the name doesn't begin with #, then we
     * will qualify it into the current scope container.
     * @param req The servlet request.
     * @param trb The TagRenderer that will output the link
     * @return return a boolean indicating if an error occurred or not
     */
    private boolean createPageAnchor(ServletRequest req, TagRenderingBase trb)
    {
        // create the fragment identifier.  If the _linkName starts with
        // '#' then we treat it as if it was fully qualified.  Otherwise we
        // need to qualify it before we add the '#'.
        _linkName = _linkName.trim();
        if (_linkName.charAt(0) != '#') {
            _state.href = "#" + getIdForTagId(_linkName);
        }
        else {
            _state.href = _linkName;
        }

        // if the tagId was set then rewrite it and output it.
        if (_state.id != null) {
            //_state.id = getIdForTagId(_state.id);
            _state.name = _state.id;
            renderNameAndId((HttpServletRequest) req, _state,null);
            //renderDefaultNameAndId((HttpServletRequest) req, _state, _state.id, _state.id);
        }

        // write out the tag.
        WriteRenderAppender writer = new WriteRenderAppender(pageContext);
        trb = TagRenderingBase.Factory.getRendering(TagRenderingBase.ANCHOR_TAG, req);
        trb.doStartTag(writer, _state);
        return !hasErrors();
    }

    /**
     * Return the action attribute for the nearest form.
     * @return The action attribute of the enclosing form
     */
    private String getFormAction()
    {
        if (_form != null)
            return _form.getAction();
        return null;
    }

    private String getFormLocation()
    {
        if (_form != null)
            return _form.getLocation();
        return null;
    }

    /**
     * This will get the real name of the form.  This is set in the
     * id attribute.
     * @return The String real name of the containing form.
     */
    private String getFormId()
    {
        if (_form != null) {
            return _form.getRealFormId();
        }
        return null;
    }

    /**
     * Release any acquired resources.
     */
    protected void localRelease()
    {
        super.localRelease();

        _state.clear();
        _linkName = null;
        _clientAction = null;

        _action = null;
        _href = null;
        _targetScope = null;
        _location = null;
        _params = null;
        _form = null;
        _formSubmit = false;
        _popupSupport = null;
        _disableSecondClick = false;
    }

    public PopupSupport getPopupSupport()
    {
        return _popupSupport;
    }
}
