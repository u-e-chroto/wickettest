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
import java.util.HashMap;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.extensions.ajax.markup.html.AjaxLazyLoadPanel;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author yui_inoue
 */
public class PagingNavigatorPanel extends Panel {
    
    public PagingNavigatorPanel(String id, String name, final String value) {
        super(id);
        Label output = new Label("output", name);
        output.setOutputMarkupId(true);
        add(output);
        
        HashMap<String, List<ListElement>> dataMap = createDataMap();
        
        final DataView<ListElement> view = new DataView<ListElement>("holidayData",
                new ListDataProvider<ListElement>(dataMap.get(value)), 5) {
            @Override
            protected void populateItem(Item<ListElement> item) {
                ListElement element = item.getModelObject();
                item.add(new Label("no", new PropertyModel<String>(element, "no")));
                item.add(new Label("monthDay", new PropertyModel<String>(element, "monthDay")));
                item.add(new Label("week", new PropertyModel<String>(element, "week")));
                item.add(new Label("name", new PropertyModel<String>(element, "name")));
            }
        };
        view.setOutputMarkupId(true);
        
        final WebMarkupContainer dataContainer = new WebMarkupContainer("dataContainer");
        dataContainer.setOutputMarkupId(true);
        dataContainer.add(new AjaxLazyLoadPanel("dataView") {
            @Override
            public Component getLazyLoadComponent(String markupId) {
                return new DataListPanel(markupId, value, view);
            }
        });
        
        add(dataContainer);
        add(new AjaxPagingNavigator("paging", view));
    }
    
