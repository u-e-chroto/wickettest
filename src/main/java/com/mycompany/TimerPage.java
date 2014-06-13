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
import org.apache.wicket.ajax.AbstractAjaxTimerBehavior;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.time.Duration;

/**
 *
 * @author yui_inoue
 */
public class TimerPage extends WebPage {

    public TimerPage() {
//        final Label timerLabel = new Label("timer",
//                new AbstractReadOnlyModel<String>() {
//                    @Override
//                    public String getObject() {
//                        SimpleDateFormat formatter =
//                                new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//                        return formatter.format(new Date());
//                    }
//                });
//        timerLabel.setOutputMarkupId(true);
//        add(timerLabel);
//        add(new Image("indicator", AbstractDefaultAjaxBehavior.INDICATOR));
//
//        WebMarkupContainer div = new WebMarkupContainer("dummy");
//        div.add(new AbstractAjaxTimerBehavior(Duration.seconds(5)) {
//            @Override
//            protected void onTimer(AjaxRequestTarget target) {
//                target.add(timerLabel);
//            }
//        });
//        add(div);
        final String nowTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
        final Label timerLabel = new Label("timer", nowTime);
        timerLabel.setOutputMarkupId(true);
        add(timerLabel);
        add(new Image("indicator", AbstractDefaultAjaxBehavior.INDICATOR));

        WebMarkupContainer div = new WebMarkupContainer("dummy");
        div.add(new AbstractAjaxTimerBehavior(Duration.seconds(5)) {
            @Override
            protected void onTimer(AjaxRequestTarget target) {
                timerLabel.setDefaultModel(new Model<String>(
                        new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())));
                target.add(timerLabel);
            }
        });
        add(div);
    }
}
