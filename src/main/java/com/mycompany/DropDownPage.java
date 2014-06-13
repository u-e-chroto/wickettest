/*
 * Copyright 2014 yui_inoue.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mycompany;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.extensions.ajax.markup.html.AjaxLazyLoadPanel;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.model.Model;

/**
 *
 * @author yui_inoue
 */
public class DropDownPage extends WebPage {

    public DropDownPage() {

        List<ChoiceElement> years = Arrays.asList(
                new ChoiceElement("2010", "2010年"),
                new ChoiceElement("2011", "2011年"),
                new ChoiceElement("2012", "2012年"),
                new ChoiceElement("2013", "2013年"),
                new ChoiceElement("2014", "2014年"),
                new ChoiceElement("2015", "2015年"));
        final DropDownChoice<ChoiceElement> choice = new DropDownChoice<ChoiceElement>(
                "choice", new Model<ChoiceElement>(), years,
                new IChoiceRenderer<ChoiceElement>() {
                    public Object getDisplayValue(ChoiceElement object) {
                        return object.getName();
                    }

                    public String getIdValue(ChoiceElement object, int index) {
                        return object.getId();
                    }
                });
        final WebMarkupContainer dataPanel = new WebMarkupContainer("dataPanel");
        dataPanel.setOutputMarkupId(true);
        dataPanel.add(new EmptyPanel("dataList"));
        choice.add(new OnChangeAjaxBehavior() {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                dataPanel.remove("dataList");
                dataPanel.add(new AjaxLazyLoadPanel("dataList") {

                    @Override
                    public Component getLazyLoadComponent(String markupId) {
                        return new PagingNavigatorPanel(markupId, 
                                choice.getModelObject().getName(), choice.getValue());
                    }
                });
                target.add(dataPanel);
            }
        });
        add(dataPanel);
        add(choice);
    }
}

class ChoiceElement implements Serializable {

    private String id;
    private String name;

    public ChoiceElement(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
