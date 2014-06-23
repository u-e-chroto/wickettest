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

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractAjaxTimerBehavior;
import org.apache.wicket.ajax.AjaxEventBehavior;
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
public class RunAwayTextPage extends WebPage {

    private int part = 0;
    private static final List<String> view = Arrays.asList(
            "                ", "               m", "              mo", "             mou",
            "            mous", "           mouse", "          mouse ", "         mouse o",
            "        mouse ov", "       mouse ove", "      mouse over", "     mouse over ",
            "    mouse over e", "   mouse over ev", "  mouse over eve", " mouse over even",
            "mouse over event");

    public RunAwayTextPage() {
        final WebMarkupContainer updateComponent = new WebMarkupContainer("updateComponent");
        updateComponent.setOutputMarkupId(true);
        add(updateComponent);

        Label t1 = new Label("t1");
        t1.setOutputMarkupId(true);
        updateComponent.add(t1);

        Label t2 = new Label("t2");
        t2.setOutputMarkupId(true);
        updateComponent.add(t2);

        Label t3 = new Label("t3");
        t3.setOutputMarkupId(true);
        updateComponent.add(t3);

        Label t4 = new Label("t4");
        t4.setOutputMarkupId(true);
        updateComponent.add(t4);

        Label t5 = new Label("t5");
        t5.setOutputMarkupId(true);
        updateComponent.add(t5);

        Label t6 = new Label("t6");
        t6.setOutputMarkupId(true);
        updateComponent.add(t6);

        Label t7 = new Label("t7");
        t7.setOutputMarkupId(true);
        updateComponent.add(t7);

        Label t8 = new Label("t8");
        t8.setOutputMarkupId(true);
        updateComponent.add(t8);

        t1.add(getEventBehavior(updateComponent));
        t1.setDefaultModel(new Model<String>("○"));

        final WebMarkupContainer rollComponent = new WebMarkupContainer("rollComponent");
        rollComponent.setOutputMarkupId(true);
        add(rollComponent);

        Label roll = new Label("roll", "マウスオーバーしてください");
        roll.setOutputMarkupId(true);
        roll.add(getRollEvent());
        rollComponent.add(roll);
    }

    private String getNextId(String id) {
//        int no = Integer.parseInt(id.substring(1));
//        no++;
//        if (no > 8) {
//            no -= 8;
//        }
        int no = 0;
        while (no == 0 || no == Integer.parseInt(id.substring(1))) {
            no = new Random().nextInt(8) + 1;
        }
        StringBuilder sb = new StringBuilder("t");
        return new String(sb.append(no));
    }

    private AjaxEventBehavior getEventBehavior(final Component updateComponent) {
        return new AjaxEventBehavior("onMouseOver") {
            @Override
            protected void onEvent(AjaxRequestTarget target) {
                this.getComponent().setDefaultModel(new Model<String>(""));
                this.getComponent().remove(this);
                String nextId = getNextId(this.getComponent().getId());
                Component nextComponent = RunAwayTextPage.this.get("updateComponent").get(nextId);
                nextComponent.add(getEventBehavior(updateComponent));
                nextComponent.setDefaultModel(new Model<String>("○"));
                target.add(updateComponent);
            }
        };
    }

    private AjaxEventBehavior getRollEvent() {
        return new AjaxEventBehavior("onMouseOver") {
            @Override
            protected void onEvent(AjaxRequestTarget target) {
                Component comp = this.getComponent();
                comp.remove(this);
                comp.add(new AbstractAjaxTimerBehavior(Duration.milliseconds(20)) {
                    @Override
                    protected void onTimer(AjaxRequestTarget target) {
                        Component comp = this.getComponent();
                        comp.setDefaultModel(new Model<String>(view.get(part)));
                        part++;
                        if (view.size() <= part) {
                            stop();
                            part = 0;
                            comp.remove(this);
                            comp.add(getRollEvent());
                        }
                        target.add(comp);
                    }
                });
                target.add(comp);
            }
        };
    }
}
