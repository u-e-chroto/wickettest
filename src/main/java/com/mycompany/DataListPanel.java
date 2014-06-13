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
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.data.DataView;

/**
 *
 * @author yui_inoue
 */
public class DataListPanel extends Panel {

    public DataListPanel(String id, String value, DataView<ListElement> view) {
        super(id);

        WebMarkupContainer viewContainer = new WebMarkupContainer("viewContainer");
        viewContainer.setOutputMarkupId(true);
        viewContainer.add(view);
        add(viewContainer);
    }
}

class ListElement implements Serializable {

    private String no;
    private String monthDay;
    private String week;
    private String name;

    public ListElement(String no, String monthDay, String week, String name) {
        super();
        this.no = no;
        this.monthDay = monthDay;
        this.week = week;
        this.name = name;
    }
}