    private HashMap<String, List<ListElement>> createDataMap() {
        HashMap returnMap = new HashMap();
        List<ListElement> data2010 = Arrays.asList(
                new ListElement("1", "1/1", "��", "����"),
                new ListElement("2", "1/11", "��", "���l�̓�"),
                new ListElement("3", "2/11", "��", "�����L�O�̓�"),
                new ListElement("4", "3/21", "��", "�t���̓�"),
                new ListElement("5", "3/22", "��", "�U�֋x��"),
                new ListElement("6", "4/29", "��", "���a�̓�"),
                new ListElement("7", "5/3", "��", "���@�L�O��"),
                new ListElement("8", "5/4", "��", "�݂ǂ�̓�"),
                new ListElement("9", "5/5", "��", "���ǂ��̓�"),
                new ListElement("10", "7/19", "��", "�C�̓�"),
                new ListElement("11", "9/20", "��", "�h�V�̓�"),
                new ListElement("12", "9/23", "��", "�H���̓�"),
                new ListElement("13", "10/11", "��", "�̈�̓�"),
                new ListElement("14", "11/3", "��", "�����̓�"),
                new ListElement("15", "11/23", "��", "�ΘJ���ӂ̓�"),
                new ListElement("16", "12/23", "��", "�V�c�a����"));
        returnMap.put("2010", data2010);
        
        List<ListElement> data2011 = Arrays.asList(
                new ListElement("1", "1/1", "�y", "����"),
                new ListElement("2", "1/10", "��", "���l�̓�"),
                new ListElement("3", "2/11", "��", "�����L�O�̓�"),
                new ListElement("4", "3/21", "��", "�t���̓�"),
                new ListElement("5", "4/29", "��", "���a�̓�"),
                new ListElement("6", "5/3", "��", "���@�L�O��"),
                new ListElement("7", "5/4", "��", "�݂ǂ�̓�"),
                new ListElement("8", "5/5", "��", "���ǂ��̓�"),
                new ListElement("9", "7/18", "��", "�C�̓�"),
                new ListElement("10", "9/19", "��", "�h�V�̓�"),
                new ListElement("11", "9/23", "��", "�H���̓�"),
                new ListElement("12", "10/10", "��", "�̈�̓�"),
                new ListElement("13", "11/3", "��", "�����̓�"),
                new ListElement("14", "11/23", "��", "�ΘJ���ӂ̓�"),
                new ListElement("15", "12/23", "��", "�V�c�a����"));
        returnMap.put("2011", data2011);
        
        List<ListElement> data2012 = Arrays.asList(
                new ListElement("1", "1/1", "��", "����"),
                new ListElement("2", "1/2", "��", "�U�֋x��"),
                new ListElement("3", "1/9", "��", "���l�̓�"),
                new ListElement("4", "2/11", "�y", "�����L�O�̓�"),
                new ListElement("5", "3/20", "��", "�t���̓�"),
                new ListElement("6", "4/29", "��", "���a�̓�"),
                new ListElement("7", "4/30", "��", "�U�֋x��"),
                new ListElement("8", "5/3", "��", "���@�L�O��"),
                new ListElement("9", "5/4", "��", "�݂ǂ�̓�"),
                new ListElement("10", "5/5", "�y", "���ǂ��̓�"),
                new ListElement("11", "7/16", "��", "�C�̓�"),
                new ListElement("12", "9/17", "��", "�h�V�̓�"),
                new ListElement("13", "9/22", "�y", "�H���̓�"),
                new ListElement("14", "10/8", "��", "�̈�̓�"),
                new ListElement("15", "11/3", "�y", "�����̓�"),
                new ListElement("16", "11/23", "��", "�ΘJ���ӂ̓�"),
                new ListElement("17", "12/23", "��", "�V�c�a����"),
                new ListElement("18", "12/24", "��", "�U�֋x��"));
        returnMap.put("2012", data2012);
        
        List<ListElement> data2013 = Arrays.asList(
                new ListElement("1", "1/1", "��", "����"),
                new ListElement("2", "1/14", "��", "���l�̓�"),
                new ListElement("3", "2/11", "��", "�����L�O�̓�"),
                new ListElement("4", "3/20", "��", "�t���̓�"),
                new ListElement("5", "4/29", "��", "���a�̓�"),
                new ListElement("6", "5/3", "��", "���@�L�O��"),
                new ListElement("7", "5/4", "�y", "�݂ǂ�̓�"),
                new ListElement("8", "5/5", "��", "���ǂ��̓�"),
                new ListElement("9", "5/6", "��", "�U�֋x��"),
                new ListElement("10", "7/15", "��", "�C�̓�"),
                new ListElement("11", "9/16", "��", "�h�V�̓�"),
                new ListElement("12", "9/23", "��", "�H���̓�"),
                new ListElement("13", "10/14", "��", "�̈�̓�"),
                new ListElement("14", "11/3", "��", "�����̓�"),
                new ListElement("15", "11/4", "��", "�U�֋x��"),
                new ListElement("16", "11/23", "�y", "�ΘJ���ӂ̓�"),
                new ListElement("17", "12/23", "��", "�V�c�a����"));
        returnMap.put("2013", data2013);
        
        List<ListElement> data2014 = Arrays.asList(
                new ListElement("1", "1/1", "��", "����"),
                new ListElement("2", "1/13", "��", "���l�̓�"),
                new ListElement("3", "2/11", "��", "�����L�O�̓�"),
                new ListElement("4", "3/21", "��", "�t���̓�"),
                new ListElement("5", "4/29", "��", "���a�̓�"),
                new ListElement("6", "5/3", "�y", "���@�L�O��"),
                new ListElement("7", "5/4", "��", "�݂ǂ�̓�"),
                new ListElement("8", "5/5", "��", "���ǂ��̓�"),
                new ListElement("9", "5/6", "��", "�U�֋x��"),
                new ListElement("10", "7/21", "��", "�C�̓�"),
                new ListElement("11", "9/15", "��", "�h�V�̓�"),
                new ListElement("12", "9/23", "��", "�H���̓�"),
                new ListElement("13", "10/13", "��", "�̈�̓�"),
                new ListElement("14", "11/3", "��", "�����̓�"),
                new ListElement("15", "11/23", "��", "�ΘJ���ӂ̓�"),
                new ListElement("16", "11/24", "��", "�U�֋x��"),
                new ListElement("17", "12/23", "��", "�V�c�a����"));
        returnMap.put("2014", data2014);
        
        List<ListElement> data2015 = Arrays.asList(
                new ListElement("1", "1/1", "��", "����"),
                new ListElement("2", "1/12", "��", "���l�̓�"),
                new ListElement("3", "2/11", "��", "�����L�O�̓�"),
                new ListElement("4", "3/21", "�y", "�t���̓�"),
                new ListElement("5", "4/29", "��", "���a�̓�"),
                new ListElement("6", "5/3", "��", "���@�L�O��"),
                new ListElement("7", "5/4", "��", "�݂ǂ�̓�"),
                new ListElement("8", "5/5", "��", "���ǂ��̓�"),
                new ListElement("9", "5/6", "��", "�U�֋x��"),
                new ListElement("10", "7/20", "��", "�C�̓�"),
                new ListElement("11", "9/21", "��", "�h�V�̓�"),
                new ListElement("12", "9/22", "��", "�����̋x��"),
                new ListElement("13", "9/23", "��", "�H���̓�"),
                new ListElement("14", "10/12", "��", "�̈�̓�"),
                new ListElement("15", "11/3", "��", "�����̓�"),
                new ListElement("16", "11/23", "��", "�ΘJ���ӂ̓�"),
                new ListElement("17", "12/23", "��", "�V�c�a����"));
        returnMap.put("2015", data2015);
        
        return returnMap;
    }
}
