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

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.time.Duration;

/**
 *
 * @author yui_inoue
 */
public class CantDosButtonPage extends WebPage {

    public CantDosButtonPage() {
        final WebMarkupContainer activeComponent = new WebMarkupContainer("activeComponent");
        activeComponent.setOutputMarkupId(true);
        add(activeComponent);

        final String nowTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
        final Label timerLabel = new Label("timer", nowTime);
        timerLabel.setOutputMarkupId(true);
        activeComponent.add(timerLabel);

        activeComponent.add(new AjaxCanNotRollLinkPanel("linkComponent",
                "�N���b�N������X�V", activeComponent, Duration.seconds(10)) {
            @Override
            protected void onClickEvent(AjaxRequestTarget target) {
                super.onClickEvent(target);
                timerLabel.setDefaultModel(new Model<String>(
                        new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())));
            }
        });
    }
}
